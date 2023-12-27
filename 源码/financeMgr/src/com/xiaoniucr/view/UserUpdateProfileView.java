package com.xiaoniucr.view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.xiaoniucr.dao.UserDao;
import com.xiaoniucr.entity.User;
import com.xiaoniucr.util.DataBuffer;
import com.xiaoniucr.util.DateUtil;

/**
 * 
 * 个人资料修改
 *
 */
public class UserUpdateProfileView extends JFrame {

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
	public UserUpdateProfileView() {
		
		setTitle("个人信息");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 388, 495);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("账号：");
		lblNewLabel.setBounds(79, 50, 43, 15);
		contentPane.add(lblNewLabel);

		usernameText = new JTextField();
		usernameText.setBounds(118, 47, 160, 21);
		contentPane.add(usernameText);
		usernameText.setColumns(10);
		
		
		JLabel label = new JLabel("密码：");
		label.setBounds(79, 91, 54, 15);
		contentPane.add(label);
		
		passwordText = new JPasswordField();
		passwordText.setBounds(118, 88, 160, 21);
		contentPane.add(passwordText);
		
		

		JLabel lblNewLabel_1 = new JLabel("姓名：");
		lblNewLabel_1.setBounds(79, 131, 43, 15);
		contentPane.add(lblNewLabel_1);

		nicknameText = new JTextField();
		nicknameText.setBounds(118, 128, 160, 21);
		contentPane.add(nicknameText);
		nicknameText.setColumns(10);

	

		JLabel lblNewLabel_3 = new JLabel("性别：");
		lblNewLabel_3.setBounds(79, 167, 54, 15);
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
		maleRadio.setBounds(118, 163, 54, 23);
		contentPane.add(maleRadio);

		femaleRadio.setBounds(174, 163, 64, 23);
		contentPane.add(femaleRadio);
		
		JLabel lblNewLabel_6 = new JLabel("生日：");
		lblNewLabel_6.setBounds(79, 205, 54, 15);
		contentPane.add(lblNewLabel_6);
		
		birthdayText = new JTextField();
		birthdayText.setBounds(118, 202, 160, 21);
		contentPane.add(birthdayText);
		birthdayText.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("yyyy-MM-dd");
		lblNewLabel_7.setBounds(288, 205, 74, 15);
		contentPane.add(lblNewLabel_7);
		
		
		
		
		JLabel lblNewLabel_4 = new JLabel("电话：");
		lblNewLabel_4.setBounds(79, 252, 54, 15);
		contentPane.add(lblNewLabel_4);

		telephoneText = new JTextField();
		telephoneText.setBounds(118, 249, 160, 21);
		contentPane.add(telephoneText);
		telephoneText.setColumns(10);
	
		
		JLabel lblNewLabel_5 = new JLabel("邮箱：");
		lblNewLabel_5.setBounds(79, 295, 54, 15);
		contentPane.add(lblNewLabel_5);
		
		emailText = new JTextField();
		emailText.setBounds(118, 292, 160, 21);
		contentPane.add(emailText);
		emailText.setColumns(10);
		
		
		JLabel lblNewLabel_2 = new JLabel("职业：");
		lblNewLabel_2.setBounds(79, 341, 43, 15);
		contentPane.add(lblNewLabel_2);

		professionText = new JTextField();
		professionText.setBounds(118, 338, 160, 21);
		contentPane.add(professionText);
		professionText.setColumns(10);
	
		
		try {
			User user = userDao.getById(DataBuffer.uid);
			usernameText.setText(user.getUsername());
			passwordText.setText(user.getPassword());
			nicknameText.setText(user.getNickname());
			if(user.getSex() == 0){
				maleRadio.setSelected(true);
				femaleRadio.setSelected(false);
			}else{
				maleRadio.setSelected(false);
				femaleRadio.setSelected(true);
			}
			birthdayText.setText(user.getBirthday());
			telephoneText.setText(user.getTelephone());
			emailText.setText(user.getEmail());
			professionText.setText(user.getProfession());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
				User user = userDao.getById(DataBuffer.uid);
				if(!user.getUsername().equals(username)){
					User exist = userDao.getByUsername(username);
					if(exist != null){
						JOptionPane.showMessageDialog(contentPane, "账号已存在", "系统提示", JOptionPane.WARNING_MESSAGE);
						return;
					}
				}
				user.setUsername(username);
				user.setPassword(password);
				user.setNickname(nickname);
				user.setSex(sex);
				user.setBirthday(birthday);
				user.setTelephone(telephone);
				user.setEmail(email);
				user.setProfession(profession);
				boolean flag = userDao.update(user);
				if (flag) {
					JOptionPane.showMessageDialog(contentPane, "保存成功!");
				} else {
					JOptionPane.showMessageDialog(contentPane, "操作失败", "系统提示", JOptionPane.WARNING_MESSAGE);
				}
				return;

			}
		});
		saveBtn.setBounds(118, 385, 74, 23);
		contentPane.add(saveBtn);

		// 取消
		JButton cancleBtn = new JButton("取消");
		cancleBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancleBtn.setBounds(204, 385, 74, 23);
		contentPane.add(cancleBtn);
	
		
	}
}
