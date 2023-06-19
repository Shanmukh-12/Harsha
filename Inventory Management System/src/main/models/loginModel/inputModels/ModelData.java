package main.models.loginModel.inputModels;

import java.time.LocalDateTime;

public class ModelData {
	private String otpnum;
	private LocalDateTime expire;

	public String getOtpnum() {
		return otpnum;
	}

	public void setOtpnum(String otpnum) {
		this.otpnum = otpnum;
	}

	public LocalDateTime getExpire() {
		return expire;
	}

	public void setExpire(LocalDateTime expire) {
		this.expire = expire;
	}

	public ModelData() {
		super();
	}

	public ModelData(String otpnum, LocalDateTime expire) {
		super();
		this.otpnum = otpnum;
		this.expire = expire;
	}

}
