#!/bin/bash
set -e

psql -v --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
    CREATE USER wilbur;
    CREATE DATABASE register;
    GRANT ALL PRIVILEGES ON DATABASE wilbur TO register;
    GRANT ALL PRIVILEGES ON DATABASE postgres TO register;
    CREATE TABLE restaurant(
        id SERIAL NOT NULL,
        owner VARCHAR NOT NULL,
        cnpj VARCHAR,
        name VARCHAR NOT NULL,
        data_created TIMESTAMP,
        data_updated TIMESTAMP
    );
    CREATE TABLE location(
        id SERIAL NOT NULL,
        latitude DOUBLE PRECISION,
        longitude DOUBLE PRECISION
    );
    CREATE TABLE dish(
        id SERIAL NOT NULL,
        name VARCHAR NOT NULL,
        description VARCHAR NOT NULL,
        price DECIMAL NOT NULL
    );
EOSQL