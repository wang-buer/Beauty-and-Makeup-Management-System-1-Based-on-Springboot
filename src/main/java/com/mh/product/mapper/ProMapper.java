package com.mh.product.mapper;

import com.mh.product.entity.CusPayL;
import com.mh.product.entity.IntoGoods;
import com.mh.product.entity.ProType;
import com.mh.product.entity.Produce;
import com.mh.util.PageInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProMapper {
    /*---------------------------------产品类型-----------------------------------------*/
   void truncate();
    //查看产品类型列表
    List<ProType> getProList(@Param("typeId") Integer typeId, PageInfo pf);
    List<ProType>  getProList1();
    int getPtCount();
    //根据id查询产品类型
    ProType getProTypeById(@Param("id")Integer id);
    //修改产品类型
    int updataById(ProType p);
    //删除产品类型
    int delById(@Param("id") Integer id);
    int addProType(ProType p);
    int vTypeName(@Param("typeName")String TypeName);
    /*---------------------------------产品类型-----------------------------------------*/
    /*---------------------------------产    品-----------------------------------------*/
    //带条件查询 带分页
    List<Produce> getProduceList(@Param("name")String name,PageInfo pf);
    //查询总条数
    int getProduceCount(@Param("name")String name);
    /*验证重名问题（添加产品的时候）*/
    int verfiyProduceName(@Param("name")String name);
    int addproduce(Produce p);
    Produce getOneProduceById(@Param("pid")Integer pid);
    int updateProduceById(Produce produce);
    int delproduceById(@Param("pid")Integer pid);
    Double getMonthProduce(@Param("empid")Integer pid,@Param("year") String year,@Param("month")String month,@Param("prevMonth")String prevMonth);
    Double getMonthPackage(@Param("empid")Integer pid,@Param("year") String year,@Param("month")String month,@Param("prevMonth")String prevMonth);
    Double getMonthVip(@Param("empid")Integer pid,@Param("year") String year,@Param("month")String month,@Param("prevMonth")String prevMonth);
    List<CusPayL>  getInfo(@Param("empid")Integer empid,@Param("year") String year,@Param("month")String month,@Param("prevMonth")String prevMonth);//销售记录所有员工所有项目
    List<CusPayL> getProReport(@Param("year") String year,@Param("month")String month,@Param("prevMonth")String prevMonth,@Param("empid")Integer empid);//销售记录本人产品

    /*根据产品id修改产品数量：进货*/
    int updatepronumber(@Param("id")Integer id,@Param("num")Integer num);
    /*添加进货记录*/
     int addIntoBook(IntoGoods in);
    /*查看进货记录*/
    List<IntoGoods> getProIntoBook(@Param("pid")Integer pid);
    List<Produce> getproduceList_option();
    @Select("SELECT COUNT(*) FROM produce WHERE name=#{name}")
    int regRepetition(String name);

    @Select("SELECT COUNT(*) FROM produce WHERE id=#{id} AND name=#{name}")
    int regRepetitionById(Integer id,String name);
    /*---------------------------------产    品-----------------------------------------*/

}
