package meiHu.control;


import meiHu.entity.ForumPost;
import meiHu.entity.ForumTopic;
import meiHu.service.LuntanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
@RequestMapping(value = "/luntan")
public class luntanControl {
    @Autowired
    private LuntanService luntanService;

    @RequestMapping(value = "/luntanshouye.action")
    public void luntanshouye(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String tid = request.getParameter("tid");
        int tid1 = Integer.parseInt(tid);
        List<ForumTopic> topicList=luntanService.getAllTopics();
        List<ForumPost> postList=luntanService.selectPostsByTid(tid1);
        request.setAttribute("topicList",topicList);
        request.setAttribute("postList",postList);
        request.setAttribute("tid1",tid1);
        request.getSession().setAttribute("uid",102);
        request.getRequestDispatcher("/jsp/luntan.jsp").forward(request,response);


    }


    @RequestMapping(value = "/tiezidetail.action",method = RequestMethod.GET)
    public void tiezidetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pid = request.getParameter("pid");
        //System.out.println(pid);
        int pid1 = Integer.parseInt(pid);
        ForumPost forumPost = luntanService.selectPostByPid(pid1);
        request.setAttribute("forumPost",forumPost);
       // System.out.println(forumPost.getPtitle());

        request.getRequestDispatcher("/jsp/tiezidetail.jsp").forward(request,response);
    }




   @RequestMapping("/tiaojian.action")
   public void tiaojian(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String tiaojian = request.getParameter("tiaojian");
       System.out.println(tiaojian);
       String tid = request.getParameter("tid");
       System.out.println(tid);
       int tid1 = Integer.parseInt(tid);
        if("tuijian".equals(tiaojian)){
            List<ForumTopic> topicList= luntanService.getAllTopics();
            List<ForumPost> postList= luntanService.selectAllPostsOrderByVisits(tid1);
           request.setAttribute("topicList",topicList);
           request.setAttribute("postList",postList);
            request.setAttribute("tid1",tid1);
           request.getRequestDispatcher("/jsp/luntan.jsp").forward(request,response);
       }else if("zuixin".equals(tiaojian)){

            List<ForumTopic> topicList= luntanService.getAllTopics();
            List<ForumPost> postList= luntanService.selectAllPostsOrderByCreatetime(tid1);
           request.setAttribute("topicList",topicList);
           request.setAttribute("postList",postList);
            request.setAttribute("tid1",tid1);
           request.getRequestDispatcher("/jsp/luntan.jsp").forward(request,response);
       }
   }

   @RequestMapping("/shoucang.action")
   public  void shoucang(HttpServletRequest request,HttpServletResponse response) throws IOException {
       String uid =request.getParameter("uid");
       String pid =request.getParameter("pid");

       int uidd = Integer.parseInt(uid);
       int pidd = Integer.parseInt(pid);
       //System.out.println(luntanService.addCollectionByUidAndPid(uidd,pidd));
       PrintWriter out = response.getWriter();
       if(luntanService.addCollectionByUidAndPid(uidd,pidd)){
           out.print(1);
       }else {
           out.print(0);

       }
   }
    @RequestMapping("/quxiaoshoucang.action")
    public  void quxiaoshoucang(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String uid =request.getParameter("uid");
        String pid =request.getParameter("pid");

        int uidd = Integer.parseInt(uid);
        int pidd = Integer.parseInt(pid);

        PrintWriter out = response.getWriter();
        if(luntanService.deleteCollectionByUidAndPid(uidd,pidd)){
            out.print(1);
        }else {
            out.print(0);

        }
    }

   @RequestMapping("/dianzan.action")
   public  void dianzan(HttpServletRequest request,HttpServletResponse response) throws IOException {
       String uid =request.getParameter("uid");
       String pid =request.getParameter("pid");
       System.out.println("dianzan"+uid+"---"+pid);
       int uidd = Integer.parseInt(uid);
       int pidd = Integer.parseInt(pid);
        //return luntanService.addLikeByUidAndPid(uidd,pidd);
       PrintWriter out = response.getWriter();
       if(luntanService.addLikeByUidAndPid(uidd,pidd)){
           out.print(1);
       }else{
           out.print(0);

       }
   }
    @RequestMapping("/quxiaodianzan.action")
    public  void quxiaodianzan(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String uid =request.getParameter("uid");
        String pid =request.getParameter("pid");

        int uidd = Integer.parseInt(uid);
        int pidd = Integer.parseInt(pid);
        PrintWriter out = response.getWriter();
        if(luntanService.deleteLikeByUidAndPid(uidd,pidd)){
            out.print(1);
        }else{
            out.print(0);

        }
    }


}
