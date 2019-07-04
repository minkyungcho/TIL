package com.mapper;

import java.util.ArrayList;

import com.vo.User;

public interface SearchMapper<K,V> {
	public ArrayList<V> search(K obj);
}



