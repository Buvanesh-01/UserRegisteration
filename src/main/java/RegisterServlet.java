import java.io.IOException;
import java.sql.*;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public RegisterServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        out.println("Servlet is working!");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");

        PrintWriter out = response.getWriter();

        try {
            // 1. Load Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. Connect to MySQL
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/userdb", "root", "Buvanesh@2004");

            // 3. SQL Insert Query
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO users(name,email,password,phone) VALUES(?,?,?,?)");

            // 4. Set Values
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setString(4, phone);

            // 5. Execute Query
            int result = ps.executeUpdate();

            // 6. Show Result
            if(result > 0)
                out.println("Registration Successful");
            else
                out.println("Registration Failed");

            // 7. Close Connection
            con.close();

        } catch(Exception e) {
            out.println(e);
        }
    }
}