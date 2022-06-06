package com.xbaimiao.easybot

import com.xbaimiao.mirai.event.GroupMessageEvent
import com.xbaimiao.mirai.packet.impl.websocket.WebSocketBot
import net.md_5.bungee.api.ProxyServer

object OnlineInfo {

    private var cooling = 0L

    fun register(bot: WebSocketBot) {
        bot.eventChancel.subscribe<GroupMessageEvent> {
            val msg = message.contentToString()
            if (msg == "在线详情") {
                if (cooling + 5000 > System.currentTimeMillis()) {
                    reply("查询冷却中 冷却时间: ${((cooling + 5000) - System.currentTimeMillis()) / 1000} 秒")
                    return@subscribe
                }
                cooling = System.currentTimeMillis()
                val stringBuffer = StringBuffer()
                val info = HashMap<String, Int>()
                for (player in ProxyServer.getInstance().players) {
                    val serverName = player.server.info.name
                    val old = info[serverName] ?: 0
                    info[serverName] = (old + 1)
                }
                info.forEach { (k, v) ->
                    stringBuffer.append("⚪ $k: $v\n")
                }
                stringBuffer.append("⚪ 总在线: ${ProxyServer.getInstance().players.size}")
                reply(stringBuffer.toString())
            }
        }
    }

}