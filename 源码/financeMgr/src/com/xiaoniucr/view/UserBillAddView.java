package com.xiaoniucr.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;


import com.xiaoniucr.dao.BillDao;
import com.xiaoniucr.dao.CardDao;
import com.xiaoniucr.entity.Bill;
import com.xiaoniucr.entity.Card;
import com.xiaoniucr.entity.User;
import com.xiaoniucr.util.DataBuffer;
import com.xiaoniucr.util.DateUtil;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;


/**
 * 
 * 账单添加
 *
 */
public class UserBillAddView extends JFrame {

	private JPanel contentPane;
	private JTextField titleText;
	private JTextField amountText;
	private JTextField payDateText;
	private CardDao cardDao = new CardDao();
	private BillDao billDao = new BillDao();

	/**
	 * Create the frame.
	 */
	public UserBillAddView(final UserBillManageView frame) {
		
		setTitle("账单添加");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 388, 507);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel("账单主题：");
		lblNewLabel.setBounds(51, 60, 82, 15);
		contentPane.add(lblNewLabel);
		
		titleText = new JTextField();
		titleText.setBounds(118, 57, 160, 21);
		contentPane.add(titleText);
		titleText.setColumns(10);
		
		JLabel lblNewLabel_2_1 = new JLabel("账单日期：");
		lblNewLabel_2_1.setBounds(50, 105, 83, 15);
		contentPane.add(lblNewLabel_2_1);
		
		payDateText = new JTextField();
		payDateText.setBounds(118, 102, 160, 21);
		contentPane.add(payDateText);
		payDateText.setColumns(10);
		
		
		JLabel lblNewLabel_2 = new JLabel("银行卡号：");
		lblNewLabel_2.setBounds(51, 154, 83, 15);
		contentPane.add(lblNewLabel_2);
		
		JComboBox cardComboBox = new JComboBox();
		cardComboBox.setBounds(118, 151, 160, 21);
		try {
			List<Card> cardList = cardDao.queryList(DataBuffer.uid,null);
			Card c = new Card();
			c.setId(0);
			c.setCardNo("请选择银行卡号");
			cardComboBox.addItem(c);
			if (cardList != null && cardList.size() > 0) {
				for (Card card : cardList) {
					cardComboBox.addItem(card);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		contentPane.add(cardComboBox);
	
		JLabel label = new JLabel("账单类型：");
		label.setBounds(51, 197, 82, 15);
		contentPane.add(label);
		
		JComboBox billTypeComboBox = new JComboBox();
		billTypeComboBox.setModel(new DefaultComboBoxModel(new String[] {"请选择账单类型", "收入", "支出"}));
		billTypeComboBox.setBounds(118, 194, 160, 21);
		contentPane.add(billTypeComboBox);
		
		
		JLabel lblNewLabel_3 = new JLabel("账单金额：");
		lblNewLabel_3.setBounds(51, 244, 82, 15);
		contentPane.add(lblNewLabel_3);
		
		amountText = new JTextField();
		amountText.setBounds(118, 241, 160, 21);
		contentPane.add(amountText);
		amountText.setColumns(10);
		
		
		JLabel lblNewLabel_1 = new JLabel("账单备注：");
		lblNewLabel_1.setBounds(51, 294, 79, 15);
		contentPane.add(lblNewLabel_1);
		
		JTextPane remarkTextPane = new JTextPane();
		remarkTextPane.setBounds(118, 294, 160, 66);
		contentPane.add(remarkTextPane);
		
		// 保存
		JButton saveBtn = new JButton("保存");
		saveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String title = titleText.getText();
				String payDate = payDateText.getText();
				Card card = (Card) cardComboBox.getSelectedItem();
				String billTypeStr = billTypeComboBox.getSelectedItem().toString();
				String amountStr = amountText.getText();
				String remark = remarkTextPane.getText();
				if (title == null || "".equals(title)) {
					JOptionPane.showMessageDialog(contentPane, "请输入账单主题", "系统提示", JOptionPane.WARNING_MESSAGE);
					return;
				}
				try {
					if (payDate == null || "".equals(payDate)) {
						JOptionPane.showMessageDialog(contentPane, "请输入账单日期", "系统提示", JOptionPane.WARNING_MESSAGE);
						return;
					}
					DateUtil.formatString(payDate, "yyyy-MM-dd");
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(contentPane, "账单日期不合法！", "系统提示", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if ("请选择银行卡号".equals(card.getCardNo())) {
					JOptionPane.showMessageDialog(contentPane, "请选择银行卡号", "系统提示", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if ("请选择账单类型".equals(billTypeStr)) {
					JOptionPane.showMessageDialog(contentPane, "请选择账单类型", "系统提示", JOptionPane.WARNING_MESSAGE);
					return;
				}
				Double amount;
				try {
					if (amountStr == null || "".equals(amountStr)) {
						JOptionPane.showMessageDialog(contentPane, "请输入账单金额", "系统提示", JOptionPane.WARNING_MESSAGE);
						return;
					}
					amount = Double.valueOf(amountStr);
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(contentPane, "金额不合法！", "系统提示", JOptionPane.WARNING_MESSAGE);
					return;
				}
				Integer billType = "收入".equals(billTypeStr) ? 0 : 1;
				//查询卡号余额
				Card c = cardDao.getById(card.getId());
				if(billType == 1){
					if(c.getBalance() < amount){
						JOptionPane.showMessageDialog(contentPane, "余额不足", "系统提示", JOptionPane.WARNING_MESSAGE);
						return;
					}
				}
				//银行卡余额更新
				Double balance = c.getBalance();
				if(billType == 1){
					balance = balance - amount;
				}else{
					balance = balance + amount;
				}
				cardDao.updateBalance(card.getId(), balance);
				//保存账单记录
				Bill bill = new Bill();
				bill.setUserId(DataBuffer.uid);
				bill.setTitle(title);
				bill.setAmount(amount);
				bill.setCardId(card.getId());
				bill.setBillType(billType);
				bill.setPayDate(payDate);
				bill.setRemark(remark);
				boolean flag = billDao.save(bill);
				if (flag) {
					frame.loadBill(frame.getCardId(),frame.getBillType(),frame.getTitle());
					dispose();
					JOptionPane.showMessageDialog(contentPane, "保存成功!");
				} else {
					JOptionPane.showMessageDialog(contentPane, "操作失败", "系统提示", JOptionPane.WARNING_MESSAGE);
				}
				return;

			}
		});
		saveBtn.setBounds(118, 389, 74, 23);
		contentPane.add(saveBtn);

		// 取消
		JButton cancleBtn = new JButton("取消");
		cancleBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancleBtn.setBounds(202, 389, 74, 23);
		contentPane.add(cancleBtn);
		
		JLabel lblNewLabel_4 = new JLabel("yyyy-MM-dd");
		lblNewLabel_4.setBounds(284, 105, 78, 15);
		contentPane.add(lblNewLabel_4);
		
		
		
		
		
		
		
		
		
		
	}
}
