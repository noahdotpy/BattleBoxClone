package me.diligord.battleboxclone.events;

import me.diligord.battleboxclone.BattleBoxClone;
import net.kyori.adventure.text.Component;
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
    }

}
