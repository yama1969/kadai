/*
 計算出題プログラムの問題種類を選択するクラス
*/
public class Selecter{
    /**
     問題種類を選択します。<br>
     ユーザーが入力した整数に基づいて、足し算・引き算・掛け算・割り算のいずれかの計算問題オブジェクトを生成し、返します。
     ユーザーからのの入力処理は、ユーザーI/Fオブジェクトに移譲します。
     
     @param ui ユーザーI/Fオブジェクト
     @return 選択された計算問題オブジェクト (入力不正時はnull)
    */
    
    //問題種類選択
    //引数:ui ユーザインターフェースオブジェクト
    public CalcQuestion select(CalcUI ui){
        String mess = "どの計算にしますか？\n";
        mess += "1.足し算\n";
        mess += "2.引き算\n";
        mess += "3.掛け算\n";
        mess += "4.割り算\n";
        mess += "番号を入力してください";
        ui.showQuestion(mess);
        
        int select = ui.inputAnswer();
        
        CalcQuestion question = null;
        switch(select){
        case 1:
            question = new CalcQuestion();
            break;
        case 2:
            question = new SubQuestion();
            break;
        case 3:
            question = new MulQuestion();
            break;
        case 4:
            question = new DivQuestion();
            break;
        default:
            question = null;
        }
        
        return question;
    }
}
