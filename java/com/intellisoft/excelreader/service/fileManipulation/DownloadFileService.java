package com.intellisoft.excelreader.service.fileManipulation;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface DownloadFileService {

    void doDownload(HttpServletRequest request,
                    HttpServletResponse response, String sourceDirectory) throws IOException;





}
