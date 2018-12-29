package kevin.aop.service;

/*******************************************************************************
 * @author wj
 * @date 2018-12-24
 * @description AOP接口定义
 ******************************************************************************/
public interface AspectJService {
    /**
     * 测试前置通知
     */
    void beforeAdvice();

    /**
     * 测试后置通知
     */
    void afterAdvice();
}
