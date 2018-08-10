package com.petstore.pet.utilities.filestorage;

import java.nio.file.Path;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    void init(Path location);

    void store(MultipartFile file, Path url);
    
	Resource loadFileAsResource(String filename) throws Exception;

	String preparePhotoURLPath(boolean load,Long id);
    
    /*
    Stream<Path> loadAll();

	Path load(String filename);

    Resource loadAsResource(String filename);

    void deleteAll();
    */

}
