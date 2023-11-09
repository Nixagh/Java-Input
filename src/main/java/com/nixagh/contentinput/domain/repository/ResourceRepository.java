package com.nixagh.contentinput.domain.repository;

import com.nixagh.contentinput.domain.entities.ResourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.Tuple;
import java.util.List;

/**
 * @author nghia.nguyen-dinh
 * @since 11/9/2023 at 11:36 AM
 */
public interface ResourceRepository extends JpaRepository<ResourceEntity, Long> {
    @Query(value =
        "select r.resourcecode, r.resourceid , r.description , p.productid , r.adaptiveresourcetype " +
        "from resource r " +
        "join product p on p.productid =r.productid " +
        "join programtoc p2 on p2.programtocid = r.programtocid " +
        "where p.code = :code " +
        "and p2.\"name\" = :unit " +
        "and r.adaptiveresourcetype = :adaptiveResourceType ", nativeQuery = true)
    List<Tuple> getResourceCodes(String code, String unit, String adaptiveResourceType);
}
