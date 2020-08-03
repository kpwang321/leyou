package com.leyou.upload.service;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author kpwang
 * @create 2020-08-02 18:07
 */
@Service
public class UploadService {
    private static final List<String> CONTENT_TYPES= Arrays.asList("image/gif","image/jpeg","image/png");
    private static final Logger LOGGER= LoggerFactory.getLogger(UploadService.class);
    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    /**
     * 文件上传
     * @param file
     * @return
     */
    public String uploadImage(MultipartFile file) {
        //校验文件类型
        String filename = file.getOriginalFilename();
        /*boolean flag = filename.endsWith(".jpg") || filename.endsWith(".png");
        if(StringUtils.endsWithAny(filename,new String[]{".jpg",".png"})){

        }*/
        String contentType = file.getContentType();
        if (!CONTENT_TYPES.contains(contentType)){
            LOGGER.info("文件类型不合法:{}",filename);
            return null;
        }
        try {
            //校验文件内容
            BufferedImage read = ImageIO.read(file.getInputStream());
            if (read==null){
                LOGGER.info("文件内容不合法:{}",filename);
                return null;
            }
            //保存到文件服务器
            //file.transferTo(new File("F:\\hm\\image\\"+filename));
            String ext = StringUtils.substringAfterLast(filename, ".");
            StorePath storePath = this.fastFileStorageClient.uploadFile(file.getInputStream(), file.getSize(), ext, null);
            return "http://image.leyou.com/"+storePath.getFullPath();
        } catch (IOException e) {
            LOGGER.error("服务器内部错误:{}"+filename,e);

        }

        //返回url，进行回显
        //return "http://image.leyou.com/"+filename;
        return null;
    }
}
