package com.bezshtanko.university_admission_servlet.service;

import com.bezshtanko.university_admission_servlet.TestData;
import com.bezshtanko.university_admission_servlet.dao.interfaces.DaoFactory;
import com.bezshtanko.university_admission_servlet.dao.interfaces.SubjectDao;
import com.bezshtanko.university_admission_servlet.model.subject.Subject;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class SubjectServiceTest {

    private static final SubjectDao SUBJECT_DAO = mock(SubjectDao.class);

    private static SubjectService testedInstance;

    @BeforeClass
    public static void setUp() {
        DaoFactory daoFactory = mock(DaoFactory.class);
        when(daoFactory.createSubjectDao()).thenReturn(SUBJECT_DAO);
        testedInstance = new SubjectService(daoFactory);
    }

    @Before
    public void init() {
        reset(SUBJECT_DAO);
    }

    @Test
    public void shouldReturnListOfSubjectsWhenFindAllInvoked() {
        List<Subject> expected = Arrays.asList(TestData.TEST_SUBJECT_1, TestData.TEST_SUBJECT_2);
        when(SUBJECT_DAO.findAll()).thenReturn(expected);

        List<Subject> actual = testedInstance.findAll();

        assertEquals(expected, actual);
    }

}
