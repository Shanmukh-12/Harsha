package main.models.userModels.outputModels;

public class UserType {
	String userType;

	public UserType() {
		super();
	}

	

	public String getUserType() {
		return userType;
	}



	public void setUserType(String userType) {
		this.userType = userType;
	}



	@Override
	public String toString() {
		return "UserType [userType=" + userType + "]";
	}
	
	

}
