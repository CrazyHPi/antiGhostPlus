package xyz.crazyh.antighost.config;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.Name;
import net.minecraftforge.common.config.Config.RangeInt;

import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import xyz.crazyh.antighost.AntiGhost;

@Config(modid = AntiGhost.MODID)
public class AntiGhostConfig {

    @Name("autoClearGhostBlockSwitch")
    @Comment("toggle for auto clear ghost block")
    public static boolean autoClearGhostBlockFlag;

    @Name("autoClearGhostBlockInterval")
    @Comment("interval between auto clearing ghost block")
    @RangeInt(min = 1, max = 6000)
    public static int autoClearGhostBlockInterval = 100;
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


