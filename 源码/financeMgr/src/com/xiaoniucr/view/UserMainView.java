package com.xiaoniucr.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.xiaoniucr.dao.UserDao;

/**
 * 用户主菜单窗体
 * @author Lenovo
 *
 */
public class UserMainView extends JFrame {

	private UserDao studentDao = new UserDao();


	/**
	 * Create the frame.
	 */
	public UserMainView() {
		setTitle("用户中心");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 578, 350);
		setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu cardMenu = new JMenu("卡号管理");
		menuBar.add(cardMenu);
		
		JMenuItem cardQueryMenu = new JMenuItem("卡号信息");
		cardQueryMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UserCardManageView cardManageView = new UserCardManageView();
				cardManageView.setVisible(true);
			}
		});
		cardMenu.add(cardQueryMenu);
		
		JMenu billMenu = new JMenu("理财管理");
		menuBar.add(billMenu);
		
		JMenuItem billQueryMenu = new JMenuItem("账单记录");
		billQueryMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UserBillManageView billManageView = new UserBillManageView();
				billManageView.setVisible(true);
			}
		});
		billMenu.add(billQueryMenu);
		
		JMenu selfSettingMenu = new JMenu("个人设置");
		menuBar.add(selfSettingMenu);
		
		JMenuItem updateProfileMenu = new JMenuItem("个人资料");
		updateProfileMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserUpdateProfileView profileView = new UserUpdateProfileView();
				profileView.setVisible(true);
			}
		});
		selfSettingMenu.add(updateProfileMenu);
		
		JMenuItem updatePwdMenu = new JMenuItem("修改密码");
		updatePwdMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdatePwdView updatePwdView = new UpdatePwdView();
				updatePwdView.setVisible(true);
			}
		});
		selfSettingMenu.add(updatePwdMenu);
		
		JMenuItem exitMenu = new JMenuItem("退出系统");
		exitMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		selfSettingMenu.add(exitMenu);
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("欢迎使用个人理财管理系统");
		label.setFont(new Font("华文行楷", Font.PLAIN, 20));
		label.setBounds(160, 102, 245, 51);
		getContentPane().add(label);
	}
}
