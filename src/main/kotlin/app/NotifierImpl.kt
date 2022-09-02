package app

import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.command
import com.github.kotlintelegrambot.entities.ChatId
import core.Notifier

class NotifierImpl (
    tokenCode: String,
    chatId: Long,

): Notifier
{
    private var chatId: ChatId
    private val bot: com.github.kotlintelegrambot.Bot

    init {
        this.chatId = ChatId.fromId(chatId)
        this.bot = bot {
            token = tokenCode
            dispatch {
                command("status") {
                    sendText("Running")
                }
            }
        }
        this.bot.startPolling()
    }
    override fun sendText(text: String){
        bot.sendMessage(chatId,text)
    }
}