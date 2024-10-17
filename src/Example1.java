import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Example1 implements ActionListener {

    private JFrame mainFrame;
    private JPanel panel;
    private JLabel label;
    private JTextField textField;
    private JTextField textField2;
    private JTextArea textArea;
    private JButton button;
    private JPanel borderPanel;
    private JPanel gridPanel;

    public static void main (String[] args) throws MalformedURLException {
        Example1 ex = new Example1();
    }

    public Example1() throws MalformedURLException {
        mainFrame = new JFrame("HTML Search");
        mainFrame.setSize(600,400);
        borderPanel = new JPanel(new BorderLayout());
        gridPanel = new JPanel(new GridLayout(3,1));

        textField = new JTextField();
        textField2 = new JTextField();


        button = new JButton("Find Search Term");
        button.addActionListener(this);


        textArea = new JTextArea();
        textArea.setEditable(false);

        gridPanel.add(textField);
        gridPanel.add(textField2);
        gridPanel.add(button);

        borderPanel.add(gridPanel,BorderLayout.NORTH);
        borderPanel.add(textArea, BorderLayout.CENTER);

        mainFrame.add(borderPanel);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);



        textField.setText("URL:");
        textField.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals("URL:")) {
                    textField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText("URL:");
                }
            }
        });

        textField2.setText("Search Term:");
        textField2.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                if (textField2.getText().equals("Search Term:")) {
                    textField2.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField2.getText().isEmpty()) {
                    textField2.setText("Search Term:");
                }
            }



        });}

    @Override
    public void actionPerformed(ActionEvent e) {
        String URL1 = textField.getText();
        String SearchTerm =textField2.getText();

        try {
            URL url = new URL(URL1);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(url.openStream())
            );
            String line;
            while ((line = reader.readLine())  !=null) {
                line = line.toLowerCase();
                if (line.contains(SearchTerm)) {
                    System.out.println(line.indexOf(SearchTerm));
                    System.out.println(line);


                    int n = line.indexOf(SearchTerm);
                    String miniLine = line.substring(n);
                    System.out.println(miniLine);

                    textArea.setText(miniLine);
                    textArea.append(miniLine);




                }
            }
            reader.close();
        } catch (IOException ex) {
            System.out.println(e);
        }


    }
}



