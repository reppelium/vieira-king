package syntactic;

import java.util.List;
import java.util.Stack;

import enums.NoTerminal;
import lexicon.Token;

public class SyntaticValidator {
	
	public void validate(Stack<Token> tokens) {
		NoTerminal nt = new NoTerminal();
		
		List<Terminal> noTerminals = nt.getPossibleNoTerminals();
		
		return;
	}
	
}
