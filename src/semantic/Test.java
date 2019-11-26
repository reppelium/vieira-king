package semantic;

import java.util.Stack;

import errors.EditorException;
import model.Token;

public class Test {

	public static void main(String[] args) {
		Stack<Token> st = new Stack<Token>();
		st.add(new Token(1, "PROGRAM", 1));
		st.add(new Token(25, "TESTEPROC2", 1));
		st.add(new Token(47, ";", 1));
		st.add(new Token(2, "LABEL", 2));
		st.add(new Token(25, "label_a", 2));
		st.add(new Token(47, ";", 2));
		st.add(new Token(3, "const", 3));
		st.add(new Token(25, "a", 3));
		st.add(new Token(40, "=", 3));
		st.add(new Token(26, "100", 3));
		st.add(new Token(47, ";", 3));
		st.add(new Token(4, "VAR", 4));
		st.add(new Token(25, "X", 4));
		st.add(new Token(46, ",", 4));
		st.add(new Token(25, "Y", 4));
		st.add(new Token(46, ",", 4));
		st.add(new Token(25, "z", 4));
		st.add(new Token(39, ":", 4));
		st.add(new Token(8, "INTEGER", 4));
		st.add(new Token(47, ";", 4));
		st.add(new Token(5, "PROCEDURE", 5));
		st.add(new Token(25, "P", 5));
		st.add(new Token(36, "(", 5));
		st.add(new Token(25, "y", 5));
		st.add(new Token(39, ":", 5));
		st.add(new Token(8, "INTEGER", 5));
		st.add(new Token(37, ")", 5));
		st.add(new Token(47, ";", 5));
		st.add(new Token(3, "CONST", 6));
		st.add(new Token(25, "A", 6));
		st.add(new Token(40, "=", 6));
		st.add(new Token(26, "2", 6));
		st.add(new Token(47, ";", 6));
		st.add(new Token(4, "VAR", 7));
		st.add(new Token(25, "X", 7));
		st.add(new Token(46, ",", 7));
		st.add(new Token(25, "T", 7));
		st.add(new Token(39, ":", 7));
		st.add(new Token(8, "INTEGER", 7));
		st.add(new Token(47, ";", 7));
		st.add(new Token(6, "begin", 8));
		st.add(new Token(7, "end", 9));
		st.add(new Token(47, ";", 9));
		st.add(new Token(6, "BEGIN", 10));
		st.add(new Token(11, "CALL", 11));
		st.add(new Token(25, "p", 11));
		st.add(new Token(36, "(", 11));
		st.add(new Token(25, "label_a", 11));
		st.add(new Token(37, ")", 11));
		st.add(new Token(7, "END", 13));
		st.add(new Token(49, ".", 13));
		
		SemanticValidator sv = new SemanticValidator(st);
		try {
			sv.validate();
		} catch (EditorException e) {
			System.out.println(e.getMessage());
		}

	}

}
