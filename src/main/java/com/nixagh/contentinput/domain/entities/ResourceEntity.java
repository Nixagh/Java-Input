package com.nixagh.contentinput.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author nghia.nguyen-dinh
 * @since 11/9/2023 at 11:33 AM
 */
@Entity
@Table(name = "resource")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResourceEntity {
    @Id
    @Column(name = "resourceid")
    private Long resourceId;
    @Column(name = "resourcecode")
    private String resourceCode;
    @Column(name = "productid")
    private Long productId;
    @Column(name = "programtocid")
    private Long programTocId;
    @Column(name = "description")
    private String description;
    @Column(name = "adaptiveresourcetype")
    private String adaptiveResourceType;
}
