
public class Trade {
	String date;
	String nation;
	int exports; //수출건수
	int export_sum; //수출금액
	int imports; //수입건수
	int import_sum; //수입금액  단위:천불(USD 1,000)
	int tradebalance=export_sum - import_sum; //무역수지
}
