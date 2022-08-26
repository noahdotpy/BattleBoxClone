package me.diligord.battleboxclone.events;

import me.diligord.battleboxclone.BattleBoxClone;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import static org.bukkit.Bukkit.broadcast;

public class GameStartEvent extends Event implements Cancellable {

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

    public GameStartEvent(BattleBoxClone plugin) {
        broadcast(Component.text("Go!"));

        plugin.gameStarted = true;

        Location teleportLocation = (Location) plugin.getConfig().get("teams.blue.teleport-location");

        Bukkit.getOnlinePlayers().forEach(player -> {
            Block blockUnder = teleportLocation.clone().add(new Location(teleportLocation.getWorld(), 0, -1, 0)).getBlock();
            if (blockUnder.getType() == Material.BARRIER) blockUnder.setType(Material.AIR);
        });
    }

}
