package com.intellisoft.excelreader.service.catalogue;

import com.intellisoft.excelreader.domain.entity.CatalogueCompanies;
import com.intellisoft.excelreader.domain.entity.Status;
import com.intellisoft.excelreader.domain.model.bindingModel.CatalogueCompaniesBindingModel;
import com.intellisoft.excelreader.domain.model.bindingModel.CatalogueCompaniesListModel;

import java.util.List;

public interface CatalogueCompaniesService  {


    List<CatalogueCompanies> findAllByStatus(Status status);
    void saveCatalogueCompanies(CatalogueCompaniesBindingModel catalogueCompaniesBindingModel);
    void saveCatalogueCompaniesArray(CatalogueCompaniesBindingModel[] catalogueCompaniesBindingModelArray);
    void findDuplicateEntries();
    List<CatalogueCompaniesListModel> extractCatalogueEntities(String name,Integer status);

    boolean deleteCompany(Long id);
    boolean saveCatalogueCompaniesComment(String data);
}
