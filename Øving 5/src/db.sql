DROP TABLE IF EXISTS konto;

CREATE TABLE konto(
  konto_nummer INTEGER NOT NULL,
  lock_field INTEGER NOT NULL,
  eier VARCHAR(50) NOT NULL,
  saldo DOUBLE NOT NULL,
  PRIMARY KEY(konto_nummer)
);

insert into konto values(11111111,0,'tusen',1000);
insert into konto values(22222222,0,'totusen',2000);
insert into konto values(33333333,0,'tretusen',3000);
insert into konto values(44444444,0,'firetusen',4000);
insert into konto values(55555555,0,'femtusen',5000);
insert into konto values(66666666,0,'sekstusen',6000);
insert into konto values(77777777,0,'syvtusen',7000);
insert into konto values(88888888,0,'aattetusen',8000);
insert into konto values(99999999,0,'nitusen',9000);