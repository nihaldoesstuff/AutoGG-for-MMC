package dev.nihal.autoggmmc.config;

import gg.essential.vigilance.Vigilant;
import gg.essential.vigilance.data.Property;
import gg.essential.vigilance.data.PropertyType;

import java.io.File;
import java.util.concurrent.ThreadLocalRandom;

public class Config extends Vigilant {

    //This is all the GUIs
    @Property(
            type = PropertyType.SWITCH, name = "AutoGGMMC",
            description = "Says GG after winning/losing a game on Minemen Club.",
            category = "General", subcategory = "AutoGGMMC"
    )

    public static boolean autoggEnabled = true;

    public static final String[] ggMessages = {"gg", "GG", "gf", "gf <3", "gg <3", "wp", "Nice!", "Good Game", "Good Fight", "Good Round!", "Well Played!"};

    @Property(
            type = PropertyType.SELECTOR, name = "GG Messages",
            description = "Choose what message is said.",
            category = "General", subcategory = "Configurations",
            options = {"gg", "GG", "gf", "gf <3", "gg <3", "wp", "Nice!", "Good Game", "Good Fight", "Good Round!", "Well Played!"}
    )
    public static int ggMessage = 0;

    @Property(
            type = PropertyType.SWITCH, name = "Random Messagge",
            description = "Sends a random GG phrase",
            category = "General", subcategory = "Configurations"
    )

    public static boolean randomMessage = false;

    @Property(
            type = PropertyType.SLIDER, name = "Delay",
            description = "Delay to say the message.\n§cMeasured in seconds.",
            category = "General", subcategory = "Configurations",
            max = 5
    )
    public static int GGDelay = 1;

    //This is to get a random GG phrase from the given list.

    public static String getRandomGGmessage() {
        return ggMessages[ThreadLocalRandom.current().nextInt(ggMessages.length)];
    }

    public static String getGGmessage() {
        return ggMessages [ggMessage];
    }


    public Config() {
        super(new File("autoggmmc.toml"), "AutoGGMMC");
    }

}
