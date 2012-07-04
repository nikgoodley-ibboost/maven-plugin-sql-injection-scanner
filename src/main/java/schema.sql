CREATE TABLE IF NOT EXISTS url_report (
         id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
         url VARCHAR(100),
         started DATETIME,
         created DATETIME
       );
create table IF NOT EXISTS address_report(
       id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
       ZIP_POSTAL VARCHAR(10)
      );