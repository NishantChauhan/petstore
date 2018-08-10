package com.petstore.pet.utilities.filestorage.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.petstore.pet.utilities.LoggerUtil;
import com.petstore.pet.utilities.filestorage.StorageService;
import com.petstore.pet.utilities.filestorage.exceptions.StorageException;

@Service
public class FileSystemStorageService implements StorageService {
	
	final static Logger logger = LoggerFactory.getLogger(FileSystemStorageService.class);
	
	@Autowired
	Environment env;
	
	@Value("${imageURLrootLocation}")
	String imageRootLocation;

     @Override
    public void store(MultipartFile file, Path url) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        
        
        
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + filename);
            }
//            if (filename.contains("..")) {
//                // This is a security check
//                throw new StorageException(
//                        "Cannot store file with relative path outside current directory "
//                                + filename);
//            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, url.resolve(filename),
                    StandardCopyOption.REPLACE_EXISTING);
                
            }
        }
        catch (IOException e) {
            throw new StorageException("Failed to store file " + filename, e);
        }
    }


 
    @Override
    public Resource loadFileAsResource(String filename) throws Exception {
    	
    	
		Path  path = Paths.get(new URI(filename));
		byte[] fileArray = Files.readAllBytes(path);
		
		logger.debug("Loaded file with URI: " + filename);
		
		Resource imageResource=new ByteArrayResource(fileArray);
		
		LoggerUtil.exit(logger);
        return imageResource;
    }
    /*
    @Override
    public Stream<Path> loadAll(Path rootLocation) {
        try {
            return Files.walk(rootLocation, 1)
                .filter(path -> !path.equals(rootLocation))
                .map(rootLocation::relativize);
        }
        catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }

    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new StorageFileNotFoundException(
                        "Could not read file: " + filename);

            }
        }
        catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }
*/
    @Override
    public void init(Path rootLocation) {
        try {
            Files.createDirectories(rootLocation);
        }
        catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }
    
    @Override
	public String preparePhotoURLPath(boolean load, Long id) {
		StringBuilder strBld = new StringBuilder(4000);
		if(load) {
			strBld.append(imageRootLocation);
		}
		strBld.append("photoURL/").append(String.valueOf(id)).append("/");
		return strBld.toString();
	}
}
