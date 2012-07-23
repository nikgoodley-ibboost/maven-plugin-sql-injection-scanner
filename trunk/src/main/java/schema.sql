CREATE TABLE IF NOT EXISTS url_report (
         id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
         url VARCHAR(100),
         started DATETIME,
         created DATETIME
       );

CREATE TABLE IF NOT EXISTS url (
         id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
         url_string VARCHAR(100),
         email_string VARCHAR(100),
         created_date DATETIME,
         log_file_name VARCHAR(100)
       );

create table IF NOT EXISTS address_report(
       id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
       ZIP_POSTAL VARCHAR(10)
      );