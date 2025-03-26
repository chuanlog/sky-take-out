package com.sky.controller.admin;

import com.sky.dto.SetmealDTO;
import com.sky.result.Result;
import com.sky.service.SetMealService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 套餐管理相关接口
 */
@RestController
@RequestMapping("/admin/setmeal")
@Slf4j
@Api(tags = "套餐相关接口")
public class SetMealController {
    @Autowired
    SetMealService setMealService;

    /**
     * 新增套餐
     * @param setMealDTO setMealDTO
     * @return
     */
    @PostMapping
    @ApiOperation("新增套餐")
    public Result save(@RequestBody SetmealDTO setMealDTO) {
        log.info("新增套餐:{}", setMealDTO);
        setMealService.saveWithDish(setMealDTO);
        return Result.success() ;
    }

    /**
     * 修改套餐
     * @param setMealDTO
     * @return
     */
    @PutMapping
    @ApiOperation("修改套餐")
    public Result update(@RequestBody SetmealDTO setMealDTO) {
        log.info("修改套餐:{}", setMealDTO);
        setMealService.updateWithDish(setMealDTO);
        return Result.success() ;
    }
}
