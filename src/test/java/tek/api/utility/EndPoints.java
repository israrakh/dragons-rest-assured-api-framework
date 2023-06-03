package tek.api.utility;

public enum EndPoints {

	TOKEN_GENERATION("/api/token"), 
	TOKEN_VERIFY("/api/token/verify"),
	GET_ALL_AACCOUNT("/api/accounts/get-all-accounts"),
	GET_ACCOUNT("/api/accounts/get-account"),
	ADD_PRIMARY_ACCOUNT("/api/accounts/add-primary-account"),
	GET_ALL_PLAN_CODE("/api/plans/get-all-plan-code"),
	USER_PROFILE("/api/user/profile");
	

	private String value;

	EndPoints(String input) {
		this.value = input;
	}

	public String getValue() {
		return this.value;
	}
}
