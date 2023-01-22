create database crud_etudiant_db;

use crud_etudiant_db;

create table etudiant(
    id int not null auto_increment primary key,
    prenom varchar(30) not null ,
    nom varchar(30) not null ,
    matiere varchar(30) not null
);

insert into etudiant(id,prenom, nom, matiere)
values(null, 'Abdoulaye', 'GAYE', 'Java');

select * from etudiant;