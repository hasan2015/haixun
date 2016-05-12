SET FOREIGN_KEY_CHECKS=0;
truncate table ymygrade;
truncate table yuser;
truncate table yschedule;
truncate table ygrade;
truncate table yspecialty;

insert into yspecialty(specialtyid,name) values(1,'拉丁舞');
/**/
insert into ygrade(gradeid,specialtyid,code,area,year,term,degree,startdate,enddate,times,type,phone,costdetail,cost,
                   description,agelimit,graderlimit,ability,manner)
            values(1,1,'Q2C03-2QH','发展中心','2015 ','暑期','初级','2015-07-06','2015-07-11',6,'普通班级','86510148 或 12355-8','学费:336元',336.0,
                   '该班为暑期拉丁舞强化班，共培训6天，每天2小时，该班上课地点为杭州市青少年发展中心（钱江新城）咨询电话：0571－86510150 周老师',
                   '小学一年级 ～ 小学三年级','不限','没有设置能力特征','没有设置态度特征');
/**/
insert into yschedule(scheduleid,gradeid,week,starttime,endtime,address) 
               values(1,1,'周二','18:00','20:00','发展中心337室');
/**/
insert into yschedule(scheduleid,gradeid,week,starttime,endtime,address) 
               values(2,1,'周五','08:00','10:00','发展中心337室');
/**/
insert into yuser(userid,name,id,mobile,school,grade,gender,patriarch,homephone,birthday,description,password)
           values(1,'闫小悦','330106200611020040','13857168929','学林小学','小学三年级','女','闫大山'
                  ,'13685761215','2006-11-02','备注yy','123456');
/**/                
insert into ymygrade(mygradeid,userid,gradeid,reservedcode,applystatus,overtime)
              values(1,1,1,'hz2179953','已缴费','2015-05-24 ');

SET FOREIGN_KEY_CHECKS=1;        
/**/
insert into yinstitution(institutionid,name,homeurl) values(1,'杭州青少年活动中心','http://hzcs.qsng.cn/hz-bsp/hz/')
		
		
		
		
		