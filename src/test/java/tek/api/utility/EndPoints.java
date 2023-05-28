package tek.api.utility;

public enum EndPoints {

	TOKEN_GENERATION("/api/token"), 
	TOKEN_VERIFY("/api/token/verify"),
	Get_All_Accounts("/api/accounts/get-all-accounts"),
	Get_Account("/api/accounts/get-account"),
	Add_Primary_Account("/api/accounts/add-primary-account");
	

	private String value;

	EndPoints(String input) {
		this.value = input;
	}

	public String getValue() {
		return this.value;
	}
}
