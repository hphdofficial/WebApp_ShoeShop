package com.j2ee.shoestoreweb.controller;

import com.j2ee.shoestoreweb.dao.DAO;
import com.j2ee.shoestoreweb.entity.Product;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Random;

@WebServlet(name = "XuatExcelProductControl", urlPatterns = {"/xuatExcelProductControl"})
public class XuatExcelProductControl extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        DAO dao = new DAO();
        List<Product> list = dao.getAllProduct();

        System.out.print(list.get(0));

        int maximum = 2147483647;
        int minimum = 1;

        Random rn = new Random();
        int range = maximum - minimum + 1;
        int randomNum = rn.nextInt(range) + minimum;

        FileOutputStream file = new FileOutputStream("C:\\ExcelWebBanGiay\\" + "san-pham" + Integer.toString(randomNum) + ".xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet workSheet = workbook.createSheet("1");
        XSSFRow row;
        XSSFCell cell0;
        XSSFCell cell1;
        XSSFCell cell2;
        XSSFCell cell3;
        XSSFCell cell4;
        XSSFCell cell5;
        XSSFCell cell6;
        XSSFCell cell7;
        XSSFCell cell8;
        XSSFCell cell9;
        XSSFCell cell10;
        XSSFCell cell11;

        row = workSheet.createRow(0);
        cell0 = row.createCell(0);
        cell0.setCellValue("ID");
        cell1 = row.createCell(1);
        cell1.setCellValue("Name");
        cell2 = row.createCell(2);
        cell2.setCellValue("Image");
        cell3 = row.createCell(3);
        cell3.setCellValue("Price");
        cell4 = row.createCell(4);
        cell4.setCellValue("Title");
        cell5 = row.createCell(5);
        cell5.setCellValue("Description");
        cell5 = row.createCell(6);
        cell5.setCellValue("Model");
        cell5 = row.createCell(7);
        cell5.setCellValue("Color");
        cell5 = row.createCell(8);
        cell5.setCellValue("Delivery");
        cell5 = row.createCell(9);
        cell5.setCellValue("Image");
        cell5 = row.createCell(10);
        cell5.setCellValue("Image");
        cell5 = row.createCell(11);
        cell5.setCellValue("Image");

        int i = 0;

        for (Product pro : list) {
            i = i + 1;
            row = workSheet.createRow(i);
            cell0 = row.createCell(0);
            cell0.setCellValue(pro.getId());
            cell1 = row.createCell(1);
            cell1.setCellValue(pro.getName());
            cell2 = row.createCell(2);
            cell2.setCellValue(pro.getImage());
            cell3 = row.createCell(3);
            cell3.setCellValue(pro.getPrice());
            cell4 = row.createCell(4);
            cell4.setCellValue(pro.getTitle());
            cell4 = row.createCell(5);
            cell4.setCellValue(pro.getDescription());
            cell4 = row.createCell(6);
            cell4.setCellValue(pro.getModel());
            cell4 = row.createCell(7);
            cell4.setCellValue(pro.getColor());
            cell4 = row.createCell(8);
            cell4.setCellValue(pro.getDelivery());
            cell4 = row.createCell(9);
            cell4.setCellValue(pro.getImage2());
            cell4 = row.createCell(10);
            cell4.setCellValue(pro.getImage3());
            cell4 = row.createCell(11);
            cell4.setCellValue(pro.getImage4());
        }

        workbook.write(file);
        workbook.close();
        file.close();

        request.setAttribute("mess", "Đã xuất file Excel thành công!");
        request.getRequestDispatcher("managerAccount").forward(request, response);
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

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}