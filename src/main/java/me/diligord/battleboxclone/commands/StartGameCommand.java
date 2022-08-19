package me.diligord.battleboxclone.commands;

import me.diligord.battleboxclone.BattleBoxClone;
import me.diligord.battleboxclone.events.GameCountdownStartEvent;
import me.diligord.battleboxclone.utils.BattleBoxCloneUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class StartGameCommand implements CommandExecutor {

    private final BattleBoxClone plugin;

    public StartGameCommand(BattleBoxClone plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!sender.hasPermission("BattleBoxClone.startGameCommand")) {
            sender.sendMessage(Bukkit.permissionMessage());
            return true;
        }

        if (args.length == 1) {
            // if argument was an integer
            if (BattleBoxCloneUtils.getIsIntegerFromString(args[0])) {
                Bukkit.getServer().getPluginManager().callEvent(new GameCountdownStartEvent(Integer.parseInt(args[0]), plugin));

            } else {
                sender.sendMessage(ChatColor.RED +"Invalid arguments.");
                return false;
            }
        } else if (args.length == 0){
            Bukkit.getServer().getPluginManager().callEvent(new GameCountdownStartEvent(10, plugin));
        } else {
            sender.sendMessage(ChatColor.RED +"Invalid arguments.");
            return false;
        }

        return true;
    }
}
