/**
 * @ java 
 */


import java.awt.Event;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.util.Locale;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;

import encoding.ui.EncodeUI;
import extract.ui.ExtractUI;
import hash.ui.HashUI;
import parameterHack.ui.FuzzerUI;
import prop.PropertiesManager;

/**
 * <pre>
 * parameterHack.ui
 * java 
 * </pre>
 *
 * @brief	: 
 * @author	: jwmoon(mjw8585@gmail.com)
 * @Date	: 2016. 10. 11.
 */
public class MainUI extends JFrame{

	/*UI 들 : 탭으로 분리됨 */
	public ExtractUI extractUI = null;
	public FuzzerUI fuzzerUI = null;
	public HashUI hashUI = null;
	public EncodeUI encodeUI = null;
	//public CipherUI cipherUI = null;
	
	public JTabbedPane tabs = null;
	
	
	// 국가 선택 버튼
	public JButton flag1 = null;
	public JButton flag2 = null;
	public JButton flag3 = null;
	
	public MainUI(){
		setTitle("Fuzz String Maker Ver 1.5 - by jwmoon");
		setLayout(null);
		
		/*국기 선택 버튼 및 핸들러 */
		flag1 = new JButton();
		flag2 = new JButton();
		flag3 = new JButton();
		String path1 = "resources/img/american-flag-graphic.png";
		String path2 = "resources/img/japanese-flag-small.jpg";
		String path3 = "resources/img/korean-flag-small.jpg";
		
		try{
			Image img1 = ImageIO.read(getClass().getResource(path1));
			Image img2 = ImageIO.read(getClass().getResource(path2));
			Image img3 = ImageIO.read(getClass().getResource(path3));
			
			flag1.setIcon(new ImageIcon(img1));
			flag2.setIcon(new ImageIcon(img2));
			flag3.setIcon(new ImageIcon(img3));
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		add(flag1);
		add(flag2);
		add(flag3);
		flag1.setBounds(5, 5, 25, 15);
		flag2.setBounds(35, 5, 25, 15);
		flag3.setBounds(65, 5, 25, 15);
		flag1.setVisible(true);
		flag2.setVisible(true);
		flag3.setVisible(true);
		flag1.addActionListener(new LocaleSelectionHandler(Locale.ENGLISH));
		flag2.addActionListener(new LocaleSelectionHandler(Locale.JAPAN));
		flag3.addActionListener(new LocaleSelectionHandler(Locale.KOREA));

		
		
		/* 탭 화면들 추가 */
		extractUI = new ExtractUI();  //1. 추출기
		fuzzerUI = new FuzzerUI(); // 2. 퍼저
		//hashUI = new HashUI();  // 3. 해싱
		//encodeUI = new EncodeUI();  //4. 인코딩
		//cipherUI = new CipherUI();  //5. 암호
									 // 6. 파싱
		
		
		tabs = new JTabbedPane();
		
		/*탭의  순서가 여기서 결점됨! */
		tabs.addTab("Extractor", extractUI);
		tabs.addTab("Fuzzer", fuzzerUI);
		//tabs.addTab("Hash", hashUI);
		//tabs.addTab("Encode", encodeUI);
		//tabs.addTab("Encrypt / Decrypt", cipherUI);
		//tabs.setEnabledAt(1, false); 
		
		tabs.setBounds(0, 25, 950, 700);
		add(tabs);
		
		
		
		/* 단축키 동작을 위한 메뉴바 (invisible) 추가 */
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu();
		JMenuItem menuItem1 = new JMenuItem("Tengoku");
		JMenuItem menuItem2 = new JMenuItem("Extractor");
		JMenuItem menuItem3 = new JMenuItem("Fuzzer");
		
		menuItem1.setAccelerator(KeyStroke.getKeyStroke('1', Event.CTRL_MASK));
		menuItem1.addActionListener(new TabSelectionHandler(0));
		menuItem2.setAccelerator(KeyStroke.getKeyStroke('2', Event.CTRL_MASK));
		menuItem2.addActionListener(new TabSelectionHandler(1));
		menuItem3.setAccelerator(KeyStroke.getKeyStroke('3', Event.CTRL_MASK));
		menuItem3.addActionListener(new TabSelectionHandler(2));
		
		menu.add(menuItem1);
		menu.add(menuItem2);
		menu.add(menuItem3);
		menuBar.add(menu);
		add(menuBar);
		 
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(950, 700);
		setVisible(true);
		setResizable(false);
	}
	
	public static void main(String[] args) {
		new MainUI();
	}
	
	// 국가 선택 버튼 핸들러 
	class LocaleSelectionHandler implements ActionListener{
		private Locale locale; 
		private PropertiesManager propManager;
		
		public LocaleSelectionHandler(Locale locale){
			this.locale = locale;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(propManager == null){
				propManager = new PropertiesManager(locale);
			}
			fuzzerUI.changeLabels(propManager);  //탭이 추가될 때마다 여기에 추가 
			extractUI.changeLabels(propManager);
		}
		
	}
	
	// 탭 선택 핸들러 
	class TabSelectionHandler implements ActionListener{

		private int selectedTab;
		
		public TabSelectionHandler(int selectedTab) {
			this.selectedTab = selectedTab;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			tabs.setSelectedIndex(selectedTab);
		}
	}
}
