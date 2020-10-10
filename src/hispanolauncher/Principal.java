package hispanolauncher;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.MouseInfo;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author inuckles
 */
public class Principal extends javax.swing.JFrame implements ActionListener {

    private String cur_dir;
    private String tituloVentana = "Hispano MC Launcher 1.0";
    Panel_Inicio inicio = new Panel_Inicio();
    Panel_Instalacion instalacion = new Panel_Instalacion();
    Panel_Configuracion configuracion = new Panel_Configuracion();
    Panel_Status status = new Panel_Status();
    /**
     * Creates new form Principal
     */
    public Principal() {
        log("Launcher Iniciado.");
        crearDirectorioLauncher();
        crearJsonPerfiles();
        
        
        //Al iniciarse por primera vez, el launcher crea la carpeta .minecraft//.hispano_launcher
        
        
        initComponents();
                
        this.btnInicio.addActionListener(this);
        this.btnInstalacion.addActionListener(this);
        this.btnConfiguracion.addActionListener(this);
        
        this.contenedor.remove(jPanel1);
        this.contenedor.add(inicio);
        this.contenedor_status.add(status);
        
        this.setTitle(tituloVentana);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        
        
        this.Menu.setBackground(new Color(0,0,0,100));
        
        this.inicio.setBackground(new Color(0,0,0,25));
        this.instalacion.setBackground(new Color(0,0,0,25));
        this.configuracion.setBackground(new Color(0,0,0,25));
        this.status.setBackground(new Color(0,0,0,100));
        
        
        this.jPanel2.setBackground(new Color(0,0,0,110));
        this.jPanel2.setOpaque(true);
        
        
        ajustarVentana();
      
      
    }
    
    
    
    private void primeraVez(){
        // Cuando el launcher se ejecuta por primera vez, entrará acá
    }
    
    private void crearJsonPerfiles(){

        String Directorio = System.getenv("APPDATA") + "\\.minecraft";
        
        
        
        
        File DirJson = new File(Directorio);

        if (DirJson.exists()) {

               File Json = new File(Directorio + "\\launcher_profiles.json");
               JsonParser parser = new JsonParser();
            try {
                FileReader fr = new FileReader(Json);
                JsonElement datos = parser.parse(fr);


                dumpJSONElement(datos);


                //Escribe el JsonElemeent actual (ya modificado) en el archivo especificado
                try (Writer writer = new FileWriter(Directorio + "\\launcher_profiles.json")) {
                    Gson gson = new GsonBuilder().create();
                    gson.toJson(datos, writer);
                }  catch (IOException ex) {
                       Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                   }








            } catch (FileNotFoundException ex) {
                log("Problema con Json    :" + ex);
            }

            // METODO ANTERIOR
            /*     Scanner lectorHispanoFile;
                try {
                lectorHispanoFile = new Scanner(Json);

                while(lectorHispanoFile.hasNextLine()){
                System.out.println(lectorHispanoFile.nextLine());
                }

                } catch (FileNotFoundException ex) {
                log("NO FUNCIONA   :" + ex);
                } */

        }
    }
    
    
    boolean estaEnPerfil = false;

 
public void dumpJSONElement(JsonElement elemento) {
   
    String json = "{\"authentication\":{\"username\":\"\"},\"name\":\"Servidor Hispano\",\"gameDir\":\"%APPDATA%\\\\.hispano_launcher\\\\minecraft\",\"lastVersionId\":\"1.12.2-forge1.12.2-14.23.5.2838\",\"javaArgs\":\"-Xmx8G\",\"typo\":\"NoPremium\",\"useForge\":false}";
    JsonObject objetosPerfil = new JsonParser().parse(json).getAsJsonObject();
    
    //JsonObject objetosDelPerfil = perfilServidor.getAsJsonObject();
    //JsonObject perfil = perfilServidor.getAsJsonObject();
    
    System.out.println(objetosPerfil.toString());
    
    
    
    
    
    
    
    if (elemento.isJsonObject()) {
        // Es un conjunto de pares clave, valor
        // Para cada par, imprimir la clave y llamar a dumpJSONElement(valor)
        //System.out.println("Es Objeto" + elemento);
        
        
        JsonObject obj = elemento.getAsJsonObject();
        java.util.Set<java.util.Map.Entry<String,JsonElement>> entradas = obj.entrySet();
        java.util.Iterator<java.util.Map.Entry<String,JsonElement>> iter = entradas.iterator();
        
        
        while (iter.hasNext()) {
            java.util.Map.Entry<String,JsonElement> entrada = iter.next();
            
                
                if (estaEnPerfil){
                    System.out.println(entrada.getKey() + " == " + entrada.getValue());
                    obj.add("1.12.2 SERVER HISPANO", objetosPerfil);
                    
                    
                    /*
                
                    
                    objetosDelPerfil.addProperty("name", "Servidor Hispano JSON");
                    objetosDelPerfil.addProperty("gameDir", System.getenv("APPDATA") + "\\.hispano_launcher\\minecraft");
                    objetosDelPerfil.addProperty("lastVersionId", "1.12.2-forge1.12.2-14.23.5.2838");
                    objetosDelPerfil.addProperty("javaArgs", "Servidor Hispano JSON");
                    objetosDelPerfil.addProperty("name", "-Xmx8G");
                    objetosDelPerfil.addProperty("typo", "NoPremium");
                    objetosDelPerfil.addProperty("useForge", "false");*/
                    
                   // perfil.add("Servidor", );
                    
                    
                    estaEnPerfil = false;
                }
                
            if ("profiles".equals(entrada.getKey())){
               // System.out.println(entrada.getKey() + " == " + entrada.getValue());
                
                estaEnPerfil = true;
                dumpJSONElement(entrada.getValue());
                
                /*
                Gson gson = new Gson();
                String jsonOutput = gson.toJson(elemento);
                System.out.println(jsonOutput);
                */
                
            }
            
            
            
            
        }
        
    
    } else if (elemento.isJsonArray()) {
        // Es un conjunto de valores, que pueden ser elementos simples o compuestos
        // Para cada valor, llamar a dumpJSONElemento(valor)
        System.out.println("Es Array");
     
    } else if (elemento.isJsonPrimitive()) {
        // Es un elemento simple. Determinar si se trata de un valor booleano,
        // un número o cadena de texto
        System.out.println("Es Primitive");
      
    } else if (elemento.isJsonNull()) {
        System.out.println("Es NULL");
    } else {
        System.out.println("Es otra cosa");
    }
}
 
                 
    /*
    private static void dumpJSONElement2(JsonElement elemento) {
        
        
        if (elemento.isJsonObject()) {
        
        JsonObject obj = elemento.getAsJsonObject();
        java.util.Set<java.util.Map.Entry<String,JsonElement>> entradas = obj.entrySet();
        java.util.Iterator<java.util.Map.Entry<String,JsonElement>> iter = entradas.iterator();
      
        
                    
        boolean encontroPerfil = false;
        
            while (iter.hasNext()) {
                java.util.Map.Entry<String,JsonElement> entrada = iter.next();
                dumpJSONElement(entrada.getValue()); 




                    if ("profiles".equals(entrada.getKey())){   
                       System.out.println("Entro a profiiles");

                        System.out.println(entrada.getKey() + " = " + entrada.getValue());

                    }



                if ("name".equals(entrada.getKey())){   
                     if (entrada.getValue().getAsString().equals("1.12.2 SERVER HISPANO")){
                        //System.out.println("El valor de " + entrada.getKey() + " = " +  entrada.getValue().getAsString());

                       // obj.add(entrada.getKey(), entrada.getValue());
                        //System.out.println(obj);
                        encontroPerfil=true;  


                     }
                }


                if ("javaArgs".equals(entrada.getKey()) && encontroPerfil ){        
                    //System.out.println("El valor de " + entrada.getKey() + " = " +  entrada.getValue().getAsString());

                    Gson gson = new Gson();
                    String datos = "-Xmx16G";
                    entrada.setValue(gson.toJsonTree(datos));
                    System.out.println("Cambia valor");

                    encontroPerfil=false;
                }



            }
        }  
    }
    */
    
    private void crearDirectorioLauncher(){
    
        
           String folder = System.getenv("APPDATA") + "\\.hispano_launcher";

           File hispanoLauncherDIR = new File(folder);
           File minecraftDIR = new File(folder + "\\minecraft");
           File logsDIR = new File(folder + "\\logs");
           
           
           //log("Verificando si existe la carpeta .hispano_launcher");
           
           if (hispanoLauncherDIR.exists()){
                    log(".hispano_launcher ya existe");
           }else{

                if (hispanoLauncherDIR.mkdir()){
                    primeraVez();
                }else{
                }
           }
           
           
           if (minecraftDIR.exists()){
           }else{
                if (minecraftDIR.mkdir()){
                }else{
                }
           }
           
           
           if (logsDIR.exists()){
           }else{
                if (logsDIR.mkdir()){
                }else{
                }
           }
           
           
           
           
    }
    
    
    
    private void ajustarVentana(){
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        
       // print(width + " x " + height);
        int ancho=800;
        int alto=600;
        
        if (width >= 1920 && height >=1080){
            ancho = 1366;
            alto = 768;
        }
        this.setSize(ancho,alto);
        setLocationRelativeTo(null);
        
    }
    
   
    
    private void limpiarHUD(){
        this.Menu.setVisible(false);
        this.status.setVisible(false);
    
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
        jButton1 = new javax.swing.JButton();
        lblInstalacion = new javax.swing.JLabel();
        lblConfiguracion = new javax.swing.JLabel();
        lblInicio = new javax.swing.JLabel();
        contenedor = new javax.swing.JPanel(){

            public void paintComponent(Graphics g){
                ImageIcon im = new ImageIcon("src/media/bg2.png");
                Image i = im.getImage();
                g.drawImage(i,0,0,this.getSize().width,this.getSize().height,this);

            }

        };
        Menu = new javax.swing.JPanel();
        btnInicio = new javax.swing.JButton();
        btnInstalacion = new javax.swing.JButton();
        btnConfiguracion = new javax.swing.JButton();
        lblLogo2 = new javax.swing.JLabel();
        contenedor_status = new javax.swing.JPanel(){

            /*public void paintComponent(Graphics g){
                ImageIcon im = new ImageIcon("bg.png");
                Image i = im.getImage();
                g.drawImage(i,0,0,this.getSize().width,this.getSize().height,this);

            }*/

        };
        jPanel2 = new javax.swing.JPanel();
        lblMinimize = new javax.swing.JLabel();
        lblX = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 639, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 352, Short.MAX_VALUE)
        );

        jButton1.setText("jButton1");

        lblInstalacion.setBackground(new java.awt.Color(255, 255, 255));
        lblInstalacion.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        lblInstalacion.setForeground(new java.awt.Color(238, 238, 238));
        lblInstalacion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblInstalacion.setText("Instalación");
        lblInstalacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblInstalacionMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblInstalacionMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblInstalacionMouseExited(evt);
            }
        });

        lblConfiguracion.setBackground(new java.awt.Color(255, 255, 255));
        lblConfiguracion.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        lblConfiguracion.setForeground(new java.awt.Color(238, 238, 238));
        lblConfiguracion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblConfiguracion.setText("Configuración");

        lblInicio.setBackground(new java.awt.Color(255, 255, 255));
        lblInicio.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        lblInicio.setForeground(new java.awt.Color(238, 238, 238));
        lblInicio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblInicio.setText("Inicio");
        lblInicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblInicioMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblInicioMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblInicioMouseExited(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 600));
        setUndecorated(true);

        contenedor.setBackground(new java.awt.Color(0, 0, 0));
        contenedor.setLayout(new java.awt.BorderLayout());

        btnInicio.setBackground(new java.awt.Color(0, 0, 0));
        btnInicio.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        btnInicio.setForeground(new java.awt.Color(238, 238, 238));
        btnInicio.setText("Inicio");
        btnInicio.setBorder(null);
        btnInicio.setBorderPainted(false);
        btnInicio.setContentAreaFilled(false);
        btnInicio.setOpaque(true);
        btnInicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnInicioMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnInicioMouseExited(evt);
            }
        });
        btnInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInicioActionPerformed(evt);
            }
        });

        btnInstalacion.setBackground(new java.awt.Color(0, 0, 0));
        btnInstalacion.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        btnInstalacion.setForeground(new java.awt.Color(238, 238, 238));
        btnInstalacion.setText("Instalación");
        btnInstalacion.setBorder(null);
        btnInstalacion.setBorderPainted(false);
        btnInstalacion.setContentAreaFilled(false);
        btnInstalacion.setOpaque(true);
        btnInstalacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnInstalacionMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnInstalacionMouseExited(evt);
            }
        });
        btnInstalacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInstalacionActionPerformed(evt);
            }
        });

        btnConfiguracion.setBackground(new java.awt.Color(0, 0, 0));
        btnConfiguracion.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        btnConfiguracion.setForeground(new java.awt.Color(238, 238, 238));
        btnConfiguracion.setText("Configuración");
        btnConfiguracion.setBorder(null);
        btnConfiguracion.setBorderPainted(false);
        btnConfiguracion.setContentAreaFilled(false);
        btnConfiguracion.setOpaque(true);
        btnConfiguracion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnConfiguracionMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnConfiguracionMouseExited(evt);
            }
        });
        btnConfiguracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfiguracionActionPerformed(evt);
            }
        });

        lblLogo2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/media/logomain.png"))); // NOI18N

        javax.swing.GroupLayout MenuLayout = new javax.swing.GroupLayout(Menu);
        Menu.setLayout(MenuLayout);
        MenuLayout.setHorizontalGroup(
            MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnInstalacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnConfiguracion, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
            .addComponent(lblLogo2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        MenuLayout.setVerticalGroup(
            MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(lblLogo2, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
                .addComponent(btnInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnInstalacion, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnConfiguracion, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(115, Short.MAX_VALUE))
        );

        contenedor.add(Menu, java.awt.BorderLayout.LINE_START);

        contenedor_status.setLayout(new java.awt.BorderLayout());
        contenedor.add(contenedor_status, java.awt.BorderLayout.PAGE_END);

        jPanel2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel2MouseDragged(evt);
            }
        });
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel2MousePressed(evt);
            }
        });

        lblMinimize.setBackground(new java.awt.Color(0, 0, 0));
        lblMinimize.setFont(new java.awt.Font("Ubuntu Medium", 1, 18)); // NOI18N
        lblMinimize.setForeground(new java.awt.Color(255, 255, 255));
        lblMinimize.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMinimize.setText("—");
        lblMinimize.setOpaque(true);
        lblMinimize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMinimizeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblMinimizeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblMinimizeMouseExited(evt);
            }
        });

        lblX.setBackground(new java.awt.Color(0, 0, 0));
        lblX.setFont(new java.awt.Font("Ubuntu Medium", 1, 18)); // NOI18N
        lblX.setForeground(new java.awt.Color(255, 255, 255));
        lblX.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblX.setText("X");
        lblX.setOpaque(true);
        lblX.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblXMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblXMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblXMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(768, Short.MAX_VALUE)
                .addComponent(lblMinimize, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lblX, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMinimize, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblX, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        contenedor.add(jPanel2, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(contenedor, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInicioActionPerformed
      cur_dir = "Inicio";
        this.btnInicio.setForeground(Color.yellow);
        this.btnConfiguracion.setForeground(Color.white);
        this.btnInstalacion.setForeground(Color.white);
        
    }//GEN-LAST:event_btnInicioActionPerformed

    private void btnInicioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInicioMouseEntered
        this.btnInicio.setForeground(Color.yellow);
        play_hover_sound();
    }//GEN-LAST:event_btnInicioMouseEntered

    private void btnInicioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInicioMouseExited
        if (!"Inicio".equals(cur_dir)){
            this.btnInicio.setForeground(Color.white);
        }
    }//GEN-LAST:event_btnInicioMouseExited

    private void btnInstalacionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInstalacionMouseEntered
      this.btnInstalacion.setForeground(Color.yellow);
        play_hover_sound();
    }//GEN-LAST:event_btnInstalacionMouseEntered

    private void btnInstalacionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInstalacionMouseExited
        if (!"Instalacion".equals(cur_dir)){
            this.btnInstalacion.setForeground(Color.white);
        }
    }//GEN-LAST:event_btnInstalacionMouseExited

    private void btnInstalacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInstalacionActionPerformed
        
        cur_dir = "Instalacion";
        this.btnInicio.setForeground(Color.white);
        this.btnConfiguracion.setForeground(Color.white);
        this.btnInstalacion.setForeground(Color.yellow);
        
    }//GEN-LAST:event_btnInstalacionActionPerformed

    private void btnConfiguracionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConfiguracionMouseEntered

        this.btnConfiguracion.setForeground(Color.yellow);
        play_hover_sound();
        
    }//GEN-LAST:event_btnConfiguracionMouseEntered

    private void btnConfiguracionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConfiguracionMouseExited
        if (!"Configuracion".equals(cur_dir)){
            this.btnConfiguracion.setForeground(Color.white);
        }
        
    }//GEN-LAST:event_btnConfiguracionMouseExited

    private void btnConfiguracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfiguracionActionPerformed

        cur_dir = "Configuracion";
        this.btnInicio.setForeground(Color.white);
        this.btnConfiguracion.setForeground(Color.yellow);
        this.btnInstalacion.setForeground(Color.white);
      
    }//GEN-LAST:event_btnConfiguracionActionPerformed

    private void lblInicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblInicioMouseClicked

        cur_dir = "Inicio";

        this.lblInicio.setForeground(Color.yellow);
        this.lblConfiguracion.setForeground(Color.white);
        this.lblInstalacion.setForeground(Color.white);

       /* this.pnlInicio.setVisible(true);
        this.pnlConfiguracion.setVisible(false);
        this.pnlInstalacion.setVisible(false);

        this.pnlInicio.setAlignmentX(114);
        this.pnlInicio.setAlignmentY(6);
*/
    }//GEN-LAST:event_lblInicioMouseClicked

    private void lblInicioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblInicioMouseEntered
        this.lblInicio.setForeground(Color.yellow);
        play_hover_sound();
    }//GEN-LAST:event_lblInicioMouseEntered

    private void lblInicioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblInicioMouseExited

        if (!"Inicio".equals(cur_dir)){
            this.lblInicio.setForeground(Color.white);
        }
    }//GEN-LAST:event_lblInicioMouseExited

    private void lblInstalacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblInstalacionMouseClicked
        cur_dir = "Instalacion";
        this.lblInicio.setForeground(Color.white);
        this.lblConfiguracion.setForeground(Color.white);
        this.lblInstalacion.setForeground(Color.yellow);

       /* this.pnlInicio.setVisible(false);
        this.pnlConfiguracion.setVisible(false);
        this.pnlInstalacion.setVisible(true);

        this.pnlConfiguracion.setAlignmentX(114);
        this.pnlConfiguracion.setAlignmentY(6);*/

    }//GEN-LAST:event_lblInstalacionMouseClicked

    private void lblInstalacionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblInstalacionMouseEntered
        this.lblInstalacion.setForeground(Color.yellow);
        play_hover_sound();
    }//GEN-LAST:event_lblInstalacionMouseEntered

    private void lblInstalacionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblInstalacionMouseExited

        if (!"Instalacion".equals(cur_dir)){
            this.lblInstalacion.setForeground(Color.white);
        }

    }//GEN-LAST:event_lblInstalacionMouseExited

    private void lblMinimizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMinimizeMouseClicked
        
        this.setState(Frame.ICONIFIED);
        
        if (SystemTray.isSupported()) {
            SystemTray tray = SystemTray.getSystemTray();
            
            File currentDirFile = new File(".");
            String helper = currentDirFile.getAbsolutePath();
            
            Image image = Toolkit.getDefaultToolkit().getImage("media/icon.gif");
         
            
            ActionListener listener = (ActionEvent e) -> {
                JOptionPane.showMessageDialog(null,"Exec");
            };
            // create a popup menu
            PopupMenu popup = new PopupMenu();
            // create menu item for the default action
            MenuItem defaultItem = new MenuItem("asd");
            defaultItem.addActionListener(listener);
            popup.add(defaultItem);
            TrayIcon trayIcon = new TrayIcon(image, "HispanoLauncher", popup);
            // set the TrayIcon properties
            trayIcon.addActionListener(listener);
            // ...
            // add the tray image
            try {
                tray.add(trayIcon);
            } catch (AWTException e) {
                System.err.println(e);
            }
            
           
        }
        
    }//GEN-LAST:event_lblMinimizeMouseClicked

    private void jPanel2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseDragged
     
     setLocation(evt.getXOnScreen() -tx, evt.getYOnScreen() -ty);
    }//GEN-LAST:event_jPanel2MouseDragged
private int tx, ty;
    private void jPanel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MousePressed
        tx = evt.getX();
        ty = evt.getY();
    }//GEN-LAST:event_jPanel2MousePressed

    private void lblMinimizeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMinimizeMouseEntered
        this.lblMinimize.setBackground(Color.darkGray);
        this.lblMinimize.setForeground(Color.white);
        play_hover_sound();

    }//GEN-LAST:event_lblMinimizeMouseEntered

    private void lblMinimizeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMinimizeMouseExited
        this.lblMinimize.setBackground(Color.black);
        this.lblMinimize.setForeground(Color.white);
    }//GEN-LAST:event_lblMinimizeMouseExited

    private void lblXMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblXMouseClicked
        log("Launcher cerrado");
        System.exit(0);
    }//GEN-LAST:event_lblXMouseClicked

    private void lblXMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblXMouseEntered
        this.lblX.setBackground(Color.darkGray);
        this.lblMinimize.setForeground(Color.white);
        play_hover_sound();
    }//GEN-LAST:event_lblXMouseEntered

    private void lblXMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblXMouseExited
        this.lblX.setBackground(Color.black);
        this.lblX.setForeground(Color.white);
    }//GEN-LAST:event_lblXMouseExited

    
 
    public void log(String texto){
        
            String rutaLog = System.getenv("APPDATA") + "\\.hispano_launcher\\logs\\";
            File logDir = new File(rutaLog);
            
            if (logDir.exists()){
                
                Date fecha = new Date(); 
                SimpleDateFormat formato = new SimpleDateFormat("dd-MM-YYYY");
                SimpleDateFormat formato2 = new SimpleDateFormat("dd/MM/YYYY HH:mm a");
                String fechaFormateada = formato.format(fecha);
                String fechaFormateada2 = formato2.format(fecha);
                String nombreLog = "log_"+ fechaFormateada + ".txt";
                FileWriter myWriter; 
                try {
                    myWriter = new FileWriter(rutaLog + nombreLog,true); 
                    myWriter.write("[" + fechaFormateada2 +  "] " + texto + "\n");
                    myWriter.close();
                } catch (IOException ex) {
                    Logger.getLogger(Panel_Inicio.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            }
                
               
                
        }      
    
    
    
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
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }
    
    
    
        private void play_hover_sound(){
        try{
            File sound = new File("src/media/hover-sound_1.wav").getAbsoluteFile();
            Clip c = AudioSystem.getClip();
            c.open(AudioSystem.getAudioInputStream(sound));
            c.start();
        }catch(IOException | LineUnavailableException | UnsupportedAudioFileException e){
            System.out.println("Error: " + e);
        }
    }
    
    
    private void print(String str){
        System.out.println(str);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Menu;
    private javax.swing.JButton btnConfiguracion;
    private javax.swing.JButton btnInicio;
    private javax.swing.JButton btnInstalacion;
    private javax.swing.JPanel contenedor;
    private javax.swing.JPanel contenedor_status;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblConfiguracion;
    private javax.swing.JLabel lblInicio;
    private javax.swing.JLabel lblInstalacion;
    private javax.swing.JLabel lblLogo2;
    private javax.swing.JLabel lblMinimize;
    private javax.swing.JLabel lblX;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent ae) {
       
        
        Object evt = ae.getSource();
    
        if (evt.equals(this.btnInicio)){
            this.inicio.setVisible(true);
            this.instalacion.setVisible(false);
            this.configuracion.setVisible(false);
            this.contenedor.add(inicio);
        }else if (evt.equals(this.btnInstalacion)){
            this.inicio.setVisible(false);
            this.instalacion.setVisible(true);
            this.configuracion.setVisible(false);
            this.contenedor.add(instalacion);
        }else if(evt.equals(this.btnConfiguracion)){
            this.inicio.setVisible(false);
            this.instalacion.setVisible(false);
            this.configuracion.setVisible(true);
            this.contenedor.add(configuracion);
        }
        
            this.contenedor.validate();
    }
}
