package pl.miasi2013.spring.lab2.dao;

import java.util.Collection;

import javax.sql.DataSource;

import pl.miasi2013.spring.lab2.model.relations.Queue;

public class QueueRepository implements QueueRepositoryInterface {
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void insertQueue(Queue queue) {
		// TODO Auto-generated method stub

	}

	@Override
	public Queue getQueueById(long queueId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateQueue(Queue queue) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteQueue(Queue queue) {
		// TODO Auto-generated method stub

	}

	@Override
	public Collection<Queue> getAllQueues() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Queue> getQueuesByBookId() {
		// TODO Auto-generated method stub
		return null;
	}

}
