package com.gr.grquickrescue.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T, ID extends Serializable> {

	public void saveInstance(T instance);

	public void updateInstance(T instance);

	public T findInstanceById(ID id,final Class<T> type);

	public void deleteInstance(T instance);

	public List<T> findAllInstances(final Class<T> clazz);
}