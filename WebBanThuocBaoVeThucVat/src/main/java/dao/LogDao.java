package dao;


import bean.User;
import db.JDBIConnector;
import log.IDao;
import log.IModel;

import java.sql.SQLOutput;
import java.time.LocalDateTime;


public class LogDao {
    private static LogDao instance;
    public static LogDao getInstance(){
        if (instance == null) instance = new LogDao();
        return instance;
    }

    public <T extends IModel> void selectModel() {

    }

    public <T extends IModel> void insertModel(T model,String ip, int level, String address) {
        LocalDateTime date= LocalDateTime.now();
        JDBIConnector.getJdbi().withHandle(handle ->
                handle.createUpdate("INSERT INTO log(ip,action,level,address,previousValue,currentValue,create_at,update_at) VALUES (?,?,?,?,?,?,?,?)")
                        .bind(0,ip)
                        .bind(1,"insert"+model.getTable())
                        .bind(2,level)
                        .bind(3,address)
                        .bind(4,"")
                        .bind(5,model.afterData())
                        .bind(6,date)
                        .bind(7,date)
                        .execute()
        );
    }

    public <T extends IModel> void deleteModel(T model,String ip, int level, String address) {

    }

    public <T extends IModel> void updateModel(T model,String ip, int level, String address) {
        String action ="Update"+model.getTable();
    }
    public <T extends IModel> void login(T model, String action, String ip, int level, String address) {
        LocalDateTime date= LocalDateTime.now();
        JDBIConnector.getJdbi().withHandle(handle ->
                handle.createUpdate("INSERT INTO log(ip,action,level,address,previousValue,currentValue,create_at,update_at) VALUES (?,?,?,?,?,?,?,?)")
                        .bind(0,ip)
                        .bind(1,action)
                        .bind(2,level)
                        .bind(3,address)
                        .bind(4,"")
                        .bind(5,model.afterData())
                        .bind(6,date)
                        .bind(7,date)
                        .execute()
        );
    }
    public static void main(String[] args) {
        User b =new User(1,1,"Son","4297f44b13955235245b2497399d7a93","0123456789","Son@gmail.com","Son","dsf");
        LogDao.getInstance().insertModel(b,"",1,"");
    }
}
