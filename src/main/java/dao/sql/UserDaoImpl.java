package dao.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import dao.UserDao;
import domain.Role;
import domain.User;
import exception.PersistentException;

public class UserDaoImpl extends DaoImpl implements UserDao {
	private static Logger logger = LogManager.getLogger(UserDaoImpl.class);

	@Override
	public Integer save(User entity) {
		String sql = "insert into \"User\"(id,login, password, role) VALUES (?,?, ?, ?)";
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(sql);
			
			ps.setInt(1,entity.getId());
			ps.setString(2, entity.getLogin());
			ps.setString(3, entity.getPassword());
			ps.setInt(4, entity.getRole().getIdentity());
			
			ps.executeUpdate();
		} catch(SQLException e) {
			logger.error("There is some problem after trying insert into table User");
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch(SQLException | NullPointerException e) { e.printStackTrace(); }
		}
		return null;
	}

	@Override
	public User read(Integer identity) {
		String sql = "select * from \"User\" where id = ?";
		ResultSet rs = null;
		PreparedStatement ps = null;
		User user = new User();
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, identity);
			rs = ps.executeQuery();
			if(rs.next()) {
				user.setId(rs.getInt(1));
				user.setLogin(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setRole(Role.getByIdentity(rs.getInt(4)));
			}
			
		} catch(SQLException e) {
			logger.error("There is some problem after trying read from table User");
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch(SQLException | NullPointerException e) { e.printStackTrace(); }
			try {
				rs.close();
			} catch(SQLException | NullPointerException e) { e.printStackTrace(); }
		}
		return user;
	}

	@Override
	public void update(User entity) {
		String sql = "update \"User\" set login = ?, password = ?, role = ? where id = ?";
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(sql);
			
			ps.setInt(1, entity.getId());
			ps.setString(2, entity.getLogin());
			ps.setString(3, entity.getPassword());
			ps.setInt(4, entity.getRole().getIdentity());
			ps.setInt(5, entity.getId());
			
			ps.executeUpdate();
		} catch(SQLException e) {
			logger.error("There is some problem after trying update table User");
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch(SQLException | NullPointerException e) { e.printStackTrace(); }
		}
	}

	@Override
	public void delete(Integer identity) {
		String sql = "delete from \"User\" where id = ?";
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, identity);
			ps.executeUpdate();
		} 
		catch(SQLException e) {
			logger.error("There is some problem after trying delete from table");
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch(SQLException | NullPointerException e) { e.printStackTrace(); }
		}		
	}

	@Override
	public List<User> findAll() {
		String sql = "select * from \"User\";";
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<User> Users = new ArrayList<>();
		try {
			User user = null;
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				user = new User();
				user.setId(rs.getInt(1));
				user.setLogin(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setRole(Role.getByIdentity(rs.getInt(4)));
				
				Users.add(user);
			}
		} catch(SQLException e) {
			logger.error("There is some problem after trying find into table User");
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch(SQLException | NullPointerException e) { e.printStackTrace(); }
			try {
				rs.close();
			} catch(SQLException | NullPointerException e) { e.printStackTrace(); }
		}
		return Users;
		
	}

	@Override
	public User read(String login, String password) throws PersistentException {
		String sql = "select id, role from \"User\" where login = ? and password = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, login);
			ps.setString(2, password);
			rs = ps.executeQuery();
			User user = null;
			if(rs.next()) {
				user = new User();
				user.setId(rs.getInt(1));
				user.setLogin(login);
				user.setPassword(password);
				user.setRole(Role.getByIdentity(rs.getInt(2)));
			}
			return user;
			
		}catch(SQLException e) {
			logger.error("There is some problem after trying find into table User");
			throw new PersistentException(e);
		} finally {
			try {
				rs.close();
			} catch(SQLException | NullPointerException e) {}
			try {
				ps.close();
			} catch(SQLException | NullPointerException e) {}
		}
	}
}
