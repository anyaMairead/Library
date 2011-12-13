/**AUTHOR: Laurence Toal
  *CLASS: CS 230 Data Structures
  *Final Project Phase 2 - Class Outlines
  *LAST MODIFIED: 22 November 2011
**/

import javax.swing.*;
import java.awt.*;

public class LibraryGUI {
 
    /**Main method to create and launch the GUI
    **/
    public static void main(String[] args) {
        JFrame frame = new JFrame("Library Search Program");
  
        //LibraryPanel library = new LibraryPanel();
        frame.getContentPane().add(new LibraryPanel(), BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setDefaultLookAndFeelDecorated(true);
        frame.setSize(550,500);
        frame.setVisible(true);
    }
    
}
