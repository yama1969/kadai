package local.model;

import local.beans.Order;
import local.beans.OrderCondition;
import local.beans.Item;
import local.dao.OrderDataAccessor;
import local.exceptions.EmptyItemsConditionException;
import local.exceptions.NoItemsException;
import local.exceptions.CannotSearchItemsException;
import local.exceptions.IllegalQuantityException;
import local.exceptions.EmptyNameException;
import local.exceptions.EmptyAddException;
import local.exceptions.CannotAddOrderException;
import local.exceptions.EmptyOrdersConditionException;
import local.exceptions.NoOrderException;
import local.exceptions.CannotSearchOrdersException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class OrdersManager{
    private final OrderDataAccessor orderDao;
    private final ItemsManager itemsManager;

    @Autowired
    public OrdersManager(OrderDataAccessor orderDao, ItemsManager itemsManager){
        this.orderDao = orderDao;
        this.itemsManager = itemsManager;
    }

    /****************************************************************************
     注文の登録
    ****************************************************************************/
    public int add(Order newOrder) throws CannotAddOrderException, IllegalQuantityException, EmptyNameException, EmptyAddException {
        try{
            ArrayList<Item> list = itemsManager.search(newOrder.getItem());
            if(list.size() > 1){
                throw new CannotAddOrderException("注文登録できませんでした。やり直してください。");
            }
        }catch(EmptyItemsConditionException e){
            e.printStackTrace();
            throw new CannotAddOrderException("注文登録できませんでした。やり直してください。");
        }catch(NoItemsException e){
            e.printStackTrace();
            throw new CannotAddOrderException("注文登録できませんでした。やり直してください。");
        }catch(CannotSearchItemsException e){
            e.printStackTrace();
            throw new CannotAddOrderException("注文登録できませんでした。やり直してください。");
        }
        
        if(newOrder.getQuantity() < 1){
            throw new IllegalQuantityException("ご注文個数は1以上の整数です");
        }
        if(newOrder.getSei() == null || newOrder.getSei().equals("")){
            throw new EmptyNameException("お名前を入力してください");
        }
        if(newOrder.getMei() == null || newOrder.getMei().equals("")){
            throw new EmptyNameException("お名前を入力してください");
        }
        if(newOrder.getPref() == null || newOrder.getPref().equals("")){
            throw new EmptyAddException("ご住所を入力してください");
        }
        if(newOrder.getAdd() == null || newOrder.getAdd().equals("")){
            throw new EmptyAddException("ご住所を入力してください");
        }
        newOrder.setDatetime(LocalDateTime.now());
        int order_no = orderDao.insert(newOrder);
        
        return order_no;
    }
    
    /****************************************************************************
     注文の検索
    ****************************************************************************/
    public ArrayList<Order> search(OrderCondition key) throws EmptyOrdersConditionException, NoOrderException, CannotSearchOrdersException{
        
        //条件有無のチェック
        boolean isExistCondition = false;
        if(key.getNo() > 0){                                          //注文番号
            isExistCondition = true;
        }
        if(key.getDatetime() != null || key.getEnddate() != null){    //自至注文日時
            isExistCondition = true;
        }
        String dat = key.getSei();                                    //姓
        if(dat != null && !dat.equals("")){
            isExistCondition = true;
        }
        dat = key.getMei();                                           //名
        if(dat != null && !dat.equals("")){
            isExistCondition = true;
        }
        dat = key.getTel();                                           //電話番号
        if(dat != null && !dat.equals("")){
            isExistCondition = true;
        }
        Item item = key.getItem();                                    //商品名
        if(item != null){
            dat = item.getName();
        }
        if(dat != null && !dat.equals("")){
            isExistCondition = true;
        }
        
        if(!isExistCondition){
            throw new EmptyOrdersConditionException("検索条件がひとつも入力されていません");
        }
        
        //検索実行
        ArrayList<Order> list = orderDao.select(key);
        if(list.size() == 0){
            throw new NoOrderException("検索結果はありません");
        }
        return list;
    }
}
