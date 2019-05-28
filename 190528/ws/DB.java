package ws;

import java.util.ArrayList;

public abstract class DB<T,U> { // T:User, U:String
	public abstract void Create(T obj);
	public abstract T Select(U obj);
	public abstract ArrayList<T> Select();
	public abstract void Update(T obj);
	public abstract void Delete(T obj);
	
}
