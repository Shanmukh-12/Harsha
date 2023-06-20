package main.models.userModels.outputModels;

import java.time.LocalDateTime;

public class UserOutput {
	private int userId;
	private String userName;
	private String userPassword;
	private String userType;
	private String status;
	private String otp;
	private LocalDateTime otpExpiryTime;

	public UserOutput() {

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

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public LocalDateTime getOtpExpiryTime() {
		return otpExpiryTime;
	}

	public void setOtpExpiryTime(LocalDateTime otpExpiryTime) {
		this.otpExpiryTime = otpExpiryTime;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userPassword=" + userPassword + ", userType="
				+ userType + ", status=" + status + ", otp=" + otp + ", otpExpiryTime=" + otpExpiryTime + "]";
	}

}
