package net.cazzar.mods.locks.blocks

import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.world.{IBlockAccess, World}
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.tileentity.TileEntity
import net.cazzar.mods.locks.blocks.tile.TileKeypad
import net.minecraft.entity.EntityLivingBase
import net.minecraft.item.ItemStack
import net.minecraft.util.{AxisAlignedBB, MathHelper}
import net.minecraftforge.common.util.ForgeDirection
import net.minecraft.server.MinecraftServer

class BlockKeypad extends Block(Material.rock) {
    setBlockName("lock_keypad")
    setTickRandomly(true)

    override def breakBlock(world: World, x: Int, y: Int, z: Int, block: Block, meta: Int): Unit = {
        val keypad: TileKeypad = world.getTileEntity(x, y, z).asInstanceOf[TileKeypad]

        world.notifyBlocksOfNeighborChange(x, y, z, this)
        world.notifyBlocksOfNeighborChange(x + keypad.facing.offsetX, y + keypad.facing.offsetY, z + keypad.facing.offsetZ, this)

        super.breakBlock(world, x, y, z, block, meta)
    }

    override def hasTileEntity(metadata: Int): Boolean = true

    override def createTileEntity(world: World, metadata: Int): TileEntity = new TileKeypad

    override def shouldSideBeRendered(world: IBlockAccess, x: Int, y: Int, z: Int, side: Int): Boolean = false

    override def isOpaqueCube: Boolean = false

    override def onBlockActivated(world: World, blockX: Int, blockY: Int, blockZ: Int, player: EntityPlayer, p_149727_6_ : Int, playerX: Float, playerY: Float, playerZ: Float): Boolean = {
        if (!world.isRemote) {
            world.getTileEntity(blockX, blockY, blockZ).asInstanceOf[TileKeypad].checkPinAndActivate("0000")

            true
        }
        else true
    }

    override def canProvidePower: Boolean = true

    override def isProvidingStrongPower(world: IBlockAccess, x: Int, y: Int, z: Int, meta: Int): Int = isProvidingWeakPower(world, x, y, z, meta)

    override def isProvidingWeakPower(world: IBlockAccess, x: Int, y: Int, z: Int, meta: Int): Int = {
        val tile = world.getTileEntity(x, y, z).asInstanceOf[TileKeypad]

        if (MinecraftServer.getServer.getTickCounter - tile.lastActivatedTick <= 20) 15
        else 0
    }

    override def onBlockPlacedBy(world: World, x: Int, y: Int, z: Int, placer: EntityLivingBase, stack: ItemStack) {
        super.onBlockPlacedBy(world, x, y, z, placer, stack)
        val heading = MathHelper.floor_double(placer.rotationYaw * 4F / 360F + 0.5D) & 3
        val tile = world.getTileEntity(x, y, z).asInstanceOf[TileKeypad]

        tile.facing = heading match {
            case 0 => ForgeDirection.SOUTH
            case 1 => ForgeDirection.WEST
            case 2 => ForgeDirection.NORTH
            case 3 => ForgeDirection.EAST

            case _ => ForgeDirection.NORTH
        }
    }

    override def getCollisionBoundingBoxFromPool(world: World, x: Int, y: Int, z: Int): AxisAlignedBB = null

    override def getSelectedBoundingBoxFromPool(world: World, x: Int, y: Int, z: Int): AxisAlignedBB = {
        world.getTileEntity(x, y, z).asInstanceOf[TileKeypad].facing match {
            case ForgeDirection.NORTH => AxisAlignedBB.getBoundingBox(x + 0.22, y + 0.05, z, x + 0.78, y + 0.9, z + 0.1)
            case ForgeDirection.SOUTH => AxisAlignedBB.getBoundingBox(x + 0.22, y + 0.05, z + 0.90, x + 0.78, y + 0.9, z + 1)
            case ForgeDirection.EAST => AxisAlignedBB.getBoundingBox(x + 0.90, y + 0.05, z + 0.22, x + 1, y + 0.9, z + 0.78)
            case ForgeDirection.WEST => AxisAlignedBB.getBoundingBox(x, y + 0.05, z + 0.22, x + 0.1, y + 0.9, z + 0.78)

            case _ => super.getSelectedBoundingBoxFromPool(world, x, y, z)
        }
    }
}
