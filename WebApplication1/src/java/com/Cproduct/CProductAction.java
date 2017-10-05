package com.Cproduct;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.*;
import javax.servlet.http.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


import com.util.DividePage;
import com.util.UUIDTools;

public class CProductAction extends HttpServlet {

	private CProductService service;
	/**
	 * Constructor of the object.
	 */
	public CProductAction() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); 
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
                
		
              
                
		String action_flag = request.getParameter("action_flag");
		if (action_flag.equals("add")) {
			addProduct(request,response);
		}else if (action_flag.equals("search")) {
			searchProduct(request,response);
		}else if (action_flag.equals("addtocart")) {
			addToCart(request,response);
		}else if (action_flag.equals("view")) {
			viewProduct(request,response);
		}else if (action_flag.equals("rank")) {
			rankProduct(request,response);
		}else if (action_flag.equals("list")) {
			listProduct(request,response);
		}else if (action_flag.equals("update")) {
			updateNumber(request,response);
		}
		
		
		out.flush();
		out.close();
	}

	private void viewProduct(HttpServletRequest request,
			HttpServletResponse response) {
		String proid = request.getParameter("proid");
		Map<String, Object> map = service.viewProduct(proid);
		request.setAttribute("productMap", map);
		try {
			request.getRequestDispatcher("/viewProduct.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}
        
        private void addToCart(HttpServletRequest request,
			HttpServletResponse response) {
		// auto-generated method stub
                
                
                
		String[] ids = request.getParameterValues("ids");
                
                for(int i=0;i<ids.length;i++){
                System.out.println(i +   "s" +ids[i]);
                }
		List<Map<String,Object>> map = service.addToCart(ids);

		request.setAttribute("CProduct", map);
		try {
			request.getRequestDispatcher("/cart.jsp").forward(request, response);
                        
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}

	private void searchProduct(HttpServletRequest request,
			HttpServletResponse response) {
		
		String productName = request.getParameter("proname");	
		String pageNum = request.getParameter("pageNum");
		System.out.println("参数 pageNum :"+pageNum);
		if (productName == null) {
			productName = "";
		}
		
		
		
		int totalRecord = service.getItemCount(productName); //get record
		int currentPage = 1;
		DividePage dividePage = new DividePage(50, totalRecord);//from page 1
		if (pageNum != null) {
			
			
			currentPage = Integer.parseInt(pageNum);
			
			dividePage.setCurrentPage(currentPage);
		}
		
		//mark start page
		int start = dividePage.fromIndex();
		//show records
		int end = dividePage.toIndex();		
		
		System.out.println("currentPageNum :"+ dividePage.getCurrentPage() +", start = "+start +", end = "+end);
		
		List<Map<String, Object>> list = null;
		try {
			list = service.listProduct(productName , start , end);
			request.setAttribute("listProduct", list);
			request.setAttribute("dividePage", dividePage);
			request.setAttribute("productName",productName );
			request.getRequestDispatcher("/searchProduct.jsp").forward(request, response);
		} catch (Exception e) {
			// exception
			e.printStackTrace();
		}		
		
	}

	private void listProduct(HttpServletRequest request,
			HttpServletResponse response) {
		String productName = request.getParameter("proname");	
		String pageNum = request.getParameter("pageNum");
		System.out.println("参数 pageNum :"+pageNum);
		if (productName == null) {
			productName = "";
		}
		
		
		
		int totalRecord = service.getItemCount(productName); 
		int currentPage = 1;
		DividePage dividePage = new DividePage(5, totalRecord);
		if (pageNum != null) {
			
			
			currentPage = Integer.parseInt(pageNum);
			
			dividePage.setCurrentPage(currentPage);
		}
		
		
		int start = dividePage.fromIndex();
		
		int end = dividePage.toIndex();		
		
		System.out.println("currentPageNum :"+ dividePage.getCurrentPage() +", start = "+start +", end = "+end);
		
		List<Map<String, Object>> list = null;
		try {
			list = service.listProduct(productName , start , end);
			request.setAttribute("listProduct", list);
			request.setAttribute("dividePage", dividePage);
			request.setAttribute("productName",productName );
			request.getRequestDispatcher("/Cmain.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
	}
        
        private void rankProduct(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
            
                String productName = request.getParameter("proname");	
		String pageNum = request.getParameter("pageNum");
		System.out.println("参数 pageNum :"+pageNum);
		if (productName == null) {
			productName = "";
		}
		
		
		
		int totalRecord = service.getItemCount(productName); 
		int currentPage = 1;
		DividePage dividePage = new DividePage(5, totalRecord);
		if (pageNum != null) {
			
			
			currentPage = Integer.parseInt(pageNum);
			
			dividePage.setCurrentPage(currentPage);
		}
		
		int start = dividePage.fromIndex();
		
		int end = dividePage.toIndex();		
		
		System.out.println("currentPageNum :"+ dividePage.getCurrentPage() +", start = "+start +", end = "+end);
		
		List<Map<String, Object>> list = null;
		try {
			list = service.rankProduct(productName , start , end);
			request.setAttribute("listProduct", list);
			request.setAttribute("dividePage", dividePage);
			request.setAttribute("productName",productName );
			request.getRequestDispatcher("/rankProduct.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}		
            
        }

	private void addProduct(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		//submit file (later
		String  path = request.getContextPath();		
		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
		servletFileUpload.setFileSizeMax(3*1024*1024);
		servletFileUpload.setSizeMax(6*1024*1024);
		List<FileItem> list = null;		
		List<Object> params = new ArrayList<Object>();
		params.add(UUIDTools.getUUID()); //key
		try {
			list = servletFileUpload.parseRequest(request);				
			
			for(FileItem fileItem : list){
				if (fileItem.isFormField()) {//text
					String fileItemName = fileItem.getFieldName(); 
					String fileItemValue = fileItem.getString("utf-8");//<input>
					if (fileItemName.equals("proname")) {
						params.add(fileItemValue); // proname
					}else if (fileItemName.equals("proprice")) {
						params.add(fileItemValue);// proprice
					}else if (fileItemName.equals("proaddress")) {
						params.add(fileItemValue);// proaddress
					}					
				}/*else{ //非文本字段					
					
					String imageName = fileItem.getName(); //
					params.add(imageName);//  proimage			
					//String path = request.getRealPath("/upload");
					String upload_dir = request.getServletContext().getRealPath("/upload");
					File uploadFile = new File(upload_dir+"/"+imageName);
					System.out.println("---upload_dir--->>"+uploadFile);
					fileItem.write(uploadFile);						
				}*/				
			}
			
			// add to db
			boolean flag = service.addProduct(params);
			if (flag) {
				
				response.sendRedirect(path+"/main.jsp");
			}
				
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
        
        private void updateNumber(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		
		String  path = request.getContextPath();		
		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
		servletFileUpload.setFileSizeMax(3*1024*1024);
		servletFileUpload.setSizeMax(6*1024*1024);
		List<FileItem> list = null;		
		List<Object> params = new ArrayList<Object>();
		//params.add(UUIDTools.getUUID()); 
                
                String ids=request.getParameter("proid");
                String number=request.getParameter("number");
                params.add(ids);
                params.add(number);
               
		try {
			//解析request的请求
			//list = servletFileUpload.parseRequest(request);				
			//取出所有表单的值，判断非文本字段和文本字段
//			for(FileItem fileItem : list){
//				if (fileItem.isFormField()) {//是文本字段
//					String fileItemName = fileItem.getFieldName(); //获取 <input>控件的 名称
//					String fileItemValue = fileItem.getString("utf-8");//获取<input>控件的值
//					if (fileItemName.equals("number")) {
//						params.add(fileItemValue); //参数传入 proname
//					}else if (fileItemName.equals("proid")) {
//						params.add(fileItemValue);////参数传入 proINV
//					}					
//				}		
//			}
			// 把产品加入数据库
			boolean flag = service.updateNumber(params);
			if (flag) {
				
				response.sendRedirect(path+"/Cmain.jsp");
			}
				
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
		service = new CProductDao();
	}

}
