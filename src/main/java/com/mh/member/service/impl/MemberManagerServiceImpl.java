package com.mh.member.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mh.member.entity.*;
import com.mh.member.mapper.*;
import com.mh.member.service.MemberMangerService;
import com.mh.model.LayuiTableResult;
import com.mh.model.PageBean;
import com.mh.model.Result;
import com.mh.sys.entity.Emp;
import com.mh.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @Author MH
 * @Date 2020/1/16 19:24
 */
@Service
public class MemberManagerServiceImpl extends ServiceImpl<MemberManagerMapper, Customer>
        implements MemberMangerService {


    @Autowired
    private MemberManagerMapper memberManagerMapper;
    @Autowired
    private VipMapper vipMapper;
    @Autowired
    private ProduceMapper produceMapper;
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private CuspayMapper cuspayMapper;
    @Autowired
    private VpackageMapper vpackageMapper;
    @Autowired
    private CustomerattendanceMapper customerattendanceMapper;
    @Autowired
    private ProTypeMapper proTypeMapper;

    @Override
    public LayuiTableResult pageQuery(Customer customer, PageBean pageBean) {
        if (pageBean != null){
            pageBean.setPage((pageBean.getPage()-1)*pageBean.getLimit());
        }
       List<Customer> customerList= memberManagerMapper.pageQuery(customer,pageBean);
        List<Customer> customerCount = memberManagerMapper.pageQuery(customer, null);
        if (customerCount != null){
            return LayuiTableResult.ok(customerCount.size(),customerList);
        }
            return LayuiTableResult.ok(0,customerList);
    }

    @Override
    public List<Vip> vipTypeList(Integer id) {
        System.out.println("----------vip----------");
        List<Vip> vips = vipMapper.getVipAndPackageList(id);
            for(Vip v:vips){
                Vpackage vpackage = v.getVpackage();
                Integer pid = vpackage.getPid();
                List<Produces> produceList = produceMapper.getProduceList(pid);
                vpackage.setProduces(produceList);
            }
        return vips;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result addCustomer(Customer customer, Emp user) {
        try {
            Cuspay cuspay =new Cuspay();
            if (customer != null) {
                LocalDateTime now = LocalDateTime.now();
                //增加客户数据
                customer.setCreateDate(now);
                int i = customerMapper.addCustomer(customer);
                //取出数据放入消费实体中
                cuspay.setPaydesc(customer.getPaydesc());
                cuspay.setPrice(customer.getPrice());
                cuspay.setVipid(customer.getVipid()); //会员类型
                cuspay.setEmpid(user.getId());
                cuspay.setCusid(customer.getCid());
                cuspay.setPaydate(now); //支付日期
                cuspayMapper.addCuspay(cuspay);
                // 向员工顾客表中增加数据
                customerMapper.addCustomerEmp(customer.getCid(),user.getId());
                // 根据vipid查询出对应的套餐id
                Integer packageid=vipMapper.getVipById(customer.getVipid());
                //向顾客套餐表中增加数据
                vpackageMapper.addCustomerAndPackage(customer.getCid(),packageid);

                // 减去套餐中对应产品的库存
                    //根据套餐id查询对应的产品并修改库存
                List<Produces> produceList = produceMapper.getProduceList(packageid);
                Produces[] produces=null;
                Stream<Produces> stream = produceList.stream();
                stream.forEach(e->e.setNum(1));
                Produces[] produces1 = produceList.stream().toArray(Produces[]::new);
                produceMapper.updateRepertory(produces1);

                //向签到表中增加一条考勤数据
                Customerattendance dance = new Customerattendance(customer.getCid(),LocalDateTime.now(),1,
                        customer.getBodyfat(),customer.getFatmass(),customer.getWeight(),customer.getStature(),
                        customer.getBust(),customer.getHip(),customer.getWaistline(),user.getId(),null);
                customerattendanceMapper.addCustomerattendance(dance);
            }
            return Result.ok("添加成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("添加失败！");
        }
    }

    @Override
    public Result delCustomerInfo(Integer id) {
        try {
            if (id!= null && id>0){
                customerMapper.updateById(id);
            }
            return  Result.ok("删除成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("删除失败！");
        }
    }

    @Override
    public Customer getCustomerById(Integer id , HttpSession session) {
        Customer customer = null;
        try {
            Emp user = SessionUtil.getUser(session);
            List<Cuspay> cuspay= cuspayMapper.getCustomerById(id,user.getId());
            customer = customerMapper.getCustomerById(id);
            if (cuspay != null && cuspay.size()==1){
                Cuspay cuspay1 = cuspay.get(0);
                customer.setPrice(cuspay1.getPrice());
                customer.setPaydesc(cuspay1.getPaydesc());
            }
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime createDate = customer.getCreateDate();
            createDate.format(dateTimeFormatter);
            System.out.println(customer);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            return customer;
        }
    }

    @Override
    public Result operating(Integer id,Integer cid) {
       List<CustomerEmp> customerEmps= customerMapper.operating(id,cid);
       if (customerEmps!=null && customerEmps.size()>0){
           return Result.ok("1");
       }
        return Result.error("-1");
    }

    @Override
    public Result updateCustomer(Customer customer,HttpSession session) {
        try {
            Emp user = SessionUtil.getUser(session);
            customer.setUpdateDate(LocalDateTime.now());
            customerMapper.updateCustomer(customer);

            Cuspay cuspay = new Cuspay();
            cuspay.setCusid(customer.getCid());
            cuspay.setPaydate(customer.getCreateDate());
            cuspay.setEmpid(user.getId());
            cuspay.setPaydesc(customer.getPaydesc());
            cuspay.setPrice(customer.getPrice());
            System.out.println(cuspay);
            System.out.println(customer);
            cuspayMapper.updateCuspay(cuspay);
            return Result.ok("修改成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return  Result.error("修改失败！");
        }
    }

    @Override
    public List<Cuspay> getCustomerInfo(Integer id) {
        return cuspayMapper.getCuspayList(id);
    }

    @Override
    public List<Vpackage> getPackageInfo(Integer id) {

        return vpackageMapper.getPackageAndProduceList(id);
    }

    @Override
    public LayuiTableResult getCustomerattendanceList(Integer id, PageBean pageBean) {
        pageBean.setPage((pageBean.getPage()-1)*pageBean.getLimit());
        List<Customerattendance> customerattendanceList = customerattendanceMapper.getCustomerattendanceList(id, pageBean.getPage(), pageBean.getLimit());
        List<Customerattendance> count = customerattendanceMapper.getCustomerattendanceList(id, null,null);
        Optional<List<Customerattendance>> count1 = Optional.of(count);
        count = count1.orElse(new ArrayList<>());
        return LayuiTableResult.ok(count.size(),customerattendanceList);
    }

    @Override
    public Result addCuspay(Customer customer, HttpSession session) {
        try {
            Integer userId = SessionUtil.getUserId(session);
            Cuspay cuspay = new Cuspay();
            cuspay.setPrice(customer.getPrice());
            cuspay.setPaydesc(customer.getPaydesc());
            cuspay.setPaydate(LocalDateTime.now());
            cuspay.setEmpid(userId);
            cuspay.setCusid(customer.getCid());
            cuspay.setVipid(customer.getVipid());
            //向消费记录表中增加一条记录
            cuspayMapper.addCuspay(cuspay);
            //向签到表中增加一条记录
            Customerattendance dance=new Customerattendance();
            dance.setBodyfat(customer.getBodyfat());
            dance.setBust(customer.getBust());
            dance.setFatmass(customer.getFatmass());
            dance.setHip(customer.getHip());
            dance.setIsin(1);
            dance.setIndate(LocalDateTime.now());
            dance.setStature(customer.getStature());
            dance.setWaistline(customer.getWaistline());
            dance.setWeight(customer.getWeight());
            dance.setEmpid(userId);
            dance.setCusid(customer.getCid());
            customerattendanceMapper.addCustomerattendance(dance);
            return  Result.ok("购买成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("购买失败！");
        }
    }

    @Override
    public List<Vpackage> getPackageList() {
       return vpackageMapper.getPackageList();
    }

    @Override
    public Result addCuspayList(CuspayArry[] array) {
        try {
            //增加消费记录
            cuspayMapper.addCuspays(array);
            //减去库存：根据套餐id查询出包含产品编号
            for (CuspayArry c:array) {
                Produces[] produces = c.getProduces();
                produceMapper.updateRepertory(produces);
            }
            return Result.ok("购买成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("购买失败，请重写购买！");
        }
    }

    @Override
    public List<ProTypes> getProduceList() {
           return proTypeMapper.getProduceList();
    }

    @Override
    public Result addProduce(CuspayArry[] proTypes) {
        try {
            //加入到消费记录
            cuspayMapper.addCuspaysByProduce(proTypes);
            //修改产品库存
            for (CuspayArry proType : proTypes) {
                Produces[] produces = proType.getProduces();
                produceMapper.updateRepertory(produces);
            }
            return Result.ok("购买成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return  Result.error("购买失败！");
        }
    }

    @Override
    public Result sign(Integer uid,Integer cid, Integer isin, String whyNo) {
        try {
            //1. 先查看有没有签到
            List<Integer> i = customerattendanceMapper.selectCheckingInfo(cid);
            if (i.size()>0){
                return Result.ok("亲！今天已经签过到，不能重复签了");
            }
            //2. 如果没有签到就签到
            customerattendanceMapper.addCheckingInfo(uid,cid, isin, whyNo);
            return Result.ok("更改成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("更改失败!");
        }
    }

    @Override
    public LayuiTableResult selectCheckingByid(Integer id) {
        LayuiTableResult layuiTableResult=new LayuiTableResult();
        try {
            //1. 先查看有没有签到
            List<Integer> i = customerattendanceMapper.selectCheckingInfo(id);
            if (i.size() > 0) {
                layuiTableResult.setMsg("亲！今天已经签过到，不能重复签了!");
                layuiTableResult.setCode(1);
                return  layuiTableResult;
            }
            Customerattendance customerattendance = customerattendanceMapper.selectCheckingByid(id);
            if (customerattendance!=null){
                //查询顾客姓名
                Customer customerById = customerMapper.getCustomerById(id);
                customerattendance.setCname(customerById.getCname());
                layuiTableResult.setData(customerattendance);
                layuiTableResult.setCode(3);
            }else{
                layuiTableResult.setMsg("此客户没有历史签到数据");
                layuiTableResult.setCode(2);
            }
        } catch (Exception e) {
            e.printStackTrace();
            layuiTableResult.setMsg("数据被外星人抓走了！");
        }
        return layuiTableResult;
    }

    @Override
    public Result saveSignIn(Customerattendance customerattendance) {
        try {
            customerattendance.setIsin(1);
            customerattendance.setIndate(LocalDateTime.now());
            System.out.println(customerattendance);
            customerattendanceMapper.addCustomerattendance(customerattendance);
            return Result.ok("签到成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return  Result.ok("签到失败！");
        }
    }
}
