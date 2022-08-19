package me.diligord.battleboxclone.listeners;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import me.diligord.battleboxclone.BattleBoxClone;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerListener implements Listener {

    private final BattleBoxClone plugin;

    public PlayerListener(BattleBoxClone plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {

        if (plugin.gameStarted) return;

        event.setTo(event.getFrom().setDirection(event.getTo().getDirection()));
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerJump(PlayerJumpEvent event) {

        if (plugin.gameStarted) return;

        event.setCancelled(true);
    }

}
