package com.Lee.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.Lee.service.ScoreService;
import com.Lee.service.impl.ScoreServiceImpl;
import com.Lee.utils.JdbcUtil;

public class RankFrm extends JFrame {

	private ScoreService ss = new ScoreServiceImpl();
	private JPanel contentPane;
	private JTable rankTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RankFrm frame = new RankFrm();
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
	public RankFrm() {
		setTitle("排行榜");
		setIconImage(Toolkit.getDefaultToolkit().getImage(RankFrm.class.getResource("/images/myTankUp.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		rankTable = new JTable();
		rankTable.setFont(new Font("宋体", Font.PLAIN, 15));
		rankTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "username", "grade", "date" }) {
			boolean[] columnEditables = new boolean[] { false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		rankTable.getColumnModel().getColumn(0).setPreferredWidth(55);
		rankTable.getColumnModel().getColumn(1).setPreferredWidth(30);
		rankTable.getColumnModel().getColumn(2).setPreferredWidth(90);
		scrollPane.setViewportView(rankTable);
		this.fillRankTable();
	}

	private void fillRankTable() {
		DefaultTableModel dtm = (DefaultTableModel) rankTable.getModel();
		dtm.setRowCount(0);
		try {
			Connection con = JdbcUtil.getCon();
			ResultSet rs = ss.getRank(con);
			while (rs.next()) {
				Vector v = new Vector();
				v.add(rs.getString("username"));
				v.add(rs.getLong("grade"));
				v.add(rs.getString("date"));
				dtm.addRow(v);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
