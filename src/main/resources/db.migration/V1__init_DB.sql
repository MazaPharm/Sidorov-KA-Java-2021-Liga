create table school(
    id integer not null primary key,
    name varchar(255)
);

create table user(
    id integer not null primary key,
    name varchar(255),
    surname varchar(255),
    school_id integer,
    foreign key (school_id) references school (id) on delete set null;
);

create table posts(
    id integer not null primary key,
    content varchar(2048)
);

create table user_posts(
    user_id integer,
    posts_id integer,
    foreign key (user_id) references user (id) on delete cascade,
    foreign key(posts_id) references posts(id),
);

create table friends(
    user_id integer,
    friends_id integer,
    foreign key (user_id) references user (id) on delete cascade,
    foreign  key (friends_id) references user(id)
);
