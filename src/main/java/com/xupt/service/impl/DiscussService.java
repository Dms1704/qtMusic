package com.xupt.service.impl;

import com.xupt.dao.IDiscussDao;
import com.xupt.domain.Discuss;
import com.xupt.domain.PageBean;
import com.xupt.service.IDiscussService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("discussService")

public class DiscussService implements IDiscussService {

    @Autowired
    private IDiscussDao discussDao1;


    @Override
    public List<Discuss> findAllBySid(Integer sid) {
        List<Discuss> discussList = discussDao1.findDiscussBySid(sid);
        return discussList;
    }

    //存评论
    @Override
    public int saveDiscuss(Discuss discuss) {
       discussDao1.saveDiscuss(discuss);
        return 1;
    }

    //根据did找评论
    @Override
    public Discuss findByDid(Integer did) {
       return discussDao1.findDiscussByDid(did);
    }

    //根据uid找评论
    @Override
    public Discuss findByUid(Integer uid) {
        return discussDao1.findDiscussByDid(uid);
    }

    //根据did删评论，一次一条
    @Override
    public int deleteDiscussByDid(Integer did) {
        discussDao1.deleteDiscussByDid(did);
        return 1;
    }

    //根据uid删评论，一次删完（注销账户的时候使用）
    @Override
    public int deleteDiscussByUid(Integer uid) {
        discussDao1.deleteDiscussByUid(uid);
        return 1;
    }

    @Override
    public int deleteDiscussBySid(Integer sid) {
        discussDao1.deleteDiscussBySid(sid);
        return 1;
    }

    @Override
    public int findUidBysid(Integer sid) {
        discussDao1.findUidBysid(sid);
        return 1;
    }

    @Override
    public PageBean<Discuss> pageDiscuss(Integer sid, Integer currentPage) {
        if (currentPage <= 0){
            currentPage = 1;
        }
        int count = 9;
        PageBean<Discuss> discussPageBean = new PageBean<>();
        discussPageBean.setCurrentCount(count);

        //总条数
        Integer discussCount = discussDao1.findDiscussCount(sid);
        discussPageBean.setTotalCount(discussCount);

        //总页数

        Integer titlePages = (discussCount % count == 0) ? (discussCount / count): (discussCount / count + 1);
        if(discussCount==0){
            titlePages=1;
        }
        if (titlePages < currentPage){
            //如果当前页大于最后一页
            currentPage = titlePages;
        }
        discussPageBean.setTotalPage(titlePages);
        Integer start = (currentPage-1) * count;
        List<Discuss> discussS = discussDao1.findLimit(sid,start ,count);
        for (Discuss discuss1 :discussS) {
            //System.out.println("评论："+discuss1.toString());
        }
        discussPageBean.setList(discussS);
        discussPageBean.setCurrentPage(currentPage);
        return discussPageBean;

    }


}
