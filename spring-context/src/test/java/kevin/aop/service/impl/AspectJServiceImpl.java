package kevin.aop.service.impl;

import kevin.aop.service.AspectJService;

/*******************************************************************************
 * @author wj
 * @date 2018-12-24
 * @description AspectJService 接口实现 
 ******************************************************************************/
public class AspectJServiceImpl implements AspectJService{
    @Override
    public void beforeAdvice() {
        System.out.println("测试前置通知，我是第一个Service。。。。。。");
    }

    @Override
    public void afterAdvice() {
        System.out.println("测试AspectJ后置通知。。。。");
    }
}
