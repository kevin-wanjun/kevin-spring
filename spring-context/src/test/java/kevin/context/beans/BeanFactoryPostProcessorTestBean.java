package kevin.context.beans;

/*******************************************************************************
 * @author wj
 * @date 2018-12-18
 * @description beanFactory后置处理器(BeanFactoryPostProcessor)Bean测试
 ******************************************************************************/
public class BeanFactoryPostProcessorTestBean {
    /**姓名*/
    private String name;
    /**年龄*/
    private String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "BeanFactoryPostProcessorTestBean{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
