package kevin.context.beans;

import org.springframework.stereotype.Component;

/*******************************************************************************
 * @author wj
 * @date 2018-12-06
 * @description 小孩 Bean 
 ******************************************************************************/
@Component
public class ChildBean{

    private String name = "child";

    @Override
    public String toString() {
        return "ChildBean{" +
                "name='" + name + '\'' +
                '}';
    }
}
