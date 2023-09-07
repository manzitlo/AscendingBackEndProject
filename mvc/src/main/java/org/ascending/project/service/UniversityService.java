package org.ascending.project.service;

import org.ascending.project.model.University;
import org.ascending.project.repository.interfaces.IUniversityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UniversityService {

    private final org.ascending.project.repository.interfaces.IUniversityDao IUniversityDao;

    @Autowired
    public UniversityService(IUniversityDao IUniversityDao) {
        this.IUniversityDao = IUniversityDao;
    }

    public void save(University university){
        IUniversityDao.save(university);
    }

    public List<University> getUniversities() {
        return IUniversityDao.getUniversities();
    }

    public void delete(University university) {
        IUniversityDao.delete(university);
    }

    public University getById(long id) {
        return IUniversityDao.getById(id);
    }

    public University update(University university){
        return IUniversityDao.update(university);
    }
}
