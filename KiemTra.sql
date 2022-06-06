create database if not exists KiemTra;
use KiemTra;
create table phim(
     ma varchar(50),
    ten varchar(50),
    thoigian varchar(50),
    auhtor varchar(50),
    numberMinutes int,
    primary key (ma)
);
insert into phim values('P01','Doraemon','2022-6-12','fujiko',160);
insert into phim values ('P02','Conan','2022-12-12','Aoyama Gosho',120);
insert into phim values('P03','Tinh nguoi duyen ma','2022-12-8','Jira Maligool',200);

select * from phim;