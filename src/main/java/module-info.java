module com.example.ohjelmistotuotanto {
    requires javafx.controls;
    requires java.sql;
    requires mysql.connector.j;
    requires org.apache.fontbox;
    requires org.apache.pdfbox;
    requires java.desktop;

    exports com.example.ohjelmistotuotanto;
    exports com.example.ohjelmistotuotanto.utils;
    exports com.example.ohjelmistotuotanto.entities;
}