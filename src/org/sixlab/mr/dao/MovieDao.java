package org.sixlab.mr.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.sixlab.mr.beans.MovieRecord;
import org.sixlab.mr.util.GetSession;

public class MovieDao {
	public List<MovieRecord> selectMoviesByID(int id) {
		SqlSession sqlSession = GetSession.getSession();
		List<MovieRecord> recordList = null;
		try {
			recordList = sqlSession.selectList("selectMoviesByID", id);
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
		MovieDao mDao=new MovieDao();
		System.out.println(mDao.selectMoviesByID(2).get(0).getName());
	}
	
	public List<MovieRecord> selectMoviesByDate(String date) {
		SqlSession sqlSession = GetSession.getSession();
		List<MovieRecord> recordList = null;
		try {
			recordList = sqlSession.selectList("selectMoviesByDate", date);
			sqlSession.commit();
			sqlSession.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return recordList;
	}
	
	public List<MovieRecord> selectMovies(MovieRecord record) {
		SqlSession sqlSession = GetSession.getSession();
		List<MovieRecord> recordList = null;
		try {
			recordList = sqlSession.selectList("selectMovies",record);
			sqlSession.commit();
			sqlSession.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return recordList;
	}
	
	public void insertMovie() {
		SqlSession sqlSession = GetSession.getSession();
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}
	
	public void updateMovie() {
		SqlSession sqlSession = GetSession.getSession();
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}
}
