package xyz.crazyh.antighost.config;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.IModGuiFactory;
import net.minecraftforge.fml.client.config.GuiConfig;
import xyz.crazyh.antighost.AntiGhost;

import java.util.Set;

public class AntiGhostConfigFactory implements IModGuiFactory {
    @Override
    public void initialize(Minecraft minecraftInstance) {

    }

    @Override
    public boolean hasConfigGui() {
        return true;
    }

    @Override
    public GuiScreen createConfigGui(GuiScreen parentScreen) {
        return new GuiConfig(parentScreen,
                ConfigElement.from(AntiGhostConfig.class).getChildElements(),
                AntiGhost.MODID,
                false,
                false,
                AntiGhost.NAME);
    }

    @Override
    public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
        return null;
    }
}
