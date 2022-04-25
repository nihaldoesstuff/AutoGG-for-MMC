package dev.nihal.autoggmmc;

import dev.nihal.autoggmmc.command.Command;
import dev.nihal.autoggmmc.config.Config;

import static dev.nihal.autoggmmc.config.Config.getRandomGGmessage;
import static dev.nihal.autoggmmc.config.Config.ggMessages;

import gg.essential.vigilance.Vigilant;
import gg.essential.api.utils.Multithreading;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.concurrent.TimeUnit;

@Mod(modid = AutoGGMMC.ID, name = AutoGGMMC.NAME, version = AutoGGMMC.VER)
public class AutoGGMMC {

    public static final String NAME = "@NAME@", VER = "@VERSION@", ID = "@ID@";

    @Mod.Instance(ID)
    public static AutoGGMMC Instance;


    public static Vigilant config;

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        config = new Config();
        config.initialize();
        MinecraftForge.EVENT_BUS.register(this);
        new Command().register();
    }
    @SubscribeEvent
    public void onMessageReceived(ClientChatReceivedEvent event) {
        String msg = event.message.getUnformattedText();

        if (Config.autoggEnabled) {

            if (msg.startsWith("Winner:")) {
                Multithreading.schedule(() -> Minecraft.getMinecraft().thePlayer.sendChatMessage(Config.randomMessage ? getRandomGGmessage() : Config.getGGmessage()), Config.GGDelay, TimeUnit.SECONDS);
            }
        }
    }
        private static String getGGmessage() {
        return ggMessages[Config.ggMessage];
    }

}
