package ra.exam.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.exam.model.Clazz;
import ra.exam.repository.ClazzRepository;
import ra.exam.service.ClazzService;

import java.util.List;

@Service
public class ClazzServiceImp implements ClazzService {
    @Autowired
    private ClazzRepository clazzRepository;

    @Override
    public List<Clazz> findAll() {
        return clazzRepository.findAll();
    }

    @Override
    public Clazz findById(int clazzId) {
        return clazzRepository.findById(clazzId);
    }

    @Override
    public boolean save(Clazz clazz) {
        return clazzRepository.save(clazz);
    }

    @Override
    public boolean update(Clazz clazz) {
        return clazzRepository.update(clazz);
    }

    @Override
    public boolean delete(int clazzId) {
        return clazzRepository.delete(clazzId);
    }
    @Override
    public void deleteClazz(int clazzId) {
        long studentCount = clazzRepository.countStudentsByClassId(clazzId);
        if (studentCount > 0) {
            throw new IllegalStateException("Cannot delete class with assigned students.");
        }
        boolean deleted = clazzRepository.delete(clazzId);
        if (!deleted) {
            throw new RuntimeException("Failed to delete class.");
        }
    }
}
