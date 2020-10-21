package bananaland.dicebot.bot;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

import bananaland.dicebot.parser.Parser;
import bananaland.dicebot.parser.expressions.Expression;
import bananaland.dicebot.tokenizer.Tokenizer;

public class Main {
    public static void main(String[] args) throws LoginException {

    	
    	
        JDABuilder builder = JDABuilder.createDefault(Secret.TOKEN);
        Bot bot = new Bot();
        builder.addEventListeners(bot);
        builder.setActivity(Activity.playing("Type !roll <dice expression>"));
        builder.build();
    	
    	/*
    	String inp = "!roll       1d6 + 1";
    	
    	Tokenizer t = new Tokenizer();
    	t.tokenize(inp.substring(5).replace(" ", ""));
    	
    	Parser p = new Parser();
    	Expression e = p.parse(t.getTokens());
    	
    	System.out.println(p.getOutput_string() + " =  " + (int) e.getValue());
    	*/
    	
    }
}
