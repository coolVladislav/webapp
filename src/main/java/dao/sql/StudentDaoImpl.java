package dao.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dao.StudentDao;
import domain.Student;

public class StudentDaoImpl extends DaoImpl implements StudentDao{
	
	private static Logger logger = LogManager.getLogger(StudentDaoImpl.class);

	@Override
	public Integer save(Student entity) {
		String sql = "insert into Student(last_name,first_name,patronymic) values(?,?,?);";
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(sql);
			
//			ps.setInt(1,entity.getId());
			ps.setString(1, entity.getLastName());
			ps.setString(2, entity.getFirstName());
			ps.setString(3, entity.getPatronymic());
			
			logger.debug("In save sql impl student "+ps);
			
			ps.executeUpdate();
			
		} catch(SQLException e) {
			logger.error("There is some problem after trying insert into table Student");
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch(SQLException | NullPointerException e) { e.printStackTrace(); }
		}
		return null;
		
	}

	@Override
	public Student read(Integer identity) {
		String sql = "select * from Student where id=?";
		ResultSet rs = null;
		PreparedStatement ps = null;
		Student student = new Student();
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, identity);
			rs = ps.executeQuery();
			if(rs.next()) {
				student.setId(rs.getInt(1));
				student.setLastName(rs.getString(2));
				student.setFirstName(rs.getString(3));
				student.setPatronymic(rs.getString(4));
			}
			
		} catch(SQLException e) {
			logger.error("There is some problem after trying read Student from table");
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch(SQLException | NullPointerException e) { e.printStackTrace(); }
			try {
				rs.close();
			} catch(SQLException | NullPointerException e) { e.printStackTrace(); }
		}
		return student;
	}

	@Override
	public void update(Student entity) {
		String sql = "update Student set id=?,last_name=?, first_name=?, patronymic=? where id=?";
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(sql);
			
			ps.setInt(1, entity.getId());
			ps.setString(2, entity.getLastName());
			ps.setString(3, entity.getFirstName());
			ps.setString(4, entity.getPatronymic());
			ps.setInt(5, entity.getId());
			
			logger.debug("In update sql impl student "+ps);
			ps.executeUpdate();
		} catch(SQLException e) {
			logger.error("There is some problem after trying update table Student");
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch(SQLException | NullPointerException e) { e.printStackTrace(); }
		}
	}
		

	@Override
	public void delete(Integer identity) {
		String sql = "delete from Student where id=?";
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, identity);
			ps.executeUpdate();
		} 
		catch(SQLException e) {
			logger.error("There is some problem after trying delete from table Student");
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch(SQLException | NullPointerException e) { e.printStackTrace(); }
		}
		
	}

	@Override
	public List<Student> findAll() {
		String sql = "select id, last_name, first_name, patronymic from Student;";
		List<Student> StudentsList = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			Student student = null;
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				student = new Student();
				student.setId(rs.getInt(1));
				student.setLastName(rs.getString(2));
				student.setFirstName(rs.getString(3));
				student.setPatronymic(rs.getString(4));
				
				StudentsList.add(student);
			}
		} catch(SQLException e) {
			logger.error("There is some problem after trying table");
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch(SQLException | NullPointerException e) { e.printStackTrace(); }
			try {
				rs.close();
			} catch(SQLException | NullPointerException e) { e.printStackTrace(); }
		}
		return StudentsList;
	}

}
