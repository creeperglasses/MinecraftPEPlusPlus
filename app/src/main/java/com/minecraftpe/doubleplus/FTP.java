package com.minecraftpe.doubleplus;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import java.io.BufferedReader;
import java.text.*;
import java.io.InputStreamReader;

public class FTP {
    private String hostName;
    private String userName;
    private String password;
    private FTPClient ftpClient;
    private List<FTPFile> list;
    public static final String REMOTE_PATH = "\\";
    private String currentPath = "";

    private double response;

    /**
     * 构造函数.
     * @param host hostName 服务器名
     * @param user userName 用户名
     * @param pass password 密码
     */
    public FTP(String host, String user, String pass) {
        this.hostName = host;
        this.userName = user;
        this.password = pass;
        this.ftpClient = new FTPClient();
        this.list = new ArrayList<FTPFile>();
    }
    public void openConnect() throws IOException {
        ftpClient.setControlEncoding("UTF-8");
        int reply;
        ftpClient.connect(hostName);
        reply = ftpClient.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            ftpClient.disconnect();
            throw new IOException("connect fail: " + reply);
        }
        ftpClient.login(userName, password);
        reply = ftpClient.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            ftpClient.disconnect();
            throw new IOException("connect fail: " + reply);
        } else {
            FTPClientConfig config = new FTPClientConfig(ftpClient.getSystemType().split(" ")[0]);
            config.setServerLanguageCode("zh");
            ftpClient.configure(config);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(org.apache.commons.net.ftp.FTP.BINARY_FILE_TYPE);
            System.out.println("login");
        }
    }
    public void closeConnect() throws IOException {
        if (ftpClient != null) {
            ftpClient.logout();
            ftpClient.disconnect();
            System.out.println("logout");
        }
    }
    public List<FTPFile> listFiles(String remotePath) throws IOException {
        FTPFile[] files = ftpClient.listFiles(remotePath);
        for (FTPFile file : files) {
            list.add(file);
        }
        return list;
    }
	/**
     * 验证.
     * @param remotePath FTP目录
     * @param fileName 文件名
     * @return boolean
     * @throws IOException 
     */
    public boolean mppCheck(String remotePath, String fileName) throws IOException {
        boolean result=false;
        ftpClient.changeWorkingDirectory(remotePath);
        FTPFile[] ftpFiles = ftpClient.listFiles();
        for (FTPFile ftpFile : ftpFiles) {
            if (ftpFile.getName().equals(fileName)) {
				result=true;
				}
				}
			return result;	
				}
			
    /**
     * 下载.
     * @param remotePath FTP目录
     * @param fileName 文件名
     * @param localPath 本地目录
     * @return Result
     * @throws IOException 
     */
    public Result download(String remotePath, String fileName, String localPath) throws IOException {
        boolean flag = true;
        Result result = null;
        currentPath = remotePath;
        response = 0;
        ftpClient.changeWorkingDirectory(remotePath);
        FTPFile[] ftpFiles = ftpClient.listFiles();
        for (FTPFile ftpFile : ftpFiles) {
            if (ftpFile.getName().equals(fileName)) {
             
                File file = new File(localPath + "/" + fileName);
                Date startTime = new Date();
                if (ftpFile.isDirectory()) {
                    flag = downloadMany(file);
                } else {
                    flag = downloadSingle(file, ftpFile);
                }
                Date endTime = new Date();
                result = new Result(flag, Util.getFormatTime(endTime.getTime() - startTime.getTime()), Util.getFormatSize(response));
            }
        }
        return result;
    }

    /**
     * 下载单个文件.
     * @param localFile 本地目录
     * @param ftpFile FTP目录
     * @return true下载成功, false下载失败
     * @throws IOException 
     */
    private boolean downloadSingle(File localFile, FTPFile ftpFile) throws IOException {
        boolean flag = true;
        OutputStream outputStream = new FileOutputStream(localFile);
        response += ftpFile.getSize();
        flag = ftpClient.retrieveFile(localFile.getName(), outputStream);
        outputStream.close();
        return flag;
    }

    /**
     * 下载多个文件.
     * @param localFile 本地目录
     * @return true下载成功, false下载失败
     * @throws IOException 
     */
    private boolean downloadMany(File localFile) throws IOException {
        boolean flag = true;
        if (!currentPath.equals(REMOTE_PATH)) {
            currentPath = currentPath + REMOTE_PATH + localFile.getName();
        } else {
            currentPath = currentPath + localFile.getName();
        }
        localFile.mkdir();
        ftpClient.changeWorkingDirectory(currentPath);
        FTPFile[] ftpFiles = ftpClient.listFiles();
        for (FTPFile ftpFile : ftpFiles) {
            File file = new File(localFile.getPath() + "/" + ftpFile.getName());
            if (ftpFile.isDirectory()) {
                flag = downloadMany(file);
            } else {
                flag = downloadSingle(file, ftpFile);
            }
        }
        return flag;
    }

   
	/**
	 * 通过ftp上传文件
	 * @param url ftp服务器地址 如： 192.168.1.110
	 * @param port 端口如 ： 21
	 * @param username  登录名
	 * @param password   密码
	 * @param remotePath  上到ftp服务器的磁盘路径
	 * @param fileNamePath  要上传的文件路径
	 * @param fileName		要上传的文件名
	 * @return
	 */
	public String ftpUpload(String url, String port, String username,String password, String remotePath, String fileNamePath,String fileName) {
		FTPClient ftpClient = new FTPClient();
		FileInputStream fis = null;
		String returnMessage = "0";
		try {
			ftpClient.connect(url, Integer.parseInt(port));
			boolean loginResult = ftpClient.login(username, password);
			int returnCode = ftpClient.getReplyCode();
			if (loginResult && FTPReply.isPositiveCompletion(returnCode)) {
				ftpClient.makeDirectory(remotePath);
				ftpClient.changeWorkingDirectory(remotePath);
				ftpClient.setBufferSize(1024);
				ftpClient.setControlEncoding("UTF-8");
				ftpClient.enterLocalPassiveMode();
				fis = new FileInputStream(fileNamePath + fileName);
				ftpClient.storeFile(fileName, fis);

				returnMessage = "1";   		
			} else {
				returnMessage = "0";
			}


		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException( e);
		} finally {
			//IOUtils.closeQuietly(fis);
			try {
				ftpClient.disconnect();
			} catch (IOException e) {
			 	e.printStackTrace();
			 	throw new RuntimeException( e);
		 	}
		}
		return returnMessage;
	}
	
	/**
	   * @param path
	   * @return function:读取指定目录下的文件名
	   * @throws IOException
	   */
	public List<String> getFileList(String path) {
		List<String> fileLists = new ArrayList<String>();
		// 获得指定目录下所有文件名
		FTPFile[] ftpFiles = null;
		try {
			ftpFiles = ftpClient.listFiles(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (int i = 0; ftpFiles != null && i < ftpFiles.length; i++) {
			FTPFile file = ftpFiles[i];
			if (file.isFile()) {
				fileLists.add(file.getName());
			}
		}
		return fileLists;
	}
	
	/**
	   * @param fileName
	   * @return function:从服务器上读取指定的文件
	   * @throws ParseException
	   * @throws IOException
	   */
	public String readFile(String file){
		InputStream ins = null;
		StringBuilder builder = null;
		try {
			// 从服务器上读取指定的文件
			ins = ftpClient.retrieveFileStream(file);
			BufferedReader reader = new BufferedReader(new InputStreamReader(ins, "UTF-8"));
			String line;
			builder = new StringBuilder(150);
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
				builder.append(line+"\n");
			}
			reader.close();
			if (ins != null) {
				ins.close();
			}
			// 主动调用一次getReply()把接下来的226消费掉. 这样做是可以解决这个返回null问题
			ftpClient.getReply();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return builder.toString();
	}

	/**
	   * @param fileName function:删除文件
	   */
	public void deleteFile(String fileName) {
		try {
			ftpClient.deleteFile(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
}
