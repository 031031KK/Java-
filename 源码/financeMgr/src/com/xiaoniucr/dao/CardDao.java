package com.xiaoniucr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.xiaoniucr.entity.Card;
import com.xiaoniucr.entity.User;
import com.xiaoniucr.util.JDBCUtils;

/**
 * 银行卡数据库操作
 *
 */
public class CardDao {

	
	
	/**
	 * 查询银行卡
	 * @param userId 用户ID
	 * @param cardNo 卡号
	 * @return
	 */
	public List<Card> queryList(Integer userId,String cardNo){
		
		
		List<Card> list = new ArrayList<Card>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JDBCUtils.getConnection();
			List<Object> params = new ArrayList<>();
			StringBuffer sb = new StringBuffer("select a.*,b.nickname "
					+ "from t_card a "
					+ "inner join t_user b on a.user_id = b.id "
					+ "where 1=1 ");
			if(userId != null){
				sb.append("and a.user_id = ? ");
				params.add(userId);
			}
			if(cardNo != null && !"".equals(cardNo)){
				sb.append("and a.card_no like ? ");
				params.add("%"+cardNo+"%");
			}
			pstmt = con.prepareStatement(sb.toString());
			if(params != null && params.size()>0){
				for(int i=0; i<params.size(); i++){
					pstmt.setObject(i+1, params.get(i));
				}
			}
			rs = pstmt.executeQuery();
			while(rs.next()){
				Card card = new Card();
				card.setId(rs.getInt("id"));
				card.setUserId(rs.getInt("user_id"));
				card.setNickname(rs.getString("nickname"));
				card.setCardNo(rs.getString("card_no"));
				card.setBank(rs.getString("bank"));
				card.setBalance(rs.getDouble("balance"));
				card.setRemark(rs.getString("remark"));
				card.setCreateTime(rs.getTimestamp("create_time"));
				list.add(card);
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
	 * 保存银行卡
	 * @param card 银行卡信息
	 * @return
	 */
	public boolean save(Card card){
		
		Connection con = null;
		String sql = "insert into t_card(user_id,card_no,bank,balance,remark,create_time,update_time) values(?,?,?,?,?,?,?)";
		PreparedStatement pstmt = null;
		try {
			con = JDBCUtils.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, card.getUserId());
			pstmt.setString(2, card.getCardNo());
			pstmt.setString(3, card.getBank());
			pstmt.setDouble(4, card.getBalance());
			pstmt.setString(5, card.getRemark());
			Date date = new Date();
			pstmt.setTimestamp(6, new Timestamp(date.getTime()));
			pstmt.setTimestamp(7, new Timestamp(date.getTime()));
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
	 * 修改银行卡信息
	 * @param card 银行卡信息
	 * @return
	 */
	public boolean update(Card card){
		
		Connection con = null;
		String sql = "update t_card set card_no=?,bank=?,balance=?,remark=?,update_time=? where id=?";
		PreparedStatement pstmt = null;
		try {
			con = JDBCUtils.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, card.getCardNo());
			pstmt.setString(2, card.getBank());
			pstmt.setDouble(3, card.getBalance());
			pstmt.setString(4, card.getRemark());
			Date date = new Date();
			pstmt.setTimestamp(5, new Timestamp(date.getTime()));
			pstmt.setInt(6, card.getId());
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
	 * 删除银行卡信息
	 * @param id 主键ID
	 * @return
	 */
	public boolean delete(int id){
		
		Connection con = null;
		String sql = "delete from t_card where id=?";
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
	 * 根据ID查询银行卡
	 * @param id 主键ID
	 * @return
	 */
	public Card getById(int id){
		
		
		Card card = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JDBCUtils.getConnection();
			String sql = "select * from t_card where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setObject(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()){
				card = new Card();
				card.setId(rs.getInt("id"));
				card.setUserId(rs.getInt("user_id"));
				card.setCardNo(rs.getString("card_no"));
				card.setBank(rs.getString("bank"));
				card.setBalance(rs.getDouble("balance"));
				card.setRemark(rs.getString("remark"));
				card.setCreateTime(rs.getTimestamp("create_time"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtils.close(con, pstmt, rs);
		}
		return card;
		
		
	}
	
	
	
	/**
	 * 根据ID查询银行卡
	 * @param cardNo 卡号
	 * @return
	 */
	public Card getByCardNo(String cardNo){
		
		
		Card card = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JDBCUtils.getConnection();
			String sql = "select * from t_card where card_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setObject(1, cardNo);
			rs = pstmt.executeQuery();
			while(rs.next()){
				card = new Card();
				card.setId(rs.getInt("id"));
				card.setUserId(rs.getInt("user_id"));
				card.setCardNo(rs.getString("card_no"));
				card.setBank(rs.getString("bank"));
				card.setBalance(rs.getDouble("balance"));
				card.setRemark(rs.getString("remark"));
				card.setCreateTime(rs.getTimestamp("create_time"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtils.close(con, pstmt, rs);
		}
		return card;
		
		
	}
	
	
	/**
	 * 银行卡余额更新
	 * @param card 银行卡信息
	 * @return
	 */
	public boolean updateBalance(Integer id,Double balance){
		
		Connection con = null;
		String sql = "update t_card set balance=? where id=?";
		PreparedStatement pstmt = null;
		try {
			con = JDBCUtils.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setDouble(1, balance);
			pstmt.setInt(2, id);
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
