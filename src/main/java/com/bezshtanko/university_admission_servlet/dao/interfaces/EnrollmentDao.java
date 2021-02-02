package com.bezshtanko.university_admission_servlet.dao.interfaces;

import com.bezshtanko.university_admission_servlet.model.enrollment.Enrollment;

import java.util.List;

public interface EnrollmentDao extends Dao<Enrollment> {

    boolean setApproved(Long id);
    boolean setFinalized(Long id);
    List<Enrollment> findAllByUserId(Long id);
    List<Enrollment> findAllByFacultyId(Long id);

}
