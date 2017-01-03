
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import oracle.jdbc.OraclePreparedStatement;

public class CLOBTEST {
public static void main(String args[]) throws SQLException, ClassNotFoundException
{
    Class.forName("oracle.jdbc.driver.OracleDriver"); 
java.sql.Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@oracle.cise.ufl.edu:1521:orcl","anjali","250792lt26i"); 

    String sql = "insert  into songs(lyrics) values (?)";
    OraclePreparedStatement st = (OraclePreparedStatement) connection.prepareStatement(sql);
    st.setStringForClob(1, ip);
st.execute();
}

}
}
