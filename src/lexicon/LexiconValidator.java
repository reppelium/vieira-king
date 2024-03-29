package lexicon;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import enums.Tokens;
import errors.EditorException;
import model.Token;


public class LexiconValidator {
	
	public Stack<Token> validate(List<String> lines) throws EditorException {
		Stack<Token> tokens = new Stack<Token>();
		
		Tokens enum_tokens = new Tokens();
		
		List<Token> possibleTokens = enum_tokens.getPossibleTokens();
		
		Boolean comments = false;
		
		for(Integer lineNumber = 1; lineNumber <= lines.size();lineNumber++) {
			String line = lines.get(lineNumber - 1);
			
			String symbol = "";
			for(Integer i=0;i<line.length();i++) {
				Character charac = line.charAt(i);
				// Lógica comentarios
				if(charac == '(' && i<line.length() + 1 && line.charAt(i + 1) == '*') {
					if(symbol != "") {
						System.out.println("Erro, inicio de comentario com empilhagem no symbol.");
					}
					comments = true;
				}
				else if(charac == ')' && 1 < line.length() && line.charAt(i - 1) == '*') {
					comments = false;
				}
				else if(comments) {
					continue;
				}
				
				//Logica Literal
				else if(charac == '\'') {
					symbol = "";
					Integer j = i + 1;
					if(j < line.length()) {
						charac = line.charAt(j);
						
						while(charac != '\'') {
							
							symbol += charac.toString();
							j++;
							if(j == line.length()) {
								throw new EditorException("Lexico", lineNumber, "Literal não foi fechado na linha de abertura.");
							}
							charac = line.charAt(j);
							
						}
					}
					else {
						throw new EditorException("Lexico", lineNumber, "Literal não foi fechado na linha de abertura.");
					}
					
					
					if(symbol.length() > 255) {
						throw new EditorException("Lexico", lineNumber, "Literal com tamanho maior que 255 caracteres.");

					}
					
					Token auxToken = generateToken("literal", symbol, possibleTokens, lineNumber);
					if(auxToken != null) {
						tokens.add(auxToken);
						symbol = "";
					}
					i = j;
					continue;
				}	
				// Logica Identificadores OU Palavra Reservada
				else if((charac >= 'a' && charac <= 'z')||(charac >= 'A' && charac <= 'Z')) {
					
					Integer j = i;
					symbol = "";
					
					while((charac >= 'a' && charac <= 'z')
							||(charac >= 'A' && charac <= 'Z')
							||(charac >= '0' && charac <= '9')
							||(charac == '_')) {
						
						symbol += charac.toString();
						j++;
						if(j == line.length()) {
							break;
						}
						charac = line.charAt(j);
						
					}
					if(symbol.length() > 30) {
						System.out.println("erro linha: " + lineNumber);
						throw new EditorException("Lexico", lineNumber, "Identificador com tamanho maior que 30 caracteres.");
					}
					Token auxToken = generateToken("identificador", symbol, possibleTokens, lineNumber);
					if(auxToken != null) {
						tokens.add(auxToken);
						symbol = "";
					}
					
					symbol = "";
					i = j - 1;
					continue;
				}
				//Inteiro
				else if((charac >= '0' && charac <= '9')||
						(charac ==  '-' && i+1<line.length() && 
									(line.charAt(i + 1) >= '0' && line.charAt(i + 1) <= '9'))) {
					Integer j = i;
					symbol = "";
					if(charac == '-') {
						symbol += charac.toString();
						j++;
						
					}
					if(j < line.length() - 1) {
						charac = line.charAt(j);
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
					
					Integer local_int = Integer.parseInt(symbol);
					if(local_int > 32767 || local_int < -32767) {
						throw new EditorException("Lexico", lineNumber, "Inteiro com tamanho incorreto.");
					}
					
					if(auxToken != null) {
						tokens.add(auxToken);
						symbol = "";
					}
					
					symbol = "";
					i = j - 1;
					continue;
				}
				//Outros Simbolos
				else if(charac != ' ' && charac != '\t') {
					
					symbol += charac.toString();
					
					Token auxToken = generateToken(null, symbol, possibleTokens, lineNumber);
					
					if(auxToken != null) {
						
						// Verifica se esse acumulado mais o próximo elemento tambem é um token
						if(line.length() > i + 1) {
							symbol += line.charAt(i + 1);
							Token auxToken2 = generateToken(null, symbol, possibleTokens, lineNumber);
							if(auxToken2 != null) {
								tokens.add(auxToken2);
								i++;
							}
							else {
								tokens.add(auxToken);
							}
						}
						else {
							tokens.add(auxToken);
						}
						
						symbol = "";
					}
					
				}
				
			}
			
		}
		if(comments) {
			throw new EditorException("Lexico", lines.size(), "Comentario não foram fechados.");
		}
		return tokens;
	}
	
	
	private Token generateToken(String type,String symbol,List<Token> possibleTokens, Integer lineNumber) throws EditorException {
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
			if((symbol.toUpperCase()).equals((possibleToken.getSymbol()).toUpperCase())) {
				return new Token(possibleToken.getIndex(), symbol, lineNumber);
			}
		}

		
		if(type == "identificador") {
			return new Token(25, symbol, lineNumber);
		}
		
		
		return null;
	}
	
}
