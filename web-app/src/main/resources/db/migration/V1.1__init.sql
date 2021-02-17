create table users
(
    id BIGSERIAL,
    name varchar not null,
    email varchar not null,
    image_url varchar,
    email_verified boolean default false,
    password varchar not null ,
    provider varchar,
    provider_id varchar,
    date_created timestamp without time zone DEFAULT now(),
    date_updated timestamp without time zone DEFAULT now(),
    is_deleted   boolean NOT NULL            DEFAULT FALSE,
    primary key (id)
);

create table folder
(
    id serial primary key ,
    user_id bigint not null,
    name varchar,
    date_created timestamp without time zone DEFAULT now(),
    date_updated timestamp without time zone DEFAULT now(),
    is_deleted   boolean NOT NULL            DEFAULT FALSE,
    FOREIGN KEY (user_id) REFERENCES users (id)
);

create table image
(
    id serial primary key ,
    original_image_id int,
    url varchar not null,
    name varchar,
    width integer not null,
    height integer not null,
    user_id bigint not null,
    folder_id integer not null ,
    date_created timestamp without time zone DEFAULT now(),
    date_updated timestamp without time zone DEFAULT now(),
    is_deleted   boolean NOT NULL            DEFAULT FALSE,
    FOREIGN KEY (original_image_id) REFERENCES image (id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (folder_id) REFERENCES folder (id)
);



