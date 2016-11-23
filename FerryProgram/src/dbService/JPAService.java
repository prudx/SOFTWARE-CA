package dbService;

import java.util.List;
import javax.persistence.*;
import model.User;
import model.Ticket;

public class JPAService {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("FerryProgram_PU");
    EntityManager em = emf.createEntityManager();
    
    public User createUser(String username, String password, String name, String DOB){
        em.getTransaction().begin(); 
        User u = new User(username, password, name, DOB); 
        em.persist(u);
        em.getTransaction().commit(); 
            return u; 
    }

    public int findUserID(String username) {
       int userID=0;
       Query query = em.createQuery("SELECT u.user_ID FROM User u WHERE u.username=:value",
               User.class).setParameter("value", username);
       try{
       userID = (int) query.getSingleResult();
       }
       catch(NoResultException e){
           System.out.println("Nothing found");
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
        Query q = em.createNativeQuery("SELECT T.ticket_id, T.ticket_type, T.ticket_cost, "
                + "R.route_name, R.route_id "
                + "FROM Ticket T, Booking B, Route R \n"
                + "WHERE R.route_id = T.ticket_id \n"
                + "AND T.ticket_ID = B.ticket_ID \n"
                + "AND B.user_ID = "+id, Ticket.class);
        List<Ticket> results = q.getResultList(); //How to display route columns with this??
        for (Ticket c : results) {
            System.out.println(c);
        }
    }
    
    public boolean findTicket(int id, String user) { 
        boolean found = false; 
        int userid = findUserID(user); 
     
        Query q = em.createNativeQuery("SELECT T.ticket_id\n" 
                + "FROM Ticket T, Booking B\n" 
                + "WHERE B.ticket_ID = T.ticket_ID\n" 
                + "and B.user_id = " + userid 
                + "and T.ticket_id = " + id); 
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
    
        public void deleteTicket(int id){
            Ticket c = em.find(Ticket.class, id);
            em.getTransaction().begin();
            em.remove(c);
            em.getTransaction().commit();
        }
    
//        public User createUser(String username, String name, String DOB) { 
//            
//            User u = em.find(User.class, id); 
//            em.getTransaction().begin(); 
//            User u = new User(nameAdd, addAdd, emailAdd, numAdd); 
//            c.addStaff(abo); 
//            em.getTransaction().commit(); 
//            return c; 
//        }
        
        
    
    
}
