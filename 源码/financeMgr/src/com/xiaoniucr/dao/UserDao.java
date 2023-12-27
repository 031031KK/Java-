package com.xiaoniucr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.xiaoniucr.entity.User;
import com.xiaoniucr.util.JDBCUtils;

/**
 * 用户数据库操作
 *
 */
public class UserDao {
	
	
	/**
	 * 用户登录
	 * @param username 账号
	 * @param password 密码
	 * @return
	 */
	public User login(String username,String password){
		
		
		User user = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JDBCUtils.getConnection();
			String sql = "select * from t_user where username = ? and password = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setObject(1, username);
			pstmt.setObject(2, password);
			rs = pstmt.executeQuery();
			while(rs.next()){
				user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setNickname(rs.getString("nickname"));
				user.setSex(rs.getInt("sex"));
				user.setBirthday(rs.getString("birthday"));
				user.setTelephone(rs.getString("telephone"));
				user.setEmail(rs.getString("email"));
				user.setProfession(rs.getString("profession"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtils.close(con, pstmt, rs);
		}
		return user;
		
		
	}
	
	
	/**
	 * 查询用户列表
	 * @param username 账号
	 * @param nickname 姓名
	 * @return
	 */
	public List<User> queryList(String username,String nickname){
		
		
		List<User> list = new ArrayList<User>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JDBCUtils.getConnection();
			List<Object> params = new ArrayList<>();
			StringBuffer sb = new StringBuffer("select * from t_user where 1=1 ");
			if(nickname != null && !"".equals(username)){
				sb.append("and username like ? ");
				params.add("%"+username+"%");
			}
			if(nickname != null && !"".equals(nickname)){
				sb.append("and nickname like ? ");
				params.add("%"+nickname+"%");
			}
			pstmt = con.prepareStatement(sb.toString());
			if(params != null && params.size()>0){
				for(int i=0; i<params.size(); i++){
					pstmt.setObject(i+1, params.get(i));
				}
			}
			rs = pstmt.executeQuery();
			while(rs.next()){
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setNickname(rs.getString("nickname"));
				user.setSex(rs.getInt("sex"));
				user.setBirthday(rs.getString("birthday"));
				user.setTelephone(rs.getString("telephone"));
				user.setEmail(rs.getString("email"));
				user.setProfession(rs.getString("profession"));
				list.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtils.close(con, pstmt, rs);
		}
		return list;
		
		
	}
	
	
	/**
	 * 保存用户信息
	 * @param user 用户信息
	 * @return
	 */
	public boolean save(User user){
		
		Connection con = null;
		String sql = "insert into t_user(username,password,nickname,sex,birthday,telephone,email,profession,create_time,update_time) values(?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = null;
		try {
			con = JDBCUtils.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getNickname());
			pstmt.setInt(4, user.getSex());
			pstmt.setString(5, user.getBirthday());
			pstmt.setString(6, user.getTelephone());
			pstmt.setString(7, user.getEmail());
			pstmt.setString(8, user.getProfession());
			Date date = new Date();
			pstmt.setTimestamp(9, new Timestamp(date.getTime()));
			pstmt.setTimestamp(10, new Timestamp(date.getTime()));
			int rows = pstmt.executeUpdate();
			if(rows > 0){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtils.close(con, pstmt, null);
		}
		return false;
	
	}
	
	
	
	/**
	 * 修改用户信息
	 * @param user 用户信息
	 * @return
	 */
	public boolean update(User user){
		
		Connection con = null;
		String sql = "update t_user set username=?,password=?,nickname=?,sex=?,birthday=?,telephone=?,email=?,profession=?,update_time=? where id=?";
		PreparedStatement pstmt = null;
		try {
			con = JDBCUtils.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getNickname());
			pstmt.setInt(4, user.getSex());
			pstmt.setString(5, user.getBirthday());
			pstmt.setString(6, user.getTelephone());
			pstmt.setString(7, user.getEmail());
			pstmt.setString(8, user.getProfession());
			pstmt.setTimestamp(9, new Timestamp(new Date().getTime()));
			pstmt.setInt(10, user.getId());
			int rows = pstmt.executeUpdate();
			if(rows > 0){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtils.close(con, pstmt, null);
		}
		return false;
	
	}
	
	
	
	
	/**
	 * 删除用户信息
	 * @param id 主键ID
	 * @return
	 */
	public boolean delete(int id){
		
		Connection con = null;
		String sql = "delete from t_user where id=?";
		PreparedStatement pstmt = null;
		try {
			con = JDBCUtils.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			int rows = pstmt.executeUpdate();
			if(rows > 0){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtils.close(con, pstmt, null);
		}
		return false;
	
	}
	
	
	
	/**
	 * 根据ID查询用户
	 * @param id 主键ID
	 * @return
	 */
	public User getById(int id){
		
		
		User user = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JDBCUtils.getConnection();
			String sql = "select * from t_user where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setObject(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()){
				user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setNickname(rs.getString("nickname"));
				user.setSex(rs.getInt("sex"));
				user.setBirthday(rs.getString("birthday"));
				user.setTelephone(rs.getString("telephone"));
				user.setEmail(rs.getString("email"));
				user.setProfession(rs.getString("profession"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtils.close(con, pstmt, rs);
		}
		return user;
		
		
	}
	
	
	
	/**
	 * 根据学号查询用户
	 * @param username 账号
	 * @return
	 */
	public User getByUsername(String username){
		
		
		User user = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JDBCUtils.getConnection();
			String sql = "select * from t_user where username = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setObject(1, username);
			rs = pstmt.executeQuery();
			while(rs.next()){
				user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setNickname(rs.getString("nickname"));
				user.setSex(rs.getInt("sex"));
				user.setBirthday(rs.getString("birthday"));
				user.setTelephone(rs.getString("telephone"));
				user.setEmail(rs.getString("email"));
				user.setProfession(rs.getString("profession"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtils.close(con, pstmt, rs);
		}
		return user;
		
		
	}
	
	
	
	
	
	/**
	 * 修改用户密码
	 * @param user 用户信息
	 * @return
	 */
	public boolean updatePwd(User user){
		
		Connection con = null;
		String sql = "update t_user set password=? where id=?";
		PreparedStatement pstmt = null;
		try {
			con = JDBCUtils.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getPassword());
			pstmt.setInt(2, user.getId());
			int rows = pstmt.executeUpdate();
			if(rows > 0){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtils.close(con, pstmt, null);
		}
		return false;
	
	}
	


}
