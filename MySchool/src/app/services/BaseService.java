package app.services;

import java.util.ArrayList;

public interface BaseService {
	String create(Object obj);
	Object update(Object obj, int id);
	Object getOne(int id);
	ArrayList<Object> getMany();
	int delete();
}
