package com.gatherhub.controller;


import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadFileController {






    // 多個檔案上傳的接口
    @PostMapping(value = "/api/upload/multi" ,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)

    public ResponseEntity<String> uploadFileMulti(
            @RequestParam("files") MultipartFile uploadfiles) {


        // 取得檔案名稱
        String fileName = uploadfiles.getOriginalFilename();

        return ResponseEntity.ok(fileName);

//        String uploadedFileName = Arrays.stream(uploadfiles)
//                .map(x -> x.getOriginalFilename())
//                .filter(x -> !StringUtils.isEmpty(x))
//                .collect(Collectors.joining(" , "));
//
//        if (StringUtils.isEmpty(uploadedFileName)) {
//            return new ResponseEntity<>("請選擇檔案!", HttpStatus.OK);
//        }
//
//        try {
//
//            saveUploadedFiles(Arrays.asList(uploadfiles));
//
//        } catch (IOException e) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//
//        return new ResponseEntity<>("成功上傳 - "
//                + uploadedFileName, HttpStatus.OK);
//
//    }
//
//    private void saveUploadedFiles(List<MultipartFile> files) throws IOException {
//
//        for (MultipartFile file : files) {
//
//            if (file.isEmpty()) {
//                continue; //繼續下一個檔案
//            }
//
//            byte[] bytes = file.getBytes();
//            Path path = Paths.get(uploadedFolder + file.getOriginalFilename());
//            Files.write(path, bytes);
//
//        }
//
//    }
    }
}

