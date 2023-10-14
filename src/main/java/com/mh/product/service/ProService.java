package com.mh.product.service;

import com.mh.product.entity.CusPayL;
import com.mh.product.entity.IntoGoods;
import com.mh.product.entity.ProType;
import com.mh.product.entity.Produce;
import com.mh.util.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProService {
    void truncate();//清空进货记录
    List<ProType> getProList(Integer typeId, PageInfo pf);
    int getPtCount();
    ProType getProTypeById(@Param("id")Integer id);
    int updataById(ProType pt);
    int delById(@Param("id") Integer id);
    int addProType(ProType p);
    int vTypeName(@Param("typeName")String TypeName);



    /*产品*/
    List<Produce> getProduceList(@Param("name")String name,PageInfo pf);
    //查询总条数
    int getProduceCount(String name);
    int verfiyProduceName(@Param("name")String name);
    List<ProType>  getProList1();
    int addproduce(Produce p);
    Produce getOneProduceById(@Param("pid")Integer pid);
    int updateProduceById(Produce produce);
    int delproduceById(@Param("pid")Integer pid);
    List<CusPayL>  getInfo(@Param("empid")Integer empid,@Param("year") String year,@Param("month")String month,@Param("prevMonth")String prevMonth);//销售记录本人
    List<CusPayL> getProReport(@Param("year") String year,@Param("month")String month,@Param("prevMonth")String prevMonth,@Param("empid")Integer empid);
    /*根据产品id修改产品数量：进货*/
    int updatepronumber(@Param("id")Integer id,@Param("num")Integer num);
    /*添加进货记录*/
    int addIntoBook(IntoGoods in);
    /*查看进货记录*/
    List<IntoGoods> getProIntoBook(@Param("pid")Integer pid);
    List<Produce> getproduceList_option();

    Double getMonthProduce(@Param("pid")Integer pid,@Param("year") String year,@Param("month")String month,@Param("prevMonth")String prevMonth);
    Double getMonthPackage(@Param("pid")Integer pid,@Param("year") String year,@Param("month")String month,@Param("prevMonth")String prevMonth);
    Double getMonthVip(@Param("pid")Integer pid,@Param("year") String year,@Param("month")String month,@Param("prevMonth")String prevMonth);
}
