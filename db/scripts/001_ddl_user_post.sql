create table if not exists auto_user
(
    id serial primary key ,
    login varchar(20),
    password varchar(30)
);

create table if not exists auto_post
(
    id           serial primary key,
    text         text,
    created      timestamp,
    auto_user_id int references auto_user (id)
);


