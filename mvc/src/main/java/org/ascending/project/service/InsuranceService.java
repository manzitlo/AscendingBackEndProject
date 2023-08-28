package org.ascending.project.service;

import org.ascending.project.model.Insurance;
import org.ascending.project.repository.IInsuranceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsuranceService {

    @Autowired
    private IInsuranceDao insuranceDao;

    public void save(Insurance insurance) {
        insuranceDao.save(insurance);
    }

    public List<Insurance> getInsurances() {
        return insuranceDao.getInsurances();
    }

    public void delete(Insurance insurance){
        insuranceDao.delete(insurance);
    }
    public Insurance getById(long id){
        return insuranceDao.getById(id);
    }
    public Insurance update(Insurance insurance){
        return insuranceDao.update(insurance);
    }
}
