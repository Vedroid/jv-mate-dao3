CREATE DATABASE mate
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Russian_Ukraine.1251'
    LC_CTYPE = 'Russian_Ukraine.1251'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

ALTER DATABASE mate OWNER TO postgres;

\connect mate

CREATE SCHEMA taxi_service
    AUTHORIZATION postgres;

GRANT ALL ON SCHEMA taxi_service TO PUBLIC;

GRANT ALL ON SCHEMA taxi_service TO postgres;

CREATE TABLE taxi_service.manufacturers
(
    manufacturer_id      bigserial             NOT NULL,
    manufacturer_name    character varying     NOT NULL,
    manufacturer_country character varying     NOT NULL,
    is_deleted           boolean DEFAULT false NOT NULL,
    PRIMARY KEY (manufacturer_id)
);

ALTER TABLE taxi_service.manufacturers
    OWNER TO postgres;
