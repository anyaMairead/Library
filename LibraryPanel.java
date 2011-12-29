/**AUTHOR: Laurence Toal
  *CLASS: CS 230 Data Structures
  *Final Project
  *LAST MODIFIED: 15 December 2011
  *CLASS DESCRIPTION: LibraryPanel is where everything happens!  Not only does it create all GUI screens (adding them to a CardLayout for easy accessibility), this is also where you search for books or patrons using a variety of criteria, and change the status (available, in transit, checked out, requested) or location (Buena Vista, Central, or Northwest) of a book.
**/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class LibraryPanel extends JPanel implements ActionListener {
  
    private JPanel cards, initialPanel, bookSearchResultPanel, bookFullInfoPanel, patronSearchResultPanel, patronFullInfoPanel, bookPanel, patronPanel, bookSearchResultsCenter, patronSearchResultsCenter;
    private JLabel welcomeLabel, bookSearchResultsLabel, patronSearchResultsLabel, setBookStatusLabel, selectBookCategoryLabel, selectPatronCategoryLabel, setBookLocationLabel, bookInfoLabel, patronInfoLabel;
    private JButton patronSearchButton, bookSearchButton, searchForBookButton, searchForPatronButton; 
    private JComboBox bookCategories, patronCategories;
    private String initial, bookSearchResult, bookFullInfo, patronSearchResult, patronFullInfo, bookInitial, patronInitial;
    private JTextField enterBookInfo, enterPatronInfo;
    private JRadioButton buenaVista, central, northwest, available, inTransit, requested, checkedOut;
    private ButtonGroup status, location;
    private java.util.List<JButton> newSearchButtons = new ArrayList<JButton>();
    private java.util.List<JButton> backButtons = new ArrayList<JButton>();
    private java.util.List<Book> bookResults = new ArrayList<Book>();
    private java.util.List<Patron> patronResults = new ArrayList<Patron>();
    private Book b; //for use when setting variables of a certian book 

    public LibraryPanel() {
        //strings to identify the cards
        initial = "welcome";
        bookSearchResult = "result of book search";
        patronSearchResult = "result of patron search";
        bookFullInfo = "book information";
        patronFullInfo = "patron information";
        bookInitial = "search for a book";
        patronInitial = "search for a patron";
    
        selectBookCategoryLabel = new JLabel("Select a category to search by", SwingConstants.CENTER);
        selectBookCategoryLabel.setForeground(Color.blue);
        selectBookCategoryLabel.setFont(new Font("Times", Font.ITALIC, 12));

        selectPatronCategoryLabel = new JLabel("Select a category to search by", SwingConstants.CENTER);
        selectPatronCategoryLabel.setForeground(Color.blue);
        selectPatronCategoryLabel.setFont(new Font("Times", Font.ITALIC, 12));
        
        setBookStatusLabel = new JLabel("Status of book:");
        setBookStatusLabel.setForeground(Color.blue);
        setBookStatusLabel.setFont(new Font("Times", Font.PLAIN, 12));

        setBookLocationLabel = new JLabel("Location of book:");
        setBookLocationLabel.setForeground(Color.blue);
        setBookLocationLabel.setFont(new Font("Times", Font.PLAIN, 12));

        searchForBookButton = new JButton("Search for Book");
        searchForBookButton.addActionListener(this);
        searchForBookButton.setBackground(new Color(132, 112, 255));

        searchForPatronButton = new JButton("Search for Patron");
        searchForPatronButton.addActionListener(this);
        searchForPatronButton.setBackground(new Color(132, 112, 255)); 

        initialPanel = makeInitialPanel();
        bookPanel = makeBookPanel();
        patronPanel = makePatronPanel();
        bookSearchResultPanel = makeBookSearchResultPanel();
        patronSearchResultPanel = makePatronSearchResultPanel();
        bookFullInfoPanel = makeBookFullInfoPanel();
        patronFullInfoPanel = makePatronFullInfoPanel();
        
        setLayout(new CardLayout());
        this.add(initialPanel, initial);
        this.add(bookPanel, bookInitial);
        this.add(patronPanel, patronInitial);
        this.add(bookSearchResultPanel, bookSearchResult);
        this.add(bookFullInfoPanel, bookFullInfo);
        this.add(patronSearchResultPanel, patronSearchResult);
        this.add(patronFullInfoPanel, patronFullInfo);
    }
    
    //Make JPanels for each different screen of the GUI
    
    /**Makes a "new search" button
      *
      *@return a new JButton
    **/
    private JButton makeNewSearchButton() {
        JButton newSearchButton = new JButton("New Search");
        newSearchButton.addActionListener(this);
        newSearchButton.setBackground(new Color(132, 112, 255));
        newSearchButtons.add(newSearchButton);
        return newSearchButton;
    }

    /**Makes a "back" button
      *
      *@return a new JButton with the text "back"
    **/
    private JButton makeBackButton() {
        JButton backButton = new JButton("Back");
        backButton.addActionListener(this);
        backButton.setBackground(new Color(132, 112, 255));
        backButtons.add(backButton);
        return backButton;
    }
    
    /**Makes the JPanel for detailed info on a book
      *
      *@return a JPanel
    **/
    public JPanel makeBookFullInfoPanel() {
        bookFullInfoPanel = new JPanel(new BorderLayout(10, 10));
        JButton newSearchButton = makeNewSearchButton();                       
        JButton backButton = makeBackButton();

        bookInfoLabel = new JLabel();  
        
        //radio buttons to set status of the book
        status = new ButtonGroup();
        available = new JRadioButton("Available");
        available.addActionListener(this);
        checkedOut = new JRadioButton("Checked out");
        checkedOut.addActionListener(this);
        inTransit = new JRadioButton("In transit");
        inTransit.addActionListener(this);
        requested = new JRadioButton("Requested");
        requested.addActionListener(this);
        status.add(available);
        status.add(checkedOut);
        status.add(inTransit);
        status.add(requested);
        
        //radio buttons to set location of book
        location = new ButtonGroup();
        buenaVista = new JRadioButton("Buena Vista");
        buenaVista.addActionListener(this);
        central = new JRadioButton("Central");
        central.addActionListener(this);
        northwest = new JRadioButton("Northwest");
        northwest.addActionListener(this);
        location.add(buenaVista);
        location.add(central);
        location.add(northwest);
        
        //top half of the center panel: book status
        JPanel bookFullInfoCenterTop = new JPanel();
        bookFullInfoCenterTop.add(setBookStatusLabel);
        bookFullInfoCenterTop.add(available);
        bookFullInfoCenterTop.add(checkedOut);
        bookFullInfoCenterTop.add(inTransit);
        bookFullInfoCenterTop.add(requested);

        //bottom half of center panel: book location
        JPanel bookFullInfoCenterBottom = new JPanel();
        bookFullInfoCenterBottom.add(setBookLocationLabel);
        bookFullInfoCenterBottom.add(buenaVista);
        bookFullInfoCenterBottom.add(central);
        bookFullInfoCenterBottom.add(northwest);

        JPanel bookFullInfoCenter = new JPanel(new GridLayout(2, 1, 2, 2));
        bookFullInfoCenter.add(bookFullInfoCenterTop);
        bookFullInfoCenter.add(bookFullInfoCenterBottom);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        buttonPanel.add(newSearchButton);

        bookFullInfoPanel.add(bookInfoLabel, BorderLayout.NORTH);
        bookFullInfoPanel.add(bookFullInfoCenter, BorderLayout.CENTER);
        bookFullInfoPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        return bookFullInfoPanel;
    }
    
    /**Makes the JPanel for the result of searching for a book
      *
      *@return a JPanel
    **/
    public JPanel makeBookSearchResultPanel() {
        JPanel bookSearchResult = new JPanel();
        bookSearchResult.setLayout(new BorderLayout());

        JButton newSearchButton = makeNewSearchButton(); 
        JButton backButton = makeBackButton();
        
        JLabel bookSearchResultsTitle = new JLabel("Result of search through book catalogue...", SwingConstants.CENTER);
        bookSearchResultsTitle.setForeground(Color.blue);
        bookSearchResultsTitle.setFont(new Font("Times", Font.PLAIN, 12));
        
        bookSearchResultsCenter = new JPanel(new GridLayout(0, 1, 5, 5));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        buttonPanel.add(newSearchButton);

        bookSearchResult.add(bookSearchResultsTitle, BorderLayout.NORTH);
        bookSearchResult.add(bookSearchResultsCenter, BorderLayout.CENTER);
        bookSearchResult.add(buttonPanel, BorderLayout.SOUTH);

        return bookSearchResult;
    }
    
    /**Makes the JPanel for the result of searching for a patron
      *
      *@return a JPanel
    **/
    public JPanel makePatronSearchResultPanel() {
        JPanel patronSearchResult = new JPanel();
        patronSearchResult.setLayout(new BorderLayout());

        JButton newSearchButton = makeNewSearchButton();
        JButton backButton = makeBackButton();

        JLabel patronSearchResultsTitle = new JLabel("Result of search through patron catalogue...", SwingConstants.CENTER);
        patronSearchResultsTitle.setForeground(Color.blue);
        patronSearchResultsTitle.setFont(new Font("Times", Font.PLAIN, 12));

        //gridlayout in the center panel for nice-looking results
        patronSearchResultsCenter = new JPanel(new GridLayout(0, 1, 5, 5));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        buttonPanel.add(newSearchButton);
 
        patronSearchResult.add(patronSearchResultsTitle, BorderLayout.NORTH);
        patronSearchResult.add(patronSearchResultsCenter, BorderLayout.CENTER);
        patronSearchResult.add(buttonPanel, BorderLayout.SOUTH);
    
        return patronSearchResult;
    }
      
    /**Makes the JPanel for detailed info on a patron
      *
      *@return a JPanel
    **/
    public JPanel makePatronFullInfoPanel() {
       JPanel patronFullInfoResult = new JPanel(new BorderLayout());
       JButton newSearchButton = makeNewSearchButton();
       JButton backButton = makeBackButton();

       patronInfoLabel = new JLabel();
       
       JPanel buttonPanel = new JPanel();
       buttonPanel.add(backButton);
       buttonPanel.add(newSearchButton);

       patronFullInfoResult.add(patronInfoLabel, BorderLayout.NORTH);
       patronFullInfoResult.add(buttonPanel, BorderLayout.SOUTH);
       return patronFullInfoResult;
    }
    
    /**Makes the JPanel for the initial screen
      *
      *@return a JPanel
    **/   
    public JPanel makeInitialPanel() {
        JPanel initialPanel = new JPanel();
        
        welcomeLabel = new JLabel("Welcome to the library management system.  Would you like to search by book or by patron?");
        welcomeLabel.setForeground(Color.blue);
        welcomeLabel.setFont(new Font("Times", Font.PLAIN, 12));

        bookSearchButton = new JButton("Book");
        bookSearchButton.addActionListener(this);
        bookSearchButton.setBackground(new Color(132, 112, 255));
        
        patronSearchButton = new JButton("Patron");
        patronSearchButton.addActionListener(this);
        patronSearchButton.setBackground(new Color(132, 112, 255));
        
        JPanel initialPanelButtons = new JPanel(new GridLayout(1, 2, 5, 5));
        initialPanelButtons.add(bookSearchButton);
        initialPanelButtons.add(patronSearchButton);
        
        initialPanel.add(welcomeLabel);
        initialPanel.add(initialPanelButtons);

        return initialPanel;
    }
    
    /**Makes the JPanel for a book search
      *
      *@return a JPanel
    **/
    public JPanel makeBookPanel() {
        bookPanel = new JPanel(new BorderLayout(0, 20));
        
        enterBookInfo = new JTextField("Enter book information", 50);
        enterBookInfo.addActionListener(this);

        JButton newSearchButton = makeNewSearchButton();
        JButton backButton = makeBackButton();

        bookCategories = new JComboBox();
        bookCategories.addItem("Title");
        bookCategories.addItem("Author");
        bookCategories.addItem("Barcode Number");
        bookCategories.setSelectedIndex(0);
        bookCategories.addActionListener(this);
        
        JPanel bookDropdownAndButtons = new JPanel();
        bookDropdownAndButtons.add(bookCategories);
        bookDropdownAndButtons.add(searchForBookButton);
        bookDropdownAndButtons.add(backButton);
        
        JPanel infoAndCategory = new JPanel(new BorderLayout(10, 20));
        infoAndCategory.add(enterBookInfo, BorderLayout.NORTH); 
        infoAndCategory.add(bookDropdownAndButtons, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        buttonPanel.add(newSearchButton);

        bookPanel.add(selectBookCategoryLabel, BorderLayout.NORTH); 
        bookPanel.add(infoAndCategory, BorderLayout.CENTER);
        bookPanel.add(buttonPanel, BorderLayout.SOUTH);
        return bookPanel;
    }
    
    /**Makes the JPanel for a patron search
      *
      *@return a JPanel
    **/
    public JPanel makePatronPanel() {
        patronPanel = new JPanel(new BorderLayout(0, 20));
        
        JButton newSearchButton = makeNewSearchButton();
        JButton backButton = makeBackButton();

        enterPatronInfo = new JTextField("Enter patron information", 50);
        enterPatronInfo.addActionListener(this);

        patronCategories = new JComboBox();
        patronCategories.addItem("Name");
        patronCategories.addItem("Library Card Number");
        patronCategories.setSelectedIndex(0);
        patronCategories.addActionListener(this);

        JPanel patronDropdownAndButtons = new JPanel();
        patronDropdownAndButtons.add(patronCategories);
        patronDropdownAndButtons.add(searchForPatronButton);

        JPanel patronInfoAndCategory = new JPanel(new BorderLayout(10, 20));
        patronInfoAndCategory.add(enterPatronInfo, BorderLayout.NORTH);
        patronInfoAndCategory.add(patronDropdownAndButtons, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        buttonPanel.add(newSearchButton);

        patronPanel.add(selectPatronCategoryLabel, BorderLayout.NORTH);
        patronPanel.add(patronInfoAndCategory, BorderLayout.CENTER); 
        patronPanel.add(buttonPanel, BorderLayout.SOUTH);

        return patronPanel;
    }
    
    //probably should move to the Library class
    public void checkOutBook(String patronToGetBook) {
        if (patronToGetBook.matches("\\d+")) {  //a library card number was entered
            //....
        } else if (patronToGetBook.matches("[a-zA-Z]+( [a-zA-Z]+)*")) {  //a name was entered
            //....
        } else {
            JOptionPane.showMessageDialog(null, "Not a valid input");
            patronToGetBook = JOptionPane.showInputDialog(null, "Please enter the name or card number of the patron \n" + "to check " + b.getTitle() + " out to"); //get patron to check the book out to
            checkOutBook(patronToGetBook);
        }
    }

    /**Handles events. 
    **/
    public void actionPerformed(ActionEvent event) { 
        CardLayout cl = (CardLayout)(this.getLayout());
        Object source = event.getSource();
        
        if (source == bookSearchButton) {
            cl.show(this, bookInitial);
        } else if (source == patronSearchButton) {
            cl.show(this, patronInitial);
        } else if (newSearchButtons.contains(source)) { //then it's a newSearchButton
            cl.show(this, initial);
        } else if (backButtons.contains(source)) {
            cl.previous(this);
        } else if (source.equals(available)) {
            b.setStatus("available");
            JOptionPane.showMessageDialog(null, b.getTitle() + " is now available");  //set container to null on all of these to use the default
            bookInfoLabel.setText(b.toString().replace("[","<html>").replaceAll("\\n", "<br>"));
        } else if (source.equals(checkedOut)) {
            b.setStatus("checked out"); 
            String input = JOptionPane.showInputDialog(null, "Please enter the name or card number of the patron \n" + "to check " + b.getTitle() + " out to"); //get patron to check the book out to
            checkOutBook(input);
            JOptionPane.showMessageDialog(null, b.getTitle() + " is now checked out");
            bookInfoLabel.setText(b.toString().replace("[","<html>").replaceAll("\\n", "<br>")); 
        } else if (source.equals(inTransit)) {
            b.setStatus("in transit");
            JOptionPane.showMessageDialog(null, b.getTitle() + " is now in transit");
            bookInfoLabel.setText(b.toString().replace("[","<html>").replaceAll("\\n", "<br>"));
        } else if (source.equals(requested)) {
            b.setStatus("requested");
            JOptionPane.showMessageDialog(null, b.getTitle() + " is now requested");
            bookInfoLabel.setText(b.toString().replace("[","<html>").replaceAll("\\n", "<br>"));
        } else if (source.equals(buenaVista)) {
            b.setBranchLocation("Buena Vista");
            JOptionPane.showMessageDialog(null, b.getTitle() + "'s location is now Buena Vista Branch");
            bookInfoLabel.setText(b.toString().replace("[","<html>").replaceAll("\\n", "<br>"));
        } else if (source.equals(central)) {
            b.setBranchLocation("Central");
            JOptionPane.showMessageDialog(null, b.getTitle() + "'s location is now Central Library");
            bookInfoLabel.setText(b.toString().replace("[","<html>").replaceAll("\\n", "<br>"));
        } else if (source.equals(northwest)) {
            b.setBranchLocation("Northwest");
            JOptionPane.showMessageDialog(null, b.getTitle() + "'s location is now Northwest Branch");
            bookInfoLabel.setText(b.toString().replace("[","<html>").replaceAll("\\n", "<br>"));
        }

        if (source instanceof JButton && ((JButton)source).getClientProperty("book") != null) { //find out which result was clicked & set the bookInfoLabel
            b = (Book) ((JButton)source).getClientProperty("book");
            bookInfoLabel.setText(b.toString().replace("[","<html>").replaceAll("\\n", "<br>")); //regex for formatting niceness 
            cl.show(this, bookFullInfo); 
        }

        if(source instanceof JButton && ((JButton)source).getClientProperty("patron") != null) {
            Patron p = (Patron) ((JButton) source).getClientProperty("patron");
            patronInfoLabel.setText(p.toString().replace("[","<html>").replaceAll("\\n", "<br>")); //regex for formatting niceness
            cl.show(this, patronFullInfo);
        }
                                                                                                                                                

        Object bookSearchCriteria = bookCategories.getSelectedItem();
        if (bookSearchCriteria.equals("Title")) {
            if (source == searchForBookButton || source == enterBookInfo) {
                bookSearchResultsCenter.removeAll();
                bookResults = Library.getLibrary().findBooksByTitle(enterBookInfo.getText());
                
                for(Book item : bookResults) {
                    String s = item.getTitle() + " by " + item.getAuthor();
                    JButton searchMatch = new JButton(s);
                    searchMatch.setSize(200, 40);
                    searchMatch.setBackground(new Color(0, 197, 205));
                    searchMatch.putClientProperty("book", item);
                    searchMatch.addActionListener(this);
                    bookSearchResultsCenter.add(searchMatch);
                }
              
                cl.show(this, bookSearchResult);
            }
        } else if (bookSearchCriteria.equals("Author")) {
            if (source == searchForBookButton || source == enterBookInfo) {
                bookSearchResultsCenter.removeAll();
                bookResults = Library.getLibrary().findBooksByAuthor(enterBookInfo.getText()); 
               
                for(Book item : bookResults) { 
                    String s = item.getTitle() + " by " + item.getAuthor();
                    JButton searchMatch = new JButton(s);
                    searchMatch.setSize(200, 40);
                    searchMatch.setBackground(new Color(0, 197, 205));
                    searchMatch.putClientProperty("book", item);
                    searchMatch.addActionListener(this);
                    bookSearchResultsCenter.add(searchMatch);
                } 
               
                cl.show(this, bookSearchResult); 

            }
        } else if (bookSearchCriteria.equals("Barcode Number")) {
            if (source == searchForBookButton || source == enterBookInfo) {
                bookSearchResultsCenter.removeAll();
                bookResults = Library.getLibrary().findBooksByBarcode(enterBookInfo.getText());
              
                for (Book item : bookResults) {
                    String s = item.getTitle() + " by " + item.getAuthor();
                    JButton searchMatch = new JButton(s);
                    searchMatch.setSize(200, 40);
                    searchMatch.setBackground(new Color(0, 197, 205));
                    searchMatch.putClientProperty("book", item);
                    searchMatch.addActionListener(this);
                    bookSearchResultsCenter.add(searchMatch);
                } 
                cl.show(this, bookSearchResult);
            }
        }
        
        Object patronSearchCriteria = patronCategories.getSelectedItem();
        if (patronSearchCriteria.equals("Name")) {
            if (source == searchForPatronButton || source == enterPatronInfo) {
                patronSearchResultsCenter.removeAll();
                patronResults = Library.getLibrary().findPatronsByName(enterPatronInfo.getText());
             
                for (Patron person : patronResults) {
                    String s = person.getName() + "\t" + person.getCardNumber();   
                    JButton searchMatch = new JButton(s);
                    searchMatch.setSize(200, 40);
                    searchMatch.setBackground(new Color(0, 197, 205));
                    searchMatch.putClientProperty("patron", person);
                    searchMatch.addActionListener(this);
                    patronSearchResultsCenter.add(searchMatch);
                }
                cl.show(this, patronSearchResult);
            }
        } else if (patronSearchCriteria.equals("Library Card Number")) {
            if (source == searchForPatronButton || source == enterPatronInfo) {
                patronSearchResultsCenter.removeAll();
                patronResults = Library.getLibrary().findPatronsByCardNumber(enterPatronInfo.getText());
            
                for (Patron person : patronResults) {
                    String s = person.getName() + "\t" + person.getCardNumber();
                    JButton searchMatch = new JButton(s);
                    searchMatch.setSize(200, 40);
                    searchMatch.setBackground(new Color(0, 197, 205));
                    searchMatch.putClientProperty("patron", person);
                    searchMatch.addActionListener(this);
                    patronSearchResultsCenter.add(searchMatch);
                }
                cl.show(this, patronSearchResult);
            }
        }
      
     }
    }
    

