package com.rhq.parkinglotsystem.common;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class UpLoadImage {
    public static String uploadImage(MultipartFile multipartFile){

        try {
            String realfilename = multipartFile.getOriginalFilename();

            String imgSuffix = realfilename.substring(realfilename.lastIndexOf("."));

            String newFilename = UUID.randomUUID() +imgSuffix;

            String filePath = "E:/finalproject/ParkingLotWeb/public/avatars";

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            String datePath = dateFormat.format(new Date());
            //上传到哪个路径下
            File targetPath = new File( filePath , datePath); //"E:\\finalproject\\parkinglotsystem\\parkinglotsystem\\src\\main\\resources\\static\\avatars"
            if (!targetPath.exists()){
                targetPath.mkdirs();
            }
            File targetFilename = new File(targetPath, newFilename);
            multipartFile.transferTo(targetFilename);
//            return String.valueOf(targetFilename);//返回文件路径
            return "avatars/"+datePath+"/"+newFilename;//资源映射路径
        } catch (IOException e) {
            e.printStackTrace();
            return "失败！";
        }
    }
}
