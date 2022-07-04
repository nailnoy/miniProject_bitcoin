<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="./css/index.scss">
	<link rel="stylesheet" type="text/css" href="./css/App.css">
	<title>chartBinancecoin</title>
	
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>

	<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
	<script type="text/javascript">
		google.charts.load('current', {packages: ['corechart', 'line']});
		google.charts.setOnLoadCallback(drawBasic);
	
		function drawBasic() {
	
		      var data = new google.visualization.DataTable();
		      data.addColumn('string', '날짜');
		      data.addColumn('number', 'Binancecoin(KRW)');
	
		      data.addRows(coinData);
	
		      var options = {
		        hAxis: {
		          title: '날짜',
				  
		        },
	
		        vAxis: {
		          title: '가격'
		        }
		      };
	
		      var chart = new google.visualization.LineChart(document.getElementById('chart_div'));
		      
		      chart.draw(data, options);
		}
    </script>
	
</head>
<body>
    <div id="chart_div" style="width: 1200px; height: 500px;"></div>
    
	<button onclick="location.href='index.html'">처음으로 돌아가기</button>
	
	<script type="text/javascript">
	    let coinData;
	        
        axios.get('https://api.coingecko.com/api/v3/coins/binancecoin/market_chart?vs_currency=krw&days=7&interval=daily'
        ).then(function (reqdata){
          console.log(reqdata.data);
          coinData = reqdata.data.prices;
          
	      let idx = 0;
	      while(idx<8){
	    	  coinData[idx][0] = Unix_timestamp(coinData[idx][0]);
	    	  idx++;
	      }
    
        }).catch(function(errdata){
          console.log('발생된 문제', errdata);
        });
	        
	    function Unix_timestamp(t){
	    	    var date = new Date(t);
	    	    var year = date.getFullYear();
	    	    var month = "0" + (date.getMonth()+1);
	    	    var day = "0" + date.getDate();
	    	    var hour = "0" + date.getHours();
	    	    var minute = "0" + date.getMinutes();
	    	    return year + "-" + month.substr(-2) + "-" + day.substr(-2) + " " + hour.substr(-2) + ":" + minute.substr(-2);
	    }
      </script>

</body>
</html>