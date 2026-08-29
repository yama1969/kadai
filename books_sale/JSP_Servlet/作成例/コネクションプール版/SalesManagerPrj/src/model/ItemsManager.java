package model;

import beans.Item;
import dao.ItemDataAccessor;
import exceptions.EmptyItemsConditionException;
import exceptions.NoItemsException;
import exceptions.CannotSearchItemsException;

import java.util.ArrayList;

public class ItemsManager{
    public ArrayList<Item> search(Item key) throws EmptyItemsConditionException, NoItemsException, CannotSearchItemsException {
        
        String code = key.getCode();
        String name = key.getName();
        if((name == null || name.equals("")) && (code == null || code.equals(""))){
            throw new EmptyItemsConditionException("検索語が入力されていません");
        }
        
        ItemDataAccessor acc = new ItemDataAccessor();
        
        ArrayList<Item> list = acc.select(key);
        if(list.size() == 0){
            throw new NoItemsException("検索結果はありません");
        }
        
        return list;
    }
}
