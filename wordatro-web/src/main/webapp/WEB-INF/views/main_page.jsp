<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/mytags.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>Steam Helper</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="keywords" content="Steam Helper">
    <meta name="description" content="Steam Helper">
    <link rel="shortcut icon" href="${ctx}/static/img/steam_logo.png">

    <link rel="stylesheet" href="${ctx}/static/layui/css/layui.css">
    <link rel="stylesheet" href="${ctx}/static/css/global.css">

    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/common.css" media="all">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/personal.css" media="all">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/fontcss.css">

    <script src="${ctx}/static/layui/layui.js"></script>
	<style type="text/css">
		.item-img a.img{ display: block; width: 76px; height: 76px; margin: 0 auto; margin-bottom: 15px;}
		.layui-table tr { height: 100px; }
	</style>

<body>
<div class="larry-grid layui-anim layui-anim-upbit larryTheme-A" >
    <div class="larry-personal" >
        <div class="layui-tab" >
            <blockquote class="layui-elem-quote mylog-info-tit">
				<form class="layui-form" id="cusSearchForm">
					<div class="layui-form-item">
						<label class="layui-form-label">现有字母</label>
						<div class="layui-inline">
							<input type="text" id="letters" name="letters" class="layui-input" maxlength="20" value="" placeholder="请输入字母">
						</div>
					</div>
<%--					<div class="layui-form-item">--%>
<%--						<div class="layui-inline">--%>
<%--							<table class="inputTable" id="inputTable" lay-filter="inputTable"></table>--%>
<%--						</div>--%>
<%--					</div>--%>
					<div class="layui-form-item">
						<div class="layui-inline">
<%--							<div class="layui-form-item" style="margin-bottom:3px;">--%>
<%--								<a class="layui-btn layui-btn-normal addLetter_btn" lay-filter="addLetterFilter"><i class="layui-icon  layui-icon-search"></i>增加字母</a>--%>
<%--							</div>--%>
							<div class="layui-form-item" style="margin-bottom:3px;">
								<a class="layui-btn layui-btn-normal searchList_btn" lay-submit lay-filter="searchFilter"><i class="layui-icon  layui-icon-search"></i>计算</a>
							</div>
						</div>
					</div>
				</form>
            </blockquote>
            <div class="larry-separate"></div>
            <div class="layui-tab-item layui-show" style="padding: 10px 15px;">
                <table class="resTableList" id="resTableList" lay-filter="resTableId"></table>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    layui.config({
        base : "${ctx}/static/js/"
    }).use(['form', 'table', 'commCms', 'bodyTab'], function () {
        var $ = layui.$,
                form = layui.form,
                table = layui.table,
                common = layui.commCms;

		const tableIns = table.render({
			elem: '#inputTable',
			data: [{
				word: '',
				point: '',
				mul: '',
				special: 0,
			}],
			width: 1200,
			skin: 'row',
			page: false,
			cols: [[
				{field: 'word', title: '字母', width: '20%', align: 'center', edit: 'text'},
				{field: 'point', title: 'points', width: '20%', align: 'center', edit: 'text'},
				{field: 'mul', title: 'multis', width: '20%', align: 'center', edit: 'text'},
				{field: 'special', title: '特殊', width: '20%', align: 'center', edit: 'text'},
				{title: '操作', width: '20%', align: 'center', toolbar: '#inputBar'}
			]],
		});

		// 添加空白行
		$(".addLetter_btn").click(function(){
			// 获取当前表格的所有数据（缓存）
			var oldData = table.cache['inputTable'] || [];
			var newRow = {
				letter: '',
				point: '',
				mul: '',
				special: 0,
			};
			var newData = oldData.slice();
			newData.push(newRow);
			// 重新加载表格
			tableIns.reload({
				data: newData,
				page: false
			});
		});

		function searchList() {
			var postData = $("#letters").val();
			// 先请求后端拿到结果
			$.ajax({
				url: '${ctx}/main/calc.do',
				type: 'POST',
				contentType: 'application/json;charset=utf-8',
				data: JSON.stringify({strValue: postData}),
				dataType: 'json',
				success: function (res) {
					// 用 res.data 渲染表格，不再用 url
					table.render({
						elem: '#resTableList',
						id: 'resTableId',
						data: res.data,
						loading: true,
						skin: 'row',
						even: 'true',
						size: 'sm',
						cols: [[
							{field:'', title: '序号', width: '10%', align:'center', templet: '#resNumTpl'},
							{field:'word', title: '单词', width: '30%', align:'center'},
							{field:'quantity', title: '用字母个数', width: '30%', align:'center'},
							// {field:'score', title: '得分', width: '30%', align:'center'},
							{fixed:'right', title: '操作', width: '30%', align:'center', toolbar: '#resBar'}
						]]
					});
				},
				error: function (xhr) {
					layer.msg('请求失败: ' + xhr.msg);
				}
			});
		}
		<%--function searchList() {--%>
		<%--	var postData = table.cache['inputTable'];  // 这是数组 [{word:'a'},...]--%>
		<%--	// 先请求后端拿到结果--%>
		<%--	$.ajax({--%>
		<%--		url: '${ctx}/main/calc.do',--%>
		<%--		type: 'POST',--%>
		<%--		contentType: 'application/json;charset=utf-8',--%>
		<%--		data: JSON.stringify(postData),--%>
		<%--		dataType: 'json',--%>
		<%--		success: function (res) {--%>
		<%--			// 用 res.data 渲染表格，不再用 url--%>
		<%--			table.render({--%>
		<%--				elem: '#resTableList',--%>
		<%--				id: 'resTableId',--%>
		<%--				data: res.data,--%>
		<%--				loading: true,--%>
		<%--				skin: 'row',--%>
		<%--				even: 'true',--%>
		<%--				size: 'lg',--%>
		<%--				cols: [[--%>
		<%--					{field:'', title: '序号', width: '10%', align:'center', templet: '#resNumTpl'},--%>
		<%--					{field:'word', title: '单词', width: '30%', align:'center'},--%>
		<%--					{field:'score', title: '得分', width: '30%', align:'center'},--%>
		<%--					{fixed:'right', title: '操作', width: '30%', align:'center', toolbar: '#resBar'}--%>
		<%--				]]--%>
		<%--			});--%>
		<%--		},--%>
		<%--		error: function (xhr) {--%>
		<%--			layer.msg('请求失败: ' + xhr.msg);--%>
		<%--		}--%>
		<%--	});--%>
		<%--}--%>

		/**查询*/
		$(".searchList_btn").click(function(){
			form.on('submit(searchFilter)', function (data) {
				searchList();
			});
		});


		/**监听工具条*/
		table.on('tool(inputTable)', function(obj) {
			const layEvent = obj.event;
			switch (layEvent) {
				case 'res_del':
					table.cache['inputTable'].splice(obj.index, 1);
					table.renderData('inputTable');
					break;
				default:
					break;
			}
		});
		table.on('tool(resTableId)', function(obj) {
			var data = obj.data;
			var layEvent = obj.event;
			var url;
			switch (layEvent) {
				case 'res_del':
					url = "${ctx}/main/del.do";
					var param = {id: data.id};
					layer.confirm('确定删除该单词:${data.word}?', {
						title: '系统提示',
						resize: false,
						offset: '50px',
						btn: ['确定', '取消'],
						btnAlign: 'c',
						anim:1,
						icon: 3
					}, function () {
						$.ajax({
							url : url,
							type : 'post',
							async: false,
							data : param,
							success : function(data) {
								if(data.code == "0000"){
									layer.msg(data.msg,{time: 3000,offset: "t",icon: 6});
									table.cache['resTableId'].splice(obj.index, 1);
									table.renderData('resTableId');
								}else{
									layer.msg(data.msg,{time: 3000,offset: "t",icon: 5});
								}
							},error:function(data){

							}
						});

					}, function () {

					})
					break;
				default:
					break;
			}
		});
	});
</script>



<!-- tpl-->
<script type="text/html" id="resNumTpl">
	{{= d.LAY_NUM}}
</script>

<!--工具条 -->
<script type="text/html" id="inputBar">
	<div class="layui-btn-group">
			<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="res_del"><i class="layui-icon  layui-icon-delete"></i>删除</a>
	</div>
</script>

<script type="text/html" id="resBar">
	<div class="layui-btn-group">
			<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="res_del"><i class="layui-icon  layui-icon-delete"></i>删除</a>
	</div>
</script>

</body>
</html>