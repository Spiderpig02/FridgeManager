module fridgemanager.springboot {
    requires com.fasterxml.jackson.databind;

    requires spring.web;
    requires spring.beans;
    requires spring.boot;
    requires spring.context;
    requires spring.boot.autoconfigure;

    requires org.slf4j;

    requires fridgemanager.core;
    requires java.net.http;

    opens fridgemanager.springboot;
}