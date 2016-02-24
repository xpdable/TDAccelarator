package xpxpxp.top.tdaccelarator;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONObject;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	String db = "";
    	String table = "";
    	
//        System.out.println( "Hello TD!" );
    	if(args[0] == null || args[1] == null){
    		//backdoor for myself
        	db = "C:/Program Files (x86)/Thunder Network/Thunder/Profiles/TaskDB.dat";
        	table = "AccelerateTaskMap27566689_superspeed_1_1";
    		
    	}else{
    		db = args[0];
    		table = args[1];
    	}
    	
        
        Connection connection = null;
        
        try {
        	
        	Map<Long,String> tasks = new HashMap<>();
        	
			Class.forName("org.sqlite.JDBC");
			
//			DriverManager.registerDriver( new org.sqlite.JDBC());
			
			connection = DriverManager.getConnection("jdbc:sqlite:"+db);
			
			Statement statement = connection.createStatement();
			
			ResultSet rs = statement.executeQuery("select * from "+table);
			
			while(rs.next()){
				byte[] b = rs.getBytes(4);
				String s = new String(b);
				System.out.println("old->"+s);
				
				JSONObject j = new JSONObject(s);
				if(j.getLong("Result")!=0){
					j.put("Result", 0);
				}
				String newS = j.toString();
				tasks.put(rs.getLong(1), newS);
				System.out.println("new->"+newS);
			}
			
			connection.close();
			
			String updateSQL = "UPDATE "+table+" SET UserData = ? WHERE LocalTaskId = ";
			
			for(Entry<Long,String> entry : tasks.entrySet()){
				try {
					Connection conn = DriverManager.getConnection("jdbc:sqlite:"+db);
					conn.setAutoCommit(false);
					PreparedStatement ps = conn.prepareStatement(updateSQL+entry.getKey());
					ps.setBytes(1, entry.getValue().getBytes());
					ps.executeUpdate();
					conn.commit();
					conn.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.err.println(e);
				}
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
		} finally {
			try{
				if( connection != null){
					connection.close();
				}
			} catch( SQLException e){
				System.err.println(e);
			}
		}
        
        
        
        
    }
}
