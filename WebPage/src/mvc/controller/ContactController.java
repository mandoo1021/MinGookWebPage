package mvc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.model.ContactDTO;
import mvc.model.ContactDAO;

public class ContactController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final int LISTCOUNT = 5;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		// ex) http://localhost:9000/FinalTest/main.jsp �� URI�ּҿ��� �պκ��� �����ϰ� 
		// /main.jsp�� ����ϱ����� substring()�޼ҵ� ���
		String command = RequestURI.substring(contextPath.length());
		
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		if (command.equals("/ContactListAction.do")) { // ��ϵ� �� ��� ������ ����ϱ�
			requestContactList(request);
			RequestDispatcher rd = request.getRequestDispatcher("./contactAdmin/contactList.jsp");
			rd.forward(request, response);
		} else if (command.equals("/ContactWriteForm.do")) { // �� ��� ������ ����ϱ�
			requestLoginName(request);
			RequestDispatcher rd = request.getRequestDispatcher("./contactAdmin/contactWriteForm.jsp");
			rd.forward(request, response);
		} else if (command.equals("/ContactWriteAction.do")) { // ���ο� �� ����ϱ�
			requestContactWrite(request);
			RequestDispatcher rd = request.getRequestDispatcher("/ContactListAction.do");
			rd.forward(request, response);
		} else if (command.equals("/ContactViewAction.do")) { // ���õ� �� �� ������ ��������
			requestContactView(request);
			RequestDispatcher rd = request.getRequestDispatcher("/ContactView.do");
			rd.forward(request, response);
		} else if (command.equals("/ContactView.do")) { // �� �� ������ ����ϱ�
			RequestDispatcher rd = request.getRequestDispatcher("./contactAdmin/contactView.jsp");
			rd.forward(request, response);
		} else if (command.equals("/ContactDeleteAction.do")) { // ���õ� �� �����ϱ�
			requestContactDelete(request);
			RequestDispatcher rd = request.getRequestDispatcher("/ContactListAction.do");
			rd.forward(request, response);
		} else if (command.equals("/ContactUpdateFormAction.do")) { // ����ȭ������ ���� �� ��������
			requestContactUpdateView(request);
			RequestDispatcher rd = request.getRequestDispatcher("/ContactUpdateForm.do");
			rd.forward(request, response);
		} else if (command.equals("/ContactUpdateForm.do")) { // ����ȭ������ ���� �� ��������
			RequestDispatcher rd = request.getRequestDispatcher("./contactAdmin/contactUpdateForm.jsp");
			rd.forward(request, response);
		} else if (command.equals("/ContactUpdateAction.do")) { // ���� �ϱ�
			requestContactUpdate(request);
			RequestDispatcher rd = request.getRequestDispatcher("/ContactListAction.do");
			rd.forward(request, response);
		}
		
	}

	// ���� ������ 
	private void requestContactUpdateView(HttpServletRequest request) {
		ContactDAO dao = ContactDAO.getInstance();
		int num = Integer.parseInt(request.getParameter("num"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		
		ContactDTO contact = new ContactDTO();
		contact = dao.getUpdateByNum(num, pageNum);
		
		request.setAttribute("contact", contact);
		
	}

	// ��ϵ� �� ��� ��������
	private void requestContactList(HttpServletRequest request) {
		ContactDAO dao = ContactDAO.getInstance();
		List<ContactDTO> contactlist = new ArrayList<ContactDTO>();
		
		int pageNum = 1;
		int limit = LISTCOUNT;
		
		if(request.getParameter("pageNum") != null)
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		
		String items = request.getParameter("items");
		String text = request.getParameter("text");
		
		int total_record = dao.getListCount(items, text);
		
		contactlist = dao.getContactList(pageNum, limit, items, text);
		int total_page;
		
		if (total_record % limit == 0) {
			total_page = total_record/limit;
			// floor() �޼ҵ� : ������ ���ں��� �۰ų� ���� �ִ� ���� ���� ��ȯ
			Math.floor(total_page);
		} else {
			total_page = total_record/limit;
			Math.floor(total_page);
			total_page = total_page + 1;
		}
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("total_page", total_page);
		request.setAttribute("total_record", total_record);
		request.setAttribute("contactlist", contactlist);
	} 
	
	// ������ ����ڸ� ��������
	private void requestLoginName(HttpServletRequest request) {
		String id = request.getParameter("id");
		ContactDAO dao = ContactDAO.getInstance();
		String name = dao.getLoginNameById(id);
		request.setAttribute("name", name);  
	}
	
	// ���ο� �� ����ϱ�
	private void requestContactWrite(HttpServletRequest request) {
		ContactDAO dao = ContactDAO.getInstance();
		
		ContactDTO contact = new ContactDTO();
		contact.setUser_id(request.getParameter("id"));
		contact.setUser_name(request.getParameter("name"));
		contact.setUser_phone(request.getParameter("phone"));
		contact.setSubject(request.getParameter("subject"));
		contact.setContent(request.getParameter("content"));
		
		System.out.println(request.getParameter("name"));
		System.out.println(request.getParameter("subject"));
		System.out.println(request.getParameter("content"));
		// �ۼ����� ������ �����ϱ�����
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss)");
		String regist_day = formatter.format(new java.util.Date());
		
		contact.setRegist_day(regist_day);
		contact.setIp(request.getRemoteAddr());
		
		dao.insertContact(contact);
	}
	
	// ���õ� �� �� ������ ��������
	private void requestContactView(HttpServletRequest request) {
		ContactDAO dao = ContactDAO.getInstance();
		int num = Integer.parseInt(request.getParameter("num"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		
		ContactDTO contact = new ContactDTO();
		contact = dao.getContactByNum(num, pageNum);
		
		request.setAttribute("num", num);
		request.setAttribute("page", pageNum);
		request.setAttribute("contact", contact);
	}
	
	// ���õ� �� ���� �����ϱ�
	private void requestContactUpdate(HttpServletRequest request) {
		int num = Integer.parseInt(request.getParameter("num"));
		
		ContactDAO dao = ContactDAO.getInstance();
		ContactDTO contact = new ContactDTO();
		contact.setNum(num);
		contact.setUser_name(request.getParameter("name"));
		contact.setUser_phone(request.getParameter("phone"));
		contact.setSubject(request.getParameter("subject"));
		contact.setContent(request.getParameter("content"));
		
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String regist_day = formatter.format(new java.util.Date()); 
		
		contact.setRegist_day(regist_day);
		contact.setIp(request.getRemoteAddr());
		
		dao.updateContact(contact);
	}
	
	private void requestContactDelete(HttpServletRequest request) {
		int num = Integer.parseInt(request.getParameter("num"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		
		ContactDAO dao = ContactDAO.getInstance();
		dao.deleteContact(num);
		
	}

}
