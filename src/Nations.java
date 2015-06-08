
public class Nations {
	String name;
	int total_exports;
	int total_export_sum;
	int total_imports;
	int total_import_sum;
	public Nations(){
		total_exports=0;
		total_export_sum=0;
		total_imports=0;
		total_import_sum=0;
		
	}
	public String toString(){
		return "( "+ name+" )　총 수출량:"+ total_exports+"　총 수출액:"+total_export_sum+"　총 수입량:"+total_imports+"　총 수입액"+total_import_sum+"　종합 무역수지:"+(total_export_sum-total_import_sum)+"\n";
	}
	public Nations(String nation){
		name = nation;
		total_exports=0;
		total_export_sum=0;
		total_imports=0;
		total_import_sum=0;
	}

}
