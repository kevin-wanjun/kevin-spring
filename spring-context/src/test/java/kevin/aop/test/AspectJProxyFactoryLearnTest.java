package kevin.aop.test;

import kevin.aop.aspect.AopAdviceConfig;
import kevin.aop.service.AspectJService;
import kevin.aop.service.impl.AspectJServiceImpl;
import org.junit.Test;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

/*******************************************************************************
 * @author wj
 * @date 2018-12-24
 * @description AspectJProxyFactory 测试 
 ******************************************************************************/
public class AspectJProxyFactoryLearnTest {

    @Test
    public void aspectJProxyFactoryLearnTest(){
        //手工创建一个实例
        AspectJService aspectJService = new AspectJServiceImpl();

        //使用AspectJ语法 自动创建代理对象
        AspectJProxyFactory aspectJProxyFactory = new AspectJProxyFactory(aspectJService);
        //添加切面和通知类
        aspectJProxyFactory.addAspect(AopAdviceConfig.class);
        //创建代理对象
        AspectJService proxyService = aspectJProxyFactory.getProxy();
        //进行方法调用
        proxyService.beforeAdvice();
    }


}
