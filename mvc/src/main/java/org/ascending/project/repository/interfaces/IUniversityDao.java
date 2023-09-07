package org.ascending.project.repository.interfaces;



import org.ascending.project.model.University;

import java.util.List;

public interface IUniversityDao {

    void save(University university); // C

    List<University> getUniversities(); // R

    University getById(long id); // U

    University update(University university);

    void delete(University university); // D


}
