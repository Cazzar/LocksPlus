package net.cazzar.mods.locks.client.render

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
import net.minecraft.tileentity.TileEntity
import net.minecraftforge.client.model.AdvancedModelLoader
import net.minecraft.util.ResourceLocation
import org.lwjgl.opengl.GL11._

class TileKeypadRender extends TileEntitySpecialRenderer {
    val model = AdvancedModelLoader.loadModel(new ResourceLocation("locksplus:pinpad.obj"))
    override def renderTileEntityAt(tile: TileEntity, x: Double, y: Double, z: Double, partialTick: Float) = {
        glPushMatrix()


        /**
         * attached south
        glTranslated(x + 0.5, y + 0.5, z + 0.97)
        glScaled(0.5, 0.5, 0.5)
        glRotated(90, 0, 1, 0)
        glRotated(-90, 0, 0, 1)
        */

        /**
         * Attached North
        glTranslated(x + 0.5, y + 0.5, z + 0.02)
        glScaled(0.5, 0.5, 0.5)
        glRotated(-90, 0, 1, 0)
        glRotated(-90, 0, 0, 1)
        */

        /**
         * Attached west
        glTranslated(x + 0.02, y + 0.5, z + 0.5)
        glScaled(0.5, 0.5, 0.5)
        glRotated(-90, 0, 1, 0)
        glRotated(-90, 0, 0, 1)
        glRotated(-90, 1, 0, 0)
        */

        /**
         * Attached west */
        glTranslated(x + 0.97, y + 0.5, z + 0.5)
        glScaled(0.5, 0.5, 0.5)
        glRotated(90, 0, 1, 0)
        glRotated(-90, 0, 0, 1)
        glRotated(-90, 1, 0, 0)
//        */

        //TODO: texture and bind
        model.renderAll()
        glPopMatrix()
    }
}
