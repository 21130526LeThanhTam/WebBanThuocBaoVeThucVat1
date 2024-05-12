package log;

import dao.LogDao;

public abstract class AbstractDao<T extends IModel> implements IDao<T> {
    @Override
    public boolean selectModel(int id) {
        LogDao.getInstance().selectModel();
        return false;
    }
    @Override
    public boolean insertModel(T model) {
        LogDao.getInstance().insertModel(model);
        return true;
    }
    @Override
    public boolean deleteModel(T model) {
        LogDao.getInstance().deleteModel(model);
        return true;
    }
    @Override
    public boolean updateModel(T model) {
        LogDao.getInstance().updateModel(model);
        return true;
    }
}
