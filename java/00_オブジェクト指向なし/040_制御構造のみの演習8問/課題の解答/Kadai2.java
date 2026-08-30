/*
[課題2]
1～6の整数乱数を生成し、その数を表示した後、その数に従い以下の表示をする。
1の場合:「イタチの狸」
2の場合:「カニの蚊取り」
3の場合:「サンマの祭り」
上記以外の場合:「その他」
*/
public class Kadai2{
    public static void main(String[] args){
        int a = (int)(Math.random() * 6.0) + 1;
        System.out.println(a);
        switch(a){
        case 1:
            System.out.println("イタチの狸");
            break;
        case 2:
            System.out.println("カニの蚊取り");
            break;
        case 3:
            System.out.println("サンマの祭り");
            break;
        default:
            System.out.println("その他");
            break;
        }
    }
}
