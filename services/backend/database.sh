#!/bin/bash

echo " ---- Deleting docker old containers ----"
docker rm -f job_market_db
docker rm -f job_market_db_local
echo " ---- Running new local db ----"
docker run -d -p 5432:5432 -e 'POSTGRES_DB=JobMarketDB' -e 'POSTGRES_USER=admin' -e 'POSTGRES_PASSWORD=test'  --name job_market_db_local chmiel_db
