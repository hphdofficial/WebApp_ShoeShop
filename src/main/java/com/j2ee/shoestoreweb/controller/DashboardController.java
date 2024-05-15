package com.j2ee.shoestoreweb.controller;

import com.j2ee.shoestoreweb.dao.DAO;
import com.j2ee.shoestoreweb.entity.Account;
import com.j2ee.shoestoreweb.entity.Invoice;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "DashboardController", urlPatterns = {"/admin"})
public class DashboardController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("acc");
        int uID;
        DAO dao = new DAO();
        if (a == null) {
            request.getSession().setAttribute("page", "admin");
            response.sendRedirect("login");
            return;
        }
        uID = a.getId();
        int checkIsAdmin = dao.checkAccountAdmin(uID);
        if (checkIsAdmin == 0) {
            request.getSession().setAttribute("page", "admin");
            response.sendRedirect("login");
            return;
        }

        double totalMoney1 = dao.totalMoneyDay(1);
        double totalMoney2 = dao.totalMoneyDay(2);
        double totalMoney3 = dao.totalMoneyDay(3);
        double totalMoney4 = dao.totalMoneyDay(4);
        double totalMoney5 = dao.totalMoneyDay(5);
        double totalMoney6 = dao.totalMoneyDay(6);
        double totalMoney7 = dao.totalMoneyDay(7);

        double totalMoneyMonth1 = dao.totalMoneyMonth(1);
        double totalMoneyMonth2 = dao.totalMoneyMonth(2);
        double totalMoneyMonth3 = dao.totalMoneyMonth(3);
        double totalMoneyMonth4 = dao.totalMoneyMonth(4);
        double totalMoneyMonth5 = dao.totalMoneyMonth(5);
        double totalMoneyMonth6 = dao.totalMoneyMonth(6);
        double totalMoneyMonth7 = dao.totalMoneyMonth(7);
        double totalMoneyMonth8 = dao.totalMoneyMonth(8);
        double totalMoneyMonth9 = dao.totalMoneyMonth(9);
        double totalMoneyMonth10 = dao.totalMoneyMonth(10);
        double totalMoneyMonth11 = dao.totalMoneyMonth(11);
        double totalMoneyMonth12 = dao.totalMoneyMonth(12);

        int allReview = dao.countAllReview();
        int allProduct = dao.countAllProduct();

        double sumAllInvoice = dao.sumAllInvoice();

        List<Invoice> listAllInvoice = dao.getAllInvoice();
        List<Account> listAllAccount = dao.getAllAccount();

        int countCustomer = 0;
        int countEmployee = 0;
        for (Account value : listAllAccount) {
            if (value.getIsSell() == 0) {
                countCustomer++;
            } else {
                countEmployee++;
            }
        }

        //doanh thu nam
        double money = 0;
        for (Invoice value : listAllInvoice) {

            String s = value.getNgayXuat().toString();
            String temp[] = s.split("-");
            if (Integer.parseInt(temp[0]) == 2023) {
                money += value.getTongGia();
            }
        }
        request.setAttribute("money", money);

        request.setAttribute("countCustomer", countCustomer);
        request.setAttribute("countEmployee", countEmployee);
        request.setAttribute("listAllInvoice", listAllInvoice);
        request.setAttribute("listAllAccount", listAllAccount);
        request.setAttribute("sumAllInvoice", sumAllInvoice);

        request.setAttribute("allReview", allReview);
        request.setAttribute("allProduct", allProduct);

        request.setAttribute("totalMoney1", totalMoney1);
        request.setAttribute("totalMoney2", totalMoney2);
        request.setAttribute("totalMoney3", totalMoney3);
        request.setAttribute("totalMoney4", totalMoney4);
        request.setAttribute("totalMoney5", totalMoney5);
        request.setAttribute("totalMoney6", totalMoney6);
        request.setAttribute("totalMoney7", totalMoney7);

        request.setAttribute("totalMoneyMonth1", totalMoneyMonth1);
        request.setAttribute("totalMoneyMonth2", totalMoneyMonth2);
        request.setAttribute("totalMoneyMonth3", totalMoneyMonth3);
        request.setAttribute("totalMoneyMonth4", totalMoneyMonth4);
        request.setAttribute("totalMoneyMonth5", totalMoneyMonth5);
        request.setAttribute("totalMoneyMonth6", totalMoneyMonth6);
        request.setAttribute("totalMoneyMonth7", totalMoneyMonth7);
        request.setAttribute("totalMoneyMonth8", totalMoneyMonth8);
        request.setAttribute("totalMoneyMonth9", totalMoneyMonth9);
        request.setAttribute("totalMoneyMonth10", totalMoneyMonth10);
        request.setAttribute("totalMoneyMonth11", totalMoneyMonth11);
        request.setAttribute("totalMoneyMonth12", totalMoneyMonth12);

        request.getRequestDispatcher("Statistic.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}