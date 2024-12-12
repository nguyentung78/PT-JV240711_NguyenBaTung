package ra.exam.repository;

import ra.exam.model.Clazz;

import java.util.List;

public interface ClazzRepository {
    List<Clazz> findAll();
    Clazz findById(int clazzId);
    boolean save(Clazz clazz);
    boolean update(Clazz clazz);
    boolean delete(int clazzId);
    long countStudentsByClassId(int clazzId);

}