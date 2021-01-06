package com.cebbys.celib.testing

import com.cebbys.celib.Celib
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.text.LiteralText
import net.minecraft.text.Text

class TItem : Item(Settings().group(ItemGroup.MISC)) {
    inline override fun getName(): Text {
        val str = StringBuilder()
        str.append(Celib.rand.nextInt())
        str.append(Celib.rand.nextInt())
        return LiteralText(str.toString())
    }

    inline override fun getName(stack: ItemStack?): Text {
        val str = StringBuilder()
        str.append(Celib.rand.nextInt())
        str.append(Celib.rand.nextInt())
        return LiteralText(str.toString())
    }
}