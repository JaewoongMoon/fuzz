/**
 * @ ExtractUI.java
 * 
 */
package extract.ui;

import java.awt.Event;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import extract.domain.ExtractModel;
import extract.domain.ExtractType;
import extract.logic.Extractor;
import prop.LocaleLabelChangable;
import prop.PropertiesManager;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
/**
 * <pre>
 * parameterHack
 * ExtractUI.java 
 * </pre>
 *
 * @brief	: 
 * @author	: 문재웅(mjw8585@gmail.com)
 * @Date	: 2016. 10. 4.
 */
public class ExtractUI extends JPanel implements LocaleLabelChangable{

	JLabel tabLabel;    
    JComboBox extractorBox;
    Vector<String> extractors;
    JTextArea input1;
    JTextArea output;
    JLabel label1;
    JLabel label2;
    JLabel label3;
    JButton triggerBtn;
    JMenuBar menuBar;
    JMenu menu1;
    JMenuItem menuItem1;
    JLabel refLabel1;
    PropertiesManager propertiesManager = new PropertiesManager(); 
    
    /** 
     *  생성자 
     */ 
    public ExtractUI() {
    	
    	/************************************************************************/
    	/************************  컴포넌트 세팅  *****************************/
    	
    	// 메뉴바
    	menuBar = new JMenuBar();
    	menu1 = new JMenu("메뉴1");
    	menuItem1 = new JMenuItem("변환");  //변환 버튼
    	menuBar.add(menu1);
    	menu1.add(menuItem1);
    	menuItem1.setAccelerator(KeyStroke.getKeyStroke('F', Event.CTRL_MASK)); //CTRL + F 로 구동
    	menuItem1.addActionListener(new ConversionActionHandler());
    	
    	// extractor 콤보 박스
    	extractors = new Vector<String>();
    	extractors.add("Input 필드의 name 추출");
    	extractors.add("모든 DOM 객체의 name 추출");
    	
    	extractorBox = new JComboBox<>(extractors);
    	//extractorBox.addActionListener(new ConversionActionHandler());
    	
    	
    	// 파라메터 입력 텍스트 Input
    	input1 = new JTextArea(5, 10);
    	input1.setLineWrap(true);
    	JScrollPane inputPane1 = new JScrollPane(input1);
    	
    	// 파라메터 출력 텍스트 Output
    	output = new JTextArea(5, 10);
    	output.setLineWrap(true);
    	JScrollPane outputPane = new JScrollPane(output);
    	
    	label1 = new JLabel("입력 값 : html 코드를 넣어주세요. ");
    	label2 = new JLabel("추출할 필드 타입 선택");
    	label3 = new JLabel("출력 값 : 자동으로 클립보드에 복사됩니다.");
    	refLabel1 = new JLabel("컴포넌트간 이동은 Ctrl + TAB 으로 가능합니다.");
    	tabLabel = new JLabel("※ TAB 이동은 Ctrl + 번호로 가능합니다.");
    	
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
    	add(extractorBox);
    	add(label2);
    	add(inputPane1);
    	add(label3);
    	add(outputPane);
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
    	extractorBox.setBounds(550, label2.getY() + PADDING_Y, 250, 20);
    	
    	// 실행 버튼
    	triggerBtn.setBounds(550, 220, 130, 40);  
    	refLabel1.setBounds(550, 190, 300, 20);
    	
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
    	label1.setText(propertiesManager.getProperty("label.extractor.input.string"));
    	label2.setText(propertiesManager.getProperty("label.extractor.type.select"));
    	label3.setText(propertiesManager.getProperty("label.fuzzer.ouput.string"));  //공통
    	refLabel1.setText(propertiesManager.getProperty("label.fuzzer.tip_1")); //공통
    	triggerBtn.setText(propertiesManager.getProperty("label.fuzzer.execute")); //공통
    	tabLabel.setText(propertiesManager.getProperty("label.common.tabLabel"));
    	
    	// 선택 박스의 값을 변경
    	//extractors.removeAllElements();
    	Vector<String> newList = new Vector<String>();
    	newList.add(propertiesManager.getProperty("label.extractor.type_1"));
    	newList.add(propertiesManager.getProperty("label.extractor.type_2"));
    	extractorBox.removeAllItems();
    	for(String label: newList){
    		extractorBox.addItem(label);
    	}
    }
    
    /**
     * @brief	: 텍스트 변환 로직을 처리  
     * @author	: 문재웅(mjw8585@gmail.com)
     * @Date	: 2016. 10. 5.
     */
    class ConversionActionHandler implements ActionListener{
    	
    	@Override
    	public void actionPerformed(ActionEvent e) {
    		
    		// STEP 1. 입력 필드 값 얻어오기 
    		// 값 체크 
    		String inputParam = input1.getText();
    		if(inputParam == null || inputParam.equals("")){
    			JOptionPane.showMessageDialog(null, 
    					propertiesManager.getProperty("alert.common.noinput"));
    			return ;
    		}
    		
    		// STEP 2. 모델객체 생성
    		ExtractModel model = new ExtractModel();
    		model.setExtractType(ExtractType.valueOf(extractorBox.getSelectedIndex() + 1));

    		
    		// STEP 3. Logic 오브젝트에게 변환 작업 요청
    		Extractor ex = new Extractor();
    		ex.setModel(model);
    		
    		Set<String> names = ex.getNameFields(inputParam, model.getExtractType());
    		String result = String.join("&", names);
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
  
}
