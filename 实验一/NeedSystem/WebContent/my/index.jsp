<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>河北省重大需求征集图表显示</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/my/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/my/js/jsapi.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/my/js/corechart.js"></script>		
<script type="text/javascript" src="${pageContext.request.contextPath }/my/js/jquery.gvChart-1.0.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/my/js/jquery.ba-resize.min.js"></script>

<script type="text/javascript">
gvChartInit();
$(document).ready(function(){
	$('#myTable5').gvChart({
		chartType: 'PieChart',
		gvSettings: {
			vAxis: {title: 'No of players'},
			hAxis: {title: 'Month'},
			width: 600,
			height: 350
		}
	});
});
</script>

<script type="text/javascript">
gvChartInit();
$(document).ready(function(){
		$('#myTable1').gvChart({
			chartType: 'PieChart',
			gvSettings: {
			vAxis: {title: 'No of players'},
			hAxis: {title: 'Month'},
			width: 600,
			height: 350
		}
	});
});
</script>

</head>


<body>

	<div style="width:600px;margin:0 auto;">

	   <table id='myTable5'>
			<caption>审核情况分布</caption>
			<thead>
				<tr>
					<th></th>
					<th>未审核</th>
					<th>已审核</th>
					
				</tr>
			</thead>
			<tbody>
				<tr>
					<th>${tongjibean.zong}</th>
					<td>${tongjibean.weishenhe}</td>
					<td>${tongjibean.shenhe }</td>
				</tr>
			</tbody>
		</table>  

	   <table id='myTable1'>
			<caption>审核结果分布</caption>
			<thead>
				<tr>
					<th></th>
					<th>通过</th>
					<th>退回</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th>${tongjibean.shenhe }</th>
					<td>${tongjibean.tongguo}</td>
					<td>${tongjibean.tuihui}</td>
					</tr>
			</tbody>
		</table>  
	
	</div>

</body>
</html>
<div style="display:none">
	<script type="text/javascript">
	var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
	document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3F6f798e51a1cd93937ee8293eece39b1a' type='text/javascript'%3E%3C/script%3E"));
	</script>
	<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_5718743'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s9.cnzz.com/stat.php%3Fid%3D5718743%26show%3Dpic2' type='text/javascript'%3E%3C/script%3E"));</script>
	</div>