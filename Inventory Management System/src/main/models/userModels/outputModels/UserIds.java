package main.models.userModels.outputModels;

public class UserIds {
	int userId;
	String userName;
	public UserIds() {
		super();
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Override
	public String toString() {
		return "UserIds [userId=" + userId + ", userName=" + userName + "]";
	}
	

}
