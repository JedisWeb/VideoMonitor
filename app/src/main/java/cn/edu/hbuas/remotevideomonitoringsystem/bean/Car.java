package cn.edu.hbuas.remotevideomonitoringsystem.bean;

import java.util.Date;

public class Car {

    private String plate_number;
    private String name;
    private String message;
    private String createTime;

    public Car() {
    }

    public Car(String plate_number, String name, String message, String createTime) {
        this.plate_number = plate_number;
        this.name = name;
        this.message = message;
        this.createTime = createTime;
    }

    public String getPlate_number() {
        return plate_number;
    }

    public void setPlate_number(String plate_number) {
        this.plate_number = plate_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Car{" +
                "plate_number='" + plate_number + '\'' +
                ", name='" + name + '\'' +
                ", message='" + message + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
