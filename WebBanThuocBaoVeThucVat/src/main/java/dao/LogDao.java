package dao;


import bean.Products;
import bean.User;
import db.JDBIConnector;
import log.AbsModel;
import log.IDao;
import log.IModel;

import java.io.File;
import java.sql.SQLOutput;
import java.time.LocalDateTime;


public class LogDao implements IDao{
    LocalDateTime date= LocalDateTime.now();
    private static LogDao instance;
    public static LogDao getInstance(){
        if (instance == null) instance = new LogDao();
        return instance;
    }

    public <T extends IModel> void selectModel() {}
//
//    public <T extends IModel> void insertModel(T model,String ip, int level, String address) {
//
//    }
//
//    public <T extends IModel> void deleteModel(T model,String ip, int level, String address) {
//
//    }
//
//    public <T extends IModel> void updateModel(T model,String ip, int level, String address) {
//        String action ="Update"+model.getTable();
//    }
//    public <T extends IModel> void login(T model, String action, String ip, int level, String address) {
//
//    }
    public boolean printLog(String action, AbsModel model, String ip, int level, String address) {
        Integer i = JDBIConnector.getJdbi().withHandle(handle ->
                handle.createUpdate("INSERT INTO log(ip,action,level,address,previousValue,currentValue,create_at,update_at) VALUES (?,?,?,?,?,?,?,?)")
                        .bind(0, ip)
                        .bind(1, action)
                        .bind(2, level)
                        .bind(3, address)
                        .bind(4, "")
                        .bind(5, model.afterData())
                        .bind(6, date)
                        .bind(7, date)
                        .execute()
        );
        return i == 1;
    }

    @Override
    public boolean selectModel(int id) {
        return false;
    }

    @Override
    public boolean insertModel(AbsModel model, String ip, int level, String address) {
        String action ="Insert "+model.getTable();
        return printLog(action,model,ip,level,address);
    }

    @Override
    public boolean deleteModel(AbsModel model, String ip, int level, String address) {
        String action ="Delete "+model.getTable();
        return printLog(action,model,ip,level,address);
    }

    @Override
    public boolean updateModel(AbsModel modelBf,AbsModel modelAt, String ip, int level, String address) {
        Integer i = JDBIConnector.getJdbi().withHandle(handle ->
                handle.createUpdate("INSERT INTO log(ip,action,level,address,previousValue,currentValue,create_at,update_at) VALUES (?,?,?,?,?,?,?,?)")
                        .bind(0, ip)
                        .bind(1, "Update"+modelBf.getTable())
                        .bind(2, level)
                        .bind(3, address)
                        .bind(4, modelBf.beforeData())
                        .bind(5, modelAt.afterData())
                        .bind(6, date)
                        .bind(7, date)
                        .execute()
        );
        return i == 1;
    }

    @Override
    public boolean login(AbsModel model, String action, String ip, int level, String address) {
        return printLog(action,model,ip,level,address);
    }

    @Override
    public boolean signUp(String email, String action, String ip, int level, String address) {
        Integer i = JDBIConnector.getJdbi().withHandle(handle ->
                handle.createUpdate("INSERT INTO log(ip,action,level,address,previousValue,currentValue,create_at,update_at) VALUES (?,?,?,?,?,?,?,?)")
                        .bind(0, ip)
                        .bind(1, action)
                        .bind(2, level)
                        .bind(3, address)
                        .bind(4, "")
                        .bind(5, email)
                        .bind(6, date)
                        .bind(7, date)
                        .execute()
        );
        return i == 1;
    }



    public static void main(String[] args) {
        String email= "hiho@gmail.com";
        String pass= "4297f44b13955235245b2497399d7a93";
        User a = new User();
        a.setEmail(email);
        a.setPassword(pass);
        User b =new User(1,1,"Son","4297f44b13955235245b2497399d7a93","0123456789","Son@gmail.com","Son","dsf");
//        LogDao.getInstance().insertModel(a,"",1,"");

        //String name, String image, int price, int category, int status, int inventory_quantity,String desc
//        Products aa = new Products("RỪ BỆNH ENDICO 5SC","dsfsd",3423,1,1,60,"dsfsd");
//        LogDao.getInstance().insertModel(aa,"",1,"");

    }

}
