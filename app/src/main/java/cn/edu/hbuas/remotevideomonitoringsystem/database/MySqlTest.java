package cn.edu.hbuas.remotevideomonitoringsystem.database;

import android.app.Application;

import java.util.List;

import cn.edu.hbuas.remotevideomonitoringsystem.bean.Car;

public class MySqlTest {

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int j = 0;
                DBService service = DBService.getDbService();
                List<Car> list = service.getUserData();
                System.out.println("ListSize:" + list.size());
                for (Car c : list) {
                    String[] arr = new String[]{c.getPlate_number(), c.getName(), c.getMessage(), c.getCreateTime()};
                    for (int i = 0; i < 4; i++) {
                        System.out.print("\t" + arr[i]);
                    }
                    System.out.println();
                }
            }
        }).start();
    }
}
