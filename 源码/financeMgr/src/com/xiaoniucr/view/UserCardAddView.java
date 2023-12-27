package com.xiaoniucr.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import com.xiaoniucr.dao.CardDao;
import com.xiaoniucr.entity.Card;
import com.xiaoniucr.util.DataBuffer;


/**
 * 
 * 活动报名
 *
 */
public class UserCardAddView extends JFrame {

	private JPanel contentPane;
	private CardDao cardDao = new CardDao();
	private JTextField cardNoText;
	private JTextField balanceText;
	/**
	 * Create the frame.
	 */
	public UserCardAddView(final UserCardManageView frame) {
		
		setTitle("银行卡添加");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 388, 404);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel("卡号：");
		lblNewLabel.setBounds(79, 60, 54, 15);
		contentPane.add(lblNewLabel);
		
		cardNoText = new JTextField();
		cardNoText.setBounds(118, 57, 160, 21);
		contentPane.add(cardNoText);
		cardNoText.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("银行：");
		lblNewLabel_2.setBounds(80, 108, 54, 15);
		contentPane.add(lblNewLabel_2);
		
		JComboBox bankComboBox = new JComboBox();
		bankComboBox.setModel(new DefaultComboBoxModel(new String[] {"请选择银行", "中国银行", "招商银行", "建设银行", "农业银行", "中信银行", "平安银行", "交通银行", "工商银行", "兴业银行", "浦发银行", "邮政储蓄银行"}));
		bankComboBox.setBounds(118, 105, 160, 21);
		contentPane.add(bankComboBox);
	
		JLabel lblNewLabel_3 = new JLabel("余额：");
		lblNewLabel_3.setBounds(79, 157, 54, 15);
		contentPane.add(lblNewLabel_3);
		
		balanceText = new JTextField();
		balanceText.setBounds(118, 154, 160, 21);
		contentPane.add(balanceText);
		balanceText.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("备注：");
		lblNewLabel_1.setBounds(79, 207, 54, 15);
		contentPane.add(lblNewLabel_1);
		
		JTextPane remarkTextPane = new JTextPane();
		remarkTextPane.setBounds(118, 207, 160, 66);
		contentPane.add(remarkTextPane);
		
		// 保存
		JButton saveBtn = new JButton("保存");
		saveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String cardNo = cardNoText.getText();
				String bank = bankComboBox.getSelectedItem().toString();
				String balanceStr = balanceText.getText();
				String remark = remarkTextPane.getText();
				if (cardNo == null || "".equals(cardNo)) {
					JOptionPane.showMessageDialog(contentPane, "请输入银行卡号", "系统提示", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if ("请选择银行".equals(bank)) {
					JOptionPane.showMessageDialog(contentPane, "请选择所属银行", "系统提示", JOptionPane.WARNING_MESSAGE);
					return;
				}
				Double balance;
				try {
					if (balanceStr == null || "".equals(balanceStr)) {
						JOptionPane.showMessageDialog(contentPane, "请输入账户余额", "系统提示", JOptionPane.WARNING_MESSAGE);
						return;
					}
					balance = Double.valueOf(balanceStr);
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(contentPane, "账户余额不合法", "系统提示", JOptionPane.WARNING_MESSAGE);
					return;
				}
				Card card = cardDao.getByCardNo(cardNo);
				if(card != null ){
					JOptionPane.showMessageDialog(contentPane, "该卡号已存在，请重新输入！", "系统提示", JOptionPane.WARNING_MESSAGE);
					return;
				}
				card = new Card();
				card.setUserId(DataBuffer.uid);
				card.setCardNo(cardNo);
				card.setBank(bank);
				card.setBalance(balance);
				card.setRemark(remark);
				boolean flag = cardDao.save(card);
				if (flag) {
					frame.load(null);
					dispose();
					JOptionPane.showMessageDialog(contentPane, "保存成功!");
				} else {
					JOptionPane.showMessageDialog(contentPane, "操作失败", "系统提示", JOptionPane.WARNING_MESSAGE);
				}
				return;

			}
		});
		saveBtn.setBounds(118, 308, 74, 23);
		contentPane.add(saveBtn);

		// 取消
		JButton cancleBtn = new JButton("取消");
		cancleBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancleBtn.setBounds(202, 308, 74, 23);
		contentPane.add(cancleBtn);
		
		
		
		
		
		
		
	}
}
