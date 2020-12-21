package bananaland.dicebot.bot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import bananaland.dicebot.parser.DiceInterpreter;
import bananaland.dicebot.parser.DiceRollEvaluator;
import bananaland.dicebot.parser.Parser;
import bananaland.dicebot.parser.Tokenizer;
import bananaland.dicebot.parser.util.Patterns;
import bananaland.dicebot.parser.util.Token;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Bot extends ListenerAdapter {
	
	public Bot() {}

	@Override
    public void onMessageReceived(MessageReceivedEvent event) {
        Message message = event.getMessage();
        String content = message.getContentRaw();
        if (content.trim().startsWith("!test")) {
        	
        	// Logging
        	System.out.println("Input: "+content);
        	
        	MessageChannel channel = event.getChannel();
        	
        	try {
        		channel.sendMessage(DiceInterpreter.interpret(content.substring(5))).queue();
        	} catch (RuntimeException e) {
        		channel.sendMessage("Incorrect input").queue();
        	}
        }
    }
}
