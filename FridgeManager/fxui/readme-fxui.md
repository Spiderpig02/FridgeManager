**Beskrivelse av klassene i fxui-modulen:**
=
<u><font size="2">**RemoteFridgeApp.java:**</font></u> Grunnleggende klasse for å starte JavaFX-appen.    
<u><font size="2">**RemoteFridgeController.java:**</font></u> Bindeleddet mellom de andre klassene - denne er selve "limet" i programmet. Administrerer brukergrensnitt og den logiske koblingen mellom RemoteFridgeAccess.java og RemoteFridgeApp.fxml     
<u><font size="2">**RemoteFridgeAccess.java:**</font></u> Denne Formaterer og håndterer HTTP-requestene til og fra REST-API-en. Hjelpeklasse til RemoteFridgeController slik at den ikke trenger å ha HTTP-requests.      
<u><font size="2">**RemoteFridgeApp.fxml:**</font></u> Inneholder designet av brukergrensesnittet i form av XML-kode.     
<u><font size="2">**RemoteFridgeControllerTest.java:**</font></u> Klasse for testing av RemoteFridgeController.java. Tester ulike "edge-cases" for å sørge for at kontrolleren fungerer som den skal til enhver tid.     

