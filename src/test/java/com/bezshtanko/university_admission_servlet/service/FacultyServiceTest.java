package com.bezshtanko.university_admission_servlet.service;

import com.bezshtanko.university_admission_servlet.TestData;
import com.bezshtanko.university_admission_servlet.dao.interfaces.DaoFactory;
import com.bezshtanko.university_admission_servlet.dao.interfaces.EnrollmentDao;
import com.bezshtanko.university_admission_servlet.dao.interfaces.FacultyDao;
import com.bezshtanko.university_admission_servlet.dto.PageInfoDTO;
import com.bezshtanko.university_admission_servlet.exception.FacultyNotExistException;
import com.bezshtanko.university_admission_servlet.model.faculty.Faculty;
import com.bezshtanko.university_admission_servlet.model.faculty.FacultyStatus;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class FacultyServiceTest {

    private Faculty testFaculty;

    private static final FacultyDao FACULTY_DAO = mock(FacultyDao.class);
    private static final EnrollmentDao ENROLLMENT_DAO = mock(EnrollmentDao.class);
    private static FacultyService testedInstance;

    @BeforeClass
    public static void setUp() {
        DaoFactory daoFactory = mock(DaoFactory.class);
        when(daoFactory.createFacultyDao()).thenReturn(FACULTY_DAO);
        when(daoFactory.createEnrollmentDao()).thenReturn(ENROLLMENT_DAO);
        testedInstance = new FacultyService(daoFactory);
    }

    @Before
    public void init() {
        testFaculty = Faculty.builder()
                .setId(TestData.TEST_FACULTY.getId())
                .setNameEn(TestData.TEST_FACULTY.getNameEn())
                .setNameUa(TestData.TEST_FACULTY.getNameUa())
                .setStatus(TestData.TEST_FACULTY.getStatus())
                .setDescriptionEn(TestData.TEST_FACULTY.getDescriptionEn())
                .setDescriptionUa(TestData.TEST_FACULTY.getDescriptionUa())
                .setStateFundedPlaces(TestData.TEST_FACULTY.getStateFundedPlaces())
                .setContractPlaces(TestData.TEST_FACULTY.getContractPlaces())
                .setSubjects(TestData.TEST_FACULTY.getSubjects())
                .build();

        reset(FACULTY_DAO);
        reset(ENROLLMENT_DAO);
    }

    @Test
    public void shouldInvokeSaveMethodInDaoWhenSavingFaculty() {
        doNothing().when(FACULTY_DAO).save(testFaculty);

        testedInstance.save(testFaculty);

        verify(FACULTY_DAO, times(1)).save(testFaculty);
    }

    @Test
    public void shouldReturnFacultyWhenFacultyExist() {
        when(FACULTY_DAO.findById(testFaculty.getId())).thenReturn(Optional.of(testFaculty));

        Faculty result = testedInstance.findById(testFaculty.getId());

        assertEquals(testFaculty, result);
    }

    @Test(expected = FacultyNotExistException.class)
    public void shouldThrowFacultyNotExistExceptionWhenFacultyNotExist() {
        when(FACULTY_DAO.findById(isA(Long.class))).thenReturn(Optional.empty());

        testedInstance.findById(testFaculty.getId());
    }

    @Test
    public void shouldReturnListOfFacultiesWhenFindAllInvoked() {
        List<Faculty> expected = Collections.singletonList(testFaculty);
        when(FACULTY_DAO.findAll(isA(PageInfoDTO.class))).thenReturn(expected);

        List<Faculty> actual = testedInstance.findAll(new PageInfoDTO());

        assertEquals(expected, actual);
    }

    @Test(expected = FacultyNotExistException.class)
    public void shouldThrowFacultyNotExistExceptionWhenFacultyWithEnrollmentsNotExist() {
        when(FACULTY_DAO.findById(isA(Long.class))).thenReturn(Optional.empty());

        testedInstance.findWithEnrollments(testFaculty.getId());
    }

    @Test
    public void shouldReturnFacultyWithRelevantEnrollmentsWhenFacultyIsActive() {
        Long id = testFaculty.getId();
        testFaculty.setStatus(FacultyStatus.ACTIVE);
        when(FACULTY_DAO.findById(id)).thenReturn(Optional.of(testFaculty));
        when(ENROLLMENT_DAO.findAllRelevantByFacultyId(id)).thenReturn(TestData.TEST_FACULTY.getEnrollments());

        Faculty faculty = testedInstance.findWithEnrollments(id);

        verify(FACULTY_DAO, times(1)).findById(id);
        verify(ENROLLMENT_DAO, times(1)).findAllRelevantByFacultyId(id);
        assertEquals(TestData.TEST_FACULTY.getEnrollments(), faculty.getEnrollments());
    }

    @Test
    public void shouldReturnFacultyWithFinalizedEnrollmentsWhenFacultyIsClosed() {
        testFaculty.setStatus(FacultyStatus.CLOSED);
        when(FACULTY_DAO.findById(testFaculty.getId())).thenReturn(Optional.of(testFaculty));
        when(ENROLLMENT_DAO.findAllFinalizedByFacultyId(testFaculty.getId()))
                .thenReturn(TestData.TEST_FACULTY.getEnrollments());

        Faculty faculty = testedInstance.findWithEnrollments(testFaculty.getId());

        verify(FACULTY_DAO, times(1)).findById(testFaculty.getId());
        verify(ENROLLMENT_DAO, times(1)).findAllFinalizedByFacultyId(testFaculty.getId());
        assertEquals(TestData.TEST_FACULTY.getEnrollments(), faculty.getEnrollments());
    }

    @Test
    public void shouldInvokeUpdateMethodInDaoWhenUpdatingFaculty() {
        doNothing().when(FACULTY_DAO).update(testFaculty);

        testedInstance.update(testFaculty);

        verify(FACULTY_DAO, times(1)).update(testFaculty);
    }

    @Test
    public void shouldInvokeFinalizeMethodInDaoWhenFinalizingFaculty() {
        doNothing().when(FACULTY_DAO).finalizeFaculty(testFaculty.getId());

        testedInstance.finalizeFaculty(testFaculty.getId());

        verify(FACULTY_DAO, times(1)).finalizeFaculty(testFaculty.getId());
    }

    @Test
    public void shouldInvokeDeleteMethodInDaoWhenDeletingFaculty() {
        doNothing().when(FACULTY_DAO).deleteById(testFaculty.getId());

        testedInstance.deleteById(testFaculty.getId());

        verify(FACULTY_DAO, times(1)).deleteById(testFaculty.getId());
    }

}
