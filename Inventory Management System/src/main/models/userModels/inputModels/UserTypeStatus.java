package main.models.userModels.inputModels;

public class UserTypeStatus {
	String userType;
	String userStatus;
	public UserTypeStatus() {
		super();
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	@Override
	public String toString() {
		return "UserTypeStatus [userType=" + userType + ", userStatus=" + userStatus + "]";
	}
	

}
