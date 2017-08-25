package GUI;

import java.awt.Component;
import java.awt.Desktop;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.print.DocFlavor;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import net.proteanit.sql.DbUtils;
import org.ibex.nestedvm.util.Seekable;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Rami M.Mohsen
 */
public class EasyStat extends javax.swing.JFrame {

    private Connection connection = null;
    private PreparedStatement pst = null;
    private ResultSet res = null;
    byte[] imageperson = null;

    public EasyStat() {

        initComponents();
        init();
        connection = javaDBconnection.dbconection();
        updatestudentinforamtion();
        updatestudent();
        Date_Time();
    }

    private void init() {
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
    }

    private void close() {
        WindowEvent wi = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wi);
    }

    private void updatestudentinforamtion() {
        try {
            String sql = "select Student_ID, First_name, Last_name, Department, "
                    + "Series, Age, Height, Gender, Weight, Blood from Student_Info ";
            pst = connection.prepareStatement(sql);
            res = pst.executeQuery();
            StudentInfo.setModel(DbUtils.resultSetToTableModel(res));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex);
        }

    }

    private void updatestudent() {
        try {
            String sql = "select Student_ID, First_name from Student_Info ";
            pst = connection.prepareStatement(sql);
            res = pst.executeQuery();
            Table.setModel(DbUtils.resultSetToTableModel(res));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex);
        }

    }

    private Image scaleimage(byte[] imge, int w, int h) {
        BufferedImage resize = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        try {
            Graphics2D d = resize.createGraphics();
            d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

            ByteArrayInputStream in = new ByteArrayInputStream(imge);
            BufferedImage b = ImageIO.read(in);
            d.drawImage(b, 0, 0, w, h, null);
            d.dispose();

        } catch (IOException e) {

            JOptionPane.showMessageDialog(rootPane, e);
        }
        return resize;
    }

    private void GetValues() {
        try {
            StudentID.setText(res.getString("Student_ID"));
            Age.setText(res.getString("Age"));
            Department.setText(res.getString("Department"));
            StudentFN.setText(res.getString("First_name"));
            StudentLN.setText(res.getString("Last_name"));
            Height.setText(res.getString("Height"));
            Weight.setText(res.getString("Weight"));
            Level.setText(res.getString("Series"));
            Blood.setText(res.getString("Blood"));
            Gender.setSelectedItem(res.getString("Gender"));
            byte[] imge = res.getBytes("Photo");
            ImageIcon format = new ImageIcon(scaleimage(imge, PhotoLable.getWidth(), PhotoLable.getHeight()));
            PhotoLable.setIcon(format);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, ex);
        }
    }

    private void OnlineHelp(String URL) throws IOException {
        try {
            Desktop.getDesktop().browse(URI.create(URL));
        } catch (IOException ex) {
            throw new IOException("Can not open the Browser");
        }
    }

    private void Date_Time() {
        Calendar cal = new GregorianCalendar();
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        Date.setText(day + "/" + (month + 1) + "/" + year);

        int sec = cal.get(Calendar.SECOND);
        int mi = cal.get(Calendar.MINUTE);
        int hour = cal.get(Calendar.HOUR);
        Time.setText(hour + ":" + mi + ":" + sec);

    }

    private static BufferedImage GetScreen(Component com) {
        BufferedImage image = new BufferedImage(com.getWidth(), com.getHeight(), BufferedImage.TYPE_INT_BGR);
        com.paint(image.getGraphics());

        return image;
    }

    private static void saveimage(Component com, String filename) throws IOException {
        BufferedImage img = GetScreen(com);
        ImageIO.write(img, "png", new File(filename));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        captured = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton_Signout = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        StudentInfo = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        Search = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnADD = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        panelStudent = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        StudentID = new javax.swing.JTextField();
        StudentLN = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        Level = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        StudentFN = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Blood = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        Weight = new javax.swing.JTextField();
        Height = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        Age = new javax.swing.JTextField();
        Department = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        Gender = new javax.swing.JComboBox<>();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Table = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        PhotoLable = new javax.swing.JLabel();
        txtPath = new javax.swing.JTextField();
        btn_Upload = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem_close = new javax.swing.JMenuItem();
        jMenuItem_Exit = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        Date = new javax.swing.JMenu();
        Time = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Easy Stat");
        setPreferredSize(new java.awt.Dimension(1400, 700));

        jToolBar1.setRollover(true);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/if_plus_1282963.png"))); // NOI18N
        jButton3.setText("Add");
        jButton3.setToolTipText("Add New Student");
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton3);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/if_icons_update_1564533.png"))); // NOI18N
        jButton4.setText("Update");
        jButton4.setToolTipText("Update Student Inforamtion ");
        jButton4.setFocusable(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton4);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/if_minus_1645995.png"))); // NOI18N
        jButton5.setText("Delete");
        jButton5.setToolTipText("Delete selected Student");
        jButton5.setFocusable(false);
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton5);

        captured.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/if_Camera_2431363.png"))); // NOI18N
        captured.setText("Capture");
        captured.setToolTipText("Capture the student panel");
        captured.setFocusable(false);
        captured.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        captured.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        captured.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                capturedActionPerformed(evt);
            }
        });
        jToolBar1.add(captured);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/if_Button Info_58482.png"))); // NOI18N
        jButton1.setText("Online Help");
        jButton1.setToolTipText("Get Connected with the author Now !");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);

        jButton_Signout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/if_logout_48698.png"))); // NOI18N
        jButton_Signout.setText("Sign Out");
        jButton_Signout.setToolTipText("Go to Login page");
        jButton_Signout.setFocusable(false);
        jButton_Signout.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton_Signout.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton_Signout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_SignoutActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton_Signout);

        jPanel1.setBackground(new java.awt.Color(0, 204, 204));

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Action Panel", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        StudentInfo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        StudentInfo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        StudentInfo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                StudentInfoMouseClicked(evt);
            }
        });
        StudentInfo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                StudentInfoKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(StudentInfo);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 591, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 143, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Data Table", jPanel2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1341, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 283, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Chart", jPanel3);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1341, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 283, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Statistics", jPanel4);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1341, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 283, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Documents", jPanel5);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1341, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 283, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Email", jPanel6);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel7.setBackground(new java.awt.Color(0, 204, 204));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " System Commands", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        Search.setText("Search...");
        Search.setToolTipText("Search by First name or ID");
        Search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SearchMouseClicked(evt);
            }
        });
        Search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                SearchKeyReleased(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/if_search_383226.png"))); // NOI18N
        jButton2.setToolTipText("Search");

        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/if_edit_173002.png"))); // NOI18N
        btnEdit.setText("Edit");
        btnEdit.setToolTipText("Edit student information");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/if_Delete_1493279.png"))); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.setToolTipText("Remove student");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnADD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/if_addthis_395263.png"))); // NOI18N
        btnADD.setText("Add");
        btnADD.setToolTipText("Add new student");
        btnADD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnADDActionPerformed(evt);
            }
        });

        btnClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/if_edit-clear_118917.png"))); // NOI18N
        btnClear.setText("Clear");
        btnClear.setToolTipText("Clear all fields ");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnADD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                    .addComponent(btnEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnClear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(btnADD)
                .addGap(18, 18, 18)
                .addComponent(btnDelete)
                .addGap(18, 18, 18)
                .addComponent(btnEdit)
                .addGap(18, 18, 18)
                .addComponent(btnClear)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(Search)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelStudent.setBackground(new java.awt.Color(0, 204, 204));
        panelStudent.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Student Info", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel1.setText("Student ID ");

        jLabel2.setText("First Nmae");

        jLabel3.setText("Level");

        jLabel4.setText("Last Name");

        jLabel6.setText("Blood");

        jLabel8.setText("Weight");

        jLabel9.setText("Height");

        jLabel10.setText("Age");

        jLabel11.setText("Department");

        jLabel5.setText("Gender");

        Gender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));

        javax.swing.GroupLayout panelStudentLayout = new javax.swing.GroupLayout(panelStudent);
        panelStudent.setLayout(panelStudentLayout);
        panelStudentLayout.setHorizontalGroup(
            panelStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelStudentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelStudentLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(StudentLN, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelStudentLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(StudentFN, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelStudentLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(StudentID, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelStudentLayout.createSequentialGroup()
                        .addGroup(panelStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Department)
                            .addComponent(Level, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 141, Short.MAX_VALUE)
                .addGroup(panelStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelStudentLayout.createSequentialGroup()
                        .addGroup(panelStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(27, 27, 27))
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addGroup(panelStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelStudentLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(panelStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Height, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(Blood)
                                .addComponent(Weight)
                                .addComponent(Gender, 0, 100, Short.MAX_VALUE))))
                    .addComponent(Age, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29))
        );
        panelStudentLayout.setVerticalGroup(
            panelStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelStudentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelStudentLayout.createSequentialGroup()
                        .addGroup(panelStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(StudentID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(StudentFN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(StudentLN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(panelStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Department, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Level, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)))
                    .addGroup(panelStudentLayout.createSequentialGroup()
                        .addGroup(panelStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(Age, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(Height, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(Weight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(panelStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(Gender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Blood, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(0, 204, 204));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Edit", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        Table.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        Table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableMouseClicked(evt);
            }
        });
        Table.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TableKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(Table);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel8.setBackground(new java.awt.Color(0, 204, 204));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Student Photo", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PhotoLable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(PhotoLable, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                .addContainerGap())
        );

        txtPath.setEnabled(false);

        btn_Upload.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/if_icon-93-inbox-upload_314777.png"))); // NOI18N
        btn_Upload.setText("Upload");
        btn_Upload.setToolTipText("Select a photo from your computer ");
        btn_Upload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_UploadActionPerformed(evt);
            }
        });

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/if_floppy_disk_save_103863.png"))); // NOI18N
        jButton7.setText("Save");
        jButton7.setToolTipText("Save photo");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(txtPath)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_Upload)))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Upload))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenu1.setMnemonic('F');
        jMenu1.setText("File");
        jMenu1.setToolTipText("");

        jMenuItem_close.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem_close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/if_Close_1891023.png"))); // NOI18N
        jMenuItem_close.setText("Close");
        jMenuItem_close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_closeActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem_close);

        jMenuItem_Exit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem_Exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/if_icons_exit2_1564506.png"))); // NOI18N
        jMenuItem_Exit.setText("Exit");
        jMenuItem_Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_ExitActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem_Exit);

        jMenuBar1.add(jMenu1);

        jMenu2.setMnemonic('E');
        jMenu2.setText("Edit");
        jMenu2.setToolTipText("");

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/if_29_camera_cam_picture_photo_capture_digital_google_2109157.png"))); // NOI18N
        jMenuItem5.setText("Capture student information");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jMenuBar1.add(jMenu2);

        jMenu3.setMnemonic('H');
        jMenu3.setText("Help");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/if_Facebook-01_1961303.png"))); // NOI18N
        jMenuItem1.setText("   Get Help From The Author    ");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/if_linkedin_986950.png"))); // NOI18N
        jMenuItem2.setText("   Connect On LinkedIn  ");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem2);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/if_youtube_986962.png"))); // NOI18N
        jMenuItem3.setText("   Subscribe To Youtube Channel ");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/if_github_401441.png"))); // NOI18N
        jMenuItem4.setText("  GitHub");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        jMenuBar1.add(jMenu3);

        jMenu4.setMnemonic('A');
        jMenu4.setText("About");
        jMenuBar1.add(jMenu4);

        Date.setMnemonic('D');
        Date.setText("Date");
        Date.setToolTipText("");
        jMenuBar1.add(Date);

        Time.setMnemonic('T');
        Time.setText("Time");
        Time.setToolTipText("");
        jMenuBar1.add(Time);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelStudent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelStudent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem_closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_closeActionPerformed

        try {
            close();
            Login obj = new Login();
            obj.setVisible(true);

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(rootPane, ex);
        } finally {
            try {
                res.close();
                pst.close();
                connection.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(rootPane, ex);
            }

        }
    }//GEN-LAST:event_jMenuItem_closeActionPerformed

    private void jMenuItem_ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_ExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem_ExitActionPerformed

    private void jButton_SignoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_SignoutActionPerformed
        try {
            close();
            Login obj = new Login();
            obj.setVisible(true);

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(rootPane, ex);
        } finally {
            try {
                res.close();
                pst.close();
                connection.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(rootPane, ex);
            }

        }
    }//GEN-LAST:event_jButton_SignoutActionPerformed

    private void TableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableMouseClicked

        try {
            int row = Table.getSelectedRow();
            String table = (Table.getModel().getValueAt(row, 0).toString());
            String sql = "select * from Student_Info where Student_ID = '" + table + "' ";
            pst = connection.prepareStatement(sql);
            res = pst.executeQuery();
            if (res.next()) {
                GetValues();

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex);

        }


    }//GEN-LAST:event_TableMouseClicked

    private void TableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TableKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_UP || evt.getKeyCode() == KeyEvent.VK_DOWN) {
            try {
                int row = Table.getSelectedRow();
                String table = (Table.getModel().getValueAt(row, 0).toString());
                String sql = "select * from Student_Info where Student_ID = '" + table + "' ";
                pst = connection.prepareStatement(sql);
                res = pst.executeQuery();
                if (res.next()) {
                    GetValues();

                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(rootPane, ex);

            }
        }


    }//GEN-LAST:event_TableKeyReleased

    private void StudentInfoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_StudentInfoMouseClicked
        try {
            int row = StudentInfo.getSelectedRow();
            String table = (StudentInfo.getModel().getValueAt(row, 0).toString());
            String sql = "select * from Student_Info where Student_ID = '" + table + "' ";
            pst = connection.prepareStatement(sql);
            res = pst.executeQuery();
            if (res.next()) {
                GetValues();

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex);

        }

    }//GEN-LAST:event_StudentInfoMouseClicked

    private void StudentInfoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_StudentInfoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_UP || evt.getKeyCode() == KeyEvent.VK_DOWN) {
            try {
                int row = StudentInfo.getSelectedRow();
                String table = (StudentInfo.getModel().getValueAt(row, 0).toString());
                String sql = "select * from Student_Info where Student_ID = '" + table + "' ";
                pst = connection.prepareStatement(sql);
                res = pst.executeQuery();
                if (res.next()) {
                    GetValues();

                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(rootPane, ex);

            }
        }
    }//GEN-LAST:event_StudentInfoKeyReleased

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed

        try {
            OnlineHelp("https://www.facebook.com/Ramimohamedmohsen");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        try {
            OnlineHelp("https://www.youtube.com/channel/UCBkCKwQFVnSXTstKwr5a-Pw"); // TODO add your handling code here:
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());

        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed

        try {
            OnlineHelp("https://www.linkedin.com/in/rami-mohsen/");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed

        try {
            OnlineHelp("https://github.com/ramimohsen/Easy_Stat");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }


    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            OnlineHelp("https://www.facebook.com/Ramimohamedmohsen");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void SearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SearchKeyReleased

        try {
            String sql = "select * from Student_Info where First_name = ?";
            pst = connection.prepareStatement(sql);
            pst.setString(1, Search.getText());
            res = pst.executeQuery();
            if (res.next()) {
                GetValues();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex);
        }
        try {
            String sql1 = "select * from Student_Info where Student_ID = ?";
            pst = connection.prepareStatement(sql1);
            pst.setString(1, Search.getText());
            res = pst.executeQuery();
            if (res.next()) {
                GetValues();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex);
        }
    }//GEN-LAST:event_SearchKeyReleased

    private void SearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SearchMouseClicked
        Search.setText("");        // TODO add your handling code here:
    }//GEN-LAST:event_SearchMouseClicked

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        ArrayList<JTextField> s = new ArrayList<JTextField>() {
            {
                add(Age);
                add(StudentFN);
                add(Blood);
                add(StudentLN);
                add(Department);
                add(Level);
                add(StudentID);
                add(Height);
                add(Weight);
            }
        };
        s.stream().forEach(x -> x.setText(null));
        Gender.setSelectedItem(null);
        PhotoLable.setIcon(null);
        txtPath.setText(null);

    }//GEN-LAST:event_btnClearActionPerformed

    private void btnADDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnADDActionPerformed

        String sql = "insert into Student_Info (Student_ID,First_name,Last_name,Department"
                + ",Series,Age,Height,Weight,Gender,Blood)"
                + "values(?,?,?,?,?,?,?,?,?,?)";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, StudentID.getText());
            pst.setString(2, StudentFN.getText());
            pst.setString(3, StudentLN.getText());
            pst.setString(4, Department.getText());
            pst.setString(5, Level.getText());
            pst.setString(6, Age.getText());
            pst.setString(7, Height.getText());
            pst.setString(8, Weight.getText());
            pst.setString(9, (String) Gender.getSelectedItem());
            pst.setString(10, Blood.getText());
            pst.execute();
            JOptionPane.showMessageDialog(rootPane, "New Student Has been Saved Successfully");
        } catch (Exception e) {

            JOptionPane.showMessageDialog(rootPane, e);
        }

        updatestudent();
        updatestudentinforamtion();
    }//GEN-LAST:event_btnADDActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            String sql = "insert into Student_Info (Student_ID,First_name,Last_name,Department"
                    + ",Series,Age,Height,Weight,Gender,Blood)"
                    + "values(?,?,?,?,?,?,?,?,?,?)";
            pst = connection.prepareStatement(sql);
            pst.setString(1, StudentID.getText());
            pst.setString(2, StudentFN.getText());
            pst.setString(3, StudentLN.getText());
            pst.setString(4, Department.getText());
            pst.setString(5, Level.getText());
            pst.setString(6, Age.getText());
            pst.setString(7, Height.getText());
            pst.setString(8, Weight.getText());
            pst.setString(9, (String) Gender.getSelectedItem());
            pst.setString(10, Blood.getText());
            pst.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex);
        }
        updatestudent();
        updatestudentinforamtion();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed

        String sql = "update  Student_Info set First_name=?,Last_name=?,Department=?"
                + ",Series=?,Age=?,Height=?,Weight=?,Gender=?,Blood=?"
                + "where Student_ID=?";
        try {
            pst = connection.prepareStatement(sql);

            pst.setString(1, StudentFN.getText());
            pst.setString(2, StudentLN.getText());
            pst.setString(3, Department.getText());
            pst.setString(4, Level.getText());
            pst.setString(5, Age.getText());
            pst.setString(6, Height.getText());
            pst.setString(7, Weight.getText());
            pst.setString(8, (String) Gender.getSelectedItem());
            pst.setString(9, Blood.getText());
            pst.setString(10, StudentID.getText());

            pst.execute();

            JOptionPane.showMessageDialog(rootPane, "New Student Has been Saved Successfully");
        } catch (Exception e) {

            JOptionPane.showMessageDialog(rootPane, e);
        }

        updatestudent();
        updatestudentinforamtion();


    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed

        int warning = JOptionPane.showConfirmDialog(rootPane, "Do you realy want to Delete ?", "Delete", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (warning == 0) {
            String sql = "delete from Student_Info where Student_ID =?";
            try {
                pst = connection.prepareStatement(sql);
                pst.setString(1, StudentID.getText());

                pst.execute();

                JOptionPane.showMessageDialog(rootPane, "Deleted");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(rootPane, ex);

            }
            updatestudent();
            updatestudentinforamtion();
            ArrayList<JTextField> s = new ArrayList<JTextField>() {
                {
                    add(Age);
                    add(StudentFN);
                    add(Blood);
                    add(StudentLN);
                    add(Department);
                    add(Level);
                    add(StudentID);
                    add(Height);
                    add(Weight);
                }
            };
            s.stream().forEach(x -> x.setText(null));
            Gender.setSelectedItem(null);

        }

    }//GEN-LAST:event_btnDeleteActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        try {
            saveimage(panelStudent, "Captuered Image.png");
            JOptionPane.showMessageDialog(rootPane, "Image Captured");
        } catch (HeadlessException | IOException e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void capturedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_capturedActionPerformed
        try {
            saveimage(panelStudent, "Captuered Image.png");
            JOptionPane.showMessageDialog(rootPane, "Image Captured");
        } catch (HeadlessException | IOException e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }  // TODO add your handling code here:
    }//GEN-LAST:event_capturedActionPerformed

    private void btn_UploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_UploadActionPerformed

        JFileChooser photo = new JFileChooser();
        photo.showOpenDialog(null);
        File f = photo.getSelectedFile();
        String name = f.getAbsolutePath();
        txtPath.setText(name);

        try {

            FileInputStream in = new FileInputStream(f);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] Buff = new byte[1024];
            for (int reed; (reed = in.read(Buff)) != -1;) {
                out.write(Buff, 0, reed);
            }
            imageperson = out.toByteArray();
            ImageIcon format = new ImageIcon(scaleimage(imageperson, PhotoLable.getWidth(), PhotoLable.getHeight()));
            PhotoLable.setIcon(format);

        } catch (IOException e) {

            JOptionPane.showMessageDialog(rootPane, e);
        }
    }//GEN-LAST:event_btn_UploadActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        String sql = "update Student_Info set Photo = ? where Student_ID = ?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setBytes(1, imageperson);
            pst.setString(2, StudentID.getText());

            pst.execute();
            JOptionPane.showMessageDialog(rootPane, "Image hase been saved");
            txtPath.setText(null);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);

        }
    }//GEN-LAST:event_jButton7ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windoes".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EasyStat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new EasyStat().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Age;
    private javax.swing.JTextField Blood;
    private javax.swing.JMenu Date;
    private javax.swing.JTextField Department;
    private javax.swing.JComboBox<String> Gender;
    private javax.swing.JTextField Height;
    private javax.swing.JTextField Level;
    private javax.swing.JLabel PhotoLable;
    private javax.swing.JTextField Search;
    private javax.swing.JTextField StudentFN;
    private javax.swing.JTextField StudentID;
    private javax.swing.JTable StudentInfo;
    private javax.swing.JTextField StudentLN;
    private javax.swing.JTable Table;
    private javax.swing.JMenu Time;
    private javax.swing.JTextField Weight;
    private javax.swing.JButton btnADD;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btn_Upload;
    private javax.swing.JButton captured;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton_Signout;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem_Exit;
    private javax.swing.JMenuItem jMenuItem_close;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JPanel panelStudent;
    private javax.swing.JTextField txtPath;
    // End of variables declaration//GEN-END:variables
}
