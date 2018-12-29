package kevin.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/*******************************************************************************
 * 1.{@link org.aspectj.lang.annotation.Aspect} 切面泛指[*交叉业务逻辑*]。事务处理和日志处理可以理解为切面。
 *  常用的切面有通知(Advice)与顾问(Advisor)。实际就是对主业务逻辑的一种增强。
 * 2.织入（Weaving）
 *  织入是指将切面代码插入到目标对象的过程。代理的invoke方法完成的工作，可以称为织入。
 * 3.连接点(JoinPoint)
 *  连接点是指可以被切面织入的方法。通常业务接口的方法均为连接点
 * 4.切入点(PointCut)
 *  切入点指切面具体织入的方法
 *  注意：被标记为final的方法是不能作为连接点与切入点的。因为最终的是不能被修改的，不能被增强的。
 * 5.目标对象（Target）
 *  目标对象指将要被增强的对象。即包含主业务逻辑的类的对象。
 * 6.通知（Advice）
 *  通知是切面的一种实现，可以完成简单的织入功能。通知定义了增强代码切入到目标代码的时间点，
 *  是目标方法执行之前执行，还是执行之后执行等。切入点定义切入的位置，通知定义切入的时间。
 * 7.顾问(Advisor)
 * 顾问是切面的另一种实现，能够将通知以更为复杂的方式织入到目标对象中，是将通知包装为更复杂切面的装配器。
 *
 * AOP是一种思想，而非实现
 * AOP是基于OOP，而又远远高于OOP，主要是将主要核心业务和交叉业务分离，交叉业务就是切面。例如，记录日志和开启事务。
 *
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
