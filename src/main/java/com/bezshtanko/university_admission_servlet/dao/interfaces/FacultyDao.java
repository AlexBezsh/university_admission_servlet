package com.bezshtanko.university_admission_servlet.dao.interfaces;

import com.bezshtanko.university_admission_servlet.dto.PageInfoDTO;
import com.bezshtanko.university_admission_servlet.model.faculty.Faculty;

import java.util.List;

public interface FacultyDao extends Dao<Faculty> {

    List<Faculty> findAll(PageInfoDTO pageInfo);
    void finalizeFaculty(Long id);

}
