package org.ascending.project.repository.interfaces;

import org.ascending.project.model.Question;

import java.util.List;

public interface IQuestionDao {

    void save(Question question);

    List<Question> getQuestions();

    Question getById(Long id);

    void delete(Question question);

    Question update(Question question);



}
