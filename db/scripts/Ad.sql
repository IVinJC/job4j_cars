create table if not exists ad
(
    id        SERIAL PRIMARY KEY,
    name varchar(255) NOT NULL,
    photo     bytea,
    description text NOT NULL,
    mark_id   int not null references mark (id),
    model_id  int not null references model (id),
    body_id   int not null references body (id),
    author_id int not null references author (id),
    price     int
);