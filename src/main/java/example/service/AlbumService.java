package example.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import example.vo.Album;
import example.vo.OrignAlbum;

public interface AlbumService {
		List<OrignAlbum> getOrginAlbumList() throws Exception;
	  List<Album> getAlbumList(int pageNo, int length) throws Exception;
	  
	  void insertAlbum(Album album, MultipartFile[] file1, String uploadDir) throws Exception;
	  List<Album> getAlbum(int no) throws Exception;
	  int getTotalPage(int pageSize) throws Exception;
	  void updateAlbum(Album album) throws Exception;
	  void deleteAlbum(int no) throws Exception;
}
