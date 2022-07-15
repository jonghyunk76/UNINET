package com.yni.fta.common.tools;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

/**
 * FTP 처리 클래스
 * 
 * @author Tom
 */
public class FtpUtil {
	private static Log log = LogFactory.getLog(FtpUtil.class);

	private FTPClient ftpClient;
	private String encoding = "UTF-8";
	private final int BUFFER_SIZE = 1024 * 10;
	private final int TIMEOUT = 60 * 1000;

	// --------------------------------------------------------------------------------
	// require to inject attributes
	// --------------------------------------------------------------------------------
	private String username;
	private String password;
	private String hostname;
	private int port;

	public FtpUtil() {
	}

	public FtpUtil(String hostname, int port, String username, String password) {
		this.hostname = hostname;
		this.port = port;
		this.username = username;
		this.password = password;
		this.ftpClient = new FTPClient();
	}

	/**
	 * Login to the FTP Server
	 * @throws Exception 
	 */
	public boolean login() throws Exception {
		FTPClientConfig fcc = new FTPClientConfig();
		fcc.setServerTimeZoneId(TimeZone.getDefault().getID());
		ftpClient.setControlEncoding(encoding);
		ftpClient.configure(fcc);
		try {
			this.ftpClient.enterLocalPassiveMode();
			if (port > 0) {
				ftpClient.connect(hostname, port);
			} else {
				ftpClient.connect(hostname);
			}
			// check reply code
			int code = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(code)) {
				ftpClient.disconnect();
				log.error("Login FTP Server is failure!");
				throw new Exception("Login FTP Server is failure!");
			}

			if (ftpClient.login(username, password)) {
				// setting
				// this.ftpClient.enterLocalPassiveMode();
				this.ftpClient.type(FTPClient.BINARY_FILE_TYPE);
				this.ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
				this.ftpClient.setBufferSize(BUFFER_SIZE);
				this.ftpClient.setDataTimeout(TIMEOUT);
				log.info(username + " successfully logined to the FTP server.");
				return true;
			} else {
				throw new Exception("FTP Username, Password 오류");
			}
		} catch (Exception e) {
			log.error(username + " failed to login to the FTP server", e);
			throw new Exception(username + " failed to login to the FTP server<br> " +e.getMessage());			
		}
	}

	/**
	 * FTP 서버에 파일 업로드
	 * 
	 * @param file       파일 디렉토리 및 이름의 전체 디렉토리를 포함하여 업로드할 파일
	 * @param remotePath FTP 서버의 경로
	 * @throws Exception 
	 */
	public boolean uploadFile(String file, String remotePath) throws Exception {
		boolean dirExists = ftpClient.changeWorkingDirectory(remotePath);
		ftpClient.enterLocalPassiveMode();
		if (!dirExists) {
			ftpClient.mkd(remotePath);
		}
		
		return uploadFile(new File(file), remotePath);
	}

	/**
	 * FTP 서버에 파일 업로드
	 * 
	 * @param file       파일 디렉토리 및 이름의 전체 디렉토리를 포함하여 업로드할 파일
	 * @param remotePath FTP 서버의 경로
	 */
	public boolean uploadFile(File srcFile, String remotePath) throws Exception{
		BufferedInputStream bis = null;
		String filePath = srcFile.getPath();
		try {
			String fileName = srcFile.getName();
			ftpClient.changeWorkingDirectory(remotePath);
			ftpClient.enterLocalPassiveMode();
			bis = new BufferedInputStream(new FileInputStream(srcFile));

			// store file
			if (this.ftpClient.storeFile(fileName, bis)) {
				log.info(fileName + " 성공적으로 업로드됨");
				return true;
			}
		} catch (FileNotFoundException e) {
			log.error(filePath + " 찾을 수 없음", e);
			throw new Exception(filePath + " 찾을 수 없음" + e.getMessage());		
		} catch (IOException e) {
			log.error(filePath + " 업로드 오류", e);
			throw new Exception("CSVSupporter.listToCsvFile Error " + e.getMessage());		
		} finally {
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
				}
			}
		}
		return false;
	}

	/**
	 * FTP 서버에 폴더 업로드
	 * 
	 * @param localPath  업로드해야 하는 로컬 폴더의 경로
	 * @param remotePath FTP 서버 경로
	 * @throws Exception 
	 */
	public boolean uploadDirectory(String localPath, String remotePath) throws Exception {
		return uploadDirectory(new File(localPath), remotePath);
	}

	/**
	 * FTP 서버에 폴더 업로드
	 * 
	 * @param dir        업로드해야 하는 로컬 폴더의 경로
	 * @param remotePath FTP 서버 경로
	 * @throws Exception 
	 */
	public boolean uploadDirectory(File dir, String remotePath) throws Exception {
		try {
			ftpClient.makeDirectory(remotePath);
		} catch (IOException e) {
			log.error(remotePath + "디렉토리 생성 실패", e);
		}
		File[] files = dir.listFiles();

		// 파일 업로드
		for (File file : files) {
			if (!file.isDirectory()) {
				uploadFile(file, remotePath);
			}
		}

		// 폴더를 찾아 재귀적으로 업로드
		for (File subDir : files) {
			if (subDir.isDirectory()) {
				String remoteSubDir = remotePath + File.separator + subDir.getName();
				uploadDirectory(subDir, remoteSubDir);
			}
		}
		return true;
	}

	/**
	 * download single file
	 * 
	 * @param remotefileName 원격 파일
	 * @param remotePath     원격 디렉토리
	 * @param localPath      로컬 디렉토리
	 * @param delete         다운로드 후 삭제 여부
	 */
	public boolean downloadFile(FTPFile remotefile, String remotePath, String localPath, boolean delete) {
		String localFileName = localPath + File.separator + remotefile.getName();
		BufferedOutputStream bos = null;
		try {
			File localFile = new File(localFileName);
			if (!localFile.exists()) {
				localFile.createNewFile();
			}

			ftpClient.changeWorkingDirectory(remotePath);
			ftpClient.enterLocalPassiveMode();
			bos = new BufferedOutputStream(new FileOutputStream(localFile));

			if (ftpClient.retrieveFile(remotefile.getName(), bos)) {
				localFile.setLastModified(remotefile.getTimestamp().getTime().getTime());
				log.info(remotefile.getName() + " successfully downloaded to the " + localFileName);
				if (delete && ftpClient.deleteFile(remotefile.getName())) {
					log.info(remotefile.getName() + " successfully removed.");
				}
				System.out.println(localFile.lastModified());
				return true;
			}
		} catch (Exception e) {
			log.error(remotefile.getName() + " download failure", e);
		} finally {
			if (null != bos) {
				try {
					bos.close();
				} catch (IOException e) {
				}
			}
		}
		return false;
	}


	/**
	 * download directory
	 * 
	 * @param localPath  로컬 경로
	 * @param remotePath 원격 폴더
	 * @param delete     다운로드 후 파일 삭제 여부
	 */
	/*
	 * public boolean downLoadDirectory(String localPath, String remotePath, boolean
	 * delete) { try { File localFile = new File(localPath); localFile.mkdirs();
	 * 
	 * FTPFile[] files = this.ftpClient.listFiles(remotePath);
	 * 
	 * // download single file for (FTPFile file : files) { if (file.isFile()) {
	 * downloadFile(file, remotePath, backupPath, localPath, delete); } }
	 * 
	 * // download directory for (FTPFile file : files) { String subDir =
	 * file.getName(); if (file.isDirectory() && !".".equals(subDir) &&
	 * !"..".equals(subDir)) { downLoadDirectory(localPath + File.separator +
	 * subDir, remotePath + File.separator + subDir, delete); } } } catch
	 * (IOException e) { log.error("download directory failure", e); return false; }
	 * return true; }
	 */
	
	/**
	 * 파일명 목록 in Directory FTP Server 
	 * @param localPath
	 * @param remotePath
	 * @param delete
	 * @return
	 * @throws Exception 
	 */
	public FTPFile[] remotePathFiles(String remotePath) throws Exception {

		FTPFile[] files = null;
		try {
			boolean dirExists = ftpClient.changeWorkingDirectory(remotePath);
			
			if (!dirExists) {
				ftpClient.mkd(remotePath);
			}
			ftpClient.changeWorkingDirectory(remotePath);	// 파일 가져올 디렉터리 위치
			ftpClient.enterLocalPassiveMode();

//			for(FTPFile fileName :ftpClient.listFiles()){
//				System.out.println(fileName);
//			}
			files = ftpClient.listFiles();
		} catch (Exception e) {
			log.error("Get Directory FileName ERROR : ", e);
			throw new Exception("CSVSupporter.remotePathFiles Error " + e.getMessage());		
		}
		return files;
	}
	
	

	/**
	 * FTP 서버에서 파일 삭제
	 * 
	 * @param remotefile
	 * @return
	 * @throws Exception
	 */
	public boolean delete(String remoteFile) throws Exception {
		return ftpClient.deleteFile(remoteFile);
	}
	
	/**
	 * FTP 서버에서 파일 이동
	 * 
	 * @param remotefile
	 * @return
	 * @throws Exception
	 */
	public boolean rename(String remotePath, String backupPath, String fileName) throws Exception {
		boolean success = false;
		boolean dirExists = true;
		boolean backupFileExists = true;
		String soruceFile = remotePath+fileName;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar c1 = Calendar.getInstance();
        String strTodayDir = sdf.format(c1.getTime());				// 백업 디렉토리(오늘날짜)
		String backupDir = backupPath+strTodayDir;					// 백업 전체 디렉토리
		String backupFile = backupDir+File.separator+fileName;		// 백업 전체 파일
		try {
			dirExists = ftpClient.changeWorkingDirectory(backupDir);
			ftpClient.enterLocalPassiveMode();
			if (!dirExists) {
				ftpClient.mkd(backupDir);
			}
			FTPFile remoteFile = ftpClient.mlistFile(backupFile);
			if (remoteFile != null) {
				success = delete(backupFile);						// 백업 폴더에서 동일 파일명 삭제
			}

			success = ftpClient.rename(soruceFile, backupFile);		// 파일 백업
			System.out.println("rename : " + success + " fileName : " + backupFile);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			throw new Exception("FTP Rename Error " + e.getMessage());
		}
		return success;
	}	

	/**
	 * logout from the FTP Server and release connect
	 */
	public void logout() {
		if (null == ftpClient || !ftpClient.isConnected()) {
			return;
		}
		try {
			// logout from the FTP Server
			if (ftpClient.logout()) {
//				log.info("logout from the FTP Server.");
			}
		} catch (IOException e) {
			log.error("failed to logout to the FTP server!", e);
		} finally {
			try {
				// release connect
				ftpClient.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws Exception {

		FtpUtil ftp = new FtpUtil("itwfta.com", 21, "encom", "encom1219!");
		// FtpUtil ftp = new FtpUtil("daeuserver.iptime.org", 60001, "toms", "toms1219");
		ftp.login();

		FTPFile[] remotePathFiles = ftp.remotePathFiles("/inbound/"); 

		ftp.logout();
	}
}
