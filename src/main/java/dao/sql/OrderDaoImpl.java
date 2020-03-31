package dao.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dao.OrderDao;
import domain.Order;
import domain.Reason;
import domain.StudentYear;

public class OrderDaoImpl extends DaoImpl implements OrderDao{
	
	private static Logger logger = LogManager.getLogger(OrderDaoImpl.class);

	@Override
	public Integer save(Order entity) {
		String sql = "insert into \"Order\"(studentyear_id,reason_id,date_of_order) values(?,?,?);";
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(sql);
			
			
			ps.setInt(1,entity.getStudentYear().getId());
			ps.setInt(2,entity.getReason().getId());
			ps.setInt(3,entity.getDateOfOrder());
			
			ps.executeUpdate();
			
		} catch(SQLException e) {
			logger.error("There is some problem after trying insert into table Order");
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch(SQLException | NullPointerException e) { e.printStackTrace(); }
		}
		return null;
	}

	@Override
	public Order read(Integer identity) {
		String sql = "select * from \"Order\" where id=?";
		ResultSet rs = null;
		PreparedStatement ps = null;
		Order order = new Order();
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, identity);
			rs = ps.executeQuery();
			if(rs.next()) {
				order.setId(rs.getInt(1));
				StudentYear studentYear = new StudentYear(rs.getInt(2));
				order.setStudentYear(studentYear);
				Reason reason = new Reason(rs.getInt(3));
				order.setReason(reason);
				order.setDateOfOrder(rs.getInt(4));
			}
			
		} catch(SQLException e) {
			logger.error("There is some problem after trying read from table Order");
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch(SQLException | NullPointerException e) { e.printStackTrace(); }
			try {
				rs.close();
			} catch(SQLException | NullPointerException e) { e.printStackTrace(); }
		}
		return order;
	}

	@Override
	public void update(Order entity) {
		String sql = "update \"Order\" set id=?, studentyear_id=?, reason_id=?, date_of_order=? where id=?";
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(sql);
			
			ps.setInt(1, entity.getId());
			ps.setInt(2, entity.getStudentYear().getId());
			ps.setInt(3, entity.getReason().getId());
			ps.setInt(4, entity.getDateOfOrder());
			ps.setInt(5, entity.getId());
			
			ps.executeUpdate();
		} catch(SQLException e) {
			logger.error("There is some problem after trying update table Order");
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch(SQLException | NullPointerException e) { e.printStackTrace(); }
		}
	}

	@Override
	public void delete(Integer identity) {
		String sql = "delete from \"Order\" where id=?";
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, identity);
			ps.executeUpdate();
		} 
		catch(SQLException e) {
			logger.error("There is some problem after trying delete from table Order");
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch(SQLException | NullPointerException e) { e.printStackTrace(); }
		}
		
	}

	@Override
	public List<Order> findAll() {
		String sql = "select * from \"Order\";";
		List<Order> OrderList = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Order order = new Order();
				order.setId(rs.getInt(1));
				StudentYear studentYear = new StudentYear(rs.getInt(2));
				order.setStudentYear(studentYear);
				Reason reason = new Reason(rs.getInt(3));
				order.setReason(reason);
				order.setDateOfOrder(rs.getInt(4));
				
				OrderList.add(order);
			}
		} catch(SQLException e) {
			logger.error("There is some problem after trying findAll table Order");
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch(SQLException | NullPointerException e) { e.printStackTrace(); }
			try {
				rs.close();
			} catch(SQLException | NullPointerException e) { e.printStackTrace(); }
		}
		return OrderList;
	}

}
