package com.github.atsarev01.cw2;

import com.github.atsarev01.cw2.exception.IncorrectAmountOfQuestions;
import com.github.atsarev01.cw2.model.Question;
import com.github.atsarev01.cw2.service.ExaminerService;
import com.github.atsarev01.cw2.service.QuestionService;
import com.github.atsarev01.cw2.service.impl.ExaminerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;


import java.util.Set;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceImplTest {

    @Mock
    private QuestionService questionService;

    @InjectMocks
    private ExaminerServiceImpl examinerService;

    private final Set<Question> questions = Set.of(
            new Question("Q1", "A1"),
            new Question("Q2", "A2"),
            new Question("Q3", "A3"),
            new Question("Q4", "A4"),
            new Question("Q5", "A5")
        );

    @Test
    public void getQuestionsTest() {
        when(questionService.getAll()).thenReturn(questions);
        when(questionService.getRandomQuestion())
                .thenReturn(
                        new Question("Q1", "A1"),
                        new Question("Q1", "A1"),
                        new Question("Q3", "A3"),
                        new Question("Q3", "A3"),
                        new Question("Q5", "A5"),
                        new Question("Q5", "A5"),
                        new Question("Q3", "A3")
                );
        assertThat(examinerService.getQuestions(3)).containsExactlyInAnyOrder(
                new Question("Q1", "A1"),
                new Question("Q3", "A3"),
                new Question("Q5", "A5")
        );
    }

    @Test
    public void getQuestionsNegativeTest() {
        assertThatExceptionOfType(IncorrectAmountOfQuestions.class)
                .isThrownBy(() -> examinerService.getQuestions(-1));

        when(questionService.getAll()).thenReturn(questions);

        assertThatExceptionOfType(IncorrectAmountOfQuestions.class)
                .isThrownBy(() -> examinerService.getQuestions(questions.size() + 1));
    }
}
