create table vehicle
(
   registration_number varchar(255) not null,
   make varchar(255) not null,
   model varchar(255) not null,
   year varchar(4) not null,
   description varchar(255) not null,
   primary key(registration_number)
);

create table vehicle_insurance
(
   registration_number varchar(255) not null,
   policy_number varchar(255) not null,
   start_date date not null,
   end_date date not null, 
   PRIMARY KEY (registration_number,policy_number)
);