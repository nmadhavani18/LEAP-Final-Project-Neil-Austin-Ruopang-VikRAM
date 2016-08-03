package gFixer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import java.util.*;
import java.io.*;


public class window {

	private JFrame frame;
	private JTextField inPath;
	private JTextField outPath;
	
	public static ArrayList<String> stuff = new ArrayList<String>();

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window window = new window();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public window() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 0, 0));
		frame.setBounds(100, 100, 554, 698);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		inPath = new JTextField();
		inPath.setText("C:\\gcode\\G.txt");
		inPath.setBackground(new Color(192, 192, 192));
		inPath.setBounds(33, 150, 451, 36);
		frame.getContentPane().add(inPath);
		inPath.setColumns(10);
		
		outPath = new JTextField();
		outPath.setText("C:\\gcode\\G-converted.txt");
		outPath.setBackground(new Color(192, 192, 192));
		outPath.setBounds(33, 240, 451, 36);
		frame.getContentPane().add(outPath);
		outPath.setColumns(10);
		
		final JTextPane txtpnInput = new JTextPane();
		txtpnInput.setFont(new Font("Tahoma", Font.PLAIN, 30));
		txtpnInput.setForeground(new Color(0, 0, 0));
		txtpnInput.setBackground(new Color(255, 0, 0));
		txtpnInput.setText("Input");
		txtpnInput.setBounds(33, 108, 89, 43);
		frame.getContentPane().add(txtpnInput);
		
		final JTextPane txtpnOutput = new JTextPane();
		txtpnOutput.setText("Output");
		txtpnOutput.setForeground(Color.BLACK);
		txtpnOutput.setFont(new Font("Tahoma", Font.PLAIN, 30));
		txtpnOutput.setBackground(Color.RED);
		txtpnOutput.setBounds(33, 197, 108, 43);
		frame.getContentPane().add(txtpnOutput);
		
		JTextPane txtpnNielsGcodeConverter = new JTextPane();
		txtpnNielsGcodeConverter.setBackground(new Color(255, 0, 0));
		txtpnNielsGcodeConverter.setFont(new Font("Tahoma", Font.PLAIN, 39));
		txtpnNielsGcodeConverter.setText("Neil's OG-code Converter");
		txtpnNielsGcodeConverter.setBounds(33, 31, 451, 54);
		frame.getContentPane().add(txtpnNielsGcodeConverter);
		
		JButton btnConvert = new JButton("Convert!");
		btnConvert.setForeground(Color.BLACK);
		btnConvert.setBackground(new Color(0, 128, 128));
		btnConvert.setFont(new Font("Tahoma", Font.PLAIN, 26));
		btnConvert.setBounds(33, 287, 451, 65);
		btnConvert.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				String filePath = inPath.getText();
				try {
					Scanner scanner = new Scanner(new File(filePath));
					scanner.useDelimiter("\n");
					while(scanner.hasNext()){
						stuff.add(scanner.next());
					}
					scanner.close();
					for(String s:stuff){
						//Replace G00 Z2 with M03 S100
						//Replace G01 Z-0.100000 F100.0(Penetrate) with M03 S140
						PrintWriter writer = new PrintWriter(outPath.getText(), "UTF-8");
						if(s.toUpperCase().contains("G00 Z2.000000".toUpperCase())){
							writer.println("M03 S100");
						}
						if(s.toUpperCase().contains("G01 Z-0.100000 F100.0(Penetrate)")){
							writer.println("M03 S140");
						}
						else
							writer.println(s);
						writer.close();
					}
				}
				catch (FileNotFoundException | UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					System.out.println("File Error");
				}
				
					
			}
		});
		frame.getContentPane().add(btnConvert);

	}
	
	
}
