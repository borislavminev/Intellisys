package com.intellisoft.excelreader.domain.model.bindingModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor

@Getter
@Setter
public class CatalogueCompaniesBindingModel {
    public CatalogueCompaniesBindingModel() {
    }

    private String partnerName;
    private String partnerAddress;
    private String partnerPhone;
    private String partnerEmail;
    private String partnerSait;
    private String partnerActivity;




}
