package com.xiaoniucr.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.xiaoniucr.dao.AdminDao;
import com.xiaoniucr.dao.UserDao;
import com.xiaoniucr.entity.Admin;
import com.xiaoniucr.entity.User;
import com.xiaoniucr.util.DataBuffer;
import com.xiaoniucr.util.StringUtil;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

/**
 * 登录界面
 *
 */
public class LoginView extends JFrame {

	private JPanel contentPane;
	private JTextField usernameText;
	private JPasswordField passwordText;
	private AdminDao adminDao = new AdminDao();
	private UserDao studentDao = new UserDao();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginView frame = new LoginView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	

	/**
	 * Create the frame.
	 */
	public LoginView() {
		setTitle("个人理财管理系统");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 313, 509);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("系统登录");
		lblNewLabel.setFont(new Font("幼圆", Font.BOLD, 20));
		lblNewLabel.setBounds(105, 30, 108, 25);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("账号");
		lblNewLabel_1.setFont(new Font("幼圆", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(67, 97, 54, 15);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("密码");
		lblNewLabel_2.setFont(new Font("幼圆", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(67, 163, 54, 15);
		contentPane.add(lblNewLabel_2);
		
		usernameText = new JTextField();
		usernameText.setBounds(67, 115, 166, 21);
		contentPane.add(usernameText);
		usernameText.setColumns(10);
		
		passwordText = new JPasswordField();
		passwordText.setBounds(67, 180, 166, 21);
		contentPane.add(passwordText);
		
		JLabel lblNewLabel_3 = new JLabel("角色");
		lblNewLabel_3.setFont(new Font("幼圆", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(67, 222, 54, 15);
		contentPane.add(lblNewLabel_3);
		
		final JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("幼圆", Font.PLAIN, 12));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"请选择角色", "用户", "管理员"}));
		comboBox.setBounds(67, 242, 166, 21);
		contentPane.add(comboBox);
		
		JButton btnNewButton = new JButton("登录");
		btnNewButton.setFont(new Font("幼圆", Font.BOLD, 12));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String username = usernameText.getText();
				String password = passwordText.getText();
				String roleText = comboBox.getSelectedItem().toString();
				if(StringUtil.isEmpty(username)){
					JOptionPane.showMessageDialog(contentPane, "请输入账号", "系统提示",JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(StringUtil.isEmpty(password)){
					JOptionPane.showMessageDialog(contentPane, "请输入密码", "系统提示",JOptionPane.WARNING_MESSAGE);
					return;
				}
				if("请选择角色".equals(roleText)){
					JOptionPane.showMessageDialog(contentPane, "请选择角色", "系统提示",JOptionPane.WARNING_MESSAGE);
					return;
				}
				try {
					Integer role = null;
					if("管理员".equals(roleText)){
						role = 0;
					}else if("用户".equals(roleText)){
						role = 1;
					}
					DataBuffer.role = role;
					if(role == 0){
						Admin admin = adminDao.login(username,password);
						if(admin == null){
							JOptionPane.showMessageDialog(contentPane, "用户名密码错误!", "系统提示",JOptionPane.WARNING_MESSAGE);
							return;
						}
						DataBuffer.uid = admin.getId();
						DataBuffer.password = admin.getPassword();
						AdminMainFrame adminMainFrame = new AdminMainFrame();
						adminMainFrame.setVisible(true);
						dispose();
					}else{
						User user = studentDao.login(username, password);
						if(user == null){
							JOptionPane.showMessageDialog(contentPane, "账号或密码错误!", "系统提示",JOptionPane.WARNING_MESSAGE);
							return;
						}

						DataBuffer.uid = user.getId();
						DataBuffer.password = user.getPassword();
						//跳转主界面
						JOptionPane.showMessageDialog(contentPane, "登录成功!");
						if(role == 1){
							UserMainView mainView = new UserMainView();
							mainView.setVisible(true);
						}else{
							UserMainView mainView = new UserMainView();
							mainView.setVisible(true);
						}
						dispose();
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(contentPane, "登录异常："+e1.getMessage(), "系统提示",JOptionPane.WARNING_MESSAGE);
					return;
				}
			}
		});
		btnNewButton.setBounds(67, 294, 166, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("退出");
		btnNewButton_1.setFont(new Font("幼圆", Font.BOLD, 12));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setBounds(67, 399, 166, 23);
		contentPane.add(btnNewButton_1);
		
		JButton registerBtn = new JButton("注册");
		registerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RegisterView registerView = new RegisterView();
				registerView.setVisible(true);
			}
		});
		registerBtn.setFont(new Font("幼圆", Font.BOLD, 12));
		registerBtn.setBounds(67, 349, 166, 23);
		contentPane.add(registerBtn);
	
		
	}
}
