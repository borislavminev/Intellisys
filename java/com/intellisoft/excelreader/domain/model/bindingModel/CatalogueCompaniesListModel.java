package com.intellisoft.excelreader.domain.model.bindingModel;

import com.intellisoft.excelreader.domain.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CatalogueCompaniesListModel {
    private Long id;
    private String partnerName;
    private String partnerAddress;
    private String partnerPhone;
    private String partnerEmail;
    private String partnerSait;
    private String partnerActivity;
    private String partnerComments;
    private String actionComments;
    private Status status;
}
