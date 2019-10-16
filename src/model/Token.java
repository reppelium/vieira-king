package model;

public class Token {
	
	private Integer index;
	private String symbol;
	private Integer line;
	
	public Token(Integer index, String symbol, Integer line) {
		super();
		this.index = index;
		this.symbol = symbol;
		this.line = line;
	}

	public Integer getIndex() {
		return index;
	}

	public String getSymbol() {
		return symbol;
	}

	public Integer getLine() {
		return line;
	}

	@Override
	public String toString() {
		return "Token [index=" + index + ", symbol=" + symbol + ", line=" + line + "]";
	}
	
	
	
}
