package bananaland.dicebot.bot;

import java.util.ArrayList;

import bananaland.dicebot.parser.Parser;
import bananaland.dicebot.parser.expressions.Expression;
import bananaland.dicebot.tokenizer.Tokenizer;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Bot extends ListenerAdapter {
	
	private Tokenizer tokenizer;
	private Parser parser;
	
	public Bot() {
		tokenizer = new Tokenizer();
		parser = new Parser();
	}

	@Override
    public void onMessageReceived(MessageReceivedEvent event) {
        Message message = event.getMessage();
        String content = message.getContentRaw();
        if (content.trim().startsWith("!roll")) {
            MessageChannel channel = event.getChannel();
            
            if(!tokenizer.tokenize(content.substring(5).replace(" ", "")))
            	channel.sendMessage("Unexpected character is input").queue();
            
            else {
            	try {
            		Expression expr = parser.parse(tokenizer.getTokens());
            		channel.sendMessage(parser.getOutput_string() + " =  " + (int) expr.getValue()).queue();
            	} catch (RuntimeException e){
            		channel.sendMessage("Incorrect input").queue();
            	}
            }
        }
    }
}
