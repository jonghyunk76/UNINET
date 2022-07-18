package com.yni.fta.common.ws;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Map;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yni.fta.common.batch.vo.BatchVo;

import kr.yni.frame.util.StringHelper;

public class TlsServiceClient {
	
	private static Log log = LogFactory.getLog(TlsServiceClient.class);
	
    private static final int delay = 1000; // in millis
    
    private static final String[] protocols = new String[] {"TLSv1.3"}; // TLSv1.2
    
    private static final String[] cipher_suites = new String[] {"TLS_AES_128_GCM_SHA256"};
    
    /**
     * TSL방식의 인터페이스 수행
     * 
     * @param batchVo
     * @param url
     * @param port
     * @param map
     * @return
     * @throws Exception
     */
    public boolean executeInterface(BatchVo batchVo, String url, int port, Map map) throws Exception {
    	boolean rst = true;
    	
    	OutputStream os = null;
        BufferedReader in = null;
    	String message = StringHelper.null2void(map.get("REQ_PARAMS"));
    	
    	// 생성된 서버에 요청하고 응답받기
        try (SSLSocket socket = createSocket(url, port)) {
            os = new BufferedOutputStream(socket.getOutputStream());
            
            os.write(message.getBytes());
            os.flush();
            
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            StringBuffer sbuf = new StringBuffer();
            String inputLine = null;
            
            while ((inputLine = in.readLine()) != null) {
            	sbuf.append(inputLine);
            }
            
            int len = sbuf.length();
            
            if (len <= 0) {
                throw new IOException("no data received");
            }
            
            log.debug("client received "+len+" bytes / " + sbuf.toString());
            
            batchVo.setReturnData(sbuf.toString());
        } catch(Exception e) {
        	log.error(e.getMessage());
        	
        	batchVo.setErrorMessage(e.getMessage());
        	
        	throw e;
        }
        
        return rst;
    }

    public static SSLSocket createSocket(String host, int port) throws IOException {
        SSLSocket socket = (SSLSocket) SSLSocketFactory.getDefault().createSocket(host, port);
        
        socket.setEnabledProtocols(protocols);
        socket.setEnabledCipherSuites(cipher_suites);
        
        return socket;
    }
    
    /**
     * 서버 생성
     * 
     * @return
     * @throws Exception
     */
    public static boolean createTlsServer() throws Exception  {
    	try(EchoServer server = EchoServer.create()) { // 서버 생성
            new Thread(server).start();
            Thread.sleep(delay);
        }
    	
    	return true;
    }
    
    // TLS 서버 생성 클래스
    public static class EchoServer implements Runnable, AutoCloseable {

        private static final int FREE_PORT = 0;

        private final SSLServerSocket sslServerSocket;

        private EchoServer(SSLServerSocket sslServerSocket) {
            this.sslServerSocket = sslServerSocket;
        }

        public int port() {
            return sslServerSocket.getLocalPort();
        }

        @Override
        public void close() throws IOException {
            if (sslServerSocket != null && !sslServerSocket.isClosed()) {
                sslServerSocket.close();
            }
        }

        @Override
        public void run() {
            log.debug("server started on port " + port());

            try (SSLSocket socket = (SSLSocket) sslServerSocket.accept()) {
                InputStream is = new BufferedInputStream(socket.getInputStream());
                OutputStream os = new BufferedOutputStream(socket.getOutputStream());
                
                byte[] data = new byte[2048];
                int len = is.read(data);
                
                if (len <= 0) {
                    throw new IOException("no data received");
                }
                
                log.debug("server received "+len+" bytes: " + new String(data, 0, len));
                
                os.write(data, 0, len);
                os.flush();
            } catch (Exception e) {
                log.error("exception: " + e.getMessage());
            }

            log.debug("server stopped");
        }
        
        public static EchoServer create() throws IOException {
            return create(FREE_PORT);
        }

        public static EchoServer create(int port) throws IOException {
            SSLServerSocket socket = (SSLServerSocket) SSLServerSocketFactory.getDefault().createServerSocket(port);
            
            socket.setEnabledProtocols(protocols);
            socket.setEnabledCipherSuites(cipher_suites);
            
            return new EchoServer(socket);
        }
    }
    
}
