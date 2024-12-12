package ra.exam.repository.imp;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ra.exam.model.Student;
import ra.exam.repository.StudentRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class StudentRepositoryImp implements StudentRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Student> findAll() {
        return entityManager.createQuery("from Student", Student.class).getResultList();
    }

    @Override
    public Student findById(int studentId) {
        return entityManager.find(Student.class, studentId);
    }

    @Override
    @Transactional
    public boolean save(Student student) {
        try {
            entityManager.persist(student);
            return true;
        } catch (Exception ex) {
            throw new RuntimeException("Error persisting student: " + ex.getMessage());
        }
    }


    @Override
    @Transactional
    public boolean update(Student student) {
        try {
            entityManager.merge(student);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    @Transactional
    public boolean delete(int studentId) {
        try {
            Student student = findById(studentId);
            if (student != null) {
                entityManager.remove(student);
                return true;
            }
            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Student> findByClassId(int classId) {
        return entityManager.createQuery("from Student where studentClass.classId = :classId", Student.class)
                .setParameter("classId", classId)
                .getResultList();
    }
}
