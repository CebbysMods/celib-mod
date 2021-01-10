package com.cebbys.celib.utilities

import com.cebbys.celib.Celib
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityPose
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.data.DataTracker
import net.minecraft.entity.data.TrackedData
import net.minecraft.item.ItemStack
import net.minecraft.text.Text
import net.minecraft.util.math.Box
import org.apache.logging.log4j.Logger
import java.lang.reflect.Field
import java.util.*
import java.util.concurrent.atomic.AtomicInteger

open class CelibEntityUtils(open val entity: Entity) {
    companion object {
        var LOGGER
            set(logger) = putObject("LOGGER", logger)
            get() = getValue("LOGGER") as Logger
        var MAX_ENTITY_ID
            set(mei) = putObject("MAX_ENTITY_ID", mei)
            get() = getValue("MAX_ENTITY_ID") as AtomicInteger
        var EMPTY_STACK_LIST: List<ItemStack>
            set(esl) = putObject("EMPTY_STACK_LIST", esl)
            get() = throwCast(getValue("EMPTY_STACK_LIST"))
        var NULL_BOX
            set(nb) = putObject("NULL_BOX", nb)
            get() = getValue("NULL_BOX") as Box
        var renderDistanceMultiplier
            set(rdm) = putObject("renderDistanceMultiplier", rdm)
            get() = getValue("renderDistanceMultiplier") as Double

        private fun putObject(name: String, obj: Any?) = Celib.unsafe.putObject(
            Entity::class.java,
            Celib.unsafe.staticFieldOffset(FastReflection.getField(Entity::class.java, name)),
            obj
        )
        private fun getValue(name: String) = FastReflection.create(FastReflection.getField(Entity::class.java, name)).get(null)
    }

    var type: EntityType<*>
        set(type) = putObject("type", type)
        get() = throwCast(getValue("type"))
    var passengerList: List<Entity>
        set(pl) = putObject("passengerList", pl)
        get() = throwCast(getValue("passengerList"))
    var random
        set(random) = putObject("random", random)
        get() = getValue("random") as Random
    var dataTracker
        set(dt) = putObject("dataTracker", dt)
        get() = getValue("dataTracker") as DataTracker
    var FLAGS: TrackedData<Byte>
        set(flags) = putObject("FLAGS", flags)
        get() = throwCast(getValue("FLAGS"))
    var AIR: TrackedData<Int>
        set(air) = putObject("AIR", air)
        get() = throwCast(getValue("AIR"))
    var CUSTOM_NAME: TrackedData<Optional<Text>>
        set(cn) = putObject("CUSTOM_NAME", cn)
        get() = throwCast(getValue("CUSTOM_NAME"))
    var NAME_VISIBLE: TrackedData<Boolean>
        set(nv) = putObject("CUSTOM_NAME", nv)
        get() = throwCast(getValue("CUSTOM_NAME"))
    var SILENT: TrackedData<Boolean>
        set(silent) = putObject("CUSTOM_NAME", silent)
        get() = throwCast(getValue("CUSTOM_NAME"))
    var NO_GRAVITY: TrackedData<Boolean>
        set(ng) = putObject("NO_GRAVITY", ng)
        get() = throwCast(getValue("NO_GRAVITY"))
    var POSE: TrackedData<EntityPose>
        set(pose) = putObject("NO_GRAVITY", pose)
        get() = throwCast(getValue("NO_GRAVITY"))
    var scoreboardTags: Set<String>
        set(st) = putObject("scoreboardTags", st)
        get() = throwCast(getValue("scoreboardTags"))
    var pistonMovementDelta
        set(pmd) = putObject("pistonMovementDelta", pmd)
        get() = getValue("pistonMovementDelta") as DoubleArray

    private fun putObject(name: String, obj: Any?) = Celib.unsafe.putObject(
        entity,
        Celib.unsafe.staticFieldOffset(FastReflection.getField(Entity::class.java, name)),
        obj
    )
    private fun getValue(name: String) = FastReflection.create(FastReflection.getField(Entity::class.java, name)).get(entity)
}