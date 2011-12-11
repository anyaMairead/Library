/**AUTHOR: Laurence Toal
  *CLASS: CS 230 Data Structures
  *Final Project Phase 2 - Class outlines
  *LAST MODIFIED: 27 November 2011
**/

import java.util.*;
import java.io.*;

public class Library {
    //instance variables
    private String libraryName;
    private String initialBookList;
    private String initialPatronList;
    private List<Book> books;
    private List<Patron> patrons;
    private Map<String, List<Book>> titleIndex;
    private Map<String, List<Book>> authorIndex;
    private Map<String, List<Book>> barcodeIndex;
    private Map<String, List<Patron>> patronNameIndex;
    private Map<String, List<Patron>> patronCardIndex;
    
    /**Constructor method to create a new library
    **/
    public Library(String libraryName, String initialBookList, String initialPatronList) {
        this.libraryName = libraryName;
        List<Book> books = new ArrayList<Book>(); 
        this.initialBookList = initialBookList;
        List<Patron> patrons = new ArrayList<Patron>();
        this.initialPatronList = initialPatronList;
        try {
            makeArrayListOfBooks(initialBookList);
        } catch (IOException e) {
            System.out.println("No such file");
        }
        try {
            makeArrayListOfPatrons(initialPatronList);
        } catch (IOException e) {
            System.out.println("No such file");
        }
        Map<String, List<Book>> titleIndex = new HashMap<String, List<Book>>();
        Map<String, List<Book>> authorIndex = new HashMap<String, List<Book>>();
        Map<String, List<Book>> barcodeIndex = new HashMap<String, List<Book>>();
        Map<String, List<Patron>> patronNameIndex = new HashMap<String, List<Patron>>();
        Map<String, List<Patron>> patronCardIndex = new HashMap<String, List<Patron>>();
    }
        
    /**Adds Books to the necessary hashmaps
      *
      *@param b the Book to add
    **/
    public void addBook(Book b) {
        books.add(b);
        
        String searchTitle = b.getTitle().toLowerCase().replaceAll(" ", "");
        if (titleIndex.containsKey(searchTitle)) {
            titleIndex.put(searchTitle, new ArrayList<Book>());
            titleIndex.get(searchTitle).add(b);
        } else {
            titleIndex.put(searchTitle, new ArrayList<Book>());
        }
        
        String[] authorKeyWords = b.getAuthor().toLowerCase().split("\\s+");
        if (authorIndex.containsKey(searchAuthor)) {
            authorIndex.put(searchAuthor, new ArrayList<Book>());
            authorIndex.get(searchAuthor).add(b);
        } else {
            authorIndex.put(searchAuthor, new ArrayList<Book>());
        }
       
        String searchBarcode = b.getBarcodeNumber();
        if (barcodeIndex.containsKey(searchBarcode)) {
            barcodeIndex.put(searchBarcode, new ArrayList<Book>());
            barcodeIndex.get(searchBarcode).add(b);
        } else {
            barcodeIndex.put(searchBarcode, new ArrayList<Book>());
        }
        
    }
    
    /**Adds patrons to the appropriate HashMaps
      * 
      *@param p the Patron to add 
    **/
    public void addPatron(Patron p) {
        patrons.add(p);
        
        String searchName = p.getName().toLowerCase().replaceAll(" ", "");
        if (patronNameIndex.containsKey(searchName)) {
            patronNameIndex.put(searchName, new ArrayList<Patron>());
            patronNameIndex.get(searchName).add(p);
        } else {
            patronNameIndex.put(searchName, new ArrayList<Patron>());
        }
        
        String searchCardNumber = p.getCardNumber();
        if (patronCardIndex.containsKey(searchCardNumber)) {
            patronCardIndex.put(searchCardNumber, new ArrayList<Patron>());
            patronCardIndex.get(searchCardNumber).add(p);
        } else {
            patronCardIndex.put(searchCardNumber, new ArrayList<Patron>());
        }
    }
    
    
    /**Fills the ArrayList books with Books created
      *by reading in information from a file
      * 
      *@param filename the file where the information is
    **/
    public void makeArrayListOfBooks(String filename) throws IOException { 
        Scanner scan = new Scanner(new File(filename));
        while (scan.hasNextLine()) {
            //read in each line, split on commas, and create a Book object out of the information
            //then add the book to the ArrayList books
            //expand the ArrayList if needed
        }
        scan.close();
    }
    
    /**Fills the ArrayList patrons with Patrons created
      *by reading in information from a file
      * 
      *@param filename the file where the information is
    **/
    public void makeArrayListOfPatrons(String filename) throws IOException { 
        Scanner scan = new Scanner(new File(filename));
        while (scan.hasNextLine()) {
            String patron = //read in each line, split on commas, and create a Patron object out of the information
            //then add the book to the ArrayList books
            //expand the ArrayList if needed
        }
        scan.close();
    }
    
    
    /**Displays the result of a search for a book by a title
      *
      *@param title the title to search for
    **/
    public List<Book> findBooksByTitle(String title) {
        //placeholder
        return new ArrayList<Book>();
    }
    
    /**Displays the result of a search for a book by an author
      *
      *@param author the author to search for
    **/
    public List<Book> findBooksByAuthor(String author) {
        //placeholder
        return new ArrayList<Book>();
    }
   
    /**Displays the result of a search for a book by a barcode number
      *
      *@param barcode the barcode number to search for
    **/
    public List<Book> findBooksByBarcode(String barcode) {
        //placeholder
        return new ArrayList<Book>();
    } 
}
