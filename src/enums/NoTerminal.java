package enums;

import java.util.ArrayList;
import java.util.List;

import syntactic.Terminal;

public class NoTerminal {
	public List<Terminal> getPossibleNoTerminals() {
		List<Terminal> possibleTerminals = new ArrayList<Terminal>();
		
		possibleTerminals.add(new Terminal(52,"PROGRAMA",null));
		possibleTerminals.add(new Terminal(53,"BLOCO",null));
		possibleTerminals.add(new Terminal(54,"DCLROT",null));
		possibleTerminals.add(new Terminal(55,"LID",null));
		possibleTerminals.add(new Terminal(56,"REPIDENT",null));
		possibleTerminals.add(new Terminal(57,"DCLCONST",null));
		possibleTerminals.add(new Terminal(58,"LDCONST",null));
		possibleTerminals.add(new Terminal(59,"DCLVAR",null));
		possibleTerminals.add(new Terminal(60,"LDVAR",null));
		possibleTerminals.add(new Terminal(61,"TIPO",null));
		possibleTerminals.add(new Terminal(62,"DCLPROC",null));
		possibleTerminals.add(new Terminal(63,"DEFPAR",null));
		possibleTerminals.add(new Terminal(64,"CORPO",null));
		possibleTerminals.add(new Terminal(65,"REPCOMANDO",null));
		possibleTerminals.add(new Terminal(66,"COMANDO",null));
		possibleTerminals.add(new Terminal(67,"RCOMID",null));
		possibleTerminals.add(new Terminal(68,"RVAR",null));
		possibleTerminals.add(new Terminal(69,"PARAMETROS",null));
		possibleTerminals.add(new Terminal(70,"REPPAR",null));
		possibleTerminals.add(new Terminal(71,"ELSEPARTE",null));
		possibleTerminals.add(new Terminal(72,"VARIAVEL",null));
		possibleTerminals.add(new Terminal(73,"VARIAVEL1",null));
		possibleTerminals.add(new Terminal(74,"REPVARIAVEL",null));
		possibleTerminals.add(new Terminal(75,"ITEMSAIDA",null));
		possibleTerminals.add(new Terminal(76,"REPITEM",null));
		possibleTerminals.add(new Terminal(77,"EXPRESSAO",null));
		possibleTerminals.add(new Terminal(78,"REPEXPSIMP",null));
		possibleTerminals.add(new Terminal(79,"EXPSIMP",null));
		possibleTerminals.add(new Terminal(80,"REPEXP",null));
		possibleTerminals.add(new Terminal(81,"TERMO",null));
		possibleTerminals.add(new Terminal(82,"REPTERMO",null));
		possibleTerminals.add(new Terminal(83,"FATOR",null));
		possibleTerminals.add(new Terminal(84,"CONDCASE",null));
		possibleTerminals.add(new Terminal(85,"CONTCASE",null));
		possibleTerminals.add(new Terminal(86,"RPINTEIRO",null));
		possibleTerminals.add(new Terminal(87,"SEM EFEITO",null));

		return possibleTerminals;
	}
}
