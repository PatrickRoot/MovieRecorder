package org.sixlab.mr.util;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.sixlab.mr.beans.MovieRecord;

public class VectorUtil {
	
	public static Vector<Vector<String>> list2Vector(List<MovieRecord> records) {
		
		Vector<Vector<String>> content = new Vector<Vector<String>>();
		
		for (Iterator<MovieRecord> iterator = records.iterator(); iterator
				.hasNext();) {
			MovieRecord record = (MovieRecord) iterator.next();
			Vector<String> vector = new Vector<String>();
			vector.add(String.valueOf(record.getId()));
			vector.add(record.getName());
			vector.add(record.getCountry());
			vector.add(record.getYear());
			vector.add(record.getDirector());
			vector.add(record.getActor());
			vector.add(record.getRemark());
			vector.add(record.getInDate());
			content.add(vector);
		}
		
		return content;
	}
}
