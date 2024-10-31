package com.javarush.telegrambot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Map;

import static com.javarush.telegrambot.BotData.BotName;
import static com.javarush.telegrambot.BotData.BotToken;


public class MyFirstTelegramBot extends MultiSessionTelegramBot {
    public static final String NAME = BotName; // TODO: добавьте имя бота в кавычках
    public static final String TOKEN = BotToken; //TODO: добавьте токен бота в кавычках

    public MyFirstTelegramBot() {
        super(NAME, TOKEN);
    }

    @Override
    public void onUpdateEventReceived(Update updateEvent) {
        // TODO: основной функционал бота будем писать здесь
//        sendTextMessageAsync("Hi!");
//        sendTextMessageAsync("*Hi!*"); // allows to use markdown, e.g. bold&cursive
//        sendTextMessageAsync("_Hi!_");
        if (getMessageText().equals("/start")) {
            sendTextMessageAsync("*Hi!*");
        }

        if (getMessageText().equals("/bye")) {
            sendTextMessageAsync("_Goodbye!_");
        }

        if (getMessageText().equals("How are you?")) {
            sendTextMessageAsync("I'm OK, thanx :)");
        }

        if (getMessageText().contains("bomb")) {
            sendTextMessageAsync("*ALARM*");
        }

        if (getMessageText().contains("picture")) {
            sendPhotoMessageAsync("step_8_pic");
        }

        if (getMessageText().contains("cat")) {
            sendTextMessageAsync("*Choose your cat: *",
                    Map.of(
                            "Cat #1", "cat1",
                            "Cat #2", "cat2",
                            "Cat #3", "cat3",
                            "Cat #4", "cat4",
                            "Cat #5", "cat5"));
        }

        if (getCallbackQueryButtonKey().equals("cat1")) {
            sendPhotoMessageAsync("step_1_pic");
        }

        if (getCallbackQueryButtonKey().equals("cat2")) {
            sendPhotoMessageAsync("step_2_pic");
        }

        if (getMessageText().equals("smile")) {
            var message = getLastSentMessage();
            editTextMessageAsync(message.getMessageId(), message.getText()+" \uD83D\uDE09 ");
        }
    }

    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(new MyFirstTelegramBot());
    }
}