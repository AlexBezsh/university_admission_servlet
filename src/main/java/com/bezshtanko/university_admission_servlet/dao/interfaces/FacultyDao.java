package com.bezshtanko.university_admission_servlet.dao.interfaces;

import com.bezshtanko.university_admission_servlet.model.faculty.Faculty;

import java.util.Optional;

public interface FacultyDao extends Dao<Faculty> {

    Optional<Faculty> getByIdWithEnrollments(Long id);

}
