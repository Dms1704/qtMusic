package com.xupt.service;

import com.xupt.domain.Mv;
import com.xupt.domain.MvList;

import java.util.List;


public interface IMvService {
    public Mv SelectMvById(int sid);
    public List<MvList> SelectMvNumber();
    public int MvPlayNumber(int mid);
}
