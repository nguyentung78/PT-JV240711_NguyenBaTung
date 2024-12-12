package ra.exam.repository.imp;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ra.exam.model.Clazz;
import ra.exam.repository.ClazzRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ClazzRepositoryImp implements ClazzRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Clazz> findAll() {
        return entityManager.createQuery("from Clazz", Clazz.class).getResultList();
    }

    @Override
    public Clazz findById(int clazzId) {
        return entityManager.createQuery("from Clazz where classId=:id", Clazz.class)
                .setParameter("id", clazzId).getSingleResult();
    }

    @Override
    @Transactional
    public boolean save(Clazz clazz) {
        try {
            entityManager.persist(clazz);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    @Transactional
    public boolean update(Clazz clazz) {
        try {
            entityManager.merge(clazz);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    @Transactional
    public boolean delete(int clazzId) {
        try {
            Clazz clazz = findById(clazzId);
            entityManager.remove(clazz);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}