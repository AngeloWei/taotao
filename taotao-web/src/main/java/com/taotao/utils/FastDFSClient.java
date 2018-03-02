package com.taotao.utils;

import org.csource.common.MyException;
import org.csource.fastdfs.*;

import java.io.IOException;

public class FastDFSClient {

    private StorageServer storageServer = null;
    /*
    * 初始化storageClient
    *
    * */
    public StorageClient1 getStorageClient(String pathOfConf) throws IOException, MyException {
        ClientGlobal.initByProperties(pathOfConf);
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        StorageClient1 storageClient = new StorageClient1(trackerServer, storageServer);
        return storageClient;
    }
}
