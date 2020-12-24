package bananaland.dicebot.bot;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

import bananaland.dicebot.parser.Parser;

public class Main {
    public static void main(String[] args) throws LoginException {
        // TODO: Allow command string to be set from command line.
        String commandString = "!test";
        JDABuilder builder = JDABuilder.createDefault(Secret.TOKEN);
        Bot bot = new Bot(commandString);
        builder.addEventListeners(bot);
        builder.setActivity(Activity.playing("Type " + commandString + "<dice expression>"));
        builder.build();

    	
    }
}
