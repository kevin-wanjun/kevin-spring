package org.springframework.my.test;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.my.beans.Car;
import org.springframework.my.beans.CarFactoryBean;

/*******************************************************************************
 * @author wj
 * @date 2018-12-05
 * @description FactoryBean 测试 
 ******************************************************************************/
public class FactoryBeanTest {

    private final DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

    @Before
    public void loadBeans() {
        Resource resource = new ClassPathResource("factory_bean.xml", getClass());
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(this.beanFactory);
        xmlBeanDefinitionReader.loadBeanDefinitions(resource);
    }

    @Test
    public void testFactoryBean() throws Exception {
        FactoryBean carFactoryBean = (FactoryBean) beanFactory.getBean("&car");



        Car car = (Car) beanFactory.getBean("car");



        System.out.println(car.toString());
    }

}



