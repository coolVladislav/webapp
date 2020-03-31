package dao.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dao.OrderDocDao;
import domain.Order;
import domain.OrderDoc;
import domain.ReasonDoc;


public class OrderDocDaoImpl extends DaoImpl implements OrderDocDao{
	
	private static Logger logger = LogManager.getLogger(OrderDocDaoImpl.class);

	@Override
	public Integer save(OrderDoc entity) {
		String sql = "insert into OrderDoc(order_id,reasondoc_id,isaccepted) VALUES(?,?,?);";
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(sql);
			
			ps.setInt(1, entity.getOrder().getId());
			ps.setInt(2, entity.getReasonDoc().getId());
			ps.setBoolean(3, entity.getIsAccepted());
			
			ps.executeUpdate();
			
		} catch(SQLException e) {
			logger.error("There is some problem after trying insert into table OrderDoc");
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch(SQLException | NullPointerException e) { e.printStackTrace(); }
		}
		return null;
	}
		

	@Override
	public OrderDoc read(Integer identity) {
		String sql = "select * from OrderDoc where id=?";
		ResultSet rs = null;
		PreparedStatement ps = null;
		OrderDoc orderDoc = new OrderDoc();
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, identity);
			rs = ps.executeQuery();
			if(rs.next()) {
				
				orderDoc.setId(rs.getInt(1));
				Order order = new Order(rs.getInt(2));
				orderDoc.setOrder(order);
				ReasonDoc reasonDoc = new ReasonDoc(rs.getInt(3));
				orderDoc.setReasonDoc(reasonDoc);
				orderDoc.setIsAccepted(rs.getBoolean(4));
			}
			
		} catch(SQLException e) {
			logger.error("There is some problem after trying read from table OrderDoc");
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch(SQLException | NullPointerException e) { e.printStackTrace(); }
			try {
				rs.close();
			} catch(SQLException | NullPointerException e) { e.printStackTrace(); }
		}
		return orderDoc;
	}

	@Override
	public void update(OrderDoc entity) {
		String sql = "update OrderDoc set id=?, order_id=?, reasondoc_id=?, isaccepted=? where id=?";
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(sql);
			
			ps.setInt(1, entity.getId());
			ps.setInt(2, entity.getOrder().getId());
			ps.setInt(3, entity.getReasonDoc().getId());
			ps.setBoolean(4, entity.getIsAccepted());
			ps.setInt(5, entity.getId());
			
			ps.executeUpdate();
		} catch(SQLException e) {
			logger.error("There is some problem after trying update table OrderDoc");
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch(SQLException | NullPointerException e) { e.printStackTrace(); }
		}
		
	}

	@Override
	public void delete(Integer identity) {
		String sql = "delete from OrderDoc where id=?";
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, identity);
			ps.executeUpdate();
		} 
		catch(SQLException e) {
			logger.error("There is some problem after trying delete from table OrderDoc");
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch(SQLException | NullPointerException e) { e.printStackTrace(); }
		}
		
	}

	@Override
	public List<OrderDoc> findAll() {
		String sql = "select * from OrderDoc;";
		List<OrderDoc> OrderDocList = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				OrderDoc orderDoc = new OrderDoc();
				
				orderDoc.setId(rs.getInt(1));
				Order order = new Order(rs.getInt(2));
				orderDoc.setOrder(order);
				ReasonDoc reasonDoc = new ReasonDoc(rs.getInt(3));
				orderDoc.setReasonDoc(reasonDoc);
				orderDoc.setIsAccepted(rs.getBoolean(4));
				
				OrderDocList.add(orderDoc);
			}
		} catch(SQLException e) {
			logger.error("There is some problem after trying findAll in table OrderDoc");
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch(SQLException | NullPointerException e) { e.printStackTrace(); }
			try {
				rs.close();
			} catch(SQLException | NullPointerException e) { e.printStackTrace(); }
		}
		return OrderDocList;
	}
}
