package lexicon;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Test {

	public static void main(String[] args) {
		LexiconValidator lv = new LexiconValidator();
		
		List<Token> st = lv.getPossibleTokens();
		
		List<String> lines = new ArrayList<String>();
		
		
		Path text_path = Paths.get("/tmp", "test.txt");
		try {
			List<String> FullText = Files.readAllLines(text_path);
			
			Stack<String> stack = new Stack();
		
			
			for (String line : FullText) {
				
				stack.push(line);
					
			}
			
			Stack allLines = new Stack();
			allLines = reverse(stack);
			
			while(!allLines.empty()) {
				lines.add((String) allLines.pop());
			}
		
			
		}catch(IOException e) {
			System.out.println(e);
		}
		
		
		
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
	
	public static Stack reverse(Stack stack){
		Stack newStack = new Stack();
		while(!stack.empty()) {
			newStack.push( stack.pop());
		}
		return newStack;
	}

}
