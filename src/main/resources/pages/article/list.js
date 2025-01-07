// 修改 admin 模块的路径配置
layui.config({
	base: '../../static/js/' // 设置模块基础路径
}).extend({
	admin: 'admin' , // 现在路径为 ../../static/js/admin.js
});

layui.use(['table', 'jquery', 'form', 'admin', 'laydate'], function() {
	var table = layui.table,
	$ = layui.jquery,
	form = layui.form,
	admin = layui.admin,
	laydate = layui.laydate;

// 表格初始化
table.render({
	elem: '#articleList',
	cellMinWidth: 80,
	cols: [[
		{type: 'checkbox'},
		{field: 'id', title: 'ID', sort: true},
		{field: 'title', title: '标题'},
		{field: 'author', title: '作者'},
		{field: 'publisher', title: '出版社'},
		{field: 'publishDate', title: '发布时间', sort: true},
		{field: 'kind', title: '文章种类', sort: true},
		{field: 'operate', title: '操作', toolbar: '#operateTpl', unresize: true}
	]],
	url: 'http://localhost:8080/article/select',
	method: 'get',
	page: true,
	response: {
		statusName: 'code',    // 规定状态码的字段名称
		statusCode: 200,       // 规定成功的状态码
		msgName: 'msg',        // 规定状态信息的字段名称
		countName: 'count',    // 规定数据总数的字段名称
		dataName: 'data'       // 规定数据列表的字段名称
	},
	parseData: function(res) { // 将原始数据解析成 table 组件所规定的数据
		return {
			"code": res.code,
			"msg": res.msg,
			"count": res.data.length, // 总条数
			"data": res.data          // 列表数据
		};
	},
	done: function(res, curr, count) {
		$('#dataCount').text(count || 0);  // 更新数据总数显示
	}
});

	// 初始化日期选择器
	laydate.render({
		elem: '#start'
	});

	laydate.render({
		elem: '#end'
	});

	// 搜索功能
	form.on('submit(sreach)', function(data) {
		table.reload('articleList', {
			where: data.field
		});
		return false;
	});

	// 监听工具条
	table.on('tool(articleList)', function(obj) {
		var data = obj.data;
		if (obj.event === 'del') {
			layer.confirm('确认要删除吗？', function(index) {
				obj.del();
				layer.close(index);
			});
		}
	});

	// 批量删除
	$('.demoTable .layui-btn').on('click', function() {
		var type = $(this).data('type');
		active[type] ? active[type].call(this) : '';
	});

	var active = {
		getCheckData: function() {
			var checkStatus = table.checkStatus('articleList'),
				data = checkStatus.data;
			if (data.length > 0) {
				layer.confirm('确认要删除吗？', function(index) {
					layer.msg('删除成功', {
						icon: 1
					});
					// 执行删除操作
				});
			} else {
				layer.msg("请先选择需要删除的文章！");
			}
		}
	};
	// 在table.render后添加
// 添加文章
// 	window.WeAdminShow = function(title, url, w, h) {
// 		if (title == null || title == '') {
// 			title = false;
// 		}
// 		if (url == null || url == '') {
// 			url = "404.html";
// 		}
// 		if (w == null || w == '') {
// 			w = 800;
// 		}
// 		if (h == null || h == '') {
// 			h = ($(window).height() - 50);
// 		}
// 		layer.open({
// 			type: 2,
// 			area: [w + 'px', h + 'px'],
// 			fix: false,
// 			maxmin: true,
// 			shadeClose: true,
// 			shade: 0.4,
// 			title: title,
// 			content: url,
// 			end: function() {
// 				// 刷新表格
// 				table.reload('articleList');
// 			}
// 		});
// 	}
});