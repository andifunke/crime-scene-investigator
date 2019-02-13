# Crime Scene Investigator v1.1


Benötigt wird eine Java Runtime 8 mit Java FX 2.2.
Die externe JDBC-Bibliothek ist nun bereits im JAR-Archiv enthalten.
Der Bilder-Ordner img/ und die Datenbank crime.db müssen sich im gleichen Verzeichnis befinden wie das JAR-File.

starten unter Windows:
- Doppelklick auf dbs4.jar

starten unter Linux:
- von der Konsole: java -jar dbs4.jar
(bei Start über Doppelklick ist es möglich, dass der Pfad zur Datenbank nicht korrekt interpretiert wird.)



Bedienungsanleitung:

1)
Über die Tabs werden die Relationen ausgewählt.
Die Relationen wurden in sinnvolle Gruppen zusammen geführt.

2)
Im unteren Bereich (Editor) können Werte geändert werden (Textfelder links)
oder die Tabelle nach Werten gefiltert werden (Textfelder rechts).

3)
Ein neuer Eintrag kann über "Neu..." erstellt werden.
Einträge werden über "Speichern..." in die Datenbank geschrieben.
Pflichtfelder müssen gesetzt sein, sonst wird der gesamte Eintrag nicht gespeichert.
"Löschen..." löscht den ausgewählten Eintrag.
