package com.cebbys.celib.mixin;

import com.cebbys.celib.Celib;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MovementType;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.crash.CrashException;
import net.minecraft.util.crash.CrashReport;
import net.minecraft.util.crash.CrashReportSection;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

import static net.minecraft.entity.Entity.squaredHorizontalLength;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Shadow public boolean  noClip;
    @Shadow public boolean  horizontalCollision;
    @Shadow public boolean  verticalCollision;
    @Shadow public float    horizontalSpeed;
    @Shadow public float    distanceTraveled;
    @Shadow public World    world;
    @Shadow protected boolean   onGround;
    @Shadow protected boolean   touchingWater;
    @Shadow protected Vec3d     movementMultiplier;
    @Shadow private int     fireTicks;
    @Shadow private float   nextStepSoundDistance;
    @Shadow private float   nextFlySoundDistance;
    @Shadow private Vec3d   velocity;
    @Shadow private Vec3d   pos;
    @Shadow private Box     entityBounds;
    @Shadow private Entity  vehicle;
    @Shadow @Final private EntityType<?> type;

    @Shadow public abstract void            populateCrashReport(CrashReportSection section);
    @Shadow public abstract void            playSound(SoundEvent sound, float volume, float pitch);
    @Shadow public abstract void            moveToBoundingBoxCenter();
    @Shadow public abstract Entity          getPrimaryPassenger();
    @Shadow public abstract List<Entity>    getPassengerList();
    @Shadow protected abstract void     fall(double heightDifference, boolean onGround, BlockState landedState, BlockPos landedPosition);
    @Shadow protected abstract void     playSwimSound(float volume);
    @Shadow protected abstract void     playStepSound(BlockPos pos, BlockState state);
    @Shadow protected abstract void     checkBlockCollision();
    @Shadow protected abstract boolean  isBeingRainedOn();
    @Shadow protected abstract boolean  isInsideBubbleColumn();
    @Shadow protected abstract boolean  canClimb();
    @Shadow protected abstract boolean  getFlag(int index);
    @Shadow protected abstract boolean  hasWings();
    @Shadow protected abstract int      getBurningDuration();
    @Shadow protected abstract float    playFlySound(float distance);
    @Shadow protected abstract float    getVelocityMultiplier();
    @Shadow protected abstract Vec3d    adjustMovementForPiston(Vec3d movement);
    @Shadow protected abstract Vec3d    adjustMovementForCollisions(Vec3d movement); // Usable
    @Shadow protected abstract Vec3d    adjustMovementForSneaking(Vec3d movement, MovementType type); // Overrideable
//    @Shadow protected abstract BlockPos getLandingPos(); // NeedToOverwrite

    /**
     * @author DomamaN202
     * @reason Optimization
     */
    @Overwrite
    protected BlockPos getLandingPos() {
        BlockPos blockPos = new BlockPos(MathHelper.floor(this.pos.x), MathHelper.floor(this.pos.y - 0.20000000298023224D), MathHelper.floor(this.pos.z));
        if (this.world.getBlockState(blockPos).isAir()) {
            Block block = this.world.getBlockState(blockPos.down()).getBlock();
            if (BlockTags.FENCES.contains(block) || BlockTags.WALLS.contains(block) || block instanceof FenceGateBlock) {
                return blockPos.down();
            }
        }

        return blockPos;
    }

    /**
     * @author DomamaN202
     * @reason Optimization
     */
    @Overwrite
    public void move(MovementType type, Vec3d movement) {
        if (this.noClip) {
            this.entityBounds = this.entityBounds.offset(movement);
            this.moveToBoundingBoxCenter();
        } else {
            if (type == MovementType.PISTON) {
                movement = this.adjustMovementForPiston(movement);
                if (movement.equals(Vec3d.ZERO)) {
                    return;
                }
            }

            this.world.getProfiler().push("move");
            if (this.movementMultiplier.lengthSquared() > 1.0E-7D) {
                movement = movement.multiply(this.movementMultiplier);
                this.movementMultiplier = Vec3d.ZERO;
                this.velocity = Vec3d.ZERO;
            }

            movement = this.adjustMovementForSneaking(movement, type);
            Vec3d vec3d = this.adjustMovementForCollisions(movement);
            if (vec3d.lengthSquared() > 1.0E-7D) {
                this.entityBounds = this.entityBounds.offset(vec3d);
                this.moveToBoundingBoxCenter();
            }

            this.world.getProfiler().pop();
            this.world.getProfiler().push("rest");
            this.horizontalCollision = !MathHelper.approximatelyEquals(movement.x, vec3d.x) || !MathHelper.approximatelyEquals(movement.z, vec3d.z);
            this.verticalCollision = movement.y != vec3d.y;
            this.onGround = this.verticalCollision && movement.y < 0.0D;
            BlockPos blockPos = this.getLandingPos();
            BlockState blockState = this.world.getBlockState(blockPos);
            this.fall(vec3d.y, this.onGround, blockState, blockPos);
            Vec3d vec3d2 = this.velocity;
            if (movement.x != vec3d.x) {
                this.velocity = new Vec3d(0.0D, vec3d2.y, vec3d2.z);
            }

            if (movement.z != vec3d.z) {
                this.velocity = new Vec3d(vec3d2.x, vec3d2.y, 0.0D);
            }

            Block block = blockState.getBlock();
            if (movement.y != vec3d.y) {
                block.onEntityLand(this.world, (Entity) (Object) this);
            }

            if (this.onGround && !this.getFlag(1)) {
                block.onSteppedOn(this.world, blockPos, (Entity) (Object) this);
            }

            if (this.canClimb() && this.vehicle == null) {
                double d = vec3d.x;
                double e = vec3d.y;
                double f = vec3d.z;
                if (!block.isIn(BlockTags.CLIMBABLE)) {
                    e = 0.0D;
                }

                this.horizontalSpeed = (float) (this.horizontalSpeed + MathHelper.sqrt(squaredHorizontalLength(vec3d)) * 0.6D);
                this.distanceTraveled = (float) (this.distanceTraveled + MathHelper.sqrt(d * d + e * e + f * f) * 0.6D);
                if (this.distanceTraveled > this.nextStepSoundDistance && !blockState.isAir()) {
                    this.nextStepSoundDistance = this.distanceTraveled + 1;
                    if (this.touchingWater) {
                        Entity entity = !this.getPassengerList().isEmpty() && this.getPrimaryPassenger() != null ? this.getPrimaryPassenger() : (Entity) (Object) this;
                        Vec3d vec3d3 = entity.getVelocity();
                        float h = MathHelper.sqrt(vec3d3.x * vec3d3.x * 0.20000000298023224D + vec3d3.y * vec3d3.y + vec3d3.z * vec3d3.z * 0.20000000298023224D) * (entity == (Object) this ? 0.35F : 0.4F);
                        if (h > 1.0F) {
                            h = 1.0F;
                        }

                        this.playSwimSound(h);
                    } else {
                        this.playStepSound(blockPos, blockState);
                    }
                } else if (this.distanceTraveled > this.nextFlySoundDistance && this.hasWings() && blockState.isAir()) {
                    this.nextFlySoundDistance = this.playFlySound(this.distanceTraveled);
                }
            }

            try {
                this.checkBlockCollision();
            } catch (Throwable var18) {
                CrashReport crashReport = CrashReport.create(var18, "Checking entity block collision");
                CrashReportSection crashReportSection = crashReport.addElement("Entity being checked for collision");
                this.populateCrashReport(crashReportSection);
                throw new CrashException(crashReport);
            }

            float i = this.getVelocityMultiplier();
            this.velocity = this.velocity.multiply(i, 1.0D, i);

            if (this.world.method_29556(this.entityBounds.contract(0.001D)).noneMatch((blockStatex) -> blockStatex.isIn(BlockTags.FIRE) || blockStatex.isOf(Blocks.LAVA)) && this.fireTicks <= 0) {
                this.fireTicks = -this.getBurningDuration();
            }
            if ((this.touchingWater || this.isBeingRainedOn() || this.isInsideBubbleColumn()) && (!this.type.isFireImmune() && (this.fireTicks > 0 || this.world != null && this.world.isClient && this.getFlag(0)))) {
                this.playSound(SoundEvents.ENTITY_GENERIC_EXTINGUISH_FIRE, 0.7F, 1.6F + (Celib.rand.nextFloat() - Celib.rand.nextFloat()) * 0.4F);
                this.fireTicks = -this.getBurningDuration();
            }

            this.world.getProfiler().pop();
        }
    }
}
