package app

import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.entities.ChatId
import core.Notifier

class BotImpl (
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

        }
    }
    override fun sendText(text: String){
        bot.sendMessage(chatId,text)
    }
}