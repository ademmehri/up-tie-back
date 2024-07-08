package com.rhplateforme.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rhplateforme.Repos.FileRepository;
import com.rhplateforme.Repos.UserRepository;
import com.rhplateforme.entities.Employee;
import com.rhplateforme.entities.File;

import jakarta.mail.Multipart;

@Service 
public class FileServiceimpl implements FileService {
@Autowired
UserRepository userrep;
@Autowired
FileRepository filerep;
	@Override
	public File addimage(MultipartFile f,Long id) throws IOException {
		Employee emp=userrep.findById(id).get();
		File fl=new File(f.getOriginalFilename(),f.getContentType(),compressBytes(f.getContentType().getBytes()));
		fl.setNomfichier("image");
		String path="//home//moj26//aa//upppp//dist//projetangular//assets";
		Path pt=Paths.get(path+"/"+f.getOriginalFilename());
		Files.copy(f.getInputStream(), pt,StandardCopyOption.REPLACE_EXISTING);
		fl.setEmp(emp);
		return filerep.save(fl);
	}
	@Override
	public File addCV(MultipartFile f, Long id) throws IOException {
		Employee emp=userrep.findById(id).get();
		File fl=new File(f.getOriginalFilename(),f.getContentType(),compressBytes(f.getContentType().getBytes()));
		fl.setNomfichier("cv");
		String path="//home//moj26//aa//upppp//dist//projetangular//assets";
		Path pt=Paths.get(path+"/"+f.getOriginalFilename());
		Files.copy(f.getInputStream(), pt,StandardCopyOption.REPLACE_EXISTING);
		fl.setEmp(emp);
		return filerep.save(fl);
	}

	
	   public static byte[] compressBytes(byte[] data) {
	        Deflater deflater = new Deflater();
	        deflater.setInput(data);
	        deflater.finish();
	        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
	        byte[] buffer = new byte[1024];
	        while (!deflater.finished()) {
	            int count = deflater.deflate(buffer);
	            outputStream.write(buffer, 0, count);
	        }
	        try {
	            outputStream.close();
	        } catch (IOException e) {
	        }
	        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
	        return outputStream.toByteArray();
	    }
	    public static byte[] decompressBytes(byte[] data) {
	    	Inflater inflater = new Inflater();
	    	inflater.setInput(data);
	    	ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
	    	byte[] buffer = new byte[1024];
	    	try {
	    	while (!inflater.finished()) {
	    	int count = inflater.inflate(buffer);
	    	outputStream.write(buffer, 0, count);
	    	}
	    	outputStream.close();
	    	} catch (IOException ioe) {
	    	} catch (DataFormatException e) {
	    	}
	    	return outputStream.toByteArray();
	    	}
		@Override
		public File updatefile(MultipartFile file,Long id) throws IOException {
			File fl= filerep.findById(id).get();
    		File f=new File(file.getOriginalFilename(),file.getContentType(),compressBytes(file.getContentType().getBytes()));;
        	String path="//home//moj26//aa//upppp//dist//projetangular//assets";
    		Path pt=Paths.get(path+"/"+file.getOriginalFilename());
    		Files.copy(file.getInputStream(), pt,StandardCopyOption.REPLACE_EXISTING);
    		fl.setTaillefile(f.getTaillefile());
        	fl.setTitlefile(f.getTitlefile());
        	fl.setTypefile(f.getTypefile());
        	return filerep.save(fl);
		}
		

	

}
