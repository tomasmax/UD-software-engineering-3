CREATE DATABASE IF NOT EXISTS hibejcc;
USE hibejcc;

--
-- Create USERS
--

GRANT ALTER, SELECT,INSERT,UPDATE,DELETE,CREATE,DROP
           ON hibejcc.*
           TO eside@'%'
           IDENTIFIED BY 'eside';

GRANT ALTER, SELECT,INSERT,UPDATE,DELETE,CREATE,DROP
           ON hibejcc.*
           TO eside@localhost
           IDENTIFIED BY 'eside';