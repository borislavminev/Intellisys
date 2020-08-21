package com.intellisoft.excelreader.service.fileManipulation;

import java.util.List;

public interface CreateDbService {

    void writeToDump(List<String> rowData, String delimiter,
                     String fileName, String writeDirectoryOrg,
                     String dbName, String userName, String password);

}
