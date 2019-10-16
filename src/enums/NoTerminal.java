package enums;

import java.util.ArrayList;
import java.util.List;

import model.Token;

public class NoTerminal {
	public List<Token> getPossibleNoTerminals() {
		List<Token> possibleTokens = new ArrayList<Token>();
		
		possibleTokens.add(new Token(52,"PROGRAMA",null));
		possibleTokens.add(new Token(53,"BLOCO",null));
		possibleTokens.add(new Token(54,"DCLROT",null));
		possibleTokens.add(new Token(55,"LID",null));
		possibleTokens.add(new Token(56,"REPIDENT",null));
		possibleTokens.add(new Token(57,"DCLCONST",null));
		possibleTokens.add(new Token(58,"LDCONST",null));
		possibleTokens.add(new Token(59,"DCLVAR",null));
		possibleTokens.add(new Token(60,"LDVAR",null));
		possibleTokens.add(new Token(61,"TIPO",null));
		possibleTokens.add(new Token(62,"DCLPROC",null));
		possibleTokens.add(new Token(63,"DEFPAR",null));
		possibleTokens.add(new Token(64,"CORPO",null));
		possibleTokens.add(new Token(65,"REPCOMANDO",null));
		possibleTokens.add(new Token(66,"COMANDO",null));
		possibleTokens.add(new Token(67,"RCOMID",null));
		possibleTokens.add(new Token(68,"RVAR",null));
		possibleTokens.add(new Token(69,"PARAMETROS",null));
		possibleTokens.add(new Token(70,"REPPAR",null));
		possibleTokens.add(new Token(71,"ELSEPARTE",null));
		possibleTokens.add(new Token(72,"VARIAVEL",null));
		possibleTokens.add(new Token(73,"VARIAVEL1",null));
		possibleTokens.add(new Token(74,"REPVARIAVEL",null));
		possibleTokens.add(new Token(75,"ITEMSAIDA",null));
		possibleTokens.add(new Token(76,"REPITEM",null));
		possibleTokens.add(new Token(77,"EXPRESSAO",null));
		possibleTokens.add(new Token(78,"REPEXPSIMP",null));
		possibleTokens.add(new Token(79,"EXPSIMP",null));
		possibleTokens.add(new Token(80,"REPEXP",null));
		possibleTokens.add(new Token(81,"TERMO",null));
		possibleTokens.add(new Token(82,"REPTERMO",null));
		possibleTokens.add(new Token(83,"FATOR",null));
		possibleTokens.add(new Token(84,"CONDCASE",null));
		possibleTokens.add(new Token(85,"CONTCASE",null));
		possibleTokens.add(new Token(86,"RPINTEIRO",null));
		possibleTokens.add(new Token(87,"SEM EFEITO",null));

		return possibleTokens;
	}
}
