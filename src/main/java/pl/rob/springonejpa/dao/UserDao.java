package pl.rob.springonejpa.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.rob.springonejpa.model.User;
import pl.rob.springonejpa.model.UserDetails;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
@Transactional
public class UserDao {

    private EntityManager entityManager;

    @Autowired
    public UserDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(User user) {
        UserDetails details = user.getDetails();
        if (details != null && user.getId() == null){
            entityManager.persist(details);
        }
            entityManager.persist(user);
    }

    public User get(Long id) {
        User user = entityManager.find(User.class, id);
        return user;
    }

    public void update(User user) {
        UserDetails details = user.getDetails();
        if (details != null) {
            details = entityManager.merge(details);
            user.setDetails(details);
        }
        entityManager.merge(user);
    }

    public void delete(User user) {
        User userToDelete = entityManager.merge(user);
        entityManager.remove(userToDelete.getDetails());
        entityManager.remove(userToDelete);
    }

}
