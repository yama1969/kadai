public class Kadai1130{
    public static void main(String[] args){
        
        StringListEx sl = new StringListEx();
        
        String prompt = "[e:終了, i:挿入, g:取出, d:削除, c:値数, l:全値, a:全消]";
        char cmd = 0;
        while((cmd = Keyboard.readChar(prompt)) != 'e'){
            switch(cmd){
            case 'i':
                int index = Keyboard.readInt("挿入位置");
                String dat = Keyboard.readString("文字列");
                if(!sl.add(index, dat)){
                    System.out.println("挿入できませんでした。");
                }
                break;
            case 'g':
                index = Keyboard.readInt("取出し位置");
                dat = sl.get(index);
                if(dat == null){
                    System.out.println("データ取得できませんでした。");
                }else{
                    System.out.println(dat);
                }
                break;
            case 'd':
                index = Keyboard.readInt("削除位置");
                dat = sl.remove(index);
                if(dat == null){
                    System.out.println("データ削除できませんでした。");
                }else{
                    System.out.println(dat + "を削除");
                }
                break;
            case 'c':
                System.out.println(sl.size() + "個");
                break;
            case 'l':
                String[] array = sl.toArray();
                for(int i = 0; i < array.length; i++){
                    System.out.printf(" %3d %s\n", (i + 1), array[i]);
                }
                break;
            case 'a':
                sl.clear();
                System.out.println("クリアしました。");
                break;
            default:
            }
            System.out.println();
        }
        
    }
}
