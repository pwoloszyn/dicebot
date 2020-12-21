package bananaland.dicebot.parser;

import java.util.Arrays;
import java.util.List;

public class InputFormatter {
	
	public InputFormatter() {}
	
	public List<String> format(String input) {
		
		return Arrays.asList(input
				.replaceAll("(?<=[0-9])(?=\\()|(?<=\\))(?=\\()|(?<=\\))(?=[0-9])", "*")
				.split("(?<=[0-9])\\s+(?=[0-9])"
						+"|"+"(?<=\\))\\s+(?=\\()"
						+"|"+"(?<=[0-9])\\s+(?=\\()"
						+"|"+"(?<=\\))\\s+(?=[0-9])")
				);
	}

}
