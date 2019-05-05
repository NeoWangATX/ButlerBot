import net.dv8tion.jda.bot.*;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.events.guild.GuildJoinEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;
import java.util.Scanner;

public class Main extends ListenerAdapter{
    private DiscordBot bot = new DiscordBot();
    public static void main(String[] args) throws LoginException
    {
        JDABuilder builder = new JDABuilder(AccountType.BOT);
        builder.setToken(BotConfiguration.BOT_TOKEN);
        builder.addEventListener(new Main());
        builder.buildAsync();
    }

    @Override
    public void onGuildJoin(GuildJoinEvent event)
    {
        try
        {
            System.out.println("New server has added ButlerBot: " + event.getGuild().getInvites());
        } catch (Exception e)
        {
            System.out.println("Bot is misssing permissions on " + event.getGuild().getName());
        }

        if(event.getGuild().getDefaultChannel().canTalk())
        {
            event.getGuild().getDefaultChannel().sendMessage("Thank you for adding ButlerBot. If you would like to donate to this bot, please dm tellem#7092").queue();
        }
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        //These following methods help get rid of messages that we do not want to butler
        if(event.getAuthor().isBot())
        {
            return;
        }

        System.out.println("Channel: #" +
                event.getChannel().getName() + ", " +
                event.getAuthor().getName() + ": " +
                event.getMessage().getContentDisplay()
        );

        if((event.getMessage().getContentRaw().charAt(0) + "").equals(BotConfiguration.BOT_PREFIX))
        {
            String messageContent = event.getMessage().getContentRaw().substring(1);
            System.out.println("<@" + event.getAuthor().getId() + "> has invoked : " + messageContent);

            bot.executeCommand(messageContent, event);
        }

    }
}
