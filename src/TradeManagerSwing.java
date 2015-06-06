import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
	int i; //for문을 위한 변수
	
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
	
	//swing 부분
	JButton button1;
	JButton button2;
	JButton button3;
	JButton button4;
	JButton button5;
	JButton button6;
	JButton send_button;
	JButton clear_button;
	TextArea ta;
	TextArea memo;
	JTextField tf;
	JPanel p1;
	JPanel p2;
	JPanel p3;
	JPanel p4;
	JPanel p5;
	JMenuBar mb;
	JMenu menuFile;
	JMenu menuFileOpen;
	JMenuItem menuItemOpentxt;
	JMenuItem menuItemOpendat;
	JMenu menuFileSave;
	JMenuItem menuItemSavetxt;
	JMenuItem menuItemSavedat;
	JMenuItem menuItemExit;
	
	public MyFrame(){ //생성자
		setTitle("Trade Manager");
		setSize(900, 750);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		button1 = new JButton("1.새 무역정보 추가");
		button2 = new JButton("2.무역정보 수정/삭제");
		button3 = new JButton("3.무역정보 전체보기");
		button4 = new JButton("4.무역정보 검색");
		button5 = new JButton("5.그래프 보기");
		button6 = new JButton("6.날짜별 파이그래프 ");	
		clear_button = new JButton("CLEAR");
		send_button = new JButton("Send");
		ta = new TextArea("",40,88,TextArea.SCROLLBARS_VERTICAL_ONLY);
		memo = new TextArea("",40,30,TextArea.SCROLLBARS_VERTICAL_ONLY);
		tf = new JTextField(20);
		p1 = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel();
		p4 = new JPanel();
		p5 = new JPanel();
		mb = new JMenuBar();
		menuFile = new JMenu("File");
		menuFileOpen = new JMenu("Open");
		menuItemOpentxt = new JMenuItem("txt");
		menuItemOpendat = new JMenuItem("dat");
		menuFileSave = new JMenu("Save");
		menuItemSavetxt = new JMenuItem("txt");
		menuItemSavedat = new JMenuItem("dat");
		menuItemExit = new JMenuItem("Exit");
		menuFileOpen.add(menuItemOpentxt);
		menuFileOpen.add(menuItemOpendat);
		menuFile.add(menuFileOpen);
		menuFileSave.add(menuItemSavetxt);
		menuFileSave.add(menuItemSavedat);
		menuFile.add(menuFileSave);
		menuFile.add(menuItemExit);
		mb.add(menuFile);
		setJMenuBar(mb);
		menuItemOpentxt.addActionListener(this);
		menuItemOpendat.addActionListener(this);
		menuItemSavetxt.addActionListener(this);
		menuItemSavedat.addActionListener(this);
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

		p1.add(tf);
		p1.add(send_button);	
		p2.add(button1);
		p2.add(button2);
		p2.add(button3);
		p2.add(button4);
		p2.add(button5);
		p2.add(button6);
		p3.add(ta,BorderLayout.CENTER);
		add(memo,BorderLayout.EAST);
		add(p2,BorderLayout.NORTH);
		add(p1,BorderLayout.SOUTH);
		add(p3,BorderLayout.CENTER);
	
		setVisible(true);
	}
	
	@Override
	public void focusGained(FocusEvent arg0) {
	
	}

	@Override
	public void focusLost(FocusEvent arg0) {
	
	}

	@Override
	public void keyPressed(KeyEvent arg0) {

		if(arg0.getKeyCode() == KeyEvent.VK_ENTER){ //엔터키가 눌러졌으면
			send_button.doClick(); //send를 클릭
			   
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {

	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == button1){
			Button1_Frame b1 = new Button1_Frame(MyFrame.this);
		}
		if(e.getSource() == button2){
			if(t.size() !=0){
				Button2_Frame b2 = new Button2_Frame(MyFrame.this);
			}
			else{
				ta.append("등록된 자료가 없습니다.\n");
			}
			
		}
		if(e.getSource() == button3){
			if(t.size() !=0){
				for(i=0;i<t.size();i++){
					ta.append("< "+t.get(i).serial_num+" >  "+t.get(i).date.substring(0,4)+"년"+t.get(i).date.substring(5)+"월　　수출건수:"+t.get(i).exports+"　　수출금액:"+t.get(i).export_sum+"　　수입건수:"+t.get(i).imports+"　　수입금액:"+t.get(i).import_sum+"　　무역수지"+(t.get(i).export_sum - t.get(i).import_sum));
				}
			}
			else{
				ta.append("등록된 자료가 없습니다.\n");
			}
			
			
		}
		if(e.getSource() == button4){
			Button4_Frame b4 = new Button4_Frame(MyFrame.this);
		}
		if(e.getSource() == button5){
			Button5_Frame b5 = new Button5_Frame(MyFrame.this);
		}
		if(e.getSource() == send_button){
			memo.append(tf.getText()+"\n");
			tf.setText("");
		}
		if(e.getSource() == menuItemOpentxt){
		}
		if(e.getSource() == menuItemOpendat){
		}
		if(e.getSource() == menuItemSavetxt){
		}
		if(e.getSource() == menuItemSavedat){
		}
		if(e.getSource() == menuItemExit){
			System.exit(0);
		}
		
		
		
	} 
	
}

class Button1_Frame extends JDialog implements ActionListener, KeyListener{
	MyFrame MyFrame;
	JLabel label1;
	JLabel label2;
	JLabel label3;
	JLabel label4;
	JLabel label5;
	JLabel label6;
	JLabel label7;
	JLabel label8;
	JLabel label9;
	JTextField tf1;
	JTextField tf2;
	JTextField tf3;
	JTextField tf4;
	JTextField tf5;
	JTextField tf6;
	JTextField tf7;
	JPanel p1;
	JPanel p2;
	JPanel p3;
	JPanel p4;
	JPanel p5;
	JButton SEND;
	JButton CLOSE;
	public Button1_Frame(MyFrame MyFrame){
		setTitle("Add Trade");
		setSize(280,240);
		this.MyFrame = MyFrame;
		label1= new JLabel("날짜");
		label2= new JLabel("년");
		label3= new JLabel("월");
		label4= new JLabel("상대국가");
		label5= new JLabel("수출건수");
		label6= new JLabel("수출금액");
		label7= new JLabel("수입건수");
		label8= new JLabel("수입금액");
		label9= new JLabel("　　　　　　　　　　단위:천불(USD 1,000)");
		tf1 = new JTextField(4);
		tf2 = new JTextField(2);
		tf3 = new JTextField(7);
		tf4 = new JTextField(5);
		tf5 = new JTextField(5);
		tf6 = new JTextField(5);
		tf7 = new JTextField(5);
		p1 = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel();
		p4 = new JPanel();
		p5 = new JPanel();
		SEND = new JButton("SEND");
		CLOSE = new JButton("CLOSE");
		setLayout(new GridLayout(6,1));
		
		p1.add(label1);
		p1.add(tf1);
		p1.add(label2);
		p1.add(tf2);
		p1.add(label3);
		p2.add(label4);
		p2.add(tf3);
		p3.add(label5);
		p3.add(tf4);
		p3.add(label6);
		p3.add(tf5);
		p4.add(label7);
		p4.add(tf6);
		p4.add(label8);
		p4.add(tf7);
		p5.add(SEND);
		p5.add(CLOSE);
		add(p1,0);
		add(p2,1);
		add(p3,2);	
		add(p4,3);	
		add(label9,4);	
		add(p5,5);
		setVisible(true);
		SEND.addActionListener(this);
		CLOSE.addActionListener(this);
	}
	

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == SEND){
			Button1_Frame.this.MyFrame.t.add(new Trade());
			Button1_Frame.this.MyFrame.t.get(this.MyFrame.t.size()-1).date = (tf1.getText()+"."+tf2.getText());
			Button1_Frame.this.MyFrame.t.get(this.MyFrame.t.size()-1).nation = tf3.getText();
			Button1_Frame.this.MyFrame.t.get(this.MyFrame.t.size()-1).exports = Integer.parseInt(tf4.getText());
			Button1_Frame.this.MyFrame.t.get(this.MyFrame.t.size()-1).export_sum = Integer.parseInt(tf5.getText());
			Button1_Frame.this.MyFrame.t.get(this.MyFrame.t.size()-1).imports = Integer.parseInt(tf6.getText());
			Button1_Frame.this.MyFrame.t.get(this.MyFrame.t.size()-1).import_sum = Integer.parseInt(tf7.getText());
			Button1_Frame.this.MyFrame.t.get(this.MyFrame.t.size()-1).serial_num = this.MyFrame.t.size();
			Button1_Frame.this.dispose();//모달창 닫기             
			
		}
		if(e.getSource() == CLOSE){
			dispose();
		}

	}
	
}

class Button2_Frame extends JDialog implements ActionListener, KeyListener{
	MyFrame MyFrame;
	JLabel label1;
	JLabel label2;
	JLabel label3;
	JLabel label4;
	JLabel label5;
	JLabel label6;
	JLabel label7;
	JLabel label8;
	JLabel label9;
	JTextField tf1;
	JTextField tf2;
	JTextField tf3;
	JTextField tf4;
	JTextField tf5;
	JTextField tf6;
	JTextField tf7;
	JPanel p1;
	JPanel p2;
	JPanel p3;
	JPanel p4;
	JPanel p5;
	JButton Edit;
	JButton Delete;
	JButton CLOSE;
	Choice nation_choice;
	Choice date_choice;
	HashSet<String> sample = new HashSet<String>();
	HashSet<String> sample2 = new HashSet<String>();
	public Button2_Frame(MyFrame MyFrame){
		setTitle("Add Trade");
		setSize(280,240);
		this.MyFrame = MyFrame;

		
		
		label1= new JLabel("날짜");
		label2= new JLabel("년");
		label3= new JLabel("월");
		label4= new JLabel("상대국가");
		label5= new JLabel("수출건수");
		label6= new JLabel("수출금액");
		label7= new JLabel("수입건수");
		label8= new JLabel("수입금액");
		label9= new JLabel("　　　　　　　　　　단위:천불(USD 1,000)");
		tf1 = new JTextField(4);
		tf2 = new JTextField(2);
		tf3 = new JTextField(7);
		tf4 = new JTextField(5);
		tf5 = new JTextField(5);
		tf6 = new JTextField(5);
		tf7 = new JTextField(5);
		p1 = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel();
		p4 = new JPanel();
		p5 = new JPanel();
		Edit = new JButton("Edit");
		Delete = new JButton("Delete");
		CLOSE = new JButton("CLOSE");
		nation_choice = new Choice();
		date_choice = new Choice();
		
		for(int i=0;i<this.MyFrame.t.size();i++){
			sample.add(this.MyFrame.t.get(i).nation);
		}
		Iterator<String> iterator = sample.iterator();
		while(iterator.hasNext()){
			 String nation = iterator.next();
			 nation_choice.add(nation);
		}
		
		for(int i=0;i<this.MyFrame.t.size();i++){
			sample2.add(this.MyFrame.t.get(i).date);
		}
		 Iterator<String> iterator2 = sample2.iterator();
		 while(iterator2.hasNext()){
			 String date = iterator2.next();
			 date_choice.add(date);
		 }
		
		 
		 

		add(p1,0);
		add(p2,1);
		add(p3,2);	
		add(p4,3);	
		add(date_choice,4);	
		add(nation_choice,5);
		setVisible(true);
		Edit.addActionListener(this);
		Delete.addActionListener(this);
		CLOSE.addActionListener(this);
	}
	

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == Edit){           
			
		}
		if(e.getSource() == Delete){           
			
		}
		if(e.getSource() == CLOSE){
			dispose();
		}

	}
	
}

class Button4_Frame extends JDialog implements ActionListener, KeyListener{
	MyFrame MyFrame;
	Choice nation_choice;
	Choice date_choice;
	public Button4_Frame(MyFrame MyFrame){
		this.MyFrame = MyFrame;
	}
	

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
	
}
class Button5_Frame extends JDialog implements ActionListener, KeyListener{
	MyFrame MyFrame;
	public Button5_Frame(MyFrame MyFrame){
		this.MyFrame = MyFrame;
	}
	

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
	
}
class Button6_Frame extends JDialog implements ActionListener, KeyListener{
	MyFrame MyFrame;
	public Button6_Frame(MyFrame MyFrame){
		this.MyFrame = MyFrame;
	}
	

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
	
}