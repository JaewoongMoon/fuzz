/**
 * @ EncodeUI.java 
 */
package encoding.ui;

import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import prop.LocaleLabelChangable;
import prop.PropertiesManager;

/**
 * <pre>
 * encoding.ui
 * EncodeUI.java 
 * </pre>
 *
 * @brief	: 
 * @author	: 문재웅(mjw8585@gmail.com)
 * @Date	: 2017. 1. 31.
 */
public class EncodeUI extends JPanel implements LocaleLabelChangable {

	/*hash encode panel */
	JPanel encodePanel;
	JLabel inputLabel;
	JTextArea inputArea;
	JButton transButton;
	
	/*hash decode panel*/
	JPanel decodePanel;
	JLabel inputLabel2;
	JTextField inputField2;
	JTextField outputField5;
	
	
	/*common variables*/
	private final int LABEL_WIDTH = 50;
	private final int LABEL_HEIGHT = 20;
	private final int LABEL_X = 30;
	private final int LABEL_Y = 30;
	
	private final int FIELD_WIDTH = 500;
	private final int FIELD_HEIGHT = 20;
	private final int FIELD_X = 100;
	private final int FIELD_Y = 30;
	private final int GAP = 10;
	
	public EncodeUI(){
		super.setLayout(null);
		
		/* Encode Panel */
		encodePanel = new JPanel();
		encodePanel.setLayout(null);
		encodePanel.setBounds(GAP, GAP, 900, 290);
		encodePanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("Input"), 
				BorderFactory.createEmptyBorder(GAP, GAP, GAP, GAP)));
		
		inputLabel = new JLabel("input");
		inputArea = new JTextArea(5, 10);
	}
	
	
	@Override
	public void changeLabels(PropertiesManager propertiesManager) {
		// TODO Auto-generated method stub
		
	}
	
}
