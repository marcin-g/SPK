package pl.miasi2013.spring.lab2.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.miasi2013.spring.lab2.dao.BorrowRepositoryInterface;
import pl.miasi2013.spring.lab2.model.relations.Borrow;
import pl.miasi2013.spring.lab2.service.exceptions.BorrowNotFoundException;

@Service
public class BorrowService {
	@Autowired
	private BorrowRepositoryInterface borrowRepository;

	public void insertBorrow(Borrow borrow) {
		borrowRepository.insertBorrow(borrow);
	}

	public Borrow getBorrowById(long borrowId) {
		Borrow borrow=borrowRepository.getBorrowById(borrowId);
		if(borrow==null){
			throw new BorrowNotFoundException(borrowId);
		}
		return borrow;
	}

	public void updateBorrow(Borrow borrow) {
		borrowRepository.updateBorrow(borrow);
	}

	public void deleteBorrowById(long borrowId) {
		Borrow borrow=borrowRepository.getBorrowById(borrowId);
		if(borrow==null){
			throw new BorrowNotFoundException(borrowId);
		}
		else{
			borrowRepository.deleteBorrow(borrow);
		}
		
	}

	public Collection<Borrow> getAllBorrows() {
		return borrowRepository.getAllBorrows();
	}
	
	

}
