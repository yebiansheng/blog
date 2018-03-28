package org.lanqiao.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.lanqiao.dao.MessageMapper;
import org.lanqiao.entity.Article;
import org.lanqiao.entity.Message;
import org.lanqiao.entity.Recommend;
import org.lanqiao.entity.User;
import org.lanqiao.service.ArticleService;
import org.lanqiao.service.ImageService;
import org.lanqiao.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sf.json.JSONObject;
import sun.misc.BASE64Decoder;

@Controller
public class ArticleController {
	@Autowired
	ArticleService articleService;
	@Autowired
	ImageService imageService;
	@Autowired
	RecommendService recommendService;
	@Autowired
	MessageMapper dao;

	@RequestMapping("initIndex")
	@ResponseBody
	public void initIndex(HttpServletResponse response, String page, String title) {
		try {
			// String reg = "[^\u4e00-\u9fa5]";
			List<Article> artList = null;
			int num = 0;
			JSONObject jsonObject = new JSONObject();
			if (title != null && !"".equals(title)) {
				artList = articleService.searchSimpleArticleByPage(title, Integer.parseInt(page), 6);
				jsonObject.put("title", title);
				num = articleService.searchSimpleArticleNum(title);
			} else {
				artList = articleService.getSimpleArticleByPage(Integer.parseInt(page), 6);
				jsonObject.put("title", "");
				num = articleService.getArticleNum();
			}
			/*
			 * for (Article article : artList) {
			 * article.setContent(article.getContent().replaceAll(reg,"").
			 * substring(0,100)); }
			 */
			jsonObject.put("artList", artList);
			jsonObject.put("topTen", articleService.getTopTenArticle());
			List<Recommend> zanTopTen=recommendService.getZanTopTen();
			jsonObject.put("zanTopTen", zanTopTen);
			jsonObject.put("page", Integer.parseInt(page));
			jsonObject.put("pageEnd", num % 6 != 0 ? (num / 6 + 1) : (num / 6));
			jsonObject.put("artNum", num);
			// response.setCharacterEncoding("utf-8");
			response.getWriter().print(jsonObject);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping("articleInit")
	@ResponseBody
	public void articleInit(HttpServletResponse response, HttpServletRequest request, HttpSession session, String id) {
		try {
			articleService.updatePageViewAdd(Integer.parseInt(id));
			JSONObject jsonObject = new JSONObject();
			// int id=Integer.parseInt(request.getParameter("id"));
			int ID = Integer.parseInt(id);
			Article list = articleService.getArticleAndMsgById(ID);
			if (list == null || list.equals("")) {
				list = articleService.getArticleById(ID);
			}
			// Map<String,Object> map=new HashMap<String,Object>();
			jsonObject.put("list", list);
			jsonObject.put("topTen", articleService.getTopTenArticle());
			int msgNum = dao.getMsgNumByArtId(ID);
			jsonObject.put("msgNum", msgNum);
			jsonObject.put("pageEnd", msgNum % 10 != 0 ? (msgNum / 10 + 1) : (msgNum / 10));
			int dislikeNum = 0;
			int likeNum = 0;
			List<Recommend> dislikeList = null;
			dislikeList = recommendService.getDislikeArticle(ID);
			if (dislikeList != null) {
				dislikeNum = dislikeList.size();
				if (session.getAttribute("userID") != null) {
					int userId = (int) session.getAttribute("userID");
					for (Recommend recommend : dislikeList) {
						if (recommend.getUser_id() == userId) {
							jsonObject.put("haveLike", 0);
						}
					}
				}
			}
			List<Recommend> likeList = null;
			likeList = recommendService.getLikeArticle(ID);
			if (likeList != null) {
				likeNum = likeList.size();
				if (session.getAttribute("userID") != null) {
					int userId = (int) session.getAttribute("userID");
					for (Recommend recommend : likeList) {
						if (recommend.getUser_id() == userId) {
							jsonObject.put("haveLike", 1);
						}
					}

				}
			}
			jsonObject.put("likeNum", likeNum);
			jsonObject.put("dislikeNum", dislikeNum);
			response.setCharacterEncoding("utf-8");
			response.getWriter().print(jsonObject);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * JSONObject jsonObject = new JSONObject(); Article
		 * list=Dao.getAllArticle().get(0); //Map<String,Object> map=new
		 * HashMap<String,Object>(); jsonObject.put("list", list); try {
		 * response.setCharacterEncoding("utf-8");
		 * response.getWriter().print(jsonObject); } catch (IOException e) { //
		 * TODO Auto-generated catch block e.printStackTrace(); }
		 */
	}

	@RequestMapping("initNewlistpic")
	@ResponseBody
	public void initNewlistpic(HttpServletResponse response, String page) {
		try {
			List<Article> artList = null;
			int num = 0;
			int pageInt = Integer.parseInt(page);
			JSONObject jsonObject = new JSONObject();
			artList = articleService.getSimpleDiaryByPage(1, pageInt, 6);
			num = articleService.getSimpleDiaryNum(1);
			jsonObject.put("artList", artList);
			// jsonObject.put("topTen", articleService.getTopTenArticle());
			jsonObject.put("page", Integer.parseInt(page));
			jsonObject.put("pageEnd", num % 6 != 0 ? (num / 6 + 1) : (num / 6));
			jsonObject.put("artNum", num);
			response.setCharacterEncoding("utf-8");
			response.getWriter().print(jsonObject);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping("insertArticle")
	@ResponseBody
	public void insertArticle(HttpServletResponse response, Article article, String user_id) {
		try {
			User user = new User();
			user.setId(Integer.parseInt(user_id));
			article.setUser(user);
			articleService.insertSelective(article);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("status", "成功");
			response.setCharacterEncoding("utf-8");
			response.getWriter().print(jsonObject);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping("initMsg")
	@ResponseBody
	public void initMsg(HttpServletResponse response, String page, String artId) {
		try {
			int pageInt = Integer.parseInt(page);
			List<Message> msgList = null;
			JSONObject jsonObject = new JSONObject();
			int num = 0;
			if (artId != null) {
				int artID = Integer.parseInt(artId);
				msgList = dao.getMsgByArtIdPage((pageInt - 1) * 10, 10, artID);
				num = dao.getMsgNumByArtId(artID);
			} else {
				msgList = dao.getNoArtMsgByPage((pageInt - 1) * 10, 10);
				num = dao.getNoArtMsgNum();
			}
			jsonObject.put("msgList", msgList);
			jsonObject.put("page", pageInt);
			jsonObject.put("pageEnd", num % 10 != 0 ? (num / 10 + 1) : (num / 10));
			jsonObject.put("msgNum", num);
			response.setCharacterEncoding("utf-8");
			response.getWriter().print(jsonObject);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping("intertMsg")
	@ResponseBody
	public void intertMsg(HttpServletResponse response, Message msg, String userid) {
		try {
			Date date = new Date();
			User user = new User();
			user.setId(Integer.parseInt(userid));
			msg.setUser(user);
			;
			msg.setDate(date);
			int i = dao.insertMsg(msg);

			JSONObject jsonObject = new JSONObject();
			jsonObject.put("statu", i);
			response.setCharacterEncoding("utf-8");
			response.getWriter().print(jsonObject);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping("upload")
	@ResponseBody
	public void upload(HttpServletResponse response, String img) {
		System.out.println(img);
		String[] pics = img.split(",");// 0为类型 1为数据
		String type = null;
		if (pics[0].indexOf("image/jpeg") > 0) {
			type = ".jpg";
		} else if (pics[0].indexOf("image/gif") > 0) {
			type = ".gif";
		} else if (pics[0].indexOf("image/png") > 0) {
			type = ".png";
		} else {

		}

		String imgname = GenerateImage(pics[1], type,
				"D:/workspace/eclipseworkspace/TJSD2018/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/blog/images/test/");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("imgUrl", "images/test/" + imgname);
		response.setCharacterEncoding("utf-8");
		try {
			response.getWriter().print(jsonObject);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// GenerateImage(pics[1],
		// type,"D:/apache-tomcat-7.0.73/webapps/imagetemplate/");
	}

	public static String GenerateImage(String imgStr, String imgType, String filePath) { // 对字节数组字符串进行Base64解码并生成图片
		if (imgStr == null) // 图像数据为空
			return "";
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64解码
			byte[] b = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			// 根据时间生成图片名称
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmsssss");
			String imgFilePath = filePath + simpleDateFormat.format(new Date()) + imgType;
			// 新生成的图片
			OutputStream out = new FileOutputStream(imgFilePath);
			out.write(b);
			out.flush();
			out.close();
			return simpleDateFormat.format(new Date()) + imgType;
		} catch (Exception e) {
			return "";
		}
	}

	@RequestMapping("initImg")
	@ResponseBody
	public void initImg(HttpServletResponse response, String page) {
		try {
			int pageInt = Integer.parseInt(page);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("page", pageInt);
			jsonObject.put("images", imageService.getImageByPage(pageInt, 24));
			int imgNum = imageService.getImageNum();
			jsonObject.put("imgNum", imgNum);
			jsonObject.put("pageEnd", imgNum % 24 == 0 ? imgNum / 24 : imgNum / 24 + 1);
			jsonObject.put("page", pageInt);
			response.setCharacterEncoding("utf-8");
			response.getWriter().print(jsonObject);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
