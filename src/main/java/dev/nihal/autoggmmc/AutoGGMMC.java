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

    //Registering

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
        //This is to check if the player is on Minemen Club server.
    public static boolean isMinemen() {
        if (Minecraft.getMinecraft().theWorld != null && !Minecraft.getMinecraft().isSingleplayer()) {
            return Minecraft.getMinecraft().getCurrentServerData().serverIP.toLowerCase().contains("minemen");
        }
        return false;
    }

        //This is where the magic happens.

    @SubscribeEvent
    public void onMessageReceived(ClientChatReceivedEvent event) {

        //This is getting the chat messages unformatted, in other words its remove all the useless colour codes and stuff.
        String msg = event.message.getUnformattedText();

        //This is checking if the mod is enabled

        if (Config.autoggEnabled) {

            //This is checking if the user is on Minemen Club

            if (isMinemen()) {

                //This is checking if the game has ended and if the user has random messages enabled and then send the GG message according to the user's settings.

                if (msg.startsWith("Winner:")) {
                    Multithreading.schedule(() -> Minecraft.getMinecraft().thePlayer.sendChatMessage(Config.randomMessage ? getRandomGGmessage() : Config.getGGmessage()), Config.GGDelay, TimeUnit.SECONDS);
                }
            }
        }
    }
        //This is to return the GG message.
        private static String getGGmessage() {
        return ggMessages[Config.ggMessage];
    }

}
