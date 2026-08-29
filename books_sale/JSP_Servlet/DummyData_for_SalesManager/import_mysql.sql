LOAD DATA LOCAL INFILE 'C:/MISA2025/projects/SalesManagerPrj/bookitems.csv'
   INTO TABLE items
   FIELDS TERMINATED BY ','
   ENCLOSED BY '"'
   LINES TERMINATED BY '\n';

SELECT COUNT(*) AS 書籍数 FROM items;

LOAD DATA LOCAL INFILE 'C:/MISA2025/projects/SalesManagerPrj/bookorders.csv'
   INTO TABLE orders
   FIELDS TERMINATED BY ','
   ENCLOSED BY '"'
   LINES TERMINATED BY '\n';

SELECT COUNT(*) AS 注文数 FROM orders;

DELETE FROM orders;

DELETE FROM items;
