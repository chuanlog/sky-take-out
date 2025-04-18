package com.sky.controller.admin;


import com.sky.result.Result;
import com.sky.service.ReportService;
import com.sky.vo.OrderReportVO;
import com.sky.vo.SalesTop10ReportVO;
import com.sky.vo.TurnoverReportVO;
import com.sky.vo.UserReportVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

/**
 * 员工管理
 */
@RestController
@RequestMapping("/admin/report")
@Slf4j
@Api(tags = "商家端数据报告展示")
public class ReportController {
    @Autowired
    private ReportService reportService;


    /**
     * 营业额统计
     *
     * @param begin 开始日期
     * @param end   结束日期
     * @return
     */
    @GetMapping("/turnoverStatistics")
    @ApiOperation("营业额统计")
    public Result<TurnoverReportVO> turnoverStatistics(
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end
    ) {
        log.info("营业额统计，开始时间：{}，结束时间：{}", begin, end);
        return Result.success(reportService.getTurnoverReport(begin, end));
    }

    /**
     * 用户数据统计
     *
     * @param begin
     * @param end
     * @return
     */
    @GetMapping("/userStatistics")
    @ApiOperation("用户统计")
    public Result<UserReportVO> userStatistics(
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        log.info("用户统计，开始时间：{}，结束时间：{}", begin, end);
        return Result.success(reportService.getUserReport(begin, end));
    }

    /**
     * 订单数量统计
     * @param begin
     * @param end
     * @return
     */
    @GetMapping("/ordersStatistics")
    @ApiOperation("订单统计")
    public Result<OrderReportVO> ordersStatistics(
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        log.info("订单统计，开始时间：{}，结束时间：{}", begin, end);
        return Result.success(reportService.getOrderReport(begin, end));
    }

    /**
     * Top10菜品统计
     * @param begin
     * @param end
     * @return
     */
    @GetMapping("/top10")
    @ApiOperation("Top10菜品统计")
    public Result<SalesTop10ReportVO> top10(
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        log.info("Top10菜品统计，开始时间：{}，结束时间：{}", begin, end);
        return Result.success(reportService.getSalesTop10(begin, end));
    }

    /**
     * 导出运营数据报表Excel
     * @param response
     * @return
     */
    @GetMapping("/export")
    @ApiOperation("导出运营数据报表Excel")
    public void export(HttpServletResponse response){
        log.info("导出运营数据报表Excel");
        reportService.exportBusinessData(response);
    }
}
