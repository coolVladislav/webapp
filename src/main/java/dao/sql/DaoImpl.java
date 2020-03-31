package dao.sql;

import java.sql.Connection;

abstract public class DaoImpl {
	protected Connection connection;

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
}