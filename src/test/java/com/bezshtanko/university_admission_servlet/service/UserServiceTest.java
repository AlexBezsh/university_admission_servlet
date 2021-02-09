package com.bezshtanko.university_admission_servlet.service;

import com.bezshtanko.university_admission_servlet.TestData;
import com.bezshtanko.university_admission_servlet.dao.interfaces.DaoFactory;
import com.bezshtanko.university_admission_servlet.dao.interfaces.EnrollmentDao;
import com.bezshtanko.university_admission_servlet.dao.interfaces.UserDao;
import com.bezshtanko.university_admission_servlet.dto.UserDTO;
import com.bezshtanko.university_admission_servlet.exception.AuthenticationException;
import com.bezshtanko.university_admission_servlet.exception.UserNotExistException;
import com.bezshtanko.university_admission_servlet.model.user.User;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

public class UserServiceTest {

    private static final UserDao USER_DAO = mock(UserDao.class);
    private static final EnrollmentDao ENROLLMENT_DAO = mock(EnrollmentDao.class);

    private static UserService testedInstance;
    private User testUser;

    @BeforeClass
    public static void setUp() {
        DaoFactory daoFactory = mock(DaoFactory.class);
        when(daoFactory.createUserDao()).thenReturn(USER_DAO);
        when(daoFactory.createEnrollmentDao()).thenReturn(ENROLLMENT_DAO);
        testedInstance = new UserService(daoFactory);
    }

    @Before
    public void init() {
        testUser = User.builder()
                .setId(TestData.TEST_USER.getId())
                .setFullName(TestData.TEST_USER.getFullName())
                .setEmail(TestData.TEST_USER.getEmail())
                .setPassword(TestData.TEST_USER.getPassword())
                .setStatus(TestData.TEST_USER.getStatus())
                .setRoles(TestData.TEST_USER.getRoles())
                .setCity(TestData.TEST_USER.getCity())
                .setRegion(TestData.TEST_USER.getRegion())
                .setEducation(TestData.TEST_USER.getEducation())
                .build();

        reset(USER_DAO);
        reset(ENROLLMENT_DAO);
    }

    @Test(expected = AuthenticationException.class)
    public void shouldThrowAuthenticationExceptionWhenUserNotExist() {
        String email = "email";
        when(USER_DAO.findByEmail(email)).thenReturn(Optional.empty());

        testedInstance.login(email, "password");
    }

    @Test(expected = AuthenticationException.class)
    public void shouldThrowExceptionWhenPasswordIsWrong() {
        testUser.setPassword(TestData.TEST_USER_HASHED_PASSWORD);
        when(USER_DAO.findByEmail(isA(String.class))).thenReturn(Optional.of(testUser));

        testedInstance.login("email", "some random password");
    }

    @Test
    public void shouldReturnUserDTOWhenEmailAndPasswordAreValid() {
        testUser.setPassword(TestData.TEST_USER_HASHED_PASSWORD);
        when(USER_DAO.findByEmail(isA(String.class))).thenReturn(Optional.of(testUser));
        when(ENROLLMENT_DAO.findAllByUserId(testUser.getId())).thenReturn(TestData.TEST_USER.getEnrollments());

        UserDTO result = testedInstance.login("email", TestData.TEST_USER.getPassword());

        assertThat(result)
                .usingRecursiveComparison()
                .isEqualTo(TestData.TEST_USER_DTO);
    }

    @Test
    public void ShouldReturnUserDTOWhenUserExist() {
        String email = "some@email";
        when(USER_DAO.findByEmail(email)).thenReturn(Optional.of(testUser));

        UserDTO result = testedInstance.getByEmail(email);

        assertThat(result)
                .usingRecursiveComparison()
                .ignoringFields("enrollments")
                .isEqualTo(TestData.TEST_USER_DTO);
    }

    @Test(expected = UserNotExistException.class)
    public void shouldThrowUserNotExistExceptionWhenUserNotExist() {
        when(USER_DAO.findByEmail(isA(String.class))).thenReturn(Optional.empty());

        testedInstance.getByEmail("email@e");
    }

    @Test
    public void shouldInvokeSaveMethodInDaoWhenSavingNewUser() {
        doNothing().when(USER_DAO).save(testUser);

        testedInstance.save(testUser);

        verify(USER_DAO, times(1)).save(testUser);
    }

    @Test(expected = UserNotExistException.class)
    public void shouldThrowUserNotExistExceptionWhenGetWithEnrollmentsInvokedForNonExistingUser() {
        when(USER_DAO.findById(isA(Long.class))).thenReturn(Optional.empty());

        testedInstance.getWithEnrollments(testUser.getId());
    }

    @Test
    public void shouldReturnUserWithEnrollmentsWhenUserExist() {
        Long id = testUser.getId();
        when(USER_DAO.findById(id)).thenReturn(Optional.of(testUser));
        when(ENROLLMENT_DAO.findAllByUserId(id)).thenReturn(TestData.TEST_USER.getEnrollments());

        User user = testedInstance.getWithEnrollments(id);

        assertEquals(TestData.TEST_USER.getEnrollments(), user.getEnrollments());
    }

    @Test
    public void shouldReturnUserWithNullPasswordWhenUserExist() {
        Long id = testUser.getId();
        when(USER_DAO.findById(id)).thenReturn(Optional.of(testUser));
        when(ENROLLMENT_DAO.findAllByUserId(id)).thenReturn(TestData.TEST_USER.getEnrollments());

        User user = testedInstance.getWithEnrollments(id);

        assertNull(user.getPassword());
    }

    @Test
    public void shouldInvokeBlockUserMethodInDaoWhenBlockingUser() {
        Long id = testUser.getId();
        when(USER_DAO.blockUser(id)).thenReturn(true);

        testedInstance.blockUser(id);

        verify(USER_DAO, times(1)).blockUser(id);
    }

    @Test
    public void shouldInvokeUnblockUserMethodInDaoWhenUnblockingUser() {
        Long id = testUser.getId();
        when(USER_DAO.unblockUser(id)).thenReturn(true);

        testedInstance.unblockUser(id);

        verify(USER_DAO, times(1)).unblockUser(id);
    }

}
