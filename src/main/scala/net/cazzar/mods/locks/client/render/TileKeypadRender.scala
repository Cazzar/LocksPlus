package net.cazzar.mods.locks.client.render

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
import net.minecraft.tileentity.TileEntity
import net.minecraftforge.client.model.AdvancedModelLoader
import net.minecraft.util.ResourceLocation
import org.lwjgl.opengl.GL11._
import net.cazzar.mods.locks.blocks.tile.TileKeypad
import net.minecraftforge.common.util.ForgeDirection
import net.cazzar.corelib.util.ClientUtil

class TileKeypadRender extends TileEntitySpecialRenderer {
    val model = AdvancedModelLoader.loadModel(new ResourceLocation("locksplus:pinpad.obj"))
    val tex = new ResourceLocation("locksplus:pinpad.png")

    override def renderTileEntityAt(tileentity: TileEntity, x: Double, y: Double, z: Double, partialTick: Float) = {
        glPushMatrix()

        val tile = tileentity.asInstanceOf[TileKeypad]

        tile.facing match {
            case ForgeDirection.NORTH =>
                glTranslated(x + 0.5, y + 0.5, z + 0.02)
                glScaled(0.5, 0.5, 0.5)
                glRotated(-90, 0, 1, 0)
                glRotated(-90, 0, 0, 1)

            case ForgeDirection.SOUTH =>
                glTranslated(x + 0.5, y + 0.5, z + 0.97)
                glScaled(0.5, 0.5, 0.5)
                glRotated(90, 0, 1, 0)
                glRotated(-90, 0, 0, 1)

            case ForgeDirection.WEST =>
                glTranslated(x + 0.02, y + 0.5, z + 0.5)
                glScaled(0.5, 0.5, 0.5)
                glRotated(-90, 0, 1, 0)
                glRotated(-90, 0, 0, 1)
                glRotated(-90, 1, 0, 0)

            case ForgeDirection.EAST =>
                glTranslated(x + 0.97, y + 0.5, z + 0.5)
                glScaled(0.5, 0.5, 0.5)
                glRotated(90, 0, 1, 0)
                glRotated(-90, 0, 0, 1)
                glRotated(-90, 1, 0, 0)
            case _ =>
        }

        ClientUtil.mc().renderEngine.bindTexture(tex)
        model.renderAll()
        glPopMatrix()
    }
}
