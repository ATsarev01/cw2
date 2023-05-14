package com.github.atsarev01.cw2.service;

import com.github.atsarev01.cw2.model.Question;

import java.util.Collection;

public interface QuestionService {

    Question add(String question, String answer);

    Question add(Question questions);

    Question remove(Question questions);

    Collection<Question> getAll();

    Question getRandomQuestion();

}
