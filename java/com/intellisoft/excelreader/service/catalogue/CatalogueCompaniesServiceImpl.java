package com.intellisoft.excelreader.service.catalogue;

import com.intellisoft.excelreader.domain.entity.CatalogueCompanies;
import com.intellisoft.excelreader.domain.entity.Status;
import com.intellisoft.excelreader.domain.model.bindingModel.CatalogueCompaniesBindingModel;
import com.intellisoft.excelreader.domain.model.bindingModel.CatalogueCompaniesListModel;
import com.intellisoft.excelreader.repository.CatalogueCompaniesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.intellisoft.excelreader.domain.entity.Status.Active;
import static com.intellisoft.excelreader.domain.entity.Status.Disabled;

@Service
public class CatalogueCompaniesServiceImpl implements CatalogueCompaniesService {
CatalogueCompaniesRepository repository;
CatalogueCompaniesListModel companyListModel;
ModelMapper modelMapper;
Status status;
    @Autowired
    public CatalogueCompaniesServiceImpl(CatalogueCompaniesRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<CatalogueCompanies> findAllByStatus(Status status) {
        return findAllByStatus(status);
    }

    @Override
    public void saveCatalogueCompanies(CatalogueCompaniesBindingModel catalogueCompaniesBindingModel) {
        CatalogueCompanies catalogueCompanies = modelMapper.map(catalogueCompaniesBindingModel,CatalogueCompanies.class);
         repository.save(catalogueCompanies);
    }



    @Override
    public void saveCatalogueCompaniesArray(CatalogueCompaniesBindingModel[] catalogueCompaniesBindingModelArray) {
        List<CatalogueCompanies> listCompanyToSave = new ArrayList<>();

        for (CatalogueCompaniesBindingModel cm:catalogueCompaniesBindingModelArray
             ) {

            CatalogueCompanies company = modelMapper.map(cm,CatalogueCompanies.class);
            company.setStatus(Active);
            if (!repository.existsByPartnerName(company.getPartnerName())){
                repository.saveAndFlush(company);
            }

        }

    }
    @Override
    public void findDuplicateEntries(){
        List <CatalogueCompanies> companies = repository.findAll(Sort.by(Sort.Order.asc("partnerName")));
        Set companiesSet = new HashSet<>();

        for (CatalogueCompanies company: companies
        ) {
            if(!companiesSet.add(company.getPartnerName())){
                repository.delete(company);
            }
        }
    }

    @Override
    public List<CatalogueCompaniesListModel> extractCatalogueEntities(String city, Integer currentStatus){
        List<CatalogueCompaniesListModel> catalogueListModel = new ArrayList<>();
        Status status = Active;
        if (currentStatus==2){
            status = Disabled;
        }
        List <CatalogueCompanies> catalogueCompanyList = repository.findAllByPartnerAddressContainingAndStatusEquals(city,status);
        catalogueCompanyList.forEach(entity -> {
            companyListModel = modelMapper.map(entity,CatalogueCompaniesListModel.class);
            catalogueListModel.add(companyListModel);
        });
        return catalogueListModel;
    }

    @Override
    public boolean deleteCompany(Long id) {
        CatalogueCompanies company= repository.findById(id).orElse(null);
        if (company==null){
            return false;
        }
        company.setStatus(Disabled);
        repository.saveAndFlush(company);
        return true;
    }

    @Override
    public boolean saveCatalogueCompaniesComment(String data) {

        Long companyId = Long.parseLong(data.substring(0,data.indexOf("?")));
        String comment = data.substring(data.indexOf("?"));

        CatalogueCompanies company = repository.findById(companyId).orElse(null);
        if (company==null){
            return false;
        }
        company.setActionComments(comment);
        repository.saveAndFlush(company);
        return true;
    }


}
