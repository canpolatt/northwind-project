package kodlamaio.northwind.api.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.northwind.business.abstracts.UserService;
import kodlamaio.northwind.core.entities.User;
import kodlamaio.northwind.core.utilities.results.ErrorDataResult;

@RestController
@RequestMapping(value="/api/users")
public class UsersController {
	
	private UserService userService;

	@Autowired
	public UsersController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@PostMapping(value="/add")
	public ResponseEntity<?>  add(@Valid @RequestBody User user) { //Errorde dönebilir başka bişeyde-- user'i validationdan geçir
		return ResponseEntity.ok(this.userService.add(user));
	}
	
	//AOP -> Bütün methodların önüne global exception handler yazıyoruz bütün methodlar çalışırken buradan geçer
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class) // Bu türden bi hata olursa
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exceptions){
		Map<String,String> validationErrors=new HashMap<String, String>(); //HashMap bir Maptir -- Dictionary
		for(FieldError fieldError: exceptions.getBindingResult().getFieldErrors()) { //Alanlar için tüm hataları oku, List of FieldError dönüyor
			validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		ErrorDataResult<Object> errors = new ErrorDataResult<Object>(validationErrors,"Dogrulama hatalari"); // Hataları içine koy ve döndür
		return errors; 
	}
	
}
//200 GET
//300
//400
//500