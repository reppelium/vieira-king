package semantic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import errors.EditorException;
import model.Token;

public class SemanticValidator {
	private Stack<Token> tokens;
	private Stack<Variable> variables;
	private Map<String,List<Variable>> functionParams;
	
	public SemanticValidator(Stack<Token> tokens) {
		this.tokens = tokens;
		functionParams = new HashMap<String,List<Variable>>();
	}
	
	public void validate() throws EditorException {
		
		//loop dos tokens
		//se eu achar 25 -> variavel ou const ou o caralho a quatro
		//para saber o tipo devo voltar e ver o que foi no passado
		
		String category = "";
		
		variables = new Stack<Variable>();
		Stack<Variable> memo = new Stack<Variable>();
		
		Boolean insideProcedure = false;
		Boolean begin = false;
		for(Integer i=0;i<tokens.size();i++) {
			Token local_token = tokens.get(i);
			System.out.println(i);
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
				//begin
				case 6:
					begin = true;
					if(!memo.empty()) {
						System.out.println("ERRO PORRA");
					}
					break;
				case 7:
					if(insideProcedure) {
						insideProcedure = false;
						deleteVariableByPos(1);
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
				//call
				case 11:
					// while encontrar um ; ele compara 1 a 1 as var encontrada com as que devem ser vindas
					Boolean first = false;
					String procedure = "";
					List<Variable> procVars = new ArrayList<Variable>();
					Integer j = 0;
					while(local_token.getIndex() != 37) {
						i++;
						local_token = tokens.get(i);
						
						if(local_token.getIndex() == 25) {
							if(!first) {
								procedure = local_token.getSymbol();
								procVars = functionParams.get(procedure);
								first = true;
							} else {
								if(j == procVars.size() && j != 0) {
									throw new EditorException("Semantico", local_token.getLine(), "Era esperado " + j + " argumentos e foram passados a mais");
								}
								Variable paramExpected = procVars.get(j);
								Variable paramPassed = getVariable(local_token);
								if(!paramPassed.getType().equals(paramExpected.getType())) {
									throw new EditorException("Semantico", local_token.getLine(), "Era esperado variavel do tipo" + paramExpected.getType() + ", porem foi recebido o tipo" + paramPassed.getType());
								}
							}
							
						}
						else if(local_token.getIndex() == 26) {
							
							Variable paramExpected = procVars.get(j);
							if(!paramExpected.getType().equals("INTEGER")) {
								throw new EditorException("Semantico", local_token.getLine(), "Era esperado variavel do tipo" + paramExpected.getType() + ", porem foi recebido o tipo INTEGER");
							}
						}
						else if(local_token.getIndex() == 48) {
							
							Variable paramExpected = procVars.get(j);
							if(!paramExpected.getType().equals("LITERAL")) {
								throw new EditorException("Semantico", local_token.getLine(), "Era esperado variavel do tipo" + paramExpected.getType() + ", porem foi recebido o tipo INTEGER");
							}
						}
						else if(local_token.getIndex() == 46) {
							j++;
						}
					}
					j++;
					if(j != procVars.size()) {
						throw new EditorException("Semantico", local_token.getLine(), "Era esperado " + procVars.size() + " argumentos e foram passados " + j);
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
								Variable aux_var = new Variable(0, category, local_token.getSymbol());
								aux_var.setType("PROCEDURE");
								insertVariable(aux_var, local_token);
								category = "Parameter";
								break;
							}
							else if(category.equals("Label")) {
								Variable aux_var = new Variable(0, category, local_token.getSymbol());
								aux_var.setType("Label");
								insertVariable(aux_var, local_token);
								break;
							}
						}
						
						memo.add(new Variable(pos, category, local_token.getSymbol()));
					}
					break;
				//inteiro
				case 26:
					while(!memo.empty()) {
						Variable local_var = memo.pop();
						local_var.setType("INTEGER");
						insertVariable(local_var, local_token);
					}
					break;
				//literal
				case 48:
					while(!memo.empty()) {
						Variable local_var = memo.pop();
						local_var.setType("LITERAL");
						insertVariable(local_var, local_token);
					}
					break;
				
				
			}

			
		}
		printt();
	}
	

	private void printt() {
		System.out.println("------------------- VARS START ------------------");
		for(Integer i=variables.size() -1;i>=0;i--) {
			Variable v = variables.get(i);
			
			System.out.println(v.getName() + " - " +
									v.getCategory() + " - " +
									v.getType() + " - " +
									v.getPos());
			if(v.getCategory().equals("Procedure")) {
				List<Variable> params = functionParams.get(v.getName());
				for(Integer j=0;j<params.size();j++) {
					Variable v2 = params.get(j);
					
					System.out.println("params - " + v2.getName() + " - " +
											v2.getCategory() + " - " +
											v2.getType() + " - " +
											v2.getPos());
				}
 			}
		}
		System.out.println("------------------- VARS END ------------------");
	}
	
	private void insertVariable(Variable var, Token t) throws EditorException {
		for(Integer i=variables.size() -1;i>=0;i--) {
			Variable v = variables.get(i);
			if((v.getName().toUpperCase()).equals((var.getName().toUpperCase())) && v.getPos() == var.getPos()) {
				throw new EditorException("Semantico", t.getLine() , 
						("O identificador '" + t.getSymbol() + "' já foi declarado."));
			}
		}
		variables.add(var);
	}
	
	private Variable getVariable(Token t) throws EditorException {
		
		for(Integer i=variables.size() -1;i>=0;i--) {
			Variable v = variables.get(i);
			if((v.getName().toUpperCase()).equals((t.getSymbol().toUpperCase()))) {
				return v;
			}
		}
		
		throw new EditorException("Semantico", t.getLine() , 
							("O identificador '" + t.getSymbol() + "' nao foi declarado."));
	}
	
	private void deleteVariableByPos(Integer pos) throws EditorException {
		List<Variable> list_of_parameters = new ArrayList<Variable>();
		while(variables.peek().getPos() == pos) {
			Variable v = variables.pop();
			if(v.getCategory().equals("Parameter")) {
				list_of_parameters.add(v);
			}
			
		}
		if(variables.peek().getCategory().equals("Procedure")) {
			functionParams.put(variables.peek().getName(), list_of_parameters);
			return;
		}
		else if(list_of_parameters.size() != 0) {
			throw new EditorException("Semantico", -1, "Erro interno ao criar paraametros da funcao");
		}
	}

}
