/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  x00131205
 * Created: 21-Nov-2016
 */
drop table booking;
drop table ticket;
drop table users;
drop table boatroute;
drop table route;
drop table boat;

create table boat(
boat_ID number,
boat_type varchar2(255),
capacity number,
Primary Key(boat_id)
);

create table route(
route_ID number,
route_name varchar2(255),
route_journey_time number,
Primary Key(route_id)
);

create table boatroute(
boat_ID number,
route_ID number,
Primary Key(boat_id, route_ID),
Foreign Key(boat_ID) references boat on delete set null,
Foreign Key(route_ID) references route on delete set null
);

create table users(
user_ID number,
username varchar2(255),
name varchar2(255),
DOB Date,
Primary Key(user_id)
);

create table ticket(
ticket_ID number,
ticket_type varchar2(255),
ticket_cost number,
route_ID number,
Primary Key(ticket_id),
Foreign Key(route_ID) references route on delete set null
);

create table Booking(
user_ID number,
ticket_ID number,
Primary Key(user_ID,ticket_id),
Foreign Key(user_ID) references users on delete set null,
Foreign Key(ticket_ID) references ticket on delete set null
);


INSERT INTO ROUTE VALUES(1,'NAME',2);

INSERT INTO TICKET VALUES(1,'BIGGGGGGG',500,1);
INSERT INTO TICKET VALUES(2,'BIGGGGG',600,1);
INSERT INTO TICKET VALUES(3,'BIGGGG',5060,1);
INSERT INTO TICKET VALUES(4,'BIGG',50660,1);

INSERT INTO USERS VALUES(1,'crx','craigo','21-JAN-2015');
INSERT INTO USERS VALUES(2,'crq','craig','21-JAN-2016');