package dao;

import Service.UserService;
import bean.User;
import junit.framework.TestCase;


public class UserDAOTest extends TestCase {

    public void testInsert() {
    }

    public void testUpdate() {
        UserDAO userDAO=new UserDAO();
        User a =UserService.getInstance().selectUser(2);
        User user=new User();
        userDAO.update(a);
    }

    public void testDelete() {
    }
}