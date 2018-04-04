package com.whl.mall.manage.controller.member;
/**
 * @Title: member
 * @Package: com.whl.mall.manage.controller
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-04-02 下午 9:23
 * @Version: V2.0.0
 */

import com.whl.mall.common.MallAjaxException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName: member
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-04-02 下午 9:23
 */
@Controller
public class MemberController {
    /**
     * 进入登陆页
     *
     * @param model model
     * @return
     * @throws Exception Exception
     */
    @RequestMapping("/toLogin")
    public String login(Model model) throws MallAjaxException{
        try {
            System.out.println(1/0);
        } catch (Exception e) {
            /*throw new MallException(e);*/
        }
        return "member/member/login";
    }

    /**
     * 进入首页
     *
     * @return
     */
    @RequestMapping("/")
    public String register() {
        return "member/member/index";
    }

    @RequestMapping("/getPageData")
    @ResponseBody
    public String getPageData() {
        try {
            System.out.println(1 / 0);
        } catch (Exception e) {
        }
        return "ok";
    }
}
