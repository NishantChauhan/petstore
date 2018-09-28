package com.petstore.user.entities;

public class ExecutionStatus {
	String status;
	
	public ExecutionStatus(String status){
		this.status=StatusEnum.valueOf(status).value;
	}
	
	public ExecutionStatus(StatusEnum status){
		this.status=status.value;
	}
	public enum StatusEnum {
		OK("Login Successful"),

		INTERNAL_ERROR("Internal Error"),
		
		BAD_LOGIN("Invalid Login"),
		NOT_FOUND("User Not Found");
		
		private String value;

		StatusEnum(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		@Override
		public String toString() {
			return String.valueOf(value);
		}

		public static StatusEnum fromValue(String text) {
			for (StatusEnum b : StatusEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}
	}

	public String getStatus() {
		return StatusEnum.fromValue(status).value;
	}
	public void setStatus(String status) {
		this.status = StatusEnum.fromValue(status).value;
	}
	
	
}
