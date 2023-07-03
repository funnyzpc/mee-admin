package com.mee.sys.vo.server;



import com.mee.common.util.ArithUtil;
import com.mee.common.util.DateUtil;

import java.lang.management.ManagementFactory;

/**
 * JVM相关信息
 * 
 * @author shadow
 */
public class Jvm {
    /**
     * 当前JVM占用的内存总数(M)
     */
    private double total;

    /**
     * JVM最大可用内存总数(M)
     */
    private double max;

    /**
     * JVM空闲内存(M)
     */
    private double free;

    /**
     * JDK版本
     */
    private String version;

    /**
     * JDK路径
     */
    private String home;

    public double getTotal()
    {
        return ArithUtil.div(total, (1024 * 1024), 2);
    }

    public void setTotal(double total)
    {
        this.total = total;
    }

    public double getMax()
    {
        return ArithUtil.div(max, (1024 * 1024), 2);
    }

    public void setMax(double max)
    {
        this.max = max;
    }

    public double getFree()
    {
        return ArithUtil.div(free, (1024 * 1024), 2);
    }

    public void setFree(double free)
    {
        this.free = free;
    }

    public double getUsed()
    {
        return ArithUtil.div(total - free, (1024 * 1024), 2);
    }

    public double getUsage()
    {
        return ArithUtil.mul(ArithUtil.div(total - free, total, 4), 100);
    }

    /**
     * 获取JDK名称
     */
    public String getName()
    {
        return ManagementFactory.getRuntimeMXBean().getVmName();
    }

    public String getVersion()
    {
        return version;
    }

    public void setVersion(String version)
    {
        this.version = version;
    }

    public String getHome()
    {
        return home;
    }

    public void setHome(String home)
    {
        this.home = home;
    }

    /**
     * JDK启动时间
     */
    public String getStartTime()
    {
//        return DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, DateUtils.getServerStartDate());
        return DateUtil.getServerStartDateStr();
    }

    /**
     * JDK运行时间
     */
    public String getRunTime() {
        //  return DateUtils.getDatePoor(DateUtils.getNowDate(), DateUtils.getServerStartDate());
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // 获得两个时间的毫秒时间差异
        //long diff = DateUtil.betweenNanos(DateUtil.getServerStartDate(), DateUtil.now());
        long diff = DateUtil.betweenNanos( DateUtil.now(),DateUtil.getServerStartDate());
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果nm / ns;
        //        return day + "天" + hour + "小时
        // long sec = diff % nd % nh % " + min + "分钟";
        return day + "天" + hour + "小时" + min + "分钟";
    }

    /**
     * 运行参数
     */
    public String getInputArgs() {
        return ManagementFactory.getRuntimeMXBean().getInputArguments().toString();
    }
}
