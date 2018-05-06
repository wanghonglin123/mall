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
package com.whl.mall.ext.component;/**
 * @Title: AuthorityComponent
 * @Package: com.whl.mall.ext.component
 * @Description:
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: WangHongLin timo-wang@msyc.cc
 * @Date: 2018/5/3
 * @Version: V2.1.5
 * @Modify-by: WangHongLin timo-wang@msyc.cc
 * @Modify-date: 2018/5/3
 * @Modify-version: 2.0.10
 * @Modify-description: 新增：增，删，改，查方法
 */

import com.whl.mall.core.MallException;
import com.whl.mall.core.common.constants.MallNumberConstants;
import com.whl.mall.ext.beans.MallBeansExt;
import com.whl.mall.pojo.member.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @ClassName: AuthorityComponent
 * @Description: 权限组件
 * @Author: WangHonglin timo-wang@msyc.cc
 * @Date: 2018/5/3
 */
@Component
public class AuthorityComponent extends MallBeansExt {
    /**
     * 获取菜单树
     *
     * @return
     */
    public List<MenuTree> getMenuTree(Long userIdx) throws MallException{
        List<MenuTree> treeList = null;
        if (userIdx == null) {
            treeList = getMenuService().getTreeData();
        } else {
            treeList = getMemberMenuTree(userIdx);
        }
        return treeList;
    }

    public List<String> getRoleByUserIdx(Long userIdx) throws MallException{
        List<Role> roles = getMemberRoles(userIdx);
        List<String> roleCodes = roles.stream().collect(Collectors.mapping(Role :: getCode, Collectors.toList()));
        return roleCodes;
    }

    private List<Role> getMemberRoles(Long userIdx) throws MallException{
        MemberRole memberRole = new MemberRole();
        memberRole.setMemberIdxCode(userIdx);
        List<MemberRole> memberRoles = getMemberRoleService().queryDataByCondition(memberRole);
        List<Long> roleIdxs =
                memberRoles.stream().collect(Collectors.mapping(MemberRole :: getRoleIdxCode, Collectors.toList()));
        return getRoleService().queryDataIn(roleIdxs);
    }

    public Set<String> getPermissions(Long userIdx) throws MallException {
        List<Button> buttons = null;
        if (userIdx == null) {
            buttons = getButtonService().queryDataByCondition(null);
        } else {
            List<Long> idxs = getMemberMenuOrButtonIdxs(userIdx, MallNumberConstants.TWO);
            buttons = getButtonService().queryDataIn(idxs);
        }
        Set<String> permissions = buttons.stream().collect(Collectors.mapping(Button :: getCode, Collectors.toSet()));
        return permissions;
    }

    
    public List<Long> getMemberMenuOrButtonIdxs(Long userIdx, Short resourceType) throws MallException {
        List<Role> roles = getMemberRoles(userIdx);
        List<Long> idxs = roles.stream().collect(Collectors.mapping(Role :: getIdx, Collectors.toList()));
        if (CollectionUtils.isEmpty(idxs)) {
            return null;
        }
        List<ResourceGroup> resourceGroups = getResourceGroupService().queryDataIn(idxs);
        idxs = resourceGroups.stream().collect(Collectors.mapping(ResourceGroup :: getIdx, Collectors.toList()));
        Resource resource = new Resource();
        resource.setResourceType(resourceType);
        List<Resource> resources = getResourceService().queryDataByCondition(resource, idxs);
        idxs = resources.stream().collect(Collectors.mapping(Resource :: getMenuButtonIdxCode, Collectors.toList()));
        return idxs;
    }

    public List<MenuTree> getMemberMenuTree(Long userIdx) throws MallException {
        List<Long> idxs = getMemberMenuOrButtonIdxs(userIdx, MallNumberConstants.ONE);
        Menu po = new Menu();
        po.setIdx((long) MallNumberConstants.ZERO);
        return getMemberMenuTree(idxs, po);
    }

    private List<MenuTree> getMemberMenuTree(List<Long> idxs, Menu po) throws MallException {
        List<MenuTree> data = new ArrayList<>();
        List<Menu> menus = getMenuService().queryDataByConditions(po.getIdx(), null);
        for (Menu menu : menus) {
            if (idxs.contains(menu.getIdx())) {
                data.add(getMenuTree(menu, idxs));
            }
        }
        return data;
    }

    private MenuTree getMenuTree(Menu menuPo, List<Long> idxs) throws MallException{
        List<Menu> menus = getMenuService().queryDataByConditions(menuPo.getIdx(), null);
        List<MenuTree> menuTrees = new ArrayList<>();
        for (Menu menu : menus) {
            if (idxs.contains(menu.getIdx())) {
                menuTrees.add(getMenuTree(menu, idxs));
            }
        }
        return getMenuService().getMenuTree(menuPo, menuTrees);
    }

    public Session getSession(boolean create) {
        Subject subject = SecurityUtils.getSubject();
        return subject.getSession(create);
    }
}
