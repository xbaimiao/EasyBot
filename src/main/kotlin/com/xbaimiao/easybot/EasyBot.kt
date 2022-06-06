package com.xbaimiao.easybot

import com.xbaimiao.mirai.packet.impl.websocket.WebSocketBot
import com.xbaimiao.mirai.packet.impl.websocket.WsInfo
import taboolib.common.platform.Platform
import taboolib.common.platform.PlatformImplementation
import taboolib.common.platform.Plugin
import taboolib.common.platform.function.info
import taboolib.common.platform.function.submit
import taboolib.module.configuration.Config
import taboolib.module.configuration.ConfigFile

@PlatformImplementation(Platform.BUNGEE)
object EasyBot : Plugin() {

    @Config(value = "config.yml")
    lateinit var conf: ConfigFile
        private set

    lateinit var bot: WebSocketBot

    override fun onEnable() {
        submit(async = true) {
            val wsInfo = WsInfo("http://run.xbaimiao.com:8099/", 2157207381, "INITKEYCgTu5Bcx")
            bot = WebSocketBot(wsInfo).connect()
            // 机器人断开链接后 60 秒后自动重连
            bot.autoReconnect(60)
            OnlineInfo.register(bot)
            info("机器人链接成功,QQ: ${wsInfo.qq}")
        }
    }

    override fun onDisable() {
        bot.disable()
        info("机器人链接已关闭")
    }

}