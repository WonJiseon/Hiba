package example.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import example.dao.AlbumDao;
import example.dao.GroupMemberDao;
import example.dao.MyScheduleDao;
import example.service.AlbumService;
import example.util.FileUploadUtil;
import example.vo.Album;
import example.vo.MySchedule;
import example.vo.OrignAlbum;

@Service
public class DefaultAlbumService implements AlbumService {
	@Autowired AlbumDao albumDao;
	@Autowired MyScheduleDao myScheduleDao;
	@Autowired GroupMemberDao groupMemberDao;
	@Autowired ServletContext sc;

	public List<OrignAlbum> getOrginAlbumList() throws Exception {
		return albumDao.selectListAl();
	}

	@Override
	public void insertAlbum(Album album, MultipartFile[] file1, String uploadDir) throws Exception {
		albumDao.insert(album);
		System.out.println(album);

		String newFilename = null;			
		System.out.println("---서비스 file1 :" + file1);
		for (int i = 0; i < file1.length; i++) {
			if (file1[i] != null && !file1[i].isEmpty()) {
				newFilename = FileUploadUtil.getNewFilename(file1[i].getOriginalFilename());
				file1[i].transferTo(new File(uploadDir + newFilename));
				Album album1 = new Album();
				album1.setFilename(newFilename);
				album1.setNo(album1.getNo());
				album1.setMemberNo(album1.getGroupNo());
				System.out.println("디폴트앨범insert서비스");
				albumDao.insert(album1);
			}
		}

	}

	@Override
	public int getTotalPage(int pageSize) throws Exception {
		int countAll = albumDao.countAll();
		int totalPage = countAll / pageSize;
		if ((countAll % pageSize) > 0) {
			totalPage++;
		}
		return totalPage;
	}

	public List<Album> getAlbum(int no) throws Exception {
		return albumDao.selectList(no);
	}

	public List<MySchedule> getMyScheduleDao(int no) throws Exception {
		HashMap<String,Object> paramMap = new HashMap<>();
		paramMap.put("no", no);
		return myScheduleDao.selectList(paramMap);
	}
	public MySchedule getScheduleByScheduleNo(int groupscNo) throws Exception {
		return myScheduleDao.selectOneByScheduleNo(groupscNo);
	}
	public void updateAlbum(Album album) throws Exception {
		HashMap<String,Object> paramMap = new HashMap<>();
		paramMap.put("gno", album.getGroupNo());
		paramMap.put("gsno", album.getGroupScheduleNo());
		paramMap.put("AL_PHOT", album.getFilename());

		System.out.println("디폴트업데이트앨범서비스");
		if (albumDao.selectList() == null) {
			throw new Exception("해당 게시물이 없거나 암호가 일치하지 않습니다!");
		}
		albumDao.update(album);
	}



	@Override
	public void deleteAlbum(int no) throws Exception {
		albumDao.delete(no);
	}

	@Override
	public List<Album> getAlbumList(int pageNo, int length) throws Exception {
		System.out.println("디폴트앨범서비스리스트");
		return albumDao.selectList();
	}






}