package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User artem = new User("Артём", "М", "am69@mail.io");
      User ksenia = new User("Ксеня", "Г", "kg88@mail.io");
      User kate = new User("Катя", "Ю", "kyou321@mail.io");
      User alex = new User("Лёха", "М", "lm9999@mail.io");

      Car bmv = new Car("BMW", 34);
      Car audi = new Car("AUDI", 80);
      Car vaz = new Car("VAZ", 2107);
      Car matiz = new Car("MATIZ", 1);

      userService.add(artem.setCar(bmv).setUser(artem));
      userService.add(ksenia.setCar(audi).setUser(ksenia));
      userService.add(kate.setCar(vaz).setUser(kate));
      userService.add(alex.setCar(matiz).setUser(alex));

      for (User user : userService.listUsers()) {
         System.out.println(user + " " + user.getCar());
      }

      System.out.println(userService.getUserByCar("BMW", 34));

      try {
         User notFoundUser = userService.getUserByCar("Merc", 190);
      } catch (NoResultException e) {
         System.out.println("User not found");
      }

      context.close();
   }
}