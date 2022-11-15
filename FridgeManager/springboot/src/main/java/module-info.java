module fridgemanager.springboot {
    requires com.fasterxml.jackson.databind;

    requires spring.web;
    requires spring.beans;
    requires spring.boot;
    requires spring.context;
    requires spring.boot.autoconfigure;

    requires org.slf4j;

    requires fridgemanager.core;

    opens fridgemanager.springboot to spring.beans, spring.context, spring.web;

}