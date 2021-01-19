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
    id         bigserial             NOT NULL,
    name       character varying     NOT NULL,
    country    character varying     NOT NULL,
    is_deleted boolean DEFAULT false NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE taxi_service.manufacturers
    OWNER TO postgres;

CREATE TABLE taxi_service.cars
(
    id              bigserial             NOT NULL,
    manufacturer_id bigint                NOT NULL,
    model           character varying     NOT NULL,
    is_deleted      boolean DEFAULT false NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (manufacturer_id) REFERENCES taxi_service.manufacturers (id)
);

ALTER TABLE taxi_service.cars
    OWNER to postgres;

CREATE TABLE taxi_service.drivers
(
    id             bigserial             NOT NULL,
    name           character varying     NOT NULL,
    licence_number character varying     NOT NULL,
    is_deleted     boolean DEFAULT false NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE taxi_service.drivers
    OWNER to postgres;

CREATE TABLE taxi_service.cars_drivers
(
    car_id    bigint,
    driver_id bigint,
    CONSTRAINT car_fkey FOREIGN KEY (car_id) REFERENCES taxi_service.cars (id),
    CONSTRAINT driver_fkey FOREIGN KEY (driver_id) REFERENCES taxi_service.drivers (id)
);

ALTER TABLE taxi_service.cars_drivers
    OWNER to postgres;
