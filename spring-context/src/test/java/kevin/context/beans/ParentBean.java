package kevin.context.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*******************************************************************************
 * @author wj
 * @date 2018-12-06
 * @description 父母Bean
 ******************************************************************************/
@Component
public class ParentBean {


    private String name = "parent";

    @Override
    public String toString() {
        return "ParentBean{" +
                ", name='" + name + '\'' +
                '}';
    }
}
