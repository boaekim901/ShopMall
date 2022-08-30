package com.ezen.biz.common;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class PointcutCommon {

	@Pointcut("execution(* com.ezen.biz..*Impl.get*(..))")
	public void getPointcut() {
		
	}
	@Pointcut("execution(* com.ezen.biz..*Impl.*(..))")
	public void allPointcut() {
		
	}
}
