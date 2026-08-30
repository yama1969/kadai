package local.controller;

import java.util.ArrayList;
import java.time.LocalDateTime;

import local.beans.Item;
import local.beans.Order;
import local.beans.OrderCondition;
import local.model.OrdersManager;
import local.exceptions.EmptyOrdersConditionException;
import local.exceptions.NoOrderException;
import local.exceptions.CannotSearchOrdersException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OrderSearch{
    private final OrdersManager ordersManager;

    @Autowired
    public OrderSearch(OrdersManager ordersManager){
        this.ordersManager = ordersManager;
    }

    @RequestMapping("/orderList.html")
    public String exec(
        @RequestParam("orderno") String orderno,
        @RequestParam("startyear") String startyear,
        @RequestParam("startmonth") String startmonth,
        @RequestParam("startday") String startday,
        @RequestParam("endyear") String endyear,
        @RequestParam("endmonth") String endmonth,
        @RequestParam("endday") String endday,
        @RequestParam("custname") String custname,
        @RequestParam("tel") String tel,
        @RequestParam("itemname") String itemname,
        Model model
    ){
        //検索条件変換
        //  注文番号
        int iorderno = 0;
        try{
            iorderno = Integer.parseInt(orderno);
        }catch(Exception e){
            //iordernoは0のまま
        }
        //  自注文日
        LocalDateTime startdate = null;
        try{
            int iyear = Integer.parseInt(startyear);
            int imonth = Integer.parseInt(startmonth);
            int iday = Integer.parseInt(startday);
            startdate = LocalDateTime.of(iyear, imonth, iday, 0, 0, 0);
        }catch(Exception e){
            //startdateはnullのまま
        }
        //  至注文日
        LocalDateTime enddate = null;
        try{
            int iyear = Integer.parseInt(endyear);
            int imonth = Integer.parseInt(endmonth);
            int iday = Integer.parseInt(endday);
            enddate = LocalDateTime.of(iyear, imonth, iday, 23, 59, 59);
        }catch(Exception e){
            //enddateはnullのまま
        }
        
        //検索実行
        OrderCondition oc = new OrderCondition();
        oc.setNo(iorderno);
        oc.setDatetime(startdate);
        oc.setEnddate(enddate);
        oc.setSei(custname);
        oc.setTel(tel);
        Item item = new Item(null, itemname, 0);
        oc.setItem(item);
        
        String html = "orderList";
        
        try{
            ArrayList<Order> list = ordersManager.search(oc);
            model.addAttribute("orderlist", list);
        }catch(EmptyOrdersConditionException e){
            model.addAttribute("message", "検索条件がひとつも入力されていません");
            html = "staff";
        }catch(NoOrderException e){
            model.addAttribute("message", "検索結果はありません");
            html = "staff";
        }catch(CannotSearchOrdersException e){
            model.addAttribute("message", "検索できませんでした。やり直してください。");
            html = "staff";
        }

        return html;
    }
}
