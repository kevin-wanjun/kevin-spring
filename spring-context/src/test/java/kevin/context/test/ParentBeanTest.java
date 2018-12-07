package kevin.context.test;

import kevin.context.beans.ParentBean;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/*******************************************************************************
 * @author wj
 * @date 2018-12-05
 * @description 循环依赖测试
 ******************************************************************************/
public class ParentBeanTest {


    private  BeanFactory beanFactory;


    @Before
    public void loadBeans() {
        beanFactory = new ClassPathXmlApplicationContext("cyclic_dependency.xml",getClass());
    }


    @Test
    public void cyclicDependencyTest() throws Exception {
        ParentBean parentBean = (ParentBean) beanFactory.getBean("parentBean");

        System.out.println(parentBean);
    }

}
