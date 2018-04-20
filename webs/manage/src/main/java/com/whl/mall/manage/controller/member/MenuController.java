/**
 * 广州市两棵树网络科技有限公司版权所有
 * DT Group Technology & commerce Co., LtdAll rights reserved.
 * <p>
 * 广州市两棵树网络科技有限公司，创立于2009年。旗下运营品牌洋葱小姐。
 * 洋葱小姐（Ms.Onion） 下属三大业务模块 [洋葱海外仓] , [洋葱DSP] , [洋葱海外聚合供应链]
 * [洋葱海外仓]（DFS）系中国海关批准的跨境电商自营平台(Cross-border ecommerce platform)，
 * 合法持有海外直邮保税模式的跨境电商营运资格。是渠道拓展，平台营运，渠道营运管理，及客户服务等前端业务模块。
 * [洋葱DSP]（DSP）系拥有1.3亿消费者大数据分析模型。 是基于客户的消费行为，消费轨迹，及多维度云算法(MDPP)
 * 沉淀而成的精准消费者模型。洋葱DSP能同时为超过36种各行业店铺 及200万个销售端口
 * 进行多店铺高精度配货，并能预判消费者购物需求进行精准推送。同时为洋葱供应链提供更前瞻的商品采买需求模型 。
 * [洋葱海外聚合供应链]（Super Supply Chain）由中国最大的进口贸易集团共同
 * 合资成立，拥有20余年的海外供应链营运经验。并已入股多家海外贸易企业，与欧美澳等9家顶级全球供应商达成战略合作伙伴关系。
 * 目前拥有835个国际品牌直接采买权，12万个单品的商品供应库。并已建设6大海外直邮仓库，为国内客户提供海外商品采买集货供应，
 * 跨境 物流，保税清关三合一的一体化模型。目前是中国唯一多模式聚合的海外商品供应链 。
 * <p>
 * 洋葱商城：http://m.msyc.cc/wx/indexView?tmn=1
 * <p>
 * 洋桃商城：http://www.yunyangtao.com
 */
package com.whl.mall.manage.controller.member;

/**
 * @Title: MenuController
 * @Package: com.xunbao.controller.member
 * @Description:
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: WangHongLin timo-wang@msyc.cc
 * @Date: 2018/3/22
 * @Version: V2.0.10
 * @Modify-by: WangHongLin timo-wang@msyc.cc
 * @Modify-date: 2018/3/22
 * @Modify-version: 2.1.5
 * @Modify-description: 新增：增，删，改，查方法
 */

import com.whl.mall.core.MallException;
import com.whl.mall.core.MallGridResult;
import com.whl.mall.core.MallResult;
import com.whl.mall.core.common.constants.MallMessage;
import com.whl.mall.core.common.constants.MallNumberConstants;
import com.whl.mall.core.common.constants.MallStatus;
import com.whl.mall.core.common.utils.MallJsonUtils;
import com.whl.mall.ext.controller.MallBaseController;
import com.whl.mall.pojo.member.Menu;
import com.whl.mall.pojo.member.Menu;
import com.whl.mall.pojo.member.MenuTree;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName: MenuController
 * @Description: 菜单Controller
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: WangHonglin timo-wang@msyc.cc
 * @Date: 2018/3/22
 */
@Controller
public class MenuController extends MallBaseController {
    /**
     * 进入列表
     *
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/menu/toList")
    public String toList(Model model) {
        return "/member/menu/menuList";
    }

    /**
     * 页面操作 1：增 2：查看 3：改
     *
     * @param type
     * @param idx
     * @return
     */
    @RequestMapping("/menu/{type}/{idx}")
    public String toSaveOrEditOrViewMenu(@PathVariable String type, @PathVariable Long idx, ModelAndView view) {
        view.addObject("type", type);
        return "/member/menu/saveOrEditOrViewMenu";
    }

    /**
     * 操作 1：新增 2：编辑 3：查看 4：删除
     *
     * @param po po
     * @return
     */
    @RequestMapping("/menu/operation/{type}")
    @ResponseBody
    public MallResult operation(@PathVariable Short type, Menu po) throws Exception{
        if (type == MallNumberConstants.ONE) { // 新增
            super.getMenuService().save(po);
            return MallResult.ok();
        }

        Long idxCode = po.getIdxCode();
        if (idxCode == null) {
            return MallResult.build(MallStatus.HTTP_STATUS_400, MallMessage.CONTROLLER_INVALID_PARAMETER);
        }
        if (type == MallNumberConstants.TWO) {
            super.getMenuService().update(po);
        } else if (type == MallNumberConstants.THREE) {
            return MallResult.ok(super.getMenuService().queryOneSomeInfoByCondition(po));
        } else {
            return MallResult.ok(super.getMenuService().delete(po));
        }
        return MallResult.ok();
    }

    /**
     * 跳转到操作页码 add：新增 edit：编辑 see：查看
     *
     * @return
     */
    @RequestMapping("/menu/toOperation/{type}/{idxCode}")
    public String toOperation(@PathVariable String type, @PathVariable Long idxCode, HttpServletRequest request) throws Exception{
        request.setAttribute("type", type);

        if ("edit".equals(type) || "see".equals(type)) {
            Menu po = new Menu();
            po.setIdxCode(idxCode);
            Menu menu = getMenuService().queryOneSomeInfoByCondition(po);
            request.setAttribute("obj", menu);
        }
        return "/member/menu/saveOrEditOrViewMenu";
    }

    /**
     * 分页查询
     * @return
     */
    @RequestMapping("/menu/paging")
    @ResponseBody
    public MallGridResult paging(Menu po, Integer page, Integer rows, String order) throws MallException {
        return getMenuService().queryPageDataByCondition(po, page, rows, order);
    }

    /**
     * 获取全部菜单
     * @return
     */
    @RequestMapping("/menu/listAll")
    @ResponseBody
    public MallResult listAll() throws MallException{
        List<MenuTree> treeList = super.getMenuService().getTreeData();
        return MallResult.ok(MallJsonUtils.objectToJson(treeList));
    }

    /**
     * 获取菜单数据
     *
     * @param level 菜单级别
     * @return
     */
    @RequestMapping("/menu/getMenuData/{level}")
    @ResponseBody
    public MallResult getMenuData(@PathVariable Short level) throws MallException {
        List<Menu> list = super.getMenuService().queryDataByConditions(null, level);
        return MallResult.ok(list);
    }
}
