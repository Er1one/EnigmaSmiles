package ru.er1one.enigmasmiles.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import ru.er1one.enigmasmiles.EnigmaSmiles;

public class SmilesCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!sender.hasPermission("smiles.reload")) {
            sender.sendMessage(color(EnigmaSmiles.getInstance().getConfig().getString("messages.no-perms")));
            return true;
        }

        EnigmaSmiles.getInstance().reloadConfig();
        int amount = EnigmaSmiles.getInstance().loadEmojis();

        sender.sendMessage(color(EnigmaSmiles.getInstance().getConfig().getString("messages.reloaded")
                .replace("%amount%", amount + "")));
        return true;
    }

    private String color(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

}
