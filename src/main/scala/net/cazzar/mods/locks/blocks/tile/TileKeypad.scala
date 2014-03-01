package net.cazzar.mods.locks.blocks.tile

import net.minecraftforge.common.util.ForgeDirection
import net.cazzar.corelib.tile.SyncedTileEntity
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.server.MinecraftServer

class TileKeypad extends SyncedTileEntity {
    var facing = ForgeDirection.NORTH
    var lastActivatedTick = -20
    var pinNumber = "0000"

    override def addExtraNBTToPacket(tag: NBTTagCompound) {}

    override def readExtraNBTFromPacket(tag: NBTTagCompound) {}

    override def updateEntity(): Unit = {
        super.updateEntity()

        if (MinecraftServer.getServer.getTickCounter - lastActivatedTick > 20) {
            worldObj.notifyBlocksOfNeighborChange(xCoord, yCoord, zCoord, worldObj.getBlock(xCoord, yCoord, zCoord))
            worldObj.notifyBlocksOfNeighborChange(xCoord + facing.offsetX, yCoord + facing.offsetY, zCoord + facing.offsetZ, worldObj.getBlock(xCoord, yCoord, zCoord))
        }
    }

    override def readFromNBT(tag: NBTTagCompound): Unit = {
        super.readFromNBT(tag)
        facing = ForgeDirection.valueOf(tag.getString("facing"))
        pinNumber = tag.getString("pinNumber")
    }

    override def writeToNBT(tag: NBTTagCompound): Unit = {
        super.writeToNBT(tag)
        tag.setString("facing", facing.name())
        tag.setString("pinNumber", pinNumber)
    }

    def checkPinAndActivate(pin: String): Boolean = {
        if (pin == pinNumber) {
            lastActivatedTick = MinecraftServer.getServer.getTickCounter
            worldObj.notifyBlocksOfNeighborChange(xCoord, yCoord, zCoord, worldObj.getBlock(xCoord, yCoord, zCoord))
            worldObj.notifyBlocksOfNeighborChange(xCoord + facing.offsetX, yCoord + facing.offsetY, zCoord + facing.offsetZ, worldObj.getBlock(xCoord, yCoord, zCoord))

            true
        }
        else false
    }
}
