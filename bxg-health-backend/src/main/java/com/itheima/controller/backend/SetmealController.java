package com.itheima.controller.backend;

import com.itheima.common.constant.MessageConstant;
import com.itheima.common.entity.PageResult;
import com.itheima.common.entity.QueryPageBean;
import com.itheima.common.entity.Result;
import com.itheima.common.utils.AliOssUtil;
import com.itheima.pojo.Setmeal;
import com.itheima.service.SetMealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/setmeal")
@Slf4j
public class SetmealController {
    @Resource
    private SetMealService setMealService;
    @Resource
    private AliOssUtil aliOssUtil;

    /**
     * 新增体检套餐
     *
     * @param checkgroupIds
     * @param setmeal
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestParam List<Integer> checkgroupIds, @RequestBody Setmeal setmeal) {
        setMealService.add(checkgroupIds, setmeal);
        return Result.success(MessageConstant.ADD_SETMEAL_SUCCESS);
    }

    /**
     * 编辑套餐
     */
    @PostMapping("/edit")
    public Result edit(@RequestParam List<Integer> checkGroupIds, @RequestBody Setmeal setmeal) {
        setMealService.edit(checkGroupIds, setmeal);
        return Result.success(MessageConstant.EDIT_SETMEAL_SUCCESS);
    }

    /**
     * 根据id查询套餐
     */
    @GetMapping("/findById")
    public Result findById(@RequestParam Integer id) {
        Setmeal setmeal = setMealService.findById(id);
        return Result.success(setmeal);
    }

    /**
     * 分页查询
     */
    @PostMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        PageResult pageResult = setMealService.findPage(queryPageBean);
        return pageResult;
    }

    /**
     * 图片上传
     */
    @PostMapping("/upload")
    public Result upload(MultipartFile imgFile) {
        try {
            int index = imgFile.getOriginalFilename().lastIndexOf(".");
            String ext = imgFile.getOriginalFilename().substring(index);
            String filename = UUID.randomUUID().toString() + ext;
            String url = aliOssUtil.upload(imgFile.getBytes(), filename);
            return Result.success(MessageConstant.PIC_UPLOAD_SUCCESS, url);
        } catch (IOException e) {
            log.error("图片上传失败：{}", e.getMessage());
        }
        return Result.fail(MessageConstant.PIC_UPLOAD_FAIL);
    }
}
