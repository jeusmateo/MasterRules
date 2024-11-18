package com.mycompany.masterrules.Database;

import com.mycompany.masterrules.Model.users.UserAccount;
import org.hibernate.Session;

import java.util.List;

public final class UserDBManager extends DatabaseManager<UserAccount, String> {

    /**
     * @param id La llave primaria de la entidad
     * @return El usuario con la llave primaria dada, de lo contrario null
     */
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
        }
        finally {
            session.close();
        }
    }

    /**
     * Busca un usuario por su nombre de usuario.
     *
     * @param userName El nombre de usuario.
     * @return El usuario con el nombre de usuario dado, o null si no existe.
     */
    public UserAccount findByUserName(String userName) {
        Session session = HibernateUtil.getOpenSession();
        try {
            session.beginTransaction();
            // Consulta HQL para buscar el usuario por userName
            UserAccount user = session.createQuery("FROM UserAccount WHERE userName = :userName", UserAccount.class)
                    .setParameter("userName", userName)
                    .uniqueResult();
            session.getTransaction().commit();
            return user;
        } catch (Exception ex) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            System.err.println("Error al buscar el usuario por nombre de usuario: " + ex);
            return null;
        } finally {
            session.close();
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
        } catch (Exception ex) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            System.err.println("Error al leer los usuarios: " + ex);
            return null;
        } finally {
            session.close();
        }
    }
}
