package com.nixagh.contentinput.domain.repository;

import com.nixagh.contentinput.domain.entities.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author nghia.nguyen-dinh
 * @since 11/2/2023 at 11:06 AM
 */
@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {
    @Query(value = "SELECT q FROM QuestionEntity q WHERE q.questionnumber = ?1 AND q.resourceid = ?2")
    Optional<QuestionEntity> getQuestionEntityByQuestionNumberAndResourceId(Integer questionNumber, Long resourceId);
}
