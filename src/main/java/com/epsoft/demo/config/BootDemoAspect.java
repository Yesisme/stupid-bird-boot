package com.epsoft.demo.config;

import com.epsoft.demo.exception.ParameterNotFound;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class BootDemoAspect {

	private static final Logger logger = LoggerFactory.getLogger(BootDemoAspect.class);

	@Pointcut("execution(public * com.epsoft.demo.controller..*.*(..))")
	public void executeMethod() {
		//logger.info("\t 切包开始{}","=====================");
	}

	@Pointcut("target(com.epsoft.demo.service.user.UserService)")
	public void method(){
		logger.info("切包开始{}","=====================");
	}

	@Before(value = "executeMethod()")
	public void doBeforeAdvice(JoinPoint joinPoint) {	
		//logger.info("前置通知启动");
		//Object[] object = joinPoint.getArgs();
		//logger.info("1请求参数是{}:"+JSON.toJSONString(joinPoint.getArgs()));
		//logger.info("2========={}"+joinPoint.getClass());
		//Signature signature = joinPoint.getSignature();
		//logger.info("前置签名是{}"+signature);
		//logger.info("{}"+signature.getDeclaringType());
		//logger.info("{}"+signature.getDeclaringTypeName()+signature.getClass());
	    // 获取RequestAttributes
		//RequestAttributes requestAttributes= RequestContextHolder.getRequestAttributes();
		// 从requestAttributes中获取HttpServletRequest信息
		//HttpServletRequest request=(HttpServletRequest)requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
		// 获取session信息
		//HttpSession session=(HttpSession)requestAttributes.resolveReference(RequestAttributes.REFERENCE_SESSION);
		//System.out.println("请求 ： "+request+" ,  HttpSession : "+session);
		
	}

	//使用Object来接收返回值
	//joinpot必须写在前面
	@AfterReturning(value = "execution(public * com.epsoft.demo.controller..*.*(..))",returning = "keys")
	public void doAfterReturningAdvice1(JoinPoint joinPoint,Object keys){
	  //logger.info("第一个后置返回通知的返回值{}："+keys);
	} 

	//exception用来接收异常的
	@AfterThrowing(value="executeMethod()",throwing = "exception")
	public void doAfterThrowing(Exception exception) {
		//logger.info("{}","空指针异常");
	}
	  
	@After(value = "executeMethod()")
	public void doAfter(JoinPoint joinPoint) {
		//logger.info("{}","后置通知执行了");
		logger.info("方法名：{}",joinPoint.getSignature().getName());
	}
	
	//@Around(value = "executeMethod()")
	@Around(value = "method()")
	public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		logger.info("环绕通知的目标方法名"+proceedingJoinPoint.getSignature().getName());
		Object obj = null;
		try {//obj之前可以写目标方法执行前的逻辑 			 
			obj = proceedingJoinPoint.proceed();
		} catch(ParameterNotFound e){
			logger.info("\n异常{}",e.getMessage());
			return e;
		}catch(Exception e1) {
			logger.info("\n异常{}",e1.getMessage());
			return e1;
		}
		return obj;
	}
}