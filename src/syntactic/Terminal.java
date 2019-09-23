package syntactic;

public class Terminal {
	
	private Integer index;
	private String symbol;
	private Integer line;
	
	public Terminal(Integer index, String symbol, Integer line) {
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
	
}
