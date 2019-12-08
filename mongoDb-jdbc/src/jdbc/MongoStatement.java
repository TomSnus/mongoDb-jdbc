package jdbc;

import java.sql.*;
import java.util.*;

import com.mongodb.*;

public class MongoStatement implements Statement {
	
	MongoConnection conn;

	final int type;
	final int concurrency;
	final int holdability;

	int fetchSize = 0;
	int maxRows = 0;

	MongoResultSet last;
	
	MongoStatement(MongoConnection conn, int type, int concurrency, int holdability) {
		this.conn = conn;
		this.type = type;
		this.concurrency = concurrency;
		this.holdability = holdability;

		if (type != 0)
			throw new UnsupportedOperationException("type not supported yet");
		if (concurrency != 0)
			throw new UnsupportedOperationException("concurrency not supported yet");
		if (holdability != 0)
			throw new UnsupportedOperationException("holdability not supported yet");

	}

	// --- batch ---

	public void addBatch(String sql) {
		throw new UnsupportedOperationException("batch not supported");
	}

	public void clearBatch() {
		throw new UnsupportedOperationException("batch not supported");
	}

	public int[] executeBatch() {
		throw new UnsupportedOperationException("batch not supported");
	}

	// --- random

	public void cancel() {
		throw new RuntimeException("not supported yet - can be");
	}

	public void close() {
		conn = null;
	}

	public Connection getConnection() {
		return conn;
	}

	public boolean isClosed() {
		return conn == null;
	}

	public boolean isPoolable() {
		return true;
	}

	public void setPoolable(boolean poolable) {
		if (!poolable)
			throw new RuntimeException("why don't you want me to be poolable?");
	}

	public void clearWarnings() {
		throw new RuntimeException("not supported yet - can be");
	}

	// --- writes ----

	public boolean execute(String sql) {
		throw new RuntimeException("execute not done");
	}

	public boolean execute(String sql, int autoGeneratedKeys) {
		throw new RuntimeException("execute not done");
	}

	public boolean execute(String sql, int[] columnIndexes) {
		throw new RuntimeException("execute not done");
	}

	public boolean execute(String sql, String[] columnNames) {
		throw new RuntimeException("execute not done");
	}

	public int executeUpdate(String sql) throws SQLException {
		return new Executor(conn.db, sql).writeop();
	}

	public int executeUpdate(String sql, int autoGeneratedKeys) {
		throw new RuntimeException("executeUpdate not done");
	}

	public int executeUpdate(String sql, int[] columnIndexes) {
		throw new RuntimeException("executeUpdate not done");
	}

	public int executeUpdate(String sql, String[] columnNames) {
		throw new RuntimeException("executeUpdate not done");
	}

	public int getUpdateCount() {
		throw new RuntimeException("getUpdateCount not done");
	}

	public ResultSet getGeneratedKeys() {
		throw new RuntimeException("getGeneratedKeys notn done");
	}

	// ---- reads -----

	public ResultSet executeQuery(String sql) throws SQLException {
		// TODO
		// handle max rows

		DBCursor cursor = new Executor(conn.db, sql).query();
		if (fetchSize > 0)
			cursor.batchSize(fetchSize);
		if (maxRows > 0)
			cursor.limit(maxRows);

		last = new MongoResultSet(cursor);
		return last;
	}

	public int getQueryTimeout() {
		throw new RuntimeException("query timeout not done");
	}

	public void setQueryTimeout(int seconds) {
		throw new RuntimeException("query timeout not done");
	}

	// ---- fetch modifiers ----

	public int getFetchSize() {
		return fetchSize;
	}

	public void setFetchSize(int rows) {
		fetchSize = rows;
	}

	public int getMaxRows() {
		return maxRows;
	}

	public void setMaxRows(int max) {
		maxRows = max;
	}

	public int getFetchDirection() {
		throw new RuntimeException("fetch direction not done yet");
	}

	public void setFetchDirection(int direction) {
		throw new RuntimeException("fetch direction not done yet");
	}

	public int getMaxFieldSize() {
		throw new RuntimeException("max field size not supported");
	}

	public void setMaxFieldSize(int max) {
		throw new RuntimeException("max field size not supported");
	}

	public boolean getMoreResults() {
		throw new RuntimeException("getMoreResults not supported");
	}

	public boolean getMoreResults(int current) {
		throw new RuntimeException("getMoreResults not supported");
	}

	public ResultSet getResultSet() {
		return last;
	}

	// ---- more random -----

	public SQLWarning getWarnings() {
		throw new UnsupportedOperationException("warning not supported yet");
	}

	public void setCursorName(String name) {
		throw new UnsupportedOperationException("can't set cursor name");
	}

	public void setEscapeProcessing(boolean enable) {
		if (!enable)
			throw new RuntimeException("why do you want to turn escape processing off?");
	}

	public int getResultSetConcurrency() {
		return concurrency;
	}

	public int getResultSetHoldability() {
		return holdability;
	}

	public int getResultSetType() {
		return type;
	}

	public <T> T unwrap(Class<T> iface) throws SQLException {
		throw new UnsupportedOperationException();
	}

	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void closeOnCompletion() throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isCloseOnCompletion() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
}