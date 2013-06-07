package pl.miasi2013.spring.lab2.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;


import pl.miasi2013.spring.lab2.dao.BorrowRepositoryInterface;
import pl.miasi2013.spring.lab2.model.Book;
import pl.miasi2013.spring.lab2.model.User;
import pl.miasi2013.spring.lab2.model.relations.Borrow;
import pl.miasi2013.spring.lab2.service.exceptions.BorrowNotFoundException;

@Service
public class BorrowService {
	@Autowired
	private BorrowRepositoryInterface borrowRepository;
	@Autowired
	private BookService bookService;
	@Autowired
	private UserService userService;

	@Transactional
	public void insertBorrow(Borrow borrow) {
		borrowRepository.insertBorrow(borrow);
	}

	@Transactional
	public Borrow getBorrowById(long borrowId) {
		Borrow borrow=borrowRepository.getBorrowById(borrowId);
		if(borrow==null){
			throw new BorrowNotFoundException(borrowId);
		}
		return borrow;
	}

	@Transactional
	public void updateBorrow(Borrow borrow) {
		borrowRepository.updateBorrow(borrow);
	}

	@Transactional
	public void deleteBorrowById(long borrowId) {
		Borrow borrow=borrowRepository.getBorrowById(borrowId);
		if(borrow==null){
			throw new BorrowNotFoundException(borrowId);
		}
		else{
			borrowRepository.deleteBorrow(borrow);
		}
		
	}

	@Transactional
	public Collection<Borrow> getAllBorrows() {
		return borrowRepository.getAllBorrows();
	}
	
	public static boolean isBorrowValid(Borrow borrow, BindingResult result) {
		boolean valid=true;
		return valid;
	}

	@Transactional
	public Map<Borrow,Book> getBorrowsWithBooks(Collection<Borrow> borrows) {
		HashMap<Borrow,Book> map=new HashMap<Borrow,Book>();
		for(Borrow borrow:borrows){
			map.put(borrow, bookService.getBookById(borrow.getBookId()));
		}
		return map;
	}

	@Transactional
	public Collection<Borrow> getUserBorrows(User user) {
		return borrowRepository.getUserBorrows(user.getId());
	}

	@Transactional
	public Collection<Borrow> getBorrowsByBookId(long bookId) {
		return borrowRepository.getBorrowsByBookId(bookId);
	}

	@Transactional
	public Collection<Borrow> getUserBorrows(long userId) {
		return borrowRepository.getUserBorrows(userId);
	}
	@Transactional
	public Map<Borrow,Book> getUserBorrowsWithBooks(long userId) {
		HashMap<Borrow,Book> map=new HashMap<Borrow,Book>();
		for(Borrow borrow:getUserBorrows(userService.getUserById(userId))){
			map.put(borrow, bookService.getBookById(borrow.getBookId()));
		}
		return map;
	}
	@Transactional
	public Map<Borrow,User> getBooksBorrowsWithUser(long bookId) {
		HashMap<Borrow,User> map=new HashMap<Borrow,User>();
		for(Borrow borrow:getBorrowsByBookId(bookId)){
			map.put(borrow, userService.getUserById(borrow.getUserId()));
		}
		return map;
	}

	@Transactional
	public Borrow getActualBorrowByBook(long bookId){
		return borrowRepository.getActualBorrowByBook(bookId);
	}


}
