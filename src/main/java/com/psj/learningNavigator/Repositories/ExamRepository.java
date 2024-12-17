package com.psj.learningNavigator.Repositories;

import com.psj.learningNavigator.Entites.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {

    Optional<Exam> findByExamId(Long examId);
}
