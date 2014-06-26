package org.sixlab.mr.action;

import java.util.List;
import java.util.Vector;

import org.sixlab.mr.beans.MovieRecord;
import org.sixlab.mr.dao.MovieDao;
import org.sixlab.mr.util.VectorUtil;

public class Action {
	
	public Vector<Vector<String>> insert(MovieRecord record) {
		
		MovieDao dao = new MovieDao();
		
		int id = dao.insertMovie(record);
		
		record.setId(id);
		
		List<MovieRecord> records = dao.selectMovies(record);
		
		return VectorUtil.list2Vector(records);
		
	}
	
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
