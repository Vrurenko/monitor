package servlet;

import com.google.gson.Gson;
import model.Question;
import service.contract.IQuestionService;
import service.contract.ISchedulerService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Inject
    private ISchedulerService schedulerService;

    @Inject
    private IQuestionService questionService;


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Question> list = questionService.getQuestionsList();
        request.setAttribute("questions", list);
        request.setCharacterEncoding("UTF-8");
        request.getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Gson gson = new Gson();
        StringBuilder stringBuilder = new StringBuilder();
        String string;

        while ((string = request.getReader().readLine()) != null) {
            stringBuilder.append(string);
        }

        Question question = gson.fromJson(stringBuilder.toString(), Question.class);

        questionService.updateProperties(question);

        schedulerService.reScheduleTasks();

        response.setContentType("text/plain");
        response.getWriter().write("Successful");
    }


}