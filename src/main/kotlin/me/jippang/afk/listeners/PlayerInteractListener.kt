package me.jippang.afk.listeners

import me.jippang.afk.updateLastActivityTime
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent

class PlayerInteractListener: Listener {
    @EventHandler
    fun playerInteractEvent(event: PlayerInteractEvent) {
        val player = event.player
        player.updateLastActivityTime(System.currentTimeMillis())
    }
}