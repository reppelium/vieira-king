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
		
		Boolean comments = false;
		Boolean literal = false;
		
		for(Integer lineNumber = 0; lineNumber < lines.size();lineNumber++) {
			String line = lines.get(lineNumber);
			
			String symbol = "";
			for(Integer i=0;i<line.length();i++) {
				Character charac = line.charAt(i);

				// Lógica comentarios
				if(charac == '(' && i<line.length() + 1 && line.charAt(i + 1) == '*') {
					//Caso exista algum symbulo pré stado
					if(symbol != "") {
						System.out.println("Erro, inicio de comentario com empilhagem no symbol.");
					}
					comments = true;
				}
				else if(charac == ')' && 1 < line.length() && line.charAt(i - 1) == '*') {
					comments = false;
				}
				else if(comments) {
					
				}
				//Lógica Strings
				else if(charac == '\'' && !literal) {
					if(symbol != "") {
						System.out.println("Erro, inicio de literal com empilhagem no symbol.");
					}
					literal = true;
				}
				else if(charac == '\'' && literal) {
					literal = false;
					Token auxToken = generateToken("literal", symbol, possibleTokens, lineNumber);
					if(auxToken != null) {
						tokens.add(auxToken);
						symbol = "";
					}
				}
				else if(literal) {
					symbol += charac.toString();
				}	
				// Identificadores OU Palavra Reservada
				else if((charac >= 'a' && charac <= 'z')||(charac >= 'A' && charac <= 'Z')) {
					
					Integer j = i;
					symbol = "";
					while((charac >= 'a' && charac <= 'z')
							||(charac >= 'A' && charac <= 'Z')
							||(charac >= '0' && charac <= '9')) {
						
						symbol += charac.toString();
						j++;
						if(j == line.length()) {
							break;
						}
						charac = line.charAt(j);
						
					}
					
					Token auxToken = generateToken("identificador", symbol, possibleTokens, lineNumber);
					if(auxToken != null) {
						tokens.add(auxToken);
						symbol = "";
					}
					
					symbol = "";
					i = j - 1;
				}
				//Inteiro
				else if((charac >= '0' && charac <= '9')||
						(charac ==  '-' && i+1<line.length() && 
									(line.charAt(i + 1) >= '0' && line.charAt(i + 1) <= '9'))) {
					Integer j = i;
					symbol = "";
					if(charac == '-') {
						symbol += charac.toString();
						charac = line.charAt(j);
						j++;
					}
					
					while(charac >= '0' && charac <= '9') {
						
						symbol += charac.toString();
						j++;
						if(j == line.length()) {
							break;
						}
						charac = line.charAt(j);
						
					}
					
					Token auxToken = generateToken("inteiro", symbol, possibleTokens, lineNumber);
					if(auxToken != null) {
						tokens.add(auxToken);
						symbol = "";
					}
					
					symbol = "";
					i = j - 1;
					
				}
				//Outros Simbolos
				else if(charac != ' ' && charac != '\t') {
					
					symbol += charac.toString();
					
					Token auxToken = generateToken(null, symbol, possibleTokens, lineNumber);
					if(auxToken != null) {
						tokens.add(auxToken);
						symbol = "";
					}
					
				}
				
			
				
			}
			
		}
		return tokens;
	}
	
	
	public Token generateToken(String type,String symbol,List<Token> possibleTokens, Integer lineNumber) {
		if(symbol == null || symbol == "") {
			System.out.println("NUll?");
			return null;
		}
		
		if(type == "literal") {
			return new Token(48, symbol, lineNumber);
		}
		if(type == "inteiro") {
			return new Token(26, symbol, lineNumber);
		}
		
		for(Token possibleToken : possibleTokens) {
			System.out.println(lineNumber + "- " + symbol + possibleToken.getSymbol());
			if((symbol.toUpperCase()).equals((possibleToken.getSymbol()).toUpperCase())) {
				System.out.println("ACHOU");
				return new Token(possibleToken.getIndex(), symbol, lineNumber);
			}
		}

		
		if(type == "identificador") {
			return new Token(25, symbol, lineNumber);
		}
		
		
		return null;
	}
	
	//TODO ENUM
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
