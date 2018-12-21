package kevin.context.test;

import kevin.context.beans.BeanFactoryPostProcessorTestBean;
import kevin.context.beans.ParentBean;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*******************************************************************************
 * @author wj
 * @date 2018-12-18
 * @description BeanFactoryPostProcessorTest beanFactory后置处理器测试 
 ******************************************************************************/
public class BeanFactoryPostProcessorTest {

    private BeanFactory beanFactory;


    @Before
    public void loadBeans() {
        beanFactory = new ClassPathXmlApplicationContext("bean_factory_post_processor.xml",getClass());
    }

    @Test
    public void beanFactoryPostProcessorTest() throws Exception {
        BeanFactoryPostProcessorTestBean beanFactoryPostProcessor
                = (BeanFactoryPostProcessorTestBean) beanFactory.getBean("BeanFactoryPostProcessor");

        System.out.println(beanFactoryPostProcessor);
    }

}
