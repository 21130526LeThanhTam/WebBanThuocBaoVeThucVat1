package log;


public class Logging {
    public static void update(IModel model){
        System.out.println("{'action':'update','model':"+model.getTable()+",'before':"+model.beforeData()+","+"{after"+model.afterData()+"}");
    }

    public static <T extends IModel> void insert(T model) {
        System.out.println("{'action':'update','model':"+model.getTable()+",'before':"+model.beforeData()+","+"{after"+model.afterData()+"}");
    }
}
