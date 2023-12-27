package com.xiaoniucr.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.xiaoniucr.dao.BillDao;
import com.xiaoniucr.dao.CardDao;
import com.xiaoniucr.entity.Bill;
import com.xiaoniucr.entity.Card;
import com.xiaoniucr.util.DataBuffer;
import com.xiaoniucr.util.DateUtil;

import sun.swing.table.DefaultTableCellHeaderRenderer;

/**
 * 
 * 献血报名申请
 *
 */
public class UserBillManageView extends JFrame {

	private JPanel contentPane;
	private JTable cardTable;
	private JTable billTable;
	private JTextField titleText;
	private JTextField cardNoText;
	private JComboBox billTypeComboBox;
	private UserBillManageView frame = this;
	private Integer cardId;
	private Integer billType;
	private String title;
	private CardDao cardDao = new CardDao();
	private BillDao billDao = new BillDao();
	



	/**
	 * Create the frame.
	 */
	public UserBillManageView() {

		setTitle("收支管理");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 856, 535);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel clubPanel = new JPanel();
		clubPanel.setBorder(
				new TitledBorder(null, "银行卡信息", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		clubPanel.setBounds(10, 16, 820, 183);
		contentPane.add(clubPanel);
		clubPanel.setLayout(null);

		JScrollPane cardScrollPane = new JScrollPane();
		cardScrollPane.setBounds(10, 59, 800, 114);
		clubPanel.add(cardScrollPane);
		
		Object[] cardColumns = { "ID", "卡号", "银行", "余额", "备注", "添加时间", };// 字段
		Object[][] clubData = null;// 需要展示的数据，一般是二维数组
		DefaultTableModel clubModel = new DefaultTableModel(clubData, cardColumns);

		DefaultTableCellRenderer clubTcr = new DefaultTableCellRenderer();// 设置table内容居中
		clubTcr.setHorizontalAlignment(SwingConstants.CENTER);// 这句和上句作用一样
		clubTcr.setVerticalAlignment(SwingConstants.CENTER);

		// 设置表头居中显示
		DefaultTableCellHeaderRenderer clubHr = new DefaultTableCellHeaderRenderer();
		clubHr.setHorizontalAlignment(JLabel.CENTER);

		cardTable = new JTable(clubModel);
		cardTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// 获取选中行
				int row = cardTable.getSelectedRow();
				if (row < 0) {
					JOptionPane.showMessageDialog(contentPane, "请选择一条记录", "系统提示", JOptionPane.WARNING_MESSAGE);
					return;
				}
				cardId = Integer.valueOf(cardTable.getValueAt(row, 0).toString());
				loadBill(cardId, null,null);
			}
		});
		cardTable.setDefaultRenderer(Object.class, clubTcr);
		cardTable.getTableHeader().setDefaultRenderer(clubHr);
		cardTable.getTableHeader().setFont(new Font("黑体", Font.PLAIN, 14));
		cardTable.getTableHeader().setPreferredSize(new Dimension(1, 30));
		// 隐藏第一列
		cardTable.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
		cardTable.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
		cardTable.getTableHeader().getColumnModel().getColumn(0).setPreferredWidth(0);

		// 加载数据
		loadCard(null);
		cardScrollPane.setViewportView(cardTable);
		
		JButton button = new JButton("查询");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardId = null;
				loadCard(cardNoText.getText());
			}
		});
		button.setBounds(165, 27, 63, 23);
		clubPanel.add(button);
		
		JLabel lblNewLabel_1 = new JLabel("卡号：");
		lblNewLabel_1.setBounds(10, 30, 48, 15);
		clubPanel.add(lblNewLabel_1);
		
		cardNoText = new JTextField();
		cardNoText.setColumns(10);
		cardNoText.setBounds(45, 28, 115, 21);
		clubPanel.add(cardNoText);

		JPanel activityPanel = new JPanel();
		activityPanel.setBorder(
				new TitledBorder(UIManager.getBorder("TitledBorder.border"), "账单信息", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		activityPanel.setBounds(10, 209, 820, 277);
		contentPane.add(activityPanel);
		activityPanel.setLayout(null);
		
		JScrollPane billScrollPane = new JScrollPane();
		billScrollPane.setBounds(10, 60, 800, 147);
		activityPanel.add(billScrollPane);
		
		Object[] applyColumns = { "ID", "账单主题", "金额", "卡号", "收支类型", "账单日期", "备注", };// 字段
		Object[][] applyData = null;// 需要展示的数据，一般是二维数组
		DefaultTableModel applyModel = new DefaultTableModel(applyData, applyColumns);
		DefaultTableCellRenderer applyTcr = new DefaultTableCellRenderer();// 设置table内容居中
		applyTcr.setHorizontalAlignment(SwingConstants.CENTER);
		applyTcr.setVerticalAlignment(SwingConstants.CENTER);
		// 设置表头居中显示
		DefaultTableCellHeaderRenderer applyHr = new DefaultTableCellHeaderRenderer();
		clubHr.setHorizontalAlignment(JLabel.CENTER);
		
		billTable = new JTable(applyModel);
		billTable.setDefaultRenderer(Object.class, applyTcr);
		billTable.getTableHeader().setDefaultRenderer(applyHr);
		billTable.getTableHeader().setFont(new Font("黑体", Font.PLAIN, 14));
		billTable.getTableHeader().setPreferredSize(new Dimension(1, 30));
		// 隐藏第一列
		billTable.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
		billTable.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
		billTable.getTableHeader().getColumnModel().getColumn(0).setPreferredWidth(0);
		loadBill(null,null,null);
		billScrollPane.setViewportView(billTable);
		
		JLabel lblNewLabel = new JLabel("账单主题：");
		lblNewLabel.setBounds(10, 31, 75, 15);
		activityPanel.add(lblNewLabel);
		
		titleText = new JTextField();
		titleText.setBounds(70, 28, 118, 21);
		activityPanel.add(titleText);
		titleText.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("账单类型：");
		lblNewLabel_2.setBounds(206, 31, 87, 15);
		activityPanel.add(lblNewLabel_2);
		
		billTypeComboBox = new JComboBox();
		billTypeComboBox.setModel(new DefaultComboBoxModel(new String[] {"请选择账单类型", "收入", "支出"}));
		billTypeComboBox.setBounds(266, 28, 118, 21);
		activityPanel.add(billTypeComboBox);
		
		JButton searchBtn = new JButton("查询");
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String title = titleText.getText();
				String billTypeStr = billTypeComboBox.getSelectedItem().toString();
				Integer billType = getBillTypeByStr(billTypeStr);
				loadBill(cardId,billType,title);
			}
		});
		searchBtn.setBounds(394, 27, 63, 23);
		activityPanel.add(searchBtn);
		
		JButton delBtn = new JButton("删除");
		delBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// 获取选中行
				int row = billTable.getSelectedRow();
				if (row < 0) {
					JOptionPane.showMessageDialog(contentPane, "请选择一条账单记录", "系统提示", JOptionPane.WARNING_MESSAGE);
					return;
				}
				int result = JOptionPane.showConfirmDialog(contentPane, "确定删除该记录吗？", "提示",
						JOptionPane.YES_NO_OPTION);
				if (result == 0) {
					int id = Integer.valueOf(billTable.getValueAt(row, 0).toString());
					boolean flag = billDao.delete(id);
					if(flag){
						JOptionPane.showMessageDialog(contentPane, "删除成功!");
						String title = titleText.getText();
						String billTypeStr = billTypeComboBox.getSelectedItem().toString();
						Integer billType = getBillTypeByStr(billTypeStr);
						loadBill(cardId,billType,title);
					}else{
						JOptionPane.showMessageDialog(contentPane, "操作失败", "系统提示", JOptionPane.WARNING_MESSAGE);
						
					}
				}
				return;
			}
		});
		delBtn.setBounds(747, 27, 63, 23);
		activityPanel.add(delBtn);
		
		
		
		JButton addBtn = new JButton("添加");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserBillAddView billAddView = new UserBillAddView(frame);
				billAddView.setVisible(true);
			}
		});
		addBtn.setBounds(584, 27, 63, 23);
		activityPanel.add(addBtn);
		
		JButton updateBtn = new JButton("修改");
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 获取选中行
				int row = billTable.getSelectedRow();
				if (row < 0) {
					JOptionPane.showMessageDialog(contentPane, "请选择一条账单记录", "系统提示", JOptionPane.WARNING_MESSAGE);
					return;
				}
				int id = Integer.valueOf(billTable.getValueAt(row, 0).toString());
				UserBillUpdateView view = new UserBillUpdateView(id,frame);
				view.setVisible(true);
			}
		});
		updateBtn.setBounds(667, 27, 63, 23);
		activityPanel.add(updateBtn);
		
		

	}

	// 填充表格数据
	public void loadCard(String cardNo) {
		List<Card> list = cardDao.queryList(DataBuffer.uid,cardNo);
		DefaultTableModel tableModel = (DefaultTableModel) cardTable.getModel();
		tableModel.setRowCount(0);// 清除原有行
		// 填充数据
		for (Card item : list) {
			String[] arr = new String[6];
			arr[0] = item.getId() + "";
			arr[1] = item.getCardNo();
			arr[2] = item.getBank();
			arr[3] = item.getBalance() + "";
			arr[4] = item.getRemark();
			arr[5] =  DateUtil.formatDate(item.getCreateTime(), "yyyy-MM-dd HH:mm:ss");
			// 添加数据到表格
			tableModel.addRow(arr);
		}
	}
	
	
	public void loadBill(Integer cardId,Integer billType,String title) {
		List<Bill> list = billDao.queryList(DataBuffer.uid, cardId, billType, title);
		DefaultTableModel tableModel = (DefaultTableModel) billTable.getModel();
		tableModel.setRowCount(0);// 清除原有行
		// 填充数据
		for (Bill item : list) {
			String[] arr = new String[7];
			arr[0] = item.getId() + "";
			arr[1] = item.getTitle();
			arr[2] = item.getAmount() + "";
			arr[3] = item.getCardNo();
			arr[4] = item.getBillType() == 0 ? "收入" : "支出";
			arr[5] = item.getPayDate();
			arr[6] = item.getRemark() == null ? "--" : item.getRemark();
			// 添加数据到表格
			tableModel.addRow(arr);
		}
	}
	
	
	private Integer getBillTypeByStr(String str){
		
		Integer billType = null;
		if("请选择账单类型".equals(str)){
			billType =  null;
		}else if("收入".equals(str)){
			billType = 0;
		}else{
			billType =  1;
		}
		return billType;
	}

	public Integer getCardId() {
		return cardTable.getSelectedRow() < 0 ? null : Integer.valueOf(cardTable.getValueAt(cardTable.getSelectedRow(), 0).toString());
	}

	public Integer getBillType() {
		return getBillTypeByStr(billTypeComboBox.getSelectedItem().toString());
	}

	public String getTitle() {
		return titleText.getText();
	}

	
}
