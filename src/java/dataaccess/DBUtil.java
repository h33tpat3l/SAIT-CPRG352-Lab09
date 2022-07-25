package dataaccess;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author heetk
 */
public class DBUtil {
    
    private static final EntityManagerFactory entityMgrFactory = Persistence.createEntityManagerFactory("Lab_09PU");

    public static EntityManagerFactory getEmFactory() {
        return entityMgrFactory;
    }
}
