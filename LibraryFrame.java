/**AUTHOR: Laurence Toal
  *CLASS: CS 230 Data Structures
  *Final Project Phase 2 - Class Outlines
  *LAST MODIFIED: 22 November 2011
**/

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class LibraryFrame extends JFrame {
  
    JPanel cards;
    
    public LibraryFrame() {
        //create a cardlayout and add each of the panels below to it
    }
    
    //Make JPanels for each different screen of the GUI
    
    /**Makes the JPanel for detailed info on a book
      *
      *@return a JPanel
    **/
    public JPanel makeBookFullInfoPanel() {
        return new JPanel();
    }
    
    /**Makes the JPanel for the result of searching for a book
      *
      *@return a JPanel
    **/
    public JPanel makeBookSearchResultPanel() {
        return new JPanel();
    }
    
    /**Makes the JPanel for the result of searching for a patron
      *
      *@return a JPanel
    **/
    public JPanel makePatronSearchResultPanel() {
        return new JPanel();
    }
      
    /**Makes the JPanel for detailed info on a patron
      *
      *@return a JPanel
    **/
    public JPanel makePatronFullInfoPanel() {
       return new JPanel();
    }
    
    /**Makes the JPanel for the initial screen
      *
      *@return a JPanel
    **/   
    public JPanel makeInitialPanel() {
        return new JPanel();
    }
    
    /**Makes the JPanel for dealing with a book
      *
      *@return a JPanel
    **/
    public JPanel makeBookPanel() {
        return new JPanel();
    }
    
    /**Makes the JPanel for dealing with a patron
      *
      *@return a JPanel
    **/
    public JPanel makePatronPanel() {
        return new JPanel();
    }
    
    /**Handles events.
      *Theoretically. 
    **/
    //public void actionPerformed(ActionEvent event) {
    //}
    
}