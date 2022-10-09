package me.jippang.afk

import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import java.util.UUID

private var playerDataMap = HashMap<UUID, PlayerData>()
var Player.lastActivityTime: Long
    get() {
        if (!playerDataMap.containsKey(this.uniqueId)) {
            playerDataMap[this.uniqueId] = PlayerData(System.currentTimeMillis(), false)
        }
        return playerDataMap[this.uniqueId]!!.lastActivityTime
    }
    private set(value) {
        val newPlayerData = playerDataMap[this.uniqueId] ?: PlayerData(System.currentTimeMillis(), false)
        newPlayerData.lastActivityTime = value
        playerDataMap[this.uniqueId] = newPlayerData
    }
var Player.isAfk: Boolean
    get() {
        if (!playerDataMap.containsKey(this.uniqueId)) {
            playerDataMap[this.uniqueId] = PlayerData(System.currentTimeMillis(), false)
        }
        return playerDataMap[this.uniqueId]!!.isAfk
    }
    set(value) {
        val newPlayerData = playerDataMap[this.uniqueId] ?: PlayerData(System.currentTimeMillis(), false)
        newPlayerData.isAfk = value
        playerDataMap[this.uniqueId] = newPlayerData
    }

fun unAfk(player: Player) {
    player.isAfk = false
    Bukkit.broadcast(Component.text("§b${player.name}이(가) 잠수 상태에서 해제됐습니다."))
}
fun afk(player: Player) {
    player.isAfk = true
    Bukkit.broadcast(Component.text("§b${player.name}이(가) 잠수 상태입니다."))
}
fun Player.updateLastActivityTime(newTime: Long) {
    if (this.isAfk) {
        unAfk(this)
    }
    this.lastActivityTime = newTime
}

class PlayerData(var lastActivityTime: Long, var isAfk: Boolean)