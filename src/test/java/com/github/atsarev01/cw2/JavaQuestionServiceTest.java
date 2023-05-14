package com.github.atsarev01.cw2;

import com.github.atsarev01.cw2.exception.IncorrectAmountOfQuestions;
import com.github.atsarev01.cw2.exception.QuestionAlreadyAddedException;
import com.github.atsarev01.cw2.exception.QuestionNotFoundException;
import com.github.atsarev01.cw2.model.Question;
import com.github.atsarev01.cw2.service.QuestionService;
import com.github.atsarev01.cw2.service.impl.JavaQuestionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;

public class JavaQuestionServiceTest {

    private final QuestionService questionService = new JavaQuestionService();

    @BeforeEach
    public void beforeEach() {
        questionService.add(new Question("Q1", "A1"));
        questionService.add(new Question("Q2", "A2"));
        questionService.add(new Question("Q3", "A3"));
    }
    @AfterEach
    public void afterEach() {
        Collection<Question> questions = new ArrayList<>(questionService.getAll());
        questions.forEach(questionService :: remove);
    }
    @Test
    public void addTest() {
        int beforeCount = questionService.getAll().size();
        Question expected = new Question("Q4", "A4");
        Question actual = questionService.add("Q4", "A4");

        assertThat(actual).isEqualTo(expected)
                .isIn(questionService.getAll());
        assertThat(questionService.getAll()).hasSize(beforeCount + 1);

    }

    @Test
    public void addNegativeTest() {
        assertThatExceptionOfType(QuestionAlreadyAddedException.class)
                .isThrownBy(() -> questionService.add("Q1", "A1"));

    }
    @Test
    public void add2Test() {
        int beforeCount = questionService.getAll().size();
        Question expected = new Question("Q4", "A4");
        Question actual = questionService.add(new Question("Q4", "A4"));

        assertThat(actual).isEqualTo(expected)
                .isIn(questionService.getAll());
        assertThat(questionService.getAll()).hasSize(beforeCount + 1);

    }

    @Test
    public void add2NegativeTest() {
        assertThatExceptionOfType(QuestionAlreadyAddedException.class)
                .isThrownBy(() -> questionService.add(new Question("Q1", "A1")));

    }

    @Test
    public void removeTest() {
        int beforeCount = questionService.getAll().size();
        Question expected = new Question("Q2", "A2");
        Question actual = questionService.remove(new Question("Q2", "A2"));

        assertThat(actual).isEqualTo(expected)
                .isNotIn(questionService.getAll());
        assertThat(questionService.getAll()).hasSize(beforeCount - 1);

    }

    @Test
    public void removeNegativeTest() {
        assertThatExceptionOfType(QuestionNotFoundException.class)
                .isThrownBy(() -> questionService.remove(new Question("Q4", "A4")));

    }

    @Test
    public void getRandomQuestionTest() {
        assertThat(questionService.getRandomQuestion())
                .isNotNull()
                .isIn(questionService.getAll());
    }

    @Test
    public void getRandomQuestionNegativeTest() {
        afterEach();
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(questionService::getRandomQuestion);
    }
}
