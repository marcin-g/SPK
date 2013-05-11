package pl.miasi2013.spring.lab2.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import pl.miasi2013.spring.lab2.dao.QueueRepositoryInterface;
import pl.miasi2013.spring.lab2.model.relations.Queue;
import pl.miasi2013.spring.lab2.service.exceptions.QueueNotFoundException;

@Service
public class QueueService {
	@Autowired
	private QueueRepositoryInterface queueRepository;

	@Transactional
	public void insertQueue(Queue queue) {
		queueRepository.insertQueue(queue);
	}

	public Queue getQueueById(long queueId) {
		Queue queue=queueRepository.getQueueById(queueId);
		if(queue==null){
			throw new QueueNotFoundException(queueId);
		}
		return queue;
	}

	@Transactional
	public void updateQueue(Queue queue) {
		queueRepository.updateQueue(queue);
	}

	@Transactional
	public void deleteQueueById(long queueId) {
		Queue queue=queueRepository.getQueueById(queueId);
		if(queue==null){
			throw new QueueNotFoundException(queueId);
		}
		else{
			queueRepository.deleteQueue(queue);
		}
		
	}

	public Collection<Queue> getAllQueues() {
		return queueRepository.getAllQueues();
	}
	
	
	public static boolean isQueueValid(Queue queue, BindingResult result) {
		boolean valid=true;
		return valid;
	}
}
