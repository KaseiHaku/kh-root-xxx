drop table if exists "ppe_feature";
create table if not exists "ppe_feature" (
    id bigint primary key ,
    creator_id bigint not null ,
    create_time timestamptz not null default now(),
    updater_id bigint not null,
    update_time timestamptz not null default now(),
    version integer not null default 0 ,
    deleted char(1) not null default 'n' ,
    priv_creator_hgid bigint not null ,

    proc_id bigint not null,
    count_sign_total integer default null
);

insert into ppe_feature (id, creator_id, updater_id, priv_creator_hgid, proc_id)
values (0, 0, 0, 1970, 12345);

delete from ppe_feature t where t.id=191894950793703424;

update ppe_feature t set count_sign_total=3 where t.id=191625391176151040;


select * from ppe_feature t ;

