package main.models.loginModel.outputmodels;

import org.springframework.stereotype.Component;

@Component
public class AuthOutput {
	private String authent;

	public String getAuthent() {
		return authent;
	}

	public void setAuthent(String authent) {
		this.authent = authent;
	}

	public AuthOutput(String authent) {
		super();
		this.authent = authent;
	}

	public AuthOutput() {
		super();
	}

	@Override
	public String toString() {
		return "AuthOutput [authent=" + authent + "]";
	}
	

}
