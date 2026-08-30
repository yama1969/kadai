DROP DATABASE BookOrder;

CREATE DATABASE BookOrder DEFAULT CHARACTER SET utf8mb4;

USE BookOrder;

CREATE TABLE items(
  code CHAR(4) PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  price INT NOT NULL
) DEFAULT CHARSET=utf8mb4;

CREATE TABLE orders(
  no INT PRIMARY KEY,
  orderdate DATETIME NOT NULL,
  itemCode CHAR(4),
  quantity INT NOT NULL,
  sei VARCHAR(50) NOT NULL,
  mei VARCHAR(50) NOT NULL,
  pref VARCHAR(4) NOT NULL,
  address VARCHAR(200) NOT NULL,
  tel VARCHAR(25),
  mail VARCHAR(250),
  FOREIGN KEY(itemCode) REFERENCES items(code)
) DEFAULT CHARSET=utf8mb4;

INSERT INTO items(code, name, price) VALUES('P001', 'HTML/CSS/JavaScript', 2460);
INSERT INTO items(code, name, price) VALUES('P002', 'Java言語の基本', 2880);
INSERT INTO items(code, name, price) VALUES('P003', '基本情報技術者試験対策', 1880);

INSERT INTO orders(no,orderdate,itemCode,quantity,sei,mei,pref,address,tel,mail)
 VALUES(1, '2025-05-18 13:35:22', 'P001', 1, '鈴木', '宏', '宮城県', '仙台市青葉区', '0222222222', null);

INSERT INTO orders(no,orderdate,itemCode,quantity,sei,mei,pref,address,tel,mail)
 VALUES(2, '2025-05-19 10:18:43', 'P003', 2, '木田', '幸治', '宮城県', '仙台市太白区', '0223333333', null);

INSERT INTO orders(no,orderdate,itemCode,quantity,sei,mei,pref,address,tel,mail)
 VALUES(3, '2025-05-21 18:26:06', 'P003', 1, '田村', '康', '宮城県', '仙台市若林区', '0224444444', null);

-- 動作確認--------------------------------------------------------------------------------------------------------
-- INSERT INTO items(code, name, price) VALUES('P003', '基礎からのサーブレット', 1880);

-- INSERT INTO orders(no,orderdate,itemCode,quantity,sei,mei,pref,address,tel,mail)
-- VALUES(1,now(),'P005',1,'山田','洋','宮城県','仙台市青葉区花京院1-3-1','022-222-2222','h.yamada@jc-21.co.jp');

-- INSERT INTO orders(no,orderdate,itemCode,quantity,sei,mei,pref,address,tel,mail)
-- VALUES(1,now(),'P003',1,'山田','洋','宮城県','仙台市青葉区花京院1-3-1','022-222-2222','h.yamada@jc-21.co.jp');

