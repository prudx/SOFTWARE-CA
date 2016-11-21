package dbService;

import java.util.List;
import javax.persistence.*;
import model.AddressBookOwner;
import model.Contact;

public class JPAService {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Lab_15_SkeletonPU");
    EntityManager em = emf.createEntityManager();

   public int findOwnerID(String name) {
       int ownerID=0;
       Query query = em.createQuery("SELECT o.id FROM AddressBookOwner o WHERE o.name=:value",
               AddressBookOwner.class).setParameter("value", name);
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
       AddressBookOwner abo = em.find(AddressBookOwner.class, id);
       if (abo != null){
           found = true;       
       }
       return found;   
   }
    
    public void printAllContacts(String name){
        int id = findOwnerID(name);
        Query q = em.createNativeQuery("SELECT C.ID, C.CNAME, C.ADDRESS, C.PNUMBER, C.EMAIL FROM CONTACTS C, CONTACTSADDRESSBOOK CAB\n WHERE C.ID = CAB.CID\n and CAB.ABID=" +id, Contact.class);
        List<Contact> results = q.getResultList();
        
        for (Contact c : results) {
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
        List<Contact> results = q.getResultList(); 
        // easier than for loop below 
        if (!results.isEmpty()) { found = true; 
        } 
        return found; 
    }
        
        public void updateContact(int id, String newNum) { 
            em.getTransaction().begin();
            Contact c = em.find(Contact.class, id); 
            c.setPhoneNumber(newNum); 
            em.getTransaction().commit(); 
        }
    
        public void removeContact(int id){
            Contact c = em.find(Contact.class, id);
            em.getTransaction().begin();
            em.remove(c);
            em.getTransaction().commit();
        }
    
        public Contact createContact(String nameAdd, String addAdd, String emailAdd, String numAdd, String owner) { 
            int id = findOwnerID(owner);
            AddressBookOwner abo = em.find(AddressBookOwner.class, id); 
            em.getTransaction().begin(); 
            Contact c = new Contact(nameAdd, addAdd, emailAdd, numAdd); 
            c.addStaff(abo); 
            em.getTransaction().commit(); 
            return c; 
        }
    
    
}
