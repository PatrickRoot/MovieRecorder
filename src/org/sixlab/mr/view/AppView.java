package org.sixlab.mr.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class AppView extends JFrame {
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
	
	public AppView() {
		this.setTitle("Movie Recorder");
		this.setBounds(300, 100, 800, 600);
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
		Vector<String> title=new Vector<>();
		title.add("ID");
		title.add("名字");
		title.add("国家");
		title.add("年份");
		title.add("导演");
		title.add("演员");
		title.add("备注");
		title.add("日期");
		content = new Vector<>();
		jTable=new JTable(content, title);
		jScrollPane.setViewportView(jTable);
		
		resultLabel = new JLabel();
		helpButton = new JButton("帮助");
		jdownpJPanel.add(resultLabel, BorderLayout.CENTER);
		jdownpJPanel.add(helpButton, BorderLayout.EAST);
		resultLabel.setText("共有 " + content.size() + " 部电影");
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
