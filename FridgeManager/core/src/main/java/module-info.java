module fridgemanager.core {
  requires transitive com.fasterxml.jackson.databind;

  exports fridgemanager.core;
  exports fridgemanager.json;

  opens fridgemanager.core;
  opens fridgemanager.json;
}
