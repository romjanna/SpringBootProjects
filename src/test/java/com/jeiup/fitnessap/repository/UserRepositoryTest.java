package com.jeiup.fitnessap.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import com.jeiup.fitnessap.model.User;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    void whenFindByUserName_thenReturnUser() {
        // given
        User user = new User();
        user.setUserName("testUser");
        user.setPassword("Test@1234");
        user.setRoles("ROLE_USER");
        entityManager.persist(user);
        entityManager.flush();

        // when
        User found = userRepository.findByUserName("testUser").orElse(null);

        // then
        assertThat(found).isNotNull();
        assertThat(found.getUserName()).isEqualTo(user.getUserName());
    }
} 