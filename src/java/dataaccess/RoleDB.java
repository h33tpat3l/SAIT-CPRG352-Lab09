package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import models.Role;
/**
 *
 * @author Alex Tompkins - 821984
 */
public class RoleDB {
    public List<Role> getAll() throws Exception {
        EntityManager entityMgr = DBUtil.getEmFactory().createEntityManager();
        try {
            List<Role> roleList = entityMgr.createNamedQuery("Role.findAll", Role.class).getResultList();
            return roleList;
        } finally {
            entityMgr.close();
        }
    }
    
    public Role getRole(String email){
         EntityManager entityMgr = DBUtil.getEmFactory().createEntityManager();
         try{
             Role role = entityMgr.find(Role.class, email);
             return role;
         }finally{
             entityMgr.close();
         }
    }
}
