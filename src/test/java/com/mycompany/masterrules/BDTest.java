/*
/*
* Clase pseudo-test para probar la conexión a la base de datos
*
* */
/*
public class BDTest {
    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            // TODO: Modifica el producto, no permite sobreescribir uno existente
            session.persist(new Product(1, "Laptop", "Laptop", BigDecimal.valueOf(1000.00), new BigDecimal(900.00)));
            tx.commit();
//            return true;
        } catch (Exception ex) {
            if (tx != null) {
                tx.rollback();
            }
            ex.printStackTrace();
//            return false;
        } finally {
            session.close();
        }
    }
}

*/