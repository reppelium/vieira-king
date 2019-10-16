package syntactic;

import java.util.ArrayList;
import java.util.Stack;

import errors.EditorException;

import java.util.List;

import model.Token;

public class Test {
	
	public static void main(String[] args) {
		Stack<Token> st = new Stack<Token>();
		st.add(new Token(1, "PROGRAM", 2));
		SyntaticValidator sv = new SyntaticValidator(st);
		
		
			try {
				while(!sv.getEnd()) {
					sv.doStep();
				}
			} catch (EditorException e) {
				System.out.println(e.getMessage());
			}
		
	}
	
}
