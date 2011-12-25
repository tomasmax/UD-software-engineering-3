CREATE DATABASE IF NOT EXISTS 'PTDB';

--
-- Create USERS
--

CREATE USER eside@localhost IDENTIFIED BY 'user1';

GRANT ALL
           ON 'ptdb'.*
           TO eside@localhost
           IDENTIFIED BY 'user1';