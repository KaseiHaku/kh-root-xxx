/******************************* Information *******************************/
select t.* from pg_catalog.pg_views t; -- 查看所有系统视图
select t.* from pg_catalog.pg_database t;
select t.* from pg_catalog.pg_tablespace t;
select t.* from pg_catalog.pg_roles t;
select t.* from pg_catalog.pg_user t;
select t.* from pg_catalog.pg_tables t;
select t.* from pg_catalog.pg_settings t;
select t.* from pg_catalog.pg_stat_replication t;
select t.* from pg_catalog.pg_replication_slots t;  -- 复制槽信息
select t.* from information_schema.role_table_grants t; -- 查看当前 database table grant 数据
select t.* from information_schema.role_usage_grants t; -- 查看当前 database usage grant 数据




-- https://www.postgresql.org/docs/current/sql-show.html
show all;
select t.* from pg_catalog.pg_settings t where t.name like '%_file%';
select t.* from pg_catalog.pg_settings t where t.name in ('archive_library');


-- 主备同步测试
insert into test.public."user" (id, creator_id, updater_id, priv_creator_homonym_group_id, nickname)
values (0, 0, 0, 1970, 'xxx2');
select t.* from test.public."user" t;





