package log;

public interface IDao<T extends IModel> {
    boolean selectModel(int id);
    boolean insertModel(T model);
    boolean deleteModel(T model);
    boolean updateModel(T model);
}
