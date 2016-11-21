package dbService;

import java.sql.*;
import oracle.jdbc.pool.OracleDataSource;

public class ContactOperations {

    private Connection conn;
    private ResultSet rset;
    private PreparedStatement pstmt;

    // This method opens a connection to the Oracle database
    public Connection openDB() {
        try {
            OracleDataSource ods = new OracleDataSource();

            // Tallaght
             ods.setURL("jdbc:oracle:thin:@//10.10.2.7:1521/global1");
             ods.setUser("x00127907");
             ods.setPassword("db04Apr97");
            // Home Oracle XE
//            ods.setURL("jdbc:oracle:thin:HR/pmagee@localhost:1521:XE");
//            ods.setUser("hr");
//            ods.setPassword("passhr");

            conn = ods.getConnection();
            System.out.println("connected.");
        } catch (Exception e) {
            System.out.print("Unable to load driver " + e);
            System.exit(1);
        }
        return conn;
    }

    public void dropContactsTable() {
        System.out.println("Checking for existence of Contacts table");
        try {
            String s1 = "DROP TABLE Contacts";
            pstmt = conn.prepareStatement(s1);
            try {
                // Drop the Contacts table.
                pstmt.execute();
                System.out.println("Contacts table dropped.");
            } catch (SQLException ex) {
                // No need to report an error.
                // The table simply did not exist.
            }
        } catch (SQLException ex) {
            System.out.println("Problem dropping the Contacts table");
        }

    }

    public void dropAddressBookOwnersTable() {
        System.out.println("Checking for existence of AddressBookOwners table.");
        try {
            String s1 = "DROP TABLE AddressBookOwners";
            pstmt = conn.prepareStatement(s1);
            try {
                pstmt.execute();
                System.out.println("AddressBook table dropped.");
            } catch (SQLException ex) {
                // No need to report an error.
                // The table does not exist.
            }
        } catch (SQLException ex) {
            System.out.println("Problem dropping the AddressBook table");
        }
    }

public void dropContactsAddressBookTable() {
        System.out.println("Checking for existence of ContactsAddressBook table.");
        try {
            String s1 = "DROP TABLE ContactsAddressBook";
            pstmt = conn.prepareStatement(s1);
            try {
                pstmt.execute();
                System.out.println("ContactsAddressBook table dropped.");
            } catch (SQLException ex) {
                // No need to report an error.
                // The table does not exist.
            }
        } catch (SQLException ex) {
            System.out.println("Problem dropping the ContactsAddressBook table");
        }
    }

    public void dropAddressBookOwnerSequence() {
        try {
            String s2 = "drop sequence addressownerid_seq";
            pstmt = conn.prepareStatement(s2);
            try {
                pstmt.execute();
                System.out.println("AddressBookOwner Sequence dropped");
            } catch (SQLException ex) {
                // No need to report an error.
                // The sequence does not exist.
            }
        } catch (SQLException ex) {
            System.out.println("Problem dropping the AddressBook sequence");
        }
    }
        public void dropContactSequence() {
        try {
            String s2 = "drop sequence cid_seq";
            pstmt = conn.prepareStatement(s2);
            try {
                pstmt.execute();
                System.out.println("Contact Sequence dropped");
            } catch (SQLException ex) {
                // No need to report an error.
                // The sequence does not exist.
            }
        } catch (SQLException ex) {
            System.out.println("Problem dropping the Contact sequence");
        }
    }
    public void createContactSequence() {
        // Creating a sequence    
        try {
            String createseq1 = "create sequence cid_seq increment by 1 start with 1";
            pstmt = conn.prepareStatement(createseq1);
            pstmt.executeUpdate();
            System.out.println("Contact Sequence created");
        } catch (SQLException ex) {
            System.out.print("Problem with Create Sequence " + ex.getMessage());
        }

    }

    public void createAddressBookOwnersSequence() {
        // Creating a sequence    
        try {
            String createseq2 = "create sequence addressownerid_seq increment by 1 start with 1";
            pstmt = conn.prepareStatement(createseq2);
            pstmt.executeUpdate();
            System.out.println("AddressBookOwner Sequence created");
        } catch (SQLException ex) {
            System.out.print("Problem with Create Sequence " + ex.getMessage());
        }
    }


    public void CreateContactsTable() {
        try {

            // Create a Table
            String create = "CREATE TABLE Contacts "
                    + "(id NUMBER PRIMARY KEY, cname VARCHAR(40), address VARCHAR(30), pnumber VARCHAR(30), email VARCHAR(20))";
            pstmt = conn.prepareStatement(create);
            pstmt.executeUpdate();

            // Insert data into table
            String insertString1 = "INSERT INTO Contacts(id,cname,address,pnumber,email) "
                    + "values(cid_seq.nextVal,?,?,?,?)";
            pstmt = conn.prepareStatement(insertString1);

            pstmt.setString(1, "Peter");
            pstmt.setString(2, "23 Lime Lane");
            pstmt.setString(3, "018776543");
            pstmt.setString(4, "p.cassidy@b.com");
            pstmt.execute();

            pstmt.setString(1, "Donal");
            pstmt.setString(2, "2 Shelbourne rd");
            pstmt.setString(3, "012445678");
            pstmt.setString(4, "d.oreilly@b.com");
            pstmt.execute();

            pstmt.setString(1, "Kevin");
            pstmt.setString(2, "4 Richmond rd");
            pstmt.setString(3, "018765456");
            pstmt.setString(4, "m.reilly@rich.com");
            pstmt.execute();

            pstmt.setString(1, "Glen");
            pstmt.setString(2, "35 Sommerville Lane");
            pstmt.setString(3, "017854563");
            pstmt.setString(4, "p.whelan@siske.com");
            pstmt.execute();

            System.out.println("Contacts table created");
        } catch (SQLException e) {
            System.out.print("SQL Exception creating and inserting values into Contacts " + e.getMessage());
            System.exit(1);
        }
    }

    public void createAddressBookOwnerstable() {
        // Create a Table           
        try {
            String create = "CREATE TABLE AddressBookOwners "
                    + "(id NUMBER PRIMARY KEY NOT NULL, name VARCHAR(40))";
            pstmt = conn.prepareStatement(create);
            pstmt.executeUpdate();

            // Insert data into table
            String insertString1 = "INSERT INTO AddressBookOwners(id,name) "
                    + "values(addressownerid_seq.nextVal,?)";
            pstmt = conn.prepareStatement(insertString1);

            pstmt.setString(1, "Mary");
            pstmt.execute();

            pstmt.setString(1, "Jane");
            pstmt.execute();

            pstmt.setString(1, "Paula");
            pstmt.execute();

            System.out.println("AddressBookOwners table created");
        } catch (SQLException ex) {
            System.out.println("SQL Exception creating and inserting values into AddressBookOwners" + ex.getMessage());
        }
    }

    
    public void createContactsAddressBook() {
        // Create a Table           
        try {
           
            
            String create = "CREATE TABLE ContactsAddressBook "
                    + "(abid NUMBER, cid NUMBER, PRIMARY KEY (abid, cid), FOREIGN KEY (abid) REFERENCES AddressBookOwners (id),"
                    + "FOREIGN KEY (cid) REFERENCES Contacts (id) ON DELETE CASCADE )";
            pstmt = conn.prepareStatement(create);
            pstmt.executeUpdate();

            // Insert data into table
            String insertString1 = "INSERT INTO ContactsAddressBook(abid,cid) "
                    + "values(?,?)";
            pstmt = conn.prepareStatement(insertString1);

            pstmt.setInt(1, 1);
            pstmt.setInt(2, 1);
            pstmt.execute();
            pstmt.setInt(1, 1);
            pstmt.setInt(2, 2);
            pstmt.execute();

            pstmt.setInt(1, 2);
            pstmt.setInt(2, 1);
            pstmt.execute();
            pstmt.setInt(1, 2);
            pstmt.setInt(2, 3);
            pstmt.execute();

            pstmt.setInt(1, 3);
            pstmt.setInt(2, 1);
            pstmt.execute();

            System.out.println("ContactsAddressBook table created");
        } catch (SQLException ex) {
            System.out.println("SQL Exception creating and inserting values into ContactsAddressBook" + ex.getMessage());
        }
    }

    public void closeDB() {
        try {
            pstmt.close();
            rset.close();
            conn.close();
            System.out.print("Connection closed");
        } catch (SQLException e) {
            System.out.print("Could not close connection ");
        }
    }
    

    
    public static void main(String[] args) {
        ContactOperations co = new ContactOperations();
        co.openDB();
        co.dropContactSequence();
        co.dropAddressBookOwnerSequence();

        co.dropContactsAddressBookTable();
        co.dropAddressBookOwnersTable();
        co.dropContactsTable();

        co.createContactSequence();
        co.createAddressBookOwnersSequence();

        co.CreateContactsTable();
        co.createAddressBookOwnerstable();
        co.createContactsAddressBook();
    }
}
