package com.bezshtanko.university_admission_servlet.dao.interfaces;

import com.bezshtanko.university_admission_servlet.model.faculty.Faculty;

public interface FacultyDao extends Dao<Faculty> {

    boolean setClosed(Long id);
    void finalizeFaculty(Long id);

}
