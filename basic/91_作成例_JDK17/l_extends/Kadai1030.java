package l_extends;
public class Kadai1030{
    public static void main(String[] args){
        
        ObjectList ol = new ObjectList();
        
        String prompt = "[e:終了, i:挿入, g:取出, d:削除, c:値数, l:全値, a:全消]";
        char cmd = 0;
        while((cmd = Keyboard.readChar(prompt)) != 'e'){
            switch(cmd){
            case 'i':
                int index = Keyboard.readInt("挿入位置");
                String dat = Keyboard.readString("文字列");
                if(!ol.add(index, dat)){
                    System.out.println("挿入できませんでした。");
                }
                break;
            case 'g':
                index = Keyboard.readInt("取出し位置");
                dat = (String)ol.get(index);
                if(dat == null){
                    System.out.println("データ取得できませんでした。");
                }else{
                    System.out.println(dat);
                }
                break;
            case 'd':
                index = Keyboard.readInt("削除位置");
                dat = (String)ol.remove(index);
                if(dat == null){
                    System.out.println("データ削除できませんでした。");
                }else{
                    System.out.println(dat + "を削除");
                }
                break;
            case 'c':
                System.out.println(ol.size() + "個");
                break;
            case 'l':
                Object[] array = ol.toArray();
                for(int i = 0; i < array.length; i++){
                    System.out.printf(" %3d %s\n", (i + 1), (String)array[i]);
                }
                break;
            case 'a':
                ol.clear();
                System.out.println("クリアしました。");
                break;
            default:
            }
            System.out.println();
        }
        
    }
}
