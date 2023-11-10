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
        "select r.resourceCode as resourceCode, r.resourceId as resourceId, r.description as description, p.productid as productId, r.adaptiveResourceType as adaptiveResourceType " +
        "from ResourceEntity r " +
        "join ProductEntity p on p.productid = r.productId " +
        "join ProgramTOCEntity p2 on p2.programtocid = r.programTocId " +
        "where p.code = :code " +
        "and p2.name = :unit " +
        "and r.adaptiveResourceType = :adaptiveResourceType ")
    List<Tuple> getResourceCodes(String code, String unit, String adaptiveResourceType);
}
