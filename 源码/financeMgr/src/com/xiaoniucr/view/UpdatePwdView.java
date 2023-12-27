package com.xiaoniucr.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import com.xiaoniucr.dao.AdminDao;
import com.xiaoniucr.dao.UserDao;
import com.xiaoniucr.entity.Admin;
import com.xiaoniucr.entity.User;
import com.xiaoniucr.util.DataBuffer;
import com.xiaoniucr.util.StringUtil;


/**
 * 修改密码通用界面
 * @author Lenovo
 *
 */
public class UpdatePwdView extends JFrame implements ActionListener {

	private JPanel backgroundPanel;
	private JPasswordField oldpwdField,newpwdField,repeatpwdField;
	private JButton saveBtn,cancleBtn;
	private AdminDao adminDao = new AdminDao();
	private UserDao studentDao = new UserDao();
	
	
	

	/**
	 * Create the frame.
	 */
	public UpdatePwdView() {
		
		
		backgroundPanel = new JPanel();
		backgroundPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		backgroundPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("原始密码：");
		lblNewLabel.setBounds(64, 34, 75, 15);
		backgroundPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("新密码：");
		lblNewLabel_1.setBounds(75, 84, 57, 15);
		backgroundPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_4 = new JLabel("确认密码：");
		lblNewLabel_4.setBounds(64, 134, 68, 15);
		backgroundPanel.add(lblNewLabel_4);
			
		
		
		oldpwdField = new JPasswordField();
		oldpwdField.setBounds(124, 31, 158, 21);
		backgroundPanel.add(oldpwdField);
		
		newpwdField = new JPasswordField();
		newpwdField.setBounds(124, 81, 158, 21);
		backgroundPanel.add(newpwdField);
		
		repeatpwdField = new JPasswordField();
		repeatpwdField.setBounds(124, 131, 158, 21);
		backgroundPanel.add(repeatpwdField);
		
		
		saveBtn = new JButton("保存");
		saveBtn.addActionListener(this);
		saveBtn.setBounds(124, 176, 74, 23);
		backgroundPanel.add(saveBtn);
		
		cancleBtn = new JButton("取消");
		cancleBtn.addActionListener(this);
		cancleBtn.setBounds(208, 176, 74, 23);
		cancleBtn.addActionListener(this);
		backgroundPanel.add(cancleBtn);
		
		getContentPane().add(backgroundPanel);
		this.setTitle("密码修改");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setBounds(100, 100, 392, 275);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		
	}




	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == saveBtn){
			updatePwd();
		}else if(e.getSource() == cancleBtn){
			this.dispose();
		}
	}
	
	
	private void updatePwd(){
		
		try {
			String oldpwd = oldpwdField.getText();
			String newpwd = newpwdField.getText();
			String repeatpwd = repeatpwdField.getText();
			if(StringUtil.isEmpty(oldpwd)){
				JOptionPane.showMessageDialog(backgroundPanel, "原始密码不能为空", "系统提示",JOptionPane.WARNING_MESSAGE);
				return;
			}
			if(StringUtil.isEmpty(newpwd)){
				JOptionPane.showMessageDialog(backgroundPanel, "新密码不能为空", "系统提示",JOptionPane.WARNING_MESSAGE);
				return;
			}
			if(!repeatpwd.equals(newpwd)){
				JOptionPane.showMessageDialog(backgroundPanel, "确认密码必须和密码一致", "系统提示",JOptionPane.WARNING_MESSAGE);
				return;
			}
			if(!oldpwd.equals(DataBuffer.password)){
				JOptionPane.showMessageDialog(backgroundPanel, "原始密码错误", "系统提示",JOptionPane.WARNING_MESSAGE);
				return;
			}
			Integer role = DataBuffer.role;
			if(role == 0){
				Admin admin = new Admin();
				admin.setId(DataBuffer.uid);
				admin.setPassword(newpwd);
				boolean flag = adminDao.updatePwd(admin);
				if(flag){
					DataBuffer.password = newpwd;
					JOptionPane.showMessageDialog(backgroundPanel, "修改成功!");
					dispose();
				}else{
					JOptionPane.showMessageDialog(backgroundPanel, "修改失败!", "系统提示",JOptionPane.WARNING_MESSAGE);
					return;
				}
			}else{
				User user = new User();
				user.setId(DataBuffer.uid);
				user.setPassword(newpwd);
				boolean flag = studentDao.updatePwd(user);
				if(flag){
					DataBuffer.password = newpwd;
					JOptionPane.showMessageDialog(backgroundPanel, "保存成功!");
					dispose();
				}else{
					JOptionPane.showMessageDialog(backgroundPanel, "操作失败!", "系统提示",JOptionPane.WARNING_MESSAGE);
					return;
				}
			}
	
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
			JOptionPane.showMessageDialog(backgroundPanel, "操作异常："+e2.getMessage(), "系统提示",JOptionPane.WARNING_MESSAGE);
		}
		
		
	}
}
