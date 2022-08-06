create table if not exists ad
(
    id      SERIAL PRIMARY KEY,
    content text,
    isSold  boolean,
    data    timestamp,
    car_id  int not null unique references car(id),
    author_id int not null references author (id)
);
