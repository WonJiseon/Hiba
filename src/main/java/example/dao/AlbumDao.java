package example.dao;

import java.util.List;
import java.util.Map;

import example.vo.Album;
import example.vo.OrignAlbum;

public interface AlbumDao {
	Album selectOneByMemberNo(Map<String, Object> paramMap);
	Album selectOneByGroupNoScheduleNo(Map<String, Object> paramMap);

	int insert(Album album) throws Exception;
	void update(Album album) throws Exception;
	void delete(int no) throws Exception;
	void detail(int gno) throws Exception;
	int countAll() throws Exception;
	
	List<OrignAlbum> selectListAl() throws Exception;
	List<Album> selectList() throws Exception;
	List<Album> selectList(int no) throws Exception;

}
