# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table book (
  id                            bigint auto_increment not null,
  title                         varchar(255),
  author                        varchar(255),
  description                   varchar(255),
  constraint pk_book primary key (id)
);


# --- !Downs

drop table if exists book;

