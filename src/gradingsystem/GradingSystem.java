package gradingsystem;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.text.DecimalFormat;
import java.awt.Font;

public class GradingSystem extends JFrame {

    JButton GPA, CGPA, next, setscale;
    static int size;
    static JTextField namef[];
    static JTextField markf[];
    static JTextField hourf[];//store credit hour for each subject
    static JTextField item[];//store cgpa value
    static JTextField[] storescale = new JTextField[12];
    static JTextField[] storegletter = new JTextField[12];
    static JFrame Input, frame, Output, viewcgpa, scaledisplay;
    DecimalFormat decimalformat = new DecimalFormat(); //
    //used for setting your own scale for GPA
    static double mark100 = 100, mark95 = 95, mark80 = 80, mark78 = 78, mark75 = 75, mark70 = 70, mark65 = 65, mark60 = 60, mark45 = 45, mark40 = 40, mark35 = 35, mark0 = 0;
    static String setmark0 = " ", Aplus = "A+", Aminus = "A-", A = "A", Bplus = "B+", Bminus = "B-", B = "B", Cplus = "C+", Cminus = "C-", C = "C", D = "D", F = "F";

    GradingSystem() {

        super("GRADE CALCULATOR");

        JPanel p1 = new JPanel(new FlowLayout());

        GPA = new JButton("GPA");
        CGPA = new JButton("CGPA");
        setscale = new JButton("Set your own Scale");

        Cursor cursor = new Cursor(Cursor.HAND_CURSOR);

        GPA.setCursor(cursor);
        CGPA.setCursor(cursor);
        setscale.setCursor(cursor);

        GPA.setToolTipText("Grade point Average");
        CGPA.setToolTipText("Comulative Grade point Average");

        p1.add(GPA);
        p1.add(CGPA);
        p1.add(setscale);

        add(p1);

        Grade finder = new Grade();

        GPA.addActionListener(finder);
        CGPA.addActionListener(finder);
        setscale.addActionListener(finder);
    }

    class Grade extends JFrame implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == GPA) {

                frame.setVisible(false);
                String ssize = JOptionPane.showInputDialog("Enter number of subject");
                size = Integer.parseInt(ssize);

                Input = new JFrame("input grade");

                Input.setSize(600, 350);
                Input.setLocationRelativeTo(null);
                Input.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                Input.setVisible(true);

                int size1 = size + 1; //adding '1' to size1 is used for placing name of the input e.g credit hour ,number

                JPanel p1 = new JPanel(new GridLayout(size1, 4));

                p1.setBorder(new LineBorder(new Color(173, 188, 202), 1));
                p1.add(new JLabel("number"));
                p1.add(new JLabel("Subject name"));
                p1.add(new JLabel("Mark(100)"));
                p1.add(new JLabel("Credit hour"));

                namef = new JTextField[size];
                markf = new JTextField[size];
                hourf = new JTextField[size];

                for (int i = 0; i < size; i++) {
                    markf[i] = new JTextField(10);
                    namef[i] = new JTextField(10);
                    hourf[i] = new JTextField(10);
                }

                for (int i = 0; i < size; i++) {
                    p1.add(new JLabel(" " + (i + 1)));
                    p1.add(namef[i]);
                    p1.add(markf[i]);
                    p1.add(hourf[i]);
                }
                next = new JButton("compute");
                JButton back = new JButton("back  ");

                back.setCursor(new Cursor(Cursor.HAND_CURSOR));
                next.setCursor(new Cursor(Cursor.HAND_CURSOR));

                JPanel p2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 15));

                p2.add(back);
                p2.add(next);

                back.addActionListener((ActionEvent e1) -> {
                    Input.setVisible(false);
                    frame.setVisible(true);
                });

                Input.add(p1, BorderLayout.CENTER);
                Input.add(p2, BorderLayout.SOUTH);

                next.addActionListener((ActionEvent e1) -> {
                    double mark[] = new double[size], ave = 0, h;
                    double amm[] = new double[size];//store grade point
                    String app[] = new String[size];//used for storing grade in letter e.g A+
                    String[] name1 = new String[size];
                    int hour[] = new int[size], sum = 0;
                    boolean truevalue = true; // for displaying grade report
                    for (int i = 0; i < size; i++) {
                        mark[i] = Double.parseDouble(markf[i].getText());
                        hour[i] = Integer.parseInt(hourf[i].getText());
                        name1[i] = String.format(namef[i].getText());
                    }
                    for (int i = 0; i < size; i++) {
                        if (mark[i] <= mark100 && mark[i] >= mark95) {
                            app[i] = Aplus;
                            amm[i] = 4;
                        } else if (mark[i] < mark95 && mark[i] >= mark80) {
                            app[i] = A;
                            amm[i] = 4;
                        } else if (mark[i] < mark80 && mark[i] >= mark78) {
                            app[i] = Aminus;
                            amm[i] = 3.75;
                        } else if (mark[i] < mark78 && mark[i] >= mark75) {
                            app[i] = Bplus;
                            amm[i] = 3.5;
                        } else if (mark[i] < mark75 && mark[i] >= mark70) {
                            app[i] = B;
                            amm[i] = 3;
                        } else if (mark[i] < mark70 && mark[i] >= mark65) {
                            app[i] = Bminus;
                            amm[i] = 2.75;
                        } else if (mark[i] < mark65 && mark[i] >= mark60) {
                            app[i] = Cplus;
                            amm[i] = 2.5;
                        } else if (mark[i] < mark60 && mark[i] >= mark45) {
                            app[i] = C;
                            amm[i] = 2;
                        } else if (mark[i] < mark45 && mark[i] >= mark40) {
                            app[i] = Cminus;
                            amm[i] = 1.75;
                        } else if (mark[i] < mark40 && mark[i] >= mark35) {
                            app[i] = D;
                            amm[i] = 1;
                        } else if (mark[i] < mark35 && mark[i] >= mark0) {
                            app[i] = F;
                            amm[i] = 0;
                        } else {
                            JOptionPane.showMessageDialog(null, "INVALID MARK PLEASE ENTER CORRECT MARK", "WARNING", JOptionPane.ERROR_MESSAGE);
                            truevalue = false;
                            break;
                        }
                    }
                    for (int i = 0; i < size; i++) {
                        sum = sum + hour[i];
                        ave = ave + (hour[i] * amm[i]);
                    }
                    h = ave / sum;
                    if (truevalue) {
                        JPanel p3 = new JPanel(new GridLayout(size, 6, 5, 5));
                        JPanel p4 = new JPanel(new GridLayout(1, 6, 5, 5));
                        LineBorder line = new LineBorder(new Color(122, 222, 122), 1);
                        p3.setBorder(line);
                        p3.setBackground(Color.white);
                        p4.setBorder(new TitledBorder("grade report"));
                        p4.add(new JLabel("number"));
                        p4.add(new JLabel("Subject name"));
                        p4.add(new JLabel("Mark(100)"));
                        p4.add(new JLabel("Credit hour"));
                        p4.add(new JLabel("L.grade"));
                        p4.add(new JLabel("G.point"));
                        for (int i = 0; i < size; i++) {
                            p3.add(new JLabel("" + (i + 1)));
                            p3.add(new JLabel(name1[i]));
                            p3.add(new JLabel("" + mark[i]));
                            p3.add(new JLabel("" + hour[i]));
                            p3.add(new JLabel(app[i]));
                            p3.add(new JLabel("" + (amm[i] * hour[i])));
                        }
                        JPanel p5 = new JPanel(new GridLayout(4, 1, 5, 5));
                        decimalformat.setMaximumFractionDigits(2);
                        p5.add(new JLabel("G.Point = " + decimalformat.format(ave)));
                        p5.add(new JLabel(" Cr.Hr = " + sum));
                        p5.add(new JLabel(" GPA = " + h));
                        p5.add(new JLabel(" =============== "));
                        JButton back1 = new JButton("back");
                        back1.setFont(new Font("serif", Font.BOLD, 16));
                        back1.setCursor(new Cursor(Cursor.HAND_CURSOR));
                        Input.setVisible(false);
                        Output = new JFrame("grade report");
                        Output.setSize(900, 500);
                        Output.setLocationRelativeTo(null);
                        Output.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        Output.setVisible(true);
                        JPanel p6 = new JPanel(new BorderLayout());
                        p6.add(p4, BorderLayout.NORTH);
                        p6.add(p3, BorderLayout.CENTER);
                        p6.add(p5, BorderLayout.SOUTH);
                        Output.add(p6, BorderLayout.CENTER);
                        Output.add(back1, BorderLayout.SOUTH);
                        back1.addActionListener((ActionEvent e2) -> {
                            Output.setVisible(false);
                            frame.setVisible(true);
                        });
                    }
                });

            } else if (e.getSource() == setscale) {

                frame.setVisible(false);
                scaledisplay = new JFrame();

                scaledisplay.setSize(1000, 500);
                scaledisplay.setLocationRelativeTo(null);
                scaledisplay.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                scaledisplay.setVisible(true);

                JPanel p1 = new JPanel(new GridLayout(11, 7));

                p1.setBorder(new TitledBorder("Enter your Scale"));
                p1.setToolTipText("press alt + s to Save");

                for (int i = 0; i < 12; i++) {
                    storescale[i] = new JTextField(10);
                    storegletter[i] = new JTextField(10);
                }

                storescale[0].setText("" + mark100);
                storescale[1].setText(String.valueOf(mark95));
                storescale[2].setText(String.valueOf(mark80));
                storescale[3].setText(String.valueOf(mark78));
                storescale[4].setText(String.valueOf(mark75));
                storescale[5].setText(String.valueOf(mark70));
                storescale[6].setText(String.valueOf(mark65));
                storescale[7].setText(String.valueOf(mark60));
                storescale[8].setText(String.valueOf(mark45));
                storescale[9].setText(String.valueOf(mark40));
                storescale[10].setText(String.valueOf(mark35));
                storescale[11].setText(String.valueOf(mark0));

                storegletter[0].setText(Aplus);
                storegletter[1].setText(A);
                storegletter[2].setText(Aminus);
                storegletter[3].setText(Bplus);
                storegletter[4].setText(B);
                storegletter[5].setText(Bminus);
                storegletter[6].setText(Cplus);
                storegletter[7].setText(C);
                storegletter[8].setText(Cminus);
                storegletter[9].setText(D);
                storegletter[10].setText(F);

                for (int i = 0; i < 11; i++) {
                    if (i == 0) {
                        p1.add(new JLabel("Mark less than or equal"));
                    } else {
                        p1.add(new JLabel("Mark less than"));
                    }

                    p1.add(storescale[i]);
                    p1.add(new JLabel("               AND "));
                    p1.add(new JLabel("greater than or equal"));
                    if (i == 10) {
                        p1.add(storescale[i + 1]);
                    } else {
                        p1.add(new JTextField(storescale[(i + 1)].getText()));
                    }
                    p1.add(new JLabel("   Grade"));
                    p1.add(storegletter[i]);

                }

                next = new JButton("Save");
                next.setCursor(new Cursor(Cursor.HAND_CURSOR));
                next.setMnemonic('s');// to save using a key alt + s

                JButton back = new JButton("back  ");
                back.setCursor(new Cursor(Cursor.HAND_CURSOR));

                JPanel p2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 15));

                p2.setBackground(Color.WHITE);
                p2.add(back);
                p2.add(next);

                scaledisplay.add(p1, BorderLayout.CENTER);
                scaledisplay.add(p2, BorderLayout.SOUTH);

                back.addActionListener((ActionEvent e1) -> {
                    scaledisplay.setVisible(false);
                    frame.setVisible(true);
                });

                next.addActionListener((ActionEvent e1) -> {
                    int confirm = JOptionPane.showConfirmDialog(null, "are you sure do you want to change grading scale?");

                    switch (confirm) {
                        case JOptionPane.YES_OPTION:
                            mark100 = Double.parseDouble(storescale[0].getText());
                            mark95 = Double.parseDouble(storescale[1].getText());
                            mark80 = Double.parseDouble(storescale[2].getText());
                            mark78 = Double.parseDouble(storescale[3].getText());
                            mark75 = Double.parseDouble(storescale[4].getText());
                            mark70 = Double.parseDouble(storescale[5].getText());
                            mark65 = Double.parseDouble(storescale[6].getText());
                            mark60 = Double.parseDouble(storescale[7].getText());
                            mark45 = Double.parseDouble(storescale[8].getText());
                            mark40 = Double.parseDouble(storescale[9].getText());
                            mark35 = Double.parseDouble(storescale[10].getText());
                            mark0 = Double.parseDouble(storescale[11].getText());
                            Aplus = storegletter[0].getText();
                            A = storegletter[1].getText();
                            Aminus = storegletter[2].getText();
                            Bplus = storegletter[3].getText();
                            B = storegletter[4].getText();
                            Bminus = storegletter[5].getText();
                            Cplus = storegletter[6].getText();
                            C = storegletter[7].getText();
                            Cminus = storegletter[8].getText();
                            D = storegletter[9].getText();
                            F = storegletter[10].getText();
                            JOptionPane.showMessageDialog(null, "Scale was Changed Successfully");
                            scaledisplay.setVisible(false);
                            frame.setVisible(true);
                            break;
                        case JOptionPane.NO_OPTION:
                            scaledisplay.setVisible(false);
                            frame.setVisible(true);
                            break;
                        case JOptionPane.CANCEL_OPTION:
                            break;
                        default:
                            break;
                    }
                });

            } else if (e.getSource() == CGPA) {
                /*
                 this method work like average it accept number of GPA from the user, then add all
                 GPA and divide by their numbers of GPA but when calculating CGPA the input are 
                 total credit hour and grade point
                 */
                String fn = JOptionPane.showInputDialog("Enter number of semister");
                size = Integer.parseInt(fn);
                frame.setVisible(false);

                viewcgpa = new JFrame("CGPA CALCULATOR");
                viewcgpa.setSize(400, 400);
                viewcgpa.setLocationRelativeTo(null);
                viewcgpa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                viewcgpa.setVisible(true);

                item = new JTextField[size];

                for (int j = 0; j < size; j++) {
                    item[j] = new JTextField(5);
                }

                JPanel p1 = new JPanel(new GridLayout(1, 2));
                Font f = new Font("serif", Font.BOLD, 20);
                JLabel l1, l2;

                l1 = new JLabel("Semister");
                l1.setFont(f);

                l2 = new JLabel("GPA");
                l2.setFont(f);

                p1.setBorder(new LineBorder(Color.GRAY));
                p1.setBackground(Color.WHITE);
                p1.add(l1);
                p1.add(l2);

                JPanel p2 = new JPanel(new GridLayout(size, 2));
                for (int j = 0; j < size; j++) {
                    p2.add(new JLabel("Semister " + (j + 1)));
                    p2.add(item[j]);
                }

                JPanel p3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 15));

                next = new JButton("next ");
                next.setCursor(new Cursor(Cursor.HAND_CURSOR));

                JButton back = new JButton("Back ");
                back.setCursor(new Cursor(Cursor.HAND_CURSOR));

                p3.setBorder(new LineBorder(Color.LIGHT_GRAY));
                p3.add(back);
                p3.add(next);

                viewcgpa.add(p1, BorderLayout.NORTH);
                viewcgpa.add(p2, BorderLayout.CENTER);
                viewcgpa.add(p3, BorderLayout.SOUTH);

                back.addActionListener((ActionEvent e1) -> {
                    viewcgpa.setVisible(false);
                    frame.setVisible(true);
                });
                next.addActionListener((ActionEvent e1) -> {
                    double su = 0, eg, g[];
                    g = new double[size];//store GPA grade point 
                    for (int j = 0; j < size; j++) {
                        g[j] = Double.parseDouble(item[j].getText());
                        if (g[j] > 4 || g[j] < 0) {
                            JOptionPane.showMessageDialog(null, "INVALID MARK PLEASE ENTER CORRECT MARK", "WARNING", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                    }
                    for (int j = 0; j < size; j++) {
                        su = su + g[j];
                    }
                    eg = su / size;
                    decimalformat.setMaximumFractionDigits(2);
                    JOptionPane.showMessageDialog(null, "Your CGPA is " + decimalformat.format(eg), "CGPA OUTPUT", JOptionPane.PLAIN_MESSAGE);
                });
            }
        }
    }

    public static void main(String[] args) {
        frame = new GradingSystem();

        frame.setSize(500, 180);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}
