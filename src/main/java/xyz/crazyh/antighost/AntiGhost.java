package xyz.crazyh.antighost;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import xyz.crazyh.antighost.config.AntiGhostConfig;
import xyz.crazyh.antighost.util.AntiGhostBlock;
import org.lwjgl.input.Keyboard;
import xyz.crazyh.antighost.util.AntiGhostInventory;

@Mod(   modid = AntiGhost.MODID,
        name = AntiGhost.NAME,
        version = AntiGhost.VERSION,
        guiFactory = AntiGhost.GUIFACTORY
)
public class AntiGhost {
    public static final String MODID = "antighost";
    public static final String NAME = "Anti Ghost";
    public static final String VERSION = "1.1.0";
    public static final String GUIFACTORY = "xyz.crazyh.antighost.config.AntiGhostConfigFactory";

    static KeyBinding clearGB;
    static KeyBinding refreshInv;
    private int tickCounterGB = 0;
    private int tickCounterRI = 0;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {

    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
        ClientRegistry.registerKeyBinding(clearGB = new KeyBinding("Clear ghost blocks", Keyboard.KEY_G, NAME));
        ClientRegistry.registerKeyBinding(refreshInv = new KeyBinding("Refresh inventory", Keyboard.KEY_H, NAME));
    }

    @SubscribeEvent
    public void keyPressed(final InputEvent.KeyInputEvent e) {
        EntityPlayerSP playerSP = Minecraft.getMinecraft().player;

        if (clearGB.isPressed()){
            AntiGhostBlock.clearGhostBlock(playerSP);
            playerSP.sendMessage(new TextComponentString("clearing ghost blocks around you"));
        }

        if (refreshInv.isPressed()) {
            AntiGhostInventory.refreshInv(playerSP);
            playerSP.sendMessage(new TextComponentString("inventory refreshed"));
        }
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {

            if (AntiGhostConfig.autoClearGhostBlockFlag) {
                tickCounterGB++;
                if (tickCounterGB >= AntiGhostConfig.autoClearGhostBlockInterval) {
                    tickCounterGB = 0;

                    EntityPlayerSP playerSP = Minecraft.getMinecraft().player;
                    AntiGhostBlock.clearGhostBlock(playerSP);
                }
            }

            if (AntiGhostConfig.autoRefreshInventoryFlag) {
                tickCounterRI++;
                if (tickCounterRI >= AntiGhostConfig.autoRefreshInventoryInterval) {
                    tickCounterRI = 0;

                    EntityPlayerSP playerSP = Minecraft.getMinecraft().player;
                    AntiGhostInventory.refreshInv(playerSP);
                }
            }
        }
    }

    //reload config on changed
    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equalsIgnoreCase(AntiGhost.MODID)) {
            ConfigManager.sync(AntiGhost.MODID, Config.Type.INSTANCE);
        }
        System.out.println(event.getResult());
        System.out.println("reloaded!");
    }
}
