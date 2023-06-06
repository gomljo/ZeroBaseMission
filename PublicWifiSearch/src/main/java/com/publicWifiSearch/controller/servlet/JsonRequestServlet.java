package com.publicWifiSearch.controller.servlet;

import com.publicWifiSearch.domain.dto.jsonRequestdtos.JsonRequestPublicWifiRecordDto;
import com.publicWifiSearch.domain.repostitory.dbConnection.DbConnectionMaker;
import com.publicWifiSearch.domain.repostitory.dbConnection.SqliteConnectionMaker;
import com.publicWifiSearch.domain.repostitory.publicWifi.PublicWifiRepository;
import com.publicWifiSearch.service.OpenAPIService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(name="openAPIRequestServlet", value= "/JsonRequest")
public class JsonRequestServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OpenAPIService openAPIService = new OpenAPIService();
        List<JsonRequestPublicWifiRecordDto> publicWifiDto = openAPIService.requestToOpenAPI();

        DbConnectionMaker dbConnectionMaker = new SqliteConnectionMaker();

        PublicWifiRepository publicWifiRepository = new PublicWifiRepository(dbConnectionMaker);
        publicWifiRepository.save(publicWifiDto);

        request.setAttribute("numberOfTotalData", publicWifiRepository.getNumberOfData());

        String viewPath = "/WEB-INF/OpenApiRequest.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);


    }
}
