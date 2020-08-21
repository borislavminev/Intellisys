package com.intellisoft.excelreader.service.fileManipulation;

import org.modelmapper.internal.util.ArrayIterator;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Service
public class CreateDbServiceImpl implements CreateDbService {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/";
    private static final File TEMP_DIRECTORY = new File(System.getProperty("java.io.tmpdir"));

    public void writeToDump(List<String> rowData, String delimiter,
                            String fileName, String writeDirectoryOrg,
                            String dbName, String userName, String password) {

        String dbNameUrl = DB_URL+dbName;


        File uploadDirectory = new File(TEMP_DIRECTORY, writeDirectoryOrg);
        if (!uploadDirectory.exists()) {
            //noinspection ResultOfMethodCallIgnored
            uploadDirectory.mkdir();
        }

        // JDBC driver name and database URL
        Connection conn = null;
        Statement stmt = null;

        try {


            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(dbNameUrl, userName, password);
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Creating table in given database...");
            stmt = conn.createStatement();
            String sql = "";

            String[] tableName = rowData.get(0).split(delimiter);
            rowData.remove(0);
            String columnsForTableNames = "(id int NOT NULL PRIMARY KEY auto_increment,";
            String columns = "(";
            for (int i = 0; i < tableName.length; i++) {
                String columnName = tableName[i];
                columnName = columnName.replaceAll("\\s*\\W", "_");
                columns = columns.concat(columnName + ", ");

                columnsForTableNames = columnsForTableNames.concat(columnName + " varchar(255), ");

            }
            String resultColumns = columns.substring(0, columns.lastIndexOf(","));
            String resultColumnsForTableNames = columnsForTableNames.substring(0, columnsForTableNames.lastIndexOf(","));
            resultColumns = resultColumns.concat(")");
            resultColumnsForTableNames =  resultColumnsForTableNames.concat(")");

            sql = "CREATE TABLE " + fileName +" "+ resultColumnsForTableNames;
            stmt.executeUpdate(sql);
            String element="";
            for (String el : rowData
            ) {
                List<String> elementSplit = Arrays.asList(el.split(","));
                for (String e : elementSplit
                ) {
                    String oldE = e;
                    if (e.contains("'")) {
                        e = e.replace("'", "\\'");
                    }

                    element = "'" + e + "'";
                    elementSplit.set(elementSplit.indexOf(oldE), element);
                }
                String result = String.join(",", elementSplit);
                rowData.set(rowData.indexOf(el), result);

            }
            for (String row:rowData
                 ) {
                sql ="INSERT INTO "+fileName+" "+ resultColumns+" VALUES ("+row+");";
                stmt.executeUpdate(sql);
            }

        } catch (SQLException se) {

            se.printStackTrace();
        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

    }
}



