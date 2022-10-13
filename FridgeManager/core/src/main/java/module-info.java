module fridge_manager.core {
    requires transitive com.fasterxml.jackson.databind;

    exports fridge_manager.core;
    exports fridge_manager.json;

    opens fridge_manager.core;
    opens fridge_manager.json;
}