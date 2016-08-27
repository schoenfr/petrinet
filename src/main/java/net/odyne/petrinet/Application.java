package net.odyne.petrinet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@EnableAspectJAutoProxy
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	

	@PostAuthorize("isAuthenticated()") // returnObject.owner == authentication.name
//	@PostFilter("hasPermission(filterObject, 'read') or hasPermission(filterObject, 'admin')")
	@RequestMapping("/yolo")
	public String findOne() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(authentication.getName());
		System.out.println(authentication.getPrincipal());
		return String.valueOf(authentication.getPrincipal());
	}


}
