create table if not exists author
(
    id    SERIAL PRIMARY KEY,
    name  varchar(250),
    email varchar(250),
    phone varchar(250)
);