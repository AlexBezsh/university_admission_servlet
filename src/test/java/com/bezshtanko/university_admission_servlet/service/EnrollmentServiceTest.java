package com.bezshtanko.university_admission_servlet.service;

import com.bezshtanko.university_admission_servlet.TestData;
import com.bezshtanko.university_admission_servlet.dao.interfaces.DaoFactory;
import com.bezshtanko.university_admission_servlet.dao.interfaces.EnrollmentDao;
import com.bezshtanko.university_admission_servlet.dto.UserDTO;
import com.bezshtanko.university_admission_servlet.model.enrollment.Enrollment;
import com.bezshtanko.university_admission_servlet.model.enrollment.EnrollmentStatus;
import com.bezshtanko.university_admission_servlet.model.faculty.Faculty;
import com.bezshtanko.university_admission_servlet.model.mark.Mark;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;

public class EnrollmentServiceTest {

    private static final EnrollmentDao ENROLLMENT_DAO = mock(EnrollmentDao.class);
    private static EnrollmentService testedInstance;

    @BeforeClass
    public static void setUp() {
        DaoFactory daoFactory = mock(DaoFactory.class);
        when(daoFactory.createEnrollmentDao()).thenReturn(ENROLLMENT_DAO);
        testedInstance = new EnrollmentService(daoFactory);
    }

    @Before
    public void init() {
        reset(ENROLLMENT_DAO);
    }

    @Test
    public void shouldCreateEnrollmentAndInvokeSaveMethodInDaoWhenSavingNewEnrollment() {
        UserDTO user = UserDTO.builder().setId(1L).build();
        Faculty faculty = Faculty.builder().setId(2L).build();
        List<Mark> marks = TestData.TEST_ENROLLMENT.getMarks();
        ArgumentCaptor<Enrollment> captor = ArgumentCaptor.forClass(Enrollment.class);
        doNothing().when(ENROLLMENT_DAO).save(any(Enrollment.class));

        testedInstance.save(user, faculty, marks);

        verify(ENROLLMENT_DAO).save(captor.capture());
        Enrollment enrollment = captor.getValue();

        assertEquals(user.getId(), enrollment.getUser().getId());
        assertEquals(faculty.getId(), enrollment.getFaculty().getId());
        assertEquals(marks, enrollment.getMarks());
        assertSame(enrollment.getStatus(), EnrollmentStatus.NEW);
    }

    @Test
    public void shouldInvokeSetApprovedMethodInDaoWhenApprovingEnrollment() {
        Long id = TestData.TEST_USER.getId();
        when(ENROLLMENT_DAO.setApproved(id)).thenReturn(true);

        testedInstance.setApproved(id);

        verify(ENROLLMENT_DAO, times(1)).setApproved(id);
    }

}
