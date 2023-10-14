package com.mh.product.service.impl;

import com.mh.product.entity.CusPayL;
import com.mh.product.entity.IntoGoods;
import com.mh.product.entity.ProType;
import com.mh.product.entity.Produce;
import com.mh.product.mapper.ProMapper;
import com.mh.product.service.ProService;
import com.mh.util.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProServiceImpl implements ProService {
    @Autowired
    ProMapper pm;

    @Override
    public void truncate() {
        pm.truncate();
    }

    @Override
    public List<ProType> getProList(Integer typeId, PageInfo pf) {
        return pm.getProList(typeId, pf);
    }

    @Override
    public int getPtCount() {
        return pm.getPtCount();
    }

    @Override
    public ProType getProTypeById(Integer id) {
        return pm.getProTypeById(id);
    }

    @Override
    public int updataById(ProType p) {
        return pm.updataById(p);
    }

    @Override
    public int delById(Integer id) {
        return pm.delById(id);
    }

    @Override
    public int addProType(ProType p) {
        return pm.addProType(p);
    }

    @Override
    public int vTypeName(String TypeName) {
        return pm.vTypeName(TypeName);
    }

    @Override
    public List<Produce> getProduceList(String name, PageInfo pf) {
        return pm.getProduceList(name, pf);
    }

    @Override
    public int getProduceCount(String name) {
        return pm.getProduceCount(name);
    }

    @Override
    public int verfiyProduceName(String name) {
        return pm.verfiyProduceName(name);
    }

    @Override
    public List<ProType> getProList1() {
        return pm.getProList1();
    }

    @Override
    public int addproduce(Produce p) {
        return pm.addproduce(p);
    }

    @Override
    public Produce getOneProduceById(Integer pid) {
        return pm.getOneProduceById(pid);
    }

    @Override
    public int updateProduceById(Produce produce) {
        //根据名称验重
        if (pm.regRepetitionById(produce.getId(),produce.getName())>=1){
            int i = pm.updateProduceById(produce);
            return 1;
        }else if (pm.regRepetition(produce.getName())<1){
            int i = pm.updateProduceById(produce);
            return 1;
        }else {
            return -1;
        }
    }

    @Override
    public int delproduceById(Integer pid) {
        return pm.delproduceById(pid);
    }

    @Override
    public List<CusPayL> getInfo(Integer empid, String year, String month, String prevMonth) {
        return pm.getInfo(empid,year,month,prevMonth);
    }


    @Override
    public  List<CusPayL> getProReport(@Param("year") String year, @Param("month")String month, @Param("prevMonth")String prevMonth,Integer empid) {
        return pm.getProReport(year,month,prevMonth,empid);
    }




    @Override
    public int updatepronumber(Integer id, Integer num) {
        return pm.updatepronumber(id, num);
    }

    @Override
    public int addIntoBook(IntoGoods in) {
        return pm.addIntoBook(in);
    }

    @Override
    public List<IntoGoods> getProIntoBook(Integer pid) {
        return pm.getProIntoBook(pid);
    }

    @Override
    public List<Produce> getproduceList_option() {
        return pm.getproduceList_option();
    }

    @Override
    public Double getMonthProduce(Integer pid, String year, String month, String prevMonth) {
        return pm.getMonthProduce(pid,year,month,prevMonth);
    }

    @Override
    public Double getMonthPackage(Integer pid, String year, String month, String prevMonth) {
        return pm.getMonthPackage(pid,year,month,prevMonth);
    }

    @Override
    public Double getMonthVip(Integer pid, String year, String month, String prevMonth) {
        return pm.getMonthVip(pid,year,month,prevMonth);
    }
}


