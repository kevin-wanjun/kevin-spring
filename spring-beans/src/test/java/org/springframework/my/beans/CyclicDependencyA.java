package org.springframework.my.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*******************************************************************************
 * @author wj
 * @date 2018-12-05
 * @description 循环依赖bean A 
 ******************************************************************************/
public class CyclicDependencyA {


    private String name = "A";

    private CyclicDependencyB cyclicDependencyB;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CyclicDependencyB getCyclicDependencyB() {
        return cyclicDependencyB;
    }

    public void setCyclicDependencyB(CyclicDependencyB cyclicDependencyB) {
        this.cyclicDependencyB = cyclicDependencyB;
    }

    @Override
    public String toString() {
        boolean flag = cyclicDependencyB == null;
        return "CyclicDependencyA{" +
                "name='" + name + '\'' +
                ", cyclicDependencyB=" + flag +
                '}';
    }
}
