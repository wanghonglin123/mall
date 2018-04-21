package com.whl.mall.manage.controller.member;
/**
 * @Title: ButtonController
 * @Package: com.whl.mall.manage.controller.member
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-04-08 下午 11:26
 * @Version: V2.0.0
 */

import com.whl.mall.core.MallException;
import com.whl.mall.core.MallGridResult;
import com.whl.mall.core.MallResult;
import com.whl.mall.core.common.constants.MallMessage;
import com.whl.mall.core.common.constants.MallNumberConstants;
import com.whl.mall.core.common.constants.MallStatus;
import com.whl.mall.core.common.utils.MallJsonUtils;
import com.whl.mall.core.log.MallLog4jLog;
import com.whl.mall.ext.controller.MallBaseController;
import com.whl.mall.pojo.member.Button;
import com.whl.mall.pojo.member.Menu;
import com.whl.mall.pojo.member.MenuTree;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName: ButtonController
 * @Description: 按钮Controller
 * @Author: WangHongLin
 * @Date: 2018-04-08 下午 11:26
 */
@Controller
public class ButtonController extends MallBaseController {

    /**
     * 操作 1：新增 2：编辑 3：查看 4：删除
     *
     * @param po po
     * @return
     */
    @RequestMapping("/button/operation/{type}")
    @ResponseBody
    public MallResult operation(@PathVariable Short type, Button po) throws Exception{
        if (type == MallNumberConstants.ONE) { // 新增
            super.getButtonService().save(po);
            return MallResult.ok();
        }

        Long idxCode = po.getIdxCode();
        if (idxCode == null) {
            return MallResult.build(MallStatus.HTTP_STATUS_400, MallMessage.CONTROLLER_INVALID_PARAMETER);
        }
        if (type == MallNumberConstants.TWO) {
            super.getButtonService().update(po);
        } else if (type == MallNumberConstants.THREE) {
            return MallResult.ok(super.getButtonService().queryOneSomeInfoByCondition(po));
        } else {
            return MallResult.ok(super.getButtonService().delete(po));
        }
        return MallResult.ok();
    }

    /**
     * 跳转到操作页码 1：新增 2：编辑 3：查看
     *
     * @return
     */
    @RequestMapping("/button/toOperation/{type}/{idxCode}")
    public String toOperation(@PathVariable String type, @PathVariable Long idxCode, HttpServletRequest request) throws Exception{
        request.setAttribute("type", type);

        if ("edit".equals(type) || "see".equals(type)) {
            Button po = new Button();
            po.setIdxCode(idxCode);
            po = getButtonService().queryOneSomeInfoByCondition(po);
            request.setAttribute("obj", po);
        }
        if ("add".equals(type)) {
            Menu menu = new Menu();
            menu.setIdx(idxCode);
            menu= getMenuService().queryOneSomeInfoByCondition(menu);
            request.setAttribute("menu", menu);
        }
        return "/member/button/saveOrEditOrViewButton";
    }

    /**
     * 分页查询
     * @return
     */
    @RequestMapping("/button/paging")
    @ResponseBody
    public MallGridResult paging(Button po, Integer page, Integer rows, String order) throws MallException {
        return getButtonService().queryPageDataByCondition(po, page, rows, order);
    }

    /**
     * 分页查询
     * @return
     */
    @RequestMapping("/button/getButtionsByMenuIdx")
    @ResponseBody
    public MallResult getButtionsByMenuIdx(Button po) throws MallException {
        return MallResult.ok(getButtonService().queryDataByCondition(po));
    }
}
