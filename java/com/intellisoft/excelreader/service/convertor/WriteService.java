package com.intellisoft.excelreader.service.convertor;

import java.io.IOException;
import java.util.List;

public interface WriteService {
    void writeToTxt(List<String> rowData, String fileName, String delimiter, String writeDirectoryOrg) throws IOException;
    void writeToJson(List<String> rowData, String fileName,  String delimiter,String writeDirectoryOrg) throws IOException;
    void writeToSql(List<String> rowData, String fileName, String delimiter, String writeDirectoryOrg) throws IOException;

}
