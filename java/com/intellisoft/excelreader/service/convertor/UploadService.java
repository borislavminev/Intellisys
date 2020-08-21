package com.intellisoft.excelreader.service.convertor;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;


public interface UploadService {
    void upload(MultipartFile[] files, String directoryName);
    Map<String,List<String>> checkFileExtension(File[] files, String delimiter, String sourceDirectoryOrg) throws IOException;
    List<String> csvReader(File file, String delimiter, String sourceDirectoryOrg) throws IOException;
    List<String> excelReader(File file, String sourceDirectoryOrg);

}
