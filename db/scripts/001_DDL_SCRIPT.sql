CREATE TABLE IF NOT EXISTS car(
                                  id SERIAL PRIMARY KEY ,
                                  NAME varchar(2000)
);

create table if not exists engine(
                                     id serial primary key,
                                     name varchar(2000),
                                     car_id int not null references car(id)
);

create table if not exists driver(
                                     id serial primary key,
                                     name varchar(2000)
);

create table if not exists history_owner(
                                            id serial primary key,
                                            driver_id int not null references driver(id),
                                            car_id int not null references car(id)
);