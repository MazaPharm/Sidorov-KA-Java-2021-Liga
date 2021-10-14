create table usr_posts(
    user_id integer,
    posts_id integer,
    foreign key (user_id) references usr (id) on delete cascade,
    foreign key(posts_id) references posts(id),
    primary key (user_id,posts_id)
);

create table friends(
    user_id integer,
    friends_id integer,
    foreign key (user_id) references usr (id) on delete cascade,
    foreign  key (friends_id) references usr (id),
    primary key (user_id, friends_id)
);
