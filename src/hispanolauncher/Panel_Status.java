/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hispanolauncher;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**
 *
 * @author inuckles
 */
public class Panel_Status extends javax.swing.JPanel   {
    
    private Timer t;
    private ActionListener ac;
    int x = 0;
    public boolean debeActualizar = false;
    public boolean puedeJugar = false;
    public boolean estaActualizando  = false;
    
    
    
    
    

    /**
     * Creates new form Inicio
     */
    public Panel_Status() {
            initComponents();
            
            checkJSONFile();
             String folder = System.getenv("APPDATA") + "\\.hispano_launcher\\minecraft\\version_hispano.txt";
             
                   
                   
                

            
           // descargarMinecraft();
            
            
            //DownloaderProgress.Downloader downloader = new DownloaderProgress.Downloader( "mods2.zip", "https://swd.cl/mods.zip" );
            
            //System.out.println(downloader.rbcProgressCallback(ac, PROPERTIES));
            
            
             
            
            
            
            
          
            
            
            
        
       
    }

    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    
 
    private void descargarMinecraftPorPrimeraVez(){
        
         log("Ingresa a descargar minecraft por primera vez");
        
        
         String rutaAppData = System.getenv("APPDATA") + "\\.hispano_launcher\\downloads\\"; 
         File directorio = new File(rutaAppData);
         
         if (!directorio.exists())
             if (!directorio.mkdir())
                 log("Directorio downloads no pudo ser creado");
        
        
        
         Runnable updatethread = () -> {
                try {
                    progresoLauncher.setStringPainted(false);
                    
                    URL url = new URL("https://swd.cl/mods.zip");
                    HttpURLConnection httpConnection = (HttpURLConnection) (url.openConnection());
                    long completeFileSize = httpConnection.getContentLength();
                    java.io.BufferedInputStream in = new java.io.BufferedInputStream(httpConnection.getInputStream());
                    java.io.FileOutputStream fos = new java.io.FileOutputStream(
                    System.getenv("APPDATA") + "\\.hispano_launcher\\downloads\\mods.zip");
                    java.io.BufferedOutputStream bout = new BufferedOutputStream(
                            fos, 1024);
                    byte[] data = new byte[1024];
                    long downloadedFileSize = 0;
                    int x1 = 0;
                    
                    
                    
                       
                        
                    while ((x1 = in.read(data, 0, 1024)) >= 0) {
                        downloadedFileSize += x1;
                        // Calcula el progreso
                        
                        long descargado = downloadedFileSize/1024;
                        long porDescargar = completeFileSize/1024;
                        final int currentProgress = (int) ((((double)downloadedFileSize) / ((double)completeFileSize)) * 100d);
                       
                        SwingUtilities.invokeLater(() -> {
                            progresoLauncher.setValue(currentProgress);
                            
                            
                            
                            if (currentProgress > 85){
                                lblDescripcion.setText("Descargando shaders ... " + descargado + " KB / " + porDescargar + " KB");
                            }else if (currentProgress > 70){
                                lblDescripcion.setText("Descargando texturas... " + descargado + " KB / " + porDescargar + " KB");
                            }else if (currentProgress > 35){
                                lblDescripcion.setText("Descargando todos los mods del servidor... " + descargado + " KB / " + porDescargar + " KB");
                            }else if (currentProgress > 0){ 
                                lblDescripcion.setText("Descargando la version 1.12.2 de Minecraft... " + descargado + " KB / " + porDescargar + " KB");
                            }
                            
                            
                            
                            
                            estaActualizando = true;
                        });
                        bout.write(data, 0, x1);
                    }
                    bout.close();
                    in.close();
                    
                    // Entrará aqui cuando se termine de descargar la nueva actualización.
                    
                    estaActualizando = false;
                    debeActualizar = false;
                    sleep(500);
                    lblDescripcion.setText("Juego descargado. Listo para jugar");
                    btnStatus.setText("Jugar");
                    checkJSONFile();
                    
                    log("Finaliza la descarga de Minecraft");
                    actualizarVersion();
                    
                    
                }catch (FileNotFoundException e) {
                }catch (IOException e) {
                } catch (InterruptedException ex) {
                 Logger.getLogger(Panel_Status.class.getName()).log(Level.SEVERE, null, ex);
             }
            };
        new Thread(updatethread).

        start();
       
        
        
    }
    
    // Esta funcion se llama cuando el jugador descarga por primera vez el minecraft
    // Basicamente lo que hace es revisar si el usuario tiene el archivo launcher_profiles.json
    // Si no lo tiene, descarga una plantilla con el perfil del servidor
    private void checkJSONFile(){
        
        String dirDescarga = System.getenv("APPDATA") + "\\.minecraft\\";
        String nombreArchivo = "launcher_profiles.json";
        
        File directorio = new File(dirDescarga+nombreArchivo);
        
        if (directorio.exists()){
                System.out.println("El archivo existe...");
        }else{
                System.out.println("El archivo no existe, descargando...");
                
                descargarArchivo("https://swd.cl/launcher_profiles.json",System.getenv("APPDATA")+"\\.minecraft\\",nombreArchivo,"Descargando perfil del servidor...");
     
            
                System.out.println("Finaliza descarga");
            
            
            
        }
    
    }
    
    
    // Esta funcion permite descargar un archivo.
    // Se le pasa por parametro:
    // String url: URL de donde se descargará
    // String ruta: Ruta donde se dejará la descarga
    // String texto: Texto que mostrará en la barra de descarga cuando esté descargando
    private void descargarArchivo(String urlDescarga, String ruta,String nombreArchivo, String texto){
         log("Ingresa a descargar archivo con los parametros");
         log("URL: " + urlDescarga);
         log("Ruta: " + ruta);
         log("Texto: " + texto);
         
         
         String rutaFinal = ruta + "/" + nombreArchivo;
         File directorio = new File(ruta + "/" + nombreArchivo);
         
         System.out.println(directorio.getAbsolutePath());
         System.out.println(rutaFinal);
         
         
        //String rutaFinal = ruta + "/" + nombreArchivo;
        //File file = new File();
        
        
         
         Runnable updatethread = () -> {
                try {
                    progresoLauncher.setStringPainted(false);
                    
                    URL url = new URL(urlDescarga);
                    HttpURLConnection httpConnection = (HttpURLConnection) (url.openConnection());
                    long completeFileSize = httpConnection.getContentLength();
                    java.io.BufferedInputStream in = new java.io.BufferedInputStream(httpConnection.getInputStream());
                    System.out.println(ruta);
                    java.io.FileOutputStream fos = new java.io.FileOutputStream(rutaFinal);
                    java.io.BufferedOutputStream bout = new BufferedOutputStream(
                            fos, 1024);
                    byte[] data = new byte[1024];
                    long downloadedFileSize = 0;
                    int x1 = 0;        
                    while ((x1 = in.read(data, 0, 1024)) >= 0) {
                        downloadedFileSize += x1;
                        long descargado = downloadedFileSize/1024/1024;
                        long porDescargar = completeFileSize/1024/1024;
                        final int currentProgress = (int) ((((double)downloadedFileSize) / ((double)completeFileSize)) * 100d);
                        SwingUtilities.invokeLater(() -> {
                            progresoLauncher.setValue(currentProgress);
                            lblDescripcion.setText(texto+" | " + descargado + " MB / " + porDescargar + " MB");
                            estaActualizando = true;
                        });
                        bout.write(data, 0, x1);
                    }
                    bout.close();
                    in.close();
                    estaActualizando = false;
                    debeActualizar = false;
                    lblDescripcion.setText("Descarga realizada. ");
                    btnStatus.setText("Jugar");
                    log("Finaliza la descarga del archivo. URL: " + url);                   
                    
                }catch (FileNotFoundException e) {
                }catch (IOException e) {
                }
            };
        new Thread(updatethread).

        start();
    
    }
    
    private void descargarMinecraft(){
        
        log("Ingresa a descargar minecraft");
        
        
         String rutaAppData = System.getenv("APPDATA") + "\\.hispano_launcher\\downloads\\"; 
         File directorio = new File(rutaAppData);
         
         if (!directorio.exists())
             if (!directorio.mkdir())
                 log("Directorio downloads no pudo ser creado");
        
        
        
         Runnable updatethread = () -> {
                try {
                    progresoLauncher.setStringPainted(false);
                    
                    URL url = new URL("https://swd.cl/mods.zip");
                    HttpURLConnection httpConnection = (HttpURLConnection) (url.openConnection());
                    long completeFileSize = httpConnection.getContentLength();
                    java.io.BufferedInputStream in = new java.io.BufferedInputStream(httpConnection.getInputStream());
                    java.io.FileOutputStream fos = new java.io.FileOutputStream(
                    System.getenv("APPDATA") + "\\.hispano_launcher\\downloads\\mods.zip");
                    java.io.BufferedOutputStream bout = new BufferedOutputStream(
                            fos, 1024);
                    byte[] data = new byte[1024];
                    long downloadedFileSize = 0;
                    int x1 = 0;
                    
                    
                    
                       
                        
                    while ((x1 = in.read(data, 0, 1024)) >= 0) {
                        downloadedFileSize += x1;
                        // Calcula el progreso
                        
                         long descargado = downloadedFileSize/1024/1024;
                        long porDescargar = completeFileSize/1024/1024;
                        final int currentProgress = (int) ((((double)downloadedFileSize) / ((double)completeFileSize)) * 100d);
                        //System.out.println((currentProgress * 100) /100000);
                        // update progress bar
                        
                        SwingUtilities.invokeLater(() -> {
                            progresoLauncher.setValue(currentProgress);
                            lblDescripcion.setText("Descargando nueva actualización... " + descargado + " MB / " + porDescargar + " MB");
                            estaActualizando = true;
                        });
                        bout.write(data, 0, x1);
                    }
                    bout.close();
                    in.close();
                    
                    // Entrará aqui cuando se termine de descargar la nueva actualización.
                    
                    estaActualizando = false;
                    debeActualizar = false;
                    lblDescripcion.setText("Actualización descargada. ");
                    btnStatus.setText("Jugar");
                    
                    log("Finaliza la descarga de Minecraft");
                    actualizarVersion();
                    
                    
                }catch (FileNotFoundException e) {
                }catch (IOException e) {
                }
            };
        new Thread(updatethread).

        start();
       
            
            
        
    }
    
    
   
    
    private void print(String texto){
       System.out.println(texto);
    }
    
    
     
    public void getChangelog(){
    
        try {
            String texto = new Scanner(new URL("https://pastebin.com/raw/mpBH40ff").openStream(), "UTF-8").useDelimiter("\\A").next();
            //System.out.println(texto);
            
            JOptionPane.showMessageDialog(null, 
                    "Hay una nueva actualización para el servidor!\n\n"
                    +texto);
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(Panel_Inicio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Panel_Inicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
        
    }
    
    private void checkInstalacion(){
       
       String rutaAppData = System.getenv("APPDATA");
       
       
        String forgeDIR = rutaAppData + "\\.minecraft\\libraries\\net\\minecraftforge\\forge\\1.12.2-14.23.5.2838";
        String forgeFile = "\\forge-1.12.2-14.23.5.2838.jar";
        
        File forgeFolder = new File(forgeDIR);
        File forgeJAR = new File(forgeDIR+forgeFile);
        
        if (!forgeFolder.exists()){
            forgeFolder.mkdir();
        }
        
        if (!forgeJAR.exists()){
            descargarForge();
        }
        
        
        
        
        String rutaMC = rutaAppData + "\\.hispano_launcher\\minecraft";
        File dirMC = new File(rutaMC);
        boolean existeDir = dirMC.exists();
        print(dirMC.getAbsolutePath());
        
        // Verifica si existe la carpeta .minecraft
        if (existeDir){
            // Ok, la carpeta .minecraft existe..
            // Pero, es del servidor hispano la instalación?
            //print(".minecraft existe");
            File hispanoFile = new File(rutaMC + "\\version_hispano.txt");
            
            if (hispanoFile.length()==0){
                
                if (hispanoFile.exists()){
                    log("El archivo version_hispano.txt está vacio");
                }else{
                    log("El archivo version_hispano.txt no existe");
                    descargarMinecraftPorPrimeraVez();
                }
                
                log("Forzando actualización...");
                //descargarMinecraft();
                
            }else{
                try { 

                    Scanner lectorHispanoFile = new Scanner(hispanoFile);
                    String versionLocal = lectorHispanoFile.nextLine();
                    
                    
                    //System.out.println(Arrays.toString(versionLocal.getBytes()));
                    
                    
                    if (hispanoFile.exists()){
                        URL url = new URL("https://pastebin.com/raw/geQEC8kH");
                        Scanner sc = new Scanner(url.openStream());
                        StringBuilder sb = new StringBuilder();
                        
                         while(sc.hasNext()) {
                            sb.append(sc.next());
                         }
                         
                        String versionInternet = sb.toString();
                        log("Verificando version..  | local: " + versionLocal + " | " +  versionInternet);


                        if (versionLocal == null ? versionInternet == null : versionLocal.equals(versionInternet)){
                            // El usuario tiene la version que corresponde con la de internet
                            // Pero antes de dejarlo jugar, tambien verificaremos si tiene el launcher yofenix.
                            
                            log("No se ha encontrado ninguna actualización nueva");
                            
                            boolean tieneLauncher = verificarYoFenixLauncher();
                            
                            sleep(500);
                            if (tieneLauncher){
                                this.lblDescripcion.setText("No se ha encontrado ninguna actualización nueva. Abriendo juego..");
                                ejecutarMinecraft();
                                debeActualizar=false;
                                puedeJugar = true;
                            }else{
                                log("ERROR: Despues de verificar el launcher, aun así no lo tenía. ");
                            }
                                    
                            
                       
                           

                            
                            
                        }else{
                            
                            log("Hay una nueva actualizacion!");
                            log("Mostrando changelog..");
                            debeActualizar = true;
                            btnStatus.setText("Actualizar");
                            getChangelog();

                        }
                    }else{
                        // El usuario tiene una instalacion de Minecraft pero no es la de Hispano
                        print("El archivo version_hispano.txt no existe");
                    }



                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Panel_Status.class.getName()).log(Level.SEVERE, null, ex);
                } catch (MalformedURLException ex) {
                        Logger.getLogger(Panel_Status.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                        Logger.getLogger(Panel_Status.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Panel_Status.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            
            
            
            
            
        }else{
            log("No se ha encontrado la carpeta .minecraft en AppData");
        }
       
     
       
    }
    
    
    
    private boolean verificarYoFenixLauncher(){
    
         String rutaAppData = System.getenv("APPDATA") + "\\.hispano_launcher\\launcher\\"; 
         File directorio = new File(rutaAppData);
         
        log("Verificando si el usuario tiene el launcher YoFenix.");
        
        if (!directorio.exists()){
            log("Directorio launcher no existe, Creando uno.");
            if (!directorio.mkdir())
                 log("No se pudo crear el directorio del launcher.");
         }
        
        
        if (directorio.exists()){
            log("Directorio launcher existe.");
            File yofenix = new File(rutaAppData + "yofenix.jar");
                 
            if (yofenix.exists()){
                return true;
            }else{
                descargarYoFenixLauncher();
                ejecutarMinecraft();
            }
        }
         
         
        return false;
    }
    
    
    
    private void descargarYoFenixLauncher(){
        
        
         Runnable updatethread = () -> {
                try {
                    progresoLauncher.setStringPainted(false);
                    
                    // Establecemos la URL de donde descargará el archivo
                    URL url = new URL("https://swd.cl/yofenix.jar");
                    // Realizamos la conexión
                    HttpURLConnection httpConnection = (HttpURLConnection) (url.openConnection());
                    // Obtenemos el tamaño del archivo a descargar
                    long completeFileSize = httpConnection.getContentLength();
                    // Definimos la ruta donde se descargará el archivo
                    String rutaDescarga = System.getenv("APPDATA") + "\\.hispano_launcher\\launcher\\yofenix.jar";
                    // Abrimos los streams para descargar y grabar el archivo
                    java.io.BufferedInputStream in = new java.io.BufferedInputStream(httpConnection.getInputStream());
                    java.io.FileOutputStream fos = new java.io.FileOutputStream(rutaDescarga);
                    java.io.BufferedOutputStream bout = new BufferedOutputStream(
                            fos, 1024);
                    byte[] data = new byte[1024];
                    long downloadedFileSize = 0;
                    int x1 = 0;
                    
                    log("Inicia descarga de Launcher");
                    
                        
                    while ((x1 = in.read(data, 0, 1024)) >= 0) {
                        downloadedFileSize += x1;
                        
                        // Calcula el progreso
                        long descargado = downloadedFileSize/1024;
                        long porDescargar = completeFileSize/1024;
                        final int currentProgress = (int) ((((double)downloadedFileSize) / ((double)completeFileSize)) * 100d);
                        //System.out.println((currentProgress * 100) /100000);
                        // update progress bar
                        
                        SwingUtilities.invokeLater(() -> {
                            progresoLauncher.setValue(currentProgress);
                            lblDescripcion.setText("Descargando YoFenix Launcher..  " + descargado + " kb / "+ porDescargar + " kb");
                            estaActualizando = true;
                            
                            
                                
                        });
                        bout.write(data, 0, x1);
                    }
                    bout.close();
                    in.close();
                    
                    // Entrará aqui cuando se termine de descargar la nueva actualización.
                    
                    log("Finaliza descarga de Launcher");
                    
                    
                    sleep(500);
                    lblDescripcion.setText("Launcher Descargado, iniciando...");
                    sleep(500);
                    lblDescripcion.setText("");
                    
                    
                    ejecutarMinecraft();
                    estaActualizando = false;
                    debeActualizar=false;
                    puedeJugar = true;
                    
                    
                }catch (FileNotFoundException e) {
                    log("Error al descargar launcher (FileNotFoundException): " + e);
                }catch (IOException e) {
                    log("Error al descargar launcher (IOException): " + e);
                } catch (InterruptedException ex) {
                 Logger.getLogger(Panel_Status.class.getName()).log(Level.SEVERE, null, ex);
             }
            };
        new Thread(updatethread).

        start();
       
            
     
        
        
    }
    
    
    
    
    
    
        private void descargarForge(){
        
        
         Runnable updatethread = () -> {
                try {
                    progresoLauncher.setStringPainted(false);
                    
                    // Establecemos la URL de donde descargará el archivo
                    URL url = new URL("https://swd.cl/forge.jar");
                    // Realizamos la conexión
                    HttpURLConnection httpConnection = (HttpURLConnection) (url.openConnection());
                    // Obtenemos el tamaño del archivo a descargar
                    long completeFileSize = httpConnection.getContentLength();
                    // Definimos la ruta donde se descargará el archivo
                    String rutaDescarga = System.getenv("APPDATA") + "\\.minecraft\\libraries\\net\\minecraftforge\\forge\\1.12.2-14.23.5.2838\\forge-1.12.2-14.23.5.2838.jar";
                    // Abrimos los streams para descargar y grabar el archivo
                    java.io.BufferedInputStream in = new java.io.BufferedInputStream(httpConnection.getInputStream());
                    java.io.FileOutputStream fos = new java.io.FileOutputStream(rutaDescarga);
                    java.io.BufferedOutputStream bout = new BufferedOutputStream(
                            fos, 1024);
                    byte[] data = new byte[1024];
                    long downloadedFileSize = 0;
                    int x1 = 0;
                    
                    
                    log("Inicia descarga del forge");
                                            
                    while ((x1 = in.read(data, 0, 1024)) >= 0) {
                        downloadedFileSize += x1;
                        
                        // Calcula el progreso
                        long descargado = downloadedFileSize/1024;
                        long porDescargar = completeFileSize/1024;
                        final int currentProgress = (int) ((((double)downloadedFileSize) / ((double)completeFileSize)) * 100d);
                        //System.out.println((currentProgress * 100) /100000);
                        // update progress bar
                        
                        SwingUtilities.invokeLater(() -> {
                            progresoLauncher.setValue(currentProgress);
                            lblDescripcion.setText("Descargando Forge...  " + descargado + " kb / "+ porDescargar + " kb");
                            estaActualizando = true;
                            
                            
                                
                        });
                        bout.write(data, 0, x1);
                    }
                    bout.close();
                    in.close();
                    
                    // Entrará aqui cuando se termine de descargar la nueva actualización.
                    
                    log("Finaliza descarga de Launcher");
                    
                    
                    sleep(500);
                    lblDescripcion.setText("Launcher Descargado, iniciando...");
                    sleep(500);
                    lblDescripcion.setText("");
                    
                    
                    estaActualizando = false;
                    debeActualizar=false;
                    puedeJugar = true;
                    
                    
                }catch (FileNotFoundException e) {
                    log("Error al descargar launcher (FileNotFoundException): " + e);
                }catch (IOException e) {
                    log("Error al descargar launcher (IOException): " + e);
                } catch (InterruptedException ex) {
                 Logger.getLogger(Panel_Status.class.getName()).log(Level.SEVERE, null, ex);
             }
            };
        new Thread(updatethread).

        start();
       
            
     
        
        
    }
    
    private void iniciarTimer1(){
        this.progresoLauncher.setStringPainted(true);
        ac = new ActionListener(){
            
            
            @Override
            public void actionPerformed(ActionEvent ae) {
                x = x + 1;  
                progresoLauncher.setValue(x);
                
                
               
                if (progresoLauncher.getValue() == 1){
                    progresoLauncher.setString("Conectando al servidor principal...");
                }else if (progresoLauncher.getValue() == 10){
                    progresoLauncher.setString("Consultando estado servidor...");
                }else if (progresoLauncher.getValue() == 20){
                    progresoLauncher.setString("Verificando instalación... 1/3");
                }else if (progresoLauncher.getValue() == 30){
                    progresoLauncher.setString("Verificando instalación... 2/3");
                }else if (progresoLauncher.getValue() == 40){
                    progresoLauncher.setString("Verificando instalación... 3/3");
                }else if (progresoLauncher.getValue() == 50){
                    progresoLauncher.setString("Cargando...");
                } else if (progresoLauncher.getValue() == 100){
                    t.stop();
                } 
                
                if (progresoLauncher.getValue() == 100){
                    t.stop();
                }
                
            }
        };
        t = new Timer(50,ac);
        t.start();
    }
    
   
    private void actualizarVersion(){
    
            print("Actualizará versión");
        String rutaLOG = System.getenv("APPDATA") + "\\.hispano_launcher\\minecraft\\version_hispano.txt";
        File file = new File(rutaLOG);
        
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException ex) {
                    log("Hubo un error al crear el archivo version_hispano.txt. " + ex);
                }
            }
        
        

        // escribe la nueva version en el archivo
        try { 
            URL url = new URL("https://pastebin.com/raw/geQEC8kH");
            Scanner sc = new Scanner(url.openStream());
            StringBuilder sb = new StringBuilder();
             while(sc.hasNext()) {
                 print("LECTURA");
                sb.append(sc.next());
             }
            String versionInternet = sb.toString();
            print(versionInternet);
            
            try {
               
                FileWriter myWriter = new FileWriter(rutaLOG); 
                myWriter.write(versionInternet);
                myWriter.close();
                log("Version Actualizada correctamente a la v" + versionInternet);
              } catch (IOException e) {
                log("ERROR: " + e);
              }
           
            btnStatus.setText("Jugar"); 
            debeActualizar=false;
            puedeJugar = true;           
            
        } catch (IOException ex) {
                log("ERROR: " + ex);
        }
         
     
        
    }
    
    
    
    public void log(String texto){
            
                String rutaLog = System.getenv("APPDATA") + "\\.hispano_launcher\\logs\\";

                
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
    
    
    
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblStatus = new javax.swing.JLabel();
        progresoLauncher = new javax.swing.JProgressBar();
        btnStatus = new javax.swing.JButton();
        lblDescripcion = new javax.swing.JLabel();

        lblStatus.setFont(new java.awt.Font("Ubuntu", 0, 48)); // NOI18N
        lblStatus.setForeground(new java.awt.Color(240, 240, 240));
        lblStatus.setText("Jugar");
        lblStatus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblStatusMouseClicked(evt);
            }
        });

        progresoLauncher.setBackground(new java.awt.Color(0, 0, 0));
        progresoLauncher.setToolTipText("");
        progresoLauncher.setBorderPainted(false);
        progresoLauncher.setName(""); // NOI18N

        btnStatus.setBackground(new java.awt.Color(255, 255, 255));
        btnStatus.setFont(new java.awt.Font("Ubuntu", 0, 36)); // NOI18N
        btnStatus.setText("Jugar");
        btnStatus.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 5, true));
        btnStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStatusActionPerformed(evt);
            }
        });

        lblDescripcion.setBackground(new java.awt.Color(0, 0, 0));
        lblDescripcion.setForeground(new java.awt.Color(255, 255, 255));
        lblDescripcion.setOpaque(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(123, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblDescripcion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(progresoLauncher, javax.swing.GroupLayout.DEFAULT_SIZE, 697, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(btnStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(114, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(progresoLauncher, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addComponent(lblDescripcion)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lblStatusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblStatusMouseClicked
        
        
        
    }//GEN-LAST:event_lblStatusMouseClicked

    private void btnStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStatusActionPerformed
       
        
        if (!estaActualizando){
            if (debeActualizar){
                log("Se presiono jugar y se actualizará el minecraft ya que detectó version nueva.");
                descargarMinecraft();
            }else if (puedeJugar){
                
                log("Se presiono jugar y el usuario puedeJugar. Pero verificamos si tiene el launcher");
                if (verificarYoFenixLauncher()){
                    log("Tiene el launcher, ejecuta minecraft");
                    ejecutarMinecraft();
                }
            }else{
                System.out.println("Checkea instalacion");
                checkInstalacion();
            }
        }
        
        
        
        
    }//GEN-LAST:event_btnStatusActionPerformed

    public void ejecutarMinecraft(){
        
        
        
        String launcher = System.getenv("APPDATA") + "\\.hispano_launcher\\launcher\\yofenix.jar";
        
        try {
            Process proc = Runtime.getRuntime().exec("java -jar " + launcher); 
        } catch (IOException ex) {
            log("Error al ejecutar el launcher YoFenix." + ex);
        }
        
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnStatus;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JProgressBar progresoLauncher;
    // End of variables declaration//GEN-END:variables

    private Object FileOutputStream(String examplezip) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
