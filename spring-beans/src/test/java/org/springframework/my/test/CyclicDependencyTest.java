package org.springframework.my.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.my.beans.Car;
import org.springframework.my.beans.CyclicDependencyA;
import org.springframework.my.beans.CyclicDependencyB;
import org.springframework.my.beans.CyclicDependencyC;

/*******************************************************************************
 * @author wj
 * @date 2018-12-05
 * @description 循环依赖测试
 ******************************************************************************/
public class CyclicDependencyTest {


    private final DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

    @Before
    public void loadBeans() {
        Resource resource = new ClassPathResource("cyclic_dependency.xml", getClass());
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(this.beanFactory);
        xmlBeanDefinitionReader.loadBeanDefinitions(resource);
    }


    @Test
    public void cyclicDependencyTest() throws Exception {
        CyclicDependencyA a = (CyclicDependencyA) beanFactory.getBean("cyclicDependencyA");
        System.out.println(a.toString());
        CyclicDependencyB b = (CyclicDependencyB) beanFactory.getBean("cyclicDependencyB");
        System.out.println(b.toString());
        CyclicDependencyC c  = (CyclicDependencyC) beanFactory.getBean("cyclicDependencyC");
        System.out.println(c.toString());


        Car car  = (Car) beanFactory.getBean("&car");
        System.out.println(car.toString());
    }

}
