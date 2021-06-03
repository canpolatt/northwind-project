package kodlamaio.northwind.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import kodlamaio.northwind.business.abstracts.ProductService;
import kodlamaio.northwind.core.utilities.results.DataResult;
import kodlamaio.northwind.core.utilities.results.Result;
import kodlamaio.northwind.entities.concretes.Product;
import kodlamaio.northwind.entities.dtos.ProductWithCategoryDto;

@RestController
@RequestMapping("/api/products")
public class ProductsController { //Dış dünyayla sistemin iletişim kurduğu yer
	
	private ProductService productService;
	
	@Autowired //Projede arıyo newleyip buraya koyuyor ProductService'i implement edeni arıyo ProductManager'i buluyo newleyip aşağıdaki productService = ProductManager atıyo
	public ProductsController(ProductService productService) {
	super();
	this.productService = productService;
}
/*Mocking yapmamız gerekiyor sahte bir manager yapıp unit test için bağımlılığı kaldırıyoruz
Test için service katmanına gitmiyoruz bu sayfayı tek başına test yapıyoruz
Data - İşlem sonucu* - Mesaj*  bunları dönüp bilgi vermemiz lazım*/

	@GetMapping("/getall")
	public  DataResult<List<Product>> getAll(){
		return this.productService.getAll();
	}
	
	
	@GetMapping("/getProductWithCategoryDto")
	public  DataResult<List<ProductWithCategoryDto>> getProductWithCategoryDto(){
		return this.productService.getProductWithCategoryDetails();
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody Product product) { //Bir pakete koyup json objesine çevirip body olarak gönderiyor
		return this.productService.add(product);      // Gönderdiğimiz datayı Product'ın içindeki alanlara yerleştiriyor ve map ediyo
	}
	
	@GetMapping("/getByProductName")
	public DataResult<Product> getByProductName(@RequestParam String productName){ //Yapılan istekten parametre okuyor
		return this.productService.getByProductName(productName);
	}
	
	@GetMapping("/getByProductNameAndCategoryId")
	public DataResult<Product> getByProductNameAndCategoryId(@RequestParam("productName") String productName,@RequestParam("categoryId") int categoryId){
		return this.productService.getByProductNameAndCategoryId(productName, categoryId);
	}
	
	@GetMapping("/getByProductNameContains")
	public DataResult<List<Product>> getByProductNameContains(@RequestParam String productName){
		return this.productService.getByProductNameContains(productName);
	}
	
	@GetMapping("/getAllByPage")
	DataResult<List<Product>>getAll(int pageNo,int pageSize){
		return this.productService.getAll(pageNo, pageSize);
	}
	@GetMapping("/getAllDesc")
	public DataResult<List<Product>> getAllSorted() {
		return this.productService.getAllSorted();
	}
}
// kodlama.io/api/products
