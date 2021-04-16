package com.Lee.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.Lee.entity.AllData;
import com.Lee.service.UserService;
import com.Lee.service.impl.UserServiceImpl;
import com.Lee.utils.JdbcUtil;

public class RechargeFrm extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RechargeFrm frame = new RechargeFrm();
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
	public RechargeFrm() {
		setTitle("充值");
		setIconImage(Toolkit.getDefaultToolkit().getImage(RechargeFrm.class.getResource("/images/myTankUp.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 335, 183);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("你的金钱：" + AllData.user.getMoney());
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 15));
		lblNewLabel.setBounds(40, 29, 148, 18);
		contentPane.add(lblNewLabel);

		JButton btnNewButton = new JButton("充 值 500");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserService us = new UserServiceImpl();
				try {
					Connection con = JdbcUtil.getCon();
					us.moneyUpdate(con, AllData.user);
					AllData.user.setMoney(AllData.user.getMoney() + 500);
					lblNewLabel.setText("你的金钱：" + AllData.user.getMoney());
				} catch (Exception e2) {
					e2.printStackTrace();
				}

			}
		});

		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 15));
		btnNewButton.setBounds(107, 80, 114, 23);
		contentPane.add(btnNewButton);
	}
}
