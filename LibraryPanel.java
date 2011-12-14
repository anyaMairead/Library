/**AUTHOR: Laurence Toal
  *CLASS: CS 230 Data Structures
  *Final Project Phase 2 - Class Outlines
  *LAST MODIFIED: 22 November 2011
**/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class LibraryPanel extends JPanel implements ActionListener {
  
    private JPanel cards, initialPanel, bookSearchResultPanel, bookFullInfoPanel, patronSearchResultPanel, patronFullInfoPanel, bookPanel, patronPanel;
    private JLabel welcomeLabel, bookSearchResultsLabel, patronSearchResultsLabel, setBookStatusLabel, selectBookCategoryLabel, selectPatronCategoryLabel, setBookLocationLabel;
    private JButton patronSearchButton, bookSearchButton, searchForBookButton, searchForPatronButton; 
    private JComboBox bookCategories, patronCategories;
    private String initial, bookSearchResult, bookFullInfo, patronSearchResult, patronFullInfo, bookInitial, patronInitial;
    private JTextField enterBookInfo, enterPatronInfo;
    private JRadioButton buenaVista, central, northwest, available, inTransit, checkedOut;
    private ButtonGroup status, location;
    private java.util.List<JButton> newSearchButtons = new ArrayList<JButton>();
    private java.util.List<Book> bookResults = new ArrayList<Book>();
    private java.util.List<Patron> patronResults = new ArrayList<Patron>();
    
    public LibraryPanel() {
        //strings to identify the cards
        initial = "welcome";
        bookSearchResult = "result of book search";
        patronSearchResult = "result of patron search";
        bookFullInfo = "book information";
        patronFullInfo = "patron information";
        bookInitial = "search for a book";
        patronInitial = "search for a patron";
    
        selectBookCategoryLabel = new JLabel("Select a category to search by");
        selectBookCategoryLabel.setForeground(Color.blue);
        selectBookCategoryLabel.setFont(new Font("Times", Font.ITALIC, 12));

        selectPatronCategoryLabel = new JLabel("Select a category to search by");
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
        this.add(patronSearchResultPanel, patronSearchResult);
        this.add(bookFullInfoPanel, bookFullInfo);
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
    
    /**Makes the JPanel for detailed info on a book
      *
      *@return a JPanel
    **/
    public JPanel makeBookFullInfoPanel() {
        bookFullInfoPanel = new JPanel();
        JButton newSearchButton = makeNewSearchButton();                       
        //want this to be string representation of the book they just clicked on
        //JLabel bookInfo = new JLabel(b.toString()); //where b is the book
        //bookFullInfoPanel.add(bookInfo);
        status = new ButtonGroup();
        available = new JRadioButton("Available");
        available.addActionListener(this);
        checkedOut = new JRadioButton("Checked out");
        checkedOut.addActionListener(this);
        inTransit = new JRadioButton("In transit");
        inTransit.addActionListener(this);
        status.add(available);
        status.add(checkedOut);
        status.add(inTransit);

        location = new ButtonGroup();
        buenaVista = new JRadioButton("Buena Vista Branch");
        buenaVista.addActionListener(this);
        central = new JRadioButton("Central");
        central.addActionListener(this);
        northwest = new JRadioButton("Northwest");
        northwest.addActionListener(this);
        location.add(buenaVista);
        location.add(central);
        location.add(inTransit);
        
        bookFullInfoPanel.add(setBookStatusLabel);
        bookFullInfoPanel.add(available);
        bookFullInfoPanel.add(checkedOut);
        bookFullInfoPanel.add(inTransit);
        bookFullInfoPanel.add(setBookLocationLabel);
        bookFullInfoPanel.add(buenaVista);
        bookFullInfoPanel.add(central);
        bookFullInfoPanel.add(northwest);
        bookFullInfoPanel.add(newSearchButton);
        
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
        
        JLabel bookSearchResultsTitle = new JLabel("Result of search through book catalogue...");
        bookSearchResultsTitle.setForeground(Color.blue);
        bookSearchResultsTitle.setFont(new Font("Times", Font.PLAIN, 12));
        
        bookSearchResultsLabel = new JLabel("<html>No results yet");
        
        bookSearchResult.add(bookSearchResultsTitle, BorderLayout.NORTH);
        bookSearchResult.add(bookSearchResultsLabel, BorderLayout.CENTER);
        bookSearchResult.add(newSearchButton, BorderLayout.SOUTH);

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

        JLabel patronSearchResultsTitle = new JLabel("Result of search through patron catalogue...");
        patronSearchResultsTitle.setForeground(Color.blue);
        patronSearchResultsTitle.setFont(new Font("Times", Font.PLAIN, 12));

        patronSearchResultsLabel = new JLabel("<html>No results yet");

        patronSearchResult.add(patronSearchResultsTitle, BorderLayout.NORTH);
        patronSearchResult.add(patronSearchResultsLabel, BorderLayout.CENTER);
        patronSearchResult.add(newSearchButton, BorderLayout.SOUTH);
        return patronSearchResult;
    }
      
    /**Makes the JPanel for detailed info on a patron
      *
      *@return a JPanel
    **/
    public JPanel makePatronFullInfoPanel() {
       JPanel patronFullInfoResult = new JPanel();
       JButton newSearchButton = makeNewSearchButton();

       //JLabel patronLabel = new JLabel(p.toString);
       //patronFullInfoResult.add(patronLabel);

       patronFullInfoResult.add(newSearchButton);
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
        
        initialPanel.add(welcomeLabel);
        initialPanel.add(bookSearchButton);
        initialPanel.add(patronSearchButton);

        return initialPanel;
    }
    
    /**Makes the JPanel for dealing with a book
      *
      *@return a JPanel
    **/
    public JPanel makeBookPanel() {
        bookPanel = new JPanel();
  
        enterBookInfo = new JTextField("Enter book information", 50);
        JButton newSearchButton = makeNewSearchButton();
                
        bookCategories = new JComboBox();
        bookCategories.addItem("Title");
        bookCategories.addItem("Author");
        bookCategories.addItem("Barcode Number");
        bookCategories.setSelectedIndex(0);
        bookCategories.addActionListener(this);

        bookPanel.add(selectBookCategoryLabel);
        bookPanel.add(enterBookInfo);
        bookPanel.add(bookCategories);
        bookPanel.add(searchForBookButton);
        bookPanel.add(newSearchButton);
       
        return bookPanel;
    }
    
    /**Makes the JPanel for dealing with a patron
      *
      *@return a JPanel
    **/
    public JPanel makePatronPanel() {
        patronPanel = new JPanel();
        
        JButton newSearchButton = makeNewSearchButton();
        enterPatronInfo = new JTextField("Enter patron information", 50);

        patronCategories = new JComboBox();
        patronCategories.addItem("Name");
        patronCategories.addItem("Library Card Number");
        patronCategories.setSelectedIndex(0);
        patronCategories.addActionListener(this);

        patronPanel.add(selectPatronCategoryLabel);
        patronPanel.add(enterPatronInfo);
        patronPanel.add(patronCategories);
        patronPanel.add(searchForPatronButton);
        patronPanel.add(newSearchButton);

        return patronPanel;
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
        } else if (source.equals(available)) {
            //...
        } else if (source.equals(checkedOut)) {
            //...
        } else if (source.equals(inTransit)) {
            //...
        } else if (source.equals(buenaVista)) {
            //...
        } else if (source.equals(central)) {
            //...
        } else if (source.equals(northwest)) {
            //...
        }

        Object bookSearchCriteria = bookCategories.getSelectedItem();
        if (bookSearchCriteria.equals("Title")) {
            if (source == searchForBookButton) {
                bookResults = Library.getLibrary().findBooksByTitle(enterBookInfo.getText());
                System.out.println(bookResults);

                StringBuilder builder = new StringBuilder();
                for(Book item : bookResults) {
                    builder.append(item.getTitle() + " by " + item.getAuthor() + "<br>");
                }
                bookSearchResultsLabel.setText("<html>" + builder.toString());
                cl.show(this, bookSearchResult);
            }
        } else if (bookSearchCriteria.equals("Author")) {
            if (source == searchForBookButton) {
                bookResults = Library.getLibrary().findBooksByAuthor(enterBookInfo.getText());
                
                StringBuilder builder = new StringBuilder();
                for(Book item : bookResults) {
                    builder.append(item.getTitle() + " by " + item.getAuthor() + "<br>");
                } 
                bookSearchResultsLabel.setText("<html>" + builder.toString());
                cl.show(this, bookSearchResult); 

            }
        } else if (bookSearchCriteria.equals("Barcode")) {
            if (source == searchForBookButton) {
                bookResults = Library.getLibrary().findBooksByBarcode(enterBookInfo.getText());
                StringBuilder builder = new StringBuilder();
                for (Book item : bookResults) {
                    builder.append(item.getTitle() + " by " + item.getAuthor() + "<br>");
                } 
                bookSearchResultsLabel.setText("<html>" + builder.toString()); 
                cl.show(this, bookSearchResult);
            }
        }
        
        Object patronSearchCriteria = patronCategories.getSelectedItem();
        if (patronSearchCriteria.equals("Name")) {
            if (source == searchForPatronButton) {
                patronResults = Library.getLibrary().findPatronsByName(enterPatronInfo.getText());
                
                StringBuilder builder = new StringBuilder();
                for (Patron person : patronResults) {
                    builder.append(person.getName() + person.getCardNumber() + "<br>");
                }
                patronSearchResultsLabel.setText("<html>" + builder.toString());
                cl.show(this, patronSearchResult);
            }
        } else if (patronSearchCriteria.equals("Library Card Number")) {
            if (source == searchForPatronButton) {
                patronResults = Library.getLibrary().findPatronsByCardNumber(enterPatronInfo.getText());
                StringBuilder builder = new StringBuilder();
                for (Patron person : patronResults) {
                    builder.append(person.getName() + person.getCardNumber() + "<br>");
                }
                patronSearchResultsLabel.setText("<html>" + builder.toString());
                
                cl.show(this, patronSearchResult);
            }
        }
      
     
    }
    
}
