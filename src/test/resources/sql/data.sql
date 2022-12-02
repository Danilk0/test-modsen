INSERT INTO organizer(id,name)
VALUES (1,'modsen'),
       (2,'inowise'),
       (3,'epam'),
       (4,'issoft');

SELECT SETVAL('organizer_id_seq', (SELECT MAX(id) FROM organizer));

INSERT INTO place(id, address)
VALUES (1,'ItGuru'),
       (2, 'MRK');
SELECT SETVAL('place_id_seq', (SELECT MAX(id) FROM place));


INSERT INTO event(id, title, description, time, organizer_id, place_id)
VALUES (1,'Java meetup','Open java meetup','2022-12-29 18:00:00',1,1),
       (2,'.Net meetup','Open .net meetup','2023-01-15 15:00:00',2,2);
SELECT SETVAL('event_id_seq', (SELECT MAX(id) FROM event));

