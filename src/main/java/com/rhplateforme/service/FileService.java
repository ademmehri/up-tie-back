package com.rhplateforme.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.rhplateforme.entities.File;

public interface FileService {
File addimage(MultipartFile f,Long id) throws IOException ;
File addCV(MultipartFile f,Long id) throws IOException ;
File updatefile(MultipartFile f,Long id) throws IOException ;
}
