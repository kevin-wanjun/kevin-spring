package kevin.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/*******************************************************************************
 * @author wj
 * @date 2018-12-20
 * @description aop 小demo 
 ******************************************************************************/
@Aspect
@Component
public class AspectDemo {

    @Pointcut("execution(public * kevin.aop.service.impl..*(..))")
    public void pointcut(){
    }

    //前置增强
    @Before(value = "pointcut()")
    public void asBefore(JoinPoint joinPoint){
        System.out.println("这是前置增强");
    }
    //后置增强
    @AfterReturning(value = "pointcut()")
    public void asAfterReturning(){
        System.out.println("这是后置增强");
    }

    //环绕增强
    @Around(value = "pointcut()")
    public void asAround(ProceedingJoinPoint pj){
        System.out.println("这是环绕前置增强");
        try {
            pj.proceed();
        } catch (Throwable e) {
            //抓捕异常
            e.printStackTrace();
        }
        System.out.println("这是环绕后置增强");
    }

    //异常增强
    @AfterThrowing(value = "pointcut()")
    public void asThorws(){
        System.out.println("这是异常增强");
    }

    //最终增强
    @After(value = "pointcut()")
    public void asAfter(){
        System.out.println("这是最终增强");
    }

}
