/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DataAccessException;
import model.MenuListService;
/**
 *
 * @author mashit
 */
@WebServlet(name = "MenuOrderController", urlPatterns = {"/Menu"})
public class MenuOrderController extends HttpServlet{
    public static final String MENU = "/RestaurantMenu.jsp",
                               ERR = "Error",
                               ERR_MSG = "error connecting to dao",
                               ITEM="item";
    
    
    MenuListService mos;
    public MenuOrderController(){
        super();
    }
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException, DataAccessException {
        request.getParameter(ITEM);
    	mos = new MenuListService();
        if(request.getParameter("submitOrder") != null){
            
        }else{
        getMenuList(request);
        }
        // Redirect to the appropriate sub-controller
        RequestDispatcher dispatcher = 
                getServletContext().getRequestDispatcher(MENU);
        dispatcher.forward(request, response);

    }

    
    
    public void getMenuList(HttpServletRequest request){
        try{
            mos.getCurrentMenuItemList(request);
        }catch (DataAccessException e){
           request.setAttribute(ERR, ERR_MSG);
        }
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
    @Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        try {
            // Call the convenience method, or write your own code here
            processRequest(request, response);
        } catch (DataAccessException ex) {
            Logger.getLogger(MenuOrderController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    @Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        try {
            // Call the convenience method, or write your own code here
            processRequest(request, response);
        } catch (DataAccessException ex) {
            Logger.getLogger(MenuOrderController.class.getName()).log(Level.SEVERE, null, ex);
        }
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occure
	 */
    @Override
	public void init() throws ServletException {
		// this would be used to initialize any of the servlet's
                // global properties, if they exit at all
	}
}
