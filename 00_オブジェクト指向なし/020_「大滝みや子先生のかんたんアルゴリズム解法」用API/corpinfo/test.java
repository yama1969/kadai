class test{
	public static void main(String[] args){
		String[] record = null;
		while((record = CorpInfo.readRecord()) != null){
			for(int i = 0; i < record.length; i++){
				System.out.print(record[i] + "\t");
			}
			System.out.println();
		}
		
		System.out.println("-------------------------------------------------");
		
		CorpInfo.reset();
		while((record = CorpInfo.readRecord()) != null){
			for(int i = 0; i < record.length; i++){
				System.out.print(record[i] + "\t");
			}
			System.out.println();
		}
	}
}
