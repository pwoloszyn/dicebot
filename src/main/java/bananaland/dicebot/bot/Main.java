package bananaland.dicebot.bot;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

import bananaland.dicebot.parser.Parser;

public class Main {
    public static void main(String[] args) throws LoginException {

        JDABuilder builder = JDABuilder.createDefault(Secret.TOKEN);
        Bot bot = new Bot();
        builder.addEventListeners(bot);
        builder.setActivity(Activity.playing("Type !roll <dice expression>"));
        builder.build();

    	
    }
}
