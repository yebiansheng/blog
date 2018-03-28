package org.lanqiao.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.lanqiao.entity.Recommend;
import org.lanqiao.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sf.json.JSONObject;

@Controller
public class RecommendController {

	@Autowired
	RecommendService recommendService;

	@RequestMapping("updateRcommend")
	@ResponseBody
	public void updateRcommend(HttpServletResponse response, HttpServletRequest request, HttpSession session,
			String articleid, String panduan) {
		try {
			JSONObject jsonObject = new JSONObject();
			if (session.getAttribute("userID") == null) {
				jsonObject.put("stutas", 0);// ÓÃ»§Î´µÇÂ¼
				response.getWriter().println(jsonObject);
			} else {
				int articleID = Integer.parseInt(articleid);
				int userid = (int) session.getAttribute("userID");
				if (panduan.equals("1")) {
					recommendService.getDislikeArticleByUser(articleID, userid);
					if (recommendService.getDislikeArticleByUser(articleID, userid).size() != 0) {
						jsonObject.put("stutas", 1);
						recommendService.deleteByPrimaryKey(recommendService.getDislikeArticleByUser(articleID, userid).get(0).getId());
						recommendService.insertSelective(articleID, userid, 1);
					} else if (recommendService.getLikeArticleByUser(articleID, userid).size() == 0) {
						recommendService.insertSelective(articleID, userid, 1);
					} else {
						int rid=recommendService.getLikeArticleByUser(articleID, userid).get(0).getId();
						recommendService.deleteByPrimaryKey(rid);
					}
				} else {
					if (recommendService.getLikeArticleByUser(articleID, userid).size() != 0) {
						jsonObject.put("stutas", 1);//
						int rid=recommendService.getLikeArticleByUser(articleID, userid).get(0).getId();
						recommendService.deleteByPrimaryKey(rid);
						recommendService.insertSelective(articleID, userid, 0);
					} else if (recommendService.getDislikeArticleByUser(articleID, userid).size() == 0) {
						recommendService.insertSelective(articleID, userid, 0);
					} else {
						recommendService.deleteByPrimaryKey(recommendService.getDislikeArticleByUser(articleID, userid).get(0).getId());
					}
				}
				int dislikeNum = 0;
				int likeNum = 0;
				List<Recommend> dislikeList = null;
				dislikeList = recommendService.getDislikeArticle(articleID);
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
				likeList = recommendService.getLikeArticle(articleID);
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
				jsonObject.put("id", articleid);
				jsonObject.put("likeNum", likeNum);
				jsonObject.put("dislikeNum", dislikeNum);
			}

			response.getWriter().println(jsonObject);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
