alter table ygrade add column applystatus varchar(128);
alter table ygrade add column areaid int;

alter table yuser add column wxopenid varchar(128);

alter table yuser drop column wxnickname varchar(128);
alter table yuser drop column wxsex varchar(128);
alter table yuser drop column wxprovince varchar(128);
alter table yuser drop column wxcity varchar(128);
alter table yuser drop column wxcountry varchar(128);
alter table yuser drop column wxheadimgurl  ;
alter table yuser drop column wxprivilege  ;
alter table yuser drop column wxunionid ;
--
alter table yuser add column latestdate datetime;
 