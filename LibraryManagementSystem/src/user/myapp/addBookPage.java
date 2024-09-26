package user.myapp;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class addBookPage extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JTextField titleField, authorField;
    private JButton addButton, backButton;

    public addBookPage(int adminId) {
        setLayout(new FlowLayout());

        // Labels and text fields
        add(new JLabel("Title:"));
        titleField = new JTextField(20);
        add(titleField);

        add(new JLabel("Author:"));
        authorField = new JTextField(20);
        add(authorField);

        // Add and back buttons
        addButton = new JButton("Add Book");
        addButton.addActionListener(this);
        add(addButton);

        backButton = new JButton("Back");
        backButton.addActionListener(e -> {
			new adminPage(adminId); // Navigate back to admin page
            dispose();
        });
        add(backButton);

        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String title = titleField.getText();
        String author = authorField.getText();

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Create a connection to the database
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lib", "root", "123456");

            // Create a prepared statement to insert the book data
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO books (title, author, availability) VALUES (?, ?, ?)");
            pstmt.setString(1, title);
            pstmt.setString(2, author);
            pstmt.setBoolean(3, true);  // Set availability to true (available)

            // Execute the prepared statement
            pstmt.executeUpdate();

            // Close the connection
            conn.close();

            // Show success message
            JOptionPane.showMessageDialog(this, "Book added successfully!");

            // Clear the fields
            titleField.setText("");
            authorField.setText("");

        } catch (SQLException ex) {
            // Handle the SQL exception
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            // Handle class not found exception
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
    
    }
}