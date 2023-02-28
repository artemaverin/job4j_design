package ru.job4j.jdbc;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) throws Exception {
        this.properties = properties;
        initConnection();
    }

    public Properties getProperties() {
        return properties;
    }

    private void initConnection() throws Exception {
        Class.forName(getProperties().getProperty("driver"));
        connection = DriverManager
                .getConnection(getProperties().getProperty("url"),
                        getProperties().getProperty("login"),
                        getProperties().getProperty("password"));
    }

    private void statementRun(String sql) throws Exception {
        Statement statement = connection.createStatement();
        statement.execute(sql);
    }

    public void createTable(String tableName) throws Exception {
        String sql = String.format(
                "create table if not exists %s();", tableName
        );
        statementRun(sql);
    }

    public void dropTable(String tableName) throws Exception {
        String sql = String.format(
                "drop table if exists %s;", tableName
        );
        statementRun(sql);
    }

    public void addColumn(String tableName, String columnName, String type) throws Exception {
        String sql = String.format(
                "alter table %s add %s %s;", tableName, columnName, type
        );
        statementRun(sql);
    }

    public void dropColumn(String tableName, String columnName) throws Exception {
        String sql = String.format(
                "alter table %s drop column %s;", tableName, columnName
        );
        statementRun(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws Exception {
        String sql = String.format(
                "alter table %s rename column %s to %s;", tableName, columnName, newColumnName
        );
        statementRun(sql);
    }


    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(in);
        }
        TableEditor tableEditor = new TableEditor(config);
//        System.out.println("CREATE TABLE");
//        tableEditor.createTable("category");
//        System.out.println(tableEditor.getTableScheme("category"));
//
//        System.out.println("ADD COLUMN");
//        tableEditor.addColumn("category", "c_name", "varchar");
//        System.out.println(tableEditor.getTableScheme("category"));
//
//        System.out.println("RENAME COLUMN");
//        tableEditor.renameColumn("category", "c_name", "category_title");
//        System.out.println(tableEditor.getTableScheme("category"));

//        System.out.println("DELETE COLUMN");
//        tableEditor.dropColumn("category", "c_name");
//        System.out.println(tableEditor.getTableScheme("category"));

        System.out.println("DROP TABLE");
        tableEditor.dropTable("category");
        System.out.println(tableEditor.getTableScheme("category"));
    }
}
