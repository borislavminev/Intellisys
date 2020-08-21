package com.intellisoft.excelreader.service.convertor;

import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Service
public class WriteServiceImpl implements WriteService {
    private static final File TEMP_DIRECTORY = new File(System.getProperty("java.io.tmpdir"));


    public void writeToTxt(List<String> rowData, String fileName, String delimiter, String writeDirectoryOrg) throws IOException {
        File downloadDirectory = new File(TEMP_DIRECTORY, writeDirectoryOrg);
        if (!downloadDirectory.exists()) {
            //noinspection ResultOfMethodCallIgnored
            downloadDirectory.mkdir();
        }

        String downloadFileName = fileName + ".txt";
        Path downloadFileNameAndPath = Paths.get(String.valueOf(downloadDirectory), downloadFileName);
        FileOutputStream fos = new FileOutputStream(new File(String.valueOf(downloadFileNameAndPath)));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

        Iterator<String> listIterator = rowData.listIterator();
        while (listIterator.hasNext()) {
            String line = listIterator.next();
            bw.write(line);
        }


        bw.flush();
        bw.close();

    }

    public void writeToJson(List<String> rowData, String fileName, String delimiter, String writeDirectoryOrg) throws IOException {

        File downloadDirectory = new File(TEMP_DIRECTORY, writeDirectoryOrg);

        if (!downloadDirectory.exists()) {
            //noinspection ResultOfMethodCallIgnored
            downloadDirectory.mkdir();
        }


        String downloadFileName = fileName + ".json";

        Path downloadFileNameAndPath = Paths.get(String.valueOf(downloadDirectory), downloadFileName);
        FileOutputStream fos = new FileOutputStream(new File(String.valueOf(downloadFileNameAndPath)));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

        String[] keyValue = rowData.get(0).split(delimiter);
       // JSON format
        //{
        //  "tableName": {
        //  "column_one": ["value_one","value_two"],
        //  "column_two": ["value_x","value_y"],
        //  "column_three" : ["value_S","value_J"]
        //  }
        //}

        bw.write("{ ");
        bw.write(fileName + " : ");
        bw.newLine();
        bw.write(" {");
        rowData.remove(0);

            for (int i = 0; i < keyValue.length; i++) {
                int counter = 0;
                bw.newLine();
                bw.write(keyValue[i] + " : ");
                bw.write("[ ");
                for (String infoRow: rowData
                     ) {
                    List <String> data = Arrays.asList(infoRow.split(delimiter));
                    String part = data.get(i);
                    bw.write(data.get(i));
                    counter++;
                    if (counter!= rowData.size()){
                        bw.write(",");
                    }

                    bw.newLine();
                }

                bw.write(" ]");
                if (i!= keyValue.length-1){
                    bw.write(",");
                }
            }
            bw.write("}");

        bw.newLine();
        bw.write("}");


        bw.close();
        fos.close();


    }

    public void writeToSql(List<String> rowData,String fileName, String delimiter, String writeDirectoryOrg) throws IOException {
        File downloadDirectory = new File(TEMP_DIRECTORY, writeDirectoryOrg);

        if (!downloadDirectory.exists()) {
            //noinspection ResultOfMethodCallIgnored
            downloadDirectory.mkdir();
        }

        String downloadFileName = fileName + ".sql";

        Path downloadFileNameAndPath = Paths.get(String.valueOf(downloadDirectory), downloadFileName);
        FileOutputStream fos = new FileOutputStream(new File(String.valueOf(downloadFileNameAndPath)));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

        String[] keyValue = rowData.get(0).split(delimiter);

        //JSON format {"tableName":["value_one","value_two","value_three"]}
        rowData.remove(0);
        String columns= "(";


        bw.write("CREATE TABLE " + fileName);
        bw.write("(");
        bw.write("id INT(11) PRIMARY KEY AUTO_INCREMENT,");
        for (int i = 0; i < keyValue.length; i++) {
            String columnName = keyValue[i];
            columnName = columnName.replaceAll("\\s*\\W","_");
            bw.write(columnName + " VARCHAR(225)");
           columns = columns.concat(columnName+", ");

        }
        String resultColumns = columns.substring(0,columns.lastIndexOf(","));
        resultColumns = resultColumns.concat(")");

        bw.write(");");

        String finalResultColumns = resultColumns;

         //Add ' around each element
        for (String row:rowData
             ) {
            List <String> rowSplit = Arrays.asList(row.split(","));

            for (String e : rowSplit) {
                if (e.contains("'")){
                    e = e.replace("'","\\'");
                }

             String element ="'" + e + "'";
                rowSplit.set(rowSplit.indexOf(e), element);
            }
            String result = String.join(",",rowSplit);
             rowData.set(rowData.indexOf(row), result);
        }

        rowData.forEach(e->
                {

                    try {
                        bw.write("INSERT INTO "+fileName+" "+ finalResultColumns +" VALUES ("+e+");" );
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
        );
        bw.write(";");

        bw.flush();
        bw.close();

    }


}
