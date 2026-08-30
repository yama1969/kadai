package local.model;

import local.beans.Item;
import local.dao.ItemDataAccessor;
import local.exceptions.EmptyItemsConditionException;
import local.exceptions.NoItemsException;
import local.exceptions.CannotSearchItemsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ItemsManager{
    private final ItemDataAccessor itemDao;

    @Autowired
    public ItemsManager(ItemDataAccessor itemDao){
        this.itemDao = itemDao;
    }

    public ArrayList<Item> search(Item key) throws EmptyItemsConditionException, NoItemsException, CannotSearchItemsException {
        
        String code = key.getCode();
        String name = key.getName();
        if((name == null || name.equals("")) && (code == null || code.equals(""))){
            throw new EmptyItemsConditionException("検索語が入力されていません");
        }

        ArrayList<Item> list = itemDao.select(key);
        if(list.size() == 0){
            throw new NoItemsException("検索結果はありません");
        }
        
        return list;
    }
}
