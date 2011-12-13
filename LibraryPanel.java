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
    private JLabel welcomeLabel, bookSearchResultsLabel, patronSearchResultsLabel, selectLabel;
    private JButton patronSearchButton, bookSearchButton, searchForBookButton, searchForPatronButton, newSearchButton;    
    private JComboBox bookCategories, patronCategories;
    private String initial, bookSearchResult, bookFullInfo, patronSearchResult, patronFullInfo, bookInitial, patronInitial;
    private JTextField enterBookInfo, enterPatronInfo;

    public LibraryPanel() {
        //strings to identify the cards
        initial = "welcome";
        bookSearchResult = "result of book search";
        patronSearchResult = "result of patron search";
        bookFullInfo = "book information";
        patronFullInfo = "patron information";
        bookInitial = "search for a book";
        patronInitial = "search for a patron";
    
        selectLabel = new JLabel("Select a category to search by");
        selectLabel.setForeground(Color.blue);
        selectLabel.setFont(new Font("Times", Font.ITALIC, 12));

        searchForBookButton = new JButton("Search for Book");
        searchForBookButton.addActionListener(this);
        searchForBookButton.setBackground(new Color(132, 112, 255));

        searchForPatronButton = new JButton("Search for Patron");
        searchForPatronButton.addActionListener(this);
        searchForPatronButton.setBackground(new Color(132, 112, 255));

        newSearchButton = new JButton("New Search");
        newSearchButton.addActionListener(this);
        newSearchButton.setBackground(new Color(132, 112, 255));

        initialPanel = makeInitialPanel();
        bookPanel = makeBookPanel();
        patronPanel = makePatronPanel();
        bookSearchResultPanel = makeBookSearchResultPanel();
        patronSearchResultPanel = makePatronSearchResultPanel();
        bookFullInfoPanel = makeBookFullInfoPanel();
        patronFullInfoPanel = makePatronFullInfoPanel();

        cards = new JPanel(new CardLayout());
        cards.add(initialPanel, initial);
        cards.add(bookPanel, bookInitial);
        cards.add(patronPanel, patronInitial);
        cards.add(bookSearchResultPanel, bookSearchResult);
        cards.add(patronSearchResultPanel, patronSearchResult);
        cards.add(bookFullInfoPanel, bookFullInfo);
        cards.add(patronFullInfoPanel, patronFullInfo);
    }
    
    //Make JPanels for each different screen of the GUI
    
    /**Makes the JPanel for detailed info on a book
      *
      *@return a JPanel
    **/
    public JPanel makeBookFullInfoPanel() {
        bookFullInfoPanel = new JPanel();
        
        //want this to be string representation of the book they just clicked on
        //JLabel bookInfo = new JLabel(b.toString()); //where b is the book
        //bookFullInfoPanel.add(bookInfo);

        bookFullInfoPanel.add(newSearchButton);
        return bookFullInfoPanel;
    }
    
    /**Makes the JPanel for the result of searching for a book
      *
      *@return a JPanel
    **/
    public JPanel makeBookSearchResultPanel() {
        JPanel bookSearchResult = new JPanel();
        
        bookSearchResultsLabel = new JLabel("Result of search through book catalogue...");
        bookSearchResultsLabel.setForeground(Color.blue);
        bookSearchResultsLabel.setFont(new Font("Times", Font.PLAIN, 12));

        bookSearchResult.add(bookSearchResultsLabel);

        bookSearchResult.add(newSearchButton);
        return bookSearchResult;
    }
    
    /**Makes the JPanel for the result of searching for a patron
      *
      *@return a JPanel
    **/
    public JPanel makePatronSearchResultPanel() {
        JPanel patronSearchResult = new JPanel();

        patronSearchResultsLabel = new JLabel("Result of search through patron catalogue...");
        patronSearchResultsLabel.setForeground(Color.blue);
        patronSearchResultsLabel.setFont(new Font("Times", Font.PLAIN, 12));

        patronSearchResult.add(patronSearchResultsLabel);

        patronSearchResult.add(newSearchButton);
        return patronSearchResult;
    }
      
    /**Makes the JPanel for detailed info on a patron
      *
      *@return a JPanel
    **/
    public JPanel makePatronFullInfoPanel() {
       JPanel patronFullInfoResult = new JPanel();
       
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

        bookCategories = new JComboBox();
        bookCategories.addItem("Title");
        bookCategories.addItem("Author");
        bookCategories.addItem("Barcode Number");
        bookCategories.setSelectedIndex(0);
        bookCategories.addActionListener(this);

        bookPanel.add(selectLabel);
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

        enterPatronInfo = new JTextField("Enter patron information", 50);

        patronCategories = new JComboBox();
        patronCategories.addItem("Name");
        patronCategories.addItem("Library Card Number");
        patronCategories.setSelectedIndex(0);
        patronCategories.addActionListener(this);

        patronPanel.add(selectLabel);
        patronPanel.add(enterPatronInfo);
        patronPanel.add(patronCategories);
        patronPanel.add(searchForPatronButton);
        patronPanel.add(newSearchButton);

        return patronPanel;
    }
    
    /**Handles events. 
    **/
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
        
        CardLayout cl = (CardLayout)(cards.getLayout());

        if (source == bookSearchButton) {
            cl.show(cards, bookInitial);
        } else if (source == patronSearchButton) {
            cl.show(cards, patronInitial);
        } else if (source == newSearchButton) {
            cl.show(cards, initial);
        }
        //check
        //else if (source == searchForBookButton) {
            //search for book 
    }
    
}
