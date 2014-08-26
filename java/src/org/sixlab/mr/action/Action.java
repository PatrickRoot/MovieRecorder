package org.sixlab.mr.action;

import java.util.List;
import java.util.Vector;

import org.sixlab.mr.beans.MovieRecord;
import org.sixlab.mr.dao.MovieDao;
import org.sixlab.mr.util.VectorUtil;

public class Action {
	
	/** 
	 * 插入一条记录，并返回相似的记录
	 * @param record 要插入的一条记录
	 * @return 返回所有相似记录
	 */
	public Vector<Vector<String>> insert(MovieRecord record) {
		
		MovieDao dao = new MovieDao();
		
		int id = dao.insertMovie(record);
		
		record.setId(id);
		
		List<MovieRecord> records = dao.selectMovies(record);
		
		return VectorUtil.list2Vector(records);
		
	}
	
	/**
	 * 更新一条记录，并返回相似记录
	 * @param record 要更新的记录
	 * @return 返回相似的记录 
	 */
	public Vector<Vector<String>> update(MovieRecord record) {
		
		MovieDao dao = new MovieDao();
		
		dao.updateMovie(record);
		
		List<MovieRecord> records = dao.selectMovies(record);
		
		return VectorUtil.list2Vector(records);
	}
	
	public Vector<Vector<String>> search(MovieRecord record) {
		
		MovieDao dao = new MovieDao();
		
		List<MovieRecord> records = dao.selectMovies(record);
		
		return VectorUtil.list2Vector(records);
	}
	
}
