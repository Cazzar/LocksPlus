package net.cazzar.mods.locks.blocks

import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.world.{IBlockAccess, World}
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.Blocks
import net.minecraft.tileentity.TileEntity
import net.cazzar.mods.locks.blocks.tile.TileKeypad

class BlockKeypad extends Block(Material.rock) {
    setBlockName("lock_keypad")

    override def hasTileEntity(metadata: Int): Boolean = true

    override def createTileEntity(world: World, metadata: Int): TileEntity = {
        println("TILE!")
        new TileKeypad
    }

    override def shouldSideBeRendered(world: IBlockAccess, x: Int, y: Int, z: Int, side: Int): Boolean = false

    override def isOpaqueCube: Boolean = false

    override def onBlockActivated(world: World, blockX: Int, blockY: Int, blockZ: Int, player: EntityPlayer, p_149727_6_ : Int, playerX: Float, playerY: Float, playerZ: Float): Boolean = {
        false
    }

    override def canProvidePower: Boolean = true

    override def isProvidingStrongPower(world: IBlockAccess, x: Int, y: Int, z: Int, meta: Int): Int =  isProvidingWeakPower(world, x, y, z, meta)

    override def isProvidingWeakPower(world: IBlockAccess, x: Int, y: Int, z: Int, meta: Int): Int = 15
}
