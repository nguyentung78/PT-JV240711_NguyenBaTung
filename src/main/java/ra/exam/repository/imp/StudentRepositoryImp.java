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
    public Student findById(int id) {
        return entityManager.find(Student.class, id);
    }

    @Transactional
    @Override
    public boolean save(Student student) {
        try {
            entityManager.persist(student);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Transactional
    @Override
    public boolean update(Student student) {
        try {
            entityManager.merge(student);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Transactional
    @Override
    public boolean delete(int studentId) {
        try {
            Student student = findById(studentId);
            if (student != null) {
                entityManager.remove(student);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean existsByStudentName(String studentName) {
        String query = "SELECT COUNT(s) FROM Student s WHERE s.studentName = :studentName";
        Long count = entityManager.createQuery(query, Long.class)
                .setParameter("studentName", studentName)
                .getSingleResult();
        return count > 0;
    }

    @Override
    public boolean existsByPhoneNumber(String phoneNumber) {
        String query = "SELECT COUNT(s) FROM Student s WHERE s.phoneNumber = :phoneNumber";
        Long count = entityManager.createQuery(query, Long.class)
                .setParameter("phoneNumber", phoneNumber)
                .getSingleResult();
        return count > 0;
    }

    @Override
    public boolean existsByEmail(String email) {
        String query = "SELECT COUNT(s) FROM Student s WHERE s.email = :email";
        Long count = entityManager.createQuery(query, Long.class)
                .setParameter("email", email)
                .getSingleResult();
        return count > 0;
    }
}
