package com.xiaoniucr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.xiaoniucr.entity.Bill;
import com.xiaoniucr.entity.Card;
import com.xiaoniucr.entity.User;
import com.xiaoniucr.util.JDBCUtils;

/**
 * 账单数据库操作
 *
 */
public class BillDao {

	
	
	/**
	 * 查询账单
	 * @param userId 用户ID
	 * @param cardId 卡号ID
	 * @param billType 账单类型：0收入，1支出
	 * @param title 账单主题
	 * @return
	 */
	public List<Bill> queryList(Integer userId,Integer cardId,Integer billType,String title){
		
		
		List<Bill> list = new ArrayList<Bill>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JDBCUtils.getConnection();
			List<Object> params = new ArrayList<>();
			StringBuffer sb = new StringBuffer("select a.*,b.nickname,c.card_no from t_bill a "
					+ "inner join t_user b on a.user_id = b.id "
					+ "inner join t_card c on a.card_id = c.id "
					+ "where 1=1 ");
			if(userId != null){
				sb.append("and a.user_id = ? ");
				params.add(userId);
			}
			if(cardId != null){
				sb.append("and a.card_id = ? ");
				params.add(cardId);
			}
			if(billType != null){
				sb.append("and a.bill_type = ? ");
				params.add(billType);
			}
			if(title != null && !"".equals(title)){
				sb.append("and a.title like ? ");
				params.add("%"+title+"%");
			}
			pstmt = con.prepareStatement(sb.toString());
			if(params != null && params.size()>0){
				for(int i=0; i<params.size(); i++){
					pstmt.setObject(i+1, params.get(i));
				}
			}
			rs = pstmt.executeQuery();
			while(rs.next()){
				Bill bill = new Bill();
				bill.setId(rs.getInt("id"));
				bill.setUserId(rs.getInt("user_id"));
				bill.setCardId(rs.getInt("card_id"));
				bill.setBillType(rs.getInt("bill_type"));
				bill.setPayDate(rs.getString("pay_date"));
				bill.setTitle(rs.getString("title"));
				bill.setAmount(rs.getDouble("amount"));
				bill.setRemark(rs.getString("remark"));
				bill.setCreateTime(rs.getTimestamp("create_time"));
				bill.setNickname(rs.getString("nickname"));
				bill.setCardNo(rs.getString("card_no"));
				list.add(bill);
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
	 * 保存账单
	 * @param bill 账单信息
	 * @return
	 */
	public boolean save(Bill bill){
		
		Connection con = null;
		String sql = "insert into t_bill(user_id,title,amount,card_id,bill_type,pay_date,remark,create_time,update_time) values(?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = null;
		try {
			con = JDBCUtils.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bill.getUserId());
			pstmt.setString(2, bill.getTitle());
			pstmt.setDouble(3, bill.getAmount());
			pstmt.setInt(4, bill.getCardId());
			pstmt.setInt(5, bill.getBillType());
			pstmt.setString(6, bill.getPayDate());
			pstmt.setString(7,bill.getRemark());
			Date date = new Date();
			pstmt.setTimestamp(8, new Timestamp(date.getTime()));
			pstmt.setTimestamp(9, new Timestamp(date.getTime()));
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
	 * 修改账单信息
	 * @param bill 账单信息
	 * @return
	 */
	public boolean update(Bill bill){
		
		Connection con = null;
		String sql = "update t_bill set title=?,amount=?,card_id=?,bill_type=?,pay_date=?,remark=?,update_time=? where id=?";
		PreparedStatement pstmt = null;
		try {
			con = JDBCUtils.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bill.getTitle());
			pstmt.setDouble(2, bill.getAmount());
			pstmt.setInt(3, bill.getCardId());
			pstmt.setInt(4, bill.getBillType());
			pstmt.setString(5, bill.getPayDate());
			pstmt.setString(6, bill.getRemark());
			Date date = new Date();
			pstmt.setTimestamp(7, new Timestamp(date.getTime()));
			pstmt.setInt(8, bill.getId());
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
	 * 删除账单信息
	 * @param id 主键ID
	 * @return
	 */
	public boolean delete(int id){
		
		Connection con = null;
		String sql = "delete from t_bill where id=?";
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
	 * 根据ID查询账单
	 * @param id 主键ID
	 * @return
	 */
	public Bill getById(int id){
		
		
		Bill bill = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JDBCUtils.getConnection();
			String sql = "select * from t_bill where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setObject(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()){
				bill = new Bill();
				bill.setId(rs.getInt("id"));
				bill.setUserId(rs.getInt("user_id"));
				bill.setCardId(rs.getInt("card_id"));
				bill.setBillType(rs.getInt("bill_type"));
				bill.setPayDate(rs.getString("pay_date"));
				bill.setTitle(rs.getString("title"));
				bill.setAmount(rs.getDouble("amount"));
				bill.setRemark(rs.getString("remark"));
				bill.setCreateTime(rs.getTimestamp("create_time"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtils.close(con, pstmt, rs);
		}
		return bill;
		
		
	}



}
