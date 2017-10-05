
package com.salesman;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


import com.util.DividePage;
import com.util.UUIDTools;
public class SalesAction extends HttpServlet  {
    private SalesService service;
	/**
	 * Constructor of the object.
	 */
	public SalesAction() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		String action_flag = request.getParameter("action_flag");
		 if (action_flag.equals("search")) {
			listSales(request,response);
		}
		
		out.flush();
		out.close();
	}
        
	


	private void listSales(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		String productName = request.getParameter("email");	
		String pageNum = request.getParameter("pageNum");
		System.out.println("参数 pageNum :"+pageNum);
		if (productName == null) {
			productName = "";
		}
		
		
		
		int totalRecord = service.getItemCount(productName); //获取总的记录数
		int currentPage = 1;
		DividePage dividePage = new DividePage(5, totalRecord);//默认第一页开始
		if (pageNum != null) {
			
			
			currentPage = Integer.parseInt(pageNum);
			
			dividePage.setCurrentPage(currentPage);
		}
		
		//记录从第几行开始
		int start = dividePage.fromIndex();
		//显示几条记录
		int end = dividePage.toIndex();		
		
		System.out.println("currentPageNum :"+ dividePage.getCurrentPage() +", start = "+start +", end = "+end);
		
		List<Map<String, Object>> list = null;
		try {
			list = service.listSales(productName , start , end);
			request.setAttribute("listSales", list);
			request.setAttribute("dividePage", dividePage);
			request.setAttribute("productName",productName );
			request.getRequestDispatcher("/main.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}		
		
	}

	public void init() throws ServletException {
		// Put your code here
		service = new SalesDao();
	}
}
