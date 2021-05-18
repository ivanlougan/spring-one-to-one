package pl.rob.springonejpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import pl.rob.springonejpa.dao.UserDao;
import pl.rob.springonejpa.model.User;
import pl.rob.springonejpa.model.UserDetails;

@SpringBootApplication
public class SpringOneJpaApplication {

    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext ctx = SpringApplication.run(SpringOneJpaApplication.class, args);

        UserDao userDao = ctx.getBean(UserDao.class);
		User user = new User("robizdebi", "wdupeczaslo", "robizdebi@gmail.com");
		// saving object without details
		userDao.save(user);


		// adding details to an object
		UserDetails details = new UserDetails("Robercik", "Zdebik", "333 London Road");
		user.setDetails(details);
		userDao.update(user);


		// updating password and firstName
		user.setPassword("maslomlaslo");
		user.getDetails().setFirstName("Andrzejos");
		userDao.update(user);


		// retrieving object from db
		User userFromDB = userDao.get(1L);
		System.out.println(userFromDB);


		// deleting object from db
		userDao.delete(user);


		// retrieving object from db again
		userFromDB = userDao.get(1L);
		System.out.println(userFromDB);


		ctx.close();


	}

}
