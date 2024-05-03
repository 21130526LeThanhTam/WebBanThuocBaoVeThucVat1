package dao;


import log.IDao;
import log.IModel;

import java.sql.SQLOutput;


public class LogDao {
    private static LogDao instance;
    public static LogDao getInstance(){
        if (instance == null) instance = new LogDao();
        return instance;
    }

    public <T extends IModel> void selectModel() {

    }

    public <T extends IModel> void insertModel(T model) {
        System.out.println("{'Action:insert'+'Model:"+model.getTable()+"'+'Được thêm:"+model.afterData());
    }

    public <T extends IModel> void deleteModel(T model) {
    }

    public <T extends IModel> void updateModel(T model) {
        String action ="Update"+model.getTable();
    }

    public static void main(String[] args) {

    }
}
