package com.intellisoft.excelreader.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comments")
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",nullable = false, unique = true)
    private Long id;
    private String comment;
//    @ManyToOne
//    @JoinColumn(name = "catalogue_companies_id")
//    private CatalogueCompanies catalogueCompanies;



}
