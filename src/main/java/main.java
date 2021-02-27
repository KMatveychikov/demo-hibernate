import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class main {

    public static void main(String[] args) {

        Product.create("pants", 500);
        Product.create("west", 700);
        Product.create("socks", 50);
        Product.create("shirt", 300);
        Product.create("boots", 900);

        Product.update(3L,"west", 600);

        System.out.println(Product.read(2L));

        Product.delete(3L);


    }


}
