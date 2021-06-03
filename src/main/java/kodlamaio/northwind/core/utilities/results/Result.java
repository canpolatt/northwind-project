package kodlamaio.northwind.core.utilities.results;

public class Result { //super-type
	private boolean success;
	private String message;
	
	public Result(boolean success) {
		this.success=success;	
		}
	
	public Result(boolean success, String message) {
		this(success); //tek parametreli olan constructorı çalıştır.
		this.message=message;
	}
	public boolean isSuccess() {
		return this.success;
	}
	public String getMessage() {
		return this.message;
	}
}
