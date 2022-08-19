package me.diligord.battleboxclone.events;

import me.diligord.battleboxclone.BattleBoxClone;
import me.diligord.battleboxclone.listeners.PlayerListener;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import static org.bukkit.Bukkit.broadcast;

public class GameCountdownStartEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();

    private boolean cancelled;

    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }

    /*
    * Parameters:
    *   - countdownTime: the game countdown time in seconds
    */
    public GameCountdownStartEvent(int countdownTime, BattleBoxClone plugin) {

        Bukkit.getPluginManager().registerEvents(new PlayerListener(plugin), plugin);

        plugin.gameStarted = false;

        Location teleportLocation = (Location) plugin.getConfig().get("teams.blue.teleport-location");

        Bukkit.getOnlinePlayers().forEach(player -> {
            player.teleport(teleportLocation);
            Block blockUnder = teleportLocation.clone().add(new Location(teleportLocation.getWorld(), 0, -1, 0)).getBlock();
            if (blockUnder.getType() == Material.AIR) blockUnder.setType(Material.BARRIER);
        });


        new BukkitRunnable() {
            int timer = countdownTime;

            @Override
            public void run() {
                if (timer == 0) {
                    Bukkit.getServer().getPluginManager().callEvent(new GameStartEvent(plugin));
                    super.cancel();
                }
                else broadcast(Component.text("Starting in " + timer-- + " seconds."));
            }
        }.runTaskTimer(plugin, 0L, 20);
    }

}
