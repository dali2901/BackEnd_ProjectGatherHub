package com.gatherhub.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadFilePDFDTO {

    private long fileSize;
    private String fileName;
    private String url;
    private String filePath;
    private  String fileType;

}
