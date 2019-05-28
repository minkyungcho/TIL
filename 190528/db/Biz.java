package db;

public abstract class Biz {
	public abstract void register(Object obj); // insert
	public abstract void remove(Object obj); // delete
	public abstract Object get(Object obj); // select
	
}
