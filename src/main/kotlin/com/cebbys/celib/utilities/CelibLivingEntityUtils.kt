package com.cebbys.celib.utilities

import com.cebbys.celib.Celib
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageTracker

class CelibLivingEntityUtils(val entity: LivingEntity) {
    fun setDamageTracker(tracker: DamageTracker) {
        Celib.unsafe.putObject(
            entity,
            Celib.unsafe.objectFieldOffset(
                LivingEntity::class.java.getDeclaredField("damageTracker")
            ),
            tracker
        )
    }
}