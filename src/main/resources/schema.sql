CREATE TABLE IF NOT EXISTS customer (
	id INTEGER PRIMARY KEY AUTOINCREMENT,
    name VARCHAR(128) NOT NULL,
    phone VARCHAR(20) NOT NULL
);


CREATE TABLE IF NOT EXISTS bill (
	id INTEGER PRIMARY KEY AUTOINCREMENT,
	phone VARCHAR(20) NOT NULL,
    status VARCHAR(1) NOT NULL,
    amount INTEGER  NOT NULL,
    due_date INTEGER  NOT NULL
);

CREATE TABLE IF NOT EXISTS cust_transaction (
	id VARCHAR(20) PRIMARY KEY,
	trans_id VARCHAR(20) NOT NULL,
	ref_id INTEGER NOT NULL,
	amount INTEGER  NOT NULL,
    pay_date INTEGER  NOT NULL
);