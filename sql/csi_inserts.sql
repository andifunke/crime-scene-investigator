INSERT INTO Faelle VALUES (101, 'Cocktail für eine Leiche', '2015-06-23', NULL);
INSERT INTO Faelle VALUES (102, 'Eine Leiche zum Dessert', '2015-08-28', '2015-08-29');

INSERT INTO Personen VALUES (201, 'Peter Pan', 'm', 'de', '1977-11-11', '2015-06-23');
INSERT INTO Personen VALUES (202, 'Beate Bohlen', 'w', 'de', '1966-12-13', '2015-08-28');
INSERT INTO Personen VALUES (203, 'Mr. X', 'u', NULL, NULL, NULL);
INSERT INTO Personen VALUES (204, 'Trudi Hubndudl', 'w', 'at', '1988-09-09', NULL);
INSERT INTO Personen VALUES (205, 'Karl-Heinz Krause', 'm', 'de', '1955-12-21', NULL);

INSERT INTO Verdaechtige VALUES (203);
INSERT INTO Verdaechtige VALUES (205);

INSERT INTO Opfer VALUES (201);
INSERT INTO Opfer VALUES (202);

INSERT INTO Polizisten VALUES (204, 'Polizeikommissaranwärter');
INSERT INTO Polizisten VALUES (205, 'Wachtmeister');

INSERT INTO Arten VALUES ('Mord','Mord steht allgemein für ein vorsätzliches Tötungsdelikt, dem gesellschaftlich ein besonderer Unwert zugeschrieben wird.');
INSERT INTO Arten VALUES ('Totschlag','Totschlag bezeichnet im deutschen Strafrecht die vorsätzliche Tötung eines Menschen, die weder die Strafdrohung erhöhenden Kriterien für Mord noch die privilegierenden und damit die Strafdrohung mindernden für eine Tötung auf Verlangen erfüllt.');
INSERT INTO Arten VALUES ('Diebstahl','Diebstahl ist eine gegen fremdes Eigentum gerichtete Straftat.');

INSERT INTO Bezirke VALUES (301, 'Hintertupflingen', 'Gemeinde');
INSERT INTO Bezirke VALUES (302, 'Obergammelau', 'Landkreis');
INSERT INTO Bezirke VALUES (303, 'Oberunterbeuern', 'Regierungsbezirk');

INSERT INTO Verbrechen VALUES (400, 'Peter Pan wurde der Schlüsselbund gestohlen.', '2015-06-23', 101, 'Diebstahl', 301);
INSERT INTO Verbrechen VALUES (401, 'Peter Pan wurde im Salon ermordet.', '2015-06-23', 101, 'Mord', 301);
INSERT INTO Verbrechen VALUES (402, 'Beate Bohlen tot im Esszimmer aufgefunden.', '2015-08-28', 102, 'Totschlag', 302);

INSERT INTO Behoerden VALUES (501, 'Hintertupflinger Ortspolizei', 'Polizeistation', 301);
INSERT INTO Behoerden VALUES (502, 'Obergammelauer Polizeidirektion', 'Polizeidirektion', 302);

INSERT INTO Zeitraeume VALUES (600, 1990-01-01, 1999-12-31, 501, 205);
INSERT INTO Zeitraeume VALUES (601, 2000-03-01, 2015-08-31, 501, 205);
INSERT INTO Zeitraeume VALUES (602, 2015-01-01, NULL, 502, 204);

INSERT INTO Notizen VALUES (700, '2015-06-23', 'Mr. X wurde beobachtet, wie er sich in der Nähe von Peter Pan aufgehalten hat, kurz bevor diesem die Hausschlüssel gestohlen wurden.', 205, 101);
INSERT INTO Notizen VALUES (701, '2015-06-24', 'Bestimmt hat Mr. X Peter Pan im Salon mit dem Seil ermordet.', 205, 101);
INSERT INTO Notizen VALUES (702, '2015-08-28', 'Wachtmeister Krause wurde am Tatort gesehen.', 204, 102);

INSERT INTO Indizien VALUES (801, '2015-06-24', NULL, 'das Seil', 205, 101);
INSERT INTO Indizien VALUES (802, '2015-08-28', NULL, 'Wachtmeister Krause', 204, 102);

INSERT INTO betrifftV VALUES (101, 203, 0);
INSERT INTO betrifftV VALUES (102, 205, 1);

INSERT INTO betrifftO VALUES (101, 201);
INSERT INTO betrifftO VALUES (102, 202);

INSERT INTO liegtin VALUES (301, 302);
INSERT INTO liegtin VALUES (302, 303);

INSERT INTO arbeitetan VALUES (205, 101, 2015-06-24, NULL);
INSERT INTO arbeitetan VALUES (204, 102, 2015-08-28, 2015-09-11);
