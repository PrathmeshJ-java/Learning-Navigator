package com.psj.learningNavigator.Controllers;

import com.psj.learningNavigator.Entites.Student;
import com.psj.learningNavigator.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/{studentId}/subjects/{subjectId}")
    public ResponseEntity<Student> enrollInSubject(@PathVariable Long studentId, @PathVariable Long subjectId) {
        Student student = studentService.enrollSubject(studentId, subjectId);
        return ResponseEntity.ok(student);
    }

    @PostMapping("/{studentId}/exams/{examId}")
    public ResponseEntity<Student> registerForExam(@PathVariable Long studentId, @PathVariable Long examId) {
        Student student = studentService.registerForExam(studentId, examId);
        return ResponseEntity.ok(student);
    }
}
