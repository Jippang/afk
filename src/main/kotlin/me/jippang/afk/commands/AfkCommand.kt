package me.jippang.afk.commands

import me.jippang.afk.afk
import me.jippang.afk.isAfk
import me.jippang.afk.unAfk
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class AfkCommand : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender is Player) {
            if (sender.isAfk) {
                unAfk(sender)
            } else {
                afk(sender)
            }
        }
        return false
    }
}