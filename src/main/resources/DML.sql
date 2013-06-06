insert into UserU(username, firstname, lastname, email, roles, password) values('admin', 'admin', 'lol', 'admin@admin.pl', 'ROLE_ADMIN', '098f6bcd4621d373cade4e832627b4f6');
insert into UserU(username, firstname, lastname, email, roles, password) values('root', 'root', 'lol', 'admin@admin.pl', 'ROLE_SUPERUSER', '098f6bcd4621d373cade4e832627b4f6');
insert into UserU(username, firstname, lastname, email, roles, password) values('user', 'user', 'lol', 'admin@admin.pl', 'ROLE_USER', '098f6bcd4621d373cade4e832627b4f6');

insert into Book(title, isbn, year, author, state) values('Pan Tadeusz', '12345', '1000', 'Adam Mickiewicz', 'AVAILABLE');
insert into Book(title, isbn, year, author, state) values('Pan Tadeusz: Początek', '12346', '1100', 'Adam Mickiewicz', 'AVAILABLE');
insert into Book(title, isbn, year, author, state) values('Pan Tadeusz: Powrót Gerwazego', '12345', '1900', 'Adam Mickiewicz', 'AVAILABLE');

insert into Borrow(book_id, user_id) values(1, 1);
