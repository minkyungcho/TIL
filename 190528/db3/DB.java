package db3;

public abstract class DB<T,U> { // T:User, U:String
	public abstract void insert(T obj);
	public abstract T Select(U obj);
}
