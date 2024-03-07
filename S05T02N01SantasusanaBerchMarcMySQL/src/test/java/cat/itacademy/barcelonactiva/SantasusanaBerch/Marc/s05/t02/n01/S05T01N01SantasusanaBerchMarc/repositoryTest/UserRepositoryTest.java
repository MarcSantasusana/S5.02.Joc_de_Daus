package cat.itacademy.barcelonactiva.SantasusanaBerch.Marc.s05.t02.n01.S05T01N01SantasusanaBerchMarc.repositoryTest;


import cat.itacademy.barcelonactiva.SantasusanaBerch.Marc.s05.t02.n01.S05T01N01SantasusanaBerchMarc.model.domain.User;
import cat.itacademy.barcelonactiva.SantasusanaBerch.Marc.s05.t02.n01.S05T01N01SantasusanaBerchMarc.model.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;


@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserRepositoryTest {


    @Autowired
    private UserRepository userRepository;

    @DisplayName("UserRepositoryUnitTest - Test return user list")
    @Test
    public void findAll_should_return_user_list() {

        User user = User.builder().id(1).build();
        User user2 = User.builder().id(2).build();

        userRepository.save(user);
        userRepository.save(user2);

        List<User> userList = userRepository.findAll();

        Assertions.assertThat(userList).isNotNull();
        Assertions.assertThat(userList.size()).isEqualTo(2);
    }


    @DisplayName("UserRepositoryUnitTest - Test return user by id")
    @Test
    public void findById_should_return_User() {

        User user = User.builder().id(1).build();

        userRepository.save(user);

        User savedUser = userRepository.findById(1).get();

        Assertions.assertThat(savedUser).isNotNull();
    }

    @DisplayName("UserRepositoryUnitTest - Test return player by email")
    @Test
    public void findByEmail_should_return_user() {

        User user = User.builder().id(1).email("marc@gmail.com").build();

        userRepository.save(user);

        User savedUser = userRepository.findByEmail("marc@gmail.com").get();

        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getEmail()).isEqualTo("marc@gmail.com");
    }

    @DisplayName("UserRepositoryUnitTest - Test for insert new user")
    @Test
    public void save_should_insert_new_user() {

        User user = User.builder().id(1).email("marc@gmail.com").build();

        User savedUser = userRepository.save(user);

        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @DisplayName("UserRepositoryUnitTest - Test for update user")
    @Test
    public void save_should_update_existing_user() {

        User user = User.builder().id(1).email("marc@gmail.com").firstName("Pere").build();

        userRepository.save(user);

        User savedUser = userRepository.findById(1).get();

        savedUser.setFirstName("Marc");

       User updatedUser = userRepository.save(savedUser);

        Assertions.assertThat(updatedUser.getFirstName()).isEqualTo("Marc");

    }


}
