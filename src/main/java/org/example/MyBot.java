package org.example;

import net.thauvin.erik.crypto.CryptoPrice;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class MyBot extends TelegramLongPollingBot {
    public MyBot() {
        super("6893445269:AAGByx-RxG0xRs_9jvgeE_hVTSjdFEa1rck");
    }

    @Override
public void onUpdateReceived(Update update) {
    var chatId = update.getMessage().getChatId();
    var text = update.getMessage().getText();

    try {
        if (text.equals("/start")) {
            sendMessage(chatId, "Hello!");
        } else if (text.equals("btc")) {
            //sendPicture(chatId, "Bitcoin.png");
            sendPrice(chatId, "BTC");
        } else if (text.equals("eth")) {
            sendPrice(chatId, "ETH");
        } else if (text.equals("doge")) {
            sendPrice(chatId, "Doge");
        } else if (text.equals("all")) {
            sendPrice(chatId, "BTC");
            sendPrice(chatId, "ETH");
            sendPrice(chatId, "Doge");
        } else {
            sendMessage(chatId, "Unknown command!");
        }
    } catch (Exception e) {
        System.out.println("Error!");
    }
}

void sendPrice(long chatId, String name) throws Exception {
    var price = CryptoPrice.spotPrice(name);
    sendMessage(chatId, name + " price: " + price.getAmount().doubleValue());
}

void sendMessage(long chatId, String text) throws Exception {
    var message = new SendMessage();
    message.setChatId(chatId);
    message.setText(text);
    execute(message);
}
    @Override
    public String getBotUsername() {
        return "Crying_java_4_bot";
    }
}
