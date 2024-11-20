package com.mycompany.masterrules.Database;

import com.mycompany.masterrules.Model.users.UserAccount;
import org.hibernate.Session;

import java.util.List;

public final class UserDatabase extends Database<String, UserAccount> {

    @Override
    public UserAccount findById(String id) {
        Session session = HibernateUtil.getOpenSession();
        try {
            session.beginTransaction();
            UserAccount findUser = session.get(UserAccount.class, id);
            session.getTransaction().commit();
            return findUser;
        } catch (Exception ex) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            System.err.println("Error al buscar el usuario: " + ex);
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public List<UserAccount> readAll() {
        Session session = HibernateUtil.getOpenSession();
        try {
            session.beginTransaction();
            List<UserAccount> users = session.createQuery("from UserAccount", UserAccount.class).list();
            session.getTransaction().commit();
            return users;
        } catch (Exception ex) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            System.err.println("Error al leer los usuarios: " + ex);
        } finally {
            session.close();
        }
        return List.of();
    }
}
