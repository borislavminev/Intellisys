package com.intellisoft.excelreader.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intellisoft.excelreader.domain.model.bindingModel.CatalogueCompaniesBindingModel;
import com.intellisoft.excelreader.domain.model.bindingModel.CatalogueCompaniesListModel;
import com.intellisoft.excelreader.service.catalogue.CatalogueCompaniesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class CompaniesController {
    private final CatalogueCompaniesService catalogueCompaniesService;

    private final ObjectMapper objectMapper;
    private static final File TEMP_DIRECTORY = new File(System.getProperty("java.io.tmpdir"));

    @Autowired
    public CompaniesController(CatalogueCompaniesService catalogueCompaniesService, ObjectMapper objectMapper) {
        this.catalogueCompaniesService = catalogueCompaniesService;

        this.objectMapper = objectMapper;
    }

    @GetMapping("/uploadCompanies")
    public String uploadCompaniesDataForm(Model model) {

        model.addAttribute("view", "catalogue/companies-catalogue");

        return "home";
    }

    @PostMapping(value = "/uploadCompanies")
    @ResponseBody
    public void uploadCompaniesInDB(@ModelAttribute CatalogueCompaniesBindingModel catalogueCompaniesBindingModel,MultipartFile file) {

        String sourceDirectoryOrg = "json";
        StringBuilder stringBuilder = new StringBuilder();
        File directoryOfSource = new File(TEMP_DIRECTORY, sourceDirectoryOrg);

        if (!directoryOfSource.exists()) {
            //noinspection ResultOfMethodCallIgnored
            directoryOfSource.mkdir();
        }

        Path fileNameAndPath = Paths.get(String.valueOf(directoryOfSource), file.getOriginalFilename());

        stringBuilder.append(file.getOriginalFilename()).append(" ");

        try {
            Files.write(fileNameAndPath, file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        File jsonFile = new File(String.valueOf(fileNameAndPath));

        try {
            // JSON file to Java object
           CatalogueCompaniesBindingModel[] cm = objectMapper.readValue(jsonFile, CatalogueCompaniesBindingModel[].class);

           catalogueCompaniesService.saveCatalogueCompaniesArray(cm);
           catalogueCompaniesService.findDuplicateEntries();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

