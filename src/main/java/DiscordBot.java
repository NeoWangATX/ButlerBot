import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class DiscordBot {

    public DiscordBot()
    {

    }

    public void executeCommand(String command, MessageReceivedEvent event)
    {
        switch(command)
        {
            case "credits":
                this.sendMessage(event, BotCommands.credits());
                System.out.println("Fulfilled command : " + command);
                break;
        }
    }

    public void sendMessage(MessageReceivedEvent event, String message)
    {
        event.getChannel().sendMessage(message).queue();
    }
}
