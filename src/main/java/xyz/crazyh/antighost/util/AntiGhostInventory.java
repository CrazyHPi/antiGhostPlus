package xyz.crazyh.antighost.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.init.Items;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.CPacketClickWindow;

public class AntiGhostInventory {
    //back port from Fallen_Breath's Tweakermore
    public static void refreshInv(EntityPlayerSP playerSP) {
        Minecraft minecraft = Minecraft.getMinecraft();
        NetHandlerPlayClient client = minecraft.getConnection();

        if (client != null && playerSP != null) {
            ItemStack itemStack = new ItemStack(Items.APPLE);
            itemStack.getOrCreateSubCompound("resync").setDouble("resync", Double.NaN);
            client.sendPacket(new CPacketClickWindow(
                    playerSP.inventoryContainer.windowId,
                    -999,
                    2,
                    ClickType.QUICK_CRAFT,
                    itemStack,
                    playerSP.inventoryContainer.getNextTransactionID(playerSP.inventory)
            ));
        }
    }
}
