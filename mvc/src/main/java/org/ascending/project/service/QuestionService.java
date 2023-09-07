package org.ascending.project.service;

import org.ascending.project.model.Question;
import org.ascending.project.repository.interfaces.IQuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    private final org.ascending.project.repository.interfaces.IQuestionDao IQuestionDao;

    @Autowired
    public QuestionService(IQuestionDao IQuestionDao) {
        this.IQuestionDao = IQuestionDao;
    }

    public void save(Question question){
        IQuestionDao.save(question);
    }

    public void delete(Question question){
        IQuestionDao.delete(question);
    }

    public List<Question> getQuestions(){
        return IQuestionDao.getQuestions();
    }

    public Question update(Question question){
        return IQuestionDao.update(question);
    }

    public Question getById(long id){
        return IQuestionDao.getById(id);
    }


}
