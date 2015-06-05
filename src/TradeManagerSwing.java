import java.awt.BorderLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class TradeManagerSwing {
	public static void main (String [] args){
		MyFrame f = new MyFrame();
	}
	
}

class MyFrame extends JFrame implements ActionListener, KeyListener, FocusListener{
	
	ArrayList<Trade> t = new ArrayList<Trade>();
	
	/*
	    FileInputStream fis = new FileInputStream("D:/TradeList.txt");
		InputStreamReader isl = new InputStreamReader(fis,"euc-kr");
		BufferedReader br  =  new BufferedReader(isl);//br.readLine();
	 */
	
	
	/*
	    FileOutputStream fos = new FileOutputStream("D:/TradeList_output.txt");
		OutputStreamWriter osw = new OutputStreamWriter(fos,"euc-kr");
		BufferedWriter bw  =  new BufferedWriter(osw); 
	*/
	
	
	/*
	    fout = new FileOutputStream("TradeData.dat");
        oos = new ObjectOutputStream(fout);
           
        oos.writeObject(f);
        oos.reset();
            
        System.out.println("저장되었습니다"); 
	 
	 */
	

	JButton button1;
	JButton button2;
	JButton button3;
	JButton button4;
	JButton button5;
	JButton button6;
	JButton send_button;
	TextArea ta;
	TextArea memo;
	JTextField tf;
	JPanel p1;
	JPanel p2;
	JPanel p3;
	JMenuBar mb;
	JMenu menuFile;
	JMenuItem menuItemOpen;
	JMenuItem menuItemOpentxt;
	JMenuItem menuItemOpendat;
	JMenuItem menuItemSave;
	JMenuItem menuItemSavetxt;
	JMenuItem menuItemSavedat;
	JMenuItem menuItemExit;
	
	public MyFrame(){
		setTitle("Trade Manager");
		setSize(850, 750);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		button1 = new JButton("1.새 무역정보 추가");
		button2 = new JButton("2.무역정보 제거");
		button3 = new JButton("3.무역정보 수정");
		button4 = new JButton("4.무역정보 검색");
		button5 = new JButton("5.그래프 보기");
		button6 = new JButton("6.날짜별 파이그래프 ");		
		send_button = new JButton("Send");
		ta = new TextArea("",40,80,TextArea.SCROLLBARS_VERTICAL_ONLY);
		memo = new TextArea("",40,30,TextArea.SCROLLBARS_VERTICAL_ONLY);
		tf = new JTextField(20);
		p1 = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel();
		mb = new JMenuBar();
		menuFile = new JMenu("File");
		menuItemOpen = new JMenuItem("Open");
		menuItemSave = new JMenuItem("Save");
		menuItemExit = new JMenuItem("Exit");
		menuFile.add(menuItemOpen);
		menuFile.add(menuItemSave);
		menuFile.add(menuItemExit);
		mb.add(menuFile);
		setJMenuBar(mb);
		menuItemOpen.addActionListener(this);
		menuItemSave.addActionListener(this);
		menuItemExit.addActionListener(this);
		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
		button4.addActionListener(this);
		button5.addActionListener(this);
		button6.addActionListener(this);
		send_button.addActionListener(this);
		tf.addKeyListener(this);
		tf.addFocusListener(this);

		p1.add(tf,BorderLayout.CENTER);
		p1.add(send_button,BorderLayout.EAST);
		p2.add(button1);
		p2.add(button2);
		p2.add(button3);
		p2.add(button4);
		p2.add(button5);
		p2.add(button6);
		p3.add(ta,BorderLayout.CENTER);
		p3.add(memo,BorderLayout.EAST);
		add(p2,BorderLayout.NORTH);
		add(p1,BorderLayout.SOUTH);
		add(p3,BorderLayout.CENTER);
	
	
		setVisible(true);
	}
	
	
	
	
	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	} 
	
}
