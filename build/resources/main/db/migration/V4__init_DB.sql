create table usr(
    id integer not null primary key,
    name varchar(40),
    surname varchar(40),
    school_id integer,
    foreign key (school_id) references school (id) on delete set null
);
