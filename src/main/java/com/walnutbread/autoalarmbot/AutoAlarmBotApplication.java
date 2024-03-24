package com.walnutbread.autoalarmbot;

import com.walnutbread.autoalarmbot.discord.DiscordBotToken;
import com.walnutbread.autoalarmbot.discord.DiscordListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class AutoAlarmBotApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(AutoAlarmBotApplication.class, args);

		DiscordBotToken discordBotTokenEntity = context.getBean(DiscordBotToken.class);
		String discordBotToken = discordBotTokenEntity.getDiscordBotToken();

		JDA jda = JDABuilder.createDefault(discordBotToken)
				.setActivity(Activity.playing("대기 중.."))
				.enableIntents(GatewayIntent.MESSAGE_CONTENT)
				.addEventListeners(new DiscordListener())
				.build();
	}

}
