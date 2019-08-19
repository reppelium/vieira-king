package lexicon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Test {

	public static void main(String[] args) {
		LexiconValidator lv = new LexiconValidator();
		
		List<Token> st = lv.getPossibleTokens();
		
		List<String> lines = new ArrayList<String>();
		lines.add("linha 10 20");
		Stack<Token> stk = lv.validate(lines);
		System.out.println(stk.size());
		while(!stk.empty()) {
			Token aux = stk.pop();
			if(aux != null) {
				System.out.println(aux.toString());
			}
			else {
				System.out.println("PORRA MERDA");
			}
			
		}
		
		
	}

}
