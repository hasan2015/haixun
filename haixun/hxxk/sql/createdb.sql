/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2015/5/27 22:10:16                           */
/*==============================================================*/

drop table if exists ymygrade;
drop table if exists yschedule;
drop table if exists ygrade;
drop table if exists yspecialty;
drop table if exists yuser;

/*==============================================================*/
/* Table: ygrade                                                */
/*==============================================================*/
create table ygrade
(
   gradeid              int not null auto_increment,
   specialtyid          int,
   areaid				int,
   code                 varchar(32),
   area                 varchar(128),
   year                 varchar(32),
   term                 varchar(32),
   degree               varchar(32),
   startdate            varchar(32),
   enddate              varchar(32),
   times                int,
   type                 varchar(32),
   phone                varchar(32),
   costdetail           varchar(128),
   cost                 float(12,2),
   description          varchar(2048),
   agelimit             varchar(128),
   genderlimit          varchar(32),
   ability              varchar(128),
   manner               varchar(128),
   link               	varchar(128),
   applystatus        	varchar(128),
   
   createdby 			varchar(128),
   createddate			datetime,
   updatedby			varchar(128),
   updateddate			datetime,
   active               SMALLINT default 1,
   primary key (gradeid)
);

/*==============================================================*/
/* Table: ymygrade                                              */
/*==============================================================*/
create table ymygrade
(
   mygradeid            int not null auto_increment,
   gradeid              int,
   userid               int,
   reservedcode         varchar(32),
   applystatus          varchar(32),
   overtime             varchar(32),
   
   createdby 			varchar(128),
   createddate			datetime,
   updatedby			varchar(128),
   updateddate			datetime,
   active               SMALLINT default 1,
   primary key (mygradeid)
);

/*==============================================================*/
/* Table: yschedule                                             */
/*==============================================================*/
create table yschedule
(
   scheduleid           int not null auto_increment,
   gradeid              int,
   week                 varchar(32),
   starttime            varchar(32),
   endtime              varchar(32),
   address              varchar(128),
   
   createdby 			varchar(128),
   createddate			datetime,
   updatedby			varchar(128),
   updateddate			datetime,
   active               SMALLINT default 1,
   primary key (scheduleid)
);

/*==============================================================*/
/* Table: yspecialty                                            */
/*==============================================================*/
create table yspecialty
(
   specialtyid          int not null auto_increment,
   name                 varchar(32),
   
   createdby 			varchar(128),
   createddate			datetime,
   updatedby			varchar(128),
   updateddate			datetime,
   active               SMALLINT default 1,
   primary key (specialtyid)
);

/*==============================================================*/
/* Table: yuser                                                 */
/*==============================================================*/
create table yuser
(
   userid               int not null auto_increment,
   name                 varchar(128),
   id                   varchar(32),--
   mobile               varchar(32),
   school               varchar(128),
   grade                varchar(32),
   gender               varchar(32),--
   patriarch            varchar(32),
   homephone            varchar(32),
   birthday             varchar(32),
   description          varchar(2048),
   password          	varchar(32),
   status				int,
   
   createdby 			varchar(128),
   createddate			datetime,
   updatedby			varchar(128),
   updateddate			datetime,
   active               SMALLINT default 1,
   primary key (userid)
);
create table ywxaccount
(
   wxaccountid          int not null auto_increment,
   userid               int,
   openid               varchar(128),
   nickname             national varchar(128),
   sex                  national varchar(32),
   province             national varchar(32),
   city                 national varchar(32),
   country              national varchar(32),
   headimgurl           varchar(128),
   privilege            varchar(128),
   unionid              varchar(128),
   status				int,
   
   createdby 			varchar(128),
   createddate			datetime,
   updatedby			varchar(128),
   updateddate			datetime,
   active               SMALLINT default 1,
   primary key (wxaccountid)
);
/*==============================================================*/
/* Table: yinstitution                                          */
/*==============================================================*/
create table yinstitution
(
   institutionid        int not null auto_increment,
   name                 national varchar(128),
   homeurl              national varchar(128),
   refreshdate          datetime,
   
   createdby 			varchar(128),
   createddate			datetime,
   updatedby			varchar(128),
   updateddate			datetime,
   active               SMALLINT default 1,
   primary key (institutionid)
);
/*==============================================================*/
/* Table: yclock                                          */
/*==============================================================*/
create table ymyclock
(
   myclockid		    int not null auto_increment,
   mygradeid            int,
   name                 national varchar(128),
   starttime            datetime,
   endtime              datetime,   
   intervalm 			int default 1,/*--1m*/
   duration				int default 10,/*10s*/
   
   createdby 			varchar(128),
   createddate			datetime,
   updatedby			varchar(128),
   updateddate			datetime,
   active               SMALLINT default 1,
   primary key (myclockid)
);
alter table ygrade add constraint fk_r01 foreign key (specialtyid)
      references yspecialty (specialtyid) on delete restrict on update restrict;

alter table ymygrade add constraint fk_r03 foreign key (userid)
      references yuser (userid) on delete restrict on update restrict;

alter table ymygrade add constraint fk_r04 foreign key (gradeid)
      references ygrade (gradeid) on delete restrict on update restrict;

alter table yschedule add constraint fk_r02 foreign key (gradeid)
      references ygrade (gradeid) on delete restrict on update restrict;
      
alter table ywxaccount add constraint fk_reference_6 foreign key (userid)
      references yuser (userid) on delete restrict on update restrict;
      
alter table ymyclock add constraint fk_r8 foreign key (mygradeid)
      references ymygrade (mygradeid) on delete restrict on update restrict;
