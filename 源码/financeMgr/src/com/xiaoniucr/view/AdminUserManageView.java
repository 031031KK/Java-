package com.xiaoniucr.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.xiaoniucr.dao.UserDao;
import com.xiaoniucr.entity.User;

import sun.swing.table.DefaultTableCellHeaderRenderer;

/**
 * 
 * 用户管理
 *
 */
public class AdminUserManageView extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField usernameText;
	private JTextField nicknameText;
	private AdminUserManageView frame = this;

	private UserDao userDao = new UserDao();

	/**
	 * Create the frame.
	 */
	public AdminUserManageView() {

		setTitle("用户列表");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 856, 337);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 39, 826, 232);
		contentPane.add(scrollPane);

		Object[] columns = { "ID", "账号", "姓名", "性别", "生日", "电话", "邮箱", "职业"};// 字段
		Object[][] data = null;// 需要展示的数据，一般是二维数组
		DefaultTableModel model = new DefaultTableModel(data, columns);
		table = new JTable(model);
		
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();// 设置table内容居中
		tcr.setHorizontalAlignment(SwingConstants.CENTER);// 这句和上句作用一样
		tcr.setVerticalAlignment(SwingConstants.CENTER);

		table.setDefaultRenderer(Object.class, tcr);

		// 设置表头居中显示
		DefaultTableCellHeaderRenderer hr = new DefaultTableCellHeaderRenderer();
		hr.setHorizontalAlignment(JLabel.CENTER);
		table.getTableHeader().setDefaultRenderer(hr);
		table.getTableHeader().setFont(new Font("黑体", Font.PLAIN, 14));
		table.getTableHeader().setPreferredSize(new Dimension(1, 30));
		//隐藏第一列
		table.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
		table.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
		table.getTableHeader().getColumnModel().getColumn(0).setPreferredWidth(0);
		
		//加载学生数据
		load(null,null);
		
		scrollPane.setViewportView(table);

		JLabel lblNewLabel = new JLabel("账号：");
		lblNewLabel.setBounds(10, 11, 42, 15);
		contentPane.add(lblNewLabel);

		usernameText = new JTextField();
		usernameText.setBounds(47, 8, 115, 21);
		contentPane.add(usernameText);
		usernameText.setColumns(10);

		//查询按钮
		JButton searchBtn = new JButton("查询");
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				load(usernameText.getText(),nicknameText.getText());
			}
		});
		searchBtn.setBounds(338, 6, 63, 23);
		contentPane.add(searchBtn);

		//添加按钮
		JButton addBtn = new JButton("添加");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminUserAddView view = new AdminUserAddView(frame);
				view.setVisible(true);
			}
		});
		addBtn.setBounds(621, 6, 63, 23);
		contentPane.add(addBtn);

		//修改按钮
		JButton updateBtn = new JButton("修改");
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 获取选中行
				int row = table.getSelectedRow();
				if (row < 0) {
					JOptionPane.showMessageDialog(contentPane, "请选择一条记录", "系统提示", JOptionPane.WARNING_MESSAGE);
					return;
				}
				int id = Integer.valueOf(table.getValueAt(row, 0).toString());
				AdminUserUpdateView view = new AdminUserUpdateView(id,frame);
				view.setVisible(true);
				
			}
		});
		updateBtn.setBounds(694, 6, 63, 23);

		//删除按钮
		JButton deleteBtn = new JButton("删除");
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 获取选中行
				int row = table.getSelectedRow();
				if (row < 0) {
					JOptionPane.showMessageDialog(contentPane, "请选择一条记录", "系统提示", JOptionPane.WARNING_MESSAGE);
					return;
				}
				int result = JOptionPane.showConfirmDialog(contentPane, "确定删除该记录吗？", "提示",
						JOptionPane.YES_NO_OPTION);
				if (result == 0) {
					int id = Integer.valueOf(table.getValueAt(row, 0).toString());
					boolean flag = userDao.delete(id);
					if(flag){
						JOptionPane.showMessageDialog(contentPane, "删除成功!");
						load(null,null);
					}else{
						JOptionPane.showMessageDialog(contentPane, "操作失败", "系统提示", JOptionPane.WARNING_MESSAGE);
						
					}
				}
				return;
			}
		});
		deleteBtn.setBounds(767, 6, 63, 23);
		contentPane.add(deleteBtn);
		contentPane.add(updateBtn);
		
		JLabel lblNewLabel_1 = new JLabel("姓名：");
		lblNewLabel_1.setBounds(181, 10, 54, 15);
		contentPane.add(lblNewLabel_1);
		
		nicknameText = new JTextField();
		nicknameText.setBounds(217, 7, 115, 21);
		contentPane.add(nicknameText);
		nicknameText.setColumns(10);
	}

	// 填充表格数据
	public void load(String username,String nickname){
		List<User> list = userDao.queryList(username,nickname);
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.setRowCount(0);// 清除原有行
		// 填充数据
		for (User item : list) {
			String[] arr = new String[8];
			arr[0] = item.getId() + "";
			arr[1] = item.getUsername();
			arr[2] = item.getNickname();
			arr[3] = item.getSex() == 0 ? "男":"女";
			arr[4] = item.getBirthday();
			arr[5] = item.getTelephone();
			arr[6] = item.getEmail();
			arr[7] = item.getProfession();
			// 添加数据到表格
			tableModel.addRow(arr);
		}
	}
}
