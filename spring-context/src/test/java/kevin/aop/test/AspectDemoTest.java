package kevin.aop.test;

import kevin.aop.service.UserDao;
import kevin.context.beans.SubjectBean;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*******************************************************************************
 * @author wj
 * @date 2018-12-20
 * @description AspectDemo 测试 
 ******************************************************************************/
public class AspectDemoTest {

    ApplicationContext ctx ;

    @Before
    public void init(){
        ctx = new ClassPathXmlApplicationContext("aspect_demo_test.xml",getClass());
    }

    @Test
    public void aspectDemoTest(){
        UserDao userDao = (UserDao) ctx.getBean("userDaoImpl");
        System.out.println("-------------");
        userDao.add();
        System.out.println("-------------");
        userDao.delete();

        System.out.println("##########################################");
        SubjectBean subjectBean = (SubjectBean) ctx.getBean("chemistry");
        System.out.println(subjectBean.getSubjectName());
    }
}
