package lexicon;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class LexiconValidator {
	
	public Stack<Token> validate(List<String> lines) {
		Stack<Token> tokens = new Stack<Token>();
		
		List<Token> possibleTokens = getPossibleTokens();

		for(Integer lineNumber = 0; lineNumber < lines.size();lineNumber++) {
			String line = lines.get(lineNumber);
			Boolean comments = false;
			Boolean literal = false;
			String symbol = "";
			for(Integer i=0;i<line.length();i++) {
				Character charac = line.charAt(i);
				
				if(charac == '(' && i<line.length() + 1 && line.charAt(i + 1) == '*') {
					comments = true;
				}
				else if(charac == ')' && 1 < line.length() && line.charAt(i - 1) == '*') {
					comments = false;
				}
				else if(comments) {
					
				}
				else if(charac != ' ' || literal) {
					
					if(literal && charac == '\'' ) {
						literal = false;
						tokens.add(new Token(48, symbol, lineNumber + 1));
						symbol = "";
					}
					else if(charac == '\'') {
						literal = true;
					}
					else {
						
						symbol += charac.toString();
					}
				}
				else {
					Token auxiliarToken = generateToken(symbol,possibleTokens, lineNumber + 1);
					tokens.add(auxiliarToken);
					symbol = "";
				}
				
				System.out.println(symbol);
			}
			
			if(lineNumber + 1== lines.size() && symbol != "") {
				Token auxiliarToken = generateToken(symbol,possibleTokens, lineNumber + 1);
				tokens.add(auxiliarToken);
				symbol = "";
			}
			
		}
		return tokens;
	}
	
	private Token generateToken(String symbol, List<Token> possibleTokens, Integer lineNumber) {
		
		for(Token possibleToken : possibleTokens) {
			if((symbol.toUpperCase()).equals((possibleToken.getSymbol()).toUpperCase())) {
				return new Token(possibleToken.getIndex(), symbol, lineNumber);
			}
		}
		
		// se chegou aqui é porque não é uma palavra reservada, preciso determinar se é string ou integer
		
		Integer theInt = 0;
		Boolean negative = false;
		Boolean isInt = false;
		for(int i=0;i<symbol.length();i++) {
			Character charac = symbol.charAt(i);
			if (charac == '-') {
				negative = true;
				i++;
			}
			if(charac >= '0' && charac <= '9') {
				isInt = true;
			}
			else {
				isInt = false;
				break;
			}	
		}
		if(isInt) {
			return new Token(26, symbol, lineNumber);
		}
		Boolean isVariable = false;
		for(int i=0;i<symbol.length();i++) {
			Character charac = symbol.charAt(i);
			if(i == 0) {
				if(charac >= '0' && charac <= '9') {
					isVariable = false;
					break;
				}
			}
		
			if((charac >= '0' && charac <= '9') || 
					(charac >= 'a' && charac <= 'z')|| 
					(charac >= 'A' && charac <= 'Z')) {
				isVariable = true;
			}
			else {
				isVariable = false;
				break;
			}
		}
		if(isVariable) {
			return new Token(25, symbol, lineNumber);
		}
		
		System.out.println("merda - " + symbol + " - line: " + lineNumber);
		return null;
	}

	public List<Token> getPossibleTokens() {
		List<Token> possibleTokens = new ArrayList<Token>();
		//read file
		String path = "docs/token.tsv";
		File file = new File(path);
		
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(file));
			String line;
			line = br.readLine();
			while ((line = br.readLine()) != null) {
				String[] splitedToken = line.split("	");
				
				Token token = new Token(Integer.parseInt(splitedToken[0]),splitedToken[1],null);
				possibleTokens.add(token);
			}
			br.close();
			return possibleTokens;
		} catch (IOException e) {
			System.out.println("Erro no getPossibleToken()");
			e.printStackTrace();
		}
		
		return null;
	}
	
}
