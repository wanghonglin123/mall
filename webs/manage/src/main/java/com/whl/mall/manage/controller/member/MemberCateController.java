package com.whl.mall.manage.controller.member;
/**
 * @Title: MemberCatController
 * @Package: com.whl.mall.manage.controller.member
 * @Description: 成员类别
 * @Author: WangHongLin
 * @Date: 2018-04-08 下午 11:23
 * @Version: V2.0.0
 */

import com.whl.mall.core.MallResult;
import com.whl.mall.ext.controller.MallBaseController;
import com.whl.mall.pojo.member.Button;
import com.whl.mall.pojo.member.Menu;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName: MemberCatController
 * @Description: 用户类别Controller
 * @Author: WangHongLin
 * @Date: 2018-04-08 下午 11:23
 */
@Controller
public class MemberCateController extends MallBaseController {
    /**
     * 进入列表
     *
     * @return
     */
    @RequestMapping("/memberCate/toList")
    public String toList() {
        return "member/memberCate/memberCatelist";
    }

    /**
     * 进入新增页面
     *
     * @return
     */
    @RequestMapping("/memberCate/{type}/{idx}")
    public String toAddOrEditOrSee(@PathVariable String type, @PathVariable Long idx) {
        return "member/memberCate/saveOrEditOrViewMemberCate";
    }

    /**
     * 新增或者修改
     *
     * @param menu
     * @return
     */
    @RequestMapping("/memberCate/do-saveOrEdit")
    @ResponseBody
    public MallResult saveOrEdit(Menu menu) throws Exception{
        super.getMenuService().save(menu);
        return MallResult.ok();
    }
}
