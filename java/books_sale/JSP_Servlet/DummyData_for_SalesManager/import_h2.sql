●H2
INSERT INTO items SELECT * FROM CSVRead('C:\java\db\bookitems.csv', null, 'UTF-8', ',');
INSERT INTO orders SELECT * FROM CSVRead('C:\java\db\bookorders35.csv', null, 'UTF-8', ',');

※2つめの引数をnullにすると1行目がインポートされないから注意して
