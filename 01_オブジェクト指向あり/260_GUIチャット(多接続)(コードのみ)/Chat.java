/**
  Chatプロトコルで通信するアプリケーションクラスは、このChatインターフェースを実装してください。
*/
public interface Chat{
    /**
      新しいユーザがチャットに参加すると、このメソッドが呼び出されます。
    */
    public abstract void newUser(String name);
    
    /**
      既に参加済みのユーザの情報が後から届いた場合に、このメソッドが呼び出されます。
    */
    public abstract void addUser(String name);
    
    /**
      同一のハンドル名があり、ハンドル名変更を要求されたときに、このメソッドが呼び出されます。
    */
    public abstract void changeName();
    
    /**
      別ユーザより発言が届くと、このメソッドが呼び出されます。
    */
    public abstract void message(String mess);
    
    /**
      別ユーザがチャットから脱退すると、このメソッドが呼び出されます。
    */
    public abstract void exitUser(String name);
    
    /**
      別ユーザの接続が切れると、このメソッドが呼び出されます。
    */
    public abstract void disconUser(String name);
}
