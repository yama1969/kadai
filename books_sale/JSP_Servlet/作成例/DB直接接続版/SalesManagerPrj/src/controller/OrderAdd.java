package controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import beans.Item;
import beans.Order;
import model.OrdersManager;
import exceptions.CannotAddOrderException;
import exceptions.IllegalQuantityException;
import exceptions.EmptyNameException;
import exceptions.EmptyAddException;

public class OrderAdd extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //リクエストパラメータ取得
        req.setCharacterEncoding("utf-8");
        String itemcode = req.getParameter("itemcode");
        String s_quantity = req.getParameter("quantity");
        String sei = req.getParameter("sei");
        String mei = req.getParameter("mei");
        String pref = req.getParameter("pref");
        String address = req.getParameter("address");
        String tel = req.getParameter("tel");
        String mail = req.getParameter("mail");
        
        //パラメータチェック
        //  商品コード
        HttpSession session = req.getSession();
        Item item = (Item)session.getAttribute("item");
        if(item == null || itemcode == null || !itemcode.equals(item.getCode())){
            req.setAttribute("message", "注文登録できませんでした。やり直してください。");
            forward(req, resp, "order.jsp");
            return;
        }
        //  数量
        int quantity = 0;
        try{
            quantity = Integer.parseInt(s_quantity);
        }catch(Exception e){ //空文字列であろうが、nullだろうが、とにかくダメならダメ
            req.setAttribute("message", "ご注文個数は1以上の整数です");
            forward(req, resp, "order.jsp");
            return;
        }
        
        //注文登録
        Order order = new Order();
        order.setItem(item);
        order.setQuantity(quantity);
        order.setSei(sei);
        order.setMei(mei);
        order.setPref(pref);
        order.setAdd(address);
        order.setTel(tel);
        order.setMail(mail);
        
        OrdersManager om = new OrdersManager();
        try{
            om.add(order);
            req.setAttribute("order", order);
            session.removeAttribute("item");
            forward(req, resp, "orderResult.jsp");
        }catch(EmptyAddException e){
            req.setAttribute("message", "ご住所を入力してください");
            forward(req, resp, "order.jsp");
            return;
        }catch(EmptyNameException e){
            req.setAttribute("message", "お名前を入力してください");
            forward(req, resp, "order.jsp");
            return;
        }catch(IllegalQuantityException e){
            req.setAttribute("message", "ご注文個数は1以上の整数です");
            forward(req, resp, "order.jsp");
            return;
        }catch(CannotAddOrderException e){
            req.setAttribute("message", "注文登録できませんでした。やり直してください。");
            forward(req, resp, "order.jsp");
            return;
        }
    }
    
    private void forward(HttpServletRequest req, HttpServletResponse resp, String jsp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher(jsp);
        rd.forward(req, resp);
    }
    
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        doPost(req, resp);
    }
}
