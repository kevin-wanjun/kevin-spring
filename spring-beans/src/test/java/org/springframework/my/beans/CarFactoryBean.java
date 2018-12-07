package org.springframework.my.beans;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.lang.Nullable;

/*******************************************************************************
 * @author wj
 * @date 2018-12-05
 * @description FactoryBean 测试FactoryBean 
 ******************************************************************************/
public class CarFactoryBean implements FactoryBean<Car> {


    private  String carInfo ;

    @Nullable
    @Override
    public Car getObject ()   throws  Exception  {
        Car car =  new  Car () ;
        String[] infos =  carInfo.split ( "," ) ;
        car.setBrand ( infos [ 0 ]) ;
        car.setMaxSpeed ( Integer. valueOf ( infos [ 1 ])) ;
        car.setPrice ( Double. valueOf ( infos [ 2 ])) ;
        return  car;
    }

    @Nullable
    @Override
    public  Class<Car> getObjectType ()   {
        return  Car.class ;
    }

    @Override
    public boolean isSingleton ()   {
        return true ;
    }


    public String getCarInfo() {
        return carInfo;
    }

    public void setCarInfo(String carInfo) {
        this.carInfo = carInfo;
    }
}
