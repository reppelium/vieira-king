package errors;

public class EditorException extends Exception {
	
	private String errorMessage;
	private Integer lineNumber;
	private String type;
	
	public EditorException(String type, Integer lineNumber, String errorMessage) {
		this.errorMessage = errorMessage;
		this.lineNumber = lineNumber;
	}

	public String getMessage() {
		return lineNumber.toString() + ": " + type + errorMessage;
	}

	public Integer getLineNumber() {
		return lineNumber;
	}
	
	
	
	
}
