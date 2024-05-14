package log;

import dao.LogDao;

public abstract class AbstractDao<T extends IModel> implements IDao<T> {
    @Override
    public boolean selectModel(int id) {
        LogDao.getInstance().selectModel();
        return false;
    }
    @Override
    public boolean insertModel(T model,String ip, int level, String address) {
        LogDao.getInstance().insertModel(model,ip,level,address);
        return true;
    }
    @Override
    public boolean deleteModel(T model,String ip, int level, String address) {
        LogDao.getInstance().deleteModel(model,ip,level,address);
        return true;
    }
    @Override
    public boolean updateModel(T model,String ip, int level, String address) {
        LogDao.getInstance().updateModel(model,ip,level,address);
        return true;
    }

    @Override
    public boolean login(T model, String action, String ip, int level, String address) {
        LogDao.getInstance().login(model,action,ip,level,address);
        return true;
    }
}
