package me.jippang.afk.listeners

import me.jippang.afk.updateLastActivityTime
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerMoveEvent

class PlayerMoveListener: Listener {
    @EventHandler
    fun playerMoveEvent(event: PlayerMoveEvent) {
        val player = event.player
        player.updateLastActivityTime(System.currentTimeMillis())
    }
}