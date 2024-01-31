import org.example.ApplicationConfiguration;
import org.example.dao.UserDao;
import org.example.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class StringApplicationTest {


    @Autowired
    UserDao userDao;

    @Test
    public void testDataSource(@Autowired DataSource dataSource) {
        Assertions.assertNotNull(dataSource);
    }


    @Test
    public void testUserDao(@Autowired User user) {
        userDao.save(user);

        var user2 = userDao.findByEmail(user.getEmail());

        Assertions.assertNotNull(user2);
        Assertions.assertEquals(user.getName(), user2.getName());
        Assertions.assertEquals(user.getEmail(), user2.getEmail());
    }


}
