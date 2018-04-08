package com.whl.mall.manage.controller.member;
/**
 * @Title: ButtonController
 * @Package: com.whl.mall.manage.controller.member
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-04-08 下午 11:26
 * @Version: V2.0.0
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: ButtonController
 * @Description: 按钮Controller
 * @Author: WangHongLin
 * @Date: 2018-04-08 下午 11:26
 */
@Controller
public class ButtonController {

    /**
     * 进入新增页面
     *
     * @return
     */
    @RequestMapping("/button/{type}/{idx}")
    public String toAddOrEditOrSee(@PathVariable String type, @PathVariable Long idx) {
        return "member/button/saveOrEditOrViewButton";
    }
}
