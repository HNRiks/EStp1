package newpackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class guardalembrete extends HttpServlet 
{

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet guardalembrete</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet guardalembrete at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        String username = (String) request.getAttribute("username");
        List<lembrete> lembretes = (List<lembrete>) request.getSession().getAttribute("lembretes");
        List<lembrete> resposta = new ArrayList();
        
        if(lembretes != null)
        {
            for(int i = 0; i < lembretes.size(); i++)
            {
                if(lembretes.get(i).getUsername() == username)
                    resposta.add(lembretes.get(i));
            }
        }
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<HTML><BODY>");
        if(resposta != null && !resposta.isEmpty())
        {
            for(int i = 0; i < resposta.size(); i++)
            {
                out.println("");
                out.println("<title>" + resposta.get(i).GetTitle() + "</title>");
                out.println("<P>" + resposta.get(i).GetBody() + "</P>");
                out.println("");
            }
        }
        
        out.println("</BODY></HTML>");
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        
        //Get de atributos (usuário, título e body do lembrete novo, lista total de lembretes da sessão)
        String username = (String) request.getAttribute("username");
        String title = (String) request.getAttribute("title");
        String body = (String) request.getAttribute("body");
        List<lembrete> lembretes = (List<lembrete>) request.getSession().getAttribute("lembretes");
        if(lembretes == null) //Caso a lista ainda não exista, cria uma nova
            lembretes = new ArrayList();
        //Cria um novo lembrete com as informações do request
        lembrete novoLembrete = new lembrete(username, title, body);
        lembretes.add(novoLembrete);
        //Atualiza a lista de lembretes da sessão
        request.getSession().setAttribute("lembretes", lembretes);
    }
    
    @Override
    public String getServletInfo() 
    {
        return "Guarda Lembrete Eletrônico";
    }

}
