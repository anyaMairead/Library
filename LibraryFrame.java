/**AUTHOR: Laurence Toal
  *CLASS: CS 230 Data Structures
  *Final Project Phase 2 - Class Outlines
  *LAST MODIFIED: 22 November 2011
**/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class LibraryFrame extends JFrame implements ActionListener {
  
    private JPanel cards, initialPanel, bookSearchResultPanel, bookFullInfoPanel, patronSearchResultPanel, patronFullInfoPanel, bookPanel, patronPanel;
    private JLabel welcomeLabel, bookSearchResultsLabel, patronSearchResultsLabel, selectLabel;
    private JButton patronSearchButton, bookSearchButton, goButton;    
    private JComboBox categories;

    public LibraryFrame() {
        selectLabel = new JLabel("Select a category to search by");
        selectLabel.setForeground(Color.blue);
        selectLabel.setFont(new Font("Times", Font.ITALIC, 12));

        goButton = new JButton("Search");
        goButton.addActionListener(this);
        goButton.setBackground(new Color(132, 112, 255));

        initialPanel = makeInitialPanel();
        bookPanel = makeBookPanel();
        patronPanel = makePatronPanel();
        bookSearchResultPanel = makeBookSearchResultPanel();
        patronSearchResultPanel = makePatronSearchResultPanel();
        bookFullInfoPanel = makeBookFullInfoPanel();
        patronFullInfoPanel = makePatronFullInfoPanel();

        cards = new JPanel(new CardLayout());
        cards.add(initialPanel);
        cards.add(bookPanel);
        cards.add(patronPanel);
        cards.add(bookSearchResultPanel);
        cards.add(patronSearchResultPanel);
        cards.add(bookFullInfoPanel);
        cards.add(patronFullInfoPanel);
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

        return new JPanel();
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

        categories = new JComboBox();
        categories.addItem("Title");
        categories.addItem("Author");
        categories.addItem("Barcode Number");
        categories.setSelectedIndex(0);
        categories.addActionListener(this);

        bookPanel.add(selectLabel);
        bookPanel.add(categories);
        bookPanel.add(goButton);

        return bookPanel;
    }
    
    /**Makes the JPanel for dealing with a patron
      *
      *@return a JPanel
    **/
    public JPanel makePatronPanel() {
        patronPanel = new JPanel();

        categories = new JComboBox();
        categories.addItem("Name");
        categories.addItem("Library Card Number");
        categories.setSelectedIndex(0);
        categories.addActionListener(this);

        patronPanel.add(selectLabel);
        patronPanel.add(categories);
        patronPanel.add(goButton);

        return new JPanel();
    }
    
    /**Handles events. 
    **/
    public void actionPerformed(ActionEvent event) {
    }
    
}
