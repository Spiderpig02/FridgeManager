**Beskrivelse av klassene i fxui-mappen:**
=
<u><font size="2">**FridgeApp.java:**</font></u> Grunnleggende klasse for å starte JavaFX-appen.    
<u><font size="2">**FridgeController.java:**</font></u> Bindeleddet mellom de andre klassene - denne er selve "limet" i programmet. Administrerer brukergrensnitt og den logiske koblingen mellom Food, FridgeManager og FridgeApp.fxml    
<u><font size="2">**RemoteFridgeAccess.java:**</font></u> Formats and handles HTTP-requests to and from REST-server    
<u><font size="2">**RemoteFridgeApp.java:**</font></u> Lik som FridgeApp, bare at denne kommuniserer med RemoteFridgeController     
<u><font size="2">**RemoteFridgeController.java:**</font></u> Lik som FridgeController, bare at denne kommuiserer med REST-server ved enhver endring av innhold i Fridge eller Freezer.     
<u><font size="2">**FridgeApp.fxml:**</font></u> Inneholder designet av brukergrensesnittet i form av XML-kode.     
<u><font size="2">**FridgeControllerTest.java:**</font></u> Klasse for testing av FridgeController.java. Tester ulike edge-cases for å sørge for at kontrolleren fungerer som den skal til enhver tid.     

