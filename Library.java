/**AUTHOR: Laurence Toal
  *CLASS: CS 230 Data Structures
  *Final Project - Library class for organizing Books and Patrons
  *LAST MODIFIED: 11 December 2011
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
        
        String authorNames = b.getAuthor().toLowerCase().replaceAll(" and ", "").trim(); //for handling multiple-author books
        String[] authorWords = authorNames.split("\\s+");
        for (String term: authorWords) {
            if (!authorIndex.containsKey(term)) {
                authorIndex.put(term, new ArrayList<Book>());
            } else {
                authorIndex.get(term).add(b);
            }
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
            String[] tokens = scan.nextLine().split("\\s*,\\s*");
            Book book = new Book(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4], tokens[5], tokens[6]);
            addBook(book);
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
            String[] tokens = scan.nextLine().split("\\s*,\\s*");
            Patron patron = new Patron(tokens[0], tokens[1]);
            addPatron(patron); 
        }
        scan.close();
    }
    
    
    /**Displays the result of a search for a book by a title
      *
      *@param title the title to search for
    **/
    public List<Book> findBooksByTitle(String title) {
        title = title.toLowerCase().replaceAll(" ", "");
        if (titleIndex.containsKey(title)) { //we've found a match
            return titleIndex.get(title);
        } else { //we don't have a book with that title, return empty list
            return new ArrayList<Book>();
        }
    }
    
    /**Displays the result of a search for a book by an author
      *
      *@param author the author to search for
    **/
    public List<Book> findBooksByAuthor(String author) {
        author = author.toLowerCase();
        String[] authorWords = author.split("\\P{L}+"); //tokenize
        for (String term: authorWords) {
            if (authorIndex.containsKey(term)) {
                return authorIndex.get(term);
            }
        } 
        //otherwise there's no match                                                   
        return new ArrayList<Book>();
    }
   
    /**Displays the result of a search for a book by a barcode number
      *
      *@param barcode the barcode number to search for
    **/
    public List<Book> findBooksByBarcode(String barcode) {
        if (barcodeIndex.containsKey(barcode)) { //we've found a match
            return barcodeIndex.get(barcode);
        } else { //we don't have a book with that barcode, return empty list
            return new ArrayList<Book>();
        }
    } 
}
