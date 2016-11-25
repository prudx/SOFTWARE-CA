package dbService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.*;
import model.*;


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
           
       }
       return userID;
    }
    
    public boolean findUser(String username){
       int id = findUserID(username);
       boolean found = false;
       User currentUser = em.find(User.class, id);
       if (currentUser != null){
           found = true;       
       }
       return found;
    }
    
    public User findUserUser(String username) {
       Query query = em.createQuery("SELECT u FROM User u WHERE u.username=:value",
               User.class).setParameter("value", username);
       List<User> results = query.getResultList();
       ArrayList<User> results1 = new ArrayList(1);
       results1.add(results.get(0));
       User u = results1.get(0);                //?!?!? WHAT AM I DOING - hey it works
       return u;       
    }
    
    public boolean passwordMatch(String passwordIn, String username){
       boolean match = false;
       Query q = em.createNativeQuery("SELECT U.password FROM Users U WHERE user_ID = "+findUserID(username));
            List<String> results = q.getResultList();
            String[] strArray = results.toArray(new String[0]);      //casts the list to array to string kind of
            String passwordFound = Arrays.toString(strArray);
     
            passwordIn = "["+passwordIn+"]";                         //This is required because the list cast returns the password 
                                                                     //inside [] brackets so we add these to the users entered password
       if(passwordFound.equals(passwordIn)){
           match = true;
       } 
       return match;
    }
    
    public void setUsersPassword(String newPass, String usernameEntered){
        int id = findUserID(usernameEntered);
        em.getTransaction().begin();
        User currentUser = em.find(User.class, id);
        currentUser.setPassword(newPass);
        em.persist(currentUser);
        em.getTransaction().commit();         
    }

    public void printAllTickets(String name){
        int id = findUserID(name);
        Query q = em.createNativeQuery("SELECT T.ticket_id, T.depDate, T.returnDate, T.route_id "
                + "FROM Ticket T, Booking B \n"
                + "WHERE B.ticket_id = T.ticket_id \n"
                + "AND B.user_id = " +id
                + "ORDER BY T.ticket_id", Ticket.class);
        List<Ticket> results = q.getResultList(); 
        System.out.println("----------------------------------------------------------------------------------");
        for (Ticket c : results) {
            System.out.println(c);
        }
        System.out.println("----------------------------------------------------------------------------------");
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
        if (!results.isEmpty()) { 
            found = true; 
        } 
        return found; 
    }
    
        public void deleteTicket(int id){
            Ticket c = em.find(Ticket.class, id);
            em.getTransaction().begin();
            em.remove(c);
            em.getTransaction().commit();
        }
        
        public Routes findRoute(int routeChoice){
            Routes r = em.find(Routes.class, routeChoice);
            r.setRoute_id(routeChoice);
            return r;
        }
        
        public void purchaseTicket(String depDate, String returnDate, int routeChoice, String usernameEntered) {
            em.getTransaction().begin();
            Ticket newTicket = new Ticket(depDate, returnDate);
            Routes r = findRoute(routeChoice);
            r.addTicket(newTicket);   
            em.persist(newTicket);
            
            User u = findUserUser(usernameEntered);
            newTicket.addUserToBooking(u);
            em.getTransaction().commit();
        }
  
}
