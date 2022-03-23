package ru.er1one.enigmasmiles.listeners;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import ru.er1one.enigmasmiles.EnigmaSmiles;
import ru.er1one.enigmasmiles.models.Emoji;

public class EventListener implements Listener {

    EnigmaSmiles instance = EnigmaSmiles.getInstance();

    @EventHandler
    public void onChatEvent(AsyncPlayerChatEvent event) {
        for(String input : instance.getEmojis().keySet()) {
            if(!event.getMessage().toLowerCase().contains(input)) {
                continue;
            }

            Emoji emoji = instance.getEmojis().get(input);

            if(!event.getPlayer().hasPermission(emoji.getPermission())) {
                continue;
            }

            event.setMessage(event.getMessage().replace(input, format(emoji.getOutput())));
        }
    }

    public String format(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

}
