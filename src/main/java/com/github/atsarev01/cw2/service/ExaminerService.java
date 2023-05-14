package com.github.atsarev01.cw2.service;

import com.github.atsarev01.cw2.model.Question;

import java.util.Collection;

public interface ExaminerService {

    Collection<Question> getQuestions(int amount);
}
