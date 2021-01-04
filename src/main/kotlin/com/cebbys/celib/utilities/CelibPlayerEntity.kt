package com.cebbys.celib.utilities

import com.cebbys.celib.Celib
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import java.lang.reflect.Field

class CelibPlayerEntity(entity: PlayerEntity) : CelibLivingEntityUtils(entity) {


    companion object {
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
    }
    override fun getValue(name: String) = getValue(entity, name)
    override fun putObject(name: String, obj: Any?) = putObject(entity, name, obj)

    override operator fun get(key: String): Any? = getValue(key)
    override operator fun set(key: String, value: Any?) = putObject(key, value)
    override operator fun invoke(target: String, vararg args: Any?): Any? = FastReflection.create(LivingEntity::class.java.getDeclaredMethod(target)).invoke(entity, args)
}