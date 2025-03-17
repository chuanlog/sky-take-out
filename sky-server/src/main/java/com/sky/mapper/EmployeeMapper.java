package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmployeeMapper {

    /**
     * 根据用户名查询员工
     *
     * @param username
     * @return
     */
    Employee getByUsername(String username);

    /**
     * 插入员工
     *
     * @param employee
     */
    @AutoFill(value = OperationType.INSERT)
    void insert(Employee employee);

    /**
     * 分页查询
     * @param employeePageQueryDTO 查询条件
     * @return Page 分页查询结果
     */
    Page<Employee> pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * 修改员工信息
     * @param employee 员工信息
     */
    @AutoFill(value = OperationType.UPDATE)
    void updateEmployee(Employee employee);

    /**
     * 根据id查询员工
     * @param id 员工id
     * @return 员工信息
     */
    Employee getById(Long id);
}
