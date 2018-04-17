<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<jsp:include page="../../header.jsp"/>
<section class="main">
    <div class="table-container">
        <div class="function-button">
            <shiro:hasPermission name="system:rebuild:index:template">
                <button type="button" id="rebuildTemplate" class="button button-ffac00">重建索引模版</button>
            </shiro:hasPermission>
            <shiro:hasPermission name="system:rebuild:item:index">
                <button type="button" id="rebuildItemIndex" class="button button-ffac00">重建商品索引</button>
            </shiro:hasPermission>
            <shiro:hasPermission name="system:rebuild:user:index">
                <button type="button" id="rebuildUserIndex" class="button button-ffac00">重建会员索引</button>
            </shiro:hasPermission>
            <shiro:hasPermission name="system:rebuild:order:index">
                <button type="button" id="rebuildOrderIndex" class="button button-ffac00">重建订单索引</button>
            </shiro:hasPermission>
            <shiro:hasPermission name="system:rebuild:returnOrder:index">
                <button type="button" id="rebuildReturnOrderIndex" class="button button-ffac00">重建售后订单索引</button>
            </shiro:hasPermission>
            <shiro:hasPermission name="system:rebuild:collector:item:index">
                <button type="button" id="rebuildCollectorItemCollectionIndex" class="button button-ffac00">重建收集器商品索引
                </button>
            </shiro:hasPermission>
            <shiro:hasPermission name="system:rebuild:collector:item:collection:index">
                <button type="button" id="rebuildCollectorItemCollectionIndex" class="button button-ffac00">重建收集器商品采集索引
                </button>
            </shiro:hasPermission>
            <shiro:hasPermission name="system:rebuild:collector:item:price:index">
                <button type="button" id="rebuildCollectorItemPriceIndex" class="button button-ffac00">重建收集器商品价格索引</button>
            </shiro:hasPermission>
            <shiro:hasPermission name="system:rebuild:collector:item:customs:index">
                <button type="button" id="rebuildCollectorItemCustomsIndex" class="button button-ffac00">重建收集器商品报关索引</button>
            </shiro:hasPermission>
            <shiro:hasPermission name="system:rebuild:collector:item:warehouse:index">
                <button type="button" id="rebuildCollectorItemWarehouseIndex" class="button button-ffac00">重建收集器商品仓库索引
                </button>
            </shiro:hasPermission>
            <shiro:hasPermission name="system:rebuild:collector:item:cart:index">
                <button type="button" id="rebuildCartIndex" class="button button-ffac00">重建进货单索引</button>
            </shiro:hasPermission>
            <shiro:hasPermission name="system:rebuild:logistics:itemTable:index">
                <button type="button" id="rebuildLogisticsItemTableIndex" class="button button-ffac00">重建物流商品表格索引</button>
            </shiro:hasPermission>
            <shiro:hasPermission name="system:rebuild:stockLockApply:index">
                <button type="button" id="rebuildStockLockApplyIndex" class="button button-ffac00">重建锁库申请索引</button>
            </shiro:hasPermission>
            <shiro:hasPermission name="system:rebuild:stockLockApplyDetail:index">
                <button type="button" id="rebuildStockLockApplyDetailIndex" class="button button-ffac00">重建锁库申请详情索引</button>
            </shiro:hasPermission>
            <shiro:hasPermission name="system:rebuild:stockLock:index">
                <button type="button" id="rebuildStockLockIndex" class="button button-ffac00">重建锁库库存索引</button>
            </shiro:hasPermission>
            <shiro:hasPermission name="system:rebuild:stockLockDetail:index">
                <button type="button" id="rebuildStockLockDetailIndex" class="button button-ffac00">重建锁库库存详情索引</button>
            </shiro:hasPermission>
            <shiro:hasPermission name="system:rebuild:stockLockDetailNoPay:index">
                <button type="button" id="rebuildStockLockDetailNoPayIndex" class="button button-ffac00">重建锁库库存详情-未支付索引</button>
            </shiro:hasPermission>
        </div>
        <div class="yt-table">
            <table id="indexRebuildLogTable" style="width:98%;height:464px"></table>
        </div>
    </div>
    <script type="text/javascript">
        $(function () {
            $('#indexRebuildLogTable').datagrid({
                url: '/system/index/rebuild/log/grid',
                idField: 'id',
                rownumbers: true,
                singleSelect: false,
                iconCls: 'icon-save',
                pagination: true,
                method: 'post',
                columns: [[
                    {title: '', field: '', width: '5%', align: 'center', checkbox: 'true'},
                    {
                        title: '操作',
                        width: '10%',
                        align: 'center',
                        field: 'productname',
                        formatter: function (value, row, index) {
                            var array = [];
                            <shiro:hasPermission name="system:rebuild:index:detail">
                            array.push('<a class="margin-auto click-icon click-icon-check" title="查看" href="javascript:" onclick="viewRebuildDetail(' + row.idxCode + ')"></a>');
                            </shiro:hasPermission>
                            <shiro:hasPermission name="system:rebuild:index:update:fail">
                            array.push('<a class="margin-auto click-icon click-icon-edit" title="重建失败" href="javascript:" onclick="updateRebuildFail(' + row.idxCode + ')"></a>');
                            </shiro:hasPermission>
                            return array.join('');
                        }
                    },
                    {
                        title: '索引名称', field: 'aliasName', width: '20%', align: 'center',
                        formatter: function (value, row, index) {
                            if (row.aliasName == null || row.aliasName == "") {
                                return "-";
                            }
                            return ${indexPrefixJson}[row.aliasName];
                        }
                    },
                    {
                        title: '重建类型', field: 'rebuildType', width: '10%', align: 'center',
                        formatter: function (value, row, index) {
                            return ${rebuildTypeJson}[row.rebuildType];
                        }
                    },
                    {
                        title: '重建状态', field: 'rebuildStatus', width: '10%', align: 'center',
                        formatter: function (value, row, index) {
                            return ${rebuildStatusJson}[row.rebuildStatus];
                        }
                    },
                    {
                        title: '开始时间', field: 'startTime', width: '15%', align: 'center', sortable: true,
                        formatter: function (value, row, index) {
                            return row.startTimeEnYyyyMMddHHmmss;
                        }
                    },
                    {
                        title: '结束时间', field: 'endTime', width: '15%', align: 'center', sortable: true,
                        formatter: function (value, row, index) {
                            return row.endTimeEnYyyyMMddHHmmss;
                        }
                    },
                    {
                        title: '失败信息', field: 'remark', width: '15%', align: 'center',
                        formatter: function (value, row, index) {
                            if (row.remark == null || row.remark == "") {
                                return "-";
                            }
                            return row.remark;
                        }
                    }
                ]],
                onLoadSuccess: function (data) {
                    /* 表格行数宽度随内容变化 */
                    $(this).datagrid("fixRownumber");
                    Ms.clearGridSelections('indexRebuildLogTable');
                }
            });
            // 重建索引模版
            $('#rebuildTemplate').click(function () {
                parent.layer.confirm('您确定要重建索引模版吗？', {
                    title: false,
                    btn: ['确定', '关闭']
                }, function (index) {
                    parent.layer.load(2, {shade: 0.01});
                    $.ajax({
                        type: "post",
                        url: "/system/index/template/rebuild",
                        dataType: 'json',
                        success: function (d) {
                            parent.layer.closeAll("loading");
                            if (d.status == 200) {
                                parent.layer.msg('操作成功！', {icon: 1});
                            } else {
                                parent.layer.msg(d.msg, {icon: 2});
                            }
                            Ms.reloadDataGrid('indexRebuildLogTable');
                        },
                        error: function (d) {
                            parent.layer.closeAll("loading");
                            parent.layer.msg(d.msg, {icon: 2});
                        }
                    });
                });
            });
            // 重建商品索引
            $('#rebuildItemIndex').click(function () {
                parent.layer.confirm('您确定要重建商品索引吗？', {
                    title: false,
                    btn: ['确定', '关闭']
                }, function (index) {
                    parent.layer.load(2, {shade: 0.01});
                    $.ajax({
                        type: "post",
                        url: "/system/index/rebuild/item",
                        dataType: 'json',
                        success: function (d) {
                            parent.layer.closeAll("loading");
                            if (d.status == 200) {
                                parent.layer.msg('操作成功！', {icon: 1});
                            } else {
                                parent.layer.msg(d.msg, {icon: 2});
                            }
                            Ms.reloadDataGrid('indexRebuildLogTable');
                        },
                        error: function (d) {
                            parent.layer.closeAll("loading");
                            parent.layer.msg(d.msg, {icon: 2});
                        }
                    });
                });
            });
            // 重建会员索引
            $('#rebuildUserIndex').click(function () {
                parent.layer.confirm('您确定要重建会员索引吗？', {
                    title: false,
                    btn: ['确定', '关闭']
                }, function (index) {
                    parent.layer.load(2, {shade: 0.01});
                    $.ajax({
                        type: "post",
                        url: "/system/index/rebuild/user",
                        dataType: 'json',
                        success: function (d) {
                            parent.layer.closeAll("loading");
                            if (d.status == 200) {
                                parent.layer.msg('操作成功！', {icon: 1});
                            } else {
                                parent.layer.msg(d.msg, {icon: 2});
                            }
                            Ms.reloadDataGrid('indexRebuildLogTable');
                        },
                        error: function (d) {
                            parent.layer.closeAll("loading");
                            parent.layer.msg(d.msg, {icon: 2});
                        }
                    });
                });
            });
            // 重建订单索引
            $('#rebuildOrderIndex').click(function () {
                parent.layer.confirm('您确定要重建订单索引吗？', {
                    title: false,
                    btn: ['确定', '关闭']
                }, function (index) {
                    parent.layer.load(2, {shade: 0.01});
                    $.ajax({
                        type: "post",
                        url: "/system/index/rebuild/order",
                        dataType: 'json',
                        success: function (d) {
                            parent.layer.closeAll("loading");
                            if (d.status == 200) {
                                parent.layer.msg('操作成功！', {icon: 1});
                            } else {
                                parent.layer.msg(d.msg, {icon: 2});
                            }
                            Ms.reloadDataGrid('indexRebuildLogTable');
                        },
                        error: function (d) {
                            parent.layer.closeAll("loading");
                            parent.layer.msg(d.msg, {icon: 2});
                        }
                    });
                });
            });
            // 重建收集器商品索引
            $('#rebuildCollectorItemIndex').click(function () {
                parent.layer.confirm('您确定要重建收集器商品索引吗？', {
                    title: false,
                    btn: ['确定', '关闭']
                }, function (index) {
                    parent.layer.load(2, {shade: 0.01});
                    $.ajax({
                        type: "post",
                        url: "/system/index/rebuild/collector/item",
                        dataType: 'json',
                        success: function (d) {
                            parent.layer.closeAll("loading");
                            if (d.status == 200) {
                                parent.layer.msg('操作成功！', {icon: 1});
                            } else {
                                parent.layer.msg(d.msg, {icon: 2});
                            }
                            Ms.reloadDataGrid('indexRebuildLogTable');
                        },
                        error: function (d) {
                            parent.layer.closeAll("loading");
                            parent.layer.msg(d.msg, {icon: 2});
                        }
                    });
                });
            });
            // 重建收集器商品采集索引
            $('#rebuildCollectorItemCollectionIndex').click(function () {
                parent.layer.confirm('您确定要重建收集器商品采集索引吗？', {
                    title: false,
                    btn: ['确定', '关闭']
                }, function (index) {
                    parent.layer.load(2, {shade: 0.01});
                    $.ajax({
                        type: "post",
                        url: "/system/index/rebuild/collector/item/collection",
                        dataType: 'json',
                        success: function (d) {
                            parent.layer.closeAll("loading");
                            if (d.status == 200) {
                                parent.layer.msg('操作成功！', {icon: 1});
                            } else {
                                parent.layer.msg(d.msg, {icon: 2});
                            }
                            Ms.reloadDataGrid('indexRebuildLogTable');
                        },
                        error: function (d) {
                            parent.layer.closeAll("loading");
                            parent.layer.msg(d.msg, {icon: 2});
                        }
                    });
                });
            });
            // 重建收集器商品价格索引
            $('#rebuildCollectorItemPriceIndex').click(function () {
                parent.layer.confirm('您确定要重建收集器商品价格索引吗？', {
                    title: false,
                    btn: ['确定', '关闭']
                }, function (index) {
                    parent.layer.load(2, {shade: 0.01});
                    $.ajax({
                        type: "post",
                        url: "/system/index/rebuild/collector/item/price",
                        dataType: 'json',
                        success: function (d) {
                            parent.layer.closeAll("loading");
                            if (d.status == 200) {
                                parent.layer.msg('操作成功！', {icon: 1});
                            } else {
                                parent.layer.msg(d.msg, {icon: 2});
                            }
                            Ms.reloadDataGrid('indexRebuildLogTable');
                        },
                        error: function (d) {
                            parent.layer.closeAll("loading");
                            parent.layer.msg(d.msg, {icon: 2});
                        }
                    });
                });
            });
            // 重建收集器商品报关索引
            $('#rebuildCollectorItemCustomsIndex').click(function () {
                parent.layer.confirm('您确定要重建收集器商品报关索引吗？', {
                    title: false,
                    btn: ['确定', '关闭']
                }, function (index) {
                    parent.layer.load(2, {shade: 0.01});
                    $.ajax({
                        type: "post",
                        url: "/system/index/rebuild/collector/item/customs",
                        dataType: 'json',
                        success: function (d) {
                            parent.layer.closeAll("loading");
                            if (d.status == 200) {
                                parent.layer.msg('操作成功！', {icon: 1});
                            } else {
                                parent.layer.msg(d.msg, {icon: 2});
                            }
                            Ms.reloadDataGrid('indexRebuildLogTable');
                        },
                        error: function (d) {
                            parent.layer.closeAll("loading");
                            parent.layer.msg(d.msg, {icon: 2});
                        }
                    });
                });
            });
            // 重建收集器商品仓库索引
            $('#rebuildCollectorItemWarehouseIndex').click(function () {
                parent.layer.confirm('您确定要重建收集器商品仓库索引吗？', {
                    title: false,
                    btn: ['确定', '关闭']
                }, function (index) {
                    parent.layer.load(2, {shade: 0.01});
                    $.ajax({
                        type: "post",
                        url: "/system/index/rebuild/collector/item/warehouse",
                        dataType: 'json',
                        success: function (d) {
                            parent.layer.closeAll("loading");
                            if (d.status == 200) {
                                parent.layer.msg('操作成功！', {icon: 1});
                            } else {
                                parent.layer.msg(d.msg, {icon: 2});
                            }
                            Ms.reloadDataGrid('indexRebuildLogTable');
                        },
                        error: function (d) {
                            parent.layer.closeAll("loading");
                            parent.layer.msg(d.msg, {icon: 2});
                        }
                    });
                });
            });
            // 重建进货单索引
            $('#rebuildCartIndex').click(function () {
                parent.layer.confirm('您确定要重建进货单索引吗？', {
                    title: false,
                    btn: ['确定', '关闭']
                }, function (index) {
                    parent.layer.load(2, {shade: 0.01});
                    $.ajax({
                        type: "post",
                        url: "/system/index/rebuild/cart",
                        dataType: 'json',
                        success: function (d) {
                            parent.layer.closeAll("loading");
                            if (d.status == 200) {
                                parent.layer.msg('操作成功！', {icon: 1});
                            } else {
                                parent.layer.msg(d.msg, {icon: 2});
                            }
                            Ms.reloadDataGrid('indexRebuildLogTable');
                        },
                        error: function (d) {
                            parent.layer.closeAll("loading");
                            parent.layer.msg(d.msg, {icon: 2});
                        }
                    });
                });
            });

            // 重建售后订单索引
            $('#rebuildReturnOrderIndex').click(function () {
                parent.layer.confirm('您确定要重建售后订单索引吗？', {
                    title: false,
                    btn: ['确定', '关闭']
                }, function (index) {
                    parent.layer.load(2, {shade: 0.01});
                    $.ajax({
                        type: "post",
                        url: "/system/index/rebuild/return-order",
                        dataType: 'json',
                        success: function (d) {
                            parent.layer.closeAll("loading");
                            if (d.status == 200) {
                                parent.layer.msg('操作成功！', {icon: 1});
                            } else {
                                parent.layer.msg(d.msg, {icon: 2});
                            }
                            Ms.reloadDataGrid('indexRebuildLogTable');
                        },
                        error: function (d) {
                            parent.layer.closeAll("loading");
                            parent.layer.msg(d.msg, {icon: 2});
                        }
                    });
                });
            });
            // 重建锁库申请索引
            $('#rebuildStockLockApplyIndex').click(function () {
                parent.layer.confirm('您确定要重建锁库申请索引吗？', {
                    title: false,
                    btn: ['确定', '关闭']
                }, function (index) {
                    parent.layer.load(2, {shade: 0.01});
                    $.ajax({
                        type: "post",
                        url: "/system/index/rebuild/stockLock/apply",
                        dataType: 'json',
                        success: function (d) {
                            parent.layer.closeAll("loading");
                            if (d.status == 200) {
                                parent.layer.msg('操作成功！', {icon: 1});
                            } else {
                                parent.layer.msg(d.msg, {icon: 2});
                            }
                            Ms.reloadDataGrid('indexRebuildLogTable');
                        },
                        error: function (d) {
                            parent.layer.closeAll("loading");
                            parent.layer.msg(d.msg, {icon: 2});
                        }
                    });
                });
            });
            // 重建锁库申请详情索引
            $('#rebuildStockLockApplyDetailIndex').click(function () {
                parent.layer.confirm('您确定要重建锁库申请详情索引吗？', {
                    title: false,
                    btn: ['确定', '关闭']
                }, function (index) {
                    parent.layer.load(2, {shade: 0.01});
                    $.ajax({
                        type: "post",
                        url: "/system/index/rebuild/stockLock/apply/detail",
                        dataType: 'json',
                        success: function (d) {
                            parent.layer.closeAll("loading");
                            if (d.status == 200) {
                                parent.layer.msg('操作成功！', {icon: 1});
                            } else {
                                parent.layer.msg(d.msg, {icon: 2});
                            }
                            Ms.reloadDataGrid('indexRebuildLogTable');
                        },
                        error: function (d) {
                            parent.layer.closeAll("loading");
                            parent.layer.msg(d.msg, {icon: 2});
                        }
                    });
                });
            });
            // 重建锁库库存索引
            $('#rebuildStockLockIndex').click(function () {
                parent.layer.confirm('您确定要重建锁库库存索引吗？', {
                    title: false,
                    btn: ['确定', '关闭']
                }, function (index) {
                    parent.layer.load(2, {shade: 0.01});
                    $.ajax({
                        type: "post",
                        url: "/system/index/rebuild/stockLock",
                        dataType: 'json',
                        success: function (d) {
                            parent.layer.closeAll("loading");
                            if (d.status == 200) {
                                parent.layer.msg('操作成功！', {icon: 1});
                            } else {
                                parent.layer.msg(d.msg, {icon: 2});
                            }
                            Ms.reloadDataGrid('indexRebuildLogTable');
                        },
                        error: function (d) {
                            parent.layer.closeAll("loading");
                            parent.layer.msg(d.msg, {icon: 2});
                        }
                    });
                });
            });
            // 重建锁库库存详情索引
            $('#rebuildStockLockDetailIndex').click(function () {
                parent.layer.confirm('您确定要重建锁库库存详情索引吗？', {
                    title: false,
                    btn: ['确定', '关闭']
                }, function (index) {
                    parent.layer.load(2, {shade: 0.01});
                    $.ajax({
                        type: "post",
                        url: "/system/index/rebuild/stockLock/detail",
                        dataType: 'json',
                        success: function (d) {
                            parent.layer.closeAll("loading");
                            if (d.status == 200) {
                                parent.layer.msg('操作成功！', {icon: 1});
                            } else {
                                parent.layer.msg(d.msg, {icon: 2});
                            }
                            Ms.reloadDataGrid('indexRebuildLogTable');
                        },
                        error: function (d) {
                            parent.layer.closeAll("loading");
                            parent.layer.msg(d.msg, {icon: 2});
                        }
                    });
                });
            });
        });
        // 重建锁库库存详情-未支付索引
        $('#rebuildStockLockDetailNoPayIndex').click(function () {
            parent.layer.confirm('您确定要重建锁库库存详情-未支付索引吗？', {
                title: false,
                btn: ['确定', '关闭']
            }, function (index) {
                parent.layer.load(2, {shade: 0.01});
                $.ajax({
                    type: "post",
                    url: "/system/index/rebuild/stockLock/detail/nopay",
                    dataType: 'json',
                    success: function (d) {
                        parent.layer.closeAll("loading");
                        if (d.status == 200) {
                            parent.layer.msg('操作成功！', {icon: 1});
                        } else {
                            parent.layer.msg(d.msg, {icon: 2});
                        }
                        Ms.reloadDataGrid('indexRebuildLogTable');
                    },
                    error: function (d) {
                        parent.layer.closeAll("loading");
                        parent.layer.msg(d.msg, {icon: 2});
                    }
                });
            });
        });
        // 重建物流商品表格索引
        $('#rebuildLogisticsItemTableIndex').click(function () {
            parent.layer.confirm('您确定要重建物流商品表格索引吗？', {
                title: false,
                btn: ['确定', '关闭']
            }, function (index) {
                parent.layer.load(2, {shade: 0.01});
                $.ajax({
                    type: "post",
                    url: "/system/index/rebuild/logisticsItemTable",
                    dataType: 'json',
                    success: function (d) {
                        parent.layer.closeAll("loading");
                        if (d.status == 200) {
                            parent.layer.msg('操作成功！', {icon: 1});
                        } else {
                            parent.layer.msg(d.msg, {icon: 2});
                        }
                        Ms.reloadDataGrid('indexRebuildLogTable');
                    },
                    error: function (d) {
                        parent.layer.closeAll("loading");
                        parent.layer.msg(d.msg, {icon: 2});
                    }
                });
            });
        });
        var viewRebuildDetail = function (idxCode) {
            parent.layer.open({
                type: 2,
                title: '查看重建详情',
                area: ['600px', '514px'],
                btn: ['关闭'],
                resize: false,
                content: ['/system/index/rebuild/detail/' + idxCode, 'no'],
                yes: function (index) {
                    Ms.clearGridSelections('indexRebuildLogTable');
                    parent.layer.close(index);
                },
                cancel: function () {
                    Ms.clearGridSelections('indexRebuildLogTable');
                }
            });
        };
        var updateRebuildFail = function (idxCode) {
            parent.layer.confirm('您确定要重建失败吗？', {
                title: false,
                btn: ['确定', '关闭'],
                btn2: function () {
                    Ms.clearGridSelections('indexRebuildLogTable');
                },
                cancel: function () {
                    Ms.clearGridSelections('indexRebuildLogTable');
                }
            }, function (index) {
                parent.layer.load(2, {shade: 0.01});
                $.ajax({
                    type: "post",
                    url: "/system/index/rebuild/update/fail",
                    data: {
                        idxCode: idxCode
                    },
                    dataType: 'json',
                    success: function (d) {
                        if (d.status == 200) {
                            parent.layer.msg('操作成功！', {icon: 1});
                        } else {
                            parent.layer.msg(d.msg, {icon: 2});
                        }
                        parent.layer.closeAll("loading");
                        $('#indexRebuildLogTable').datagrid('reload', {});
                    },
                    error: function (d) {
                        parent.layer.closeAll("loading");
                        parent.layer.msg(d.msg, {icon: 2});
                    }
                });
            });
        };
    </script>
</section>
<jsp:include page="../../footer.jsp"/>