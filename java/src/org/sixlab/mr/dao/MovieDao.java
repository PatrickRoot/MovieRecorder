package org.sixlab.mr.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.sixlab.mr.beans.MovieRecord;
import org.sixlab.mr.util.GetSession;

public class MovieDao {
	
	public List<MovieRecord> selectMovies(MovieRecord record) {
		SqlSession sqlSession = GetSession.getSession();
		List<MovieRecord> recordList = null;
		try {
			recordList = sqlSession.selectList("selectMovies", record);
			sqlSession.commit();
			sqlSession.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return recordList;
	}
	
	public static void main(String[] args) {
		MovieRecord record = new MovieRecord();
		record.setName("111");
		record.setInDate("20122323");
		new MovieDao().insertMovie(record);
	}
	
	public int insertMovie(MovieRecord record) {
		SqlSession sqlSession = GetSession.getSession();
		try {
			sqlSession.insert("insertMovie", record);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return record.getId();
	}
	
	public void updateMovie(MovieRecord record) {
		SqlSession sqlSession = GetSession.getSession();
		try {
			sqlSession.update("updateMovie", record);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}
}
