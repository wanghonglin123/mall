/* 定义数组，存储省份信息  */
var pro = ["北京", "上海", "天津", "重庆", "浙江", "江苏", "广东", "福建", "湖南", "湖北", "辽宁", 
"吉林", "黑龙江", "河北", "河南", "山东", "陕西", "甘肃", "新疆", "青海", "山西", "四川", 
"贵州", "安徽", "江西", "云南", "内蒙古", "西藏", "广西", "宁夏", "海南", "香港", "澳门", "台湾"];
/* 定义数组,存储城市信息  */
var beijing = ["北京"]; 
var shanghai = ["上海"]; 
var tianjing = ["天津"]; 
var chongqing = ["重庆"];
var jiangsu = ["南京", "无锡", "常州", "徐州", "苏州", "南通", "连云港", "淮安", "扬州", "盐城", "镇江", "泰州", "宿迁","常熟", "张家港", "太仓", "昆山", "吴江", "如皋", "通州", "海门", "启东", "东台", "大丰", "高邮", "江都", "仪征", "丹阳", "扬中", "句容", "泰兴", "姜堰", "靖江", "兴化"]; 
var zhejiang = ["杭州", "宁波", "温州", "嘉兴", "湖州", "绍兴", "金华", "衢州", "舟山", "台州", "丽水","建德", "富阳", "临安", "余姚", "慈溪", "奉化", "瑞安", "乐清", "海宁", "平湖", "桐乡", "诸暨", "上虞","嵊州", "兰溪", "义乌", "东阳", "永康", "江山", "临海", "温岭", "龙泉"]; 
var guangdong = ["广州", "深圳", "汕头", "惠州", "珠海", "揭阳", "佛山", "河源", "阳江", "茂名", "湛江", "梅州", "肇庆", "韶关", "江门", "汕尾", "清远", "东莞", "中山", "潮州", "云浮","增城", "从化", "乐昌", "南雄", "台山", "开平", "鹤山", "恩平", "廉江", "雷州", "吴川", "高州", "化州", "高要", "四会", "兴宁", "陆丰", "阳春", "英德", "连州", "普宁", "罗定"]; 
var fujiang = ["福州", "厦门", "莆田", "三明", "泉州", "漳州", "南平", "龙岩", "宁德", "福清", "长乐", "永安", "石狮", "晋江", "南安", "龙海", "邵武", "建瓯", "建阳", "漳平", "福安", "福鼎"]; 
var hunan = ["长沙", "株洲", "湘潭", "衡阳", "邵阳", "岳阳", "常德", "张家界", "益阳", "郴州", "永州", "怀化", "娄底", "浏阳","醴陵", "湘乡", "韶山", "耒阳", "常宁", "武冈", "临湘", "汨罗", "津市", "沅江", "资兴", "洪江", "冷水江", "涟源", "吉首"]; 
var hubei = ["武汉", "襄阳", "黄石", "十堰", "宜昌", "鄂州", "荆门", "孝感", "荆州", "黄冈", "咸宁", "随州", "大冶", "丹江口", "洪湖", "石首", "松滋", "宜都", "当阳", "枝江", "老河口", "枣阳", "宜城", "钟祥", "应城", "安陆", "汉川", "麻城", "武穴", "赤壁", "广水", "仙桃", "天门", "潜江", "恩施", "利川"]; 
var liaoning = ["沈阳", "大连", "鞍山", "抚顺", "本溪", "丹东", "锦州", "营口", "阜新", "辽阳", "盘锦", "铁岭", "朝阳", "葫芦岛","新民", "瓦房店", "普兰", "庄河", "海城", "东港", "凤城", "凌海", "北镇", "大石桥", "盖州", "灯塔", "调兵山", "开源", "北票", "兴城"]; 
var jilin = ["长春", "吉林", "四平", "辽源", "通化", "白山", "松原", "白城","九台", "榆树", "德惠", "舒兰", "桦甸", "蛟河", "磐石", "公主岭","双辽", "梅河口", "集安", "临江", "大安", "洮南", "延吉", "图们","敦化", "龙井", "珲春", "和龙"]; 
var heilongjiang = ["哈尔滨", "大庆","齐齐哈尔", "佳木斯", "鸡西", "鹤岗", "鹤岗", "双鸭山", "牡丹江",  "伊春",  "七台河", "黑河", "绥化", "五常", "双城", "尚志", "纳河", "虎林", "密山", "铁力", "同江", "富锦", "绥芬河", "海林", "宁安", "穆林", "五大连池", "肇东", "海伦", "安达"];  
var hebei = ["石家庄", "唐山", "邯郸", "秦皇岛", "保定", "张家口", "承德", "廊坊", "沧州", "衡水", "邢台","辛集", "藁城", "晋州", "新乐", "鹿泉", "遵化", "迁安", "武安", "任丘", "黄骅", "河间", "霸州", "三河", "冀州", "深州"]; 
var henan = ["郑州", "洛阳", "商丘", "安阳", "南阳", "开封", "平顶山", "焦作", "新乡", "鹤壁", "许昌", "漯河", "三门峡", "信阳", "周口", "驻马店", "济源","巩义", "新郑", "新密", "登封", "荥阳", "偃师", "汝州", "舞钢", "林州", "卫辉", "辉县", "沁阳", "孟州", "禹州", "长葛", "义马", "灵宝", "邓州", "永城", "项城", "济源"]; 
var shandong = ["济南", "青岛", "淄博", "枣庄", "东营", "烟台", "潍坊", "济宁", "泰安", "威海", "日照", "莱芜", "临沂", "德州", "聊城", "菏泽", "滨州","章丘", "胶南", "胶州", "平度", "莱西", "即墨", "滕州", "龙口", "莱阳", "莱州", "招远", "蓬莱", "栖霞", "海阳", "青州", "诸城", "安丘", "高密", "昌邑", "衮州", "曲阜", "邹城", "乳山", "文登", "荣成", "乐陵", "临清", "禹城"]; 
var shangxi = ["西安", "宝鸡", "咸阳", "渭南", "铜川", "延安", "榆林", "汉中", "安康", "商洛","兴平", "韩城", "华阴"]; 
var gansu = ["兰州", "嘉峪关", "金昌", "白银", "天水", "武威", "张掖", "酒泉", "平凉", "庆阳", "定西", "陇南", "临夏", "合作", "敦煌", "玉门"]; 
var qinghai = ["西宁", "格尔木", "德令哈"];  
var shanxi = ["太原", "大同", "阳泉", "长治", "晋城", "朔州", "晋中", "运城", "忻州", "临汾", "吕梁","古交", "潞城", "高平", "介休", "永济", "河津", "原平", "侯马", "霍州", "孝义", "汾阳"]; 
var sichuan = ["成都", "自贡", "攀枝花", "泸州", "德阳", "绵阳", "广元", "遂宁", "内江", "乐山", "南充", "眉山", "宜宾", "广安", "达州", "雅安", "资阳", "都江堰", "彭州", "邛崃", "崇州", "广汉", "什邡", "绵竹", "江油", "峨眉山", "阆中", "华蓥", "万源", "简阳", "西昌"]; 
var guizhou = ["贵阳", "六盘水", "遵义", "安顺", "清镇", "赤水", "仁怀", "铜仁", "毕节", "兴义", "凯里", "都匀", "福泉"]; 
var anhui = ["合肥", "蚌埠", "芜湖", "淮南", "亳州", "阜阳", "淮北", "宿州", "滁州", "安庆", "巢湖", "马鞍山", "宣城", "铜陵", "黄山", "池州", "界首", "天长", "明光", "桐城", "宁国"]; 
var jiangxi = ["南昌", "九江", "景德镇", "萍乡", "新余", "鹰潭", "赣州", "宜春", "上饶", "吉安", "抚州","乐平", "瑞昌", "贵溪", "瑞金", "南康", "井冈山", "丰城", "樟树", "高安", "德兴"]; 
var hainan = ["海口", "三亚","琼海", "文昌","万宁", "五指山","儋州", "东方"]; 
var yunnan = ["昆明", "曲靖", "玉溪", "保山", "昭通", "丽江", "普洱", "临沧", "安宁", "宣威", "个旧", "开远", "景洪", "楚雄", "大理", "潞西", "瑞丽"];
var taiwan =["台北","台中", "基隆", "高雄", "新竹", "嘉义", "板桥", "宜兰", "竹北", "桃园", "苗栗", "丰原", "彰化", "南投", "太保", "斗六", "新营", "凤山", "屏东", "台东", "花莲", "马公"];
var xinjiang = ["阿克苏", "阿拉尔", "阿勒泰", "阿图什", "阜康", "乌鲁木齐", "奎屯", "米泉", "石河子", "昌吉", "吐鲁番", "库尔勒", "喀什", "伊宁", "克拉玛依", "塔城", "图木舒克", "哈密", "乌苏", "五家渠", "巴州", "伊犁", "和田", "博乐"]; 
var neimenggu = ["呼和浩特", "包头", "乌海", "赤峰", "通辽", "鄂尔多斯", "呼伦贝尔", "巴彦淖尔", "乌兰察布"]; 
var guangxi = ["南宁", "柳州", "桂林", "梧州", "北海", "防城港", "钦州", "贵港", "玉林", "百色", "贺州", "河池", "崇左", "来宾", "北流", "桂平", "青州"]; 
var xizang = ["拉萨", "昌都地区", "林芝地区", "山南地区", "日喀则地区", "那曲地区", "阿里地区"]; 
var ningxia = ["银川", "石嘴山", "吴忠", "固原", "中卫"];  
var xianggang = ["香港"]; 
var aomen = ["澳门"];  
/* 调用函数 */
setProvince();  
function setProvince() { 
    var input, modelVal; 
    $("#selProvince").html("");
    for (var i = 0, len = pro.length; i < len; i++) { 
        modelVal = pro[i]; 
        input = '<label class="province-l"><input class="province-c" type="checkbox" name="province" value="'+modelVal+'"/>'+'<span class="province-s">'+modelVal+'</span></label>';
        $("#selProvince").append(input); 
    }  
} 
/* 省份选中事件 */
$("#selProvince").on('click','input',function(){  /* 点击的事件  与$("#selProvince").click(function(){}) 有区别 */
    $("#selCity").empty(); 
    var province="";
    $("#selProvince input[name='province']:checked").each(function(){
        if(province!= ''){
            province+='/';
        }
        province+=$(this).val();
    });
    if(province==""){
    }else{
        var provinces=province.split('/');
        for(var i in provinces){
            if(i>=0){
                $("#selCity").append('<br><div class="c-interval">'+provinces[i]+'</div>');
            }
            setCity(provinces[i]);
        }
    }
})
/* 根据选中的省份获取对应的城市 */
function setCity(province) {
    var proCity, input, modelVal; 
    switch (province) { 
    case "北京": 
    proCity = beijing; 
    break; 
    case "上海": 
    proCity = shanghai; 
    break; 
    case "天津": 
    proCity = tianjing; 
    break; 
    case "重庆": 
    proCity = chongqing; 
    break; 
    case "浙江": 
    proCity = zhejiang; 
    break; 
    case "江苏": 
    proCity = jiangsu; 
    break; 
    case "广东": 
    proCity = guangdong; 
    break; 
    case "福建": 
    proCity = fujiang; 
    break; 
    case "湖南": 
    proCity = hunan; 
    break; 
    case "湖北": 
    proCity = hubei; 
    break; 
    case "辽宁": 
    proCity = liaoning; 
    break; 
    case "吉林": 
    proCity = jilin; 
    break; 
    case "黑龙江": 
    proCity = heilongjiang; 
    break; 
    case "河北": 
    proCity = hebei; 
    break; 
    case "河南": 
    proCity = henan; 
    break; 
    case "山东": 
    proCity = shandong; 
    break; 
    case "陕西": 
    proCity = shangxi; 
    break; 
    case "甘肃": 
    proCity = gansu; 
    break; 
    case "新疆": 
    proCity = xinjiang; 
    break; 
    case "青海": 
    proCity = qinghai; 
    break; 
    case "山西": 
    proCity = shanxi; 
    break; 
    case "四川": 
    proCity = sichuan; 
    break; 
    case "贵州": 
    proCity = guizhou; 
    break; 
    case "安徽": 
    proCity = anhui; 
    break; 
    case "江西": 
    proCity = jiangxi; 
    break; 
    case "云南": 
    proCity = yunnan; 
    break; 
    case "内蒙古": 
    proCity = neimenggu; 
    break; 
    case "西藏": 
    proCity = xizang; 
    break; 
    case "广西": 
    proCity = guangxi; 
    break;  
    case "宁夏": 
    proCity = ningxia; 
    break; 
    case "海南": 
    proCity = hainan; 
    break; 
    case "香港": 
    proCity = xianggang; 
    break; 
    case "澳门": 
    proCity = aomen; 
    break; 
    case "台湾": 
    proCity = taiwan; 
    break; 
    } 
    for (var i = 0, len = proCity.length; i < len; i++) { 
        modelVal = proCity[i]; 
        input = '<label class="city-l" style="display:inline-block"><input class="city-c" type="checkbox" name="city" value="'+modelVal+'"/>'+'<span class="city-s">'+modelVal+'</span></label>';
        $("#selCity").append(input); 
    } 
}