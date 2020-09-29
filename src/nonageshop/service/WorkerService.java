package nonageshop.service;

import nonageshop.dao.impl.WorkerDaoImpl;
import nonageshop.dto.Worker;

public class WorkerService {
	private WorkerDaoImpl dao = WorkerDaoImpl.getInstance();

	public int workerCheck(Worker worker) {
		return dao.workerCheck(worker);
	}
}
