package com.gatherhub.service.Impl;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.gatherhub.dto.UploadFilePDFDTO;
import com.gatherhub.service.UploadPDFToAwsService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;


@Service
public class UploadPDFToAwsServiceImpl implements UploadPDFToAwsService {

    AmazonS3 s3 = null;

    @Value("${s3.region}")
    private String region;

    @Value("${s3.endpoint}")
    private String endpoint;

    @Value("${s3.bucketName}")
    private String bucketName;

    @Value("${s3.accessKeyId}")
    private String accessKeyId;

    @Value("${s3.accessKeySecret}")
    private String accessKeySecret;




    @Override
    public UploadFilePDFDTO uploadFilePDF(MultipartFile file) {

        String tempFileName = file.getOriginalFilename();
        String originalFilename = file.getOriginalFilename();

        String contentType = file.getContentType();
        long fileSize = file.getSize();

        String awsBucketName = bucketName;
        String filePath = awsBucketName + "/" +tempFileName;

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(contentType);
        objectMetadata.setContentLength(fileSize);

//        ObjectMetadata是AWS 提供的一個Class，用於描述儲存在AWS S3服務中的物件的元資料
//        在AWS S3中，一個物件除了包含實際的檔案內容外，還可以有一些描述該檔案的元資料
//        EX:檔案類型、檔案大小、最後修改時間。這些元資料通常是(key-value)的存在。

        try {
            PutObjectResult putObjectResult = s3.putObject(awsBucketName, tempFileName, file.getInputStream(), objectMetadata);
            s3.setObjectAcl(awsBucketName, tempFileName, CannedAccessControlList.PublicRead);
        } catch (AmazonServiceException e) {
            System.out.println(e.getErrorMessage());
        }catch (IOException e){
            System.out.println(e.getMessage());

        }

//       使用putObject()方法將檔案上傳到指定的AWS S3 Bucket中
//       putObject()方法需要傳入以下參數：
//       -- awsBucketName：指定要上傳的AWS S3 Bucket的名稱。
//       --tempFileName：指定在AWS S3中要儲存的檔案名稱。
//       --file.getInputStream()：獲取要上傳檔案的輸入串流(InputStream)。
//       --objectMetadata：用於指定檔案的元資料。


//        putObject()方法會返回一個PutObjectResult物件
//        可以使用這個返回的PutObjectResult物件來獲取這些信息
//        --確認上傳是否成功：可以檢查PutObjectResult物件中的一些屬性，如getETag()，這個值可以用於確認上傳是否成功。
//        --處理上傳後的其他操作：在上傳成功後，你可能需要進行其他相關的後續操作，例如設置檔案的訪問權限、更新檔案的元資料等。

//        使用setObjectAcl()方法來設置檔案的訪問權限。
//        我們將檔案的訪問權限設置為PublicRead，代表該檔案可以被公開讀取。

        UploadFilePDFDTO uploadFilePDFDTO = new UploadFilePDFDTO();


        uploadFilePDFDTO.setFileName(originalFilename);
        uploadFilePDFDTO.setFileSize(fileSize);
        uploadFilePDFDTO.setFileType(contentType);
        uploadFilePDFDTO.setFilePath(filePath);
        uploadFilePDFDTO.setUrl(endpoint + "/" + filePath);
        return  uploadFilePDFDTO;
    }


    @PostConstruct
    public void init() {
        ClientConfiguration config = new ClientConfiguration();
        AwsClientBuilder.EndpointConfiguration endpointConfiguration = new AwsClientBuilder.EndpointConfiguration(endpoint, region);
        AWSCredentials awsCredentials = new BasicAWSCredentials(accessKeyId, accessKeySecret);
        AWSCredentialsProvider awsCredentialsProvider = new AWSStaticCredentialsProvider(awsCredentials);

        s3 = AmazonS3Client.builder()
                .withEndpointConfiguration(endpointConfiguration)
                .withClientConfiguration(config)
                .withCredentials(awsCredentialsProvider)
                .disableChunkedEncoding()
                .withPathStyleAccessEnabled(true)
                .build();
    }

}
