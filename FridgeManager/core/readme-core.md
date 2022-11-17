**Beskrivelse av klassene i core-modulen:**
=
<u><font size="4">**Klassene i core-mappen**:</font></u>   

<u><font size="2">**Food.java:**</font></u> Grunnleggende klasse for en matvare med tilhørende navn, antall, enhet, utløpsdato og eier.       
<u><font size="2">**FridgeManager.java:**</font></u> Klasse som administrer to lister med Food-objekter, en for Fridge og en for Freezer.    
<u><font size="2">**FoodComparator.java:**</font></u> Funksjonelt grensesnitt som muligjør sammenlikning av Foodobjekter. Dette gjør for eksempel at en liste Food-objekter kan sorteres etter utløpsdato.      

<u><font size="4">**Klassene i json-mappen**:</font></u> 

<u><font size="2">**FileHandler.java:**</font></u> Administrerer lagring og skriving av innholdet i Fridge og Freezer til filen "FridgeSave.json"       
<u><font size="2">**FoodDeserializer.java:**</font></u> Tar inn JSON-tekst tilhørende food-objekter og omgjør denne til faktiske Food-objekter          
<u><font size="2">**FoodSerializer.java:**</font></u> Tar inn Food-objekter og gjør disse om til JSON-tekst      
<u><font size="2">**FridgeManagerDeserializer.fxml:**</font></u> Tar inn JSON-tekst tilhørende FridgeManager-objekter og omgjør denne til et faktisk FridgeManager-objekt      
<u><font size="2">**FridgeManagerSerializer.java:**</font></u> Tar inn FridgeManager-objekter og gjør disse om til JSON-tekst     
<u><font size="2">**FridgeManagerModule.java:**</font></u> Klasse som knytter Food-klassen til de tilhørende klassene for serialisering (FoodSerializer) og deserialisering (FoodDeserializer). Dette gjør den også for FridgeManager.     
<u><font size="2">**InterfaceFileHandler.java:**</font></u> Interface for FileHandler.java       
<u><font size="2">**FoodTest.java:**</font></u> Klasse for testing av Food-klassen.    
<u><font size="2">**FridgeManagerTest.java:**</font></u> Klasse for testing av FridgeManager-klassen.     
<u><font size="2">**FridgeModuloTest.java:**</font></u> Klasse for testing av serialiseringen og deserialiseringen til JSON-tekst av Food- og FridgeManager-objekter    
