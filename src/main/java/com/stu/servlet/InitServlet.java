package com.stu.servlet;

import com.stu.factory.DaoFactory;
import com.stu.util.DBConnection;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(urlPatterns = "/init", loadOnStartup = 1,
    initParams = {
        @WebInitParam(name = "dbDriver", value = ""),
        @WebInitParam(name = "dbUrl", value = ""),
        @WebInitParam(name = "dbUser", value = ""),
        @WebInitParam(name = "dbPassword", value = ""),
        @WebInitParam(name = "daoType", value = "memory")
    }
)
public class InitServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        String daoType = config.getServletContext().getInitParameter("daoType");
        if (daoType == null || daoType.isEmpty()) {
            daoType = "memory";
        }
        DaoFactory.getInstance().setDaoType(daoType);

        String driver = config.getServletContext().getInitParameter("dbDriver");
        String url = config.getServletContext().getInitParameter("dbUrl");
        String user = config.getServletContext().getInitParameter("dbUser");
        String password = config.getServletContext().getInitParameter("dbPassword");

        if (driver != null && url != null && user != null && password != null) {
            DBConnection.init(driver, url, user, password);
        }

        System.out.println("系统初始化完成，DAO类型: " + daoType);
    }
}
