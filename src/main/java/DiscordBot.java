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
                break;
            case "paperclip":
                this.sendMessage(event, BotCommands.paperclip());
                break;
        }
    }

    public void sendMessage(MessageReceivedEvent event, String message)
    {
        event.getChannel().sendMessage(message).queue();
    }
}
