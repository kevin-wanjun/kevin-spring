package kevin.context.test;

import kevin.context.beans.NotBean;
import kevin.context.beans.ParentBean;
import kevin.context.beans.SubjectBean;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


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


        /**
         * @see ClassLoader#loadClass(String, boolean)
         * 此类位于<context:component-scan base-package="kevin.context.beans"> 包扫描路径下
         * 但是这里实例化对象的时候才会引发 ClassLoader,是因为Spring 采用ASM直接 对class文件进行读取，不会涉及到ClassLoader
         */
        NotBean notBean = new NotBean();
        //Spring 容器已经实例化了该类的class对象，所以这里不再会涉及 ClassLoader
        ParentBean p = new ParentBean();
    }

}
