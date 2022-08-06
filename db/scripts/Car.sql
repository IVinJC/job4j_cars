create table if not exists car(
                                  id SERIAL PRIMARY KEY ,
                                  name varchar(250),
                                  describe text,
                                  photo bytea,
                                  id_body int not null references body(id)
);
