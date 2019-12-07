
import java.net.UnknownHostException;
import java.util.List;

import com.mongodb.*;


public class Test {

	public static void main(String[] args) {
		Mongo mongo;
		try {
			mongo = new Mongo();
			DB db = mongo.getDB( "test" );
			
			
			String name = "col1";
	        DBCollection c = db.getCollection( name );
	        
	        List<String> dbs = mongo.getDatabaseNames();
	        for(String d : dbs)
	        {
	        	System.out.println(d);
	        }
	        
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MongoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}

}
