package me.jippang.afk.listeners

import io.papermc.paper.event.player.AsyncChatEvent
import me.jippang.afk.updateLastActivityTime
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

class PlayerChatListener: Listener {
    @EventHandler
    fun playerChatEvent(event: AsyncChatEvent) {
        val player = event.player
        player.updateLastActivityTime(System.currentTimeMillis())
    }
}