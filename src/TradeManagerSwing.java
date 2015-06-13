import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.RefineryUtilities;

public class TradeManagerSwing {
	public static void main(String[] args) {
		new MyFrame();
	}
}

class MyFrame extends JFrame implements ActionListener, KeyListener {

	ArrayList<Trade> t = new ArrayList<Trade>();
	ArrayList<MyTrade> mt = new ArrayList<MyTrade>();
	ArrayList<Nations> n = new ArrayList<Nations>();
	int i; // for문을 위한 변수
	int count=1; // MyTrade에 새로운 serial을 부여할때 쓰는 변수
	
	// swing 부분
	JButton button1;
	JButton button2;
	JButton button3;
	JButton button3_;
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
	JMenuItem menuItemOpen;
	JMenuItem menuItemSave;
	JMenuItem menuItemExit;

	public MyFrame() { // 생성자
		setTitle("Trade Manager");
		setSize(1050, 760);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		button1 = new JButton("1.무역정보 추가");
		button2 = new JButton("2.무역정보 수정/삭제");
		button3 = new JButton("3.무역정보 전체보기");
		button3_ = new JButton("3-2.국가별 통계");
		button4 = new JButton("4.무역정보 검색");
		button5 = new JButton("5.무역수지 그래프");
		button6 = new JButton("6.항목별 파이그래프 ");
		clear_button = new JButton("CLEAR");
		send_button = new JButton("Send");
		ta = new TextArea("국내 무역총괄 관리 프로그램입니다. 단축키를 보시려면 /?을 입력해주세요.\n", 39, 109, TextArea.SCROLLBARS_VERTICAL_ONLY);
		memo = new TextArea("", 39, 30, TextArea.SCROLLBARS_VERTICAL_ONLY);
		tf = new JTextField(20);
		p1 = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel();
		p4 = new JPanel();
		p5 = new JPanel();
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
		button3_.addActionListener(this);
		button4.addActionListener(this);
		button5.addActionListener(this);
		button6.addActionListener(this);
		send_button.addActionListener(this);
		tf.addKeyListener(this);

		p1.add(tf);
		p1.add(send_button);
		p2.add(button1);
		p2.add(button2);
		p2.add(button3);
		p2.add(button3_);
		p2.add(button4);
		p2.add(button5);
		p2.add(button6);
		p3.add(ta, BorderLayout.CENTER);
		add(memo, BorderLayout.EAST);
		add(p2, BorderLayout.NORTH);
		add(p1, BorderLayout.SOUTH);
		add(p3, BorderLayout.CENTER);
		

		setVisible(true);
	}


	public void set_Nations(){
		n.clear();
		HashSet<String> sample = new HashSet<String>(); //총 나라의 수를 구하기
		for (int i = 0; i < t.size(); i++) { 
			sample.add(t.get(i).nation);
		}
		for(int j=0;j<sample.size();j++){ //총 나라의 수만큼 Nations ArrayList생성
			n.add(new Nations());
		}
		
		int k=0;
		Iterator<String> iterator = sample.iterator();
		while (iterator.hasNext()) {
			String nation = iterator.next();
			n.get(k).name=nation;
			k++;
		}
		
		for(int j=0;j<n.size();j++){
			for(i=0;i<t.size();i++){
				if(t.get(i).nation.equals(n.get(j).name)){
					n.get(j).total_exports += t.get(i).exports;
					n.get(j).total_export_sum += t.get(i).export_sum;
					n.get(j).total_imports += t.get(i).imports;
					n.get(j).total_import_sum += t.get(i).import_sum;		
				}
			}	
		}		
	}
	public void add(){
		new Button1_Frame(MyFrame.this);
	}
	
	public void edit(){
		if (t.size() != 0) {
			new Button2_Frame(MyFrame.this);
		} else {
			ta.append("등록된 자료가 없습니다.\n");
		}
	}
	
	public void show_all(){
		if (t.size() != 0) {
			for (i = 0; i < t.size(); i++) {
				ta.append(t.get(i).toString());
			}
		} else {
			ta.append("등록된 자료가 없습니다.\n");
		}
	}
	
	public void show_by_nation(){
		set_Nations();
		HashSet<String> sample = new HashSet<String>();
		for (int i = 0; i < t.size(); i++) { // 콤보박스 설정
			sample.add(t.get(i).nation);
		}
		Iterator<String> iterator = sample.iterator();
		int i=0;
		String [] nation = new String[n.size()]; 
		while (iterator.hasNext()) {
			nation[i] = iterator.next();
			i++;
		}
		Arrays.sort(nation);

		if (t.size() != 0) {
			for(i=0;i<nation.length;i++){
				for(int j=0;j<n.size();j++){
					if(nation[i].equals(n.get(j).name)){
						ta.append(n.get(j).toString());
					}
				}
			}
		} else {
			ta.append("등록된 자료가 없습니다.\n");
		}
	}
	
	public void search(){
		if (t.size() != 0) {

			new Button4_Frame(MyFrame.this);
		} else {
			ta.append("등록된 자료가 없습니다.\n");
		}
	}
	public void line_graph(){
		if (t.size() != 0) {
			new Button5_Select_Frame(MyFrame.this);
		} else {
			ta.append("등록된 자료가 없습니다.\n");
		}
	}
	public void pie_graph(){
		if (t.size() != 0) {
			new Button6_Select_Frame(MyFrame.this);				
			
		} else {
			ta.append("등록된 자료가 없습니다.\n");
		}
	}
	public void send_to_memo(){
		if(tf.getText().equals("/?")){
			ta.append("'/add 시리얼번호' - 메모추가\n'/delete' 시리얼번호 - 메모삭제\n'/show' - 메모 전체보기\n'/save' - 메모를 txt파일로 저장 \n'/load' - txt파일을 메모로 불러오기\n'/clear' - 메인창 클리어\n'/clear memo' - 메모창 클리어\n");
			tf.setText("");
		}
		else if(tf.getText().equals("/clear")){
			ta.setText("");
			tf.setText("");
		}
		else if(tf.getText().equals("/clear memo")){
			memo.setText(" ");
			tf.setText("");
		}
		else if(t.size() <= 0){
			ta.append("아직 자료가 등록되지 않았습니다. 자료를 먼저 등록해주세요.\n");
			tf.setText("");
		}
		else{

			if(tf.getText().indexOf("/add ") ==0){
				add_MyTrade(Integer.parseInt(tf.getText().substring(5)));
				tf.setText("");
			}
			else if(tf.getText().indexOf("/delete ")==0){
				delete_MyTrade(Integer.parseInt(tf.getText().substring(8)));
				tf.setText("");
			}
			else if(tf.getText().equals("/show")){
				show_MyTrade();
				tf.setText("");
			}
			else if(tf.getText().equals("/save")){
				save_MyTrade();
				tf.setText("");
			}
			else if(tf.getText().equals("/load")){
				load_MyTrade();
				tf.setText("");
			}
			else{
				ta.append(tf.getText() + "\n");
				tf.setText("");
			}
		}	
	}
	public void add_MyTrade(int serial){		
		if(serial > t.size() || serial <=0){
			ta.append("범위밖의 숫자를 입력하셨습니다.\n");
		}
		else{
			mt.add(new MyTrade());
			mt.get(mt.size()-1).date =  t.get(serial-1).date;
			mt.get(mt.size()-1).nation = t.get(serial-1).nation;
			mt.get(mt.size()-1).serial_num = count; 
			mt.get(mt.size()-1).exports = t.get(serial-1).exports;
			mt.get(mt.size()-1).export_sum = t.get(serial-1).export_sum;
			mt.get(mt.size()-1).imports = t.get(serial-1).imports;
			mt.get(mt.size()-1).import_sum = t.get(serial-1).import_sum;
			memo.append(mt.get(mt.size()-1).toString());
			count++;
		}
	}
	public void delete_MyTrade(int serial){
		if(serial > mt.size() || serial <=0){
			ta.append("범위밖의 숫자를 입력하셨습니다.\n");
		}
		else{
			int del_index = -1;
			for(i=0;i<mt.size();i++){
				if(mt.get(i).serial_num ==Integer.parseInt(tf.getText().substring(8))){
					del_index = i;
					System.out.println("ok");
				}
			}
			mt.remove(del_index);
		}
	}
	public void show_MyTrade(){
		if(mt.size() == 0){
			ta.append("메모에 등록한 자료가 없습니다..");
		}
		else{
			memo.setText("");
			for(i=0;i<mt.size();i++){
				memo.append(mt.get(i).toString());
			}
		}
	}
	public void save_MyTrade(){
		if(mt.size()==0){
			ta.append("아직 메모에 등록된 자료가 없습니다.");
		}
		else{
			File f = null;
			JFileChooser fc = new JFileChooser();
			String save_path = null;
			FileNameExtensionFilter filter = new FileNameExtensionFilter("텍스트문서(*.txt)", "txt"); 
			fc.setFileFilter(filter); 
			
			int answer = fc.showSaveDialog(null);	
			if(answer == JFileChooser.APPROVE_OPTION){
	            f = fc.getSelectedFile();
	            save_path = f.getAbsolutePath();
			}
			if(f!=null){
				String format = f.toString();
				format = format.substring(format.length()-3, format.length());				
				FileOutputStream fos = null;
				OutputStreamWriter osw = null;
				BufferedWriter bw;
				
				try {
					fos = new FileOutputStream(save_path);
					osw = new OutputStreamWriter(fos, "euc-kr");
					bw = new BufferedWriter(osw);
					for (i = 0; i < mt.size(); i++) {
						bw.write(mt.get(i).date + "/" + mt.get(i).nation + "/"
								+ mt.get(i).exports + "/" + mt.get(i).export_sum
								+ "/" + mt.get(i).imports + "/"
								+ mt.get(i).import_sum);
						bw.newLine();
					}
					ta.append("메모를 텍스트 파일로 저장했습니다.\n");
					bw.close();
					osw.close();
					fos.close();

				} catch (IOException ioe) {
					// TODO Auto-generated catch block
					ioe.printStackTrace();
				}	
			}	
		}
	}
	public void load_MyTrade(){
		File f = null;
		JFileChooser fc = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("텍스트문서(*.txt)", "txt"); 
		fc.setFileFilter(filter); 
		int answer = fc.showOpenDialog(null); 
		if(answer == JFileChooser.APPROVE_OPTION){ 
			f = fc.getSelectedFile();
		}
		if(f != null){
			mt.clear();	
			try {
				FileInputStream fis = null;
				InputStreamReader isl = null;
				fis = new FileInputStream(f);
				isl = new InputStreamReader(fis, "euc-kr");
				BufferedReader br = new BufferedReader(isl);
				int count = 0;
				while (true) {
					String list = br.readLine();
					if (list != null) {
						String[] list_split = list.split("/");
						for (i = 0; i < 6; i++) {
							list_split[i] = list_split[i].trim();
						}
						mt.add(new MyTrade());
						if (list_split[0].substring(5).length() == 1) {
							list_split[0] = "0" + list_split[0];
						}
						mt.get(count).date = list_split[0];
						mt.get(count).nation = list_split[1];
						mt.get(count).serial_num = (count + 1);
						mt.get(count).exports = Integer.parseInt(list_split[2]);
						mt.get(count).export_sum = Integer
								.parseInt(list_split[3]);
						mt.get(count).imports = Integer.parseInt(list_split[4]);
						mt.get(count).import_sum = Integer
								.parseInt(list_split[5]);
						count++;

					} else {
						break;
					}
				}
				ta.append("메모를 텍스트 파일에서 로드했습니다.\n");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}

	public void load(){
		File f = null;
		JFileChooser fc = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("텍스트문서(*.txt)", "txt"); 
		FileNameExtensionFilter filter2 = new FileNameExtensionFilter("데이터파일(*.dat)", "dat"); 
		fc.setFileFilter(filter); 
		fc.setFileFilter(filter2); 
		int answer = fc.showOpenDialog(null); 
		if(answer == JFileChooser.APPROVE_OPTION){ 
			f = fc.getSelectedFile();
		}
		
		if(f != null){
			t.clear();
			String format = f.toString();
			format = format.substring(format.length()-3, format.length());
			if(format.equals("txt")){
				load_to_txt(f);
			}
			else if(format.equals("dat")){
				load_to_dat(f);
			}
		}			
	}
	public void load_to_txt(File f){
		try {
			FileInputStream fis = null;
			InputStreamReader isl = null;
			fis = new FileInputStream(f);
			isl = new InputStreamReader(fis, "euc-kr");
			BufferedReader br = new BufferedReader(isl);
			int count = 0;
			

			while (true) {
				String list = br.readLine();
				if (list != null) {
					String[] list_split = list.split("/");
					for (i = 0; i < 6; i++) {
						list_split[i] = list_split[i].trim();
					}
					t.add(new Trade());
					if (list_split[0].substring(5).length() == 1) {
						list_split[0] = "0" + list_split[0];
					}

					t.get(count).date = list_split[0];
					t.get(count).nation = list_split[1];
					t.get(count).serial_num = (count + 1);
					t.get(count).exports = Integer.parseInt(list_split[2]);
					t.get(count).export_sum = Integer
							.parseInt(list_split[3]);
					t.get(count).imports = Integer.parseInt(list_split[4]);
					t.get(count).import_sum = Integer
							.parseInt(list_split[5]);
					count++;

				} else {
					break;
				}
			}
			ta.append("텍스트 파일에서 로드했습니다.\n");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public void load_to_dat(File f){
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {

			fis = new FileInputStream(f);
			ois = new ObjectInputStream(fis);

			ArrayList t = (ArrayList) ois.readObject();
			this.t = t;
			ta.append("dat파일에서 로드했습니다.\n");
		} catch (Exception ex) {
		} finally {
			try {

				ois.close();
				fis.close();
			} catch (IOException ioe) {
			}
		}
	}
	public void save(){
		File f = null;
		JFileChooser fc = new JFileChooser();
		String save_path = null;
		FileNameExtensionFilter filter = new FileNameExtensionFilter("텍스트문서(*.txt)", "txt"); 
		FileNameExtensionFilter filter2 = new FileNameExtensionFilter("데이터파일(*.dat)", "dat"); 
		fc.setFileFilter(filter); 
		fc.setFileFilter(filter2); 
		
		int answer = fc.showSaveDialog(null);	
		if(answer == JFileChooser.APPROVE_OPTION){
            f = fc.getSelectedFile();
            save_path = f.getAbsolutePath();
		}
		if(f!=null){
			String format = f.toString();
			format = format.substring(format.length()-3, format.length());	
			if(format.equals("txt")){
				save_to_text(save_path);	
			}
			else if(format.equals("dat")){
				save_to_dat(save_path);
			}
		}

	}
	public void save_to_text(String save_path){
		FileOutputStream fos = null;
		OutputStreamWriter osw = null;
		BufferedWriter bw;
		
		try {
			fos = new FileOutputStream(save_path);
			osw = new OutputStreamWriter(fos, "euc-kr");
			bw = new BufferedWriter(osw);
			for (i = 0; i < t.size(); i++) {
				bw.write(t.get(i).date + "/" + t.get(i).nation + "/"
						+ t.get(i).exports + "/" + t.get(i).export_sum
						+ "/" + t.get(i).imports + "/"
						+ t.get(i).import_sum);
				bw.newLine();
			}
			ta.append("텍스트 파일로 저장했습니다.\n");
			bw.close();
			osw.close();
			fos.close();

		} catch (IOException ioe) {
			// TODO Auto-generated catch block
			ioe.printStackTrace();
		}
	}
	public void save_to_dat(String save_path){
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream(save_path);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(t);
			oos.reset();
			ta.append("dat로 저장되었습니다\n");

		} catch (Exception ex) {
		} finally {
			try {
				oos.close();
				fos.close();
			} catch (IOException ioe) {

			}
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {

		if (arg0.getKeyCode() == KeyEvent.VK_ENTER) { // 엔터키가 눌러졌으면
			send_button.doClick(); // send를 클릭

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
		if (e.getSource() == button1) {
			add();
		}

		if (e.getSource() == button2) {
			edit();
		}

		if (e.getSource() == button3) {
			show_all();
		}
		if (e.getSource() == button3_) {
			show_by_nation();
		}

		if (e.getSource() == button4) {
			search();
		}

		if (e.getSource() == button5) {
			line_graph();
		}

		if (e.getSource() == button6) {
			pie_graph();
		}

		if (e.getSource() == send_button) {
			send_to_memo();
		}

		if (e.getSource() == menuItemOpen) {
			load();
		}


		if (e.getSource() == menuItemSave) {
			save();
		}
		
		if (e.getSource() == menuItemExit) {
			System.exit(0);
		}

	}

}

class Button1_Frame extends JDialog implements ActionListener {
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

	public Button1_Frame(MyFrame MyFrame) {
		setTitle("Add Trade");
		setSize(280, 240);
		this.MyFrame = MyFrame;
		label1 = new JLabel("날짜");
		label2 = new JLabel("년");
		label3 = new JLabel("월");
		label4 = new JLabel("상대국가");
		label5 = new JLabel("수출건수");
		label6 = new JLabel("수출금액");
		label7 = new JLabel("수입건수");
		label8 = new JLabel("수입금액");
		label9 = new JLabel("　　　　　　　　　　단위:천불(USD 1,000)");
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
		setLayout(new GridLayout(6, 1));

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
		add(p1, 0);
		add(p2, 1);
		add(p3, 2);
		add(p4, 3);
		add(label9, 4);
		add(p5, 5);
		setVisible(true);
		SEND.addActionListener(this);
		CLOSE.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == SEND) {
			MyFrame.t.add(new Trade());
			MyFrame.t.get(MyFrame.t.size() - 1).date = (tf1.getText() + "." + tf2
					.getText());
			MyFrame.t.get(MyFrame.t.size() - 1).nation = tf3.getText();
			MyFrame.t.get(MyFrame.t.size() - 1).exports = Integer.parseInt(tf4
					.getText());
			MyFrame.t.get(MyFrame.t.size() - 1).export_sum = Integer
					.parseInt(tf5.getText());
			MyFrame.t.get(MyFrame.t.size() - 1).imports = Integer.parseInt(tf6
					.getText());
			MyFrame.t.get(MyFrame.t.size() - 1).import_sum = Integer
					.parseInt(tf7.getText());
			if (MyFrame.t.size() == 1) {
				MyFrame.t.get(MyFrame.t.size() - 1).serial_num = 1;
			} else {
				MyFrame.t.get(MyFrame.t.size() - 1).serial_num = MyFrame.t
						.get(MyFrame.t.size() - 2).serial_num + 1;
			}

			dispose();

		}
		if (e.getSource() == CLOSE) {
			dispose();
		}

	}

}

class Button2_Frame extends JDialog implements ActionListener {
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
	JLabel label_year;
	JLabel label_month;
	JTextField year;
	JTextField month;
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
	JPanel p6;
	JButton Edit;
	JButton Delete;
	JButton CLOSE;
	JButton Search;

	public Button2_Frame(MyFrame MyFrame) {
		setTitle("Edit Trade");
		setSize(450, 250);
		this.MyFrame = MyFrame;
		label1 = new JLabel("삭제");
		label2 = new JLabel("날짜");
		label3 = new JLabel("상대국가");
		label4 = new JLabel("일렬번호");
		label5 = new JLabel("수출건수");
		label6 = new JLabel("수출금액");
		label7 = new JLabel("수입건수");
		label8 = new JLabel("수입금액");
		label9 = new JLabel("　　　　　　　　　　　　　　　　　　　　　　　　단위:천불(USD 1,000)");
		label_year = new JLabel("년");
		label_month = new JLabel("월");
		year = new JTextField(4);
		month = new JTextField(2);
		tf2 = new JTextField(8);
		tf3 = new JTextField(10);
		tf4 = new JTextField(5);
		tf5 = new JTextField(5);
		tf6 = new JTextField(5);
		tf7 = new JTextField(5);
		p1 = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel();
		p4 = new JPanel();
		p5 = new JPanel();
		p6 = new JPanel();
		Edit = new JButton("Edit");
		Delete = new JButton("Delete");
		CLOSE = new JButton("CLOSE");
		Search = new JButton("SEARCH");
		setLayout(new GridLayout(6, 1));
		p1.add(label2);
		p1.add(year);
		p1.add(label_year);
		p1.add(month);
		p1.add(label_month);
		p1.add(label3);
		p1.add(tf2);
		p1.add(Search);
		add(p1, 0);
		p2.add(label4);
		p2.add(tf3);
		p2.add(Delete);
		add(p2, 1);
		p3.add(label5);
		p3.add(tf4);
		p3.add(label6);
		p3.add(tf5);
		p4.add(label7);
		p4.add(tf6);
		p4.add(label8);
		p4.add(tf7);
		add(p3, 2);
		add(p4, 3);
		p5.add(label9);
		add(p5, 4);
		p6.add(Edit);
		p6.add(CLOSE);
		add(p6, 5);
		setVisible(true);
		Edit.addActionListener(this);
		Delete.addActionListener(this);
		CLOSE.addActionListener(this);
		Search.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == Edit) {
			MyFrame.t.get(Integer.parseInt((tf3.getText())) - 1).exports = Integer
					.parseInt(tf4.getText());
			MyFrame.t.get(Integer.parseInt((tf3.getText())) - 1).export_sum = Integer
					.parseInt(tf5.getText());
			MyFrame.t.get(Integer.parseInt((tf3.getText())) - 1).imports = Integer
					.parseInt(tf6.getText());
			MyFrame.t.get(Integer.parseInt((tf3.getText())) - 1).import_sum = Integer
					.parseInt(tf7.getText());
			MyFrame.ta
					.append(MyFrame.t.get(Integer.parseInt((tf3.getText())) - 1).serial_num
							+ "번 자료가 수정되었습니다.");
			dispose();
		}
		if (e.getSource() == Delete) {
			if(tf3.getText().equals("")){
			}
			else if(Integer.parseInt(tf3.getText()) > MyFrame.t.size() || Integer.parseInt(tf3.getText()) <1 ){
			}
			else{
				MyFrame.t.remove(Integer.parseInt((tf3.getText())) - 1);
				MyFrame.ta.append(tf3.getText() + "번 자료가 삭제되었습니다.\n");
				dispose();
			}
		}
		if (e.getSource() == CLOSE) {
			dispose();
		}
		if (e.getSource() == Search) {
			tf3.setText("");
			int find = -1;
			String s_year = year.getText();
			String s_month = month.getText();
			if (s_month.length() < 2) { // 날짜입력 유연성 확보
				s_month = "0" + s_month;
			}
			String date = s_year + "." + s_month;
			for (int i = 0; i < MyFrame.t.size(); i++) {
				if (MyFrame.t.get(i).date.equals(date)
						&& MyFrame.t.get(i).nation.equals(tf2.getText())) {
					tf3.setText(MyFrame.t.get(i).serial_num + "");
					find = 1;
				}
			}
			if (find == -1) {
				tf3.setText("존재하지 않습니다.");
			}
		}
	}
}

class Button4_Frame extends JDialog implements ActionListener {
	MyFrame MyFrame;
	Choice nation_choice;
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
	JTextArea ta;
	JPanel p1;
	JPanel p2;
	JPanel p3;
	JPanel p4;
	JPanel p5;
	JButton Add;
	JButton Search;
	JButton CLOSE;
	int all = -1;
	int all_date = -1;

	HashSet<String> sample = new HashSet<String>();

	public Button4_Frame(MyFrame MyFrame) {
		this.MyFrame = MyFrame;
		setTitle("Find Trade");
		setSize(340, 240);
		this.MyFrame = MyFrame;
		label1 = new JLabel("기간");
		label2 = new JLabel("년");
		label3 = new JLabel("월");
		label4 = new JLabel("~");
		label5 = new JLabel("년");
		label6 = new JLabel("월");
		label7 = new JLabel("국가");
		label8 = new JLabel("검색대상");
		label9 = new JLabel("　　　　　　　　　　　　　(기간 미입력시 전체검색)");
		tf1 = new JTextField(4);
		tf2 = new JTextField(2);
		tf3 = new JTextField(4);
		tf4 = new JTextField(2);
		ta = new JTextArea();
		ta.disable();
		p1 = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel();
		p4 = new JPanel();
		p5 = new JPanel();
		nation_choice = new Choice();
		for (int i = 0; i < this.MyFrame.t.size(); i++) { // 콤보박스 설정
			sample.add(this.MyFrame.t.get(i).nation);
		}
		Iterator<String> iterator = sample.iterator();
		nation_choice.add("전체");
		int i=0;
		MyFrame.set_Nations();
		String [] nation = new String[MyFrame.n.size()]; 
		while (iterator.hasNext()) {
			nation[i] = iterator.next();
			i++;
		}
		Arrays.sort(nation);
		for(i=0;i<nation.length;i++){
			nation_choice.add(nation[i]);
		}
		Add = new JButton("Add");
		Search = new JButton("SEARCH");
		CLOSE = new JButton("CLOSE");

		setLayout(new GridLayout(5, 1));
		
		p1.add(label1);
		p1.add(tf1);
		p1.add(label2);
		p1.add(tf2);
		p1.add(label3);
		p1.add(label4);
		p1.add(tf3);
		p1.add(label5);
		p1.add(tf4);
		p1.add(label6);
		p2.add(label7);
		p2.add(nation_choice);
		p2.add(Add);
		p3.add(Search);
		p3.add(CLOSE);
		p4.setLayout(new BorderLayout());
		p4.add(label8, "West");
		p4.add(ta, "Center");
		p5.add(label9);
		add(p2, 0);
		add(p4, 1);
		add(p1, 2);
		add(p5, 3);
		add(p3, 4);
		Add.addActionListener(this);
		Search.addActionListener(this);
		CLOSE.addActionListener(this);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == Add && all == -1) {
			String choosen = nation_choice.getSelectedItem().toString();
			if (choosen.equals("전체")) { // 전체가 선택되면 비활성화
				all = 1;
			}
			nation_choice.remove(nation_choice.getSelectedIndex());
			ta.append(choosen + "/");
		}

		if (e.getSource() == Search) {
			if (ta.getText().length() == 0) {
				MyFrame.ta.append("국가를 선택하지 않았습니다."); // 국가선택을 안했을 경우
			} else {
				int find = -1; // 찾는 값이 없을경우 알려주기 위한 키
				if (tf1.getText().length() == 0 && tf2.getText().length() == 0
						&& tf3.getText().length() == 0
						&& tf4.getText().length() == 0) {
					all_date = 1;
				}// 입력값이 없으면 전체검색

				else if (tf1.getText().length() == 0
						|| tf2.getText().length() == 0
						|| tf3.getText().length() == 0
						|| tf4.getText().length() == 0) {
					all_date = 0;

				}// 중간에 빈칸이 있으면 검색불가

				if (all_date == 0) {
					MyFrame.ta.append("기간을 올바르게 입력하지 않았습니다.\n");
				}

				else {
					String s1 = tf1.getText();
					String s2 = tf2.getText();
					String s3 = tf3.getText();
					String s4 = tf4.getText();
					if (s1.length() == 0) { // parseInt시 null값 방지
						s1 = "0" + s1;
					}
					if (s2.length() < 2) { // 날짜입력 유연성 확보, parseInt시 null값 방지
						s2 = "0" + s2;
					}
					if (s3.length() == 0) { // parseInt시 null값 방지
						s3 = "0" + s3;
					}
					if (s4.length() < 2) { // 날짜입력 유연성 확보, parseInt시 null값 방지
						s4 = "0" + s4;
					}
					int s_date = Integer.parseInt(s1 + s2);
					int e_date = Integer.parseInt(s3 + s4);
					if (e_date < s_date || Integer.parseInt(s1) > 9999
							|| Integer.parseInt(s3) > 9999
							|| Integer.parseInt(s2) > 12
							|| Integer.parseInt(s4) > 12) {
						MyFrame.ta.append("기간을 올바르게 입력하지 않았습니다.\n");
					} else {
						String[] list = ta.getText().split("/");
						if (all_date == 1 && all == 1) { // 모든날짜, 모든국가일때
							MyFrame.ta.append("\n<<검색결과>>\n");
							for (int i = 0; i < MyFrame.t.size(); i++) {
								MyFrame.ta.append(MyFrame.t.get(i).toString());
								find = 1;
							}
						} else if (all_date == 1 && all != 1) { // 모든날짜, 선택국가일때
							MyFrame.ta.append("\n<<검색결과>>\n");
							for (int j = 0; j < list.length; j++) {
								for (int i = 0; i < MyFrame.t.size(); i++) {
									if (list[j].equals(MyFrame.t.get(i).nation)) {
										MyFrame.ta.append(MyFrame.t.get(i)
												.toString());
										find = 1;
									}
								}
							}
						} else if (all_date == 1 && all != 1) { // 선택날짜, 모든국가일때
							MyFrame.ta.append("\n<<검색결과>>\n");

							for (int i = 0; i < MyFrame.t.size(); i++) {
								if (s_date <= Integer
										.parseInt(MyFrame.t.get(i).date
												.substring(0, 4)
												+ MyFrame.t.get(i).date
														.substring(5))
										&& Integer
												.parseInt(MyFrame.t.get(i).date
														.substring(0, 4)
														+ MyFrame.t.get(i).date
																.substring(5)) <= e_date) {
									MyFrame.ta.append(MyFrame.t.get(i)
											.toString());
									find = 1;
								}
							}
						} else { // 선택국가, 선택날짜일때

							MyFrame.ta.append("\n<<검색결과>>\n");
							for (int i = 0; i < MyFrame.t.size(); i++) {
								for (int j = 0; j < list.length; j++) {
									if (s_date <= Integer.parseInt(MyFrame.t
											.get(i).date.substring(0, 4)
											+ MyFrame.t.get(i).date
													.substring(5))
											&& Integer.parseInt(MyFrame.t
													.get(i).date
													.substring(0, 4)
													+ MyFrame.t.get(i).date
															.substring(5)) <= e_date) {
										if (list[j]
												.equals(MyFrame.t.get(i).nation)) {
											MyFrame.ta.append(MyFrame.t.get(i)
													.toString());
											find = 1;
										}
									}
								}
							}
						}
						if (find != 1) {
							MyFrame.ta.append("조건에 맞는 값이 없습니다.\n");
						}
					}
				}
			}
			dispose();
		}
		if (e.getSource() == CLOSE) {
			dispose();
		}
	}
}


class Button5_Select_Frame extends JDialog implements ActionListener {
	static MyFrame MyFrame;
	JLabel label1;
	Choice nation_choice;
	HashSet<String> sample = new HashSet<String>();
	JButton Search;
	JButton CLOSE;
	JPanel p1;
	JPanel p2;
	 
	public Button5_Select_Frame(MyFrame MyFrame) {
		this.MyFrame = MyFrame;
		setTitle("Select Nation");
		setSize(300,140);
		label1 = new JLabel("대상국가");
		nation_choice = new Choice();
		Search = new JButton("Search");
		CLOSE = new JButton("CLOSE");
		p1 = new JPanel();
		p2 = new JPanel();
		
		for(int i=0;i<this.MyFrame.t.size();i++){ //콤보박스 설정
			sample.add(this.MyFrame.t.get(i).nation); 
		} 
		Iterator<String> iterator = sample.iterator();
		nation_choice.add("전체");
		int i=0;
		MyFrame.set_Nations();
		String [] nation = new String[MyFrame.n.size()]; 
		while (iterator.hasNext()) {
			nation[i] = iterator.next();
			i++;
		}
		Arrays.sort(nation);
		for(i=0;i<nation.length;i++){
			nation_choice.add(nation[i]);
		}
		Search.addActionListener(this);
		CLOSE.addActionListener(this);
		setLayout(new GridLayout(2,1));
		
		p1.add(label1);
		p1.add(nation_choice);
		p2.add(Search);
		p2.add(CLOSE);	
		add(p1,0);
		add(p2,1);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == Search){
			if(nation_choice.getSelectedItem().equals("전체")){
				Button5_Frame b5 = new Button5_Frame(MyFrame);
				b5.pack();
				RefineryUtilities.centerFrameOnScreen(b5);
				b5.setVisible(true);

			}
			else{
				String nation = nation_choice.getSelectedItem();
				Button5_Frame b5 = new Button5_Frame(MyFrame,nation);
				b5.pack();
				RefineryUtilities.centerFrameOnScreen(b5);
				b5.setVisible(true);
			}
			dispose();
			
		}
		if(e.getSource() == CLOSE){
			dispose();
		}

	}

}


class Button5_Frame extends JDialog {
	MyFrame MyFrame;
	String nations;

	public Button5_Frame(MyFrame MyFrame) {
		this.MyFrame = MyFrame;
		nations = null;
		setTitle("Total Trade Balance");
		CategoryDataset dataset = createDataset();
		JFreeChart chart = createChart(dataset);
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(700, 470));
		setContentPane(chartPanel);
	}
	public Button5_Frame(MyFrame MyFrame, String nations) {
		this.MyFrame = MyFrame;
		this.nations = nations;
		setTitle("Total Trade Balance");
		CategoryDataset dataset = createDataset();
		JFreeChart chart = createChart(dataset);
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(700, 470));
		setContentPane(chartPanel);
	}
	

	private CategoryDataset createDataset() {

		// row keys
		final String series1 = "Total Trade Balance";

		// column keys
		final String type1 = "2000";
		final String type2 = "2001";
		final String type3 = "2002";
		final String type4 = "2003";
		final String type5 = "2004";
		final String type6 = "2005";
		final String type7 = "2006";
		final String type8 = "2007";
		final String type9 = "2008";
		final String type10 = "2009";
		final String type11 = "2010";
		final String type12 = "2011";
		final String type13 = "2012";
		final String type14 = "2013";
		final String type15 = "2014";
		final String type16 = "2015";
		// create the dataset
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		
		int import_sum_year [] = new int [16];
		int export_sum_year [] = new int [16];
		int trade_balance_year [] = new int [16];
		for(int i=0;i<16;i++){ //초기화
			import_sum_year [i] = 0;
			export_sum_year [i] = 0;
		}

		for(int i=0;i<MyFrame.t.size();i++){
			if(MyFrame.t.get(i).nation.equals(nations)){
				if(Integer.parseInt(MyFrame.t.get(i).date.substring(0,4)) == 2000){		
					export_sum_year [0] += MyFrame.t.get(i).export_sum;
					import_sum_year [0] += MyFrame.t.get(i).import_sum;	
				}
				else if(Integer.parseInt(MyFrame.t.get(i).date.substring(0,4)) == 2001){
					export_sum_year [1] += MyFrame.t.get(i).export_sum;
					import_sum_year [1] += MyFrame.t.get(i).import_sum;
				}
				else if(Integer.parseInt(MyFrame.t.get(i).date.substring(0,4)) == 2002){
					export_sum_year [2] += MyFrame.t.get(i).export_sum;
					import_sum_year [2] += MyFrame.t.get(i).import_sum;
				}
				else if(Integer.parseInt(MyFrame.t.get(i).date.substring(0,4)) == 2003){
					export_sum_year [3] += MyFrame.t.get(i).export_sum;
					import_sum_year [3] += MyFrame.t.get(i).import_sum;
				}
				else if(Integer.parseInt(MyFrame.t.get(i).date.substring(0,4)) == 2004){
					export_sum_year [4] += MyFrame.t.get(i).export_sum;
					import_sum_year [4] += MyFrame.t.get(i).import_sum;
				}
				else if(Integer.parseInt(MyFrame.t.get(i).date.substring(0,4)) == 2005){
					export_sum_year [5] += MyFrame.t.get(i).export_sum;
					import_sum_year [5] += MyFrame.t.get(i).import_sum;
				}
				else if(Integer.parseInt(MyFrame.t.get(i).date.substring(0,4)) == 2006){
					export_sum_year [6] += MyFrame.t.get(i).export_sum;
					import_sum_year [6] += MyFrame.t.get(i).import_sum;
				}
				else if(Integer.parseInt(MyFrame.t.get(i).date.substring(0,4)) == 2007){
					export_sum_year [7] += MyFrame.t.get(i).export_sum;
					import_sum_year [7] += MyFrame.t.get(i).import_sum;
				}
				else if(Integer.parseInt(MyFrame.t.get(i).date.substring(0,4)) == 2008){
					export_sum_year [8] += MyFrame.t.get(i).export_sum;
					import_sum_year [8] += MyFrame.t.get(i).import_sum;
				}
				else if(Integer.parseInt(MyFrame.t.get(i).date.substring(0,4)) == 2009){
					export_sum_year [9] += MyFrame.t.get(i).export_sum;
					import_sum_year [9] += MyFrame.t.get(i).import_sum;
				}
				else if(Integer.parseInt(MyFrame.t.get(i).date.substring(0,4)) == 2010){
					export_sum_year [10] += MyFrame.t.get(i).export_sum;
					import_sum_year [10] += MyFrame.t.get(i).import_sum;
				}
				else if(Integer.parseInt(MyFrame.t.get(i).date.substring(0,4)) == 2011){
					export_sum_year [11] += MyFrame.t.get(i).export_sum;
					import_sum_year [11] += MyFrame.t.get(i).import_sum;
				}
				else if(Integer.parseInt(MyFrame.t.get(i).date.substring(0,4)) == 2012){
					export_sum_year [12] += MyFrame.t.get(i).export_sum;
					import_sum_year [12] += MyFrame.t.get(i).import_sum;
				}
				else if(Integer.parseInt(MyFrame.t.get(i).date.substring(0,4)) == 2013){
					export_sum_year [13] += MyFrame.t.get(i).export_sum;
					import_sum_year [13] += MyFrame.t.get(i).import_sum;
				}
				else if(Integer.parseInt(MyFrame.t.get(i).date.substring(0,4)) == 2014){
					export_sum_year [14] += MyFrame.t.get(i).export_sum;
					import_sum_year [14] += MyFrame.t.get(i).import_sum;
				}
				else if(Integer.parseInt(MyFrame.t.get(i).date.substring(0,4)) == 2015){
					export_sum_year [15] += MyFrame.t.get(i).export_sum;
					import_sum_year [15] += MyFrame.t.get(i).import_sum;
				}
			}
			else if(nations == null || nations.equals("all")){
				nations = "all";
				if(Integer.parseInt(MyFrame.t.get(i).date.substring(0,4)) == 2000){		
					export_sum_year [0] += MyFrame.t.get(i).export_sum;
					import_sum_year [0] += MyFrame.t.get(i).import_sum;	
				}
				else if(Integer.parseInt(MyFrame.t.get(i).date.substring(0,4)) == 2001){
					export_sum_year [1] += MyFrame.t.get(i).export_sum;
					import_sum_year [1] += MyFrame.t.get(i).import_sum;
				}
				else if(Integer.parseInt(MyFrame.t.get(i).date.substring(0,4)) == 2002){
					export_sum_year [2] += MyFrame.t.get(i).export_sum;
					import_sum_year [2] += MyFrame.t.get(i).import_sum;
				}
				else if(Integer.parseInt(MyFrame.t.get(i).date.substring(0,4)) == 2003){
					export_sum_year [3] += MyFrame.t.get(i).export_sum;
					import_sum_year [3] += MyFrame.t.get(i).import_sum;
				}
				else if(Integer.parseInt(MyFrame.t.get(i).date.substring(0,4)) == 2004){
					export_sum_year [4] += MyFrame.t.get(i).export_sum;
					import_sum_year [4] += MyFrame.t.get(i).import_sum;
				}
				else if(Integer.parseInt(MyFrame.t.get(i).date.substring(0,4)) == 2005){
					export_sum_year [5] += MyFrame.t.get(i).export_sum;
					import_sum_year [5] += MyFrame.t.get(i).import_sum;
				}
				else if(Integer.parseInt(MyFrame.t.get(i).date.substring(0,4)) == 2006){
					export_sum_year [6] += MyFrame.t.get(i).export_sum;
					import_sum_year [6] += MyFrame.t.get(i).import_sum;
				}
				else if(Integer.parseInt(MyFrame.t.get(i).date.substring(0,4)) == 2007){
					export_sum_year [7] += MyFrame.t.get(i).export_sum;
					import_sum_year [7] += MyFrame.t.get(i).import_sum;
				}
				else if(Integer.parseInt(MyFrame.t.get(i).date.substring(0,4)) == 2008){
					export_sum_year [8] += MyFrame.t.get(i).export_sum;
					import_sum_year [8] += MyFrame.t.get(i).import_sum;
				}
				else if(Integer.parseInt(MyFrame.t.get(i).date.substring(0,4)) == 2009){
					export_sum_year [9] += MyFrame.t.get(i).export_sum;
					import_sum_year [9] += MyFrame.t.get(i).import_sum;
				}
				else if(Integer.parseInt(MyFrame.t.get(i).date.substring(0,4)) == 2010){
					export_sum_year [10] += MyFrame.t.get(i).export_sum;
					import_sum_year [10] += MyFrame.t.get(i).import_sum;
				}
				else if(Integer.parseInt(MyFrame.t.get(i).date.substring(0,4)) == 2011){
					export_sum_year [11] += MyFrame.t.get(i).export_sum;
					import_sum_year [11] += MyFrame.t.get(i).import_sum;
				}
				else if(Integer.parseInt(MyFrame.t.get(i).date.substring(0,4)) == 2012){
					export_sum_year [12] += MyFrame.t.get(i).export_sum;
					import_sum_year [12] += MyFrame.t.get(i).import_sum;
				}
				else if(Integer.parseInt(MyFrame.t.get(i).date.substring(0,4)) == 2013){
					export_sum_year [13] += MyFrame.t.get(i).export_sum;
					import_sum_year [13] += MyFrame.t.get(i).import_sum;
				}
				else if(Integer.parseInt(MyFrame.t.get(i).date.substring(0,4)) == 2014){
					export_sum_year [14] += MyFrame.t.get(i).export_sum;
					import_sum_year [14] += MyFrame.t.get(i).import_sum;
				}
				else if(Integer.parseInt(MyFrame.t.get(i).date.substring(0,4)) == 2015){
					export_sum_year [15] += MyFrame.t.get(i).export_sum;
					import_sum_year [15] += MyFrame.t.get(i).import_sum;
				}
			}		
		}

		for(int i=0;i<16;i++){
			trade_balance_year[i] = export_sum_year [i] - import_sum_year [i];
		}
		

		dataset.addValue(trade_balance_year[0], series1, type1);
		dataset.addValue(trade_balance_year[1], series1, type2);
		dataset.addValue(trade_balance_year[2], series1, type3);
		dataset.addValue(trade_balance_year[3], series1, type4);
		dataset.addValue(trade_balance_year[4], series1, type5);
		dataset.addValue(trade_balance_year[5], series1, type6);
		dataset.addValue(trade_balance_year[6], series1, type7);
		dataset.addValue(trade_balance_year[7], series1, type8);
		dataset.addValue(trade_balance_year[8], series1, type9);
		dataset.addValue(trade_balance_year[9], series1, type10);
		dataset.addValue(trade_balance_year[10], series1, type11);
		dataset.addValue(trade_balance_year[11], series1, type12);
		dataset.addValue(trade_balance_year[12], series1, type13);
		dataset.addValue(trade_balance_year[13], series1, type14);
		dataset.addValue(trade_balance_year[14], series1, type15);
		dataset.addValue(trade_balance_year[15], series1, type16);

		return dataset;

	}

	private JFreeChart createChart(final CategoryDataset dataset) {

		// create the chart...
		final JFreeChart chart = ChartFactory.createLineChart(
				"Trade Balance", // title
				"Year", // domain axis label
				"Value", // range axis label
				dataset, // data
				PlotOrientation.VERTICAL, // orientation
				false, // include legend
				true, // tooltips
				false // urls
				);

		// NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
		// final StandardLegend legend = (StandardLegend) chart.getLegend();
		// legend.setDisplaySeriesShapes(true);
		// legend.setShapeScaleX(1.5);
		// legend.setShapeScaleY(1.5);
		// legend.setDisplaySeriesLines(true);

		chart.setBackgroundPaint(Color.white);

		final CategoryPlot plot = (CategoryPlot) chart.getPlot();
		plot.setBackgroundPaint(Color.lightGray);
		plot.setRangeGridlinePaint(Color.white);

		// customise the range axis
		final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		rangeAxis.setAutoRangeIncludesZero(true);

		final LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot
				.getRenderer();
		// renderer.setDrawShapes(true);

		renderer.setSeriesStroke(0, new BasicStroke(2.0f,
				BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1.0f,
				new float[] { 10.0f, 6.0f }, 0.0f));
		renderer.setSeriesStroke(1, new BasicStroke(2.0f,
				BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1.0f,
				new float[] { 6.0f, 6.0f }, 0.0f));
		renderer.setSeriesStroke(2, new BasicStroke(2.0f,
				BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1.0f,
				new float[] { 2.0f, 6.0f }, 0.0f));
		// OPTIONAL CUSTOMISATION COMPLETED.

		return chart;
	}

}

class Button6_Select_Frame extends JDialog implements ActionListener {
	MyFrame MyFrame;
	JLabel label1;
	JLabel label2;
	JLabel label3;
	Choice legend_choice;
	Choice nation_choice;
	JTextField n_tf;
	JTextArea ta;
	JButton Add;
	JButton Search;
	JButton CLOSE;
	JPanel p1;
	JPanel p2;
	JPanel p3;
	JPanel p4;
	int all = -1;
	HashSet<String> sample = new HashSet<String>();
	public Button6_Select_Frame(MyFrame MyFrame) {
		this.MyFrame = MyFrame;
		setTitle("Select Legend");
		setSize(370,220);
		label1 = new JLabel("범례설정");
		label2 = new JLabel("국가선택");
		label3 = new JLabel("검색대상");
		legend_choice = new Choice();
		Add = new JButton("Add");
		Search = new JButton("Search");
		CLOSE = new JButton("CLOSE");
		p1 = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel();
		p4 = new JPanel();
		legend_choice.add("수출량");
		legend_choice.add("수출금액");
		legend_choice.add("수입량");
		legend_choice.add("수입금액");
		ta = new JTextArea();
		ta.disable();
		nation_choice = new Choice();
		for (int i = 0; i < this.MyFrame.t.size(); i++) { // 콤보박스 설정
			sample.add(this.MyFrame.t.get(i).nation);
		}
		Iterator<String> iterator = sample.iterator();
		nation_choice.add("전체");
		int i=0;
		MyFrame.set_Nations();
		String [] nation = new String[MyFrame.n.size()]; 
		while (iterator.hasNext()) {
			nation[i] = iterator.next();
			i++;
		}
		Arrays.sort(nation);
		for(i=0;i<nation.length;i++){
			nation_choice.add(nation[i]);
		}
		Add.addActionListener(this);
		Search.addActionListener(this);
		CLOSE.addActionListener(this);
		setLayout(new GridLayout(4,1));
		
		p2.add(label1);
		p2.add(legend_choice);
		p1.add(label2);
		p1.add(nation_choice);
		p1.add(Add);
		p3.setLayout(new BorderLayout());
		p3.add(label3,"West");
		p3.add(ta,"Center");
		p4.add(Search);
		p4.add(CLOSE);	
		add(p1,0);
		add(p3,1);
		add(p2,2);
		add(p4,3);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == Add){
			if (e.getSource() == Add && all == -1) {
				String choosen = nation_choice.getSelectedItem().toString();
				if (choosen.equals("전체")) { // 전체가 선택되면 비활성화
					all = 1;
				}
				nation_choice.remove(nation_choice.getSelectedIndex());
				ta.append(choosen + "/");
			}
		}
		if(e.getSource() == Search){
			if (ta.getText().length() == 0) {
				MyFrame.ta.append("국가를 선택하지 않았습니다."); // 국가선택을 안했을 경우
			}
			else{
				String key = null;
				if(legend_choice.getSelectedItem().equals("수출량")){
					key="수출량";
				}
				else if(legend_choice.getSelectedItem().equals("수출금액")){
					key="수출금액";
				}
				else if(legend_choice.getSelectedItem().equals("수입량")){
					key="수입량";
				}
				else if(legend_choice.getSelectedItem().equals("수입금액")){
					key="수입금액";
				}
				
				Button6_Frame b6 = new Button6_Frame(MyFrame,key,ta.getText());
				b6.nation_sample = ta.getText().split("/");
		        b6.pack();
		        RefineryUtilities.centerFrameOnScreen(b6);
		        b6.setVisible(true);
			}
			dispose();		
		}

		if(e.getSource() == CLOSE){
			dispose();
		}
	}
}

class Button6_Frame extends JDialog  {
	static MyFrame MyFrame;
	static String key;
	static String [] nation_sample;
	
	public Button6_Frame(MyFrame MyFrame,String key, String before_split){
		setTitle("Pie Chart");
		this.MyFrame = MyFrame;
		this.key = key;
		nation_sample = before_split.split("/");
		setContentPane(createDemoPanel());
	}


    private static PieDataset createDataset() {
    	int all=-1;
        DefaultPieDataset dataset = new DefaultPieDataset();
        MyFrame.set_Nations();
        for(int j=0;j<nation_sample.length;j++){
        	if(nation_sample[j].equals("전체")){
        		all=1;
        	}
        }


        if(key.equals("수출량")){
        	if(all==1){
        		for(int i=0;i<MyFrame.n.size();i++){
        			dataset.setValue(MyFrame.n.get(i).name, MyFrame.n.get(i).total_exports);
        		}	
        		
        	}
        	else{
        		for(int i=0;i<MyFrame.n.size();i++){
            		for(int j=0;j<nation_sample.length;j++){
            			if(nation_sample[j].equals(MyFrame.n.get(i).name)){
            				dataset.setValue(MyFrame.n.get(i).name, MyFrame.n.get(i).total_exports);
            			}
            		}
            	} 	
        	}
        	
        		
        }
        else if(key.equals("수출금액")){
        	if(all==1){
        		for(int i=0;i<MyFrame.n.size();i++){
        			dataset.setValue(MyFrame.n.get(i).name, MyFrame.n.get(i).total_export_sum);
        		}	

        	}
        	else{
        		for(int i=0;i<MyFrame.n.size();i++){
            		for(int j=0;j<nation_sample.length;j++){
            			if(nation_sample[j].equals(MyFrame.n.get(i).name)){
            				dataset.setValue(MyFrame.n.get(i).name, MyFrame.n.get(i).total_export_sum);
            			}
            		}
            	}
        	}
        	
        	
        }
        else if(key.equals("수입량")){
        	if(all==1){
        		for(int i=0;i<MyFrame.n.size();i++){
        			dataset.setValue(MyFrame.n.get(i).name, MyFrame.n.get(i).total_imports);
        		}	
        		
        	}
        	else{
            	for(int i=0;i<MyFrame.n.size();i++){
            		for(int j=0;j<nation_sample.length;j++){
            			if(nation_sample[j].equals(MyFrame.n.get(i).name)){
            				dataset.setValue(MyFrame.n.get(i).name, MyFrame.n.get(i).total_imports);
            			}
            		}
            		
            	}
        	}

        	
        }
        else if(key.equals("수입금액")){
        	if(all==1){
        		for(int i=0;i<MyFrame.n.size();i++){
        			dataset.setValue(MyFrame.n.get(i).name, MyFrame.n.get(i).total_import_sum);
        		}	
        	}
        	else{
        		for(int i=0;i<MyFrame.n.size();i++){
            		for(int j=0;j<nation_sample.length;j++){
            			if(nation_sample[j].equals(MyFrame.n.get(i).name)){
            				dataset.setValue(MyFrame.n.get(i).name, MyFrame.n.get(i).total_import_sum);
            			}
            		}
            	
            		
            	}
        	}
        	
        	
        }

        return dataset;   
 }
    

    private static JFreeChart createChart(PieDataset dataset) {
        
        JFreeChart chart = ChartFactory.createPieChart(
            "",  				// chart title
            dataset,             // data
            false,               // include legend
            true,
            false
        );

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
        plot.setNoDataMessage("No data available");
        plot.setCircular(false);
        plot.setLabelGap(0.02);
        return chart;
        
    }
    
    public static JPanel createDemoPanel() {
        JFreeChart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }
}


