--insert into Movie(title) values('Bolek i Lolek - W piaskach Gobi');
--insert into Movie(title) values('Bolek i Lolek - Lampa Aladyna');
-- insert into Movie(title) values('Reksio - magik');
MERGE INTO Movie USING (VALUES ('Reksio - magik'),('Bolek i Lolek - W piaskach Gobi'),('Bolek i Lolek - Lampa Aladyna')) 
AS vals(y) ON Movie.title = vals.y
WHEN NOT MATCHED THEN INSERT (TITLE) VALUES TRIM(vals.y);