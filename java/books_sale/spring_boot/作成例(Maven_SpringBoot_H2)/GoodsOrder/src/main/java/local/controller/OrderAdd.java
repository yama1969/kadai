package local.controller;

import jakarta.servlet.http.HttpSession;

import local.beans.Item;
import local.beans.Order;
import local.model.OrdersManager;
import local.exceptions.CannotAddOrderException;
import local.exceptions.IllegalQuantityException;
import local.exceptions.EmptyNameException;
import local.exceptions.EmptyAddException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OrderAdd{
    private final OrdersManager ordersManager;

    @Autowired
    public OrderAdd(OrdersManager ordersManager){
        this.ordersManager = ordersManager;
    }

    @RequestMapping("/orderResult.html")
    public String exec(
        @RequestParam("itemcode") String itemcode,
        @RequestParam("quantity") String quantity,
        @RequestParam("sei") String sei,
        @RequestParam("mei") String mei,
        @RequestParam("pref") String pref,
        @RequestParam("address") String address,
        @RequestParam("tel") String tel,
        @RequestParam("mail") String mail,
        Model model,
        HttpSession session
    ){
        //パラメータチェック
        //  商品コード
        Item item = (Item)session.getAttribute("item");
        if(item == null || itemcode == null || !itemcode.equals(item.getCode())){
            model.addAttribute("message", "注文登録できませんでした。やり直してください。");
            return "order";
        }
        //  数量
        int iquantity = 0;
        try{
            iquantity = Integer.parseInt(quantity);
        }catch(Exception e){ //空文字列であろうが、nullだろうが、とにかくダメならダメ
            model.addAttribute("message", "ご注文個数は1以上の整数です");
            return "order";
        }
        
        //注文登録
        Order order = new Order();
        order.setItem(item);
        order.setQuantity(iquantity);
        order.setSei(sei);
        order.setMei(mei);
        order.setPref(pref);
        order.setAdd(address);
        order.setTel(tel);
        order.setMail(mail);
        
        try{
            ordersManager.add(order);
            model.addAttribute("order", order);
            session.removeAttribute("item");
            return "orderResult";
        }catch(EmptyAddException e){
            model.addAttribute("message", "ご住所を入力してください");
            return "order";
        }catch(EmptyNameException e){
            model.addAttribute("message", "お名前を入力してください");
            return "order";
        }catch(IllegalQuantityException e){
            model.addAttribute("message", "ご注文個数は1以上の整数です");
            return "order";
        }catch(CannotAddOrderException e){
            model.addAttribute("message", "注文登録できませんでした。やり直してください。");
            return "order";
        }
    }
}
