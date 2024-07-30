/******************************* Replication ********************************
 * psql 文档: https://www.postgresql.org/docs/current/app-psql.html
 * */



-- \c 或 \connect 是 psql 的命令，用于切换 数据库 和 用户，不切换默认为 compose 中配置的 user 和 dbname
\connect test k8s_rw
\conninfo

-- 创建 schema，这里定义成跟 dbname 相同，这样更符合 mysql 的习惯
-- create schema if not exists test authorization k8s_rw;


-- 数据库所有表的创建模板
drop table if exists "example";
create table if not exists "example" (
    id bigint primary key ,
    creator_id bigint not null ,
    create_time timestamptz not null default now(),
    updater_id bigint not null,
    update_time timestamptz not null default now(),
    version integer not null default 0 ,
    deleted char(1) not null default 'n' ,
    priv_creator_hgid bigint not null ,

    custom_field bigint not null default null
);

-- 用户表
drop table if exists "user";
create table if not exists "user" (
    id bigint primary key ,
    creator_id bigint not null ,
    create_time timestamptz not null default now(),
    updater_id bigint not null,
    update_time timestamptz not null default now(),
    version integer not null default 0 ,
    deleted char(1) not null default 'n' ,
    priv_creator_hgid bigint not null ,

    nickname varchar(32) not null
);

insert into ppe_feature (id, creator_id, updater_id, priv_creator_hgid, nickname)
values (0, 0, 0, 1970, 'root');

