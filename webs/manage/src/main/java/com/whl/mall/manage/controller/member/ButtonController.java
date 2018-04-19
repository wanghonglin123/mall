package com.whl.mall.manage.controller.member;
/**
 * @Title: ButtonController
 * @Package: com.whl.mall.manage.controller.member
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-04-08 下午 11:26
 * @Version: V2.0.0
 */

import com.whl.mall.core.MallResult;
import com.whl.mall.core.log.MallLog4jLog;
import com.whl.mall.ext.controller.MallBaseController;
import com.whl.mall.pojo.member.Button;
import com.whl.mall.pojo.member.Menu;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName: ButtonController
 * @Description: 按钮Controller
 * @Author: WangHongLin
 * @Date: 2018-04-08 下午 11:26
 */
@Controller
public class ButtonController extends MallBaseController {

    /**
     * 进入新增页面
     *
     * @return
     */
    @RequestMapping("/button/{type}/{idx}")
    public String toAddOrEditOrSee(@PathVariable String type, @PathVariable Long idx) {
        return "member/button/saveOrEditOrViewButton";
    }
    private static final Logger LOGGER = LogManager.getLogger(ButtonController.class);
    /**
     * 新增或者修改
     *
     * @param po po
     * @return
     */
    @RequestMapping("/button/do-saveOrEdit")
    @ResponseBody
    public MallResult saveOrEdit(Button po) throws Exception{
        super.getButtonService().save(po);
        return MallResult.ok();
    }

}
