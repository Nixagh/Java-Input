package com.nixagh.contentinput.repository;

import com.nixagh.contentinput.entities.Passage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author nghia.nguyen-dinh
 * @since 11/6/2023 at 1:20 PM
 */
public interface PassageRepository extends JpaRepository<Passage, Long> {
    // get passage by questionNumber question join with resource id
    @Query(value = """
        select p
        from Passage p
        where p.passageId in (
            select q.passageid as passageid
            from Question q
            where q.questionnumber = :questionNumber
            and p.resourceId = :resourceId
        )
        """)
    List<Passage> getPassageByQuestionNumberAndResourceId(int questionNumber, Long resourceId);

    // get lass passageNumber
    @Query(value = """
        select max(p.passageNumber)
        from Passage p
        """)
    Long getLastPassageNumber();
}
