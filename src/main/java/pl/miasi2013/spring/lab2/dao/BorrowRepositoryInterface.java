package pl.miasi2013.spring.lab2.dao;

import java.util.Collection;

import pl.miasi2013.spring.lab2.model.relations.Borrow;

public interface BorrowRepositoryInterface {

	int insertBorrow(Borrow borrow);

	Borrow getBorrowById(long borrowId);

	void updateBorrow(Borrow borrow);

	void deleteBorrow(Borrow borrow);

	Collection<Borrow> getAllBorrows();
	
	Collection<Borrow> getUserBorrows(long userId);
	
	Borrow getBorrowByBookId(long bookId);

}
