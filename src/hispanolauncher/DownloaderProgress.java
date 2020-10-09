/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hispanolauncher;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author inuckles
 */



public class DownloaderProgress {
    

    public interface RBCWrapperDelegate {
        // The RBCWrapperDelegate receives rbcProgressCallback() messages
        // from the read loop.  It is passed the progress as a percentage
        // if known, or -1.0 to indicate indeterminate progress.
        // 
        // This callback hangs the read loop so a smart implementation will
        // spend the least amount of time possible here before returning.
        // 
        // One possible implementation is to push the progress message
        // atomically onto a queue managed by a secondary thread then
        // wake that thread up.  The queue manager thread then updates
        // the user interface progress bar.  This lets the read loop
        // continue as fast as possible.
        public String rbcProgressCallback( RBCWrapper rbc, double progress );
    }

    public static final class Downloader implements RBCWrapperDelegate {
        public Downloader( String localPath, String remoteURL ) {
            FileOutputStream        fos;
            ReadableByteChannel     rbc;
            URL                     url;

            try {
                
                url = new URL( remoteURL );
                rbc = new RBCWrapper( Channels.newChannel( url.openStream() ), contentLength( url ), this );
                fos = new FileOutputStream( localPath );
                fos.getChannel().transferFrom( rbc, 0, Long.MAX_VALUE );
            } catch ( IOException e ) {
                System.err.println( "Uh oh: " + e.getMessage() );
            }
        }

        public String rbcProgressCallback( RBCWrapper rbc, double progress ) { 
                 return "s";
        }

        public int contentLength( URL url ) {
            HttpURLConnection           connection;
            int                         contentLength = -1;

            try {
                HttpURLConnection.setFollowRedirects( false );

                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod( "HEAD" );

                contentLength = connection.getContentLength();
            } catch ( IOException e ) { }

            return contentLength;
        }
    }

    private static final class RBCWrapper implements ReadableByteChannel {
        private RBCWrapperDelegate              delegate;
        private long                            expectedSize;
        private ReadableByteChannel             rbc;
        private long                            readSoFar;

        RBCWrapper( ReadableByteChannel rbc, long expectedSize, RBCWrapperDelegate delegate ) {
            this.delegate = delegate;
            this.expectedSize = expectedSize;
            this.rbc = rbc;
        }

        @Override
        public void close() throws IOException { rbc.close(); }
        public long getReadSoFar() { return readSoFar; }
        @Override
        public boolean isOpen() { return rbc.isOpen(); }

        @Override
        public int read( ByteBuffer bb ) throws IOException {
            int                     n;
            double                  progress;

            if ( ( n = rbc.read( bb ) ) > 0 ) {
                readSoFar += n;
                progress = expectedSize > 0 ? (double) readSoFar / (double) expectedSize * 100.0 : -1.0;
                delegate.rbcProgressCallback( this, progress );
                
            }

            return n;
        }
    }
}