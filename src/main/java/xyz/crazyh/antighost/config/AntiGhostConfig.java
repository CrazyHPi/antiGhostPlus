package xyz.crazyh.antighost.config;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.Name;
import net.minecraftforge.common.config.Config.RangeInt;

/*import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
*/

import xyz.crazyh.antighost.AntiGhost;

@Config(modid = AntiGhost.MODID)
public class AntiGhostConfig {

    @Name("auto Clear Ghost Block Switch")
    @Comment("on/offtoggle for auto clear ghost block")
    public static boolean autoClearGhostBlockFlag = false;

    @Name("auto Clear Ghost Block Interval")
    @Comment("interval between auto clearing ghost block, count in gameticks, default is 5 sec (5*20gt)")
    @RangeInt(min = 1, max = 6000)
    public static int autoClearGhostBlockInterval = 100;

    @Name("auto Refesh Inventory Switch")
    @Comment("on/off toggle for auto refresh inventory")
    public static boolean autoRefreshInventoryFlag = false;

    @Name("auto Refresh Inventory Interval")
    @Comment("interval between auto refreshing inventory, count in gameticks, default is 5 sec (5*20gt)")
    @RangeInt(min = 1, max = 6000)
    public static int autoRefreshInventoryInterval = 100;
}

/*
@Mod.EventBusSubscriber(modid = AntiGhost.MODID)
class ConfigSyncHandler {
    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equalsIgnoreCase(AntiGhost.MODID)) {
            ConfigManager.sync(AntiGhost.MODID, Config.Type.INSTANCE);
        }
        System.out.println(event.getResult());
        System.out.println("reloaded!");
    }
}
*/


