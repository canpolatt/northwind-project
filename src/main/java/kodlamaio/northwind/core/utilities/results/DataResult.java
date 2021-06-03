package kodlamaio.northwind.core.utilities.results;

public class DataResult<T> extends Result { //genericle çalışıyoruz çünkü birden fazla datatype olabilir

	private T data;
	public DataResult(T data, boolean success, String message) {
		super(success, message); //base sınıfın constructorlarını çalışıp değerleri set ediyor
		this.data=data;
	}
	
	public DataResult(T data, boolean success) {
		super(success); //base sınıfın constructorlarını çalışıp değerleri set ediyor
		this.data=data;
	}
	public T getData(){
		return this.data;
	}
	
}
