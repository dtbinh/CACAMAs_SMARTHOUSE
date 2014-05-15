package com.memorammstein.cacama.io;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;

import com.memorammstein.cacama.smarthouse.Building;
import com.memorammstein.cacama.smarthouse.Room;
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
		
		JButton btnExecuteQuery = new JButton("Execute query");
		
		txtCommand = new JTextField();
		txtCommand.setText("command");
		txtCommand.setBounds(10, 523, 619, 37);
		frmCacamasSmarthouse.getContentPane().add(txtCommand);
		txtCommand.setColumns(10);
		
		btnExecuteQuery = new JButton("Execute query");
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
						OutputManager.printComplexManagerAction("showAll", "building");
						break;
					case "add":
						buildings.add(new Building(command[2]));
						lblNull.setText("building added");
						OutputManager.printComplexManagerAction("add", "building");
						break;
					}
					switch (command[2]) {
					case "ignite":
						buildings.get(Integer.parseInt(command[1])).start();
						lblNull.setText("Building started");
						OutputManager.printComplexManagerAction("ignite", "building #" + Integer.parseInt(command[1]));
						break;
					case "room":
						switch (command[3]) {
						case "add":
							buildings.get(Integer.parseInt(command[1])).addRoom(new Room(command[4]));
							lblNull.setText("room added to building");
							OutputManager.printComplexManagerAction("add", "room " + command[4] + " to  building #" + Integer.parseInt(command[1]));
							break;
						case "showAll":
							for (int i = 0; i < buildings.get(Integer.parseInt(command[1])).getRooms().size(); i++)
								lblNull.setText(lblNull.getText() + "Room #" + i + ": " + buildings.get(Integer.parseInt(command[1])).getRooms().get(i).getRoomName() + "\n");
							OutputManager.printComplexManagerAction("showAll", "room");
							break;
						}
						switch (command[4]) {
						case "automaton":
							switch (command[5]) {
								case "showAll":
									for (int i = 0; i < buildings.get(Integer.parseInt(command[1])).getRooms().get(Integer.parseInt(command[3])).getAutomatonWrappers().size(); i++)
										lblNull.setText(lblNull.getText() + "Automaton #" + i + ": " + buildings.get(Integer.parseInt(command[1])).getRooms().get(Integer.parseInt(command[3])).getAutomatonWrappers().get(i).getAutomatonName() + "\n");
									OutputManager.printComplexManagerAction("showAll", "automaton on room " + buildings.get(Integer.parseInt(command[1])).getRooms().get(Integer.parseInt(command[3])).getRoomName());
									break;
							}
							switch (command[6]) {
							case "feed":
								String tempMessage = null;
								tempMessage = "Possible input for each automaton:"
										+ "\n" + "Access control: " + "\"star\", \"enter\", \"number0\", \"number1\", \"number2\", \"number3\", \"number4\", \"number5\", \"number6\", \"number7\", \"number8\", \"number9\""
										+ "\n" + "Energy: " + "\"programmed turn-off-> in effective\", \"programmed turn-off-> effective\", \"motion-> off\", \"motion-> on\""
										+ "\n" + "Ilumination: " + "integer as a string, percentage representation, without percentage symbol"
										+ "\n" + "Security: " + "\"alarm-> off\", \"alarm-> on\", \"motion-> off\", \"motion-> on\", \"access-> off\", \"access-> on\""
										+ "\n" + "Temperature: " + "double as a string";
								int selectedOption = JOptionPane.showConfirmDialog(null, tempMessage, "Are you sure about your query? Press OK to continue", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
								if (selectedOption == JOptionPane.OK_OPTION) {
									buildings.get(Integer.parseInt(command[1])).getRooms().get(Integer.parseInt(command[3])).getAutomatonWrappers().get(Integer.parseInt(command[5])).feed(command[7]);
									lblNull.setText("Automaton fed by user (see log for appretiation of state change)");
									OutputManager.printComplexManagerAction("feed", "fed String \"" + command[7] + "\" to automaton " + buildings.get(Integer.parseInt(command[1])).getRooms().get(Integer.parseInt(command[3])).getAutomatonWrappers().get(Integer.parseInt(command[5])).getAutomatonName());
								} else {
									lblNull.setText("Query cancelled");
								}
								break;
							case "disable":
								buildings.get(Integer.parseInt(command[1])).getRooms().get(Integer.parseInt(command[3])).getAutomatonWrappers().get(Integer.parseInt(command[5])).disable();
								lblNull.setText("Automaton disabled");
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
					for (iBuilding building : buildings) {
						building.disable();
					}
					JOptionPane.showMessageDialog(null, "All buildings disabled");
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
