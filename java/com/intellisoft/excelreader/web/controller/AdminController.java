package com.intellisoft.excelreader.web.controller;

import com.intellisoft.excelreader.domain.entity.AirLine;
import com.intellisoft.excelreader.domain.entity.User;
import com.intellisoft.excelreader.domain.model.bindingModel.DbBindingModel;
import com.intellisoft.excelreader.service.UserModelService;
import com.intellisoft.excelreader.service.convertor.UploadService;
import com.intellisoft.excelreader.service.fileManipulation.CreateDbService;
import com.intellisoft.excelreader.service.pnrAndPnlService.AirLineService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class AdminController {
    private static final File TEMP_DIRECTORY = new File(System.getProperty("java.io.tmpdir"));
        private final ModelMapper modelMapper;
        private final UploadService uploadService;
        private final CreateDbService createDbService;
        private final AirLineService airLineService;
        private final UserModelService userModelService;
    public AdminController(ModelMapper modelMapper, UploadService uploadService, CreateDbService createDbService, AirLineService airLineService, UserModelService userModelService) {
        this.modelMapper = modelMapper;
        this.uploadService = uploadService;
        this.createDbService = createDbService;
        this.airLineService = airLineService;
        this.userModelService = userModelService;
    }

    @GetMapping("/admin")
        public String adminPanel(Model model){
        List<AirLine> airlineList = airLineService.findAllAirline();
        List<User> userList = userModelService.findAllUser();
            model.addAttribute("dbBindingModel",new DbBindingModel());
            model.addAttribute("airlines",airlineList);
            model.addAttribute("users",userList);
            model.addAttribute("view","admin/admin");
            return "admin-main";
        }

        @PostMapping("/admin")
        public String dbFill(Model model,@ModelAttribute DbBindingModel dbBindingModel, MultipartFile[] files,String delimiter,String output) throws IOException {
            String savedDirectory="db";

            String writeDirectory = savedDirectory+"write";
            File sourceDirectory = new File(TEMP_DIRECTORY, savedDirectory);
            uploadService.upload(files,savedDirectory);
            dbBindingModel.getName();
            Integer counter = 0;
            int fileCount = sourceDirectory.listFiles().length;
            File[] downloadedFiles = new File[fileCount];
            String firstFileName=files[0].getOriginalFilename().substring(0,files[0].getOriginalFilename().indexOf("."));
            if (sourceDirectory.isDirectory()) {
                for (File file : sourceDirectory.listFiles()
                ) {

                    downloadedFiles[counter] = file;
                    counter++;
                }
            }

            Map<String, List<String>> result = uploadService.checkFileExtension(downloadedFiles, delimiter, savedDirectory);
            result.get(firstFileName);
            createDbService.writeToDump(result.get(firstFileName),delimiter,firstFileName,writeDirectory,
                    dbBindingModel.getDb(),dbBindingModel.getName(),dbBindingModel.getPassword());
            String msg = "Done!";
            model.addAttribute("msg",msg);
            model.addAttribute("view","admin/admin");
            return "admin-main";

        }




}
