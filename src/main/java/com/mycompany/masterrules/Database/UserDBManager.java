package com.mycompany.masterrules.Database;

import com.mycompany.masterrules.Model.UserAccount;
import org.hibernate.Session;

import java.util.List;

public final class UserDBManager extends DatabaseManager<UserAccount, String> {

    /**
     * @param id La llave primaria de la entidad
     * @return El usuario con la llave primaria dada, de lo contrario null
     */
    @Override
    public UserAccount findById(String id) {
        try (Session session = HibernateUtil.getOpenSession()) {
            session.beginTransaction();
            UserAccount findUser = session.get(UserAccount.class, id);
            session.getTransaction().commit();
            return findUser;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * @return Todos los usuarios en la base de datos
     */
    @Override
    public List<UserAccount> readAll() {
        Session session = HibernateUtil.getOpenSession();
        try {
            return session.createQuery("from UserAccount", UserAccount.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
}
