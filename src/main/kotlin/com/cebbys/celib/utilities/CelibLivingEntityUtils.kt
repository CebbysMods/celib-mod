package com.cebbys.celib.utilities

import com.cebbys.celib.Celib
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityDimensions
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.attribute.AttributeContainer
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.entity.damage.DamageTracker
import net.minecraft.entity.data.TrackedData
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.item.ItemStack
import net.minecraft.util.collection.DefaultedList
import net.minecraft.util.math.BlockPos
import java.lang.reflect.Field
import java.lang.reflect.Method
import java.util.*

open class CelibLivingEntityUtils(override val entity: LivingEntity): CelibEntityUtils(entity) {
    companion object {
        var SPRINTING_SPEED_BOOST_ID
            set(uuid) = putObject("SPRINTING_SPEED_BOOST_ID", uuid)
            get() = getValue("SPRINTING_SPEED_BOOST_ID") as UUID
        var SOUL_SPEED_BOOST_ID
            set(uuid) = putObject("SOUL_SPEED_BOOST_ID", uuid)
            get() = getValue("SOUL_SPEED_BOOST_ID") as UUID
        var SPRINTING_SPEED_BOOST
            set(eam) = putObject("SPRINTING_SPEED_BOOST", eam)
            get() = getValue("SPRINTING_SPEED_BOOST") as EntityAttributeModifier
        var LIVING_FLAGS: TrackedData<Byte>
            set(livingFlags) = putObject("LIVING_FLAGS", livingFlags)
            get() = throwCast(getValue("LIVING_FLAGS"))
        var HEALTH: TrackedData<Float>
            set(health) = putObject("HEALTH", health)
            get() = throwCast(getValue("HEALTH"))
        var POTION_SWIRLS_COLOR: TrackedData<Int>
            set(psc) = putObject("POTION_SWIRLS_COLOR", psc)
            get() = throwCast(getValue("POTION_SWIRLS_COLOR"))
        var POTION_SWIRLS_AMBIENT: TrackedData<Boolean>
            set(psa) = putObject("POTION_SWIRLS_AMBIENT", psa)
            get() = throwCast(getValue("POTION_SWIRLS_AMBIENT"))
        var STUCK_ARROW_COUNT: TrackedData<Int>
            set(sac) = putObject("STUCK_ARROW_COUNT", sac)
            get() = throwCast(getValue("STUCK_ARROW_COUNT"))
        var STINGER_COUNT: TrackedData<Int>
            set(sc) = putObject("STINGER_COUNT", sc)
            get() = throwCast(getValue("STINGER_COUNT"))
        var SLEEPING_POSITION: TrackedData<Optional<BlockPos>>
            set(sp) = putObject("SLEEPING_POSITION", sp)
            get() = throwCast(getValue("SLEEPING_POSITION"))
        var SLEEPING_DIMENSIONS: EntityDimensions
            set(sd) = putObject("SLEEPING_DIMENSIONS", sd)
            get() = getValue("SLEEPING_DIMENSIONS") as EntityDimensions

        inline fun getDeclaredField(name: String): Field =
            LivingEntity::class.java.getDeclaredField(name)
        inline fun getObjectFieldOffset(field: Field) =
            Celib.unsafe.objectFieldOffset(field)
        inline fun putObject(entity: LivingEntity, name: String, obj: Any?) =
            Celib.unsafe.putObject(
                entity,
                getObjectFieldOffset(getDeclaredField(name)),
                obj)
        inline fun getValue(entity: LivingEntity, name: String): Any? =
            FastReflection.create(
                getDeclaredField(name))
                .get(entity)

        inline fun putObject(name: String, obj: Any?) =
            Celib.unsafe.putObject(
                LivingEntity::class.java,
                getObjectFieldOffset(getDeclaredField(name)),
                obj)
        inline fun getValue(name: String): Any? =
            FastReflection.create(
                getDeclaredField(name)
            ).get(null)
    }
    var attributes: AttributeContainer
        set(attributes) = putObject("attributes", attributes)
        get() = getValue("attributes") as AttributeContainer
    var damageTracker: DamageTracker
        set(tracker) = putObject("damageTracker", tracker)
        get() = getValue("damageTracker") as DamageTracker
    var activeStatusEffects: Map<StatusEffect, StatusEffectInstance>
        set(ase) = putObject("activeStatusEffects", ase)
        get() = throwCast(getValue("activeStatusEffects"))
    var equippedHand: DefaultedList<ItemStack>
        set(eh) = putObject("equippedHand", eh)
        get() = throwCast(getValue("equippedHand"))
    var equippedArmor: DefaultedList<ItemStack>
        set(ar) = putObject("equippedArmor", ar)
        get() = throwCast(getValue("equippedArmor"))

    override fun getValue(name: String) = getValue(entity, name)
    override fun putObject(name: String, obj: Any?) = putObject(entity, name, obj)

    override operator fun get(key: String): Any? = getValue(key)
    override operator fun set(key: String, value: Any?) = putObject(key, value)
    override operator fun invoke(target: String, vararg args: Any?): Any? = FastReflection.create(LivingEntity::class.java.getDeclaredMethod(target)).invoke(entity, args)
}