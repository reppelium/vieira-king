package syntactic;

import java.util.List;
import java.util.Stack;

import enums.NoTerminal;
import enums.Tokens;
import enums.UnionTA;
import errors.EditorException;
import model.Token;
import model.UnionTokenAction;

public class SyntaticValidator {
	
	private Stack<Token> tokens;
	private Stack<Token> terminals;
	private List<Token> possiblesNoTerminals;
	private List<Token> possiblesTokens;
	private List<UnionTokenAction> possiblesUnions;
	private Boolean end;
	
	
	public SyntaticValidator(Stack<Token> tokens) {
		end = false;
		this.tokens = tokens;
		
		NoTerminal nt = new NoTerminal();
		possiblesNoTerminals = nt.getPossibleNoTerminals();
		
		Tokens enum_tokens = new Tokens();
		possiblesTokens = enum_tokens.getPossibleTokens();
		
		UnionTA utas = new UnionTA();
		possiblesUnions = utas.getPossiblesUnions();
		
		List<Token> possibleTokens = enum_tokens.getPossibleTokens();
		
		this.terminals = new Stack<Token>();
		terminals.add(new Token(52, "PROGRAMA",null));
	}
	
	public void doStep() throws EditorException {
		if(terminals.empty() || tokens.empty()) {
			throw new EditorException("Syntatic", -1, "Elementos faltantes");
		}
		//x
		Token top = terminals.peek();
		// a
		Token nextInput = tokens.peek();
		System.out.println("Iteration");
		System.out.println("TOP->" + top.getIndex() + " - " + top.getSymbol());
		System.out.println("NEXT->" + nextInput.getIndex() + " - " + nextInput.getSymbol());
		
		if(isTerminal(top) || top.getSymbol().equals("$")) {
			if(top.getIndex() == nextInput.getIndex()) {
				terminals.pop();
				tokens.pop();
			}
			else {
				throw new EditorException("Syntatic", nextInput.getLine(), "Os index nao sao iguais. Era esperado " + terminals.peek() + " obtivemos "+ tokens.peek());
			}
		}
		else {
			List<String> result = foundUnion(nextInput, top);
			terminals.pop();
			
			for (Integer i=result.size() - 1;i>=0;i--) {
				if(!result.get(i).equals("NULL")) {
					terminals.add(generateToken(result.get(i)));
				}
				
			}
			
		}
		
		if(terminals.empty()) {
			end = true;
		}
		return;
	}
	
	private Token generateToken(String res) throws EditorException {
		for (Token token : possiblesNoTerminals) {
			if(token.getSymbol().toUpperCase().equals(res.toUpperCase())) {
				return new Token(token.getIndex(),token.getSymbol(),token.getLine());
			}
		}
		
		for (Token token : possiblesTokens) {
			if(token.getSymbol().toUpperCase().equals(res.toUpperCase())) {
				return new Token(token.getIndex(),token.getSymbol(),token.getLine());
			}
		}
		System.out.println(res);
		throw new EditorException("Syntatic", -1 , "Nao conseguiu converter o simbulo");
	}

	private List<String> foundUnion(Token tok, Token action) throws EditorException {
		for (UnionTokenAction union : possiblesUnions) {
			//use set
			if(tok.getIndex() == union.getToken() && action.getIndex() == union.getAction()) {
				return union.getResult();
			}
		}
		
		throw new EditorException("Syntatic", tok.getLine() , "Nao encontrou combinacao de Token e Acao. Era esperado " + action.getSymbol()+ " obtivemos "+ tok.getSymbol());
		
	}
	
	private Boolean isTerminal(Token top) {
		if(top.getIndex() < 52) {
			return true;
		}
		/*
		for (Token token : possiblesNoTerminals) {
			if(token.getSymbol() == top.getSymbol()) {
				return true;
			}
		}*/
		
		return false;
	}

	public Stack<Token> getTokens() {
		Stack<Token> ans = new Stack<Token>();
		for(Integer i=0;i<tokens.size();i++) {
			ans.add(tokens.get(i));
		}
		return ans;
	}

	public Stack<Token> getTerminals() {
		Stack<Token> ans = new Stack<Token>();
		for(Integer i=0;i<terminals.size();i++) {
			ans.add(terminals.get(i));
		}
		return ans;
	}
	
	
	public void setTokens(Stack<Token> tokens) {
		this.end = false;
		this.tokens = tokens;
	}

	public void setTerminals(Stack<Token> terminals) {
		this.end = false;
		this.terminals = terminals;
	}

	public Boolean getEnd() {
		return end;
	}
	
	
	
}
