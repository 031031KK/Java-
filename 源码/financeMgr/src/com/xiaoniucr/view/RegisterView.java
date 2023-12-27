package com.xiaoniucr.view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.xiaoniucr.dao.UserDao;
import com.xiaoniucr.entity.User;
import com.xiaoniucr.util.DateUtil;

import javax.swing.JRadioButton;
import javax.swing.JPasswordField;
import java.awt.Font;

/**
 * 
 * 用户添加
 *
 */
public class RegisterView extends JFrame {

	private JPanel contentPane;
	private JTextField usernameText;
	private JPasswordField passwordText;
	private JTextField nicknameText;
	private JTextField birthdayText;
	private JTextField telephoneText;
	private JTextField emailText;
	private JTextField professionText;
	private UserDao userDao = new UserDao();

	/**
	 * Create the frame.
	 */
	public RegisterView() {
		
		setTitle("用户注册");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 388, 565);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("账号：");
		lblNewLabel.setBounds(79, 88, 43, 15);
		contentPane.add(lblNewLabel);

		usernameText = new JTextField();
		usernameText.setBounds(118, 85, 160, 21);
		contentPane.add(usernameText);
		usernameText.setColumns(10);
		
		
		JLabel label = new JLabel("密码：");
		label.setBounds(79, 129, 54, 15);
		contentPane.add(label);
		
		passwordText = new JPasswordField();
		passwordText.setBounds(118, 126, 160, 21);
		contentPane.add(passwordText);
		
		

		JLabel lblNewLabel_1 = new JLabel("姓名：");
		lblNewLabel_1.setBounds(79, 171, 43, 15);
		contentPane.add(lblNewLabel_1);

		nicknameText = new JTextField();
		nicknameText.setBounds(118, 168, 160, 21);
		contentPane.add(nicknameText);
		nicknameText.setColumns(10);

	

		JLabel lblNewLabel_3 = new JLabel("性别：");
		lblNewLabel_3.setBounds(79, 211, 54, 15);
		contentPane.add(lblNewLabel_3);

		final JRadioButton maleRadio = new JRadioButton("男");
		final JRadioButton femaleRadio = new JRadioButton("女");
		femaleRadio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				maleRadio.setSelected(false);
				femaleRadio.setSelected(true);
			}
		});
		maleRadio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				maleRadio.setSelected(true);
				femaleRadio.setSelected(false);
			}
		});
		maleRadio.setSelected(true);
		maleRadio.setBounds(118, 207, 54, 23);
		contentPane.add(maleRadio);

		femaleRadio.setBounds(174, 207, 64, 23);
		contentPane.add(femaleRadio);
		
		JLabel lblNewLabel_6 = new JLabel("生日：");
		lblNewLabel_6.setBounds(79, 251, 54, 15);
		contentPane.add(lblNewLabel_6);
		
		birthdayText = new JTextField();
		birthdayText.setBounds(118, 248, 160, 21);
		contentPane.add(birthdayText);
		birthdayText.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("yyyy-MM-dd");
		lblNewLabel_7.setBounds(288, 251, 74, 15);
		contentPane.add(lblNewLabel_7);
		
		
		
		
		JLabel lblNewLabel_4 = new JLabel("电话：");
		lblNewLabel_4.setBounds(79, 293, 54, 15);
		contentPane.add(lblNewLabel_4);

		telephoneText = new JTextField();
		telephoneText.setBounds(118, 290, 160, 21);
		contentPane.add(telephoneText);
		telephoneText.setColumns(10);
	
		
		JLabel lblNewLabel_5 = new JLabel("邮箱：");
		lblNewLabel_5.setBounds(79, 337, 54, 15);
		contentPane.add(lblNewLabel_5);
		
		emailText = new JTextField();
		emailText.setBounds(118, 334, 160, 21);
		contentPane.add(emailText);
		emailText.setColumns(10);
		
		
		JLabel lblNewLabel_2 = new JLabel("职业：");
		lblNewLabel_2.setBounds(79, 382, 43, 15);
		contentPane.add(lblNewLabel_2);

		professionText = new JTextField();
		professionText.setBounds(118, 379, 160, 21);
		contentPane.add(professionText);
		professionText.setColumns(10);


		// 保存
		JButton saveBtn = new JButton("保存");
		saveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String username = usernameText.getText();
				String password = passwordText.getText();
				String nickname = nicknameText.getText();
				String birthday = birthdayText.getText();
				String telephone = telephoneText.getText();
				String email = emailText.getText();
				String profession = professionText.getText();

				if (username == null || "".equals(username)) {
					JOptionPane.showMessageDialog(contentPane, "请输入账号", "系统提示", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if (password == null || "".equals(password)) {
					JOptionPane.showMessageDialog(contentPane, "请输入密码", "系统提示", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if (nickname == null || "".equals(nickname)) {
					JOptionPane.showMessageDialog(contentPane, "请输入姓名", "系统提示", JOptionPane.WARNING_MESSAGE);
					return;
				}
				Integer sex = 0;
				// 获取性别
				for (Component c : contentPane.getComponents()) {
					if (c instanceof JRadioButton) {
						if (((JRadioButton) c).isSelected()) {
							String text = ((JRadioButton) c).getText();
							if ("男".equals(text)) {
								sex = 0;
							} else {
								sex = 1;
							}
						}
					}
				}
				
				try {
					if (birthday == null || "".equals(birthday)) {
						JOptionPane.showMessageDialog(contentPane, "请输入生日", "系统提示", JOptionPane.WARNING_MESSAGE);
						return;
					}
					DateUtil.formatString(birthday, "yyyy-MM-dd");
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(contentPane, "生日格式错误，请重新输入", "系统提示", JOptionPane.WARNING_MESSAGE);
					return;
				}
			
				if (telephone == null || "".equals(telephone)) {
					JOptionPane.showMessageDialog(contentPane, "请输入电话", "系统提示", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if (email == null || "".equals(email)) {
					JOptionPane.showMessageDialog(contentPane, "请输入邮箱", "系统提示", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if (profession == null || "".equals(profession)) {
					JOptionPane.showMessageDialog(contentPane, "请输入专业", "系统提示", JOptionPane.WARNING_MESSAGE);
					return;
				}
				User user = new User();
				user.setUsername(username);
				user.setPassword(password);
				user.setNickname(nickname);
				user.setSex(sex);
				user.setBirthday(birthday);
				user.setTelephone(telephone);
				user.setEmail(email);
				user.setProfession(profession);
				boolean flag = userDao.save(user);
				if (flag) {
					dispose();
					JOptionPane.showMessageDialog(contentPane, "注册成功!");
				} else {
					JOptionPane.showMessageDialog(contentPane, "操作失败", "系统提示", JOptionPane.WARNING_MESSAGE);
				}
				return;

			}
		});
		saveBtn.setBounds(118, 430, 74, 23);
		contentPane.add(saveBtn);

		// 取消
		JButton cancleBtn = new JButton("取消");
		cancleBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancleBtn.setBounds(204, 430, 74, 23);
		contentPane.add(cancleBtn);
		
		JLabel lblNewLabel_8 = new JLabel("用户注册");
		lblNewLabel_8.setFont(new Font("幼圆", Font.BOLD, 20));
		lblNewLabel_8.setBounds(143, 35, 108, 25);
		contentPane.add(lblNewLabel_8);
		
		
		
	}
}
