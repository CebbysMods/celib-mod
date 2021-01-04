package com.cebbys.celib.testing

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.item.ItemUsageContext
import net.minecraft.text.LiteralText
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand

class TItem : Item(Settings().group(ItemGroup.MISC)) {
    override fun useOnBlock(context: ItemUsageContext?): ActionResult {
        return ActionResult.SUCCESS
    }

    override fun useOnEntity(stack: ItemStack?, user: PlayerEntity?, entity: LivingEntity?, hand: Hand?): ActionResult {
        return ActionResult.SUCCESS
    }
}