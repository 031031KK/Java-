package com.xiaoniucr.view;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;

/**
 * 管理员主菜单窗体
 * @author Lenovo
 *
 */
public class AdminMainFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public AdminMainFrame() {
		setTitle("管理员中心");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 578, 350);
		setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu userMenu = new JMenu("用户管理");
		menuBar.add(userMenu);
		
		JMenuItem userQueryMenu = new JMenuItem("志愿者信息");
		userQueryMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminUserManageView studentManageView = new AdminUserManageView();
				studentManageView.setVisible(true);
			}
		});
		userMenu.add(userQueryMenu);
		
		JMenu cardMenu = new JMenu("卡号管理");
		menuBar.add(cardMenu);
		
		JMenuItem cardQueryMenu = new JMenuItem("卡号信息");
		cardQueryMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AdminCardManageView cardManageView = new AdminCardManageView();
				cardManageView.setVisible(true);
			}
		});
		cardMenu.add(cardQueryMenu);
		
		JMenu billMenu = new JMenu("理财管理");
		menuBar.add(billMenu);
		
		JMenuItem billQueryMenu = new JMenuItem("账单记录");
		billQueryMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminBillManageView billManageView = new AdminBillManageView();
				billManageView.setVisible(true);
			}
		});
		billMenu.add(billQueryMenu);
		
		
		JMenu selfSettingMenu = new JMenu("个人设置");
		menuBar.add(selfSettingMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("修改密码");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UpdatePwdView updatePwdView = new UpdatePwdView();
				updatePwdView.setVisible(true);
			}
		});
		selfSettingMenu.add(mntmNewMenuItem);
		
		JMenuItem exitMenu = new JMenuItem("退出系统");
		exitMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		selfSettingMenu.add(exitMenu);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel label = new JLabel("欢迎使用个人理财管理系统");
		label.setFont(new Font("华文行楷", Font.PLAIN, 20));
		label.setBounds(166, 94, 245, 51);
		contentPane.add(label);
	}
}
