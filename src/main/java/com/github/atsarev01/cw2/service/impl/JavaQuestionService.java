package com.github.atsarev01.cw2.service.impl;

import com.github.atsarev01.cw2.exception.QuestionAlreadyAddedException;
import com.github.atsarev01.cw2.exception.QuestionNotFoundException;
import com.github.atsarev01.cw2.model.Question;
import com.github.atsarev01.cw2.service.QuestionService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {

        private final Set<Question> questions = new HashSet<>();

        private final Random random = new Random();


        @Override
        public Question add(String question, String answer) {
            return add(new Question(question, answer));
        }
        @Override
        public Question add(Question question) {
            if (questions.add(question)) {
                return question;
            } throw new QuestionAlreadyAddedException();
        }
        @Override
        public Question remove(Question question) {
            if (questions.remove(question)) {
                return question;
            } throw new QuestionNotFoundException();
        }
        @Override
        public Collection<Question> getAll() {
            return Collections.unmodifiableCollection(questions);
        }
        @Override
        public Question getRandomQuestion() {
            return new ArrayList<>(questions).get(random.nextInt(questions.size()));
        }

    }

