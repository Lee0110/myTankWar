package com.Lee.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import com.Lee.controller.Controller;

public class LoginFrm extends JFrame {

	private JPanel contentPane;
	private JTextField usernameTxt;
	private JPasswordField passwordTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrm frame = new LoginFrm();
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
	public LoginFrm() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginFrm.class.getResource("/images/myTankUp.png")));
		setTitle("登录");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblNewLabel = new JLabel("用户名：");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 15));

		usernameTxt = new JTextField();
		usernameTxt.setToolTipText("");
		usernameTxt.setFont(new Font("宋体", Font.PLAIN, 15));
		usernameTxt.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("密  码：");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 15));

		JButton btnNewButton = new JButton("登  录");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 登录点击事件处理
				boolean flag = Controller.loginActionPerformed(e, usernameTxt, passwordTxt);
				if (flag) {
					dispose();
				} else {
					passwordTxt.setText("");
				}
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 15));

		JLabel lblNewLabel_2 = new JLabel("欢迎来到坦克大战");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 15));

		JLabel lblNewLabel_3 = new JLabel("3-12位英文或数字");
		lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 15));

		JLabel lblNewLabel_4 = new JLabel("3-12位英文或数字");
		lblNewLabel_4.setFont(new Font("宋体", Font.PLAIN, 15));

		passwordTxt = new JPasswordField();
		passwordTxt.setFont(new Font("宋体", Font.PLAIN, 15));

		JButton btnNewButton_1 = new JButton("注  册");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.loginFrmRegisterActionPerformed(e);
			}
		});
		btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 15));

		JLabel lblNewLabel_5 = new JLabel("软件1801李宇龙");
		lblNewLabel_5.setFont(new Font("宋体", Font.PLAIN, 12));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup().addGap(37)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 81,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 70,
												GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(passwordTxt)
										.addComponent(usernameTxt, GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel_4, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
										.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 135, Short.MAX_VALUE)))
						.addGroup(gl_contentPane.createSequentialGroup().addGap(148).addComponent(lblNewLabel_2,
								GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup().addGap(101)
								.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
								.addGap(33).addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 97,
										GroupLayout.PREFERRED_SIZE)))
				.addContainerGap())
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
						.addContainerGap(297, Short.MAX_VALUE)
						.addComponent(lblNewLabel_5, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
						.addGap(25)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addGap(28).addComponent(lblNewLabel_2).addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_3)
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
								.addComponent(usernameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_1)
								.addComponent(lblNewLabel_4).addComponent(passwordTxt, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(btnNewButton)
								.addComponent(btnNewButton_1))
						.addGap(16).addComponent(lblNewLabel_5)));
		contentPane.setLayout(gl_contentPane);
		this.setLocationRelativeTo(null);
	}
}
