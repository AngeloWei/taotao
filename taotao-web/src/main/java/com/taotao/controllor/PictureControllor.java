package com.taotao.controllor;

import com.taotao.utils.FastDFSClient;
import org.csource.common.MyException;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


import java.util.HashMap;
import java.util.Map;

@Controller
public class PictureControllor {
    @Value("${IMAGE_SERVER_URL}")
    private String IMAGE_SERVER_URL;
    @RequestMapping("/pic/upload")
    @ResponseBody
    public Map uploadFile(MultipartFile uploadFile) {

        //获取文件服务器的StorageServer
        try {
            String fileName = uploadFile.getOriginalFilename();
            String fileExtName = fileName.substring(fileName.lastIndexOf(".")+1);

            StorageClient1 storageClient = new FastDFSClient().getStorageClient("fastdfs/fastdfs-client.properties");
            String path = storageClient.upload_file1(uploadFile.getBytes(), fileExtName, null);
            path = IMAGE_SERVER_URL +path;
            Map result = new HashMap();
            result.put("error", 0);
            result.put("url", path);
            return  result;

        } catch (Exception e) {
            e.printStackTrace();
            Map result = new HashMap();
            result.put("error", 0);
            result.put("message", "图片上传失败");
            return result;
        }

    }
}
