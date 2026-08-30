package teldatabase;

import java.util.ArrayList;

public class ExecSQL {
	
	private TableFile tables;
	
	public ExecSQL(){
		tables = new TableFile();
	}
	
	public ArrayList execSQL(String sql){
		ArrayList result = null;
		String[] work = null;
		
		if(sql == null || sql.equals("")){
			throw new SqlException("空文です。");
		}
		
		sql = sql.trim();
		work = sql.split(" ",2);
		work[0] = work[0].toLowerCase();
		if(work.length < 2){
			throw new SqlException("2語以上必要です。");
		}
		work[1] = work[1].trim();
		
		if(work[0].equals("select")){
			result = excecSelect(work[1]);
		}else if(work[0].equals("insert")){
			result = excecInsert(work[1]);
		}else if(work[0].equals("delete")){
			result = excecDelete(work[1]);
		}else{
			throw new SqlException("サポートされるSQLはSELECT、INSERT、DELETEのみです。");
		}
		
		return result;
	}
	
	private ArrayList excecSelect(String sql){
		ArrayList result = null;
		String[] work = null;
		ArrayList columns = new ArrayList();
		String table_name = null;
		ArrayList filter = null;
		
		String str = null;
		int pos;
		
		//カラムの読取り
		sql = sql.trim();
		pos = sql.toLowerCase().indexOf("from");
		if(pos == -1){
			throw new SqlException("FROM句がありません。");
		}
		str = sql.substring(0, pos).trim();
		if(str.equals("")){
			throw new SqlException("カラム名がありません。");
		}
		sql = sql.substring(pos);
		while(true){
			work = str.split(",",2);
			work[0] = work[0].trim();
			columns.add(work[0]);
			if(work.length < 2){
				break;
			}else if(work[0].equals("*")){
				throw new SqlException("*は単独で使用してください。");
			}
			str = work[1].trim();
		}
		
		//テーブル名の読取りと条件の抽出
		sql = sql.trim();
		work = sql.split(" ",2);
		if( ! work[0].toLowerCase().equals("from") ){
			throw new SqlException("FROM句がありません。");
		}
		if(work.length < 2){
			throw new SqlException("TABLE名がありません。");
		}
		sql = work[1].trim();
		work = sql.split(" ",2);
		table_name = work[0].trim();
		if(work.length < 2){
			//WHERE句なし
			filter = null;
		}else{
			//WHERE句の読取り
			sql = work[1].trim();
			filter = getFilter(sql);
		}
		result = tables.select(columns, table_name, filter);
		result.add("SELECT OK.");
		return result;
	}
	
	private ArrayList excecInsert(String sql){
		ArrayList result = new ArrayList();
		String[] work = null;
		String table_name = null;
		ArrayList data = new ArrayList();
		
		int pos;
		
		work = sql.split(" ",2);
		if(!work[0].trim().toLowerCase().equals("into")){
			throw new SqlException("INTOがありません。");
		}
		if(work.length < 2){
			throw new SqlException("TABLE名がありません。");
		}
		sql = work[1].trim();
		work = sql.split(" ",2);
		table_name = work[0].trim();
		if(work.length < 2){
			throw new SqlException("VALUES句がありません。");
		}
		sql = work[1].trim();
		if(sql.toLowerCase().indexOf("values") != 0){
			throw new SqlException("VALUESが不正スペルです。");
		}
		sql = sql.substring(6).trim();
		if(sql.indexOf('(') != 0){
			throw new SqlException("VALUES句の'('がありません。");
		}
		sql = sql.substring(1).trim();
		if( (pos = sql.indexOf(')')) == -1){
			throw new SqlException("VALUES句の'('が閉じられていません。");
		}
		if( sql.length() > pos + 1){
			throw new SqlException("VALUES句に余計な文字列がついています。");
		}
		sql = sql.substring(0,pos).trim();
		
		while(true){
			work = sql.split(",",2);
			work[0] = checkStringData(work[0]);
			data.add((String)work[0]);
			if(work.length < 2){
				break;
			}
			sql = work[1].trim();
		}
		
		if(tables.insert(table_name, data)){
			result.add("INSERT OK.");
		}else{
			result.add("INSERT FAIL.");
		}

		return result;
	}
	
	private ArrayList excecDelete(String sql){
		ArrayList result = new ArrayList();
		String[] work = null;
		String table_name = null;
		ArrayList filter = null;
		
		work = sql.split(" ",2);
		if(!work[0].trim().toLowerCase().equals("from")){
			throw new SqlException("FROMがありません。");
		}
		if(work.length < 2){
			throw new SqlException("TABLE名がありません。");
		}
		sql = work[1].trim();
		work = sql.split(" ",2);
		table_name = work[0].trim();
		if(work.length < 2){
			//WHERE句なし
			filter = null;
		}else{
			//WHERE句あり
			sql = work[1].trim();
			filter = getFilter(sql);
		}
		
		if(tables.delete(table_name, filter)){
			result.add("DELETE OK.");
		}else{
			result.add("DELETE FAIL.");
		}
		
		return result;
	}
	
	//WHERE句の解析をします。
	private ArrayList getFilter(String sql){
		ArrayList filter = new ArrayList();
		String[] work = null;
		
		if(sql.toLowerCase().indexOf("where") != 0){
			throw new SqlException("WHEREが不正スペルです。");
		}
		sql = sql.substring(5).trim();
		if(sql.length() < 1){
			throw new SqlException("条件式がありません。");
		}
		work = sql.split("=",2);
		if(work.length != 2){
			throw new SqlException("条件式が不正です。");
		}
		work[0] = work[0].trim();
		work[1] = checkStringData(work[1]);
		filter.add(work[0]);
		filter.add(work[1]);
		return filter;
	}
	
	//文字列データから'を取り除きます。
	private String checkStringData(String dat){
		dat = dat.trim();
		if( dat.indexOf((int)'\'') != 0 || dat.indexOf((int)'\'',1) != dat.length() - 1 ){
			throw new SqlException("データが'で括られていません。");
		}
		dat = dat.substring(1, dat.length() - 1);
		return dat;
	}
}
