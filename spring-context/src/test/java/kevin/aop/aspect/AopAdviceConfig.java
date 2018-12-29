package kevin.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/*******************************************************************************
 * @author wj
 * @date 2018-12-24
 * @description 一个 aop 切面配置 
 ******************************************************************************/
@Aspect
public class AopAdviceConfig {

    @Before("execution(public * kevin.aop.service.impl..*(..))")
    public void beforeAdvice(JoinPoint joinPoint) {
        System.out.println(joinPoint.getThis());
        System.out.println("我是前置通知....");
    }
}
