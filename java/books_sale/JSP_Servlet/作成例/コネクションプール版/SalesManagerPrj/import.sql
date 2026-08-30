LOAD DATA LOCAL INFILE '../../projects/SalesManagerPrj/bookitems.csv'
   INTO TABLE items
   FIELDS TERMINATED BY ','
   ENCLOSED BY '"'
   LINES TERMINATED BY '\n';

SELECT COUNT(*) AS 書籍数 FROM items;

LOAD DATA LOCAL INFILE 'C:/Users/PC-003/Desktop/DummyData_for_SalesManager/bookorders35.csv'
   INTO TABLE orders
   FIELDS TERMINATED BY ','
   ENCLOSED BY '"'
   LINES TERMINATED BY '\n';

SELECT COUNT(*) AS 注文数 FROM orders;

DELETE FROM orders;

DELETE FROM items;
