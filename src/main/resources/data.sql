INSERT INTO TIP (id,title,author,description,url,created) VALUES (1000,'Testaus', 'Alex Samurin', 'Any test that passed in unit testing can fail in system testing.', 'extremesoftwaretesting.com/Humor/SoftwareTestingJokes2.html',TO_DATE('07/02/2018', 'DD/MM/YYYY'));

INSERT INTO TIP (id,title,author,description,url,created) VALUES (1001, 'If it hurts, do it more often', 'Matti', 'Adding manpower to a late software project makes it later!', 'https://fullstack-hy.github.io/',TO_DATE('17/11/2018', 'DD/MM/YYYY'));
INSERT INTO TIP (id,title,author,description,url,created) VALUES (1002,'TIETOKANTAAN TALLENNETTAVAT OLIOT ELI ENTITEETIT', 'Arto', 'Luokat, joista tehdyt oliot voidaan tallentaa tietokantaan, tulee annotoida @Entity-annotaatiolla.', 'http://web-palvelinohjelmointi.github.io/osa1.html',TO_DATE('01/08/2018', 'DD/MM/YYYY'));
INSERT INTO TIP (id,title,author,description,url,created) VALUES (1003,'INTERNETIN PERUSOSAT', 'Arto', 'Internetin peruskomponentit ovat (1) palveluiden, palvelinohjelmistojen ja resurssien yksilöintiin käytetyt merkkijonomuotoiset osoitteet (URI...', 'http://web-palvelinohjelmointi.github.io/osa2.html',TO_DATE('11/09/2018', 'DD/MM/YYYY'));
INSERT INTO TIP (id,title,author,description,url,created) VALUES (1004,'HTTP: SELAINTEN JA PALVELINTEN VÄLINEN KOMMUNIKAATIOPROTOKOLLA', 'Arto', 'HTTP (HyperText Transfer Protocol) on TCP/IP -protokolla...', 'http://web-palvelinohjelmointi.github.io/osa2.html',TO_DATE('10/11/2018', 'DD/MM/YYYY'));
INSERT INTO TIP (id,title,author,description,url,created) VALUES (1005,'HTTP-VIESTIN RAKENNE PALVELIMELTA SAAPUVA VASTAUS', 'Arto', 'Palvelimelle tehtyyn pyyntöön saadaan aina jonkinlainen vastaus.', 'http://web-palvelinohjelmointi.github.io/osa2.html',TO_DATE('10/10/2018', 'DD/MM/YYYY'));
INSERT INTO TIP (id,title,author,description,url,created) VALUES (1006,'The first 90 percent of the code accounts for the first 90 percent of the development time...The remaining 10 percent of the code accounts for the other 90 percent of the development time.', 'Matti', 'Any fool can write code that a computer can understand. Good programmers write code that humans can understand.', 'https://fullstack-hy.github.io/',TO_DATE('11/10/2015', 'DD/MM/YYYY'));
INSERT INTO TIP (id,title,author,description,url,created) VALUES (1007,'Premature optimization is the root of all evil.', 'Matti', 'Debugging is twice as hard as writing the code in the first place. Therefore, if you write the code as cleverly as possible, you are, by definition, not smart enough to debug it.', 'https://fullstack-hy.github.io/',TO_DATE('12/10/2017', 'DD/MM/YYYY'));
INSERT INTO TIP (id,title,author,description,url,created) VALUES (1008,'Model-view-controller', 'Wikipedia','Diagram of interactions within the MVC pattern.
Model–view–controller is an architectural pattern commonly used for developing user interfaces that divides an application into three interconnected parts.', 'https://en.wikipedia.org/wiki/Model-view-controller',TO_DATE('17/12/2015', 'DD/MM/YYYY'));
INSERT INTO TIP (id,title,author,description,url,created) VALUES (1009,'asddasdas', 'asddasdas', 'asddas', 'http://www.google.fi',TO_DATE('17/12/2016', 'DD/MM/YYYY'));

INSERT INTO TAG(id,name) VALUES (1001, 'Wepa');
INSERT INTO TAG(id,name) VALUES (1002, 'Fullstack');
INSERT INTO TAG(id,name) VALUES (1003, 'Anecdotes');

INSERT INTO TIP_TAGS(tip_id, tag_id) VALUES(1000,1001);
INSERT INTO TIP_TAGS(tip_id, tag_id) VALUES(1000,1002);
INSERT INTO TIP_TAGS(tip_id, tag_id) VALUES(1001,1003);
INSERT INTO TIP_TAGS(tip_id, tag_id) VALUES(1002,1003);
INSERT INTO TIP_TAGS(tip_id, tag_id) VALUES(1003,1001);
INSERT INTO TIP_TAGS(tip_id, tag_id) VALUES(1004,1001);
INSERT INTO TIP_TAGS(tip_id, tag_id) VALUES(1005,1001);
INSERT INTO TIP_TAGS(tip_id, tag_id) VALUES(1006,1002);
INSERT INTO TIP_TAGS(tip_id, tag_id) VALUES(1007,1002);
INSERT INTO TIP_TAGS(tip_id, tag_id) VALUES(1008,1003);
INSERT INTO TIP_TAGS(tip_id, tag_id) VALUES(1009,1001);
INSERT INTO TIP_TAGS(tip_id, tag_id) VALUES(1009,1002);
