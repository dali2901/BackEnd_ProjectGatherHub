package com.gatherhub.service;

import com.gatherhub.dto.UploadFilePDFDTO;
import org.springframework.web.multipart.MultipartFile;

//這個service處理前端傳來的PDF檔案


public interface UploadPDFToAwsService {

    UploadFilePDFDTO  uploadFilePDF ( MultipartFile file);
}
