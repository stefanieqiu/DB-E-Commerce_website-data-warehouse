package com.store;

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

public class StoreAction extends HttpServlet {

	private StoreService service;
	/**
	 * Constructor of the object.
	 */
	public StoreAction() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
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
		
		String action_flag = request.getParameter("action_flag");
		if (action_flag.equals("add")) {
			addStore(request,response);
		}else if (action_flag.equals("search")) {
			listStore(request,response);
		}else if (action_flag.equals("del")) {
			delStore(request,response);
		}
                //else if (action_flag.equals("view")) {
		//	viewStore(request,response);
		//}
                else if (action_flag.equals("update")) {
			updateStore(request,response);
		}
		
		
		out.flush();
		out.close();
	}
/*
	private void viewStore(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		String sid = request.getParameter("sid");
		Map<String, Object> map = service.viewStore(sid);
		request.setAttribute("storeMap", map);
		try {
			request.getRequestDispatcher("/viewstore.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
*/
	/**批量删除产品
	 * @param request
	 * @param response
	 */
	private void delStore(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		System.out.println("进入del");
		//获得复选框的值
		String[] ids = request.getParameterValues("ids");
		for (int i = 0; i < ids.length; i++) {
			System.out.println("ids["+i+"]="+ids[i]);
		}
		boolean flag = service.delStore(ids);
		System.out.println("删除flag:"+flag);
		if (flag) {
			try {
				request.getRequestDispatcher("/store.jsp").forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
	}

	private void listStore(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		String productName = request.getParameter("sid");	
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
			list = service.listStore(productName , start , end);
			request.setAttribute("listStore", list);
			request.setAttribute("dividePage", dividePage);
			request.setAttribute("productName",productName );
			request.getRequestDispatcher("/main.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}		
		
	}

	private void addStore(HttpServletRequest request, HttpServletResponse response) 
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
		try {
			//解析request的请求
			list = servletFileUpload.parseRequest(request);				
			//取出所有表单的值，判断非文本字段和文本字段
			for(FileItem fileItem : list){
				if (fileItem.isFormField()) {//是文本字段
					String fileItemName = fileItem.getFieldName(); //获取 <input>控件的 名称
					String fileItemValue = fileItem.getString("utf-8");//获取<input>控件的值
					if (fileItemName.equals("sid")) {
						params.add(fileItemValue); //参数传入 proname
					}else if (fileItemName.equals("zip")) {
						params.add(fileItemValue);//参数传入 proprice
					}else if (fileItemName.equals("num")) {
						params.add(fileItemValue);////参数传入 proinv
					}else if (fileItemName.equals("rname")) {
						params.add(fileItemValue);////参数传入 prokind
					}		
                                        
				}		
			}
			
			// 把产品加入数据库
			boolean flag = service.addStore(params);
			if (flag) {
				
				response.sendRedirect(path+"/store.jsp");
			}
				
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		
	}
        private void updateStore(HttpServletRequest request, HttpServletResponse response) 
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
		try {
			//解析request的请求
			list = servletFileUpload.parseRequest(request);				
			//取出所有表单的值，判断非文本字段和文本字段
			for(FileItem fileItem : list){
				if (fileItem.isFormField()) {//是文本字段
					String fileItemName = fileItem.getFieldName(); //获取 <input>控件的 名称
					String fileItemValue = fileItem.getString("utf-8");//获取<input>控件的值
					if (fileItemName.equals("num")) {
						params.add(fileItemValue); //参数传入 proname
					}else if (fileItemName.equals("sid")) {
						params.add(fileItemValue);////参数传入 proINV
					}					
				}		
			}
			// 把产品加入数据库
			boolean flag = service.updateStore(params);
			if (flag) {
				
				response.sendRedirect(path+"/main.jsp");
			}
				
			
		} catch (Exception e) {
			// TODO: handle exception
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
		service = new StoreDao();
	}

}
