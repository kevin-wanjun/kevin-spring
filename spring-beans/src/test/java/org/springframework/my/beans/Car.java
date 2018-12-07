package org.springframework.my.beans;

/*******************************************************************************
 * @author wj
 * @date 2018-12-05
 * @description 汽车 bean 
 ******************************************************************************/
public class Car {
    /**最大速度*/
    private int maxSpeed ;
    /**品牌*/
    private String brand ;
    /**价格*/
    private double price ;

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "maxSpeed=" + maxSpeed +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                '}';
    }
}
