package org.ascending.project.service;

import org.ascending.project.model.Answer;
import org.ascending.project.repository.interfaces.IAnswerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class AnswerService {

    private final org.ascending.project.repository.interfaces.IAnswerDao IAnswerDao;

    @Autowired
    public AnswerService(IAnswerDao IAnswerDao) {
        this.IAnswerDao = IAnswerDao;
    }

    public void save(Answer answer){
        IAnswerDao.save(answer);
    }

    public void delete(Answer answer){
        IAnswerDao.delete(answer);
    }

    public List<Answer> getAnswers(){
        return IAnswerDao.getAnswers();
    }

    public Answer update(Answer answer){
        return IAnswerDao.update(answer);
    }

    public Answer getById(long id){
        return IAnswerDao.getById(id);
    }
}
