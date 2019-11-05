package app.services;

public interface BaseService {
	Object create(Object obj);
	Object update(Object obj, int id);
	Object getOne(int id);
	Object[] getMany();
	int delete();
}
