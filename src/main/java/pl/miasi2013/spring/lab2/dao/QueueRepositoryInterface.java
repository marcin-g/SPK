package pl.miasi2013.spring.lab2.dao;

import java.util.Collection;

import pl.miasi2013.spring.lab2.model.relations.Queue;

public interface QueueRepositoryInterface {

	int insertQueue(Queue queue);

	Queue getQueueById(long queueId);

	void updateQueue(Queue queue);

	void deleteQueue(Queue queue);

	Collection<Queue> getAllQueues();
	
	Collection<Queue> getQueuesByBookId(long bookId);
	
	public boolean isBookQueuedByUser(long bookId, long userId);

}

