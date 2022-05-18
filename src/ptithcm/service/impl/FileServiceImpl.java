package ptithcm.service.impl;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

import javafx.util.Pair;
import ptithcm.service.FileService;

public class FileServiceImpl implements FileService{

	@Override
	public Pair<Boolean, String> uploadFile(String basePath, MultipartFile imageFile, String fileName, String type, String object){
		if(!imageFile.getContentType().contains(type)) {
			return new Pair<Boolean, String>(false, "Không đúng định dạng file ảnh");
		}else {
			String photoPath = basePath + File.separator + object + File.separator + fileName;
			System.out.println(photoPath);
			try {
				imageFile.transferTo(new File(photoPath));
				Thread.sleep(2500);
				return new Pair<Boolean, String>(true, "resources" + File.separator + type + File.separator + object + File.separator + fileName);
			}
			catch (Exception e) {
				return new Pair<Boolean, String>(false, "Lỗi lưu file!");
			}
		}
	}

}
