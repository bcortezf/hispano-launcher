/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hispanolauncher;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Hashtable;
import javax.swing.ImageIcon;
/**
 *
 * @author inuckles
 */
public class Panel_Configuracion extends javax.swing.JPanel {

    /**
     * Creates new form Inicio
     */
    public Panel_Configuracion() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlConfiguracion = new javax.swing.JPanel(){

        };
        lblConfiguracion = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        listRam = new java.awt.Choice();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(84, 84, 84));

        pnlConfiguracion.setOpaque(false);

        lblConfiguracion.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        lblConfiguracion.setForeground(new java.awt.Color(238, 238, 238));
        lblConfiguracion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblConfiguracion.setText("Configuración");

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("RAM");

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nombre de Usuario");

        jButton1.setText("Aplicar Cambios");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(listRam, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(120, 120, 120))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(listRam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 129, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(38, 38, 38))
        );

        javax.swing.GroupLayout pnlConfiguracionLayout = new javax.swing.GroupLayout(pnlConfiguracion);
        pnlConfiguracion.setLayout(pnlConfiguracionLayout);
        pnlConfiguracionLayout.setHorizontalGroup(
            pnlConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblConfiguracion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlConfiguracionLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlConfiguracionLayout.setVerticalGroup(
            pnlConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlConfiguracionLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(lblConfiguracion)
                .addGap(87, 87, 87)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlConfiguracion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlConfiguracion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String usuario = this.txtUsuario.getText();
        
        aplicarCambiosPerfil();
        
        
        
        
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    
    
    public void rellenarData(Hashtable datos,JsonElement json){
        
        JsonObject obj = getJSONElement().getAsJsonObject();
        java.util.Set<java.util.Map.Entry<String,JsonElement>> entradas = obj.entrySet();
        java.util.Iterator<java.util.Map.Entry<String,JsonElement>> iter = entradas.iterator();
        
        
        
         while (iter.hasNext()) {
                    java.util.Map.Entry<String,JsonElement> entrada = iter.next();                    
                    
                    //System.out.println("Es objeto " + entrada.getKey() + " = " + entrada.getValue());
                    
                    
                     if ("profiles".equals(entrada.getKey())){ // Si esta en el objeto profiles
                         
                         //   System.out.println("2Es objeto " + entrada.getKey() + " = " + entrada.getValue());
                            
                            JsonObject perfiles = entrada.getValue().getAsJsonObject();
                            
                            if (perfiles.get("Servidor Hispano").isJsonObject()){
                                JsonObject perfilHispano = perfiles.get("Servidor Hispano").getAsJsonObject();
                                System.out.println(perfilHispano.get("authentication").getAsJsonObject().getAsJsonPrimitive("displayName"));
                                System.out.println(perfilHispano.get("authentication").getAsJsonObject().getAsJsonPrimitive("username"));
                                   
                                if (perfilHispano.get("authentication").getAsJsonObject().getAsJsonPrimitive("displayName") != null){
                                    this.txtUsuario.setText(perfilHispano.get("authentication").getAsJsonObject().get("displayName").getAsString());
                                }else if(perfilHispano.get("authentication").getAsJsonObject().getAsJsonPrimitive("username") != null){ 
                                    System.out.println(perfilHispano.get("authentication").getAsJsonObject().get("username").getAsString());
                                    this.txtUsuario.setText(perfilHispano.get("authentication").getAsJsonObject().get("username").getAsString());
                                }
                                
                                this.listRam.removeAll();
                                for (int i = 2; i <= 16; i=i+2) {
                                    this.listRam.add(i+" GB");
                                }
                                
                                String RAM = perfilHispano.get("javaArgs").getAsString().replace("-Xmx", "").replace("G", " GB");
                                
                                this.listRam.select(RAM);
                            }
                            
                            
                            
                            
                        }
                    
                    
                        
                        if ("name".equals(entrada.getKey()) && "Servidor Hispano".equals(entrada.getValue().getAsString())){
                           // System.out.println("Hispano: " + entrada.getKey() + " = " + entrada.getValue());
                        }
                        
                        if ("javaArgs".equals(entrada.getKey())){
                            //System.out.println("Hispano: " + entrada.getKey() + " = " + entrada.getValue());
                        }
                    if (entrada.getValue().isJsonObject()){
                       // System.out.println("Es objeto " + entrada.getKey() + " = " + entrada.getValue());
                        
                        
                       
                        //if (obj.get("perfiles").isJsonObject() ){
                       // System.out.println("Es objeto2 " + entrada.getKey() + " = " + entrada.getValue());
                        /*
                             JsonObject perfiles = entrada.getValue().getAsJsonObject();
                            System.out.println(perfiles);
                            JsonObject servidorHispano = perfiles.get("Servidor Hispano").getAsJsonObject();
                            JsonObject nombreUsuario = servidorHispano.get("authentication").getAsJsonObject().get("displayName").getAsJsonObject();
                            this.txtUsuario.setText(nombreUsuario.getAsString());
                            System.out.println();*/
                        
                     //   }
                       
                        
                        
                        
                        
                    }

                    
                   
         }
    }
    
    
    
    
    
    
     public JsonElement getJSONElement(){
     
     
             // Revisamos si el jugador tiene el perfil del servidor hispano.
        String Directorio = System.getenv("APPDATA") + "\\.minecraft";
        File DirJson = new File(Directorio);

        // Verificamos si el directorio .minecraft existe
        if (DirJson.exists()) {
            
            try { //Establecemos la ruta del launcher_profiles y nos preparamos para abrir el archivo
                File Json = new File(Directorio + "\\launcher_profiles.json");
                JsonParser parser = new JsonParser();
                FileReader fr = new FileReader(Json);
                JsonElement datos = parser.parse(fr);
                
                return datos;
            } catch (FileNotFoundException ex) {
                System.out.println("Problema con Json    :" + ex);
            }
        }else{
            //Si no encuentra el archivo JSON, deberia descargar un template aquí.
            return null;
        }
        return null;
         
     }
    
     
     
     
     
     
     
     
     public void aplicarCambiosPerfil(){
        
        System.out.println("");
        System.out.println("");
        System.out.println("");
        JsonObject obj = getJSONElement().getAsJsonObject();
        java.util.Set<java.util.Map.Entry<String,JsonElement>> entradas = obj.entrySet();
        java.util.Iterator<java.util.Map.Entry<String,JsonElement>> iter = entradas.iterator();
        
        System.out.println("AplicarCambios" + obj);
        
         while (iter.hasNext()) {
                    java.util.Map.Entry<String,JsonElement> entrada = iter.next();                    
                    
                    //System.out.println("Es objeto " + entrada.getKey() + " = " + entrada.getValue());
                    
                    
                     if ("profiles".equals(entrada.getKey())){ // Si esta en el objeto profiles
                            JsonObject perfiles = entrada.getValue().getAsJsonObject();
                           // System.out.println("ObjetoProfiles " + entrada.getKey() + " = " + entrada.getValue());
                            //System.out.println(perfiles);
                            
                            if (perfiles.get("Servidor Hispano").isJsonObject()){
                               // System.out.println("perfilHispano= " + perfilHispano );
                               
                               //Obtenemos el "perfil entero" del servidor, y el objeto auth que está dentro del mismo perfilHispano
                                JsonObject perfilHispano = perfiles.get("Servidor Hispano").getAsJsonObject();
                                JsonObject auth = perfilHispano.get("authentication").getAsJsonObject();
                                
                                
                                String ram = this.listRam.getSelectedItem().replace("B", "").replace(" ", "");
                                //System.out.println("RAM: " + "-Xmx"+ram);
                                
                                // Le aplicamos los cambios al objeto
                                auth.addProperty("displayName", this.txtUsuario.getText());
                                auth.addProperty("username", this.txtUsuario.getText());
                                
                                perfilHispano.addProperty("javaArgs", "-Xmx"+ram);
                                
                                
                            }
                            
                            
                            
                            
                        }
                    
                    
                        
                        if ("name".equals(entrada.getKey()) && "Servidor Hispano".equals(entrada.getValue().getAsString())){
                           // System.out.println("Hispano: " + entrada.getKey() + " = " + entrada.getValue());
                        }
                        
                        if ("javaArgs".equals(entrada.getKey())){
                            //System.out.println("Hispano: " + entrada.getKey() + " = " + entrada.getValue());
                        }
                    if (entrada.getValue().isJsonObject()){
                       // System.out.println("Es objeto " + entrada.getKey() + " = " + entrada.getValue());
                        
                        
                       
                        //if (obj.get("perfiles").isJsonObject() ){
                       // System.out.println("Es objeto2 " + entrada.getKey() + " = " + entrada.getValue());
                        /*
                             JsonObject perfiles = entrada.getValue().getAsJsonObject();
                            System.out.println(perfiles);
                            JsonObject servidorHispano = perfiles.get("Servidor Hispano").getAsJsonObject();
                            JsonObject nombreUsuario = servidorHispano.get("authentication").getAsJsonObject().get("displayName").getAsJsonObject();
                            this.txtUsuario.setText(nombreUsuario.getAsString());
                            System.out.println();*/
                        
                     //   }
                       
                        
                        
                        
                        
                    }

                    
                   
         }
         
        System.out.println("AplicarCambiosFinal" + obj);
        
        
        
         try (Writer writer = new FileWriter(System.getenv("APPDATA") + "\\.minecraft\\launcher_profiles.json")) {
                    Gson gson = new GsonBuilder().create();
                    gson.toJson(obj, writer);
                }  catch (IOException ex) {
                        System.out.println("Erorr: " +ex);
                }
        
        
    }
    
     
     
     
     
    public void aplicarCambiosPerfil2(){
            
         System.out.println("");
         System.out.println("----------------------------------------");
         JsonElement json = getJSONElement();
         
                       System.out.println(json);
        
        
        JsonObject obj = json.getAsJsonObject();
        java.util.Set<java.util.Map.Entry<String,JsonElement>> entradas = obj.entrySet();
        java.util.Iterator<java.util.Map.Entry<String,JsonElement>> iter = entradas.iterator();
        
         while (iter.hasNext()) {
                    java.util.Map.Entry<String,JsonElement> entrada = iter.next();
                    
                   // System.out.println(entrada.getKey() + " = " + entrada.getValue());
                    
                  /*  
                    if (entrada.getValue().isJsonObject()){
                        System.out.println("Es objeto " + entrada.getKey() + " = " + entrada.getValue());
                        
                        JsonObject perfiles = entrada.getValue().getAsJsonObject();
                        System.out.println(perfiles);
                        JsonObject servidorHispano = perfiles.get("Servidor Hispano").getAsJsonObject();
                        JsonObject nombreUsuario = servidorHispano.get("authentication").getAsJsonObject().get("displayName").getAsJsonObject();
                        this.txtUsuario.setText(nombreUsuario.getAsString());
                        System.out.println();
                        
                        
                        
                        
                    }

                    
                   if ("authentication".equals(entrada.getKey())){
                       System.out.println("auth");
                      // System.out.println(entrada.getValue().getAsJsonObject().get("username"));
                       //txtUsuario.setText(entrada.getValue().getAsJsonObject().get("username").getAsString());
                   }*/
         }
         
         
         
         
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Choice choice1;
    private java.awt.Choice choice2;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblConfiguracion;
    private java.awt.Choice listRam;
    private javax.swing.JPanel pnlConfiguracion;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
