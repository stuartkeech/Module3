// Created by Chin Han Chen on 2018/08/15

package com;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import java.util.*;

@MultipartConfig
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Database DB = new Database(); // Remove after integration
		
		// Create the session
		// created by Chin Han Chen on 2018/08/17
		HttpSession session = request.getSession();
		try {
			if(request.getParameter("pageID").equals("1")) {
				session.setAttribute("uname",request.getParameter("uname"));
				response.sendRedirect("Menu.jsp");
		
			}
		}catch(Exception e) {
			
		}
		
		 // module for determining for validating claim input (includes @MultipartConfig outside class)
 		// created by Chin Han Chen on 2018/08/16
 		/*int polMid = Integer.parseInt(request.getParameter("policy map id"));
 		int manid = Integer.parseInt(request.getParameter("manager id"));*/
 		if(/*Indicate what type of claim*/) {
 			// other webpages
 		}else if(/*deathclaim*/) {
 			// success
 			DB.createConnection();
 			if(Validation.checkImage(request.getParameter("request for death certificate form name"))) {
 				Part filePart = request.getPart("image");
 				DB.inputData(polMid,new java.util.Date(),manid, request.getParameter("claim type name"), null, filePart);
 				// request.setAttribute("name", "value");
 				request.getRequestDispatcher("success input webpage").forward(request, response);
 			}else {
 				// request.setAttribute("name", "value");
 				request.getRequestDispatcher("failure input return to claim jsp").forward(request, response);
 			}
 			DB.destroyConnection();
 		}else if(/*mature*/) {
 			// success
 			DB.createConnection();
 			if(DB.checkDate("request for Policy Map ID ")) {
 				DB.inputData(polMid,new java.util.Date(),manid, request.getParameter("claim type name"), null, null);
 				// request.setAttribute("name", "value");
 				request.getRequestDispatcher("success input webpage").forward(request, response);
 			}else {
 				// request.setAttribute("name", "value");
 				request.getRequestDispatcher("failure input return to claim jsp").forward(request, response);
 			}
 			DB.destroyConnection();
 		}else if(/*close*/) {
 			// success
 			DB.createConnection();
 			if(Validation.checkInjection("request for textArea form name")) {
 				DB.inputData(polMid,new java.util.Date(),manid, request.getParameter("claim type name"), request.getParameter("textArea name"), null);
 				// request.setAttribute("name", "value");
 				request.getRequestDispatcher("success input webpage").forward(request, response);
 			}else {
 				// request.setAttribute("name", "value");
 				request.getRequestDispatcher("failure input return to claim jsp").forward(request, response);
 			}
 			DB.destroyConnection();
 		}
 		
 		// module for inserting reason for rejection into PolicyMap Table 
 		// created by Chin Han Chen on 2018/08/16
 		if(/*Condition where leads you to submit claim*/) {
 			if(Validation.checkInjection(request.getParameter("Rejection"))) {
 				DB.createConnection();
 				DB.inputRejectionReason(/*Confirm the PolicyMap ID*/,request.getParameter("Rejection"));
 				DB.inputRejectionStatus(/*Confirm the PolicyMap ID*/,request.getParameter("Rejection"));
 				DB.destroyConnection();
 			}else {
 				request.setAttribute("fail", "fail");
 				RequestDispatcher dis1 = request.getRequestDispatcher("/ClaimRejection.jsp");
 				dis1.include(request, response);
 			}
 		}
 		
 		// module for confirming claim person and policy owner are the same
 		// created by Chin Han Chen on 2018/08/16
 		if(/*Condition where leads you to confirming Claim Person and Policy Owner*/) {
 			DB.createConnection();
 			if(DB.checkOwner(request.getParameter("cusID"),/*request policy map id*/)) {
 				DB.destroyConnection();
 				/*forward to whatever place comes after successful validation...*/
 			}else {
 				DB.destroyConnection();
 				request.setAttribute("fail", "fail");
 				RequestDispatcher dis2 = request.getRequestDispatcher("/CheckClaimer.jsp");
 				dis2.include(request, response);
 			}
 		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
