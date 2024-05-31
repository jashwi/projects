

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

public class quiz extends JFrame implements ActionListener {
   String[][] questions = new String[10][5];
   String[][] answers = new String[10][2];
   String[][] useranswers = new String[10][1];
   JLabel qno;
   JLabel question;
   JRadioButton opt1;
   JRadioButton opt2;
   JRadioButton opt3;
   JRadioButton opt4;
   ButtonGroup groupoptions;
   JButton next;
   JButton submit;
   JButton lifeline;
   public static int timer = 15;
   public static int ans_given = 0;
   public static int count = 0;
   public static int score = 0;
   String name;

   quiz(String name) {
      this.name = name;
      this.setBounds(50, 0, 1440, 850);
      this.getContentPane().setBackground(Color.WHITE);
      this.setLayout((LayoutManager)null);
      ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/quiz.jpg"));
      JLabel image = new JLabel(i1);
      image.setBounds(0, 0, 1440, 392);
      this.add(image);
      this.qno = new JLabel();
      this.qno.setBounds(100, 450, 50, 30);
      this.qno.setFont(new Font("Tahoma", 0, 24));
      this.add(this.qno);
      this.question = new JLabel();
      this.question.setBounds(150, 450, 900, 30);
      this.question.setFont(new Font("Tahoma", 0, 24));
      this.add(this.question);
      this.questions[0][0] = "Which is used to find and fix bugs in the Java programs.?";
      this.questions[0][1] = "JVM";
      this.questions[0][2] = "JDB";
      this.questions[0][3] = "JDK";
      this.questions[0][4] = "JRE";
      this.questions[1][0] = "What is the return type of the hashCode() method in the Object class?";
      this.questions[1][1] = "int";
      this.questions[1][2] = "Object";
      this.questions[1][3] = "long";
      this.questions[1][4] = "void";
      this.questions[2][0] = "Which package contains the Random class?";
      this.questions[2][1] = "java.util package";
      this.questions[2][2] = "java.lang package";
      this.questions[2][3] = "java.awt package";
      this.questions[2][4] = "java.io package";
      this.questions[3][0] = "An interface with no fields or methods is known as?";
      this.questions[3][1] = "Runnable Interface";
      this.questions[3][2] = "Abstract Interface";
      this.questions[3][3] = "Marker Interface";
      this.questions[3][4] = "CharSequence Interface";
      this.questions[4][0] = "In which memory a String is stored, when we create a string using new operator?";
      this.questions[4][1] = "Stack";
      this.questions[4][2] = "String memory";
      this.questions[4][3] = "Random storage space";
      this.questions[4][4] = "Heap memory";
      this.questions[5][0] = "Which of the following is a marker interface?";
      this.questions[5][1] = "Runnable interface";
      this.questions[5][2] = "Remote interface";
      this.questions[5][3] = "Readable interface";
      this.questions[5][4] = "Result interface";
      this.questions[6][0] = "Which keyword is used for accessing the features of a package?";
      this.questions[6][1] = "import";
      this.questions[6][2] = "package";
      this.questions[6][3] = "extends";
      this.questions[6][4] = "export";
      this.questions[7][0] = "In java, jar stands for?";
      this.questions[7][1] = "Java Archive Runner";
      this.questions[7][2] = "Java Archive";
      this.questions[7][3] = "Java Application Resource";
      this.questions[7][4] = "Java Application Runner";
      this.questions[8][0] = "Which of the following is a mutable class in java?";
      this.questions[8][1] = "java.lang.StringBuilder";
      this.questions[8][2] = "java.lang.Short";
      this.questions[8][3] = "java.lang.Byte";
      this.questions[8][4] = "java.lang.String";
      this.questions[9][0] = "Which of the following option leads to the portability and security of Java?";
      this.questions[9][1] = "Bytecode is executed by JVM";
      this.questions[9][2] = "The applet makes the Java code secure and portable";
      this.questions[9][3] = "Use of exception handling";
      this.questions[9][4] = "Dynamic binding between objects";
      this.answers[0][1] = "JDB";
      this.answers[1][1] = "int";
      this.answers[2][1] = "java.util package";
      this.answers[3][1] = "Marker Interface";
      this.answers[4][1] = "Heap memory";
      this.answers[5][1] = "Remote interface";
      this.answers[6][1] = "import";
      this.answers[7][1] = "Java Archive";
      this.answers[8][1] = "java.lang.StringBuilder";
      this.answers[9][1] = "Bytecode is executed by JVM";
      this.opt1 = new JRadioButton();
      this.opt1.setBounds(170, 520, 700, 30);
      this.opt1.setBackground(Color.WHITE);
      this.opt1.setFont(new Font("Dialog", 0, 20));
      this.add(this.opt1);
      this.opt2 = new JRadioButton();
      this.opt2.setBounds(170, 560, 700, 30);
      this.opt2.setBackground(Color.WHITE);
      this.opt2.setFont(new Font("Dialog", 0, 20));
      this.add(this.opt2);
      this.opt3 = new JRadioButton();
      this.opt3.setBounds(170, 600, 700, 30);
      this.opt3.setBackground(Color.WHITE);
      this.opt3.setFont(new Font("Dialog", 0, 20));
      this.add(this.opt3);
      this.opt4 = new JRadioButton();
      this.opt4.setBounds(170, 640, 700, 30);
      this.opt4.setBackground(Color.WHITE);
      this.opt4.setFont(new Font("Dialog", 0, 20));
      this.add(this.opt4);
      this.groupoptions = new ButtonGroup();
      this.groupoptions.add(this.opt1);
      this.groupoptions.add(this.opt2);
      this.groupoptions.add(this.opt3);
      this.groupoptions.add(this.opt4);
      this.next = new JButton("Next");
      this.next.setBounds(1100, 550, 200, 40);
      this.next.setFont(new Font("Tahoma", 0, 22));
      this.next.setBackground(new Color(30, 144, 255));
      this.next.setForeground(Color.WHITE);
      this.next.addActionListener(this);
      this.add(this.next);
      this.lifeline = new JButton("50-50 Lifeline");
      this.lifeline.setBounds(1100, 630, 200, 40);
      this.lifeline.setFont(new Font("Tahoma", 0, 22));
      this.lifeline.setBackground(new Color(30, 144, 255));
      this.lifeline.setForeground(Color.WHITE);
      this.lifeline.addActionListener(this);
      this.add(this.lifeline);
      this.submit = new JButton("Submit");
      this.submit.setBounds(1100, 710, 200, 40);
      this.submit.setFont(new Font("Tahoma", 0, 22));
      this.submit.setBackground(new Color(30, 144, 255));
      this.submit.setForeground(Color.WHITE);
      this.submit.addActionListener(this);
      this.submit.setEnabled(false);
      this.add(this.submit);
      this.start(count);
      this.setVisible(true);
   }

   public void actionPerformed(ActionEvent ae) {
      if (ae.getSource() == this.next) {
         this.repaint();
         this.opt1.setEnabled(true);
         this.opt2.setEnabled(true);
         this.opt3.setEnabled(true);
         this.opt4.setEnabled(true);
         ans_given = 1;
         if (this.groupoptions.getSelection() == null) {
            this.useranswers[count][0] = "";
         } else {
            this.useranswers[count][0] = this.groupoptions.getSelection().getActionCommand();
         }

         if (count == 8) {
            this.next.setEnabled(false);
            this.submit.setEnabled(true);
         }

         ++count;
         this.start(count);
      } else if (ae.getSource() == this.lifeline) {
         if (count != 2 && count != 4 && count != 6 && count != 8 && count != 9) {
            this.opt1.setEnabled(false);
            this.opt4.setEnabled(false);
         } else {
            this.opt2.setEnabled(false);
            this.opt3.setEnabled(false);
         }

         this.lifeline.setEnabled(false);
      } else if (ae.getSource() == this.submit) {
         ans_given = 1;
         if (this.groupoptions.getSelection() == null) {
            this.useranswers[count][0] = "";
         } else {
            this.useranswers[count][0] = this.groupoptions.getSelection().getActionCommand();
         }

         for(int i = 0; i < this.useranswers.length; ++i) {
            if (this.useranswers[i][0].equals(this.answers[i][1])) {
               score += 10;
            } else {
               score += 0;
            }
         }

         this.setVisible(false);
         new score(this.name, score);
      }

   }

   public void paint(Graphics g) {
      super.paint(g);
      String time = "Time left - " + timer + " seconds";
      g.setColor(Color.RED);
      g.setFont(new Font("Tahoma", 1, 25));
      if (timer > 0) {
         g.drawString(time, 1100, 500);
      } else {
         g.drawString("Times up!!", 1100, 500);
      }

      --timer;

      try {
         Thread.sleep(1000L);
         this.repaint();
      } catch (Exception var4) {
         var4.printStackTrace();
      }

      if (ans_given == 1) {
         ans_given = 0;
         timer = 15;
      } else if (timer < 0) {
         timer = 15;
         this.opt1.setEnabled(true);
         this.opt2.setEnabled(true);
         this.opt3.setEnabled(true);
         this.opt4.setEnabled(true);
         if (count == 8) {
            this.next.setEnabled(false);
            this.submit.setEnabled(true);
         }

         if (count == 9) {
            if (this.groupoptions.getSelection() == null) {
               this.useranswers[count][0] = "";
            } else {
               this.useranswers[count][0] = this.groupoptions.getSelection().getActionCommand();
            }

            for(int i = 0; i < this.useranswers.length; ++i) {
               if (this.useranswers[i][0].equals(this.answers[i][1])) {
                  score += 10;
               } else {
                  score += 0;
               }
            }

            this.setVisible(false);
            new score(this.name, score);
         } else {
            if (this.groupoptions.getSelection() == null) {
               this.useranswers[count][0] = "";
            } else {
               this.useranswers[count][0] = this.groupoptions.getSelection().getActionCommand();
            }

            ++count;
            this.start(count);
         }
      }

   }

   public void start(int count) {
      this.qno.setText(count + 1 + ". ");
      this.question.setText(this.questions[count][0]);
      this.opt1.setText(this.questions[count][1]);
      this.opt1.setActionCommand(this.questions[count][1]);
      this.opt2.setText(this.questions[count][2]);
      this.opt2.setActionCommand(this.questions[count][2]);
      this.opt3.setText(this.questions[count][3]);
      this.opt3.setActionCommand(this.questions[count][3]);
      this.opt4.setText(this.questions[count][4]);
      this.opt4.setActionCommand(this.questions[count][4]);
      this.groupoptions.clearSelection();
   }

   public static void main(String[] args) {
      new quiz("User");
   }
}
