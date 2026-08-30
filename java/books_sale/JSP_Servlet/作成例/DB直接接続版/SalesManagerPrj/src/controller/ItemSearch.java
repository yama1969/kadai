package controller;

import java.util.ArrayList;
import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import beans.Item;
import model.ItemsManager;
import exceptions.EmptyItemsConditionException;
import exceptions.NoItemsException;
import exceptions.CannotSearchItemsException;

public class ItemSearch extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //リクエストパラメータ取得
        req.setCharacterEncoding("utf-8");
        String itemname = req.getParameter("itemname");
        Item key = new Item(null, itemname, 0);
        
        //検索実行
        String message = null;
        String jsp = "itemList.jsp";
        ItemsManager im = new ItemsManager();
        try{
            ArrayList<Item> itemlist = im.search(key);
            
            HttpSession session = req.getSession();
            session.setAttribute("itemlist", itemlist);
            
        }catch(EmptyItemsConditionException e){
            message = "検索語が入力されていません";
            req.setAttribute("message", message);
            jsp = "itemCondition.jsp";
        }catch(NoItemsException e){
            message = "検索結果はありません";
            req.setAttribute("message", message);
            jsp = "itemCondition.jsp";
        }catch(CannotSearchItemsException e){
            message = "検索できませんでした。やり直してください。";
            req.setAttribute("message", message);
            jsp = "itemCondition.jsp";
        }
        
        RequestDispatcher rd = req.getRequestDispatcher(jsp);
        rd.forward(req, resp);
    }
    
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        doPost(req, resp);
    }
}
