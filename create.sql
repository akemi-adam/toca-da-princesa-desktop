
drop database if exists db_tocadaprincesa;

create database if not exists db_tocadaprincesa;

use db_tocadaprincesa;

create table if not exists animals(
	id int primary key auto_increment,
    name varchar(255) not null,
    age int not null,
    sick int not null default 0,
    owner varchar(255) not null
);

create table if not exists cats(
	id int primary key auto_increment,
    blue_eye int not null default 0,
    animal_id int,
    foreign key (animal_id) references animals (id)
);

create table if not exists dogs(
	id int primary key auto_increment,
    pedrigree int not null default 0,
    animal_id int,
    foreign key (animal_id) references animals (id)
);

create table if not exists employees(
	id int primary key auto_increment,
    name varchar(255) not null,
    age int not null,
    cpf varchar(15) not null unique,
    password varchar(256) not null
);

create table if not exists vets(
	id int primary key auto_increment,
    name varchar(255) not null,
    specialty varchar (50)
);

create table if not exists schedules(
	animal_id int,
    employee_id int,
    entry_date date not null,
    exit_date date,
    vet_id int,
    foreign key (animal_id) references animals (id),
    foreign key (employee_id) references employees (id),
    foreign key (vet_id) references vets (id),
    primary key (animal_id, employee_id)
);