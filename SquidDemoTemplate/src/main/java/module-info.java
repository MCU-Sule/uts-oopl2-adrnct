module com.example.squiddemo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    exports com.example.squiddemo;
    exports com.example.squiddemo.controller;
    opens com.example.squiddemo.controller;
    exports com.example.squiddemo.dao;
    opens com.example.squiddemo.dao;
    exports com.example.squiddemo.entity;
    opens com.example.squiddemo.entity;
    exports com.example.squiddemo.util;
    opens com.example.squiddemo.util;
}