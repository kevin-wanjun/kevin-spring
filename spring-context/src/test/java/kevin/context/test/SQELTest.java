package kevin.context.test;

import kevin.context.beans.StudentBean;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*******************************************************************************
 * @author wj
 * @date 2018-12-12
 * @description SQEL 测试 
 ******************************************************************************/
public class SQELTest {

    private BeanFactory beanFactory;


    @Before
    public void loadBeans() {
        beanFactory = new ClassPathXmlApplicationContext("sqel.xml",getClass());
    }


    @Test
    public void cyclicDependencyTest() throws Exception {
        StudentBean zhangsan = (StudentBean) beanFactory.getBean("zhangsan");
        System.out.println(zhangsan);

        StudentBean lisi = (StudentBean) beanFactory.getBean("lisi");
        System.out.println(lisi);
    }
}
