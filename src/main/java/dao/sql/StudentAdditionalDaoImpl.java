package dao.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dao.StudentAdditionalDao;
import domain.Reason;
import domain.StudentAdditional;
import domain.StudentYear;

public class StudentAdditionalDaoImpl extends DaoImpl implements StudentAdditionalDao{
	
	private static Logger logger = LogManager.getLogger(StudentAdditionalDaoImpl.class);

	@Override
	public Integer save(StudentAdditional entity) {
		String sql = "insert into StudentAdditional(studentyear_id,sum,reason_id,month,isproved,date_of_prove) VALUES(?,?,?,?,?,?);";
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(sql);
			
			ps.setInt(1,entity.getStudentYear().getId());
			ps.setDouble(2, entity.getSum());
			ps.setInt(3,entity.getReason().getId());
			ps.setInt(4, entity.getMonth());
			ps.setBoolean(5, entity.getIsProved());
			ps.setInt(6, entity.getDateOfProve());
			
			ps.executeUpdate();
			
		} catch(SQLException e) {
			logger.error("There is some problem after trying insert into table StudentAdditional");
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch(SQLException | NullPointerException e) { e.printStackTrace(); }
		}
		return null;
		
	}

	@Override
	public StudentAdditional read(Integer identity) {
		String sql = "select * from StudentAdditional where id=?";
		ResultSet rs = null;
		PreparedStatement ps = null;
		StudentAdditional studentAdditional = new StudentAdditional();
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, identity);
			rs = ps.executeQuery();
			if(rs.next()) {
				studentAdditional.setId(rs.getInt(1));
				StudentYear studentYear = new StudentYear(rs.getInt(2));
				studentAdditional.setStudentYear(studentYear);
				studentAdditional.setSum(rs.getDouble(3));
				Reason reason = new Reason(rs.getInt(4));
				studentAdditional.setReason(reason);
				studentAdditional.setMonth(rs.getInt(5));
				studentAdditional.setIsProved(rs.getBoolean(6));
				studentAdditional.setDateOfProve(rs.getInt(7));
			}
			
		} catch(SQLException e) {
			logger.error("There is some problem after trying read from table StudentAdditional");
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch(SQLException | NullPointerException e) { e.printStackTrace(); }
			try {
				rs.close();
			} catch(SQLException | NullPointerException e) { e.printStackTrace(); }
		}
		return studentAdditional;
	}

	@Override
	public void update(StudentAdditional entity) {
		String sql = "update StudentAdditional set id=?, studentyear_id=?, sum=?, reason_id=?, month=?, isproved=?, date_of_prove=? where id=?";
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(sql);
			
			ps.setInt(1,entity.getId());
			ps.setInt(2,entity.getStudentYear().getId());
			ps.setDouble(3, entity.getSum());
			ps.setInt(4,entity.getReason().getId());
			ps.setInt(5, entity.getMonth());
			ps.setBoolean(6, entity.getIsProved());
			ps.setInt(7, entity.getDateOfProve());
			ps.setInt(8,entity.getId());
			
			ps.executeUpdate();
		} catch(SQLException e) {
			logger.error("There is some problem after trying update table StudentAdditional");
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch(SQLException | NullPointerException e) { e.printStackTrace(); }
		}
		
	}

	@Override
	public void delete(Integer identity) {
		String sql = "delete from StudentAdditional where id=?";
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, identity);
			ps.executeUpdate();
		} 
		catch(SQLException e) {
			logger.error("There is some problem after trying delete from table StudentAdditional");
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch(SQLException | NullPointerException e) { e.printStackTrace(); }
		}
		
	}

	@Override
	public List<StudentAdditional> findAll() {
		String sql = "select * from StudentAdditional;";
		List<StudentAdditional> StudentAdditionalList = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				StudentAdditional studentAdditional = new StudentAdditional();
				
				studentAdditional.setId(rs.getInt(1));
				StudentYear studentYear = new StudentYear(rs.getInt(2));
				studentAdditional.setStudentYear(studentYear);
				studentAdditional.setSum(rs.getDouble(3));
				Reason reason = new Reason(rs.getInt(4));
				studentAdditional.setReason(reason);
				studentAdditional.setMonth(rs.getInt(5));
				studentAdditional.setIsProved(rs.getBoolean(6));
				studentAdditional.setDateOfProve(rs.getInt(7));
				
				StudentAdditionalList.add(studentAdditional);
			}
		} catch(SQLException e) {
			logger.error("There is some problem after trying findAll from table StudentAdditional");
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch(SQLException | NullPointerException e) { e.printStackTrace(); }
			try {
				rs.close();
			} catch(SQLException | NullPointerException e) { e.printStackTrace(); }
		}
		return StudentAdditionalList;
	}

}
