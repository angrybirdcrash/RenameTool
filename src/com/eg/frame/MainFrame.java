package com.eg.frame;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.eg.util.FileRenameUtil;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 6449274803717503350L;

	private Font font = new Font("黑体", Font.PLAIN, 26);

	private JLabel folderPath_label = new JLabel("Folder path:");
	private JTextField folderPath_textField = new JTextField();

	private JLabel delete_label = new JLabel("What you want to delete:");
	private JTextField delete_textField1 = new JTextField();
	private JTextField delete_textField2 = new JTextField();
	private JTextField delete_textField3 = new JTextField();

	private JButton rename_button = new JButton("Let's Rename!");

	public MainFrame() {
		addComponent();
		addListener();
		initFrame();
	}

	private void addComponent() {
		folderPath_label.setBounds(70, 30, 180, 40);
		folderPath_label.setFont(font);
		add(folderPath_label);

		folderPath_textField.setBounds(30, 80, 930, 40);
		folderPath_textField.setFont(font);
		add(folderPath_textField);

		delete_label.setBounds(70, 150, 340, 40);
		delete_label.setFont(font);
		add(delete_label);

		delete_textField1.setBounds(30, 210, 270, 40);
		delete_textField1.setFont(font);
		add(delete_textField1);

		delete_textField2.setBounds(360, 210, 270, 40);
		delete_textField2.setFont(font);
		add(delete_textField2);

		delete_textField3.setBounds(690, 210, 270, 40);
		delete_textField3.setFont(font);
		add(delete_textField3);

		rename_button.setBounds(350, 300, 300, 50);
		rename_button.setFont(font);
		add(rename_button);
	}

	private void addListener() {
		// Rename button listener
		rename_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				executeRename();
			}
		});

		// Key "Enter" listener
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					executeRename();
				}
				super.keyPressed(e);
			}
		});
	}

	private void initFrame() {
		int width = 1000;
		int height = 430;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int locationX = screenSize.width / 2 - width / 2;
		int locationY = screenSize.height / 2 - height / 2;
		setSize(width, height);
		setLocation(locationX, locationY);
		setTitle("RenameTool By QB");
		setLayout(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void executeRename() {
		String folderPath = folderPath_textField.getText();
		File folder = new File(folderPath);
		// If the folderPath not exists or not directory
		if ((folder.exists() && folder.isDirectory()) == false) {
			JOptionPane.showMessageDialog(MainFrame.this, "Please input the correct folder path!");
			return;
		}
		// If what you want to delete is empty
		String delete1 = delete_textField1.getText();
		String delete2 = delete_textField2.getText();
		String delete3 = delete_textField3.getText();
		if (delete1.isEmpty() && delete2.isEmpty() && delete3.isEmpty()) {
			JOptionPane.showMessageDialog(MainFrame.this, "Please input what you want to delete!");
			return;
		}
		// Rename the files
		List<String> deleteList = new ArrayList<>();
		deleteList.add(delete1);
		deleteList.add(delete2);
		deleteList.add(delete3);
		int count = FileRenameUtil.rename(folder, deleteList);
		JOptionPane.showMessageDialog(MainFrame.this, "Done!Total rename " + count + " files!");
	}

	// Program entrance
	public static void main(String[] args) {
		new MainFrame();
	}
}
