package net.odyne.petrinet.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import net.odyne.petrinet.entities.Customer;

//@Aspect
//@Component
public class AuthAspect {

	// @Around("execution(* net.odyne.repositories.PetrinetRepository.*(..))")
	@Around("within(net.odyne.petrinet.repositories.PetrinetRepository+)")
	public Object checkAccess(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("log before " + joinPoint.getSignature().getName());
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(auth.getPrincipal());
		System.out.println(auth.getCredentials());
		if (auth == null || auth.getPrincipal() == null || !(auth.getPrincipal() instanceof Customer))
			throw new SecurityException("ne, mag ich nicht");
		
		Customer user = (Customer) auth.getPrincipal();
        System.out.println(user.getEmail());
		
        
        
        Object result = joinPoint.proceed();
		System.out.println("log after");
		return result;
	}
}
