package ptithcm.service;

import org.springframework.web.multipart.MultipartFile;

import javafx.util.Pair;

public interface FileService {
	public Pair<Boolean, String> uploadFile(String basePath, MultipartFile imageFile, String fileName, String type, String object);
}
