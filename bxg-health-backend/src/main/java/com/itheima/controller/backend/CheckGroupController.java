package com.itheima.controller.backend;

import com.itheima.common.constant.MessageConstant;
import com.itheima.common.entity.PageResult;
import com.itheima.common.entity.QueryPageBean;
import com.itheima.common.entity.Result;
import com.itheima.pojo.CheckGroup;
import com.itheima.service.CheckGroupService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/checkgroup")
public class CheckGroupController {
    @Resource
    private CheckGroupService checkGroupService;

    /**
     * 新增检查组
     *
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestParam List<Integer> checkitemIds, @RequestBody CheckGroup checkGroup) {
        checkGroupService.add(checkitemIds, checkGroup);
        return Result.success(MessageConstant.ADD_CHECKGROUP_SUCCESS);
    }

    /**
     * 根据id删除检查组
     *
     * @param id 检查组id
     * @return
     */
    @GetMapping("/deleteById")
    public Result deleteById(Integer id) {
        checkGroupService.deleteById(id);
        return Result.success(MessageConstant.DELETE_CHECKGROUP_SUCCESS);
    }

    /**
     * 根据套餐id查询关联的检查组id
     *
     * @param seatmealId 套餐id
     * @return
     */
    @GetMapping("/findCheckGroupIdsBySetmealId")
    public Result findCheckGroupIdsBySetmealId(Integer seatmealId) {
        List<Integer> checkgroupIds = checkGroupService.findCheckgroupIdsBySetmealId(seatmealId);
        return Result.success(MessageConstant.QUERY_CHECKGROUP_SUCCESS, checkgroupIds);
    }

    /**
     * 分页查询
     *
     * @param queryPageBean
     * @return
     */
    @PostMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        return checkGroupService.findPage(queryPageBean);
    }

    /**
     * 编辑检查组
     *
     * @param checkitemIds
     * @param checkGroup
     * @return
     */
    @PostMapping("/edit")
    public Result edit(@RequestParam List<Integer> checkitemIds, @RequestBody CheckGroup checkGroup) {
        checkGroupService.edit(checkitemIds, checkGroup);
        return Result.success(MessageConstant.EDIT_CHECKGROUP_SUCCESS);
    }

    /**
     * 查询所有检查组
     */
    @GetMapping("/findAll")
    public Result findAll() {
        List<CheckGroup> checkGroups = checkGroupService.findAll();
        return Result.success(MessageConstant.QUERY_CHECKGROUP_SUCCESS, checkGroups);
    }

    /**
     * 根据id查询检查组
     */
    @GetMapping("/findById")
    public Result findById(Integer id) {
        CheckGroup checkGroup = checkGroupService.findById(id);
        return Result.success(MessageConstant.QUERY_CHECKGROUP_SUCCESS, checkGroup);
    }
}
