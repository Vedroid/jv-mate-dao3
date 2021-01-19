TRUNCATE TABLE taxi_service.manufacturers RESTART IDENTITY CASCADE;
TRUNCATE TABLE taxi_service.cars RESTART IDENTITY CASCADE;
TRUNCATE TABLE taxi_service.drivers RESTART IDENTITY CASCADE;

INSERT INTO taxi_service.manufacturers(name, country) VALUES ('Audi', 'Germany');
INSERT INTO taxi_service.manufacturers(name, country) VALUES ('Volkswagen', 'Germany');
INSERT INTO taxi_service.manufacturers(name, country) VALUES ('Skoda', 'USA');
INSERT INTO taxi_service.manufacturers(name, country) VALUES ('Tesla', 'USA');

insert into cars (manufacturer_id, model) values (1, 'A4');
insert into cars (manufacturer_id, model) values (1, 'S4');
insert into cars (manufacturer_id, model) values (1, 'A6');
insert into cars (manufacturer_id, model) values (1, 'Q5');
insert into cars (manufacturer_id, model) values (2, 'Jetta');
insert into cars (manufacturer_id, model) values (2, 'Golf');
insert into cars (manufacturer_id, model) values (2, 'Passat');
insert into cars (manufacturer_id, model) values (3, 'Superb');
insert into cars (manufacturer_id, model) values (3, 'Fabia');
insert into cars (manufacturer_id, model) values (3, 'Rapid');
insert into cars (manufacturer_id, model) values (3, 'Octavia');
insert into cars (manufacturer_id, model) values (4, 'Model S');
insert into cars (manufacturer_id, model) values (4, 'Cybertruck');
insert into cars (manufacturer_id, model) values (4, 'Model X');
insert into cars (manufacturer_id, model) values (4, 'Model Y');

insert into taxi_service.drivers (name, licence_number) values ('Delcina', '87-400-6368');
insert into taxi_service.drivers (name, licence_number) values ('Alwin', '64-385-3276');
insert into taxi_service.drivers (name, licence_number) values ('Cchaddie', '48-463-1860');
insert into taxi_service.drivers (name, licence_number) values ('Shelby', '85-916-9527');
insert into taxi_service.drivers (name, licence_number) values ('Frederich', '57-196-6738');
insert into taxi_service.drivers (name, licence_number) values ('Netta', '97-662-7244');
insert into taxi_service.drivers (name, licence_number) values ('Fonzie', '79-899-3692');
insert into taxi_service.drivers (name, licence_number) values ('Leo', '44-209-3927');
insert into taxi_service.drivers (name, licence_number) values ('Husain', '73-399-2004');
insert into taxi_service.drivers (name, licence_number) values ('Matthieu', '31-234-1034');
insert into taxi_service.drivers (name, licence_number) values ('Claudetta', '35-604-8807');
insert into taxi_service.drivers (name, licence_number) values ('Kelcey', '42-877-5316');
insert into taxi_service.drivers (name, licence_number) values ('Lynnette', '97-814-8878');
insert into taxi_service.drivers (name, licence_number) values ('Paloma', '80-944-9162');
insert into taxi_service.drivers (name, licence_number) values ('Tami', '62-265-1239');


insert into taxi_service.cars_drivers (car_id, driver_id) values (1, 1);
insert into taxi_service.cars_drivers (car_id, driver_id) values (1, 2);
insert into taxi_service.cars_drivers (car_id, driver_id) values (1, 3);
insert into taxi_service.cars_drivers (car_id, driver_id) values (1, 4);
insert into taxi_service.cars_drivers (car_id, driver_id) values (1, 5);
insert into taxi_service.cars_drivers (car_id, driver_id) values (2, 1);
insert into taxi_service.cars_drivers (car_id, driver_id) values (2, 2);
insert into taxi_service.cars_drivers (car_id, driver_id) values (2, 3);
insert into taxi_service.cars_drivers (car_id, driver_id) values (3, 3);
insert into taxi_service.cars_drivers (car_id, driver_id) values (3, 4);
insert into taxi_service.cars_drivers (car_id, driver_id) values (3, 5);
insert into taxi_service.cars_drivers (car_id, driver_id) values (5, 5);
insert into taxi_service.cars_drivers (car_id, driver_id) values (6, 7);
insert into taxi_service.cars_drivers (car_id, driver_id) values (6, 8);
