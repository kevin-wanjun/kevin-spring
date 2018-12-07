package org.springframework.my.beans;

import org.springframework.beans.factory.annotation.Autowired;

/*******************************************************************************
 * @author wj
 * @date 2018-12-05
 * @description 循环依赖 bean B 
 ******************************************************************************/
public class CyclicDependencyB {

    private String name = "B";

    private CyclicDependencyC cyclicDependencyC;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CyclicDependencyC getCyclicDependencyC() {
        return cyclicDependencyC;
    }

    public void setCyclicDependencyC(CyclicDependencyC cyclicDependencyC) {
        this.cyclicDependencyC = cyclicDependencyC;
    }

    @Override
    public String toString() {
        boolean flag = cyclicDependencyC == null;
        return "CyclicDependencyB{" +
                "name='" + name + '\'' +
                ", cyclicDependencyC=" + flag +
                '}';
    }
}
