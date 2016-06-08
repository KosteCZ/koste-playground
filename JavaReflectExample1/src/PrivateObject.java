public class PrivateObject {

	private String privateString = null;

	public PrivateObject(String privateString) {
		this.privateString = privateString;
	}

	@SuppressWarnings("unused")
	private String getPrivateString(){
		return this.privateString;
	}
	  
	@SuppressWarnings("unused")
	private String getPrivateString2(String text){
		return this.privateString + text;
	}
  
}