package com.psj.learningNavigator.Services;

import com.psj.learningNavigator.Entites.Exam;
import com.psj.learningNavigator.Entites.Subject;
import com.psj.learningNavigator.Repositories.ExamRepository;
import com.psj.learningNavigator.Repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamService {

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    public Exam createExam(Long subjectId, String examName) {
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        Exam exam = new Exam();
        exam.setSubject(subject);
        return examRepository.save(exam);
    }
}
