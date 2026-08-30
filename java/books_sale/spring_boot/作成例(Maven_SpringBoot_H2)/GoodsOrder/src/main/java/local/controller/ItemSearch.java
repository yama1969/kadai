package local.controller;

import java.util.ArrayList;
import jakarta.servlet.http.HttpSession;

import local.beans.Item;
import local.model.ItemsManager;
import local.exceptions.EmptyItemsConditionException;
import local.exceptions.NoItemsException;
import local.exceptions.CannotSearchItemsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ItemSearch{
    private final ItemsManager itemsManager;

    @Autowired
    public ItemSearch(ItemsManager itemsManager){
        this.itemsManager = itemsManager;
    }

    @RequestMapping("/itemList.html")
    public String exec(
        @RequestParam("itemname") String itemname,
        Model model,
        HttpSession session
    ){
        //検索実行
        String message = null;
        String html = "itemList";
        try{
            Item key = new Item(null, itemname, 0);
            ArrayList<Item> itemlist = itemsManager.search(key);
            session.setAttribute("itemlist", itemlist);
        }catch(EmptyItemsConditionException e){
            message = "検索語が入力されていません";
            model.addAttribute("message", message);
            html = "start";
        }catch(NoItemsException e){
            message = "検索結果はありません";
            model.addAttribute("message", message);
            html = "start";
        }catch(CannotSearchItemsException e){
            message = "検索できませんでした。やり直してください。";
            model.addAttribute("message", message);
            html = "start";
        }
        return html;
    }
}
