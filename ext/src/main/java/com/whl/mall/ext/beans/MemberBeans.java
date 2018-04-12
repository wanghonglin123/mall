package com.whl.mall.ext.beans;
/**
 * @Title: MemberBeans
 * @Package: com.whl.mall.common.beans
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-04-12 下午 11:09
 * @Version: V2.0.0
 */

import com.whl.mall.core.common.beans.MallBeans;
import com.whl.mall.interfaces.member.MemberService;
import com.whl.mall.interfaces.member.MenuService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName: MemberBeans
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-04-12 下午 11:09
 */
public class MemberBeans extends MallBeans{
    @Autowired
    private MemberService memberService;

    @Autowired
    private MenuService menuService;

    public MemberService getMemberService() {
        return memberService;
    }

    public MenuService getMenuService() {
        return menuService;
    }
}
