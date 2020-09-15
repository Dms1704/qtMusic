package com.xupt.service.impl;

import com.xupt.dao.IMvDao;
import com.xupt.domain.Mv;
import com.xupt.domain.MvList;
import com.xupt.service.IMvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service("mvService")
public class MvService implements IMvService {
    @Autowired
    private IMvDao mvDao;
    @Override
    public Mv SelectMvById(int sid) {
        return mvDao.SelectMvById(sid);
    }

    @Override
    public List<MvList> SelectMvNumber() {
        List<MvList> list = mvDao.SelectMvNumber();
        list.sort(new Comparator<MvList>() {
            @Override
            public int compare(MvList o1, MvList o2) {
                int num=o2.getMv_num()-o1.getMv_num();
                int num1=o1.getSongname().compareTo(o2.getSongname());
                if(num1==0){
                    return num1;
                }
                return num;
            }
        });
        return list;
    }

    @Override
    public int MvPlayNumber(int mid) {
        return mvDao.MvPlayNumber(mid);
    }
}
