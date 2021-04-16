package com.Lee.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.Lee.controller.Controller;

public class StartGame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartGame frame = new StartGame();
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
	public StartGame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(StartGame.class.getResource("/images/myTankUp.png")));
		setTitle("坦克大战");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 720);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.LIGHT_GRAY);
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("开 始");
		mnNewMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Controller.menuClick(e);
			}
		});
		mnNewMenu.setFont(new Font("宋体", Font.PLAIN, 15));
		menuBar.add(mnNewMenu);

		JMenu mnNewMenu_1 = new JMenu("充 值");
		mnNewMenu_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Controller.rechargeMouseClicked(e);
			}
		});
		mnNewMenu_1.setFont(new Font("宋体", Font.PLAIN, 15));
		menuBar.add(mnNewMenu_1);

		JMenu mnNewMenu_2 = new JMenu("排行榜");
		mnNewMenu_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Controller.rankMouseClicked(e);
			}
		});
		mnNewMenu_2.setFont(new Font("宋体", Font.PLAIN, 15));
		menuBar.add(mnNewMenu_2);

		JMenu mnNewMenu_3 = new JMenu("商 城");
		mnNewMenu_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Controller.shopMouseClicked(e);
			}
		});
		mnNewMenu_3.setFont(new Font("宋体", Font.PLAIN, 15));
		menuBar.add(mnNewMenu_3);

		JMenu mnNewMenu_4 = new JMenu("帮 助");
		mnNewMenu_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Controller.helpMouseClicked(e);
			}
		});

		mnNewMenu_4.setFont(new Font("宋体", Font.PLAIN, 15));
		menuBar.add(mnNewMenu_4);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		GamePanel gamePanel = new GamePanel();
		getContentPane().add(gamePanel);
	}

}
