package org.ascending.project.repository.interfaces;

import org.ascending.project.model.Answer;

import java.util.List;

public interface IAnswerDao {

    List<Answer> getAnswers();
    void save(Answer answer);
    Answer getById(Long id);
    void delete(Answer answer);
    Answer update(Answer answer);

}
