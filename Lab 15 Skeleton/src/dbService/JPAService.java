package dbService;

import java.util.List;
import javax.persistence.*;
import model.User;
import model.Ticket;

public class JPAService {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Lab_15_SkeletonPU");
    EntityManager em = emf.createEntityManager();

   public int findOwnerID(String name) {
       int ownerID=0;
       Query query = em.createQuery("SELECT o.id FROM AddressBookOwner o WHERE o.name=:value",
               User.class).setParameter("value", name);
       try{
       ownerID = (int) query.getSingleResult();
       }
       catch(NoResultException e){
           System.out.println("Nothing found    "+e.getMessage());
       }
       return ownerID;
   }
   
   public boolean findOwner(String name){
       int id = findOwnerID(name);
       boolean found = false;
       User abo = em.find(User.class, id);
       if (abo != null){
           found = true;       
       }
       return found;   
   }
    
    public void printAllTickets(String name){
        int id = findOwnerID(name);
        Query q = em.createNativeQuery("SELECT U.ID, U.NAME, T.TICKET_TYPE, T.TICKET_COST FROM USER U, TICKET T"
                + "\n WHERE U.ID = T.ID\n and U.USER_ID=" +id, Ticket.class);
        List<Ticket> results = q.getResultList();
        
        for (Ticket c : results) {
            System.out.println(c);
        }
    }
    
    public boolean findContact(int cid, String owner) { 
        System. out.println("In here Contact ID" + cid); 
        boolean found = false; 
        int ownerid = findOwnerID(owner); 
     
        Query q = em.createNativeQuery("SELECT C.PNUMBER\n" 
                + "FROM CONTACTS C, CONTACTSADDRESSBOOK CAB\n" 
                + "where C.ID = CAB.CID\n" 
                + "and CAB.ABID = " + ownerid 
                + "and c.id = " + cid); 
        List<Ticket> results = q.getResultList(); 
        // easier than for loop below 
        if (!results.isEmpty()) { found = true; 
        } 
        return found; 
    }
        
        public void updateContact(int id, String newNum) { 
            em.getTransaction().begin();
            Ticket c = em.find(Ticket.class, id); 
//            c.setPhoneNumber(newNum); 
            em.getTransaction().commit(); 
        }
    
        public void removeContact(int id){
            Ticket c = em.find(Ticket.class, id);
            em.getTransaction().begin();
            em.remove(c);
            em.getTransaction().commit();
        }
    
//        public Ticket createContact(String nameAdd, String addAdd, String emailAdd, String numAdd, String owner) { 
//            int id = findOwnerID(owner);
//            User abo = em.find(User.class, id); 
//            em.getTransaction().begin(); 
////            Ticket c = new Ticket(nameAdd, addAdd, emailAdd, numAdd); 
//            c.addStaff(abo); 
//            em.getTransaction().commit(); 
//            return c; 
//        }
    
    
}
