package com.sky.service;

import com.sky.vo.TurnoverReportVO;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface ReportService {

    /**
     * 统计营业额
     * @param begin 开始日期
     * @param end 结束日期
     * @return TurnoverReportVO
     */
    TurnoverReportVO getTurnoverReport(LocalDate begin, LocalDate end);
}
