package com.memorammstein.cacama.io;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;

import com.memorammstein.cacama.smarthouse.iBuilding;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;

public class ComplexManager_VisualEdition implements Runnable {
	
	private ArrayList<iBuilding> buildings = null;
	private String[] command = null;

	private JFrame frmCacamasSmarthouse;
	private JTextField txtCommand;
	private JTextPane lblNull;

	/**
	 * Create the application.
	 */
	public ComplexManager_VisualEdition(ArrayList<iBuilding> buildings) {
		this.buildings = buildings;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCacamasSmarthouse = new JFrame();
		frmCacamasSmarthouse.setResizable(false);
		frmCacamasSmarthouse.setTitle("Cacama's smarthouse");
		frmCacamasSmarthouse.setBounds(100, 100, 800, 600);
		frmCacamasSmarthouse.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmCacamasSmarthouse.getContentPane().setLayout(null);
		
		lblNull = new JTextPane();
		lblNull.setBounds(10, 11, 774, 478);
		frmCacamasSmarthouse.getContentPane().add(lblNull);
		
		txtCommand = new JTextField();
		txtCommand.setText("command");
		txtCommand.setBounds(10, 523, 619, 37);
		frmCacamasSmarthouse.getContentPane().add(txtCommand);
		txtCommand.setColumns(10);
		
		JButton btnExecuteQuery = new JButton("Execute query");
		btnExecuteQuery.setBounds(639, 523, 145, 39);
		btnExecuteQuery.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				lblNull.setText("");
				command = txtCommand.getText().split(":");
				switch (command[0]) {
				case "building":
					switch (command[1]) {
					case "showAll":
						for (int i = 0; i < buildings.size(); i++)
							lblNull.setText(lblNull.getText() + "Building #" + i + ": " + buildings.get(i).getBuildingName() + "\n");
						break;
					}
					switch (command[2]) {
					case "room":
						switch (command[3]) {
						case "showAll":
							for (int i = 0; i < buildings.get(Integer.parseInt(command[1])).getRooms().size(); i++)
								lblNull.setText(lblNull.getText() + "Room #" + i + ": " + buildings.get(Integer.parseInt(command[1])).getRooms().get(i).getRoomName() + "\n");
							break;
						}
						switch (command[4]) {
						case "automaton":
							switch (command[5]) {
								case "showAll":
									for (int i = 0; i < buildings.get(Integer.parseInt(command[1])).getRooms().get(Integer.parseInt(command[3])).getAutomatonWrappers().size(); i++)
										lblNull.setText(lblNull.getText() + "Automaton #" + i + ": " + buildings.get(Integer.parseInt(command[1])).getRooms().get(Integer.parseInt(command[3])).getAutomatonWrappers().get(i).getAutomatonName() + "\n");
								break;
							}
							switch (command[6]) {
							case "feed":
								String tempMessage = null;
								tempMessage = "Possible input for each automaton:"
										+ "\n" + "Access control: " + org.apache.commons.lang3.StringEscapeUtils.escapeJava("\"star\", \"enter\", \"number0\", \"number1\", \"number2\", \"number3\", \"number4\", \"number5\", \"number6\", \"number7\", \"number8\", \"number9\"")
										+ "\n" + "Energy: " + org.apache.commons.lang3.StringEscapeUtils.escapeJava("\"programmed turn-off: in effective\", \"programmed turn-off: effective\", \"motion: off\", \"motion: on\"")
										+ "\n" + "Ilumination: " + org.apache.commons.lang3.StringEscapeUtils.escapeJava("integer as a string, percentage representation, without percentage symbol")
										+ "\n" + "Security: " + org.apache.commons.lang3.StringEscapeUtils.escapeJava("\"alarm: off\", \"alarm: on\", \"motion: off\", \"motion: on\", \"access: off\", \"access: on\"")
										+ "\n" + "Temperature: " + org.apache.commons.lang3.StringEscapeUtils.escapeJava("double as a string");
								int selectedOption = JOptionPane.showConfirmDialog(null, tempMessage, "Are you sure about your query? Press OK to continue", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
								if (selectedOption == JOptionPane.OK_OPTION) {
									buildings.get(Integer.parseInt(command[1])).getRooms().get(Integer.parseInt(command[3])).getAutomatonWrappers().get(Integer.parseInt(command[5])).feed(command[7]);
									lblNull.setText("Automaton fed by user (see log for appretiation of state change)");
								} else {
									lblNull.setText("Query cancelled");
								}
								break;
							}
							break;
						case "disable":
							buildings.get(Integer.parseInt(command[1])).getRooms().get(Integer.parseInt(command[3])).disable();
							lblNull.setText("Room disabled -> all automata off");
							break;
						}
						break;
					case "disable":
						buildings.get(Integer.parseInt(command[1])).disable();
						lblNull.setText("Building disabled -> all rooms off");
						break;
					}
					break;
				case "exit":
					frmCacamasSmarthouse.dispose();
					break;
				}
			}
		});
		frmCacamasSmarthouse.getContentPane().add(btnExecuteQuery);
	}
	
	/**
	 * Launch the application.
	 */
	public void start() {
		EventQueue.invokeLater(this);
	}

	@Override
	public void run() {
		try {
			this.frmCacamasSmarthouse.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
