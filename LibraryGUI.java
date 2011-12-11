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
  
        LibraryFrame library = new LibraryFrame();
        library.getContentPane().add(library);
  
        library.pack();
        library.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        library.setVisible(true);
        library.setDefaultLookAndFeelDecorated(true);
        library.setSize(350,500);
    }
    
}