package ru.er1one.enigmasmiles;

import org.bukkit.plugin.java.JavaPlugin;
import ru.er1one.enigmasmiles.commands.SmilesCommand;
import ru.er1one.enigmasmiles.listeners.EventListener;
import ru.er1one.enigmasmiles.models.Emoji;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public final class EnigmaSmiles extends JavaPlugin {

    private static EnigmaSmiles instance;

    private static final Logger log = Logger.getLogger("Minecraft");

    private final Map<String, Emoji> emojis = new HashMap<>();

    @Override
    public void onEnable() {
        instance = this;
        loadEmojis();
        // Подключение метрики
        Metrics metrics = new Metrics(this, 12790);
        saveDefaultConfig();
        sendStartedInfo();
        getServer().getPluginManager().registerEvents(new EventListener(), this);
        getServer().getPluginCommand("smiles").setExecutor(new SmilesCommand());
    }

    private void sendStartedInfo() {
        log.info("|<---------------------------------------->|");
        log.info("|  EnigmaSmiles v1.0 has been started      |");
        log.info("|<---------------------------------------->|");
    }

    public static EnigmaSmiles getInstance() {
        return instance;
    }

    public Map<String, Emoji> getEmojis() {
        return emojis;
    }

    public int loadEmojis() {
        if(!emojis.isEmpty()) {
            emojis.clear();
        }
        int amount = 0;
        for(String key : getConfig().getConfigurationSection("smiles").getKeys(false)) {
            amount++;
            String input = getConfig().getString("smiles." + key + ".input").toLowerCase();
            String output = getConfig().getString("smiles." + key + ".output");
            String permission = getConfig().getString("smiles." + key + ".permission");
            emojis.put(input, new Emoji(output, permission));
        }
        return amount;
    }
}
