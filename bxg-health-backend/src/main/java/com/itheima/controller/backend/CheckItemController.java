package com.itheima.controller.backend;

import com.itheima.common.constant.MessageConstant;
import com.itheima.common.entity.PageResult;
import com.itheima.common.entity.QueryPageBean;
import com.itheima.common.entity.Result;
import com.itheima.pojo.CheckItem;
import com.itheima.service.CheckItemService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/checkitem")
public class CheckItemController {
    @Resource
    private CheckItemService checkItemService;

    /**
     * 新增检查项
     *
     * @param checkItem 检查项信息
     * @return 操作结果
     */
    @PostMapping("/add")
    public Result add(@RequestBody CheckItem checkItem) {
        checkItemService.add(checkItem);
        return Result.success(MessageConstant.ADD_CHECKITEM_SUCCESS);
    }

    /**
     * 根据id删除检查项
     *
     * @param id 检查项id
     * @return 操作结果
     */
    @GetMapping("/delete")
    public Result delete(Integer id) {
        checkItemService.deleteById(id);
        return Result.success(MessageConstant.DELETE_CHECKITEM_SUCCESS);
    }

    /**
     * 根据检查组id查询检查项id集合
     *
     * @param checkgroupId 检查组id
     * @return 检查项id集合
     */
    @GetMapping("/findCheckItemIdsByCheckGroupId")
    public Result findCheckItemIdsByCheckGroupId(@RequestParam Integer checkgroupId) {
        List<Integer> checkItemIds = checkItemService.findCheckItemIdsByCheckGroupId(checkgroupId);
        return Result.success(checkItemIds);
    }

    /**
     * 编辑检查项
     *
     * @param checkItem 检查项信息
     * @return 操作结果
     */
    @PostMapping("/edit")
    public Result edit(@RequestBody CheckItem checkItem) {
        checkItemService.update(checkItem);
        return Result.success(MessageConstant.EDIT_CHECKITEM_SUCCESS);
    }

    /**
     * 分页查询检查项
     *
     * @param queryPageBean 分页条件
     * @return 分页结果
     */
    @PostMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        return checkItemService.findPage(queryPageBean);
    }

    /**
     * 查询所有检查项
     *
     * @return 检查项集合
     */
    @GetMapping("/findAll")
    public Result findAll() {
        return Result.success(MessageConstant.QUERY_CHECKITEM_SUCCESS, checkItemService.findAll());
    }

    /**
     * 根据id查询检查项
     *
     * @param id 检查项id
     * @return 检查项信息
     */
    @GetMapping("/findById")
    public Result findById(Integer id) {
        return Result.success(MessageConstant.QUERY_CHECKITEM_SUCCESS, checkItemService.findById(id));
    }
}
