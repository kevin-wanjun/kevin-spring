package kevin.context.test;

import kevin.context.beans.ParentBean;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*******************************************************************************
 * @author wj
 * @date 2018-12-11
 * @description ApplicationContext 测试 
 ******************************************************************************/
public class ApplicationContextTest {

    private ApplicationContext applicationContext;


    @Before
    public void loadBeans() {
        applicationContext = new ClassPathXmlApplicationContext("cyclic_dependency.xml",getClass());

        applicationContext = new ClassPathXmlApplicationContext("cyclic_dependency.xml");
    }


    @Test
    public void cyclicDependencyTest() throws Exception {
        ParentBean parentBean = (ParentBean) applicationContext.getBean("parentBean");
        System.out.println(parentBean);
    }

}
