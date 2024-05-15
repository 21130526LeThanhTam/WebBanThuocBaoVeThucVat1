package log;

public interface IDao<T extends IModel> {
    boolean selectModel(int id);
    boolean insertModel(T model,String ip, int level, String address);
    boolean deleteModel(T model,String ip, int level, String address);
    boolean updateModel(T model,String ip, int level, String address);
    boolean login(T model,String action,String ip, int level, String address);
}
