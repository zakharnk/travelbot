package com.telegram.bot;

import com.telegram.bot.service.CityService;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.NoSuchElementException;

public class TravelBot extends TelegramLongPollingBot {

    private String botUserName;

    private String botToken;

    public final CityService cityService;

    public TravelBot(CityService cityService) {
        this.cityService = cityService;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public String getBotUsername() {
        return botUserName;
    }
    public void setBotUserName(String botUserName) {
        this.botUserName = botUserName;
    }

    public void setBotToken(String botToken) {
        this.botToken = botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.getMessage().getText().equals("/start")) {
            SendMessage message = new SendMessage().setChatId(update.getMessage().getChatId());
            message.setText("Hello! \n" +
                    "I am a bot for sights in cities around the world.\n" +
                    "To start communication with me please enter the name of the city.");
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

        } else {
            try {
                sendMessage(update);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMessage (Update update) throws TelegramApiException {
        try {
            SendMessage sendMessage = new SendMessage();
            sendMessage.enableMarkdown(true);
            sendMessage.setChatId(update.getMessage().getChatId());
            sendMessage.setText(cityService.findByName(update.getMessage().getText()));
            execute(sendMessage);
        } catch (NoSuchElementException e) {
            SendMessage exception = new SendMessage();
            exception.setChatId(update.getMessage().getChatId());
            exception.setText("This city doesn't exist");
            execute(exception);
        }
    }


}
