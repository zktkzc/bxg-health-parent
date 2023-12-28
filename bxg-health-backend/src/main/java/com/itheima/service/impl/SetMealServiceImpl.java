package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.common.entity.PageResult;
import com.itheima.common.entity.QueryPageBean;
import com.itheima.dao.*;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.CheckItem;
import com.itheima.pojo.Setmeal;
import com.itheima.service.SetMealService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class SetMealServiceImpl implements SetMealService {
    private static final String OUTPUT_PATH = "bxg-health-mobile/src/main/resources/mobile/pages";
    @Resource
    private SetmealMapper setmealMapper;
    @Resource
    private SetmealCheckgroupMapper setmealCheckgroupMapper;
    @Resource
    private CheckGroupMapper checkGroupMapper;
    @Resource
    private FreeMarkerConfigurer freeMarkerConfigurer;
    @Resource
    private CheckGroupCheckItemMapper checkGroupCheckItemMapper;
    @Resource
    private CheckItemMapper checkItemMapper;

    /**
     * 分页查询
     *
     * @param queryPageBean
     * @return
     */
    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        Page<Setmeal> page = setmealMapper.list(queryPageBean.getQueryString());
        for (Setmeal setmeal : page) {
            List<Integer> checkgroupIds = setmealCheckgroupMapper.selectBySetmealId(setmeal.getId());
            List<CheckGroup> checkGroups = new ArrayList<>();
            for (Integer checkgroupId : checkgroupIds) {
                CheckGroup checkGroup = checkGroupMapper.selectById(checkgroupId);
                checkGroups.add(checkGroup);
            }
            setmeal.setCheckGroups(checkGroups);
        }
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 根据id查询套餐
     *
     * @param id
     * @return
     */
    @Override
    public Setmeal findById(Integer id) {
        Setmeal setmeal = setmealMapper.selectById(id);
        List<Integer> checkgroupIds = setmealCheckgroupMapper.selectBySetmealId(id);
        List<CheckGroup> checkGroups = new ArrayList<>();
        for (Integer checkgroupId : checkgroupIds) {
            CheckGroup checkGroup = checkGroupMapper.selectById(id);
            checkGroups.add(checkGroup);
        }
        return setmeal;
    }

    /**
     * 编辑套餐
     *
     * @param checkgroupIds
     * @param setmeal
     */
    @Override
    @Transactional
    public void edit(List<Integer> checkgroupIds, Setmeal setmeal) {
        setmealCheckgroupMapper.deleteBySetmealId(setmeal.getId());
        setmealMapper.update(setmeal);
        setmealCheckgroupMapper.insertBatch(setmeal.getId(), checkgroupIds);
    }

    /**
     * 新增体检套餐
     *
     * @param checkgroupIds
     * @param setmeal
     */
    @Override
    @Transactional
    public void add(List<Integer> checkgroupIds, Setmeal setmeal) {
        setmealMapper.insert(setmeal);
        setmealCheckgroupMapper.insertBatch(setmeal.getId(), checkgroupIds);
        // 生成静态页面
        generateMobileStaticHtml();
    }

    private void generateMobileStaticHtml() {
        List<Setmeal> setmealList = setmealMapper.list(null);
        // 生成套餐详情静态页面
        for (Setmeal setmeal : setmealList) {
            List<Integer> checkgroupIds = setmealCheckgroupMapper.selectBySetmealId(setmeal.getId());
            List<CheckGroup> checkGroups = new ArrayList<>();
            for (Integer checkgroupId : checkgroupIds) {
                CheckGroup checkGroup = checkGroupMapper.selectById(checkgroupId);
                List<Integer> checkItemIds = checkGroupCheckItemMapper.findCheckItemIdsByCheckGroupId(checkGroup.getId());
                List<CheckItem> checkItems = new ArrayList<>();
                for (Integer checkItemId : checkItemIds) {
                    CheckItem checkItem = checkItemMapper.selectById(checkItemId);
                    checkItems.add(checkItem);
                }
                checkGroup.setCheckItems(checkItems);
                checkGroups.add(checkGroup);
            }
            setmeal.setCheckGroups(checkGroups);
            generateMobileSetmealDetailHtml(setmeal);
        }
        // 生成套餐列表静态页面
        generateMobileSetmealListHtml(setmealList);
    }

    /**
     * 生成套餐详情静态页面
     */
    private void generateMobileSetmealDetailHtml(Setmeal setmeal) {
        Map<String, Object> map = new HashMap<>();
        map.put("setmeal", setmeal);
        generateHtml("mobile_setmeal_detail.ftl", "setmeal_detail_" + setmeal.getId() + ".html", map);
    }

    /**
     * 生成套餐列表静态页面
     */
    private void generateMobileSetmealListHtml(List<Setmeal> setmealList) {
        Map<String, Object> map = new HashMap<>();
        map.put("setmealList", setmealList);
        generateHtml("mobile_setmeal.ftl", "m_setmeal.html", map);
    }

    private void generateHtml(String templateName, String htmlPageName, Map<String, Object> dataMap) {
        try {
            freeMarkerConfigurer.setDefaultEncoding("utf-8");
            freeMarkerConfigurer.setTemplateLoaderPath("classpath:/templates");
            Configuration configuration = freeMarkerConfigurer.getConfiguration();
            Template template = configuration.getTemplate(templateName);
            FileWriter fileWriter = new FileWriter(new File(OUTPUT_PATH + "/" + htmlPageName));
            template.process(dataMap, fileWriter);
            fileWriter.close();
        } catch (Exception e) {
            log.error("生成静态页面失败：{}", e.getMessage());
        }
    }
}
