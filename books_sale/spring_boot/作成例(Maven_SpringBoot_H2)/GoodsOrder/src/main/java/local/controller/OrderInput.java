package local.controller;

import java.util.ArrayList;
import jakarta.servlet.http.HttpSession;

import local.beans.Item;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OrderInput{
    @RequestMapping("/order.html")
    public String exec(
        @RequestParam("itemcode") String itemcode,
        Model model,
        HttpSession session
    ){
        //パラメータチェック
        String message = null;
        String html = "order";
        Item item = null;
        
        if(itemcode != null){
            ArrayList<Item> itemlist = (ArrayList<Item>)session.getAttribute("itemlist");
            for(Item curr_item : itemlist){
                if(itemcode.equals(curr_item.getCode())){
                    item = curr_item;
                    session.setAttribute("item", item);
                    break;
                }
            }
        }
        
        if(item == null){
            message = "商品の番号が違います";
            model.addAttribute("message", message);
            html = "itemList";
        }

        return html;
    }
}
