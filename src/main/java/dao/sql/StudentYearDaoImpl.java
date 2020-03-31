package dao.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dao.StudentYearDao;
import domain.Student;
import domain.StudentYear;

public class StudentYearDaoImpl extends DaoImpl implements StudentYearDao {
	
	private static Logger logger = LogManager.getLogger(StudentYearDaoImpl.class);

	@Override
	public Integer save(StudentYear entity) {
		String sql = "insert into StudentYear(course,\"group\",student_id,year) values(?,?,?,?);";
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(sql);
			logger.debug("In StudentYear DaoImpl save "+ps);
			ps.setInt(1, entity.getCourse());
			ps.setInt(2, entity.getGroup());
			logger.debug("Group was set "+ps);
//			ps.setBoolean(3, entity.getIsFreeibe());
			ps.setInt(3, entity.getStudent().getId());
			ps.setInt(4, entity.getYear());
			
			ps.executeUpdate();
			
		} catch(SQLException e) {
			logger.error("There is some problem after trying insert into table StudentYear");
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch(SQLException | NullPointerException e) { e.printStackTrace(); }
		}
		return null;
		
	}

	@Override
	public StudentYear read(Integer identity) {
		String sql = "select * from StudentYear where id=?";
		ResultSet rs = null;
		PreparedStatement ps = null;
		StudentYear studentYear = new StudentYear();
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, identity);
			rs = ps.executeQuery();
			if(rs.next()) {
				studentYear.setId(rs.getInt(1));
				studentYear.setCourse(rs.getInt(2));
				studentYear.setGroup(rs.getInt(3));
				studentYear.setIsFreeibe(rs.getBoolean(4));
				Student student = new Student(rs.getInt(5));
				studentYear.setStudent(student);
				studentYear.setYear(rs.getInt(6));
			}
			
		} catch(SQLException e) {
			logger.error("There is some problem after trying read from table StudentYear");
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch(SQLException | NullPointerException e) { e.printStackTrace(); }
			try {
				rs.close();
			} catch(SQLException | NullPointerException e) { e.printStackTrace(); }
		}
		return studentYear;
	}

	@Override
	public void update(StudentYear entity) {
		String sql = "update StudentYear set id=?, course=?, \"group\"=?,student_id=?, year=? where id=?";
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(sql);
			
			ps.setInt(1,entity.getId());
			ps.setInt(2, entity.getCourse());
			ps.setInt(3, entity.getGroup());
			ps.setInt(4, entity.getStudent().getId());
			ps.setInt(5, entity.getYear());
			ps.setInt(6,entity.getId());
			
			ps.executeUpdate();
		} catch(SQLException e) {
			logger.error("There is some problem after trying update table StudentYear");
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch(SQLException | NullPointerException e) { e.printStackTrace(); }
		}
		
	}

	@Override
	public void delete(Integer identity) {
		String sql = "delete from StudentYear where id=?";
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, identity);
			ps.executeUpdate();
		} 
		catch(SQLException e) {
			logger.error("There is some problem after trying delete from table StudentYear");
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch(SQLException | NullPointerException e) { e.printStackTrace(); }
		}
		
	}

	@Override
	public List<StudentYear> findAll() {
		String sql = "select * from StudentYear;";
		List<StudentYear> StudentYearList = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				StudentYear studentYear = new StudentYear();

				studentYear.setId(rs.getInt(1));
				studentYear.setCourse(rs.getInt(2));
				studentYear.setGroup(rs.getInt(3));
				studentYear.setIsFreeibe(rs.getBoolean(4));
				Student student = new Student(rs.getInt(5));
				studentYear.setStudent(student);
				studentYear.setYear(rs.getInt(6));
				
				StudentYearList.add(studentYear);
				logger.debug("StudentYearDaoImpl" + StudentYearList);
			}
		} catch(SQLException e) {
			logger.error("There is some problem after trying find all in table StudentYear");
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch(SQLException | NullPointerException e) { e.printStackTrace(); }
			try {
				rs.close();
			} catch(SQLException | NullPointerException e) { e.printStackTrace(); }
		}
		return StudentYearList;
	}

}
