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
		
		List<String> lines = new ArrayList<String>();;
		
		Path text_path = Paths.get("tests", "test.txt");
		try {
			List<String> FullText = Files.readAllLines(text_path);
			
			
		
			for (String line : FullText) {
				
				lines.add(line);
					
			}
		
			
		}catch(IOException e) {
			System.out.println(e);
		}
		
		
		Stack<Token> stk;
		try {
			stk = lv.validate(lines);
			System.out.println(stk.size());
			while(!stk.empty()) {
				Token aux = stk.pop();
				if(aux != null) {
					System.out.println(aux.toString());
				}
				else {
					System.out.println("ERRO");
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
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
