package dev.nihal.autoggmmc.command;

import dev.nihal.autoggmmc.AutoGGMMC;
import gg.essential.api.EssentialAPI;
import gg.essential.api.commands.DefaultHandler;

public class Command extends gg.essential.api.commands.Command {

    //This to add the command i.e /autogggmmc.

    public Command() {
        super("autoggmmc", true);
    }

    @DefaultHandler
    public void handle() {
        EssentialAPI.getGuiUtil().openScreen(AutoGGMMC.config.gui());
    }
}
