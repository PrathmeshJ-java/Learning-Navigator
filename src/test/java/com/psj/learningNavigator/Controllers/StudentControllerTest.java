package com.psj.learningNavigator.Controllers;

import com.psj.learningNavigator.Entites.Student;
import com.psj.learningNavigator.Services.StudentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private StudentService studentService;

    @Test
    public void testEnrollInSubject() throws Exception {
        Student student = new Student();
        student.setStudentId(1L);
        student.setName("John Doe");

        Mockito.when(studentService.enrollSubject(1L, 1L)).thenReturn(student);

        mockMvc.perform(post("/students/1/subjects/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"));
    }
}
