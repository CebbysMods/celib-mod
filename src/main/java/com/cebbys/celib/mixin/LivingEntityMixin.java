package com.cebbys.celib.mixin;

import com.cebbys.celib.Celib;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.UUID;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements IEntityMixin {
    public LivingEntityMixin() { super(null, null); }

    @Inject(method = "isClimbing", at = @At(value = "RETURN", ordinal = 2), locals = LocalCapture.CAPTURE_FAILHARD, cancellable = true)
    public void canClimb(CallbackInfoReturnable<Boolean> cir, BlockPos blockPos, BlockState blockState) {
        if (Celib.CLIMBABLE.contains(blockState.getBlock())) {
            cir.setReturnValue(true);
        }
    }

    /**
     * @author DomamaN202
     * @reason Optimization
     */
    @Overwrite
    public void fall(double heightDifference, boolean onGround, BlockState landedState, BlockPos landedPosition) {
        if (!this.touchingWater) {
            this.callCheckWaterState();
        }

        if (!this.world.isClient && onGround && this.fallDistance > 0F) {
            this.removeSoulSpeedBoost();
            this.addSoulSpeedBoostIfNeeded();

            if (this.fallDistance > 3F) {
                if (!landedState.isAir()) {
                    ((ServerWorld) this.world).spawnParticles(
                            new BlockStateParticleEffect(ParticleTypes.BLOCK, landedState),
                            this.getX(),
                            this.getY(),
                            this.getZ(),
                            (int) (Math.min(0.2F + MathHelper.ceil(this.fallDistance - 3.0F) / 15.0F, 2.5D) * 150.0D),
                            0.0D,
                            0.0D,
                            0.0D,
                            0.15000000596046448D
                    );
                }
            }
        }

        super.fall(heightDifference, onGround, landedState, landedPosition);
    }

    /**
     * @author DomamaN202
     * @reason Optimization
     */
    @Overwrite
    public void addSoulSpeedBoostIfNeeded() {
        if (!this.getLandingBlockState().isAir()) {
            int i = EnchantmentHelper.getEquipmentLevel(Enchantments.SOUL_SPEED, (LivingEntity) (Object) this);
            if (i > 0 && this.isOnSoulSpeedBlock()) {
                EntityAttributeInstance entityAttributeInstance = this.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);
                if (entityAttributeInstance == null) {
                    return;
                }

                entityAttributeInstance.addTemporaryModifier(new EntityAttributeModifier(SOUL_SPEED_BOOST_ID, "Soul speed boost", (0.03F * (1.0F + (float)i * 0.35F)), EntityAttributeModifier.Operation.ADDITION));
                if (this.random.nextFloat() < 0.04F) {
                    ItemStack itemStack = this.getEquippedStack(EquipmentSlot.FEET);
                    itemStack.damage(1, (LivingEntity) (Object) this, (livingEntity) -> {
                        livingEntity.sendEquipmentBreakStatus(EquipmentSlot.FEET);
                    });
                }
            }
        }

    }

    @Shadow protected abstract void removeSoulSpeedBoost();
    @Shadow protected abstract boolean isOnSoulSpeedBlock();
    @Shadow public abstract ItemStack getEquippedStack(EquipmentSlot slot);
    @Shadow public abstract EntityAttributeInstance getAttributeInstance(EntityAttribute attribute);

    @Final @Shadow private static final UUID SOUL_SPEED_BOOST_ID = UUID.fromString("87f46a96-686f-4796-b035-22e16ee9e038");
}