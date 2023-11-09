package com.nixagh.contentinput.repository;

import com.nixagh.contentinput.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author nghia.nguyen-dinh
 * @since 11/2/2023 at 11:06 AM
 */
@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Query(value = "SELECT MAX(questionid) FROM question", nativeQuery = true)
    Long getLastQuestionId();
}
