package xyz.crazyh.antighost.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class AntiGhostBlock {
    public static void clearGhostBlock(EntityPlayerSP playerSP) {
        Minecraft minecraft = Minecraft.getMinecraft();
        NetHandlerPlayClient client = minecraft.getConnection();

        if (client == null) {
            return;
        }

        BlockPos pos = playerSP.getPosition();

        int range = 4;

        for (int i = -range; i <= range; i++) {
            for (int j = -range; j <= range ; j++) {
                for (int k = -range; k <=range ; k++) {
                    CPacketPlayerDigging packet= new CPacketPlayerDigging(
                            CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK,
                            new BlockPos(pos.getX() + i, pos.getY() + j, pos.getZ() + k),
                            EnumFacing.UP
                    );

                    client.sendPacket(packet);
                }
            }

        }
    }
}
