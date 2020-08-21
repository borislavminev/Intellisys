package com.intellisoft.excelreader.service.fileManipulation;

import java.io.IOException;


public interface ArchiveFilesService  {

    void ZipFile(String writeDirectoryOrg, String targetDirectory, String fileNameSource) throws IOException;

}
