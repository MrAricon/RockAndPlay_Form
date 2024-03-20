package abp_form;

import java.sql.*;
import java.io.*;
import validations.UserValidations;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author paucanmil
 */
public class JFrameSQLForm extends javax.swing.JFrame {

    /**
     * Creates new form JFrameSQLForm
     */
    public JFrameSQLForm() {
        initComponents();
        initConfig();
    }

    Connection connection;

    Statement statement;

    ResultSet resultSetUsers;
    
    ResultSet resultSetRanking;
    
    ResultSet resultSetEntradas;

    public void initConfig() {
        disconnectionJavaObjects();
    }
    
    public void connectionJavaObjects() {
        jButtonConnect.setEnabled(false);
        jButtonDisconnect.setEnabled(true);
        jButtonGetDataUsers.setEnabled(true);
        jTextFieldUserLogin.setEnabled(false);
        jPasswordFieldLogin.setEnabled(false);
        jButtonInsertUsers.setEnabled(true);
        jButtonGetDataRanking.setEnabled(true);
        jButtonModifyRanking.setEnabled(true);
        jButtonGetDataEntradas.setEnabled(true);
        jButtonInsertEntradas.setEnabled(true);
        jLabelLoginError.setVisible(false);
        jButtonResetUsers.setEnabled(true);
        jButtonDeleteUsers.setEnabled(true);
        jButtonModifyRanking.setEnabled(true);
        jButtonResetRanking.setEnabled(true);
        jButtonInsertEntradas.setEnabled(true);
        jButtonResetEntradas.setEnabled(true);
    }
    
    public void disconnectionJavaObjects() {
        jButtonDisconnect.setEnabled(false);
        jButtonGetDataUsers.setEnabled(false);
        jButtonInsertUsers.setEnabled(false);
        jButtonGetDataRanking.setEnabled(false);
        jButtonModifyRanking.setEnabled(false);
        jButtonGetDataEntradas.setEnabled(false);
        jButtonInsertEntradas.setEnabled(false);
        jButtonConnect.setEnabled(true);
        jTextFieldUserLogin.setEnabled(true);
        jPasswordFieldLogin.setEnabled(true);
        jLabelLoginError.setVisible(false);
        jLabelErrorNombreUsers.setVisible(false);
        jLabelErrorPasswdUsers.setVisible(false);
        jLabelErrorApellidoUsers.setVisible(false);
        jLabelErrorUsuarioUsers.setVisible(false);
        jLabelErrorCorreoUsers.setVisible(false);
        jButtonResetUsers.setEnabled(false);
        jButtonDeleteUsers.setEnabled(false);
        jLabelErrorRankingID.setVisible(false);
        jLabelErrorRankingPuntos.setVisible(false);
        jLabelErrorEntradasID.setVisible(false);
        jLabelErrorEntradasCantidad.setVisible(false);
        jButtonModifyRanking.setEnabled(false);
        jButtonResetRanking.setEnabled(false);
        jButtonInsertEntradas.setEnabled(false);
        jButtonResetEntradas.setEnabled(false);
        jLabelErrorEntradasInsert.setVisible(false);
        jLabelErrorRankingInsert.setVisible(false);
        jLabelErrorUsersInsert.setVisible(false);
    }

    public void connect() {
        String userLogin = jTextFieldUserLogin.getText();
        String passwordLogin = jPasswordFieldLogin.getText();
        
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection("jdbc:oracle:thin:@rockandplay.sytes.net:1521:orcl", userLogin, passwordLogin);

            statement = connection.createStatement();

            System.out.println("Connection - Success!");
            connectionJavaObjects();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            jLabelLoginError.setVisible(true);
        }
    }
    
    public void clearTable(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        model.setRowCount(50);
    }

    //OBTENER DATOS USUARIOS:
    public void getDataUsers() {
        clearTable(jTableUsers);
        try {
            resultSetUsers = statement.executeQuery("SELECT * FROM users ORDER BY 1");

            int count = 0;

            while (resultSetUsers.next()) {
                System.out.println(resultSetUsers.getInt(1) + " " + resultSetUsers.getString(2) + " " + resultSetUsers.getString(3) + " " + resultSetUsers.getString(4) + " " + resultSetUsers.getString(5) + " " + resultSetUsers.getString(6) + " " + resultSetUsers.getString(7));
                jTableUsers.setValueAt(resultSetUsers.getInt(1), count, 0);
                jTableUsers.setValueAt(resultSetUsers.getString(2), count, 1);
                jTableUsers.setValueAt(resultSetUsers.getString(3), count, 2);
                jTableUsers.setValueAt(resultSetUsers.getString(4), count, 3);
                jTableUsers.setValueAt(resultSetUsers.getString(5), count, 4);
                jTableUsers.setValueAt(resultSetUsers.getString(6), count, 5);
                jTableUsers.setValueAt(resultSetUsers.getString(7), count, 6);
                count++;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    
    public void resetUsersFields() {
        jTextFieldNombreUsers.setText("");
        jTextFieldApellidoUsers.setText("");
        jTextFieldUsernameUsers.setText("");
        jTextFieldCorreoUsers.setText("");
        jTextFieldPasswdUsers.setText("");
        jLabelLoginError.setVisible(false);
        jLabelErrorNombreUsers.setVisible(false);
        jLabelErrorPasswdUsers.setVisible(false);
        jLabelErrorApellidoUsers.setVisible(false);
        jLabelErrorUsuarioUsers.setVisible(false);       
        jLabelErrorCorreoUsers.setVisible(false);
    }
    
    public void resetDeleteIDField() {
        jTextFieldDeleteID.setText("");
        jLabelErrorUsersInsert.setVisible(false);
    }
    
    public void insertarDatosUsers() {
        
        String nombre_users = jTextFieldNombreUsers.getText();
        String apellido_users = jTextFieldApellidoUsers.getText();
        String username_users = jTextFieldUsernameUsers.getText();
        String correo_users = jTextFieldCorreoUsers.getText();
        String password_users = jTextFieldPasswdUsers.getText();
    
        if (!UserValidations.checkName(nombre_users)) {
            jLabelErrorNombreUsers.setVisible(true);
        }
        
        if (!UserValidations.checkSurname(apellido_users)) {
            jLabelErrorApellidoUsers.setVisible(true);
        }
        
        if (!UserValidations.checkUsername(username_users)) {
            jLabelErrorUsuarioUsers.setVisible(true);
        }
        
        if (!UserValidations.checkEmail(correo_users)) {
            jLabelErrorCorreoUsers.setVisible(true);
        }
        
        if (!UserValidations.checkPassword(password_users)) {
            jLabelErrorPasswdUsers.setVisible(true);
        }
        
        if (UserValidations.checkEmail(correo_users) && UserValidations.checkName(nombre_users) && UserValidations.checkSurname(apellido_users) && UserValidations.checkUsername(username_users) && UserValidations.checkPassword(password_users)) {
            jLabelLoginError.setVisible(false);
            jLabelErrorNombreUsers.setVisible(false);
            jLabelErrorPasswdUsers.setVisible(false);
            jLabelErrorApellidoUsers.setVisible(false);
            jLabelErrorUsuarioUsers.setVisible(false);
            jLabelErrorCorreoUsers.setVisible(false);
            try { 
                statement.executeUpdate("INSERT INTO users " +  "VALUES (seq_users_id_user.NEXTVAL,'" + nombre_users + "','" + apellido_users + "','" + username_users + "','" + correo_users + "','" + password_users + "',SYSDATE)");
                jLabelErrorUsersInsert.setVisible(false);
            } catch (Exception e) { 
                System.err.println("Hay un error! "); 
                System.err.println(e.getMessage()); 
                jLabelErrorUsersInsert.setVisible(true);
            } 
            resetUsersFields();
            getDataUsers();
        }
    }
    
    public void deleteUsers() {
        String id_user = jTextFieldDeleteID.getText();
        try {
            statement.executeUpdate("DELETE FROM ranking WHERE id_user='" + id_user + "'");
            statement.executeUpdate("DELETE FROM entradas WHERE id_user='" + id_user + "'");
            statement.executeUpdate("DELETE FROM users WHERE id_user='" + id_user + "'");
            
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        resetDeleteIDField();
        getDataUsers();
    }
    
    public void modifyDataRanking() {
        String idUserRanking = jTextFieldSelectUserRanking.getText();
        String puntosRanking = jTextFieldPuntosRanking.getText();
        
        if (!UserValidations.isNumeric(idUserRanking)) {
            jLabelErrorRankingID.setVisible(true);
        }
        
        if (!UserValidations.isNumeric(puntosRanking)) {
            jLabelErrorRankingPuntos.setVisible(true);
        }
        
        if (UserValidations.isNumeric(idUserRanking) && UserValidations.isNumeric(puntosRanking)) {
            jLabelErrorRankingID.setVisible(false);
            jLabelErrorRankingPuntos.setVisible(false);
            try { 
                statement.executeUpdate("UPDATE ranking SET puntuacion_ranking = '" + puntosRanking + "' WHERE id_user = '" + idUserRanking + "'");
                jLabelErrorRankingInsert.setVisible(false);
            } catch (Exception e) { 
                System.err.println("Hay un error! "); 
                System.err.println(e.getMessage()); 
                jLabelErrorRankingInsert.setVisible(true);
            }
            resetRankingFields();
            getDataRanking();
        }
    }
    
    public void resetRankingFields() {
        jTextFieldSelectUserRanking.setText("");
        jTextFieldPuntosRanking.setText("");
        jLabelErrorRankingInsert.setVisible(false);
    }
     
    public void getDataRanking() {
        clearTable(jTableRanking);
        try {
            resultSetRanking = statement.executeQuery("SELECT * FROM ranking");

            int count = 0;

            while (resultSetRanking.next()) {
                System.out.println(resultSetRanking.getInt(1) + " " + resultSetRanking.getString(2) + " " + resultSetRanking.getString(3) + " " + resultSetRanking.getString(4));
                jTableRanking.setValueAt(resultSetRanking.getInt(1), count, 0);
                jTableRanking.setValueAt(resultSetRanking.getString(2), count, 1);
                jTableRanking.setValueAt(resultSetRanking.getString(3), count, 2);
                jTableRanking.setValueAt(resultSetRanking.getString(4), count, 3);
                count++;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    
    public void modifyDataEntradas() {
        String idUserEntradas = jTextFieldSelectUserEntradas.getText();
        String cantidadEntradas = jTextFieldCanitdadEntradas.getText();
        
        if (!UserValidations.isNumeric(idUserEntradas)) {
            jLabelErrorEntradasID.setVisible(true);
        }
        
        if (!UserValidations.verificarEntradas(cantidadEntradas)) {
            jLabelErrorEntradasCantidad.setVisible(true);
        }
        
        if (UserValidations.isNumeric(idUserEntradas) && UserValidations.verificarEntradas(cantidadEntradas)) {
            jLabelErrorEntradasID.setVisible(false);
            jLabelErrorEntradasCantidad.setVisible(false);
            try { 
                statement.executeUpdate("UPDATE entradas SET cantidad_entradas = '" + cantidadEntradas + "' WHERE id_user = '" + idUserEntradas + "'");
                jLabelErrorEntradasInsert.setVisible(false);
            } catch (Exception e) { 
                System.err.println("Hay un error! "); 
                System.err.println(e.getMessage()); 
                jLabelErrorEntradasInsert.setVisible(true);
            }
            resetEntradasField();
            getDataEntradas();
        }
    }
    
    public void resetEntradasField() {
        jTextFieldSelectUserEntradas.setText("");
        jTextFieldCanitdadEntradas.setText("");
        jLabelErrorEntradasInsert.setVisible(false);
    }
    
    public void getDataEntradas() {
        clearTable(jTableEntradas);
        try {
            resultSetEntradas = statement.executeQuery("SELECT * FROM entradas");

            int count = 0;

            while (resultSetEntradas.next()) {
                System.out.println(resultSetEntradas.getInt(1) + " " + resultSetEntradas.getString(2) + " " + resultSetEntradas.getString(3));
                jTableEntradas.setValueAt(resultSetEntradas.getInt(1), count, 0);
                jTableEntradas.setValueAt(resultSetEntradas.getString(2), count, 1);
                jTableEntradas.setValueAt(resultSetEntradas.getString(3), count, 2);
                count++;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void disconnect() {
        try {
            connection.close();
            
            System.out.println("Disconnection - Success!");
            disconnectionJavaObjects();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButtonConnect = new javax.swing.JButton();
        jButtonDisconnect = new javax.swing.JButton();
        jLabelLogin = new javax.swing.JLabel();
        jLabelUserLogin = new javax.swing.JLabel();
        jTextFieldUserLogin = new javax.swing.JTextField();
        jLabelPasswordLogin = new javax.swing.JLabel();
        jPasswordFieldLogin = new javax.swing.JPasswordField();
        jLabelLoginError = new javax.swing.JLabel();
        jLabelMenuImage = new javax.swing.JLabel();
        jTabbedPaneTablas = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableUsers = new javax.swing.JTable();
        jLabelTablaUsers = new javax.swing.JLabel();
        jLabelInsertarUsers = new javax.swing.JLabel();
        jLabelNombreUser = new javax.swing.JLabel();
        jTextFieldNombreUsers = new javax.swing.JTextField();
        jLabelApellidoUsers = new javax.swing.JLabel();
        jTextFieldApellidoUsers = new javax.swing.JTextField();
        jLabelUsernameUsers = new javax.swing.JLabel();
        jTextFieldUsernameUsers = new javax.swing.JTextField();
        jLabelCorreoUsers = new javax.swing.JLabel();
        jTextFieldCorreoUsers = new javax.swing.JTextField();
        jTextFieldPasswdUsers = new javax.swing.JTextField();
        jLabelPasswdUsers = new javax.swing.JLabel();
        jButtonGetDataUsers = new javax.swing.JButton();
        jButtonInsertUsers = new javax.swing.JButton();
        jLabelErrorPasswdUsers = new javax.swing.JLabel();
        jLabelErrorUsuarioUsers = new javax.swing.JLabel();
        jLabelErrorApellidoUsers = new javax.swing.JLabel();
        jLabelErrorNombreUsers = new javax.swing.JLabel();
        jLabelErrorCorreoUsers = new javax.swing.JLabel();
        jButtonResetUsers = new javax.swing.JButton();
        jLabelDeleteUsers = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabelDeleteID = new javax.swing.JLabel();
        jTextFieldDeleteID = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButtonDeleteUsers = new javax.swing.JButton();
        jLabelErrorUsersInsert = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabelTablaRanking = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableRanking = new javax.swing.JTable();
        jButtonGetDataRanking = new javax.swing.JButton();
        jLabelInsertarRanking = new javax.swing.JLabel();
        jLabelNombreUser1 = new javax.swing.JLabel();
        jTextFieldSelectUserRanking = new javax.swing.JTextField();
        jLabelApellidoUsers1 = new javax.swing.JLabel();
        jTextFieldPuntosRanking = new javax.swing.JTextField();
        jButtonModifyRanking = new javax.swing.JButton();
        jButtonResetRanking = new javax.swing.JButton();
        jLabelErrorRankingID = new javax.swing.JLabel();
        jLabelErrorRankingPuntos = new javax.swing.JLabel();
        jLabelErrorRankingInsert = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabelTablaEntradas = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableEntradas = new javax.swing.JTable();
        jButtonGetDataEntradas = new javax.swing.JButton();
        jLabelInsertarRanking1 = new javax.swing.JLabel();
        jLabelNombreUser3 = new javax.swing.JLabel();
        jTextFieldSelectUserEntradas = new javax.swing.JTextField();
        jLabelErrorEntradasID = new javax.swing.JLabel();
        jLabelApellidoUsers3 = new javax.swing.JLabel();
        jTextFieldCanitdadEntradas = new javax.swing.JTextField();
        jLabelErrorEntradasCantidad = new javax.swing.JLabel();
        jButtonInsertEntradas = new javax.swing.JButton();
        jButtonResetEntradas = new javax.swing.JButton();
        jLabelErrorEntradasInsert = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ROCKandPLAY - Java Oracle Fomr");
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(64, 64, 64));

        jButtonConnect.setText("Connect");
        jButtonConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConnectActionPerformed(evt);
            }
        });

        jButtonDisconnect.setText("Disconnect");
        jButtonDisconnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDisconnectActionPerformed(evt);
            }
        });

        jLabelLogin.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelLogin.setForeground(new java.awt.Color(255, 255, 255));
        jLabelLogin.setText("Login:");

        jLabelUserLogin.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelUserLogin.setForeground(new java.awt.Color(255, 255, 255));
        jLabelUserLogin.setText("User:");

        jLabelPasswordLogin.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelPasswordLogin.setForeground(new java.awt.Color(255, 255, 255));
        jLabelPasswordLogin.setText("Password:");

        jPasswordFieldLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordFieldLoginActionPerformed(evt);
            }
        });

        jLabelLoginError.setForeground(new java.awt.Color(255, 51, 51));
        jLabelLoginError.setText("Error: Incorrect User or Password");

        jLabelMenuImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/abp_form/logo-javaform.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelLoginError, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabelLogin))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addGap(21, 21, 21)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jButtonConnect, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButtonDisconnect, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE))
                                .addComponent(jPasswordFieldLogin)
                                .addComponent(jTextFieldUserLogin)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabelPasswordLogin)
                                        .addComponent(jLabelUserLogin))
                                    .addGap(0, 0, Short.MAX_VALUE))))))
                .addContainerGap(25, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabelMenuImage, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabelLogin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelUserLogin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldUserLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelPasswordLogin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPasswordFieldLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonConnect)
                    .addComponent(jButtonDisconnect))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelLoginError)
                .addGap(18, 18, 18)
                .addComponent(jLabelMenuImage, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPaneTablas.setName(""); // NOI18N

        jTableUsers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "id_user", "nombre", "apellido", "username", "correo", "passwd", "creado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTableUsers);
        if (jTableUsers.getColumnModel().getColumnCount() > 0) {
            jTableUsers.getColumnModel().getColumn(6).setResizable(false);
        }

        jLabelTablaUsers.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelTablaUsers.setText("Tabla Users:");

        jLabelInsertarUsers.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelInsertarUsers.setText("Insertar Datos:");

        jLabelNombreUser.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelNombreUser.setText("Nombre:");

        jTextFieldNombreUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNombreUsersActionPerformed(evt);
            }
        });

        jLabelApellidoUsers.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelApellidoUsers.setText("Apellido:");

        jLabelUsernameUsers.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelUsernameUsers.setText("Usuario:");

        jTextFieldUsernameUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldUsernameUsersActionPerformed(evt);
            }
        });

        jLabelCorreoUsers.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelCorreoUsers.setText("Correo:");

        jTextFieldCorreoUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCorreoUsersActionPerformed(evt);
            }
        });

        jLabelPasswdUsers.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelPasswdUsers.setText("Password:");

        jButtonGetDataUsers.setText("Get Data");
        jButtonGetDataUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGetDataUsersActionPerformed(evt);
            }
        });

        jButtonInsertUsers.setText("Insert Data");
        jButtonInsertUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInsertUsersActionPerformed(evt);
            }
        });

        jLabelErrorPasswdUsers.setForeground(new java.awt.Color(255, 51, 51));
        jLabelErrorPasswdUsers.setText("Error: Contrasña no permitida");

        jLabelErrorUsuarioUsers.setForeground(new java.awt.Color(255, 51, 51));
        jLabelErrorUsuarioUsers.setText("Error: Usuario incorrecto");

        jLabelErrorApellidoUsers.setForeground(new java.awt.Color(255, 51, 51));
        jLabelErrorApellidoUsers.setText("Error: Apellido incorrecto");

        jLabelErrorNombreUsers.setForeground(new java.awt.Color(255, 51, 51));
        jLabelErrorNombreUsers.setText("Error: Nombre incorrecto");

        jLabelErrorCorreoUsers.setForeground(new java.awt.Color(255, 51, 51));
        jLabelErrorCorreoUsers.setText("Error: Correo incorrecto");

        jButtonResetUsers.setText("Reset Fields");
        jButtonResetUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonResetUsersActionPerformed(evt);
            }
        });

        jLabelDeleteUsers.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelDeleteUsers.setText("Eliminar Users:");

        jLabelDeleteID.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelDeleteID.setText("Introudce el ID del usuario a eliminar:");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("ID_USER:");

        jButtonDeleteUsers.setText("Delete");
        jButtonDeleteUsers.setToolTipText("");
        jButtonDeleteUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteUsersActionPerformed(evt);
            }
        });

        jLabelErrorUsersInsert.setBackground(new java.awt.Color(255, 255, 255));
        jLabelErrorUsersInsert.setForeground(new java.awt.Color(255, 0, 51));
        jLabelErrorUsersInsert.setText("Error: No se han podido introducir los campos");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabelTablaUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 787, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelCorreoUsers)
                                    .addComponent(jLabelPasswdUsers))
                                .addGap(3, 3, 3)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldPasswdUsers, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                    .addComponent(jTextFieldCorreoUsers))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelErrorCorreoUsers)
                                    .addComponent(jLabelErrorPasswdUsers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelApellidoUsers)
                                    .addComponent(jLabelNombreUser, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelUsernameUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jTextFieldNombreUsers, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                    .addComponent(jTextFieldApellidoUsers, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldUsernameUsers))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelErrorNombreUsers)
                                    .addComponent(jLabelErrorApellidoUsers)
                                    .addComponent(jLabelErrorUsuarioUsers))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextFieldDeleteID, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabelDeleteID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jButtonDeleteUsers))
                        .addGap(91, 91, 91)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jLabelInsertarUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelDeleteUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(196, 196, 196))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButtonInsertUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonResetUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelErrorUsersInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButtonGetDataUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(324, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelTablaUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonGetDataUsers)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelInsertarUsers)
                    .addComponent(jLabelDeleteUsers))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNombreUser)
                    .addComponent(jTextFieldNombreUsers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelErrorNombreUsers)
                    .addComponent(jLabel2)
                    .addComponent(jLabelDeleteID))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelApellidoUsers)
                    .addComponent(jTextFieldApellidoUsers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelErrorApellidoUsers)
                    .addComponent(jTextFieldDeleteID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelUsernameUsers)
                    .addComponent(jTextFieldUsernameUsers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelErrorUsuarioUsers)
                    .addComponent(jButtonDeleteUsers))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCorreoUsers)
                    .addComponent(jTextFieldCorreoUsers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelErrorCorreoUsers))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPasswdUsers)
                    .addComponent(jTextFieldPasswdUsers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelErrorPasswdUsers))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonInsertUsers)
                    .addComponent(jButtonResetUsers)
                    .addComponent(jLabelErrorUsersInsert))
                .addContainerGap())
        );

        jTabbedPaneTablas.addTab("Users", jPanel3);

        jLabelTablaRanking.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelTablaRanking.setText("Tabla Ranking:");

        jTableRanking.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "id_user", "username", "puntos_ranking", "fecha_hora_ranking"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTableRanking);

        jButtonGetDataRanking.setText("Get Data");
        jButtonGetDataRanking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGetDataRankingActionPerformed(evt);
            }
        });

        jLabelInsertarRanking.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelInsertarRanking.setText("Modificar Datos:");

        jLabelNombreUser1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelNombreUser1.setText("ID_USER:");

        jTextFieldSelectUserRanking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSelectUserRankingActionPerformed(evt);
            }
        });

        jLabelApellidoUsers1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelApellidoUsers1.setText("Puntos:");

        jButtonModifyRanking.setText("Insert Data");
        jButtonModifyRanking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModifyRankingActionPerformed(evt);
            }
        });

        jButtonResetRanking.setText("Reset Fields");
        jButtonResetRanking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonResetRankingActionPerformed(evt);
            }
        });

        jLabelErrorRankingID.setBackground(new java.awt.Color(255, 255, 255));
        jLabelErrorRankingID.setForeground(new java.awt.Color(255, 0, 51));
        jLabelErrorRankingID.setText("Error: id_user incorrecto");

        jLabelErrorRankingPuntos.setBackground(new java.awt.Color(255, 255, 255));
        jLabelErrorRankingPuntos.setForeground(new java.awt.Color(255, 0, 51));
        jLabelErrorRankingPuntos.setText("Error: Puntos incorrectos");

        jLabelErrorRankingInsert.setBackground(new java.awt.Color(255, 255, 255));
        jLabelErrorRankingInsert.setForeground(new java.awt.Color(255, 0, 51));
        jLabelErrorRankingInsert.setText("Error: No se han podido introducir los campos");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 787, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelTablaRanking, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelInsertarRanking, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonGetDataRanking)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabelApellidoUsers1)
                                            .addComponent(jLabelNombreUser1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jTextFieldSelectUserRanking, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                                            .addComponent(jTextFieldPuntosRanking))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabelErrorRankingID, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabelErrorRankingPuntos, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jButtonModifyRanking)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButtonResetRanking, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabelErrorRankingInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelTablaRanking, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonGetDataRanking)
                .addGap(18, 18, 18)
                .addComponent(jLabelInsertarRanking)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNombreUser1)
                    .addComponent(jTextFieldSelectUserRanking, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelErrorRankingID))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelApellidoUsers1)
                    .addComponent(jTextFieldPuntosRanking, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelErrorRankingPuntos))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonModifyRanking)
                    .addComponent(jButtonResetRanking)
                    .addComponent(jLabelErrorRankingInsert))
                .addContainerGap(121, Short.MAX_VALUE))
        );

        jTabbedPaneTablas.addTab("Ranking", jPanel4);

        jLabelTablaEntradas.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelTablaEntradas.setText("Tabla Entradas:");

        jTableEntradas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "id_user", "cantidad_entradas", "fecha_hora_entrada"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(jTableEntradas);

        jButtonGetDataEntradas.setText("Get Data");
        jButtonGetDataEntradas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGetDataEntradasActionPerformed(evt);
            }
        });

        jLabelInsertarRanking1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelInsertarRanking1.setText("Modificar Datos:");

        jLabelNombreUser3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelNombreUser3.setText("ID_USER:");

        jTextFieldSelectUserEntradas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSelectUserEntradasActionPerformed(evt);
            }
        });

        jLabelErrorEntradasID.setBackground(new java.awt.Color(255, 255, 255));
        jLabelErrorEntradasID.setForeground(new java.awt.Color(255, 0, 51));
        jLabelErrorEntradasID.setText("Error: id_user incorrecto");

        jLabelApellidoUsers3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelApellidoUsers3.setText("Cantidad:");

        jTextFieldCanitdadEntradas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCanitdadEntradasActionPerformed(evt);
            }
        });

        jLabelErrorEntradasCantidad.setBackground(new java.awt.Color(255, 255, 255));
        jLabelErrorEntradasCantidad.setForeground(new java.awt.Color(255, 0, 51));
        jLabelErrorEntradasCantidad.setText("Error: Cantidad incorrecta (debe ser entre 1 y 4)");

        jButtonInsertEntradas.setText("Insert Data");
        jButtonInsertEntradas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInsertEntradasActionPerformed(evt);
            }
        });

        jButtonResetEntradas.setText("Reset Fields");
        jButtonResetEntradas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonResetEntradasActionPerformed(evt);
            }
        });

        jLabelErrorEntradasInsert.setBackground(new java.awt.Color(255, 255, 255));
        jLabelErrorEntradasInsert.setForeground(new java.awt.Color(255, 0, 51));
        jLabelErrorEntradasInsert.setText("Error: No se han podido introducir los campos");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 787, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelTablaEntradas, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonGetDataEntradas)
                            .addComponent(jLabelInsertarRanking1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabelApellidoUsers3)
                                            .addComponent(jLabelNombreUser3, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jTextFieldSelectUserEntradas)
                                            .addComponent(jTextFieldCanitdadEntradas, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabelErrorEntradasID, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabelErrorEntradasCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jButtonInsertEntradas)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButtonResetEntradas, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabelErrorEntradasInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelTablaEntradas, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonGetDataEntradas)
                .addGap(18, 18, 18)
                .addComponent(jLabelInsertarRanking1)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNombreUser3)
                    .addComponent(jTextFieldSelectUserEntradas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelErrorEntradasID))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelApellidoUsers3)
                    .addComponent(jTextFieldCanitdadEntradas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelErrorEntradasCantidad))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonInsertEntradas)
                    .addComponent(jButtonResetEntradas)
                    .addComponent(jLabelErrorEntradasInsert))
                .addContainerGap(121, Short.MAX_VALUE))
        );

        jLabelErrorEntradasCantidad.getAccessibleContext().setAccessibleName("Error: Cantidad incorrecta");

        jTabbedPaneTablas.addTab("Entradas", jPanel5);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPaneTablas, javax.swing.GroupLayout.PREFERRED_SIZE, 793, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPaneTablas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPaneTablas.getAccessibleContext().setAccessibleName("Users");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleName("ROCKandPLAY - Java Oracle Form");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConnectActionPerformed
        // TODO add your handling code here:
        connect();
    }//GEN-LAST:event_jButtonConnectActionPerformed

    private void jButtonDisconnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDisconnectActionPerformed
        // TODO add your handling code here:
        disconnect();
    }//GEN-LAST:event_jButtonDisconnectActionPerformed

    private void jButtonGetDataUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGetDataUsersActionPerformed
        // TODO add your handling code here:
        getDataUsers();
    }//GEN-LAST:event_jButtonGetDataUsersActionPerformed

    private void jPasswordFieldLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordFieldLoginActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordFieldLoginActionPerformed

    private void jButtonGetDataRankingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGetDataRankingActionPerformed
        // TODO add your handling code here:
        getDataRanking();
    }//GEN-LAST:event_jButtonGetDataRankingActionPerformed

    private void jButtonGetDataEntradasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGetDataEntradasActionPerformed
        // TODO add your handling code here:
        getDataEntradas();
    }//GEN-LAST:event_jButtonGetDataEntradasActionPerformed

    private void jTextFieldNombreUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNombreUsersActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNombreUsersActionPerformed

    private void jButtonInsertUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInsertUsersActionPerformed
        // TODO add your handling code here:
        insertarDatosUsers();
    }//GEN-LAST:event_jButtonInsertUsersActionPerformed

    private void jTextFieldUsernameUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldUsernameUsersActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldUsernameUsersActionPerformed

    private void jButtonResetUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonResetUsersActionPerformed
        // TODO add your handling code here:
        resetUsersFields();
    }//GEN-LAST:event_jButtonResetUsersActionPerformed

    private void jTextFieldCorreoUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCorreoUsersActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCorreoUsersActionPerformed

    private void jButtonDeleteUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteUsersActionPerformed
        // TODO add your handling code here:
        deleteUsers();
    }//GEN-LAST:event_jButtonDeleteUsersActionPerformed

    private void jTextFieldSelectUserRankingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSelectUserRankingActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldSelectUserRankingActionPerformed

    private void jButtonResetRankingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonResetRankingActionPerformed
        // TODO add your handling code here:
        resetRankingFields();
    }//GEN-LAST:event_jButtonResetRankingActionPerformed

    private void jButtonModifyRankingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModifyRankingActionPerformed
        // TODO add your handling code here:
        modifyDataRanking();
    }//GEN-LAST:event_jButtonModifyRankingActionPerformed

    private void jTextFieldSelectUserEntradasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSelectUserEntradasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldSelectUserEntradasActionPerformed

    private void jButtonInsertEntradasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInsertEntradasActionPerformed
        // TODO add your handling code here:
        modifyDataEntradas();
    }//GEN-LAST:event_jButtonInsertEntradasActionPerformed

    private void jButtonResetEntradasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonResetEntradasActionPerformed
        // TODO add your handling code here:
        resetEntradasField();
    }//GEN-LAST:event_jButtonResetEntradasActionPerformed

    private void jTextFieldCanitdadEntradasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCanitdadEntradasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCanitdadEntradasActionPerformed

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFrameSQLForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrameSQLForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrameSQLForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrameSQLForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrameSQLForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonConnect;
    private javax.swing.JButton jButtonDeleteUsers;
    private javax.swing.JButton jButtonDisconnect;
    private javax.swing.JButton jButtonGetDataEntradas;
    private javax.swing.JButton jButtonGetDataRanking;
    private javax.swing.JButton jButtonGetDataUsers;
    private javax.swing.JButton jButtonInsertEntradas;
    private javax.swing.JButton jButtonInsertUsers;
    private javax.swing.JButton jButtonModifyRanking;
    private javax.swing.JButton jButtonResetEntradas;
    private javax.swing.JButton jButtonResetRanking;
    private javax.swing.JButton jButtonResetUsers;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelApellidoUsers;
    private javax.swing.JLabel jLabelApellidoUsers1;
    private javax.swing.JLabel jLabelApellidoUsers3;
    private javax.swing.JLabel jLabelCorreoUsers;
    private javax.swing.JLabel jLabelDeleteID;
    private javax.swing.JLabel jLabelDeleteUsers;
    private javax.swing.JLabel jLabelErrorApellidoUsers;
    private javax.swing.JLabel jLabelErrorCorreoUsers;
    private javax.swing.JLabel jLabelErrorEntradasCantidad;
    private javax.swing.JLabel jLabelErrorEntradasID;
    private javax.swing.JLabel jLabelErrorEntradasInsert;
    private javax.swing.JLabel jLabelErrorNombreUsers;
    private javax.swing.JLabel jLabelErrorPasswdUsers;
    private javax.swing.JLabel jLabelErrorRankingID;
    private javax.swing.JLabel jLabelErrorRankingInsert;
    private javax.swing.JLabel jLabelErrorRankingPuntos;
    private javax.swing.JLabel jLabelErrorUsersInsert;
    private javax.swing.JLabel jLabelErrorUsuarioUsers;
    private javax.swing.JLabel jLabelInsertarRanking;
    private javax.swing.JLabel jLabelInsertarRanking1;
    private javax.swing.JLabel jLabelInsertarUsers;
    private javax.swing.JLabel jLabelLogin;
    private javax.swing.JLabel jLabelLoginError;
    private javax.swing.JLabel jLabelMenuImage;
    private javax.swing.JLabel jLabelNombreUser;
    private javax.swing.JLabel jLabelNombreUser1;
    private javax.swing.JLabel jLabelNombreUser3;
    private javax.swing.JLabel jLabelPasswdUsers;
    private javax.swing.JLabel jLabelPasswordLogin;
    private javax.swing.JLabel jLabelTablaEntradas;
    private javax.swing.JLabel jLabelTablaRanking;
    private javax.swing.JLabel jLabelTablaUsers;
    private javax.swing.JLabel jLabelUserLogin;
    private javax.swing.JLabel jLabelUsernameUsers;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPasswordField jPasswordFieldLogin;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPaneTablas;
    private javax.swing.JTable jTableEntradas;
    private javax.swing.JTable jTableRanking;
    private javax.swing.JTable jTableUsers;
    private javax.swing.JTextField jTextFieldApellidoUsers;
    private javax.swing.JTextField jTextFieldCanitdadEntradas;
    private javax.swing.JTextField jTextFieldCorreoUsers;
    private javax.swing.JTextField jTextFieldDeleteID;
    private javax.swing.JTextField jTextFieldNombreUsers;
    private javax.swing.JTextField jTextFieldPasswdUsers;
    private javax.swing.JTextField jTextFieldPuntosRanking;
    private javax.swing.JTextField jTextFieldSelectUserEntradas;
    private javax.swing.JTextField jTextFieldSelectUserRanking;
    private javax.swing.JTextField jTextFieldUserLogin;
    private javax.swing.JTextField jTextFieldUsernameUsers;
    // End of variables declaration//GEN-END:variables
}
