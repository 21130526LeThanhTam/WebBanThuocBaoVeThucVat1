package log;

import dao.LogDao;

public abstract class AbstractDao<T extends IModel> implements IDao {
    @Override
    public boolean selectModel(int id) {
        LogDao.getInstance().selectModel();
        return false;
    }

    @Override
    public boolean insertModel(AbsModel model,String ip, int level, String address) {
        LogDao.getInstance().insertModel(model,ip,level,address);
        return true;
    }
    @Override
    public boolean deleteModel(AbsModel model,String ip, int level, String address) {
        LogDao.getInstance().deleteModel(model,ip,level,address);
        return true;
    }
    @Override
    public boolean updateModel(AbsModel model,String ip, int level, String address) {
        LogDao.getInstance().updateModel(model,ip,level,address);
        return true;
    }

    @Override
    public boolean login(AbsModel model, String action, String ip, int level, String address) {
        LogDao.getInstance().login(model,action,ip,level,address);
        return true;
    }
}
