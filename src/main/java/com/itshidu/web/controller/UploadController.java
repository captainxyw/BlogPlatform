package com.itshidu.web.controller;

import com.itshidu.web.vo.Result;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;


@Controller
public class UploadController {

    @ResponseBody
    @RequestMapping("/post/upload")
    public Object upload(int size, MultipartFile file, HttpServletRequest request) {
        System.out.println("文件大小：" + file.getSize());
        System.out.println("缩放尺寸：" + size);

        try {
            BufferedImage bi = ImageIO.read(file.getInputStream());


            int w = bi.getWidth();
            int h = bi.getHeight();
            int max = (int) Math.max(w, h);
            int tow = w;
            int toh = h;
            if (max > size) {
                if (w > h) {
                    tow = size;
                    toh = h * size / w;
                } else {
                    tow = w * size / h;
                    toh = size;
                }
            }

            String filename = UUID.randomUUID().toString() + ".jpg";
            String realPath = request.getSession().getServletContext().getRealPath("/store/temp");
            File dir = new File(realPath);
            if(!dir.exists()) {
                dir.mkdirs();
            }
            Thumbnails.of(file.getInputStream())
                .size(tow, toh)
                .outputFormat("jpg")
                .toFile(new File(dir,filename));
            return Result.of(1, "上传成功", "/store/temp/" + filename);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return Result.of(2, "上传失败");
    }

}
