package com.telegram.bot.config;


import com.telegram.bot.TravelBot;
import com.telegram.bot.service.CityService;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

@Configuration
@ConfigurationProperties(prefix = "travelbot")
public class TravelBotConfig {
    private String botUserName;
    private String botToken;

    @Bean
    public TravelBot travelBot(CityService service) {
        TravelBot bot = new TravelBot(service);
        bot.setBotUserName(botUserName);
        bot.setBotToken(botToken);


        TelegramBotsApi telegram=new TelegramBotsApi();
        try{
            telegram.registerBot(bot);
        }
        catch (TelegramApiRequestException e){
            e.printStackTrace();
        }

        return bot;
    }
    public String getBotUserName() {
        return botUserName;
    }

    public void setBotUserName(String botUserName) {
        this.botUserName = botUserName;
    }

    public String getBotToken() {
        return botToken;
    }

    public void setBotToken(String botToken) {
        this.botToken = botToken;
    }
}