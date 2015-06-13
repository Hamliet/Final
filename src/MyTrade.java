import java.io.Serializable;


public class MyTrade extends Trade{
	public String toString(){
		return ("< "+serial_num+" >　("+nation+")　"+date.substring(0,4)+"년"+date.substring(5)+"월　\n수출건수:"+exports+"　수출금액:"+export_sum+"　\n수입건수:"+imports+"　수입금액:"+import_sum+"　\n무역수지:"+(export_sum - import_sum)+"\n\n");
	}
}
