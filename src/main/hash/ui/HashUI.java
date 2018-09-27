/**
 * @ HashUI.java 
 */
package hash.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import hash.logic.HashMaker;
import prop.LocaleLabelChangable;
import prop.PropertiesManager;

/**
 * <pre>
 * hash.ui
 * HashUI.java 
 * </pre>
 *
 * @brief	: 
 * @author	: 문재웅(mjw8585@gmail.com)
 * @Date	: 2016. 12. 27.
 */
public class HashUI extends JPanel implements LocaleLabelChangable{

	/*hash encode panel */
	JPanel encodePanel;
	JLabel inputLabel;
	JLabel outputLabel1;  //md5
	JLabel outputLabel2; //sha1
	JLabel outputLabel3;  // sha256 ..
	JLabel outputLabel4;
	JTextField inputField;
	JTextField outputField1;
	JTextField outputField2;
	JTextField outputField3;
	JTextField outputField4;
	JButton transButton;
	
	/*hash decode panel*/
	JPanel decodePanel;
	JLabel inputLabel2;
	JTextField inputField2;
	JTextField outputField5;
	
	PropertiesManager propertiesManager = new PropertiesManager();
	
	private final int LABEL_WIDTH = 50;
	private final int LABEL_HEIGHT = 20;
	private final int LABEL_X = 30;
	private final int LABEL_Y = 30;
	
	private final int FIELD_WIDTH = 500;
	private final int FIELD_HEIGHT = 20;
	private final int FIELD_X = 100;
	private final int FIELD_Y = 30;
	private final int GAP = 10;
	
	public HashUI(){
		
		super.setLayout(null); //필수
		
		
		
		/* Encode Panel */
		encodePanel = new JPanel();
		encodePanel.setLayout(null);
		encodePanel.setBounds(GAP, GAP, 900, 290);
		encodePanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("Encoding"), 
				BorderFactory.createEmptyBorder(GAP, GAP, GAP, GAP)));
		
		
		inputLabel = new JLabel();
		outputLabel1 = new JLabel("MD5");
		outputLabel2 = new JLabel("SHA1");
		outputLabel3 = new JLabel("SHA256");
		outputLabel4 = new JLabel("SHA512");
		inputField = new JTextField();
		outputField1 = new JTextField();
		outputField2 = new JTextField();
		outputField3 = new JTextField();
		outputField4 = new JTextField();
		transButton = new JButton("Encoding");
		
		
		inputLabel.setBounds( LABEL_X, LABEL_Y, LABEL_WIDTH, LABEL_HEIGHT);
		outputLabel1.setBounds(LABEL_X, inputLabel.getY() + LABEL_Y, LABEL_WIDTH, LABEL_HEIGHT);
		outputLabel2.setBounds(LABEL_X, outputLabel1.getY() + LABEL_Y, LABEL_WIDTH, LABEL_HEIGHT);
		outputLabel3.setBounds(LABEL_X, outputLabel2.getY() + LABEL_Y, LABEL_WIDTH, LABEL_HEIGHT);
		outputLabel4.setBounds(LABEL_X, outputLabel3.getY() + LABEL_Y, LABEL_WIDTH, LABEL_HEIGHT);
		
		inputField.setBounds( FIELD_X, FIELD_Y, FIELD_WIDTH, FIELD_HEIGHT);
		outputField1.setBounds(FIELD_X, inputField.getY() + FIELD_Y, FIELD_WIDTH, FIELD_HEIGHT);
		outputField2.setBounds(FIELD_X, outputField1.getY() + FIELD_Y, FIELD_WIDTH, FIELD_HEIGHT);
		outputField3.setBounds(FIELD_X, outputField2.getY() + FIELD_Y, FIELD_WIDTH, FIELD_HEIGHT);
		outputField4.setBounds(FIELD_X, outputField3.getY() + FIELD_Y, FIELD_WIDTH, FIELD_HEIGHT);
		
		transButton.setBounds(150, 200, 130, 40);
		transButton.addActionListener(new TranslateHandler());
		
		encodePanel.add(inputLabel);
		encodePanel.add(outputLabel1);
		encodePanel.add(outputLabel2);
		encodePanel.add(outputLabel3);
		encodePanel.add(outputLabel4);
		encodePanel.add(inputField);
		encodePanel.add(outputField1);
		encodePanel.add(outputField2);
		encodePanel.add(outputField3);
		encodePanel.add(outputField4);
		encodePanel.add(transButton);
		
	
		/* Decode Panel */
		decodePanel = new JPanel(new BorderLayout());
		decodePanel.setLayout(null);
		System.out.println(encodePanel.getY());
		decodePanel.setBounds(GAP, 310, 900, 300);
		decodePanel.setVisible(true);
		decodePanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("Decoding"), 
				BorderFactory.createEmptyBorder(GAP, GAP, GAP, GAP)));
		
		
		inputLabel2 = new JLabel("입력 값");
		inputField2 = new JTextField();
		
		inputLabel2.setBounds( LABEL_X, LABEL_Y, LABEL_WIDTH, LABEL_HEIGHT);
		inputField2.setBounds( FIELD_X, FIELD_Y, FIELD_WIDTH, FIELD_HEIGHT);
		
		decodePanel.add(inputLabel2);
		decodePanel.add(inputField2);
		
		/* and Panel Components */
		add(encodePanel);
		add(decodePanel);
		
		changeLabels(propertiesManager);
    	// 크기 지정
    	setSize(950, 700);
    	setVisible(true);
	}
	
	@Override
	public void changeLabels(PropertiesManager propertiesManager) {
    	// change now propertiesManager
    	if (propertiesManager.getLocale() != this.propertiesManager.getLocale()){
    		this.propertiesManager = propertiesManager;
    	}
    	
    	inputLabel.setText(propertiesManager.getProperty("label.hash.input"));
		
	}
	
	class TranslateHandler implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			//System.out.println(inputField.getText());
			String input = inputField.getText();
			
			// md5
			HashMaker hashMaker = new HashMaker();
			String md5 = hashMaker.MD5(input);
			outputField1.setText(md5);
			
			// sha256
			outputField3.setText(hashMaker.SHA256(input));
			
		}
	}
	
	
}
