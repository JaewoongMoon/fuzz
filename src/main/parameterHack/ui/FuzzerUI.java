/**
 * @ FuzzerUI.java
 * 
 */
package parameterHack.ui;

import java.awt.Event;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Locale;
import java.util.TreeSet;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import parameterHack.domain.DbmsType;
import parameterHack.domain.Fuzzer;
import parameterHack.domain.FuzzerType;
import parameterHack.logic.ParameterHack;
import prop.LocaleLabelChangable;
import prop.PropertiesManager;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
/**
 * <pre>
 * parameterHack
 * FuzzerUI.java 
 * </pre>
 *
 * @brief	: 
 * @author	: 문재웅(mjw8585@gmail.com)
 * @Date	: 2016. 10. 4.
 */
public class FuzzerUI extends JPanel implements LocaleLabelChangable{

	JLabel tabLabel;    
    JComboBox fuzzerBox;
    Vector<String> fuzzers;
    JTextArea input1;
    JTextArea output;
    JLabel label1;
    JLabel label2;
    JLabel label3;
    JLabel label4;
    JTextField userParamField;
    Vector dbmses;
    JComboBox dbmsBox;
    JButton triggerBtn;
    JMenuBar menuBar;
    JMenu menu1;
    JMenuItem menuItem1;
    JLabel refLabel1;
    PropertiesManager propertiesManager = new PropertiesManager(); 
    
    /** 
     *  생성자 
     */ 
    public FuzzerUI() {
    	
    	/************************************************************************/
    	/************************  컴포넌트 세팅  *****************************/
    	
    	// 메뉴바
    	menuBar = new JMenuBar();
    	menu1 = new JMenu("메뉴1");
    	menuItem1 = new JMenuItem("변환");
    	menuBar.add(menu1);
    	menu1.add(menuItem1);
    	menuItem1.setAccelerator(KeyStroke.getKeyStroke('F', Event.CTRL_MASK));
    	menuItem1.addActionListener(new ConversionActionHandler());
    	
    	// fuzzer 콤보 박스
    	fuzzers = new Vector<String>();
    	fuzzers.add("--SELECT--");
    	fuzzers.add(FuzzerType.NORMAL.toString());
    	fuzzers.add(FuzzerType.XSS.toString());
    	fuzzers.add("Actions Script XSS");
    	fuzzers.add(FuzzerType.CSRF.toString());
    	fuzzers.add(FuzzerType.SQL_INJECTION.toString());
    	fuzzers.add(FuzzerType.BLIND_SQL_INJECTION.toString());
    	
    	fuzzerBox = new JComboBox<>(fuzzers);
    	fuzzerBox.addActionListener(new FuzzerSelectionHandler());
    	
    	
    	// 파라메터 입력 텍스트 Input
    	input1 = new JTextArea(5, 10);
    	input1.setLineWrap(true);
    	JScrollPane inputPane1 = new JScrollPane(input1);
    	
    	
    	// 파라메터 출력 텍스트 Output
    	output = new JTextArea(5, 10);
    	output.setLineWrap(true);
    	JScrollPane outputPane = new JScrollPane(output);
    	
    	label1 = new JLabel("입력 값 : 변환할 파라메터 문자열 (파라메터는 &로 구분)");
    	label2 = new JLabel("퍼저 타입 선택");
    	label3 = new JLabel("출력 값 : 자동으로 클립보드에 복사됩니다.");
    	label4 = new JLabel("세팅할 파라메터 값");
    	refLabel1 = new JLabel("컴포넌트간 이동은 Ctrl + TAB 으로 가능합니다.");
    	tabLabel = new JLabel("※ TAB 이동은 Ctrl + 번호로 가능합니다.");
    	
    	// 유저가 입력하는 Param Value
    	userParamField = new JTextField();
    	
    	dbmses = new Vector<String>();
    	dbmses.add("--DBMS SELECT--");
    	for(DbmsType dbmsType : DbmsType.values()){
    		dbmses.add(dbmsType);
    	}
    	dbmsBox = new JComboBox<>(dbmses);
    	
    	// 변환 버튼
    	triggerBtn = new JButton("변환 (Ctrl + F)");
    	triggerBtn.addActionListener(new ConversionActionHandler());
    	//triggerBtn.
    	
    	/************************************************************************/
    	/**********************  컨테이너 view 세팅  **************************/
    	// 레이아웃 세팅
    	super.setLayout(null); 
    	
    	// 컴포넌트 추가 
    	add(menuBar);
    	add(label1);
    	add(fuzzerBox);
    	add(dbmsBox);
    	add(label2);
    	add(inputPane1);
    	add(label3);
    	add(outputPane);
    	add(label4);
    	add(userParamField);
    	add(triggerBtn);
    	add(refLabel1);
    	add(tabLabel);
    	
    	// 컴포넌트 위치 조정
    	int START_X = 30;
    	int START_Y = 10;
    	int PADDING_Y = 30;
    	
    	//menuBar.setBounds(0,0,400,20); // 일단 보이지 않게 
    	tabLabel.setBounds(START_X, START_Y, 500, 20);
    	
    	// 입력 필드
    	label1.setBounds( START_X, tabLabel.getY() + PADDING_Y + 10, 500, 20);
    	inputPane1.setBounds( START_X, label1.getY() + PADDING_Y, 500, 200);
    	
    	// 선택 필드
    	label2.setBounds( 550, tabLabel.getY() + PADDING_Y + 10, 300, 20);
    	fuzzerBox.setBounds(550, label2.getY() + PADDING_Y, 150, 20);
    	dbmsBox.setBounds(720, fuzzerBox.getY(), 150, 20);
    	label4.setBounds(550, fuzzerBox.getY() + PADDING_Y, 300, 20);
    	userParamField.setBounds(550, label4.getY() + PADDING_Y, 250, 20);
    	
    	dbmsBox.setVisible(false);
    	label4.setVisible(false);
    	userParamField.setVisible(false);
    	
    	// 실행 버튼
    	refLabel1.setBounds(550, 190, 300, 20);
    	triggerBtn.setBounds(550, 220, 130, 40);
    	
    	// 출력 필드
    	label3.setBounds( START_X, inputPane1.getY() + inputPane1.getHeight() + PADDING_Y, 500, 20);
    	outputPane.setBounds( START_X, label3.getY() + PADDING_Y, 500, 200);
    	
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
    	label1.setText(propertiesManager.getProperty("label.fuzzer.input.string"));
    	label2.setText(propertiesManager.getProperty("label.fuzzer.type.select"));
    	label3.setText(propertiesManager.getProperty("label.fuzzer.ouput.string"));
    	label4.setText(propertiesManager.getProperty("label.fuzzer.user.input"));
    	refLabel1.setText(propertiesManager.getProperty("label.fuzzer.tip_1"));
    	triggerBtn.setText(propertiesManager.getProperty("label.fuzzer.execute"));
    	tabLabel.setText(propertiesManager.getProperty("label.common.tabLabel"));
    }
    
    /**
     * @brief	: 텍스트 변환 로직을 처리  
     * @author	: 문재웅(mjw8585@gmail.com)
     * @Date	: 2016. 10. 5.
     */
    class ConversionActionHandler implements ActionListener{
    	
    	@Override
    	public void actionPerformed(ActionEvent e) {
    		//System.out.println("**** 선택한 값: " + fuzzerBox.getSelectedItem());
    		
    		
    		// STEP 1. 입력된 파라메터 값 얻어오기 
    		// 값 체크 
    		String inputParam = input1.getText();
    		if(inputParam == null || inputParam.equals("")){
    			JOptionPane.showMessageDialog(null, 
    					propertiesManager.getProperty("alert.fuzzer.noinputparam"));
    			return ;
    		}
    		// (보완) 파라메터 형식에 맞는 지도 체크를 하면 좋을 듯
    		//System.out.println(input1.getText());
    		
    		// STEP 2. Fuzzer 생성
    		// 2-1. 선택 된 Fuzzer Type 처리
    		if (fuzzerBox.getSelectedIndex() == 0){
    			JOptionPane.showMessageDialog(null, 
    					propertiesManager.getProperty("alert.fuzzer.nofuzzertype"));
    			return ;
    		}	
    		Fuzzer fuzzer = new Fuzzer();
    		fuzzer.setFuzzerType(FuzzerType.valueOf(fuzzerBox.getSelectedIndex()));
    		
    		// 2-2. paramValue 처리
    		String paramValue = userParamField.getText() == null? "" : userParamField.getText();
    		fuzzer.setParamValue(paramValue);
    		
    		// 2-3. DbmsType 처리
    		if( fuzzerBox.getSelectedItem() == FuzzerType.SQL_INJECTION.toString()){
    			if (dbmsBox.getSelectedIndex() == 0){
	    			JOptionPane.showMessageDialog(null, 
	    					propertiesManager.getProperty("alert.fuzzer.nodbmstype"));
	    			return ;
    			}else{
    				fuzzer.setDbmsType(DbmsType.valueOf(dbmsBox.getSelectedIndex()));
    			}
    		}
    		
    		// STEP 3. ParameterHack 에게 변환 작업 요청
    		ParameterHack paramHack = new ParameterHack();
    		paramHack.setFuzzer(fuzzer);
    		
    		String result = paramHack.makeFuzzerString(inputParam);
    		
    		// STEP 4. 결과 값 출력 
    		output.setText(result);
    		
    		//클립보드에 복사 
    		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    		if(result != null)
    		{
    		     StringSelection contents = new StringSelection(result);
    		     clipboard.setContents(contents, null);
    		}


    		
    	}
    }
    
    /**
     *
     * @brief	: DBMS 선택 이벤트 핸들러
     * @author	: 문재웅(mjw8585@gmail.com)
     * @Date	: 2016. 10. 5.
     */
    class FuzzerSelectionHandler implements ActionListener{
    	
    	@Override
    	public void actionPerformed(ActionEvent e) {
    		
    		// FuzzerType을 NORMAL을 선택했을 때 : 파라메터 값을 입력받을 수 있도록 한다.
    		if (fuzzerBox.getSelectedItem() == FuzzerType.NORMAL.toString()){   
    			userParamField.setVisible(true);
    			label4.setVisible(true);
    		}else{
    			userParamField.setVisible(false);
    			label4.setVisible(false);
    			userParamField.setText("");
    		}
    		// SQL INJECTION을 선택했을 때 : DBMS ComboBox를 보이게 
    		if (fuzzerBox.getSelectedItem() == FuzzerType.SQL_INJECTION.toString()){
    			dbmsBox.setVisible(true);
    			
    		}else{
    			dbmsBox.setVisible(false);
    			dbmsBox.setSelectedIndex(0);
    		}
    	
    	}
    }
}
