package errors;

public class EditorException extends Exception {
	
	private String errorMessage;
	private Integer lineNumber;
	
	public EditorException(Integer lineNumber, String errorMessage) {
		this.errorMessage = errorMessage;
		this.lineNumber = lineNumber;
	}

	public String getMessage() {
		return errorMessage;
	}

	public Integer getLineNumber() {
		return lineNumber;
	}
	
	
	
	
}
