package com.Lee.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.Lee.controller.Controller;
import com.Lee.entity.AllData;

public class ShopFrm extends JFrame {

	private JPanel contentPane;
	JLabel moneyTxt = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShopFrm frame = new ShopFrm();
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
	public ShopFrm() {
		setResizable(false);
		setTitle("商城");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ShopFrm.class.getResource("/images/myTankUp.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 286, 168);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(ShopFrm.class.getResource("/images/health.png")));
		lblNewLabel.setBounds(34, 50, 32, 32);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("生命值 500");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(76, 57, 84, 18);
		contentPane.add(lblNewLabel_1);

		JButton btnNewButton = new JButton("购 买");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch (Controller.purchaseHealthActionPerformed(e)) {
				case -2:
					JOptionPane.showMessageDialog(null, "生命值已达上限！");
					break;
				case -1:
					JOptionPane.showMessageDialog(null, "金币不足请充值！");
					break;
				case 1:
					AllData.myTank.setHealthNum(AllData.myTank.getHealthNum() + 1);
					AllData.user.setMoney(AllData.user.getMoney() - 500);
					moneyTxt.setText("你的金币：" + AllData.user.getMoney());
				}
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 15));
		btnNewButton.setBounds(170, 55, 84, 23);
		contentPane.add(btnNewButton);

		moneyTxt = new JLabel("你的金币：" + AllData.user.getMoney());
		moneyTxt.setFont(new Font("宋体", Font.PLAIN, 15));
		moneyTxt.setBounds(34, 16, 146, 31);
		contentPane.add(moneyTxt);
	}
}
