package com.walnutbread.autoalarmbot.discord;

import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Arrays;
import java.util.Objects;

@Slf4j
public class DiscordListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        User user = event.getAuthor();
        TextChannel textChannel = event.getChannel().asTextChannel();
        Message message = event.getMessage();

        log.info("get message : " + message.getContentDisplay());

        if(user.isBot()) return;
        else if(message.getContentDisplay().isEmpty()) log.info("디스코드 Message 문자열 값 공백");

        String[] messageArr = message.getContentDisplay().split("/");

        if(messageArr[0].equalsIgnoreCase("!bot")){
            String[] messageArgs = Arrays.copyOfRange(messageArr, 1, messageArr.length);

            for(String msg : messageArgs) {
                String returnMessage = SendMessage(event, msg);
                textChannel.sendMessage(returnMessage).queue();
            }
        }
    }

    private String SendMessage(MessageReceivedEvent event, String message) {
        User user = event.getAuthor();
        String returnMessage = "";
        if(Objects.equals(message, "test")) {
            returnMessage = "안녕하세요 " + user.getAsMention() + "님, 테스트 메시지 전송입니다.";
        } else {
            returnMessage = "현재 작업 중입니다. 잠시만 기다려주세요.";
        }

        log.info("message : " + returnMessage);

        return returnMessage;
    }
}
