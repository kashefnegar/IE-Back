package servlet;


import models.AllSkills;
import models.MyUser;
import models.Register;
import models.Skills;
import org.json.JSONArray;
import org.json.JSONObject;
import servlet.mytools.Project_page_tool;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/addSkill")
public class UserAddSkill extends MyServlet {
    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        JSONObject resp_massage = new JSONObject();
        MyUser user = MyUser.getInstance();
        ArrayList<Skills> skcan_be_add = this.getskills(user.FindUser(((Register)request.getAttribute("user")).getId()));
        if (skcan_be_add.size() == 0) {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            resp_massage.put("massage", "skill not found");
        } else
            resp_massage.put("skills", new JSONArray(skcan_be_add));

        PrintWriter out = response.getWriter();
        out.print(resp_massage);
//        request.setAttribute("skillsList" , AllSkills.getInstance().getAllskills());

//        RequestDispatcher view=getServletContext().getRequestDispatcher("/UserAddSkill.jsp");
//        view.forward(request,response);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("hi4");
        response.setContentType("application/json");
        JSONObject resp_massage = new JSONObject();
        MyUser user = MyUser.getInstance();
        String newskill = request.getParameter("id");
        System.out.println(newskill);
        if(user.FindUser(((Register)request.getAttribute("user")).getId()).findSkill(newskill)==-1)
        {
            user.FindUser(((Register)request.getAttribute("user")).getId()).addSkills(new Skills(newskill, 0));
        }
        response.setStatus(HttpServletResponse.SC_OK);
        resp_massage.put("massage", "skill added");
        System.out.println("hi5");
        System.out.println(((Register) request.getAttribute("user")).getSkill().size());
        PrintWriter out = response.getWriter();
        out.print(resp_massage);


//         we are asomming we now are users id that is 1;
//        user.FindUser(request.getParameter("id_user")).addSkills(new Skills(newskill, 0));
//        response.sendRedirect("user/1");

    }

    private ArrayList<Skills> getskills(Register user) {
        ArrayList<Skills> skcan_be_add = new ArrayList<>();
        ArrayList<Skills> allSkill = AllSkills.getInstance().getAllskills();
        for (Skills skill : allSkill) {
            if (user.hasSkill(skill)) {
                continue;
            } else skcan_be_add.add(skill);
        }
        return skcan_be_add;
    }
}
