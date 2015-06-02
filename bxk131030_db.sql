drop database AIRLINE;
create database AIRLINE;
use AIRLINE;

create table AIRPORT(
Airport_code varchar(3) PRIMARY KEY,
Name varchar(50),
City varchar(20),
State varchar(2));

create table FLIGHT (
Flight_number INT(4) PRIMARY KEY,
Airline varchar(2),
Weekdays varchar(30),
Departure_Airport_Code VARCHAR(20), 
Scheduled_departure_time TIME, 
Arrival_airport_code VARCHAR(20), 
Scheduled_arrival_time TIME,
Flight_duration varchar (10),
FOREIGN KEY(DEPARTURE_AIRPORT_CODE) REFERENCES AIRPORT(AIRPORT_CODE) ON UPDATE CASCADE, 
FOREIGN KEY(ARRIVAL_AIRPORT_CODE) REFERENCES AIRPORT(AIRPORT_CODE) ON UPDATE CASCADE);

create table AIRPLANE_TYPE (
Airplane_type_name varchar(3) PRIMARY KEY,
Max_seats int(3),
Company varchar(15));

create table FARE(
Flight_number int(4),
Fare_code varchar(20),
Amount FLOAT(4),
Restrictions varchar(20),
PRIMARY KEY (Flight_number,Fare_code,Amount),
FOREIGN KEY(FLIGHT_NUMBER) REFERENCES FLIGHT(FLIGHT_NUMBER));

create table AIRPLANE(
Airplane_id int(4) PRIMARY KEY,
Total_number_of_seats int(2),
Airplane_type varchar(3),
FOREIGN KEY(AIRPLANE_TYPE) REFERENCES AIRPLANE_TYPE(AIRPLANE_TYPE_NAME));

create table FLIGHT_INSTANCE(
Flight_number int(4),
Date date,
Number_of_available_seats int(3),
Airplane_id int(4),
Departure_time TIME,
Arrival_time TIME,
PRIMARY KEY(Flight_number,Date),
FOREIGN KEY(Airplane_id) REFERENCES AIRPLANE(Airplane_id),
FOREIGN KEY(Flight_number) REFERENCES FLIGHT(Flight_number));

CREATE TABLE SEAT_RESERVATION (
FLIGHT_NUMBER INT(4), 
DATE DATE, 
SEAT_NUMBER varchar(3), 
CUSTOMER_NAME VARCHAR(80), 
CUSTOMER_PHONE CHAR(10), 
PRIMARY KEY(FLIGHT_NUMBER, DATE, SEAT_NUMBER),
FOREIGN KEY(FLIGHT_NUMBER, DATE) REFERENCES FLIGHT_INSTANCE(FLIGHT_NUMBER,DATE)
);

CREATE TABLE CAN_LAND (
AIRPLANE_TYPE_NAME VARCHAR(3), 
AIRPORT_CODE VARCHAR(3), 
PRIMARY KEY(AIRPLANE_TYPE_NAME, AIRPORT_CODE), 
FOREIGN KEY(AIRPLANE_TYPE_NAME) REFERENCES AIRPLANE_TYPE(AIRPLANE_TYPE_NAME), 
FOREIGN KEY(AIRPORT_CODE) REFERENCES AIRPORT(AIRPORT_CODE)) ;