package com.intellisoft.excelreader.domain.entity;


import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.util.Set;


@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "catalogue_companies")
public class CatalogueCompanies {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",nullable = false, unique = true)
    private Long id;
    @Column(name = "partner_name")
    private String partnerName;
    @Column(name = "partner_address",length = 600)
    private String partnerAddress;
    @Column(name = "partner_phone")
    private String partnerPhone;
    @Column(name = "partner_email")
    private String partnerEmail;
    @Column(name="partner_sait")
    private String  partnerSait;
    @Column(name="partner_activity",length = 1000)
    private String  partnerActivity;
    @Column(name="partner_comment",length = 2000)
    private String partnerComments;
    @Column(name="action_comments",length = 2000)
    private String actionComments;
    @Enumerated(EnumType.STRING)
    private Status status;




}
