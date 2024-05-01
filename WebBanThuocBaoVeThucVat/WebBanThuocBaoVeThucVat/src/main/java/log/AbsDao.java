package log;

public abstract class AbsDao <T extends IModel> implements IDao<T>{
    @Override
    public int insert(T model) {
        Logging.insert(model);
        return 1;
    }

    @Override
    public int update(T model) {
        Logging.update(model);
        return 1;
    }

    @Override
    public int delete(T model) {
        Logging.update(model);
        return 1;
    }
}
