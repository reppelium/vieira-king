package model;

import java.util.Arrays;
import java.util.List;

public class UnionTokenAction {
	
	Integer action;
	Integer token;
	List<String> result;
	
	public UnionTokenAction(Integer action, Integer token, String res) {
		this.action = action;
		this.token = token;
		this.result = Arrays.asList(res.split("@!@"));
	}

	public Integer getAction() {
		return action;
	}

	public Integer getToken() {
		return token;
	}

	public List<String> getResult() {
		return result;
	}

	public void setAction(Integer action) {
		this.action = action;
	}

	public void setToken(Integer token) {
		this.token = token;
	}

	public void setResult(List<String> result) {
		this.result = result;
	}
	
}
