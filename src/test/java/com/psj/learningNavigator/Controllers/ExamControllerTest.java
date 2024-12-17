package com.psj.learningNavigator.Controllers;

import com.psj.learningNavigator.Entites.Exam;
import com.psj.learningNavigator.Entites.Subject;
import com.psj.learningNavigator.Services.ExamService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ExamControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ExamService examService;

    @InjectMocks
    private ExamController examController;

    private Subject subject;
    private Exam exam;

    @BeforeEach
    public void setUp() {

        mockMvc = MockMvcBuilders.standaloneSetup(examController).build();


        subject = new Subject();
        subject.setSubjectId(1L);
        subject.setName("Math");

        exam = new Exam();
        exam.setExamId(1L);
        exam.setSubject(subject);
    }

    @Test
    public void testCreateExamSuccess() throws Exception {

        when(examService.createExam(1L, "Math Final Exam")).thenReturn(exam);


        mockMvc.perform(post("/exams/1")
                        .param("examName", "Math Final Exam")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // HTTP 200 OK
                .andExpect(jsonPath("$.examId").value(1)) // Exam ID should be 1
                .andExpect(jsonPath("$.subject.subjectId").value(1)) // Subject ID should be 1
                .andExpect(jsonPath("$.subject.name").value("Math")); // Subject name should be "Math"


        verify(examService, times(1)).createExam(1L, "Math Final Exam");
    }

    @Test
    public void testCreateExamSubjectNotFound() throws Exception {

        when(examService.createExam(99L, "History Exam")).thenThrow(new RuntimeException("Subject not found"));


        mockMvc.perform(post("/exams/99")
                        .param("examName", "History Exam")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest()) // HTTP 400 Bad Request for non-existing subject
                .andExpect(content().string("Subject not found"));


        verify(examService, times(1)).createExam(99L, "History Exam");
    }
}
