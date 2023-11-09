package com.nixagh.contentinput.domain.repository;

import com.nixagh.contentinput.domain.entities.PassageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;
import java.util.List;

/**
 * @author nghia.nguyen-dinh
 * @since 11/6/2023 at 1:20 PM
 */
public interface PassageRepository extends JpaRepository<PassageEntity, Long> {
    // get passage by questionNumber question join with resource id
    @Query(value =
        "select p " +
            "from PassageEntity p " +
            "where p.passageId in (" +
            "select q.passageid as passageid " +
            "from QuestionEntity q " +
            "where q.questionnumber = :questionNumber " +
            "and p.resourceId = :resourceId" +
            ")"
    )
    List<PassageEntity> getPassageByQuestionNumberAndResourceId(int questionNumber, BigInteger resourceId);

    // get lass passageNumber
    @Query(value = "select max(p.passageNumber) from PassageEntity p")
    Long getLastPassageNumber();
}
