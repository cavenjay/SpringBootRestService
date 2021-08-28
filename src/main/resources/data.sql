DROP TABLE IF EXISTS storage_test;

CREATE TABLE storage_test (book_name varchar(50),id varchar(50) PRIMARY KEY,

isbn varchar(50),

aisle int,

author varchar(50));

INSERT INTO storage_test (book_name, id, isbn, aisle, author) VALUES
  ('Cyber', '12', 'fdsefr3', '43', 'Banana'),
  ('Bay', '123', 'fdsefr3', '43', 'Banana'),
  ('Dog', '1234', 'fdsefr3', '43', 'Banana'),
  ('Cat', '1603', 'fdsefr3', '16', 'Caven');
