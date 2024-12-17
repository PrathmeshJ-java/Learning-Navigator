package com.psj.learningNavigator.Controllers;

import com.psj.learningNavigator.Entites.Exam;
import com.psj.learningNavigator.Services.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exams")
public class ExamController {

    @Autowired
    private ExamService examService;

    @PostMapping("/{subjectId}")
    public ResponseEntity<Exam> createExam(@PathVariable Long subjectId, @RequestParam String examName) {
        Exam exam = examService.createExam(subjectId, examName);
        return ResponseEntity.ok(exam);
    }


}
