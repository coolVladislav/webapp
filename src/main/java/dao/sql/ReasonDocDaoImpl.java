package dao.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dao.ReasonDocDao;
import domain.ReasonDoc;


public class ReasonDocDaoImpl extends DaoImpl implements ReasonDocDao {
	
	private static Logger logger = LogManager.getLogger(ReasonDocDaoImpl.class);

	@Override
	public Integer save(ReasonDoc entity) {
		String sql = "insert into ReasonDoc(number,doc_name) VALUES(?,?);";
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(sql);
			
			ps.setDouble(1, entity.getNumber());
			ps.setString(2, entity.getDocName());
			
			ps.executeUpdate();
			
		} catch(SQLException e) {
			logger.error("There is some problem after trying insert into table ReasonDoc");
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch(SQLException | NullPointerException e) { e.printStackTrace(); }
		}
		return null;
		
	}

	@Override
	public ReasonDoc read(Integer identity) {
		String sql = "select * from ReasonDoc where id=?";
		ResultSet rs = null;
		PreparedStatement ps = null;
		ReasonDoc reasonDoc = new ReasonDoc();
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, identity);
			rs = ps.executeQuery();
			if(rs.next()) {				
				reasonDoc.setId(rs.getInt(1));
				reasonDoc.setNumber(rs.getDouble(2));
				reasonDoc.setDocName(rs.getString(3));
			}
			
		} catch(SQLException e) {
			logger.error("There is some problem after trying read from table ReasonDoc");
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch(SQLException | NullPointerException e) { e.printStackTrace(); }
			try {
				rs.close();
			} catch(SQLException | NullPointerException e) { e.printStackTrace(); }
		}
		return reasonDoc;
	}

	@Override
	public void update(ReasonDoc entity) {
		String sql = "update ReasonDoc set id=?, number=?, doc_name=? where id=?";
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(sql);
			
			ps.setInt(1, entity.getId());
			ps.setDouble(2, entity.getNumber());
			ps.setString(3, entity.getDocName());
			ps.setInt(4, entity.getId());
			
			ps.executeUpdate();
		} catch(SQLException e) {
			logger.error("There is some problem after trying update table ReasonDoc");
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch(SQLException | NullPointerException e) { e.printStackTrace(); }
		}
	}

	@Override
	public void delete(Integer identity) {
		String sql = "delete from ReasonDoc where id=?";
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, identity);
			ps.executeUpdate();
		} 
		catch(SQLException e) {
			logger.error("There is some problem after trying delete from table ReasonDoc");
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch(SQLException | NullPointerException e) { e.printStackTrace(); }
		}
		
	}

	@Override
	public List<ReasonDoc> findAll() {
		String sql = "select * from ReasonDoc;";
		List<ReasonDoc> ReasonDocsList = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				ReasonDoc reasonDoc = new ReasonDoc();
				
				reasonDoc.setId(rs.getInt(1));
				reasonDoc.setNumber(rs.getDouble(2));
				reasonDoc.setDocName(rs.getString(3));
				
				ReasonDocsList.add(reasonDoc);
			}
		} catch(SQLException e) {
			logger.error("There is some problem after trying frindAll in table ReasonDoc");
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch(SQLException | NullPointerException e) { e.printStackTrace(); }
			try {
				rs.close();
			} catch(SQLException | NullPointerException e) { e.printStackTrace(); }
		}
		return ReasonDocsList;
	}

}
