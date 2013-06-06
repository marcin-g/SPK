insert into UserU(username, firstname, lastname, email, roles, password) values('admin', 'admin', 'lol', 'admin@fooo.fo', 'ROLE_ADMIN', '098f6bcd4621d373cade4e832627b4f6');
insert into UserU(username, firstname, lastname, email, roles, password) values('root', 'root', 'lol', 'root@fooo.fo', 'ROLE_SUPERUSER', '098f6bcd4621d373cade4e832627b4f6');
insert into UserU(username, firstname, lastname, email, roles, password) values('user', 'user', 'lol', 'user@fooo.fo', 'ROLE_USER', '098f6bcd4621d373cade4e832627b4f6');

insert into Book(title, isbn, year, author, state) values('Pan Tadeusz', '12345', '1000', 'Adam Mickiewicz', 'AVAILABLE');
insert into Book(title, isbn, year, author, state) values('Pan Tadeusz: Początek', '12346', '1100', 'Adam Mickiewicz', 'AVAILABLE');
insert into Book(title, isbn, year, author, state) values('Pan Tadeusz: Powrót Gerwazego', '12345', '1900', 'Adam Mickiewicz', 'AVAILABLE');
insert into Book(title, isbn, year, author, state) values('Pan Tadeusz: Przewrót Gerwazego', '12345', '1900', 'Adam Mickiewicz', 'AVAILABLE');
insert into Book(title, isbn, year, author, state) values('Pan Tadeusz: Nawrót Gerwazego', '12345', '1900', 'Adam Mickiewicz', 'AVAILABLE');

insert into Borrow(book_id, user_id, begin, end) values(1, 1, 1370558512, 1370558522);
insert into Borrow(book_id, user_id, begin) values(2, 1, 1370558512);
insert into Borrow(book_id, user_id, begin, end) values(5, 1, 1370558512, 1370558532);
insert into Borrow(book_id, user_id, begin) values(4, 2, 1370558512);
insert into Borrow(book_id, user_id, begin, end) values(5, 2, 1370558533, 1370558534);
