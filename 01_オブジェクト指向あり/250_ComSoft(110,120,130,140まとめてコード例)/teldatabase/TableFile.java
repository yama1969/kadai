package teldatabase;

import java.util.ArrayList;
import java.util.Iterator;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;

public class TableFile {
	public ArrayList select(ArrayList columns, String tableName, ArrayList filter){
		ArrayList result = new ArrayList();
		ArrayList[] data = null;
		
		int cnt;
		
		data = getDataArrayList(tableName);
		
		if(filter != null){
			int[] int_no = getMachDataNo(data, filter);
			for(cnt = 0; cnt < int_no.length; cnt++){
				result.add(getSelectResult(data, columns, int_no[cnt]));
			}
		}else{
			for(cnt = 1; cnt < data[0].size(); cnt++){
				result.add(getSelectResult(data, columns, cnt));
			}
		}
		return result;
	}
	
	public boolean delete(String tableName, ArrayList filter){
		ArrayList[] data = null;
		
		int cnt,cnt2;
		
		data = getDataArrayList(tableName);
		
		if(filter != null){
			int[] int_no = getMachDataNo(data, filter);
			for(cnt = 0; cnt < int_no.length; cnt++){
				for(cnt2 = 0; cnt2 < data.length; cnt2++){
					data[cnt2].remove(int_no[cnt] - cnt2);
				}
			}
		}else{
			for(cnt = data[0].size() - 1; cnt > 0; cnt--){
				for(cnt2 = 0; cnt2 < data.length; cnt2++){
					data[cnt2].remove(cnt);
				}
			}
		}
		
		writeDataFile(data, tableName);		
		return true;
	}

	public boolean insert(String tableName, ArrayList insert_data){
		ArrayList[] data = null;
		
		int cnt;
		
		data = getDataArrayList(tableName);
		for(cnt = 0; cnt < data.length; cnt++){
			try{
				data[cnt].add((String)insert_data.get(cnt));
			}catch(IndexOutOfBoundsException ioe){
				throw new DataException("データ数が不足です。");
			}
		}
		writeDataFile(data, tableName);		
		return true;
	}
	
	//指定番号に対するSELECT結果を1行返します。
	private String getSelectResult(ArrayList[] data, ArrayList columns, int no){
		int cnt;
		String strResult;
		String strDispCol;
		
		strResult = "";
		Iterator col_i = columns.iterator();
		while(col_i.hasNext()){
			strDispCol = (String)col_i.next();
			if(strDispCol.equals("*")){
				for(cnt = 0; cnt < data.length; cnt++){
					strResult = strResult + (String)(data[cnt].get(no)) + "\t";
				}
				break;
			}
			for(cnt = 0; cnt < data.length; cnt++){
				if( strDispCol.equals((String)data[cnt].get(0)) ){
					strResult = strResult + (String)(data[cnt].get(no)) + "\t";
				}
			}
		}
		return strResult;
	}
	
	//指定条件にマッチする番号を配列で返します。
	private int[] getMachDataNo(ArrayList[] data, ArrayList filter){
		ArrayList result = new ArrayList();
		int cnt,cnt2;
		
		if(data == null || filter == null){
			throw new DataException("データ配列がNULL、または条件配列がNULLです。これはプログラムミスです。");
		}
		
		Iterator filter_i = filter.iterator();
		while(filter_i.hasNext()){
			String degi_col = (String)filter_i.next();
			String degi_dat = (String)filter_i.next();
			
			for(cnt = 0; cnt < data.length; cnt++){
				if(degi_col.equals((String)data[cnt].get(0))){
					break;
				}
			}
			if(cnt != data.length){
				for(cnt2 = 1; cnt2 < data[cnt].size(); cnt2++){
					if( degi_dat.equals(data[cnt].get(cnt2)) ){
						result.add(new Integer(cnt2));
					}
				}
			}
		}
		
		int[] int_result = new int[result.size()];
		for(cnt = 0; cnt < int_result.length; cnt++){
			int_result[cnt] = ((Integer)result.get(cnt)).intValue();
		}
		return int_result;
	}
	
	//データをArrayListの配列に読み込みます。配列はカラム数だけ作成されます。
	private ArrayList[] getDataArrayList(String tableName){
		ArrayList[] data = null;
		BufferedReader fileReader = null;
		
		int cnt;
		String str;
		String[] work = null;
		
		String fileName = tableName+".tbl";
		File file = new File(fileName);
		try{
			fileReader = new BufferedReader(new FileReader(file));
		}catch(FileNotFoundException e){
			throw new DataException("該当するテーブルがありません。");
		}
		
		try{
			if( (str = fileReader.readLine()) != null ){
				work = str.split(",");
				data = new ArrayList[work.length];
				for(cnt = 0; cnt < work.length; cnt++ ){
					data[cnt] = new ArrayList();
					data[cnt].add(work[cnt]);
				}
			}
			while((str = fileReader.readLine()) != null ){
				work = str.split(",");
				for(cnt = 0; cnt < work.length; cnt++ ){
					data[cnt].add(work[cnt]);
				}
			}
			fileReader.close();
		}catch(IOException ie){
			throw new DataException("ファイル読み込みに失敗しました。");
		}catch(IndexOutOfBoundsException iobe){
			throw new DataException("ファイルの書式が不正です。");
		}
		
		return data;
	}
	
	//データをファイルに保存します。
	private void writeDataFile(ArrayList[] data, String tableName){
		BufferedWriter fileWriter;
		
		int cnt,cnt2;
		
		String fileName = tableName+".tbl";
		File file = new File(fileName);
		try{
			fileWriter = new BufferedWriter(new FileWriter(file));
			for(cnt = 0; cnt < data[0].size(); cnt++){
				for(cnt2 = 0; cnt2 < data.length; cnt2++){
					fileWriter.write((String)data[cnt2].get(cnt));
					if(cnt2 != data.length - 1 ){
						fileWriter.write(",");
					}
				}
				fileWriter.newLine();
			}
			fileWriter.close();
		}catch(IOException e){
			throw new DataException("ファイル出力時に例外が発生しました。");
		}
	}
}
