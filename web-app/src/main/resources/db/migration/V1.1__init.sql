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
    primary key (id)
);

create table images
(
    id serial primary key ,
    url varchar not null
)