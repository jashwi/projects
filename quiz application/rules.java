
import java.awt.Color;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class rules extends JFrame implements ActionListener {
   String name;
   JButton start;
   JButton back;

   rules(String name) {
      this.name = name;
      this.getContentPane().setBackground(Color.WHITE);
      this.setLayout((LayoutManager)null);
      JLabel heading = new JLabel("Welcome " + name + " to Simple Minds");
      heading.setBounds(50, 20, 700, 30);
      heading.setFont(new Font("Viner Hand ITC", 1, 28));
      heading.setForeground(new Color(30, 144, 254));
      this.add(heading);
      JLabel rules = new JLabel();
      rules.setBounds(20, 90, 700, 350);
      rules.setFont(new Font("Tahoma", 0, 16));
      rules.setText("<html>\n" + //
                    "1. No cheating.<br><br>\n" + //
                    "2. No unauthorized materials.<br><br>\n" + //
                    "3. No communication.<br><br>\n" + //
                    "4. No disturbance.<br><br>\n" + //
                    "5. Follow instructions carefully.\n" + //
                    "</html>");
      this.add(rules);
      this.back = new JButton("Back");
      this.back.setBounds(250, 500, 100, 30);
      this.back.setBackground(new Color(30, 144, 254));
      this.back.setForeground(Color.WHITE);
      this.back.addActionListener(this);
      this.add(this.back);
      this.start = new JButton("Start");
      this.start.setBounds(400, 500, 100, 30);
      this.start.setBackground(new Color(30, 144, 254));
      this.start.setForeground(Color.WHITE);
      this.start.addActionListener(this);
      this.add(this.start);
      this.setSize(800, 650);
      this.setLocation(350, 100);
      this.setVisible(true);
   }

   public void actionPerformed(ActionEvent ae) {
      if (ae.getSource() == this.start) {
         this.setVisible(false);
         new quiz(this.name);
      } else {
         this.setVisible(false);
         new login();
      }

   }

   public static void main(String[] args) {
      new rules("User");
   }
}
