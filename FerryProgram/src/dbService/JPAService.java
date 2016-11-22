package dbService;

import java.util.List;
import javax.persistence.*;
import model.User;
import model.Ticket;

public class JPAService {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("FerryProgram_PU");
    EntityManager em = emf.createEntityManager();

   public int findUserID(String username) {
       int userID=0;
       Query query = em.createQuery("SELECT u.user_ID FROM User u WHERE u.username=:value",
               User.class).setParameter("value", username);
       try{
       userID = (int) query.getSingleResult();
       }
       catch(NoResultException e){
           System.out.println("Nothing found    "+e.getMessage());
       }
       return userID;
   }
   
   public boolean findUser(String name){
       int id = findUserID(name);
       boolean found = false;
       User currentUser = em.find(User.class, id);
       if (currentUser != null){
           found = true;       
       }
       return found;
       
   }
    
    public void printAllTickets(String name){
        int id = findUserID(name);
        Query q = em.createNativeQuery("SELECT T.ticket_type, T.ticket_cost FROM Booking B, Ticket T"
                + "\n WHERE B.user_ID = T.ticket_ID and B.ticket_ID = "+id, Ticket.class);
        List<Ticket> results = q.getResultList();
        
        for (Ticket c : results) {
            System.out.println(c);
        }
    }
    
    public boolean findContact(int cid, String user) { 
        System. out.println("In here Contact ID" + cid); 
        boolean found = false; 
        int userid = findUserID(user); 
     
        Query q = em.createNativeQuery("SELECT C.PNUMBER\n" 
                + "FROM CONTACTS C, CONTACTSADDRESSBOOK CAB\n" 
                + "where C.ID = CAB.CID\n" 
                + "and CAB.ABID = " + userid 
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
    
        public User createUser(String username, String name, String DOB) { 
            
            User u = em.find(User.class, id); 
            em.getTransaction().begin(); 
            User u = new User(nameAdd, addAdd, emailAdd, numAdd); 
            c.addStaff(abo); 
            em.getTransaction().commit(); 
            return c; 
        }
    
    
}
