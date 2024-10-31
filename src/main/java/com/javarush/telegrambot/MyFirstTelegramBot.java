package com.javarush.telegrambot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

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
        sendTextMessageAsync("Hi!");
        sendTextMessageAsync("*Hi!*"); // allows to use markdown, e.g. bold&cursive
        sendTextMessageAsync("_Hi!_");
    }

    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(new MyFirstTelegramBot());
    }
}