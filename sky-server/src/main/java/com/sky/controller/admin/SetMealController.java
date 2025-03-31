package com.sky.controller.admin;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.SetmealService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 套餐管理相关接口
 */
@RestController
@RequestMapping("/admin/setmeal")
@Slf4j
@Api(tags = "套餐相关接口")
public class SetMealController {
    @Autowired
    SetmealService setMealService;

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

    /**
     * 批量删除套餐
     * @param ids
     * @return Result
     */
    @DeleteMapping
    @ApiOperation("批量删除套餐")
    public Result delete(@RequestParam List<Long> ids) {
        log.info("批量删除套餐:{}", ids);
        setMealService.deleteBatch(ids);
        return Result.success() ;
    }

    /**
     * 分页查询
     * @param setmealPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("分页查询")
    public Result<PageResult> page(SetmealPageQueryDTO setmealPageQueryDTO) {
        PageResult pageResult = setMealService.pageQuery(setmealPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 起售停售
     * @param status
     * @param id
     */
    @PostMapping("/status/{status}")
    @ApiOperation("起售停售")
    public Result startOrStop(@PathVariable Integer status,@RequestParam Long id) {
        log.info("起售停售:{},{}", status, id);
        setMealService.startOrStop(status, id);
        return Result.success();
    }
}
