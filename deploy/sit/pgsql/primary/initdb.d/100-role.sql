/******************************* Replica 主从复制 用户配置 ********************************
 *
 * connection limit '-1'            # 当前 role 最多能建立多少个连接，-1 无限
 * */
create role standby with replication login password 'foobar' connection limit 8;


/******************************* Application 读写 用户配置 ********************************
 *
 * */
create role k8s_rw with login password 'k8s_rw';
create role k8s_ro with login password 'k8s_ro';


/******************************* Grant ********************************
 * privilege 介绍文档: https://www.postgresql.org/docs/current/ddl-priv.html
 * grant 语句文档: https://www.postgresql.org/docs/15/sql-grant.html
 * */

-- \c 或 \connect 是 psql 的命令，用于切换 数据库 和 用户，不切换默认为 compose 中配置的 user 和 dbname
\connect test
\conninfo
grant all on database test to k8s_rw;
grant all on schema public to k8s_rw;

grant connect on database test to k8s_ro;
grant usage on schema public to k8s_ro;
grant select on all tables in schema public to k8s_ro; -- @trap 该语句只对现有 table 有效，对后续新建的 table 无效
-- 当 k8s_rw 在当前 database 创建新对象(表，视图，函数等)时，这些对象会执行哪些权限配置
ALTER DEFAULT PRIVILEGES FOR ROLE k8s_rw IN SCHEMA public GRANT SELECT ON TABLES TO k8s_ro;

\connect bpm
\conninfo
grant all on database bpm to k8s_rw;
grant all on schema public to k8s_rw;

grant connect on database bpm to k8s_ro;
grant usage on schema public to k8s_ro;
grant select on all tables in schema public to k8s_ro;
ALTER DEFAULT PRIVILEGES FOR ROLE k8s_rw IN SCHEMA public GRANT SELECT ON TABLES TO k8s_ro;

