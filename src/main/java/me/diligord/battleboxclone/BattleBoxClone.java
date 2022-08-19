package me.diligord.battleboxclone;

import me.diligord.battleboxclone.commands.StartGameCommand;
import me.diligord.battleboxclone.listeners.GameEventListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class BattleBoxClone extends JavaPlugin {

    @Override
    public void onEnable() {
        // Config
        saveDefaultConfig();
        getConfig().options().copyDefaults();

        // Plugin startup logic
        PluginManager plMan = Bukkit.getPluginManager();

        plMan.registerEvents(new GameEventListener(), this);

        getCommand("startgame").setExecutor(new StartGameCommand(this));


        // Game start countdown

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
