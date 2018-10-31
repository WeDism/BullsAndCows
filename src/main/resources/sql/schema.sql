DROP TABLE IF EXISTS game CASCADE;
DROP TABLE IF EXISTS step_game CASCADE;
DROP TABLE IF EXISTS user_entity CASCADE;

create table user_entity (
  nickname varchar(255) not null,
  email    varchar(255) not null unique,
  password varchar(200) not null,
  rating   float4       not null,
  role     varchar(255) not null,
  primary key (nickname)
);
create table game (
  id              uuid not null,
  game_end_time   timestamp,
  game_start_time timestamp,
  riddle          varchar(255),
  user_nickname   varchar(255) references user_entity (nickname),
  primary key (id)
);
create table step_game (
  id        uuid not null,
  answer    varchar(255),
  date_time timestamp,
  game_id   uuid not null references game (id),
  primary key (id)
);
