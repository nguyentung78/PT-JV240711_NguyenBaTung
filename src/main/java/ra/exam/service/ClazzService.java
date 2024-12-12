package ra.exam.service;

import ra.exam.model.Clazz;

import java.util.List;

public interface ClazzService {
    List<Clazz> findAll();
    Clazz findById(int clazzId);
    boolean save(Clazz clazz);
    boolean update(Clazz clazz);
    boolean delete(int clazzId);
}