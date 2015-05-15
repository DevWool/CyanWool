package net.devwool.cyanwool.api.command.cyanwool;

import java.util.ArrayList;
import java.util.List;

import net.devwool.cyanwool.api.CyanWool;
import net.devwool.cyanwool.api.command.Command;
import net.devwool.cyanwool.api.command.ICommandSender;
import net.devwool.cyanwool.api.utils.ChatColor;

public class VersionCommand extends Command {

    public VersionCommand() {
        super("version", "About server", true);
    }

    @Override
    public void execute(ICommandSender sender, String[] args) {
        // ???
        List<String> list = new ArrayList<String>();
        list.add(ChatColor.GREEN + "#=====#_" + ChatColor.AQUA + "CyanWool" + ChatColor.GREEN + "#=====#");
        list.add(ChatColor.AQUA + CyanWool.getLanguageManager().getLocale(sender.getLangCode(), "cw.version.modname") + ": " + ChatColor.WHITE + CyanWool.getModName());
        list.add(ChatColor.AQUA + CyanWool.getLanguageManager().getLocale(sender.getLangCode(), "cw.version.mcversion") + ": " + ChatColor.WHITE + CyanWool.getMCVersion());

        List<String> dev = CyanWool.getDevelopers();
        list.add(ChatColor.AQUA + CyanWool.getLanguageManager().getLocale(sender.getLangCode(), "cw.version.developers") + ": ");
        for (String developer : dev) {
            list.add("- " + developer);
        }

        for (String message : list) {
            sender.sendMessage(message);
        }
    }

}