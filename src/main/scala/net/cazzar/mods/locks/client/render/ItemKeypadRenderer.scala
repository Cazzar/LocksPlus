package net.cazzar.mods.locks.client.render

import net.minecraftforge.client.IItemRenderer
import net.minecraft.item.ItemStack
import net.minecraftforge.client.IItemRenderer.{ItemRendererHelper, ItemRenderType}
import net.minecraftforge.client.model.AdvancedModelLoader
import net.minecraft.util.ResourceLocation
import org.lwjgl.opengl.GL11._
import net.cazzar.corelib.util.ClientUtil

class ItemKeypadRenderer extends IItemRenderer {
    val model = AdvancedModelLoader.loadModel(new ResourceLocation("locksplus:pinpad.obj"))
    val tex = new ResourceLocation("locksplus:pinpad.png")

    override def renderItem(`type`: ItemRenderType, item: ItemStack, data: AnyRef*): Unit = {
        glPushMatrix()
        glScaled(0.75, 0.75, 0.75)
        glRotated(-90, 0, 0, 1)
        glRotated(90, 1, 0, 0)
        ClientUtil.mc().renderEngine.bindTexture(tex);
        model.renderAll()
        glPopMatrix()
    }

    override def shouldUseRenderHelper(`type`: ItemRenderType, item: ItemStack, helper: ItemRendererHelper): Boolean = true

    override def handleRenderType(item: ItemStack, `type`: ItemRenderType): Boolean = true
}
