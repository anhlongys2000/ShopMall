/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.RauDAO;
import dto.DetailDTO;
import dto.RauDTO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author asus
 */
@WebServlet(name = "AddToCartController", urlPatterns = {"/AddToCartController"})
public class AddToCartController extends HttpServlet {
    private static final String ERROR = "ERROR.jsp";
    private static final String SUCCESS = "Home.jsp";
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        int bid = Integer.parseInt(request.getParameter("bid"));
        RauDAO dao = new RauDAO();
        int quantity =1;
        DetailDTO detailDTO = new DetailDTO();
        ArrayList<DetailDTO> list = null;
        boolean flag =true;
        try {
            RauDTO bookDTO = dao.getListbyID(bid);
            detailDTO = new DetailDTO(0, bid, quantity, 0, bookDTO);
            
            HttpSession session = request.getSession();
            list = (ArrayList<DetailDTO>) session.getAttribute("CART");
            if(list!=null){
                for (int i = 0; i < list.size(); i++) {
                    if(list.get(i).getBook().getRauID()==detailDTO.getBook().getRauID()){
                        int CurQuantity = dao.getQuantityOfBanh(bid);
                        if(list.get(i).getQuantity()+1<=CurQuantity){
                            detailDTO.setQuantity(list.get(i).getQuantity()+1);
                            detailDTO.setPrice(detailDTO.getQuantity() * detailDTO.getBook().getPrice());
                            list.set(i, detailDTO);
                        }else{
                            request.setAttribute("EMPTY", "Sold out already, Please choose anothor!");
                        }
                        flag = false;
                    }
                }
            }else if(list ==null){
                list = new ArrayList<>();
            }
            if(flag){
                list.add(detailDTO);
            }
            session.setAttribute("CART", list);
            url =SUCCESS;
            
        } catch (Exception e) {
            log("Error at AddToCartController");
        }finally{
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
