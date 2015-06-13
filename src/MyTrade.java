import java.io.Serializable;


public class MyTrade implements Serializable{
	String date;
	String nation;
	int serial_num;
	int exports; //수출건수
	int export_sum; //수출금액
	int imports; //수입건수
	int import_sum; //수입금액  단위:천불(USD 1,000)
	public String toString(){
		return ("< "+serial_num+" >　("+nation+")　"+date.substring(0,4)+"년"+date.substring(5)+"월　\n수출건수:"+exports+"　수출금액:"+export_sum+"　\n수입건수:"+imports+"　수입금액:"+import_sum+"　\n무역수지:"+(export_sum - import_sum)+"\n\n");
	}
	public MyTrade(){
		date = null;
		nation = null;
		serial_num=0;
		exports=0; 
		export_sum=0; 
		imports=0; 
		import_sum=0; 
	}	
}
