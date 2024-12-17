package com.psj.learningNavigator.Services;

import com.psj.learningNavigator.Entites.Exam;
import com.psj.learningNavigator.Entites.Student;
import com.psj.learningNavigator.Entites.Subject;
import com.psj.learningNavigator.Repositories.ExamRepository;
import com.psj.learningNavigator.Repositories.StudentRepository;
import com.psj.learningNavigator.Repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private ExamRepository examRepository;

    public Student enrollSubject(Long studentId, Long subjectId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        student.getSubjects().add(subject);
        return studentRepository.save(student);
    }

    public Student registerForExam(Long studentId, Long examId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        Exam exam = examRepository.findById(examId)
                .orElseThrow(() -> new RuntimeException("Exam not found"));

        if (!student.getSubjects().contains(exam.getSubject())) {
            throw new RuntimeException("Student must be enrolled in the subject to register for the exam");
        }

        student.getExams().add(exam);
        return studentRepository.save(student);
    }
}
