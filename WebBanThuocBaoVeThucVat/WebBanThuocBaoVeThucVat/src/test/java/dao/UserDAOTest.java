package dao;

import bean.User;
import junit.framework.TestCase;

public class UserDAOTest extends TestCase {

    public void testSelectModel() {
        UserDAO userDAO= new UserDAO();
        User user= new User();
        userDAO.insertModel(user);
    }

    public void testInsertModel() {
    }

    public void testDeleteModel() {
    }

    public void testUpdateModel() {
        UserDAO userDAO= new UserDAO();
        User a= new User();
//        userDAO.updateModel(a);
    }
}