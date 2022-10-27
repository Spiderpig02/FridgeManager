module fridge_manager.springboot {
    requires com.fasterxml.jackson.databind;

    requires spring.web;
    requires spring.beans;
    requires spring.boot;
    requires spring.context;
    requires spring.boot.autoconfigure;

    requires fridge_manager.core;

    opens fridge_manager.springboot to spring.beans, spring.context, spring.web;


}