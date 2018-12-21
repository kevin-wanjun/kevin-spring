package kevin.aop.service.impl;

import kevin.aop.service.UserDao;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;

/*******************************************************************************
 * @author wj
 * @date 2018-12-20
 * @description 默认 UserDao 实现 
 ******************************************************************************/
@Service
public class UserDaoImpl implements UserDao{


    @Override
    public void add() {
        System.out.println("==ADD==");
        this.delete();

        //这里
        ((UserDao)AopContext.currentProxy()).delete();
    }

    @Override
    public void delete() {
        System.out.println("==DELETE==");
    }
}
