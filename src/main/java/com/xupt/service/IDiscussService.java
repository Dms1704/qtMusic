package com.xupt.service;

import com.xupt.domain.Discuss;
import com.xupt.domain.PageBean;

import java.util.List;


public interface IDiscussService {
    /**
     * 查所有
     * @return
     */
    List<Discuss> findAllBySid(Integer sid);

    /**
     * 写评论
     * @param discuss
     */
    int saveDiscuss(Discuss discuss);

    /**
     *
     * @param did
     * @return
     */
    Discuss findByDid(Integer did);

    /**
     *
     * @param uid
     * @return
     */
    Discuss findByUid(Integer uid);

    /**
     *
     * @param did
     * @return
     */
    int deleteDiscussByDid(Integer did);

    /**
     *
     * @param uid
     * @return
     */
    int deleteDiscussByUid(Integer uid);

    /**
     *
     * @param sid
     * @return
     */
    int deleteDiscussBySid(Integer sid);


    int findUidBysid(Integer sid);


    /**
     * 分页找评论
     * @param sid
     * @return
     */
    PageBean<Discuss> pageDiscuss(Integer sid, Integer currentPage);
}
