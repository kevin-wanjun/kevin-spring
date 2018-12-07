package org.springframework.my.beans;

import org.springframework.beans.factory.annotation.Autowired;

/*******************************************************************************
 * @author wj
 * @date 2018-12-05
 * @description 循环依赖 bean C 
 ******************************************************************************/
public class CyclicDependencyC {

    private String name = "C";

    @Autowired
    private CyclicDependencyA cyclicDependencyA;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CyclicDependencyA getCyclicDependencyA() {
        return cyclicDependencyA;
    }

    public void setCyclicDependencyA(CyclicDependencyA cyclicDependencyA) {
        this.cyclicDependencyA = cyclicDependencyA;
    }

    @Override
    public String toString() {
        boolean flag = cyclicDependencyA == null;
        return "CyclicDependencyC{" +
                "name='" + name + '\'' +
                ", cyclicDependencyA=" + flag +
                '}';
    }
}
