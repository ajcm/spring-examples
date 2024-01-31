import org.example.ApplicationConfiguration;
import org.example.dao.RoleRepository;
import org.example.dao.UserRepository;
import org.example.model.Role;
import org.example.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class StringApplicationTest {


    @Test
    public void test(@Autowired UserRepository userRepository,
                     @Autowired RoleRepository roleRepository, @Autowired User user) {

        userRepository.save(user);

        var user2 = userRepository.findByEmail(user.getEmail());

        Assertions.assertNotNull(user2);
        Assertions.assertEquals(user.getName(), user2.getName());
        Assertions.assertEquals(user.getEmail(), user2.getEmail());

        Role role = new Role();
        role.setDescription("admin");

        Role role2 = new Role();
        role2.setDescription("user");

        role = roleRepository.save(role);
        role2 = roleRepository.save(role2);

        user2.getRoles().add(role);
        user2.getRoles().add(role2);

        userRepository.save(user2);

        var user3 = userRepository.findByEmail(user.getEmail());

        Assertions.assertEquals(user3.getRoles().size(), 2);
    }


}
