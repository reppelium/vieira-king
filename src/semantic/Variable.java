package semantic;

public class Variable {
	Integer pos;
	String category;
	String name;
	String type;

	public Variable(Integer pos, String category, String name) {
		super();
		this.pos = pos;
		this.category = category;
		this.name = name;
	}

	public String getCategory() {
		return category;
	}
	
	public String getType() {
		return type;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getPos() {
		return pos;
	}

	public String getName() {
		return name;
	}

	public void setPos(Integer pos) {
		this.pos = pos;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
