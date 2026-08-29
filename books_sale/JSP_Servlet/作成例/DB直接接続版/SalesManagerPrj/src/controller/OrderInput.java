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

public class OrderInput extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //リクエストパラメータ取得
        req.setCharacterEncoding("utf-8");
        String itemcode = req.getParameter("itemcode");
        
        //パラメータチェック
        String message = null;
        String jsp = "order.jsp";
        Item item = null;
        
        if(itemcode != null){
            HttpSession session = req.getSession();
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
            req.setAttribute("message", message);
            jsp = "itemList.jsp";
        }
        
        //画面表示
        RequestDispatcher rd = req.getRequestDispatcher(jsp);
        rd.forward(req, resp);
    }
    
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        doPost(req, resp);
    }
}
