create table if not exists model
(
    id      SERIAL PRIMARY KEY,
    name    varchar(250),
    mark_id int NOT NULL references mark(id)
);


