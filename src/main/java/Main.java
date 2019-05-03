import net.dv8tion.jda.bot.*;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;

public class Main extends ListenerAdapter{
    private DiscordBot bot = new DiscordBot();;
    public static void main(String[] args) throws LoginException
    {
        JDABuilder builder = new JDABuilder(AccountType.BOT);
        builder.setToken(BotConfiguration.BOT_TOKEN);
        builder.addEventListener(new Main());
        builder.buildAsync();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        //These following methods help get rid of messages that we do not want to butler
        if(event.getAuthor().isBot())
        {
            return;
        }

        System.out.println("Message from " +
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
