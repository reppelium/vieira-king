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
		st.add(new Token(25, "TESTE123", 2));
		st.add(new Token(47, ";", 2));
		st.add(new Token(2, "LABEL", 3));
		st.add(new Token(25, "label_a", 4));
		st.add(new Token(46, ",", 4));
		st.add(new Token(25, "label_b", 4));
		st.add(new Token(47, ";", 4));
		st.add(new Token(3, "CONST", 6));
		st.add(new Token(25, "a", 7));
		st.add(new Token(40, "=", 7));
		st.add(new Token(26, "-100", 7));
		st.add(new Token(47, ";", 7));
		st.add(new Token(25, "b", 8));
		st.add(new Token(40, "=", 8));
		st.add(new Token(26, "-200", 8));
		st.add(new Token(47, ";", 8));
		st.add(new Token(4, "VAR", 9));
		st.add(new Token(25, "X", 10));
		st.add(new Token(46, ",", 10));
		st.add(new Token(25, "Y", 10));
		st.add(new Token(46, ",", 10));
		st.add(new Token(25, "Z", 10));
		st.add(new Token(39, ":", 10));
		st.add(new Token(8, "INTEGER", 10));
		st.add(new Token(47, ";", 10));
		st.add(new Token(25, "array_a", 11));
		st.add(new Token(39, ":", 11));
		st.add(new Token(9, "ARRAY", 11));
		st.add(new Token(34, "[", 11));
		st.add(new Token(26, "0", 11));
		st.add(new Token(50, "..", 11));
		st.add(new Token(26, "20", 11));
		st.add(new Token(35, "]", 11));
		st.add(new Token(10, "OF", 11));
		st.add(new Token(8, "INTEGER", 11));
		st.add(new Token(47, ";", 11));
		st.add(new Token(25, "array_b", 12));
		st.add(new Token(46, ",", 12));
		st.add(new Token(25, "array_c", 12));
		st.add(new Token(46, ",", 12));
		st.add(new Token(25, "array_d", 12));
		st.add(new Token(39, ":", 12));
		st.add(new Token(9, "ARRAY", 12));
		st.add(new Token(34, "[", 12));
		st.add(new Token(26, "0", 12));
		st.add(new Token(50, "..", 12));
		st.add(new Token(26, "1000", 12));
		st.add(new Token(35, "]", 12));
		st.add(new Token(10, "OF", 12));
		st.add(new Token(8, "INTEGER", 12));
		st.add(new Token(47, ";", 12));
		st.add(new Token(5, "PROCEDURE", 15));
		st.add(new Token(25, "p_teste", 15));
		st.add(new Token(36, "(", 15));
		st.add(new Token(25, "idd", 15));
		st.add(new Token(39, ":", 15));
		st.add(new Token(8, "INTEGER", 15));
		st.add(new Token(37, ")", 15));
		st.add(new Token(47, ";", 15));
		st.add(new Token(2, "LABEL", 16));
		st.add(new Token(25, "label_a", 17));
		st.add(new Token(46, ",", 17));
		st.add(new Token(25, "label_b", 17));
		st.add(new Token(47, ";", 17));
		st.add(new Token(3, "CONST", 19));
		st.add(new Token(25, "a", 20));
		st.add(new Token(40, "=", 20));
		st.add(new Token(26, "100", 20));
		st.add(new Token(47, ";", 20));
		st.add(new Token(25, "b", 21));
		st.add(new Token(40, "=", 21));
		st.add(new Token(26, "200", 21));
		st.add(new Token(47, ";", 21));
		st.add(new Token(4, "VAR", 23));
		st.add(new Token(25, "X", 24));
		st.add(new Token(46, ",", 24));
		st.add(new Token(25, "Y", 24));
		st.add(new Token(46, ",", 24));
		st.add(new Token(25, "Z", 24));
		st.add(new Token(39, ":", 24));
		st.add(new Token(8, "INTEGER", 24));
		st.add(new Token(47, ";", 24));
		st.add(new Token(25, "array_a", 25));
		st.add(new Token(39, ":", 25));
		st.add(new Token(9, "ARRAY", 25));
		st.add(new Token(34, "[", 25));
		st.add(new Token(26, "0", 25));
		st.add(new Token(50, "..", 25));
		st.add(new Token(26, "20", 25));
		st.add(new Token(35, "]", 25));
		st.add(new Token(10, "OF", 25));
		st.add(new Token(8, "INTEGER", 25));
		st.add(new Token(47, ";", 25));
		st.add(new Token(25, "array_b", 26));
		st.add(new Token(39, ":", 26));
		st.add(new Token(9, "ARRAY", 26));
		st.add(new Token(34, "[", 26));
		st.add(new Token(26, "0", 26));
		st.add(new Token(50, "..", 26));
		st.add(new Token(26, "1000", 26));
		st.add(new Token(35, "]", 26));
		st.add(new Token(10, "OF", 26));
		st.add(new Token(8, "INTEGER", 26));
		st.add(new Token(47, ";", 26));
		st.add(new Token(6, "BEGIN", 27));
		st.add(new Token(25, "X", 28));
		st.add(new Token(38, ":=", 28));
		st.add(new Token(25, "a", 28));
		st.add(new Token(32, "*", 28));
		st.add(new Token(25, "b", 28));
		st.add(new Token(47, ";", 28));
		st.add(new Token(7, "END", 30));
		st.add(new Token(47, ";", 30));
		st.add(new Token(6, "BEGIN", 36));
		st.add(new Token(25, "x", 37));
		st.add(new Token(38, ":=", 37));
		st.add(new Token(26, "150", 37));
		st.add(new Token(47, ";", 37));
		st.add(new Token(6, "begin", 38));
		st.add(new Token(25, "x", 39));
		st.add(new Token(38, ":=", 39));
		st.add(new Token(26, "20", 39));
		st.add(new Token(47, ";", 39));
		st.add(new Token(7, "end", 40));
		st.add(new Token(47, ";", 40));
		st.add(new Token(11, "CALL", 43));
		st.add(new Token(25, "p_teste", 43));
		st.add(new Token(36, "(", 43));
		st.add(new Token(26, "100", 43));
		st.add(new Token(37, ")", 43));
		st.add(new Token(47, ";", 43));
		st.add(new Token(13, "IF", 46));
		st.add(new Token(36, "(", 46));
		st.add(new Token(26, "10", 46));
		st.add(new Token(41, ">", 46));
		st.add(new Token(26, "15", 46));
		st.add(new Token(37, ")", 46));
		st.add(new Token(14, "THEN", 46));
		st.add(new Token(6, "BEGIN", 47));
		st.add(new Token(7, "END", 48));
		st.add(new Token(15, "ELSE", 49));
		st.add(new Token(6, "BEGIN", 50));
		st.add(new Token(7, "END", 51));
		st.add(new Token(47, ";", 51));
		st.add(new Token(16, "WHILE", 54));
		st.add(new Token(36, "(", 54));
		st.add(new Token(25, "x", 54));
		st.add(new Token(45, "<>", 54));
		st.add(new Token(26, "0", 54));
		st.add(new Token(37, ")", 54));
		st.add(new Token(17, "DO", 54));
		st.add(new Token(6, "BEGIN", 55));
		st.add(new Token(7, "END", 56));
		st.add(new Token(47, ";", 56));
		st.add(new Token(18, "REPEAT", 59));
		st.add(new Token(6, "BEGIN", 60));
		st.add(new Token(7, "END", 61));
		st.add(new Token(19, "UNTIL", 62));
		st.add(new Token(25, "X", 62));
		st.add(new Token(41, ">", 62));
		st.add(new Token(26, "10", 62));
		st.add(new Token(47, ";", 62));
		st.add(new Token(21, "WRITELN", 65));
		st.add(new Token(36, "(", 65));
		st.add(new Token(25, "X", 65));
		st.add(new Token(46, ",", 65));
		st.add(new Token(25, "Z", 65));
		st.add(new Token(46, ",", 65));
		st.add(new Token(25, "Y", 65));
		st.add(new Token(37, ")", 65));
		st.add(new Token(47, ";", 65));
		st.add(new Token(27, "FOR", 68));
		st.add(new Token(25, "x", 68));
		st.add(new Token(38, ":=", 68));
		st.add(new Token(25, "y", 68));
		st.add(new Token(41, ">", 68));
		st.add(new Token(26, "2", 68));
		st.add(new Token(28, "TO", 68));
		st.add(new Token(26, "10", 68));
		st.add(new Token(31, "-", 68));
		st.add(new Token(26, "2", 68));
		st.add(new Token(17, "DO", 68));
		st.add(new Token(6, "BEGIN", 69));
		st.add(new Token(7, "END", 70));
		st.add(new Token(47, ";", 70));
		st.add(new Token(29, "CASE", 73));
		st.add(new Token(25, "A", 73));
		st.add(new Token(30, "+", 73));
		st.add(new Token(26, "2", 73));
		st.add(new Token(10, "OF", 73));
		st.add(new Token(26, "10", 74));
		st.add(new Token(39, ":", 74));
		st.add(new Token(6, "BEGIN", 74));
		st.add(new Token(7, "END", 74));
		st.add(new Token(47, ";", 74));
		st.add(new Token(26, "20", 75));
		st.add(new Token(39, ":", 75));
		st.add(new Token(6, "BEGIN", 75));
		st.add(new Token(7, "END", 75));
		st.add(new Token(47, ";", 75));
		st.add(new Token(26, "30", 76));
		st.add(new Token(39, ":", 76));
		st.add(new Token(6, "BEGIN", 76));
		st.add(new Token(7, "END", 76));
		st.add(new Token(7, "END", 77));
		st.add(new Token(47, ";", 77));
		st.add(new Token(7, "END", 78));
		st.add(new Token(49, ".", 78));

		SyntaticValidator sv = new SyntaticValidator(reverse(st));
		
		
			try {
				while(!sv.getEnd()) {
					sv.doStep();
					sv.getTerminals();
					sv.getTokens();
				}
			} catch (EditorException e) {
				System.out.println(e.getMessage());
			}
		
	}
	
	public static Stack reverse(Stack<Token> stack){
		Stack<Token> newStack = new Stack();
		while(!stack.empty()) {
			newStack.push( stack.pop());
		}
		return newStack;
	}
}
