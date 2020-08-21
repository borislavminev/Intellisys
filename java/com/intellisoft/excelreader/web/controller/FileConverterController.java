package com.intellisoft.excelreader.web.controller;

import com.intellisoft.excelreader.service.UserModelService;
import com.intellisoft.excelreader.service.convertor.UploadService;
import com.intellisoft.excelreader.service.convertor.WriteService;
import com.intellisoft.excelreader.service.fileManipulation.ArchiveFilesService;
import com.intellisoft.excelreader.service.fileManipulation.DownloadFileService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

@Controller
public class FileConverterController {
    private static final File TEMP_DIRECTORY = new File(System.getProperty("java.io.tmpdir"));

    private final UploadService uploadService;
    private final WriteService writeService;
    private final UserModelService userModelService;
    private final DownloadFileService downloadFileService;
    private final ArchiveFilesService archiveFilesService;

    @Autowired
    public FileConverterController(UploadService uploadService, WriteService writeService,
                                   UserModelService userModelService, UserModelService userModelService1,
                                   DownloadFileService downloadFileService, ArchiveFilesService archiveFilesService) {
        this.uploadService = uploadService;
        this.writeService = writeService;
        this.userModelService = userModelService1;
        this.downloadFileService = downloadFileService;
        this.archiveFilesService = archiveFilesService;
    }

    @GetMapping(value = "/intelli-convert" )
    private String uploadFile(Model model) {
        model.addAttribute("view", "converter/intelli-convert");
        return "home";

    }

    @PostMapping("/intelli-convert")
    private void uploadConformation(HttpServletRequest request,
                                      HttpServletResponse response, Model model, MultipartFile[] files,
                                    String delimiter, String output) throws IOException {

        String partFile = files[0].getOriginalFilename();
        String outputFileName =  partFile.substring(0,partFile.indexOf("."));
        String sourceDirectoryOrg = outputFileName+"source";
        String writeDirectoryOrg = outputFileName+"write";
        String downloadDirectoryOrg = outputFileName+"download";
        File sourceDirectory = new File(TEMP_DIRECTORY, sourceDirectoryOrg);

        Path path = Paths.get(sourceDirectory.toString());
        uploadService.upload(files, sourceDirectoryOrg);
        Integer counter = 0;
        int fileCount = sourceDirectory.listFiles().length;
        File[] downloadedFiles = new File[fileCount];

        if (sourceDirectory.isDirectory()) {
            for (File file : sourceDirectory.listFiles()
            ) {

                downloadedFiles[counter] = file;
                counter++;
            }
        }

        Map<String, List<String>> result = uploadService.checkFileExtension(downloadedFiles, delimiter, sourceDirectoryOrg);

        for (Map.Entry<String, List<String>> element : result.entrySet()
        ) {
            String fileName = element.getKey();
            List<String> fileValues = element.getValue();
            if (output.equals("json")) {
                writeService.writeToJson(fileValues, fileName, delimiter, writeDirectoryOrg);
            } else if (output.equals("sql")) {
                writeService.writeToSql(fileValues, fileName, delimiter, writeDirectoryOrg);
            }
        }

        if (fileCount > 1) {
            archiveFilesService.ZipFile(writeDirectoryOrg,downloadDirectoryOrg,outputFileName);
              writeDirectoryOrg = downloadDirectoryOrg;
        }

        downloadFileService.doDownload(request, response ,writeDirectoryOrg);
        Authentication loggedUser = SecurityContextHolder.getContext().getAuthentication();
        String currentUserEmail = loggedUser.getName();
        Integer creditCharged = fileCount;
        //  userModelService.decreaseUserCredit(currentUserEmail, creditCharged);

        File DirectoryOfSource = new File(TEMP_DIRECTORY, sourceDirectoryOrg);
        File DirectoryOfWrite = new File(TEMP_DIRECTORY, writeDirectoryOrg);
        File DirectoryOfDownload = new File(TEMP_DIRECTORY, downloadDirectoryOrg);
        FileUtils.deleteDirectory(DirectoryOfSource);
        FileUtils.deleteDirectory(DirectoryOfWrite);
        FileUtils.deleteDirectory(DirectoryOfDownload);

    }

}
