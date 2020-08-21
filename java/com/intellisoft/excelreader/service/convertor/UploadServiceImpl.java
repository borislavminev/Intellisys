package com.intellisoft.excelreader.service.convertor;

import org.apache.commons.io.LineIterator;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@SuppressWarnings("ResultOfMethodCallIgnored")
@Service
public class UploadServiceImpl implements UploadService {
    private static final File TEMP_DIRECTORY = new File(System.getProperty("java.io.tmpdir"));

    @Override
    public void upload(MultipartFile[] files, String sourceDirectoryOrg) {
        StringBuilder stringBuilder = new StringBuilder();
        File directoryOfSource = new File(TEMP_DIRECTORY, sourceDirectoryOrg);

        if (!directoryOfSource.exists()) {
            //noinspection ResultOfMethodCallIgnored
            directoryOfSource.mkdir();
        }


        for (MultipartFile file : files
        ) {
            Path fileNameAndPath = Paths.get(String.valueOf(directoryOfSource), file.getOriginalFilename());

            stringBuilder.append(file.getOriginalFilename()).append(" ");

            try {
                Files.write(fileNameAndPath, file.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public Map<String, List<String>> checkFileExtension(File[] files, String delimiter, String sourceDirectoryOrg) throws IOException {
        String fullFileName = "";
        String fileNameExtension = "";
        List<String> rowData = new ArrayList<>();
        Map<String, List<String>> rowDataMap = new HashMap<String, List<String>>();

        for (File file : files
        ) {
            fullFileName = file.getName();
            fileNameExtension = fullFileName.substring(fullFileName.lastIndexOf(".")).toUpperCase();


            switch (fileNameExtension) {
                case ".CSV":
                    rowData = csvReader(file, delimiter, sourceDirectoryOrg);

                    break;
                case ".TXT":
                    rowData = csvReader(file, delimiter, sourceDirectoryOrg);

                    break;
                case ".XLSX":
                    rowData = excelReader(file, sourceDirectoryOrg);

                    break;
                case ".XLS":
                    rowData = excelReader(file, sourceDirectoryOrg);

                    break;

            }
            String fileName = file.getName().substring(0, file.getName().lastIndexOf("."));
            rowDataMap.put(fileName, rowData);
        }

        return rowDataMap;

    }

    @Override
    public List<String> csvReader(File file, String delimiter, String sourceDirectoryOrg) throws IOException {

        File directoryOfSource = new File(TEMP_DIRECTORY, sourceDirectoryOrg);
        List<String> rowData = new ArrayList<>();
        Path fileNameAndPath = Paths.get(String.valueOf(directoryOfSource), file.getName());
        FileInputStream csvFile = new FileInputStream(new File(String.valueOf(fileNameAndPath)));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(csvFile));
        final LineIterator li = new LineIterator(bufferedReader);

        while (li.hasNext()) {
            String line = li.nextLine();
            rowData.add(String.join(",", line.split(delimiter)));
        }
        Files.deleteIfExists(fileNameAndPath);
        return rowData;
    }

    @Override
    public List<String> excelReader(File file, String sourceDirectoryOrg) {

        File directoryOfSource = new File(TEMP_DIRECTORY, sourceDirectoryOrg);
        List<String> cellData = new ArrayList<>();
        List<String> rowData = new ArrayList<>();
        int listCounter = 0;


        try {
            Path fileNameAndPath = Paths.get(String.valueOf(directoryOfSource), file.getName());
            FileInputStream excelFile = new FileInputStream(new File(String.valueOf(fileNameAndPath)));
            String fullFileName = file.getName();
            String fileNameExtension = fullFileName.substring(fullFileName.lastIndexOf(".")).toUpperCase();


            Sheet sheet;
            if (fileNameExtension.equals(".XLSX")) {
                XSSFWorkbook wb = new XSSFWorkbook(excelFile);
                sheet = wb.getSheetAt(0);
            } else {
                HSSFWorkbook wb2 = new HSSFWorkbook(excelFile);
                sheet = wb2.getSheetAt(0);
            }

            Integer endRow = sheet.getLastRowNum();
            Integer searchRowNumber = sheet.getFirstRowNum();

            Row row;
            int counter = 0;

            for (int i = searchRowNumber; i <= endRow; i++) {
                counter = 0;
                //row.getFirstCellNum()
                row = sheet.getRow(i);

                for (int j = 0; j < row.getLastCellNum(); j++) {
                    Cell cell = row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    if (counter > row.getLastCellNum()) {
                        counter = row.getLastCellNum();
                    }

                    switch (cell.getCellType()) {
                        case STRING:
                            cellData.add(counter, cell.getStringCellValue());
                            break;
                        case NUMERIC:
                            Double cellValue = cell.getNumericCellValue();
                            Integer cellValuewInt = cellValue.intValue();
                            String cellValueText = cellValuewInt.toString();
                            cellData.add(counter, cellValueText);
                            break;
                        case BLANK:
                            cellData.add(counter, "NO DATA ADDED");
                            break;
                        default:
                            cellData.add(counter, "UNKNOWN");

                    }
                    counter++;
                }

                String result = String.join(",", cellData);
                rowData.add(i, result);


                cellData.clear();
                excelFile.close();
                Files.deleteIfExists(fileNameAndPath);


            }
        } catch (Exception e) {
            System.out.println("Wrong file path.");
        }


        return rowData;
    }


}
