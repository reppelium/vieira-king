package enums;

import java.util.ArrayList;
import java.util.List;

import model.Token;

public class Tokens {
	
	public List<Token> getPossibleTokens() {
		List<Token> possibleTokens = new ArrayList<Token>();
		
		possibleTokens.add(new Token(1,"Program",null));
		possibleTokens.add(new Token(2,"Label",null));
		possibleTokens.add(new Token(3,"Const",null));
		possibleTokens.add(new Token(4,"Var",null));
		possibleTokens.add(new Token(5,"Procedure",null));
		possibleTokens.add(new Token(6,"Begin",null));
		possibleTokens.add(new Token(7,"End",null));
		possibleTokens.add(new Token(8,"Integer",null));
		possibleTokens.add(new Token(9,"Array",null));
		possibleTokens.add(new Token(10,"Of",null));
		possibleTokens.add(new Token(11,"Call",null));
		possibleTokens.add(new Token(12,"Goto",null));
		possibleTokens.add(new Token(13,"If",null));
		possibleTokens.add(new Token(14,"Then",null));
		possibleTokens.add(new Token(15,"Else",null));
		possibleTokens.add(new Token(16,"While",null));
		possibleTokens.add(new Token(17,"Do",null));
		possibleTokens.add(new Token(18,"Repeat",null));
		possibleTokens.add(new Token(19,"Until",null));
		possibleTokens.add(new Token(20,"Readln",null));
		possibleTokens.add(new Token(21,"Writeln",null));
		possibleTokens.add(new Token(22,"Or",null));
		possibleTokens.add(new Token(23,"And",null));
		possibleTokens.add(new Token(24,"Not",null));
		possibleTokens.add(new Token(27,"For",null));
		possibleTokens.add(new Token(28,"To",null));
		possibleTokens.add(new Token(29,"Case",null));
		possibleTokens.add(new Token(30,"+",null));
		possibleTokens.add(new Token(31,"-",null));
		possibleTokens.add(new Token(32,"*",null));
		possibleTokens.add(new Token(33,"/",null));
		possibleTokens.add(new Token(34,"[",null));
		possibleTokens.add(new Token(35,"]",null));
		possibleTokens.add(new Token(36,"(",null));
		possibleTokens.add(new Token(37,")",null));
		possibleTokens.add(new Token(38,":=",null));
		possibleTokens.add(new Token(39,":",null));
		possibleTokens.add(new Token(40,"=",null));
		possibleTokens.add(new Token(41,">",null));
		possibleTokens.add(new Token(42,">=",null));
		possibleTokens.add(new Token(43,"<",null));
		possibleTokens.add(new Token(44,"<=",null));
		possibleTokens.add(new Token(45,"<>",null));
		possibleTokens.add(new Token(46,",",null));
		possibleTokens.add(new Token(47,";",null));
		possibleTokens.add(new Token(49,".",null));
		possibleTokens.add(new Token(50,"..",null));
		possibleTokens.add(new Token(51,"$",null));

		return possibleTokens;
	}
	
}
