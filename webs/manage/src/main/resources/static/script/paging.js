(function($, window, document, undefined) {
    /* 定义分页类 */
    function Paging(element, options) {
        this.element = element;
        /* 传入形参 */
        this.options = {
            pageNo: options.pageNo||1,
            totalPage: options.totalPage,
            totalSize:options.totalSize,
            callback:options.callback
        };
        /* 根据形参初始化分页html和css代码 */
        this.init();
    }
    /* 对Paging的实例对象添加公共的属性和方法 */
    Paging.prototype = {
        constructor: Paging,
        init: function() {
            this.creatHtml();
            this.bindEvent(parseInt(this.options.totalPage));
        },
        creatHtml: function() {
            var me = this;
            var content = "<div class='page-div-rel-just'><div class='page-div-rel-lose'>";
            var current = parseInt(me.options.pageNo);
            var total = parseInt(me.options.totalPage);
            var totalNum = parseInt(me.options.totalSize);
            if(current>1){
                content += "<a id='prePage' class='prePage'><i>&lt;</i>上一页</a>";
            }else {
                content += "<a id='no-prePage' class='prePage'><i>&lt;</i>上一页</a>";
            }
            /* 总页数大于6时候 */
            if(total > 6) {
                /* 当前页数小于5时显示省略号 */
                if(current < 5) {
                    for(var i = 1; i < 6; i++) {
                        if(current == i) {
                            content += "<a class='current'>" + i + "</a>";
                        } else {
                            content += "<a>" + i + "</a>";
                        }
                    }
                    content += "<span>...</span>";
                    content += "<a>"+total+"</a>";
                } else {
                    /* 判断页码在末尾的时候 */
                    if(current < total - 3) {
                        for(var i = current - 2; i < current + 3; i++) {
                            if(current == i) {
                                content += "<a class='current'>" + i + "</a>";
                            } else {
                                content += "<a>" + i + "</a>";
                            }
                        }
                        content += "<span>...</span>";
                        content += "<a>"+total+"</a>";
                        /* 页码在中间部分时候 */
                    } else {
                        content += "<a>1</a>";
                        content += "<span>...</span>";
                        for(var i = total - 4; i < total + 1; i++) {
                            if(current == i) {
                                content += "<a class='current'>" + i + "</a>";
                            } else {
                                content += "<a>" + i + "</a>";
                            }
                        }
                    }
                }
                /* 页面总数小于6的时候 */
            } else {
                for(var i = 1; i < total + 1; i++) {
                    if(current == i) {
                        content += "<a class='current'>" + i + "</a>";
                    } else {
                        content += "<a>" + i + "</a>";
                    }
                }
            }
            if(total>1 && current<total){
                content += "<a id='nextPage' class='nextPage'>下一页<i>&gt;</i></a>";
            }else {
                content += "<a id='no-nextPage' class='nextPage'>下一页<i>&gt;</i></a>";
            }
            content += "<span class='totalPages'> 共<em>"+total+"</em>页 </span><span>到第<input id='skiP-data' type='text' value="+ current +">页</span><a id='skip'>确定</a></div></div>";
            //content += "<span class='totalSize'> 共<em>"+totalNum+"</em>条记录 </span>";
            me.element.html(content);
        },
        /* 添加页面操作事件 */
        bindEvent: function(TpageNumber) {
            var me = this;
            me.element.off('click', 'a');
            me.element.on('click', 'a', function() {
                if(!$(this).hasClass('current')){
                    var num = $(this).html();
                    var id=$(this).attr("id");
                    if(id == "prePage") {
                        if(me.options.pageNo == 1) {
                            me.options.pageNo = 1;
                        } else {
                            me.options.pageNo = +me.options.pageNo - 1;
                        }
                    } else if(id == "nextPage") {
                        if(me.options.pageNo == me.options.totalPage) {
                            me.options.pageNo = me.options.totalPage;
                        } else {
                            me.options.pageNo = +me.options.pageNo + 1;
                        }
                    }else if(id == "skip") {
                        var skip_d=parseInt($('#skiP-data').val());
                        if(parseInt($(this).parent().find('.current').text()) - skip_d == 0){
                            return false;
                        }
                        if(skip_d!=me.options.pageNo && skip_d<=me.options.totalPage && skip_d>0){
                            me.options.pageNo = skip_d;
                        }else if(skip_d>TpageNumber) {
                            me.options.pageNo = 1;
                        }
                    }else if(id == 'no-prePage' || id == 'no-nextPage'){
                        return false;
                    }else{
                        me.options.pageNo = +num;
                    }
                    me.creatHtml();
                    if(me.options.callback) {
                        me.options.callback(me.options.pageNo);
                    }
                }
            });
            me.element.on('keyup','#skiP-data',function (e) {
                if(e.which==13){
                    me.element.find('#skip').click();
                }
            });
        }
    };
    /* 通过jQuery对象初始化分页对象 */
    $.fn.paging = function(options) {
        return new Paging($(this), options);
    }
})(jQuery, window, document);