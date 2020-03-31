package dao.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dao.ReasonDao;
import domain.Reason;


public class ReasonDaoImpl extends DaoImpl implements ReasonDao{
	
	private static Logger logger = LogManager.getLogger(ReasonDaoImpl.class);

	@Override
	public Integer save(Reason entity) {
		String sql = "insert into Reason(number,note,max_coef) values(?,?,?);";
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(sql);
			
			ps.setDouble(1, entity.getNumber());
			ps.setString(2, entity.getNote());
			ps.setInt(3, entity.getMaxCoef());
			
			ps.executeUpdate();
			
		} catch(SQLException e) {
			logger.error("There is some problem after trying insert into table Reason");
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch(SQLException | NullPointerException e) { e.printStackTrace(); }
		}
		return null;
		
	}

	@Override
	public Reason read(Integer identity) {
		String sql = "select * from Reason where id=?";
		ResultSet rs = null;
		PreparedStatement ps = null;
		Reason reason = new Reason();
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, identity);
			rs = ps.executeQuery();
			if(rs.next()) {
				reason.setId(rs.getInt(1));
				reason.setNumber(rs.getDouble(2));
				reason.setNote(rs.getString(3));
				reason.setMaxCoef(rs.getInt(4));
			}
			
		} catch(SQLException e) {
			logger.error("There is some problem after trying read from table Reason");
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch(SQLException | NullPointerException e) { e.printStackTrace(); }
			try {
				rs.close();
			} catch(SQLException | NullPointerException e) { e.printStackTrace(); }
		}
		return reason;
	}

	@Override
	public void update(Reason entity) {
		String sql = "update Reason set id=? ,note=?, number=?, max_coef=? where id=?";
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(sql);
			
			ps.setInt(1, entity.getId());
			ps.setDouble(2, entity.getNumber());
			ps.setString(3, entity.getNote());
			ps.setInt(4, entity.getMaxCoef());
			ps.setInt(5, entity.getId());
			
			ps.executeUpdate();
		} catch(SQLException e) {
			logger.error("There is some problem after trying update table Reason");
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch(SQLException | NullPointerException e) { e.printStackTrace(); }
		}
		
	}

	@Override
	public void delete(Integer identity) {
		String sql = "delete from Reason where id=?";
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, identity);
			ps.executeUpdate();
		} 
		catch(SQLException e) {
			logger.error("There is some problem after trying delete from table Reason");
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch(SQLException | NullPointerException e) { e.printStackTrace(); }
		}
		
	}

	@Override
	public List<Reason> findAll() {
		String sql = "select * from Reason;";
		List<Reason> ReasonList = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Reason reason = new Reason();
				reason.setId(rs.getInt(1));
				reason.setNumber(rs.getDouble(2));
				reason.setNote(rs.getString(3));
				reason.setMaxCoef(rs.getInt(4));
				
				ReasonList.add(reason);
			}
		} catch(SQLException e) {
			logger.error("There is some problem after trying findAll from table Reason");
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch(SQLException | NullPointerException e) { e.printStackTrace(); }
			try {
				rs.close();
			} catch(SQLException | NullPointerException e) { e.printStackTrace(); }
		}
		return ReasonList;
	}

}
