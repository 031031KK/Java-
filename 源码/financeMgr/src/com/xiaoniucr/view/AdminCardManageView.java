package com.xiaoniucr.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
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

import com.xiaoniucr.dao.CardDao;
import com.xiaoniucr.entity.Card;
import com.xiaoniucr.util.DataBuffer;
import com.xiaoniucr.util.DateUtil;

import sun.swing.table.DefaultTableCellHeaderRenderer;

/**
 * 
 * 活动管理
 *
 */
public class AdminCardManageView extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField titleText;
	private AdminCardManageView frame = this;
	private CardDao cardDao = new CardDao();

	/**
	 * Create the frame.
	 */
	public AdminCardManageView() {

		setTitle("银行卡管理");
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

		Object[] columns = { "ID", "姓名", "卡号", "银行", "余额", "备注", "添加时间", };// 字段
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
		load(null);
		
		scrollPane.setViewportView(table);

		JLabel lblNewLabel = new JLabel("卡号：");
		lblNewLabel.setBounds(10, 11, 48, 15);
		contentPane.add(lblNewLabel);

		titleText = new JTextField();
		titleText.setBounds(46, 8, 115, 21);
		contentPane.add(titleText);
		titleText.setColumns(10);

		//查询按钮
		JButton searchBtn = new JButton("查询");
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				load(titleText.getText());
			}
		});
		searchBtn.setBounds(171, 6, 63, 23);
		contentPane.add(searchBtn);

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
					boolean flag = cardDao.delete(id);
					if(flag){
						JOptionPane.showMessageDialog(contentPane, "删除成功!");
						load(null);
					}else{
						JOptionPane.showMessageDialog(contentPane, "操作失败", "系统提示", JOptionPane.WARNING_MESSAGE);
						
					}
				}
				return;
			}
		});
		deleteBtn.setBounds(767, 6, 63, 23);
		contentPane.add(deleteBtn);
	}

	// 填充表格数据
	public void load(String cardNo){
		List<Card> list = cardDao.queryList(null,cardNo);
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.setRowCount(0);// 清除原有行
		// 填充数据
		for (Card item : list) {
			String[] arr = new String[7];
			arr[0] = item.getId() + "";
			arr[1] = item.getNickname();
			arr[2] = item.getCardNo();
			arr[3] = item.getBank();
			arr[4] = item.getBalance() + "";
			arr[5] = item.getRemark();
			arr[6] =  DateUtil.formatDate(item.getCreateTime(), "yyyy-MM-dd HH:mm:ss");
			// 添加数据到表格
			tableModel.addRow(arr);
		}
	}
}
