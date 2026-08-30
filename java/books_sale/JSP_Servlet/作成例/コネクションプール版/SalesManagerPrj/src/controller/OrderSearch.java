package controller;

import java.util.ArrayList;
import java.io.IOException;
import java.time.LocalDateTime;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import beans.Item;
import beans.Order;
import beans.OrderCondition;
import model.OrdersManager;
import exceptions.EmptyOrdersConditionException;
import exceptions.NoOrderException;
import exceptions.CannotSearchOrdersException;

public class OrderSearch extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //リクエストパラメータ取得
        req.setCharacterEncoding("utf-8");
        String s_orderno = req.getParameter("orderno");
        String s_startyear = req.getParameter("startyear");
        String s_startmonth = req.getParameter("startmonth");
        String s_startday = req.getParameter("startday");
        String s_endyear = req.getParameter("endyear");
        String s_endmonth = req.getParameter("endmonth");
        String s_endday = req.getParameter("endday");
        String custname = req.getParameter("custname");
        String tel = req.getParameter("tel");
        String itemname = req.getParameter("itemname");
        
        //検索条件変換
        //  注文番号
        int orderno = 0;
        try{
            orderno = Integer.parseInt(s_orderno);
        }catch(Exception e){
            //ordernoは0のまま
        }
        //  自注文日
        LocalDateTime startdate = null;
        try{
            int year = Integer.parseInt(s_startyear);
            int month = Integer.parseInt(s_startmonth);
            int day = Integer.parseInt(s_startday);
            startdate = LocalDateTime.of(year, month, day, 0, 0, 0);
        }catch(Exception e){
            //startdateはnullのまま
        }
        //  至注文日
        LocalDateTime enddate = null;
        try{
            int year = Integer.parseInt(s_endyear);
            int month = Integer.parseInt(s_endmonth);
            int day = Integer.parseInt(s_endday);
            enddate = LocalDateTime.of(year, month, day, 23, 59, 59);
        }catch(Exception e){
            //enddateはnullのまま
        }
        
        //検索実行
        OrderCondition oc = new OrderCondition();
        oc.setNo(orderno);
        oc.setDatetime(startdate);
        oc.setEnddate(enddate);
        oc.setSei(custname);
        oc.setTel(tel);
        Item item = new Item(null, itemname, 0);
        oc.setItem(item);
        
        String jsp = "/WEB-INF/orderList.jsp";
        
        OrdersManager om = new OrdersManager();
        try{
            ArrayList<Order> list = om.search(oc);
            req.setAttribute("orderlist", list);
        }catch(EmptyOrdersConditionException e){
            req.setAttribute("message", "検索条件がひとつも入力されていません");
            jsp = "/WEB-INF/orderCondition.jsp";
        }catch(NoOrderException e){
            req.setAttribute("message", "検索結果はありません");
            jsp = "/WEB-INF/orderCondition.jsp";
        }catch(CannotSearchOrdersException e){
            req.setAttribute("message", "検索できませんでした。やり直してください。");
            jsp = "/WEB-INF/orderCondition.jsp";
        }
        
        RequestDispatcher rd = req.getRequestDispatcher(jsp);
        rd.forward(req, resp);
    }
    
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        doPost(req, resp);
    }
}
