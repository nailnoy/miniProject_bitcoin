<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!DOCTYPE html>
    <html>
    <head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/index.scss">
    <title>chartBitcoin</title>
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>

    </head>
    <body>
        <div id="chart_div" style="width: 1200px; height: 500px;"></div>
 
      <script type="text/javascript">
	    let coinData;
	        
        console.log("loadChart() 호출");
        
        axios.get(`https://api.coingecko.com/api/v3/coins/${param.id}/market_chart?vs_currency=krw&days=7&interval=daily`
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

	<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
	<script type="text/javascript">
		google.charts.load('current', {packages: ['corechart', 'line']});
		google.charts.setOnLoadCallback(drawBasic);
	
		function drawBasic() {
	
		      var data = new google.visualization.DataTable();
		      data.addColumn('string', '날짜');
		      data.addColumn('number', `${param.id}(KRW)`);
	
		      data.addRows([
				[coinData[0][0], coinData[0][1]],
				[coinData[1][0], coinData[1][1]],
				[coinData[2][0], coinData[2][1]],
				[coinData[3][0], coinData[3][1]],
				[coinData[4][0], coinData[4][1]],
				[coinData[5][0], coinData[5][1]],
				[coinData[6][0], coinData[6][1]],
				[coinData[7][0], coinData[7][1]]
			  ]);
	
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

</body>
</html>