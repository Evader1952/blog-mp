//package com.mp.blog.common.utils;
//
//import com.jcraft.jsch.*;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.*;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Properties;
//import java.util.Vector;
//
///**
// * @author lvlu
// * @date 2019-07-06 11:51
// **/
//public class SftpUtils {
//    private static Logger log = LoggerFactory.getLogger(SftpUtils.class);
//
//    private String host;
//    private String username;
//    private String password;
//    private int port = 22;
//    private ChannelSftp sftp = null;
//    private Session sshSession = null;
//
//    public SftpUtils() {
//    }
//
//    public SftpUtils(String host, int port, String username, String password) {
//        this.host = host;
//        this.username = username;
//        this.password = password;
//        this.port = port;
//    }
//
//    public SftpUtils(String host, String username, String password) {
//        this.host = host;
//        this.username = username;
//        this.password = password;
//    }
//
//    /**
//     * * 通过SFTP连接服务器
//     */
//    public boolean connect() {
//        try {
//            JSch jsch = new JSch();
//            sshSession = jsch.getSession(username, host, port);
//            sshSession.setPassword(password);
//
//            Properties sshConfig = new Properties();
//            sshConfig.put("StrictHostKeyChecking", "no");
//            sshSession.setConfig(sshConfig);
//            sshSession.connect();
//            if (log.isInfoEnabled()) {
//                log.info("jsch Session connected.");
//            }
//
//            Channel channel = sshSession.openChannel("sftp");
//            channel.connect();
//            if (log.isInfoEnabled()) {
//                log.info("jsch Opening sftp Channel.");
//            }
//
//            sftp = (ChannelSftp) channel;
//            if (log.isInfoEnabled()) {
//                log.info("Connected to " + host + ".");
//            }
//
//            return true;
//        } catch (Exception e) {
//            log.error(" sftp 连接超时...{}", e.getMessage());
//        }
//
//        return false;
//    }
//
//    /**
//     * * 关闭连接
//     */
//    public void disconnect() {
//        if (this.sftp != null) {
//            if (this.sftp.isConnected()) {
//                this.sftp.disconnect();
//                if (log.isInfoEnabled()) {
//                    log.info(" sftp is closed");
//                }
//            }
//        }
//        if (this.sshSession != null) {
//            if (this.sshSession.isConnected()) {
//                this.sshSession.disconnect();
//                if (log.isInfoEnabled()) {
//                    log.info(" sshSession is closed");
//                }
//            }
//        }
//    }
//
//    /**
//     * 下载单个文件
//     *
//     * @param remotePath
//     * @param remoteFileName
//     * @return
//     */
//    public InputStream downloadFile(String remotePath, String remoteFileName) {
//        try {
//            InputStream inputStream = sftp.get(remotePath + remoteFileName);
//            log.info("=== file.download: [{}] success.", remoteFileName);
//            return inputStream;
//        } catch (Exception e) {
//            if (e.getMessage().toLowerCase().equals("no such file")) {
//                if (log.isDebugEnabled()) {
//                    log.debug("=== file.download.error: [{}], {}.", remoteFileName, e.getMessage());
//                }
//            } else {
//                log.error("=== file.download.error: [{}], {}.", remoteFileName, e.getMessage());
//            }
//        }
//        return null;
//    }
//
//    /**
//     * * 批量下载文件
//     * * @return
//     */
//    public List<InputStream> downloadFiles(String remotePath, List<String> fileNames) {
//        List<InputStream> downFileList = new ArrayList<InputStream>();
//        try {
//            if (!DataUtil.isEmpty(fileNames)) {
//                for (String remoteFileName : fileNames) {
//                    InputStream inputStream = downloadFile(remotePath, remoteFileName);
//                    if (!DataUtil.isEmpty(inputStream)) {
//                        downFileList.add(inputStream);
//                    }
//                }
//            }
//            return downFileList;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return downFileList;
//    }
//
//    /**
//     * * 上传单个文件
//     * * @param remotePath：远程保存目录
//     * * @param remoteFileName：保存文件名
//     * * @param localPath：本地上传目录(以路径符号结束)
//     * * @param localFileName：上传的文件名
//     * * @return
//     */
//    public boolean uploadFile(String remotePath, String remoteFileName, String localFile) {
//        FileInputStream in = null;
//        try {
//            cdDir(remotePath);
//            in = new FileInputStream(localFile);
//            sftp.put(in, remoteFileName);
//            if (log.isInfoEnabled()) {
//                log.info(" file.upload: [{}] success.", localFile.substring(localFile.lastIndexOf(File.separator)));
//            }
//            return true;
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (SftpException e) {
//            e.printStackTrace();
//        } finally {
//            if (in != null) {
//                try {
//                    in.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return false;
//    }
//
//    /**
//     * * 删除本地文件
//     * * @param filePath
//     * * @return
//     */
//    public boolean deleteLocal(File file) {
//        if (!file.exists()) {
//            return false;
//        }
//
//        if (!file.isFile()) {
//            return false;
//        }
//
//        boolean rs = file.delete();
//        if (rs && log.isInfoEnabled()) {
//            log.info(" file.delete.success.");
//        }
//        return rs;
//    }
//
//    /**
//     * * 创建目录
//     * * @param createpath
//     * * @return
//     */
//    public boolean cdDir(String createpath) {
//        try {
//            String pwd = this.sftp.pwd();
//            if (pwd.contains("/" + createpath + "/")) {
//                return true;
//            }
//            if (isDirExist(createpath)) {
//                this.sftp.cd(createpath);
//                return true;
//            }
//            String pathArry[] = createpath.split("/");
//            StringBuffer filePath = new StringBuffer("/");
//            for (String path : pathArry) {
//                if (path.equals("")) {
//                    continue;
//                }
//                filePath.append(path + "/");
//                if (isDirExist(filePath.toString())) {
//                    sftp.cd(filePath.toString());
//                } else {
//                    // 建立目录
//                    sftp.mkdir(filePath.toString());
//                    // 进入并设置为当前目录
//                    sftp.cd(filePath.toString());
//                }
//            }
//            pwd = this.sftp.pwd();
//            if (pwd.contains("/" + createpath + "/")) {
//                return true;
//            }
//        } catch (SftpException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    /**
//     * * 判断目录是否存在
//     * * @param directory
//     * * @return
//     */
//    public boolean isDirExist(String directory) {
//        boolean isDirExistFlag = false;
//        try {
//            SftpATTRS sftpATTRS = sftp.lstat(directory);
//            isDirExistFlag = true;
//            return sftpATTRS.isDir();
//        } catch (SftpException e) {
//            if (e.id == ChannelSftp.SSH_FX_NO_SUCH_FILE)
//            //if (e.getMessage().toLowerCase().equals("no such file"))
//            {
//                isDirExistFlag = false;
//            }
//        }
//        return isDirExistFlag;
//    }
//
//    /**
//     * * 删除stfp文件
//     * * @param directory：要删除文件所在目录
//     * * @param deleteLocal：要删除的文件
//     * * @param sftp
//     */
//    public void deleteSFTP(String directory, String deleteFile) {
//        try {
//            // sftp.cd(directory);
//            sftp.rm(directory + deleteFile);
//            if (log.isInfoEnabled()) {
//                log.info("delete file success from sftp.");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * * 如果目录不存在就创建目录
//     * * @param path
//     */
//    public void mkdirs(String path) {
//        File f = new File(path);
//
//        String fs = f.getParent();
//
//        f = new File(fs);
//
//        if (!f.exists()) {
//            f.mkdirs();
//        }
//    }
//
//    /**
//     * * 列出目录下的文件
//     * *
//     * * @param directory：要列出的目录
//     * * @param sftp
//     * * @return
//     * * @throws SftpException
//     */
//    public Vector listFiles(String directory) throws SftpException {
//        return sftp.ls(directory);
//    }
//
//    public String getHost() {
//        return host;
//    }
//
//    public void setHost(String host) {
//        this.host = host;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public int getPort() {
//        return port;
//    }
//
//    public void setPort(int port) {
//        this.port = port;
//    }
//
//    public ChannelSftp getSftp() {
//        return sftp;
//    }
//
//    public void setSftp(ChannelSftp sftp) {
//        this.sftp = sftp;
//    }
//
//    /**
//     * 测试
//     */
//    public static void main(String[] args) {
//
//    }
//}
