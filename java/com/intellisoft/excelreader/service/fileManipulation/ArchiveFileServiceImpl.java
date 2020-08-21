package com.intellisoft.excelreader.service.fileManipulation;


import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class ArchiveFileServiceImpl implements ArchiveFilesService{
    private static final File TEMP_DIRECTORY = new File(System.getProperty("java.io.tmpdir"));

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Override
    public void ZipFile(String writeDirectoryOrg, String downloadDirectory, String fileNameSource) throws IOException {
        File sourceDirectory = new File(TEMP_DIRECTORY, writeDirectoryOrg);

        File destinationDirectory = new File(TEMP_DIRECTORY, downloadDirectory);

        if (!destinationDirectory.exists()) {
            //noinspection ResultOfMethodCallIgnored
            destinationDirectory.mkdir();
        }

        List<File> filesArr = new ArrayList<File>();
        if (sourceDirectory.isDirectory()) {
            String[] files = sourceDirectory.list();

            if (files != null) {
                for (String file : files) {

                     filesArr.add(new File(file));

                }
            }

        }


        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(String.valueOf(destinationDirectory)+ File.separator + fileNameSource + ".zip"));

        for (File file : filesArr) {
            zos.putNextEntry(new ZipEntry(file.getName()));
            //new File(String.valueOf(fileNameAndPath))
            FileInputStream fileInputStream = new FileInputStream(String.valueOf(sourceDirectory) + File.separator + file.getName());
            IOUtils.copy(fileInputStream, zos);
            fileInputStream.close();
            zos.closeEntry();
            file.delete();

        }

        sourceDirectory.delete();
        zos.close();


    }
}
