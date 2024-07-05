package com.nie.tool.server.file.web;

import com.nie.tool.common.core.util.AssertUtil;
import com.nie.tool.common.web.domain.R;
import com.nie.tool.server.file.biz.FileBiz;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author njy
 * @since 2024/6/28 09:53
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("file")
public class FileController {
//    private final FileBiz fileBiz;

    /**
     * 上传图片
     *
     * @param file file
     * @return com.olrtc.common.core.domain.R<java.lang.String>
     * @author njy
     * @since 15:23 2023/3/30
     */
    @PostMapping("img")
    public R<String> uploadImg(MultipartFile file) {
//        AssertUtil.noNull(file, "文件不能为空");
//        AssertUtil.isTrue(fileBiz.checkFileIsImg(file), "文件上传失败,请上传符合格式的 png/jpg 类型的图片");
//        AssertUtil.isFalse(fileBiz.checkFileIsOverSize(file.getSize(), 5, "M"), "文件上传失败,请上传大小不超过5MB的图片");

        String originalFilename = file.getOriginalFilename();
        String newFileName = "file/images/"
                + UUID.randomUUID().toString().replace("-", "")
                + "-"
                + originalFilename;
        try {
            InputStream inputStream = file.getInputStream();
//            Thread.startVirtualThread(() -> {
//                // TODO upload
//            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return R.ok();
    }


}
