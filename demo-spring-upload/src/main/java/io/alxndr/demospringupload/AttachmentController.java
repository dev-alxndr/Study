package io.alxndr.demospringupload;

import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class AttachmentController {

    private Logger logger = LoggerFactory.getLogger(AttachmentController.class);

    @Value("${file.upload.path}")
    private String filePath;

    @GetMapping
    public String index() {
        logger.info(filePath + File.separator );
        return "index";
    }

    @PostMapping
    @ResponseBody
    public AttachMentDto write(@RequestParam("file") MultipartFile files) {
        AttachMentDto attachMentDto = null;
        try {
            String origFilename = files.getOriginalFilename();
            String filename = UUID.randomUUID().toString() + FilenameUtils.EXTENSION_SEPARATOR + FilenameUtils.getExtension(origFilename);
            /* 실행되는 위치의 'files' 폴더에 파일이 저장됩니다. */
//            String savePath = System.getProperty("user.home") + File.separator + filePath + File.separator + FileUtil.FileType.IMAGE.getPath();
            String savePath = filePath + File.separator + FileUtil.FileType.IMAGE.getPath();
            /* 파일이 저장되는 폴더가 없으면 폴더를 생성합니다. */
            if (!new File(savePath).exists()) {
                try{
                    new File(savePath).mkdir();
                }
                catch(Exception e){
                    e.getStackTrace();
                }
            }
            String filePath = savePath + File.separator + filename;
            files.transferTo(new File(filePath));
            attachMentDto= new AttachMentDto();
            attachMentDto.setOriginalFileName(origFilename);
            attachMentDto.setFilePath(filePath);
            attachMentDto.setSavedFileName(filename);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return attachMentDto;
    }

}
