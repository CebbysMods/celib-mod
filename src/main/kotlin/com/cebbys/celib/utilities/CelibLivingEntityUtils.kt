package com.cebbys.celib.utilities

import com.cebbys.celib.Celib
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
import java.util.*

class CelibLivingEntityUtils(val entity: LivingEntity) {
    fun setSPRINTING_SPEED_BOOST_ID(uuid: UUID) =
        putObject("SPRINTING_SPEED_BOOST_ID", uuid)
    fun setSOUL_SPEED_BOOST_ID(uuid: UUID) =
        putObject("SOUL_SPEED_BOOST_ID", uuid)
    fun setSPRINTING_SPEED_BOOST(eam: EntityAttributeModifier) =
        putObject("SPRINTING_SPEED_BOOST", eam)
    fun setLIVING_FLAGS(livingFlags: TrackedData<Byte>) =
        putObject("LIVING_FLAGS", livingFlags)
    fun setHEALTH(health: TrackedData<Float>) =
        putObject("HEALTH", health)
    fun setPOTION_SWIRLS_COLOR(psc: TrackedData<Int>) =
        putObject("POTION_SWIRLS_COLOR", psc)
    fun setPOTION_SWIRLS_AMBIENT(psa: TrackedData<Boolean>) =
        putObject("POTION_SWIRLS_AMBIENT", psa)
    fun setSTUCK_ARROW_COUNT(sac: TrackedData<Int>) =
        putObject("STUCK_ARROW_COUNT", sac)
    fun setSTINGER_COUNT(sc: TrackedData<Int>) =
        putObject("STINGER_COUNT", sc)
    fun setSLEEPING_POSITION(sp: TrackedData<Optional<BlockPos>>) =
        putObject("SLEEPING_POSITION", sp)
    fun setSLEEPING_DIMENSIONS(sd: EntityDimensions) =
        putObject("SLEEPING_DIMENSIONS", sd)
    fun setAttributes(attributes: AttributeContainer) =
        putObject("attributes", attributes)
    fun setDamageTracker(tracker: DamageTracker) =
        putObject("damageTracker", tracker)
    fun setActiveStatusEffects(ase: Map<StatusEffect, StatusEffectInstance>) =
        putObject("activeStatusEffects", ase)
    fun setEquippedHand(eh: DefaultedList<ItemStack>) =
        putObject("equippedHand", eh)
    fun setEquippedArmor(ar: DefaultedList<ItemStack>) =
        putObject("equippedArmor", ar)

    inline fun getDeclaredField(name: String): Field = LivingEntity::class.java.getDeclaredField(name)
    inline fun getObjectFieldOffset(field: Field) = Celib.unsafe.objectFieldOffset(field)
    inline fun putObject(name: String, obj: Any?) = Celib.unsafe.putObject(
        entity,
        getObjectFieldOffset(getDeclaredField(name)),
        obj
    )
}