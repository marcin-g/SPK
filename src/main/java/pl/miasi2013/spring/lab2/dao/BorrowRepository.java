package pl.miasi2013.spring.lab2.dao;

import java.util.ArrayList;
import java.util.Collection;

import javax.sql.DataSource;

import pl.miasi2013.spring.lab2.model.relations.Borrow;

public class BorrowRepository implements BorrowRepositoryInterface {
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void insertBorrow(Borrow borrow) {
		// TODO Auto-generated method stub

	}

	@Override
	public Borrow getBorrowById(long borrowId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateBorrow(Borrow borrow) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteBorrow(Borrow borrow) {
		// TODO Auto-generated method stub

	}

	@Override
	public Collection<Borrow> getAllBorrows() {
		// TODO Auto-generated method stub
		//return null;
		Collection<Borrow> borrowList=new ArrayList<Borrow>();
		Borrow borrow;
		borrow=new Borrow();
		borrow.setId(1);
		borrow.setBookId(1);
		borrowList.add(borrow);
		borrow=new Borrow();
		borrow.setId(2);
		borrow.setBookId(3);
		borrowList.add(borrow);
		return borrowList;
	}

}
