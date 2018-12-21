package kevin.context.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/*******************************************************************************
 * @author wj
 * @date 2018-12-06
 * @description 小孩 Bean 
 ******************************************************************************/
@Component
public class ChildBean{

    private String name = "child";

    @Autowired
    private ParentBean parentBean;

    @Override
    public String toString() {
        return "ChildBean{" +
                "name='" + name + '\'' +
                ", parentBean=" + parentBean +
                '}';
    }
}
