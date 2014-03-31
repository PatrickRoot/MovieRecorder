package org.sixlab.mr.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

import org.sixlab.mr.action.Action;
import org.sixlab.mr.beans.MovieRecord;

public class AppView extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Vector<Vector<String>> content;
	
	private JPanel jupJPanel;
	private JPanel jcenJPanel;
	private JPanel jdownpJPanel;
	
	private JTextField idJtField;
	private JTextField nameJTextField;
	private JTextField countryJTextField;
	private JTextField yearJTextField;
	private JTextField directorJTextField;
	private JTextField actorJTextField;
	private JTextField remarkJTextField;
	private JTextField inDateJTextField;
	
	private JButton resetButton;
	private JButton updateButton;
	private JButton insertButton;
	private JButton searchButton;
	
	private JTable jTable;
	private JLabel resultLabel;
	private JButton helpButton;
	
	private JPopupMenu pop;
	
	private JMenuItem cut;
	
	private JMenuItem copy;
	
	private JMenuItem paste;
	
	private JMenuItem select;
	
	private Vector<String> title;
	
	public AppView() {
		this.setTitle("Movie Recorder");
		this.setBounds(300, 100, 800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container container = this.getContentPane();
		jupJPanel = new JPanel(new GridLayout(3, 8));
		jcenJPanel = new JPanel();
		jdownpJPanel = new JPanel(new BorderLayout());
		container.add(jupJPanel, BorderLayout.NORTH);
		container.add(jcenJPanel, BorderLayout.CENTER);
		container.add(jdownpJPanel, BorderLayout.SOUTH);
		
		idJtField = new JTextField();
		nameJTextField = new JTextField();
		countryJTextField = new JTextField();
		yearJTextField = new JTextField();
		directorJTextField = new JTextField();
		actorJTextField = new JTextField();
		remarkJTextField = new JTextField();
		inDateJTextField = new JTextField();
		
		updateButton = new JButton("修改");
		insertButton = new JButton("插入");
		searchButton = new JButton("搜索");
		resetButton = new JButton("重置");
		
		jupJPanel.add(new JLabel("ID"));
		jupJPanel.add(new JLabel("名字"));
		jupJPanel.add(new JLabel("国家"));
		jupJPanel.add(new JLabel("年份"));
		jupJPanel.add(new JLabel("导演"));
		jupJPanel.add(new JLabel("演员"));
		jupJPanel.add(new JLabel("备注"));
		jupJPanel.add(new JLabel("日期"));
		
		jupJPanel.add(idJtField);
		jupJPanel.add(nameJTextField);
		jupJPanel.add(countryJTextField);
		jupJPanel.add(yearJTextField);
		jupJPanel.add(directorJTextField);
		jupJPanel.add(actorJTextField);
		jupJPanel.add(remarkJTextField);
		jupJPanel.add(inDateJTextField);
		
		jupJPanel.add(new JLabel());
		jupJPanel.add(new JLabel());
		jupJPanel.add(new JLabel());
		jupJPanel.add(new JLabel());
		jupJPanel.add(updateButton);
		jupJPanel.add(insertButton);
		jupJPanel.add(searchButton);
		jupJPanel.add(resetButton);
		
		JScrollPane jScrollPane = new JScrollPane();
		jcenJPanel.add(jScrollPane);
		title = new Vector<String>();
		title.add("ID");
		title.add("名字");
		title.add("国家");
		title.add("年份");
		title.add("导演");
		title.add("演员");
		title.add("备注");
		title.add("日期");
		content = new Action().search(null);
		jTable = new JTable(content, title);
		jScrollPane.setViewportView(jTable);
		setColumnWidth();
		
		resultLabel = new JLabel();
		helpButton = new JButton("帮助");
		jdownpJPanel.add(resultLabel, BorderLayout.CENTER);
		jdownpJPanel.add(helpButton, BorderLayout.EAST);
		resultLabel.setText("共有 " + content.size() + " 部电影。");
		
		helpButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				System.out.println(jTable.getColumnModel().getColumn(0)
						.getWidth());
				System.out.println(jTable.getColumnModel().getColumn(1)
						.getWidth());
				System.out.println(jTable.getColumnModel().getColumn(2)
						.getWidth());
				System.out.println(jTable.getColumnModel().getColumn(3)
						.getWidth());
				System.out.println(jTable.getColumnModel().getColumn(4)
						.getWidth());
				System.out.println(jTable.getColumnModel().getColumn(5)
						.getWidth());
				System.out.println(jTable.getColumnModel().getColumn(6)
						.getWidth());
				System.out.println(jTable.getColumnModel().getColumn(7)
						.getWidth());
				
				StringBuffer helpText = new StringBuffer();
				helpText.append("1、修改的时候必需有ID。\n");
				helpText.append("2、修改时日期为空，则表示当前日期。\n");
				helpText.append("3、插入时必需有名字。\n");
				helpText.append("4、重置时，恢复最初状态。\n");
				helpText.append("5、所有文本框为空时，搜索所有记录。\n");
				helpText.append("6、在记录上右键点击，记录内容会填充到文本框。\n");
				JOptionPane.showMessageDialog(null, helpText, "帮助",
						JOptionPane.PLAIN_MESSAGE);
			}
		});
		
		this.getContentPane().addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					searchTrigger();
				}
			}
		});
		
		jTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.isMetaDown()) {
					tableRightClicked(e);
				}
			}
		});
		
		searchButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				searchTrigger();
			}
		});
		
		insertButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				insertTrigger();
			}
		});
		
		updateButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				updateTrigger();
			}
		});
		
		resetButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				idJtField.setText("");
				nameJTextField.setText("");
				countryJTextField.setText("");
				yearJTextField.setText("");
				directorJTextField.setText("");
				actorJTextField.setText("");
				remarkJTextField.setText("");
				inDateJTextField.setText("");
				content = new Action().search(null);
				resultLabel.setText("共有 " + content.size() + " 部电影。");
				jTable.setModel(new DefaultTableModel(content, title));
				setColumnWidth();
				pack();
			}
		});
		
		idJtField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					popUp(idJtField, e);
				}
			}
		});
		
		nameJTextField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					popUp(nameJTextField, e);
				}
			}
		});
		
		countryJTextField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					popUp(countryJTextField, e);
				}
			}
		});
		
		yearJTextField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					popUp(yearJTextField, e);
				}
			}
		});
		
		directorJTextField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					popUp(directorJTextField, e);
				}
			}
		});
		
		actorJTextField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					popUp(actorJTextField, e);
				}
			}
		});
		
		remarkJTextField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					popUp(remarkJTextField, e);
				}
			}
		});
		
		inDateJTextField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					popUp(inDateJTextField, e);
				}
			}
		});
		
		this.pack();
		nameJTextField.requestFocus();
	}
	
	private void setColumnWidth() {
		
		jTable.getColumnModel().getColumn(0).setMinWidth(32);
		jTable.getColumnModel().getColumn(0).setMaxWidth(32);
		
		jTable.getColumnModel().getColumn(1).setMinWidth(75);
		jTable.getColumnModel().getColumn(1).setMaxWidth(75);
		
		jTable.getColumnModel().getColumn(3).setMinWidth(32);
		jTable.getColumnModel().getColumn(3).setMaxWidth(32);
		
		jTable.getColumnModel().getColumn(7).setMinWidth(60);
		jTable.getColumnModel().getColumn(7).setMaxWidth(60);
	}
	
	private void updateTrigger() {
		
		String id = idJtField.getText();
		if (null == id || "".equals(id)) {
			resultLabel.setText("严重警告：没有ID无法修改信息，请一定要输入ID！！！");
			nameJTextField.requestFocus();
			this.pack();
			return;
		}
		
		MovieRecord record = inputRecord();
		
		content = new Action().update(record);
		resultLabel.setText("更新成功，id：" + content.get(0).get(0));
		jTable.setModel(new DefaultTableModel(content, title));
		setColumnWidth();
		pack();
	}
	
	private void insertTrigger() {
		
		String name = nameJTextField.getText();
		if (null == name || "".equals(name)) {
			resultLabel.setText("严重警告：没有名字无法插入信息，请一定要输入名字！");
			nameJTextField.requestFocus();
			this.pack();
			return;
		}
		
		MovieRecord record = inputRecord();
		
		content = new Action().insert(record);
		System.out.println(content.size());
		String id = content.get(0).get(0);
		resultLabel.setText("插入成功，id：" + id);
		jTable.setModel(new DefaultTableModel(content, title));
		setColumnWidth();
		pack();
	}
	
	private void popUp(JTextField jTextField, MouseEvent e) {
		pop = new JPopupMenu();
		pop.add(cut = new JMenuItem("剪切"));
		pop.add(copy = new JMenuItem("复制"));
		pop.add(paste = new JMenuItem("粘贴"));
		pop.add(select = new JMenuItem("全选"));
		
		cut.setAccelerator(KeyStroke.getKeyStroke('X', InputEvent.CTRL_MASK));
		copy.setAccelerator(KeyStroke.getKeyStroke('C', InputEvent.CTRL_MASK));
		paste.setAccelerator(KeyStroke.getKeyStroke('V', InputEvent.CTRL_MASK));
		select.setAccelerator(KeyStroke.getKeyStroke('A', InputEvent.CTRL_MASK));
		
		cut.addActionListener(new PopActionListener(jTextField));
		copy.addActionListener(new PopActionListener(jTextField));
		paste.addActionListener(new PopActionListener(jTextField));
		select.addActionListener(new PopActionListener(jTextField));
		copy.setEnabled(true);
		cut.setEnabled(true);
		paste.setEnabled(true);
		select.setEnabled(true);
		pop.show(this, e.getX(), e.getY());
	}
	
	class PopActionListener implements ActionListener {
		
		JTextField jTextField;
		
		public PopActionListener(JTextField jTextField) {
			this.jTextField = jTextField;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String str = e.getActionCommand();
			if (str.equals(cut.getText())) {
				jTextField.cut();
			} else if (str.equals(copy.getText())) {
				jTextField.copy();
			} else if (str.equals(paste.getText())) {
				jTextField.paste();
			} else if (str.equals(select.getText())) {
				jTextField.selectAll();
			}
		}
	}
	
	private void searchTrigger() {
		MovieRecord record = inputRecord();
		
		content = new Action().search(record);
		resultLabel.setText("搜索成功，数量：" + content.size());
		jTable.setModel(new DefaultTableModel(content, title));
		setColumnWidth();
		pack();
	}
	
	private void tableRightClicked(MouseEvent e) {
		if (e.isMetaDown()) {
			
			JTable clickTable = (JTable) e.getSource();
			int row = clickTable.rowAtPoint(e.getPoint());
			
			idJtField.setText((String) clickTable.getValueAt(row, 0));
			nameJTextField.setText((String) clickTable.getValueAt(row, 1));
			countryJTextField.setText((String) clickTable.getValueAt(row, 2));
			yearJTextField.setText((String) clickTable.getValueAt(row, 3));
			directorJTextField.setText((String) clickTable.getValueAt(row, 4));
			actorJTextField.setText((String) clickTable.getValueAt(row, 5));
			remarkJTextField.setText((String) clickTable.getValueAt(row, 6));
			inDateJTextField.setText((String) clickTable.getValueAt(row, 7));
			
		}
		nameJTextField.requestFocus();
	}
	
	private MovieRecord inputRecord() {
		String idInput = idJtField.getText();
		
		String name = nameJTextField.getText();
		String country = countryJTextField.getText();
		String year = yearJTextField.getText();
		String director = directorJTextField.getText();
		String actor = actorJTextField.getText();
		String remark = remarkJTextField.getText();
		String inDate = inDateJTextField.getText();
		
		MovieRecord record = new MovieRecord();
		if (null != idInput && !"".equals(idInput)) {
			record.setId(Integer.parseInt(idInput));
		}
		
		if (null == inDate || "".equals(inDate)) {
			Date today = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			inDate = sdf.format(today);
		}
		
		record.setName(name);
		record.setCountry(country);
		record.setYear(year);
		record.setDirector(director);
		record.setActor(actor);
		record.setRemark(remark);
		record.setInDate(inDate);
		
		return record;
	}
	
	public static void main(String[] args) {
		AppView appView = new AppView();
		appView.setVisible(true);
	}
	
	public Vector<Vector<String>> getContent() {
		return content;
	}
	
	public void setContent(Vector<Vector<String>> content) {
		this.content = content;
	}
	
	public JPanel getJupJPanel() {
		return jupJPanel;
	}
	
	public void setJupJPanel(JPanel jupJPanel) {
		this.jupJPanel = jupJPanel;
	}
	
	public JPanel getJcenJPanel() {
		return jcenJPanel;
	}
	
	public void setJcenJPanel(JPanel jcenJPanel) {
		this.jcenJPanel = jcenJPanel;
	}
	
	public JPanel getJdownpJPanel() {
		return jdownpJPanel;
	}
	
	public void setJdownpJPanel(JPanel jdownpJPanel) {
		this.jdownpJPanel = jdownpJPanel;
	}
	
	public JTextField getIdJtField() {
		return idJtField;
	}
	
	public void setIdJtField(JTextField idJtField) {
		this.idJtField = idJtField;
	}
	
	public JTextField getNameJTextField() {
		return nameJTextField;
	}
	
	public void setNameJTextField(JTextField nameJTextField) {
		this.nameJTextField = nameJTextField;
	}
	
	public JTextField getCountryJTextField() {
		return countryJTextField;
	}
	
	public void setCountryJTextField(JTextField countryJTextField) {
		this.countryJTextField = countryJTextField;
	}
	
	public JTextField getYearJTextField() {
		return yearJTextField;
	}
	
	public void setYearJTextField(JTextField yearJTextField) {
		this.yearJTextField = yearJTextField;
	}
	
	public JTextField getDirectorJTextField() {
		return directorJTextField;
	}
	
	public void setDirectorJTextField(JTextField directorJTextField) {
		this.directorJTextField = directorJTextField;
	}
	
	public JTextField getActorJTextField() {
		return actorJTextField;
	}
	
	public void setActorJTextField(JTextField actorJTextField) {
		this.actorJTextField = actorJTextField;
	}
	
	public JTextField getRemarkJTextField() {
		return remarkJTextField;
	}
	
	public void setRemarkJTextField(JTextField remarkJTextField) {
		this.remarkJTextField = remarkJTextField;
	}
	
	public JTextField getInDateJTextField() {
		return inDateJTextField;
	}
	
	public void setInDateJTextField(JTextField inDateJTextField) {
		this.inDateJTextField = inDateJTextField;
	}
	
	public JButton getResetButton() {
		return resetButton;
	}
	
	public void setResetButton(JButton resetButton) {
		this.resetButton = resetButton;
	}
	
	public JButton getUpdateButton() {
		return updateButton;
	}
	
	public void setUpdateButton(JButton updateButton) {
		this.updateButton = updateButton;
	}
	
	public JButton getInsertButton() {
		return insertButton;
	}
	
	public void setInsertButton(JButton insertButton) {
		this.insertButton = insertButton;
	}
	
	public JButton getSearchButton() {
		return searchButton;
	}
	
	public void setSearchButton(JButton searchButton) {
		this.searchButton = searchButton;
	}
	
	public JTable getjTable() {
		return jTable;
	}
	
	public void setjTable(JTable jTable) {
		this.jTable = jTable;
	}
	
	public JLabel getResultLabel() {
		return resultLabel;
	}
	
	public void setResultLabel(JLabel resultLabel) {
		this.resultLabel = resultLabel;
	}
	
	public JButton getHelpButton() {
		return helpButton;
	}
	
	public void setHelpButton(JButton helpButton) {
		this.helpButton = helpButton;
	}
}
