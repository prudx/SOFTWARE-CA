/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  x00131205
 * Created: 21-Nov-2016
 */
drop table ferryroute;
drop table booking;
drop table ticket;
drop table users;
drop table route;
drop table ferry;
drop sequence uid_seq;
drop sequence tid_seq;

create sequence uid_seq increment by 1 start with 3;
create sequence tid_seq increment by 1 start with 4;

create table ferry(
ferry_ID number,
ferry_type varchar2(255),
capacity number,
Primary Key(ferry_id)
);

create table route(
route_ID number,
route_name varchar2(255),
route_time number,
Primary Key(route_id)
);

create table ferryroute(
ferry_ID number,
route_ID number,
Primary Key(ferry_id, route_ID),
Foreign Key(ferry_ID) references ferry on delete cascade,
Foreign Key(route_ID) references route on delete cascade
);

create table users(
user_ID number,
username varchar2(255),
password varchar2(255),
name varchar2(255),
DOB Date,
Primary Key(user_id)
);

create table ticket(
ticket_ID number,
depDate date,
returnDate date,
route_ID number,
Primary Key(ticket_id),
Foreign Key(route_ID) references route on delete cascade
);

create table Booking(
user_ID number,
ticket_ID number,
Primary Key(user_ID,ticket_id),
Foreign Key(user_ID) references users on delete cascade,
Foreign Key(ticket_ID) references ticket on delete cascade
);


INSERT INTO ROUTE VALUES(1,'Dublin-Hollyhead',120);
INSERT INTO ROUTE VALUES(2,'Dublin-Liverpool',300);
INSERT INTO ROUTE VALUES(3,'Dublin-Manchester',420);
INSERT INTO ROUTE VALUES(4,'Belfast-Hollyhead',240);
INSERT INTO ROUTE VALUES(5,'Belfast-Liverpool',240);
INSERT INTO ROUTE VALUES(6,'Belfast-Manchester',360);
INSERT INTO ROUTE VALUES(7,'Rosslare-Pembroke',300);
INSERT INTO ROUTE VALUES(8,'Rosslare-Fishguardl',360);

INSERT INTO USERS VALUES(1,'Crx','password','Craig','08-JAN-1997');
INSERT INTO USERS VALUES(2,'prudx','password','Daniel','04-APR-1997');

INSERT INTO FERRY VALUES(1,'Small ferry',1);
INSERT INTO FERRY VALUES(2,'Big ferry',2);

INSERT INTO FERRYROUTE VALUES(1,1);
INSERT INTO FERRYROUTE VALUES(2,2);

INSERT INTO TICKET VALUES(1,'20-DEC-2017','18-JAN-2018',1);
INSERT INTO TICKET VALUES(2,'02-MAR-2007','23-APR-2017',1);
INSERT INTO TICKET VALUES(3,'08-JAN-2017','02-FEB-2017',3);

INSERT INTO BOOKING VALUES(1,1);
INSERT INTO BOOKING VALUES(1,2);
INSERT INTO BOOKING VALUES(2,3);