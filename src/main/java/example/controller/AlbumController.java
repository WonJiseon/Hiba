package example.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import example.dao.AlbumDao;
import example.vo.Album;
    
@Controller
@RequestMapping("/album/")
public class AlbumController {
	  @Autowired AlbumDao albumDao;
		
		
		@RequestMapping(path="list")
		public String list(
				@RequestParam(defaultValue="1")int pageNo,
				@RequestParam(defaultValue="5")int length,
				Model model) throws Exception {
			List<Album> list = albumDao.selectList();
			model.addAttribute("list", list);			
			
			return "album/AlbumList";	
		}
		
		@RequestMapping("add")
		public String add(Album album) throws Exception {
			albumDao.insert(album);		
			return "redirect:list.do";
		}
		
		
		@RequestMapping("detail")
		public String detail(int no, Model model) throws Exception{
			List<Album> album = albumDao.selectList();
			model.addAttribute("album", album);
			return "album/AlbumDetail";
		}
		
		@RequestMapping("update")
		public String update(Album album) throws Exception{
			HashMap<String,Object> paramMap = new HashMap<>();
			paramMap.put("no", album.getNo());

			if (albumDao.selectOneByGroupNoScheduleNo(paramMap) == null) {
				throw new Exception("해당 게시물이 없거나 암호가 일치하지 않습니다.!");
			}
			albumDao.update(album);
			return "redirect:list.do";
		}
		
		@RequestMapping("delete")
		public String delete(int no) throws Exception {
			albumDao.delete(no);    	
			return "redirect:list.do";
		}
	}
