package com.sky.controller.admin;

import com.sky.constant.MessageConstant;
import com.sky.result.Result;
import com.sky.utils.AliOssUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/admin/common")
@Slf4j
@Api(tags = "通用接口")
public class CommonController {
    @Autowired
    private AliOssUtil aliOssUtil;

    /**
     * 文件上传
     *
     * @param file 要上传的文件
     * @return 文件上传的阿里云oss路径
     */
    @PostMapping("/upload")
    @ApiOperation("文件上传")
    public Result<String> upload(MultipartFile file) {
        log.info("文件上传:{}", file);
        try {
            //获取文件原始名称
            String originalFilename = file.getOriginalFilename();
            //获取文件拓展名
            String extensionName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            String fileName = UUID.randomUUID().toString() + "." + extensionName;
            String url = aliOssUtil.upload(file.getBytes(), fileName);
            log.info("文件上传成功:{}", url);
            return Result.success(url);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Result.error(MessageConstant.UPLOAD_FAILED);
    }
}
