package com.xbaimiao.easybot

import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
import taboolib.common.platform.ProxyCommandSender
import taboolib.common.platform.command.command

@Suppress("unused")
object BotCommand {

    @Awake(LifeCycle.ENABLE)
    fun init() {
        command(
            name = "bot",
            permission = "op"
        ) {
            //重新链接
            literal("reConnect", optional = true) {
                execute<ProxyCommandSender> { sender, _, _ ->
                    EasyBot.bot.connect()
                    sender.sendMessage("重新链接成功,QQ: ${EasyBot.bot.id}")
                }
            }
        }
    }

}