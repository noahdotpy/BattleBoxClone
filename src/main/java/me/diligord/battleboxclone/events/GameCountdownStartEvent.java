package me.diligord.battleboxclone.events;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.Plugin;
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
    public GameCountdownStartEvent(int countdownTime, Plugin plugin) {
        new BukkitRunnable() {
            int timer = countdownTime;

            @Override
            public void run() {
                if (timer == 0) {
                    Bukkit.getServer().getPluginManager().callEvent(new GameStartEvent());
                    super.cancel();
                }
                else broadcast(Component.text("Starting in " + timer-- + " seconds."));
            }
        }.runTaskTimer(plugin, 0L, 20);
    }

}
