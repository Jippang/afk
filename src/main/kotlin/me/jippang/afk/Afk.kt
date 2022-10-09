package me.jippang.afk

import me.jippang.afk.commands.AfkCommand
import me.jippang.afk.listeners.PlayerChatListener
import me.jippang.afk.listeners.PlayerInteractListener
import me.jippang.afk.listeners.PlayerMoveListener
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class Afk : JavaPlugin() {
    override fun onEnable() {
        saveDefaultConfig()
        registerListeners()
        registerCommands()
        Bukkit.getScheduler().runTaskTimer(this, Runnable {
            for (player in Bukkit.getOnlinePlayers()) {
                if (player.isAfk) {
                    continue
                }
                if (System.currentTimeMillis() - player.lastActivityTime >= 1000 * config.getInt("seconds_in_afk")) {
                    afk(player)
                }
            }
        }, 0, 20)
        logger.info("Afk is enabled.")
    }

    private fun registerListeners() {
        listOf(
            PlayerMoveListener(),
            PlayerInteractListener(),
            PlayerChatListener()
        ).forEach {
            server.pluginManager.registerEvents(it, this)
        }
        logger.info("Successfully registered listeners.")
    }

    private fun registerCommands() {
        getCommand("afk")?.setExecutor(AfkCommand())
        logger.info("Successfully registered commands.")
    }

    override fun onDisable() {
        logger.info("Afk is disabled.")
    }
}