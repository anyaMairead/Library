/**AUTHOR: Laurence Toal
  *CLASS: CS 230 Data Structures
  *Final Project
  *LAST MODIFIED: 12 December 2011
  *Class Description: Launches the GUI
**/

import javax.swing.*;
import java.awt.*;

public class LibraryGUI {
 
    /**Main method to create and launch the GUI
    **/
    public static void main(String[] args) {
        JFrame frame = new JFrame("Library Search Program");
        
        frame.getContentPane().add(new LibraryPanel(), BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setDefaultLookAndFeelDecorated(true);
        frame.setSize(550,500);
        frame.setVisible(true);
    }
    
}
