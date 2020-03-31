package service;

import dao.TransactionDao;

abstract public class ServiceImpl implements Service {
	protected TransactionDao transaction = null;

	public void setTransaction(TransactionDao transaction) {
		this.transaction = transaction;
	}
}
