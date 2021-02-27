import org.hibernate.cfg.Configuration;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String title;

    @Column
    private int cost;

    public Product(String title, int cost) {

        this.title = title;
        this.cost = cost;
    }



    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getCost() {
        return cost;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Product() {
    }



    public static void create(String title, int cost){
        EntityManager entityManager = getEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(new Product(title , cost));
        entityManager.getTransaction().commit();
    }

    private static EntityManager getEntityManager() {
        EntityManagerFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        return factory.createEntityManager();
    }

    public static Product read(Long id){
        EntityManager entityManager = getEntityManager();

        entityManager.getTransaction().begin();
        Product p = entityManager.find(Product.class, id);
        entityManager.getTransaction().commit();
        return p;
    }

    public static void update(Long id, String title, int cost){
        Product p;
        EntityManager entityManager = getEntityManager();

        entityManager.getTransaction().begin();
        p = read(id);
        p.setTitle(title);
        p.setCost(cost);
        entityManager.merge(p);
        entityManager.getTransaction().commit();
    }

    public static void delete(Long id){
        Product p;
        EntityManager entityManager = getEntityManager();

        entityManager.getTransaction().begin();
        p = read(id);
        entityManager.detach(p);
        entityManager.getTransaction().commit();


    }
}
