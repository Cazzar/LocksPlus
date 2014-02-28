package net.cazzar.mods.locks

import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.event.FMLPreInitializationEvent
import cpw.mods.fml.common.registry.GameRegistry
import net.cazzar.mods.locks.blocks.BlockKeypad
import net.cazzar.mods.locks.blocks.tile.TileKeypad
import cpw.mods.fml.client.registry.{ClientRegistry, RenderingRegistry}
import net.cazzar.mods.locks.client.render.TileKeypadRender

@Mod(modid = "LocksPlus", modLanguage = "scala")
object LocksPlus {
    @Mod.EventHandler
    def preInit(e: FMLPreInitializationEvent) = {
        val block = new BlockKeypad()
        GameRegistry.registerBlock(block, "keypad")
        GameRegistry.registerTileEntity(classOf[TileKeypad], "keypad")

        if (e.getSide.isClient) {
            ClientRegistry.bindTileEntitySpecialRenderer(classOf[TileKeypad], new TileKeypadRender);
        }
    }
}
