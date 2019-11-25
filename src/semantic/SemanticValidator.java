package semantic;

import java.util.Stack;

import errors.EditorException;
import model.Token;

public class SemanticValidator {
	private Stack<Token> tokens;
	private Stack<Variable> variables;
	
	public SemanticValidator(Stack<Token> tokens) {
		this.tokens = tokens;
	}
	
	public void validate() throws EditorException {
		
	}
	
	private void insertVariable(Variable var, Token t) throws EditorException {
		for(Integer i=variables.size();i>=0;i--) {
			Variable v = variables.get(i);
			if(v.getName().equals(var.getName()) && v.getPos() == var.getPos()) {
				throw new EditorException("Semantico", t.getLine() , 
						("O identificador '" + t.getSymbol() + "' já foi declarado."));
			}
		}
	}
	
	private Variable getVariable(Token t) throws EditorException {
		
		for(Integer i=variables.size();i>=0;i--) {
			Variable v = variables.get(i);
			if(v.getName().equals(t.getSymbol())) {
				return v;
			}
		}
		
		throw new EditorException("Semantico", t.getLine() , 
							("O identificador '" + t.getSymbol() + "' nao foi declarado."));
	}
	
	private void removeVariableByPos(Integer pos) {
		while(variables.peek().getPos() == pos) {
			variables.pop();
		}
	}

}
