package tek.api.utility;

public enum EndPoints {

	TOKEN_GENERATION("/api/token"), 
	TOKEN_VERIFY("/api/token/verify");

	private String value;

	EndPoints(String input) {
		this.value = input;
	}

	public String getValue() {
		return this.value;
	}
}
