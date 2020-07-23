package bananaland.dicebot.bot;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Bot extends ListenerAdapter {
	
	//Some cahnges will happen
	int x = 5 + 2 + 3;

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        Message message = event.getMessage();
        String content = message.getContentRaw();
        if (content.trim().startsWith("!roll")) {
            MessageChannel channel = event.getChannel();
            // TODO: Parse `content` and send a message with the dice expression rolled.
            channel.sendMessage("I don't know how to do that command yet! (Coming Soon)").queue();
        }
    }
}
