App.controller('explorerController', function($scope, $http, $location,
		$routeParams, stockService) {
	

	var handleGetMarketCapBySectorSuccess = function(data, status) {
		var pieSerieData = [];
		var total = 0.0;
		for(var i = 0; i < data.length; i++) {
			total += data[i].count;
		}
		
		for(var i = 0; i < data.length; i++) {
			var serieElement = { name:data[i].name, y:(data[i].count/total) * 100.0, info:"ADASD" };
			pieSerieData.push(serieElement);
		}
		
		$('#container2').highcharts({
	        chart: {
	            type: 'pie'
	        },
	        title: {
	            text: 'Sector distribution (Mkt Cap)'
	        },
	        subtitle: {
	            text: ''
	        },
	        plotOptions: {
	            series: {
	                dataLabels: {
	                    enabled: true,
	                    format: '{point.name}: {point.y:.1f}%'
	                }
	            }
	        },

	        tooltip: {
	            headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
	            pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.2f}%</b> of total<br/>'
	        },
	        series: [{
	            name: "Sectors",
	            colorByPoint: true,
	            data: pieSerieData
	        }]
		});
	};
	
	var handleGetCountBySectorSuccess = function(data, status) {
		var pieSerieData = [];
		var total = 0.0;
		for(var i = 0; i < data.length; i++) {
			total += data[i].count;
		}
		
		for(var i = 0; i < data.length; i++) {
			var serieElement = { name:data[i].name, y:(data[i].count/total) * 100.0 };
			pieSerieData.push(serieElement);
		}
		
		$('#container1').highcharts({
	        chart: {
	            type: 'pie'
	        },
	        title: {
	            text: 'Sector distribution (count)'
	        },
	        subtitle: {
	            text: ''
	        },
	        plotOptions: {
	            series: {
	                dataLabels: {
	                    enabled: true,
	                    format: '{point.name}: {point.y:.1f}%'
	                }
	            }
	        },

	        tooltip: {
	            headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
	            pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.2f}%</b> of total<br/>'
	        },
	        series: [{
	            name: "Sectors",
	            colorByPoint: true,
	            data: pieSerieData
	        }]
		});
	};
	
	stockService.getMarketCapBySector().success(handleGetMarketCapBySectorSuccess);
	stockService.getCountBySector().success(handleGetCountBySectorSuccess);
	
});