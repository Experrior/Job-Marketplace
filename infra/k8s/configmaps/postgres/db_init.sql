-- CONFIGURE REPLICATION
DROP USER IF EXISTS "replication"
CREATE USER 'replication' WITH REPLICATION ENCRYPTED PASSWORD 'replicator_password';

SELECT pg_create_physical_replication_slot('replication_slot');