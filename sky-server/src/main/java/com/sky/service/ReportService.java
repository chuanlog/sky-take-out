package com.sky.service;

import com.sky.vo.OrderReportVO;
import com.sky.vo.SalesTop10ReportVO;
import com.sky.vo.TurnoverReportVO;
import com.sky.vo.UserReportVO;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

public interface ReportService {

    /**
     * 统计营业额
     * @param begin 开始日期
     * @param end 结束日期
     * @return TurnoverReportVO
     */
    TurnoverReportVO getTurnoverReport(LocalDate begin, LocalDate end);
    /**
     * 统计用户
     * @param begin 开始日期
     * @param end 结束日期
     * @return UserReportVO
     */
    UserReportVO getUserReport(LocalDate begin, LocalDate end);

    /**
     * 统计订单
     * @param begin 开始日期
     * @param end 结束日期
     * @return OrderReportVO
     */
    OrderReportVO getOrderReport(LocalDate begin, LocalDate end);

    /**
     * 统计销量排名前十
     * @param begin
     * @param end
     * @return
     */
    SalesTop10ReportVO getSalesTop10(LocalDate begin, LocalDate end);

    void exportBusinessData(HttpServletResponse response);
}
