#!/bin/bash
set -e

dt=$(date '+%d/%m/%Y %H:%M:%S');
echo "$dt - Creating replication role...";
echo "$dt - Running: psql -v ON_ERROR_STOP=1 --username $POSTGRES_USER --dbname $POSTGRES_DB ... CREATE ROLE replication WITH REPLICATION PASSWORD ... GRANT EXECUTE ON FUNCTION pg_promote TO replication;";

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<EOSQL
CREATE ROLE replication WITH REPLICATION PASSWORD '$POSTGRES_REPLICATION_PASSWORD' LOGIN;
GRANT EXECUTE ON FUNCTION pg_promote TO replication;
EOSQL

echo "$dt - Replication role created";