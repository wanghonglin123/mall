<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<jsp:include page="../../header.jsp"/>

<div style="overflow: hidden;height: 414px;background: #ffffff;">
    <div class="winCont">
        <div class="winRow">
            <label class="brandName">索引名称：</label>
            <input class="brandTxt" type="text" value="${indexRebuildLog.aliasName}" readonly>
        </div>
        <div class="winRow">
            <label class="brandName">重建类型：</label>
            <input class="brandTxt" type="text" value="${indexRebuildLog.rebuildType}" readonly>
        </div>
        <div class="winRow">
            <label class="brandName">重建状态：</label>
            <input class="brandTxt" type="text" value="${indexRebuildLog.rebuildStatus}" readonly>
        </div>
        <div class="winRow">
            <label class="brandName">开始时间：</label>
            <input class="brandTxt" type="text" value="${indexRebuildLog.startTime}" readonly>
        </div>
        <div class="winRow">
            <label class="brandName">结束时间：</label>
            <input class="brandTxt" type="text" value="${indexRebuildLog.endTime}" readonly>
        </div>
        <div class="winRow">
            <label class="brandName">失败信息：</label>
            <textarea class="brandTxt" style="width: 300px;height: 60px;" readonly>${indexRebuildLog.remark}</textarea>
        </div>
    </div>
</div>

<jsp:include page="../../footer.jsp"/>