package com.intellisoft.excelreader.web.controller;

import com.intellisoft.excelreader.domain.entity.CatalogueCompanies;
import com.intellisoft.excelreader.domain.model.bindingModel.CatalogueCompaniesListModel;
import com.intellisoft.excelreader.repository.CatalogueCompaniesRepository;
import com.intellisoft.excelreader.service.catalogue.CatalogueCompaniesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LoadDataRestController {

   private final CatalogueCompaniesService catalogueCompaniesService;
private final CatalogueCompaniesRepository catalogueCompaniesRepository;
    @Autowired
    public LoadDataRestController(CatalogueCompaniesService catalogueCompaniesService, CatalogueCompaniesRepository catalogueCompaniesRepository) {
        this.catalogueCompaniesService = catalogueCompaniesService;
        this.catalogueCompaniesRepository = catalogueCompaniesRepository;
    }

    @GetMapping("/loadCompanies{data}")
    public List loadCompaniesByCity(@PathVariable(value = "data") String data){
        int statusLength = data.length();
        Integer status= Integer.parseInt(data.substring(statusLength-1));
        data = data.substring(0,statusLength-1);
        List<CatalogueCompaniesListModel> catalogueList2 = catalogueCompaniesService.extractCatalogueEntities(data,status);
        return catalogueList2;
    }

    @DeleteMapping("/disableCompany{id}")
    public void DeleteCompany(@PathVariable(value = "id") Long id){
        catalogueCompaniesService.deleteCompany(id);
    }

    @PutMapping("/editCompany{data}")
    public void SaveCompanyComment(@PathVariable(value = "data") String data){
        catalogueCompaniesService.saveCatalogueCompaniesComment(data);
    }

}
