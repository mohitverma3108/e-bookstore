-- Inserting static data into book_type table
INSERT INTO book_type(id,type_name,total_discount) VALUES (11, 'fiction', 10);
INSERT INTO book_type(id,type_name,total_discount) VALUES (12, 'comic', 0);
INSERT INTO book_type(id,type_name,total_discount) VALUES (13, 'graphic', 5);
INSERT INTO book_type(id,type_name,total_discount) VALUES (14, 'historical', 15);
INSERT INTO book_type(id,type_name,total_discount) VALUES (15, 'others', 20);

-- Inserting static data into promotion table
INSERT INTO promotion(id,prom_cd,prom_valid) VALUES (101, 'ABC123', 'Y');
INSERT INTO promotion(id,prom_cd,prom_valid) VALUES (102, 'BCD234', 'Y');
INSERT INTO promotion(id,prom_cd,prom_valid) VALUES (103, 'CDE345', 'Y');
INSERT INTO promotion(id,prom_cd,prom_valid) VALUES (104, 'DEF456', 'Y');
INSERT INTO promotion(id,prom_cd,prom_valid) VALUES (105, 'EFG567', 'N');
INSERT INTO promotion(id,prom_cd,prom_valid) VALUES (106, 'FGH678', 'N');