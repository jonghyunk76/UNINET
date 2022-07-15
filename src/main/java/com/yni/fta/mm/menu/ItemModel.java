package com.yni.fta.mm.menu;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ItemModel<E> implements Items<E>{
	
	private E[] items;

    public ItemModel(Class<E> clazz, int length)  {
    	@SuppressWarnings("unchecked")
    	final E[] dataArray = (E[]) Array.newInstance(clazz, length);
    	this.items = dataArray;
    }

    public ItemModel(E[] stuff) {
    	this.items = stuff;
	}
    
	@Override
	public String toString() {
		return "{\"result\":"+Arrays.toString(items)+"}";
	}

	public E get(int i) {
        return items[i];
    }

	public int size() {
		return items.length;
	}

	public E[] getItems() {
		return items;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setItems(Object[] items) {
		this.items=(E[]) items;
	}

	
}
