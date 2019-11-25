package semantic;

import java.util.ArrayList;
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
		
		//loop dos tokens
		//se eu achar 25 -> variavel ou const ou o caralho a quatro
		//para saber o tipo devo voltar e ver o que foi no passado
		
		String category = "";
		String type = "";
		
		variables = new Stack<Variable>();
		Stack<Variable> memo = new Stack<Variable>();
		
		Boolean insideProcedure = false;
		Boolean begin = false;
		for(Integer i=0;i<tokens.size();i++) {
			Token local_token = tokens.get(i);
			System.out.println(i);
			printt();
			switch(local_token.getIndex()) {
				case 2:
					category = "Label";
					break;
				case 3:
					category = "Constante";
					break;
				case 4:
					category = "Variavel";
					break;
				case 5:
					category = "Procedure";
					break;
				case 7:
					if(insideProcedure) {
						insideProcedure = false;
						deleteVariableByPos(1);
					}
					
					break;
				case 25:
					//aqui ele ta chamando
					if(begin) {
						getVariable(local_token);
					}
					//aqui significa que ele ta criando a variavel
					else if(!begin) {
						Integer pos;
						
						if(insideProcedure) {
							pos = 1;
						}
						else {
							pos = 0;
							if(category.equals("Procedure")) {
								insideProcedure = true;
							}
						}
						
						memo.add(new Variable(pos, category, local_token.getSymbol()));
					}
					break;
					
				case 8:
					while(!memo.empty()) {
						Variable local_var = memo.pop();
						local_var.setType("INTEGER");
						insertVariable(local_var, local_token);
					}
					break;
				case 9:
					while(!memo.empty()) {
						Variable local_var = memo.pop();
						local_var.setType("ARRAY");
						insertVariable(local_var, local_token);
					}
					break;
				case 11:
					break;
				//literal
				case 48:
					while(!memo.empty()) {
						Variable local_var = memo.pop();
						local_var.setType("LITERAL");
						insertVariable(local_var, local_token);
					}
					break;
				//literal
				case 26:
					while(!memo.empty()) {
						Variable local_var = memo.pop();
						local_var.setType("INTEGER");
						insertVariable(local_var, local_token);
					}
					break;
				//begin
				case 6:
					begin = true;
					if(!memo.empty()) {
						System.out.println("ERRO PORRA");
					}
					break;
			}
			
		}
		
	}
	
	private void printt() {
		System.out.println("------------------- LETS START ------------------");
		for(Integer i=variables.size() -1;i>=0;i--) {
			Variable v = variables.get(i);
			System.out.println(v.getName() + " - " +
									v.getCategory() + " - " +
									v.getType() + " - " +
									v.getPos());
		}
		System.out.println("------------------- LETS END ------------------");
	}
	
	private void insertVariable(Variable var, Token t) throws EditorException {
		for(Integer i=variables.size() -1;i>=0;i--) {
			Variable v = variables.get(i);
			if(v.getName().equals(var.getName()) && v.getPos() == var.getPos()) {
				throw new EditorException("Semantico", t.getLine() , 
						("O identificador '" + t.getSymbol() + "' já foi declarado."));
			}
		}
		variables.add(var);
	}
	
	private Variable getVariable(Token t) throws EditorException {
		
		for(Integer i=variables.size() -1;i>=0;i--) {
			Variable v = variables.get(i);
			if(v.getName().equals(t.getSymbol())) {
				return v;
			}
		}
		
		throw new EditorException("Semantico", t.getLine() , 
							("O identificador '" + t.getSymbol() + "' nao foi declarado."));
	}
	
	private void deleteVariableByPos(Integer pos) {
		while(variables.peek().getPos() == pos) {
			variables.pop();
		}
	}

}
