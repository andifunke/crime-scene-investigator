SELECT DISTINCT name
FROM   polizisten NATURAL JOIN personen
WHERE  personid IN (SELECT personid
                    FROM   polizisten NATURAL JOIN zeitraeume
                    GROUP BY behoerdeid
                    HAVING COUNT(behoerdeid) = 2);


SELECT DISTINCT f.fallid, f.name, f.eroeffnungsdatum, f.enddatum
FROM   faelle f, verbrechen v
WHERE  f.fallid = v.fallid
  AND  v.artname = 'Mord';


SELECT DISTINCT *
FROM   bezirke
WHERE  bezirkid in (SELECT bezirkid
                    FROM   verbrechen
                    GROUP BY bezirkid
                    HAVING COUNT(bezirkid) > 1);
