package com.itheima.web;

import com.itheima.domain.ContactInfo;
import com.itheima.service.ContactService;
import com.itheima.service.impl.ContactServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/update_contact")
public class UpdateContactServlet extends HttpServlet {
    ContactService service=new ContactServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");

        //1接受数据
        Map<String, String[]> parameterMap = request.getParameterMap();

        boolean flag=service.updateContact(parameterMap);
        //2
        if (flag){
            response.sendRedirect("change_page_size");
        }else {
            request.setAttribute("errorMsg","更新出错拉");
            request.getRequestDispatcher("/error.jsp").forward(request,response);
        }
        //3
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
