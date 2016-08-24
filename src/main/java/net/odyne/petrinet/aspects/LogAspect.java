package net.odyne.petrinet.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class LogAspect {

	@Around("within(net.odyne.petrinet.auth.*)")
	public Object checkAccess(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("ooolog before " + joinPoint.getSignature().getName());
		Object result = joinPoint.proceed();
		System.out.println("ooolog after");
		return result;
	}
	
	@Around("within(net.odyne.petrinet.Application.*)")
	public Object checkAccess2(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("iiilog before " + joinPoint.getSignature().getName());
		Object result = joinPoint.proceed();
		System.out.println("iiilog after");
		return result;
	}
	
}
