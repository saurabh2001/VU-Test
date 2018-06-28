
public class APIObject {

	private String appName;
	private String apiName;
	private String apiVersion;
	
	private boolean oldVersionFlag;

	
	
	public APIObject(String appName, String apiName, String apiVersion) {
		super();
		this.appName = appName;
		this.apiName = apiName;
		this.apiVersion = apiVersion;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getApiName() {
		return apiName;
	}

	public void setApiName(String apiName) {
		this.apiName = apiName;
	}

	public String getApiVersion() {
		return apiVersion;
	}

	public void setApiVersion(String apiVersion) {
		this.apiVersion = apiVersion;
	}
	
	public boolean isOldVersionFlag() {
		return oldVersionFlag;
	}

	public void setOldVersionFlag(boolean oldVersionFlag) {
		this.oldVersionFlag = oldVersionFlag;
	}

	@Override
	public String toString() {
		return "APIObject [appName=" + appName + ", apiName=" + apiName + ", apiVersion=" + apiVersion
				+ ", oldVersionFlag=" + oldVersionFlag + "\n]";
	}

}
