package test;

import static org.junit.jupiter.api.Assertions.*;

import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.Test;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

import jdbc.MongoConnection;

class mongoTest {

	@Test
	void test() throws SQLException {
		Mongo mongo = null;
		try {
			mongo = new Mongo();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MongoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DB db = mongo.getDB("test");
		MongoConnection conn = new MongoConnection(db);

		String name = "col1";
		DBCollection c = db.getCollection(name);

		DBCollection coll = db.getCollection(name);
		coll.drop();

		coll.insert(BasicDBObjectBuilder.start("x", 1).add("y", "foo").get());
		coll.insert(BasicDBObjectBuilder.start("x", 2).add("y", "bar").get());

		Statement stmt = conn.createStatement();

		ResultSet res = stmt.executeQuery("select * from " + name + " order by x");
		assertTrue(res.next());
		assertEquals(1, res.getInt("x"));
		assertEquals("foo", res.getString("y"));
		assertTrue(res.next());
		assertEquals(2, res.getInt("x"));
		assertEquals("bar", res.getString("y"));
		assertFalse(res.next());
		res.close();
	}

}
