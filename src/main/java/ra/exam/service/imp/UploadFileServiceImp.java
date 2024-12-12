package ra.exam.service.imp;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ra.exam.config.CloundiaryConfig;
import ra.exam.service.UploadFileService;

import java.io.IOException;
import java.util.Map;

@Service
public class UploadFileServiceImp implements UploadFileService {
    @Autowired
    private Cloudinary cloudiary;
    @Override
    public String uploadFile(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        if (originalFilename != null && originalFilename.contains(".")) {
            originalFilename = originalFilename.substring(0, originalFilename.lastIndexOf("."));
        }

        Map uploadParams = ObjectUtils.asMap(
                "public_id", originalFilename
        );

        Map uploadResult = cloudiary.uploader().upload(file.getBytes(), uploadParams);
        return uploadResult.get("url").toString();
    }
}

