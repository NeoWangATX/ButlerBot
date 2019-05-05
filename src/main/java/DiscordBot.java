import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.Scanner;

public class DiscordBot {

    public DiscordBot()
    {

    }

    public void executeCommand(String command, MessageReceivedEvent event)
    {
        switch(command)
        {
            case "credits": //credits
                this.sendMessage(event, BotCommands.credits());
                break;
            case "paperclip": //paperclip
                this.sendMessage(event, BotCommands.paperclip());
                break;
        }

        //more complicated commands

        if(event.getMessage().mentionsEveryone())
        {
            System.out.println("Did not respond because user pinged @everyone");
        }
        else if(command.contains("paperclip") && !(((event.getMessage().getContentRaw().charAt(11)+"").equals("<"))&&(event.getMessage().getContentRaw().endsWith(">"))))
        {
            System.out.println("Did not respond because user did not tag anyone");
            this.sendMessage(event,"Proper usage: %paperclip <user>");
        }
        else if(command.substring(0,9).equals("paperclip") && command.length() > 10) //paperclip <user>
        {
            this.sendMessage(event, command.substring(10) + " " + BotCommands.paperclip());
        }
        else if(command.contains("TALK_THROUGH_BOT"))
        {
            System.out.println("Author : " + event.getMessage().getAuthor().getIdLong());
            if(BotConfiguration.BOT_DEVELOPERS.contains(event.getMessage().getAuthor().getIdLong()) && event.getMessage().getContentRaw().contains("TALK_THROUGH_BOT"))
            {
                System.out.println("AUTHORIZED " +  event.getMessage().getAuthor().getName());
                System.out.print("Send: ");
                String message;
                Scanner keyboard = new Scanner(System.in);

                do {

                    message = keyboard.nextLine();

                    if(!message.isEmpty())
                    {
                        event.getChannel().sendMessage(message).queue();
                    }
                } while(!message.equals("STOP TYPING"));
            }
        }
    }

    public void sendMessage(MessageReceivedEvent event, String message)
    {
        event.getChannel().sendMessage(message).queue();
    }
}
