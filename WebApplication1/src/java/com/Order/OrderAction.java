/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Order;
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
/**
 *
 * @author lenovo
 */
public class OrderAction extends HttpServlet{
        private OrderService service;
        
        public OrderAction() {
		super();
	}

    public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
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
                //String path = request.getContextPath();
		
		String action_flag = request.getParameter("action_flag");
		if (action_flag.equals("add")) {
			addOrder(request,response);
		}else if (action_flag.equals("search")) {
			listOrder(request,response);
		}
                else if (action_flag.equals("view")) {
			viewOrder(request,response);
		}
//                else if(action_flag.equals("finishOrder")){
//                        recordTotal(request,response);
//                }
                else if (action_flag.equals("mostac")) {
			mosttotalOrder(request,response);
		}
                
		out.flush();
		out.close();
	}
        
        private void mosttotalOrder(HttpServletRequest request,
			HttpServletResponse response) {
                 List<Map<String, Object>> list = null;
		String oid = request.getParameter("oid");
		String pageNum = request.getParameter("pageNum");
		String ordName = request.getParameter("username");
		
		int totalRecord = service.getItemCount(ordName); //获取总的记录数
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
                try {
                        int a=1;
                        list = service.mosttotalOrder(ordName);  
			request.setAttribute("listOrder", list);
			request.setAttribute("dividePage", dividePage);
			request.setAttribute("ordName",ordName );
			request.getRequestDispatcher("/mostorder.jsp").forward(request, response); //????
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}		
        }
                
        private void recordTotal(HttpServletRequest request,HttpServletResponse response) 
                throws ServletException, IOException{
		//表单含有文件要提交
		String  path = request.getContextPath();		
		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
		servletFileUpload.setFileSizeMax(3*1024*1024);//单个文件大小限制3M
		servletFileUpload.setSizeMax(6*1024*1024);//上传文件总大小
		List<FileItem> list = null;		
		List<Object> params = new ArrayList<Object>();
		params.add(UUIDTools.getUUID()); // 参数传 product表的主键
                String username = request.getParameter("username");
                params.add(username);
                String sum = request.getParameter("sum");
                params.add(sum);
            
		try {
			//解析request的请求
//			list = servletFileUpload.parseRequest(request);				
			//取出所有表单的值，判断非文本字段和文本字段
//			for(FileItem fileItem : list){
//				if (fileItem.isFormField()) {//是文本字段
//					String fileItemName = fileItem.getFieldName(); //获取 <input>控件的 名称
//					String fileItemValue = fileItem.getString("utf-8");//获取<input>控件的值
//					if (fileItemName.equals("sum")) {
//						params.add(fileItemValue);
//                                        }//参数传入 proname
////					}else if (fileItemName.equals("proprice")) {
////						params.add(fileItemValue);//参数传入 proprice
////					}else if (fileItemName.equals("proaddress")) {
////						params.add(fileItemValue);////参数传入 proaddress
////					}					
//				}else{ //非文本字段					
//					
//					String imageName = fileItem.getName(); //获取文件名称
//					params.add(imageName);//参数传入  proimage			
//					//String path = request.getRealPath("/upload");
//					String upload_dir = request.getServletContext().getRealPath("/upload");//获取服务器端 /upload 路径
//					File uploadFile = new File(upload_dir+"/"+imageName);
//					System.out.println("---upload_dir--->>"+uploadFile);
//					fileItem.write(uploadFile);						
//				}				
//			}
//			
//			// 把产品加入数据库
			boolean flag = service.recordTotal(params);
			if (flag) {
				
				response.sendRedirect(path+"/Cmain.jsp");
			}
				
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
                    
        }

	private void viewOrder(HttpServletRequest request,
			HttpServletResponse response) {
//		// TODO Auto-generated method stub
//		String oid = request.getParameter("oid");
//		Map<String, Object> map = service.viewOrder(oid);
//		request.setAttribute("ordMap", map);//what??????
//		try {
//			request.getRequestDispatcher("/viewOrder.jsp").forward(request, response);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
                List<Map<String, Object>> list = null;
                List<Object> params = new ArrayList<Object>();
                String oid = request.getParameter("oid");
                params.add(oid);
                list = service.viewOrder(params);
                request.setAttribute("listOrder",list);
                
                try{
                        request.setAttribute("listOrder", list);
				
			request.getRequestDispatcher("/viewOrder.jsp").forward(request, response);
                } catch(Exception e){
                    e.printStackTrace();
                }
                
//		
	}

	private void listOrder(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		String ordName = request.getParameter("username");	
		String pageNum = request.getParameter("pageNum");
		System.out.println("参数 pageNum :"+pageNum);
		if (ordName == null) {
			ordName = "";
		}
		
		
		
		int totalRecord = service.getItemCount(ordName); //获取总的记录数
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
                        int a=1;
			list = service.listOrder(ordName , start , end);
			request.setAttribute("listOrder", list);
			request.setAttribute("dividePage", dividePage);
			request.setAttribute("ordName",ordName );
			request.getRequestDispatcher("/main.jsp").forward(request, response); //????
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}		
		
	}
//        private void finishOrder(HttpServletRequest requset, HttpServletResponse response){
//            
//        }

	private void addOrder(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		//表单含有文件要提交
		String  path = request.getContextPath();		
		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
		servletFileUpload.setFileSizeMax(3*1024*1024);//单个文件大小限制3M
		servletFileUpload.setSizeMax(6*1024*1024);//上传文件总大小
		List<FileItem> list = null;		
		List<Object> params1 = new ArrayList<Object>();
                
                String sum=request.getParameter("sum");
                String username=request.getParameter("username");
                String[] proinv=request.getParameterValues("proinv");
                String[] proname=request.getParameterValues("proname");
                String[] proquantity=request.getParameterValues("proquantity");
                
                for(int i=0;i<proquantity.length;i++){
                    boolean flag2=(Integer.parseInt( proquantity[i])>Integer.parseInt(proinv[i]));
                    if(flag2){

                        response.sendRedirect(path+"/outOfStock.jsp");
                        return;
                    }
                }
                                
		String key = UUIDTools.getUUID();
                params1.add(key); // 参数传 product表的主键
                params1.add(username);
                params1.add(sum);
		try {
			//解析request的请求
//			list = servletFileUpload.parseRequest(request);				
//			//取出所有表单的值，判断非文本字段和文本字段
//			for(FileItem fileItem : list){
//				if (fileItem.isFormField()) {//是文本字段
//					String fileItemName = fileItem.getFieldName(); //获取 <input>控件的 名称
//					String fileItemValue = fileItem.getString("utf-8");//获取<input>控件的值
//				if (fileItemName.equals("oid")) {
//						params.add(fileItemValue); //参数传入 proname
//					}else if (fileItemName.equals("username")) {
//						params.add(fileItemValue);//参数传入 proprice
//					}else if (fileItemName.equals("total")) {
//						params.add(fileItemValue);////参数传入 proinv
//					}else if (fileItemName.equals("time")) {
//						params.add(fileItemValue);////参数传入 prokind
//					}		
//                                        
//				}
//			}
                        for(int i = 0; i<proname.length; i++){
                            List<Object> params = new ArrayList<Object>();
                            params.add(key);
                            params.add(proname[i]);
                            params.add(proquantity[i]);
                            boolean flag = service.addDetail(params);
                        }
			
			// 把产品加入数据库
			boolean flag1 = service.addOrder(params1);
			if (flag1) {
				
				response.sendRedirect(path+"/Cmain.jsp");
			}
				
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
        }
        
        private void addDetail(HttpServletRequest request, HttpServletResponse response) 
                throws ServletException, IOException{
		//表单含有文件要提交
		String  path = request.getContextPath();		
		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
		servletFileUpload.setFileSizeMax(3*1024*1024);//单个文件大小限制3M
		servletFileUpload.setSizeMax(6*1024*1024);//上传文件总大小
		List<FileItem> list = null;		
		List<Object> params = new ArrayList<Object>();
		//params.add(UUIDTools.getUUID()); // 参数传 product表的主键
                
                String proname=request.getParameter("proname");
                String proquantity=request.getParameter("proquantity");
                
                
                params.add(proname);
                params.add(proquantity);
		try {
			//解析request的请求
//			list = servletFileUpload.parseRequest(request);				
			//取出所有表单的值，判断非文本字段和文本字段
//			for(FileItem fileItem : list){
//				if (fileItem.isFormField()) {//是文本字段
//					String fileItemName = fileItem.getFieldName(); //获取 <input>控件的 名称
//					String fileItemValue = fileItem.getString("utf-8");//获取<input>控件的值
//				if (fileItemName.equals("oid")) {
//						params.add(fileItemValue); //参数传入 proname
//					}else if (fileItemName.equals("username")) {
//						params.add(fileItemValue);//参数传入 proprice
//					}else if (fileItemName.equals("sum")) {
//						params.add(fileItemValue);////参数传入 proinv
//					}else if (fileItemName.equals("time")) {
//						params.add(fileItemValue);////参数传入 prokind
//					}		
//                                        
//				}
//			}
			
			// 把产品加入数据库
			boolean flag = service.addDetail(params);
			if (flag) {
				
				response.sendRedirect(path+"/Cmain.jsp");
			}
				
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
        }

	public void init() throws ServletException {
		// Put your code here
		service = new OrderDao();
	}

}
