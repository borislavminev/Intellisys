package com.intellisoft.excelreader.service.fileManipulation;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class DownloadFileServiceImpl implements DownloadFileService{
    /**
     * Size of a byte buffer to read/write file
     */
    private static final int BUFFER_SIZE = 4096;

    /**
     * Path of the file to be downloaded, relative to application's directory
     */
    private static final File TEMP_DIRECTORY = new File(System.getProperty("java.io.tmpdir"));


    @SuppressWarnings("ResultOfMethodCallIgnored")
    @RequestMapping(method = RequestMethod.GET)
    public void doDownload(HttpServletRequest request,
                           HttpServletResponse response, String sourceDirectory) throws IOException {



        File directorySource = new File(TEMP_DIRECTORY, sourceDirectory);


        List<File> filesArr = new ArrayList<File>();

           File[] file = directorySource.listFiles();
           filesArr.addAll(Arrays.asList(file));

        String partFileName = filesArr.get(0).getName();
        // get absolute path of the application
        ServletContext context = request.getServletContext();
        String appPath = context.getRealPath("");
        // construct the complete absolute path of the file

        String sourceFileName = File.separator + partFileName;
        String sourcePath = directorySource + sourceFileName;
        File downloadFile  = new File(sourcePath);
        FileInputStream inputStream = new FileInputStream(downloadFile);

        // get MIME type of the file
        String mimeType = context.getMimeType(sourcePath);
        if (mimeType == null) {
            // set to binary type if MIME mapping not found
            mimeType = "application/octet-stream";
        }

        // set content attributes for the response
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());

        // set headers for the response
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                downloadFile.getName());
        response.setHeader(headerKey, headerValue);



//        String destinationFileName = File.separator + partFileName;
//        String destinationPath = directoryDownload + destinationFileName;
//        FileOutputStream outStream = new FileOutputStream(new File(destinationPath));

        // get output stream of the response
        OutputStream outStream = response.getOutputStream();

        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead = -1;

        // write bytes read from the input stream into the output stream
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }

        inputStream.close();
        outStream.close();
        directorySource.delete();



    }
}
