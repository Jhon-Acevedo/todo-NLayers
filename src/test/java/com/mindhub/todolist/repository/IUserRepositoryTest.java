package com.mindhub.todolist.repository;

import com.mindhub.todolist.models.UserEntity;
import com.mindhub.todolist.repositories.IUserRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Fail.fail;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class IUserRepositoryTest {

    @Autowired
    private IUserRepository userRepository;

    @Test
    public void UserRepository_UpdateUser_ReturnUserNotNull(){
        UserEntity user = new UserEntity("username", "password", "email@gmail.com");
        userRepository.save(user);

        Optional<UserEntity> userEntitySave = userRepository.findById(user.getId());

        if(userEntitySave.isEmpty())
            fail("User not found");
        else{
            userEntitySave.get().setUsername("username2");
            userEntitySave.get().setEmail("emailChange@gmail.com");
            UserEntity updatedUser = userRepository.save(userEntitySave.get());

            Assertions.assertThat(updatedUser).isNotNull();
            Assertions.assertThat(updatedUser.getUsername()).isEqualTo("username2");
        }
    }

    @Test
    public void UserRepository_DeleteUser_ReturnUserIsEmpty() {
        UserEntity user = new UserEntity("username", "password", "email@gmail.com");

        userRepository.save(user);

        userRepository.deleteById(user.getId());
        Optional<UserEntity> userEntitySave = userRepository.findById(user.getId());

        Assertions.assertThat(userEntitySave).isEmpty();
    }

}
