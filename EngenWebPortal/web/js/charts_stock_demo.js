


/*CHART QUANTIES PER DAY*/
$(function () {
    $('#demo_productssoldperday').highcharts({
	chart: {
            type: 'column',
			style: {	fontFamily: 'ubuntu'},
        },
        title: {
            text: ''
        },

        xAxis: {
            categories: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24', '25', '26', '27', '28', '29', '30', '31'],
            crosshair: true
        },


        yAxis: {
            min: 0,
                labels: {
                    align: 'right',
                    format: '{value:.,0f} L'
                },
            title: {
                text: 'Quantity in Liters'
			},
			plotLines: [{
                color: '#057ac0',
                dashStyle: 'longdashdot',
                width: 2,
                value:40000,
                label: {
                    align: 'right',
                    style: {
                        color:'#000000',
						fontWeight: 'bold',
						fontSize: '15',
                        fontStyle: 'italic'
                    },
                    text: 'Goal for Super :45,000 L',
                    x: -10
                },
                zIndex: 111
            },{
                color: '#d9d928',
                dashStyle: 'longdashdot',
                width:2,
                value:20000,
                label: {
                    align: 'right',
                    style: {
                        color:'#000000',
						fontWeight: 'bold',
						fontSize: '15',
                        fontStyle: 'italic'
                    },
                    text: 'Goal for Gasoil: 20,000 L',
                    x: -10
                },
                zIndex: 111
            }]
            },


        tooltip: {
            headerFormat: '<strong>Day {point.key}</strong><br />',
            pointFormat: 'Sold quantities of <span style="color:{series.color}">{series.name}: <b>{point.y:.1f} L</b></span><br />',
            shared: true
        },

        plotOptions: {
            column: {
                pointPadding: 0,
                borderWidth: 0
            }
        },

		credits: {
            enabled: false
        },

        series: [{
            name: 'Super',
			color: '#057ac0',
			borderRadius: '0',
            data: [2141, 2547, 3212, 3554, 3850, 3776, 4007, 4352, 3914, 3910, 3301, 4050, 4700, 2803, 2915, 3116, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]

        },{
            name: 'Gasoil',
            color: '#d9d928',
			borderRadius: '0',
            data: [1141, 1547, 2212, 1554, 2850, 1776, 3007, 2352, 2914, 2910, 2301, 2050, 2700, 1803, 1915, 1116, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]

        }]
    });
});

/*End CHART QUANTIES PER DAY*/






/*CHART GOALS*/
$(function () {
    $('#demo_productspersales').highcharts({
        chart: {
            type: 'column',
			 style: {	fontFamily: 'ubuntu'},
        },
        
        title: {
            text: '   '
        },
        
        xAxis: {
            categories: ['August 2016'],
        },
        
        yAxis: {
            min: 0,
                labels: {
                    align: 'right',
                    format: '{value:.,0f} L'
                },
            title: {
                text: 'Tank Quantities in Liters'
            },
			plotLines: [{
                color: '#057ac0',
                dashStyle: 'longdashdot',
                width: 2,
                value:4000000,
                label: {
                    align: 'right',
                    style: {
                        color:'#000000',
						fontWeight: 'bold',
						fontSize: '15',
                        fontStyle: 'italic'
                    },
                    text: 'Goal for Super :4,000,000 L',
                    x: -10
                },
                zIndex: 111
            },{
                color: '#d9d928',
                dashStyle: 'longdashdot',
                width:2,
                value:2500000,
                label: {
                    align: 'right',
                    style: {
                        color:'#000000',
						fontWeight: 'bold',
						fontSize: '15',
                        fontStyle: 'italic'
                    },
                    text: 'Goal for Gasoil: 2,500,000 L',
                    x: -10
                },
                zIndex: 111
            }]
        },

        credits: {
            enabled: false
        },

        tooltip: {
			 valueSuffix: ' Liters',
			 headerFormat: '<span style="font-size:11px">Quantity Status</span><br>',
			 pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b><br/>',
            shared: true
        },

        plotOptions: {            
			series: {
                animation: {duration: 2000},
            },

			column: {
                grouping: true,
                shadow: false,
                borderWidth: 0,				
                dataLabels: {
                    enabled: true,
					pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b><br/>',
                    format: '{point.y:,.0f} Liters'
                }
            }
        },

        series: [{
            name: 'Super',
			color: '#057ac0',
			borderRadius: '0',
            data: [4000600]
        }, {
            name: 'Gasoil',
			color:"#d9d928",
			borderRadius: '0',
            data: [2800000]
        }]
    });
});

/*End CHART GOALS*/






/*CHART TANKS*/
$(function () {
    $('#demo_tanks').highcharts({
        
		chart: {
            type: 'column',
			 style: {	fontFamily: 'ubuntu'},
        },
        
        title: {
            fontSize:'14',
			text: ''
        },
        
        xAxis: {
            categories: ['Super', 'Gasoil']
        },
        
        yAxis: {
            min: 0,
                labels: {
                    align: 'right',
                    format: '{value:.,0f} L'
                },
            title: {
                text: 'Tank Quantities in Liters'
            }
        },

        credits: {
            enabled: false
        },

        tooltip: {
			 valueSuffix: ' Liters',
			 pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b><br/>',
            shared: true
        },

        plotOptions: {
			colorByPoint: true,
			series: {
                animation: {duration: 2000},
            },
            column: {
                grouping: false,
                shadow: false,
                borderWidth: 0,				
                dataLabels: {
                    enabled: true,
					pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b><br/>',
                    format: '{point.y:,.0f} Liters'
                }
            }
        },

        series: [{
            showInLegend: false,   
			name: 'Current Quantity',
			colors: [
			'#057ac0', 
			'#d9d928'
			],
			colorByPoint: true,
			borderRadius: '0',
            data: [6500000, 4000000]
        }]
    });
});
/*End CHART TANKS*/




/*CHART SALES*/
$(function () {
    $('#demo_sales').highcharts({

        chart: {
			 style: {	fontFamily: 'ubuntu'},
        },

        title: {
            text: 'Sales Comparison per Payment Method'
        },
        
        yAxis: {
			labels: {
                    align: 'right',
                    format: '{value:.,0f} FRW'
                },
            title: {
                text: 'Amount'
            }
        },
        xAxis: {
            categories: ['-']
        },

		credits: {
            enabled: false
        },
		
				
		series: [
		
		{
            type: 'column',
            name: 'CASH',
            color:'#000000',
			borderRadius: '0',
            data: [2200]
        }, 
		
		{
            type: 'column',
            name: 'MTN',
            color:'#ffc508',
			borderRadius: '0',
            data: [2000]
        }, 
		
		{
            type: 'column',
            name: 'TIGO',
            color:'#193370',
			borderRadius: '0',
            data: [1500]
        },
		
		{
            type: 'column',
            name: 'AIRTEL',
            color:'#ec1f27',
			borderRadius: '0',
            data: [1400]
        },

		{
            type: 'column',
            name: 'Voucher',
            color:'#7aaa75',
			borderRadius: '0',
            data: [2000]
        },

		{
            type: 'column',
            name: 'Debt',
            color:'#808080',
			borderRadius: '0',
            data: [200]
        },

		{
            type: 'column',
            name: 'Visa',
            color:'#1a1f71',
			borderRadius: '0',
            data: [1100]
        },

		{
            type: 'column',
            name: 'Mastercard',
            color:'#fcbb37',
			borderRadius: '0',
            data: [1000]
        },
		
		{
            type: 'column',
            name: 'SP Card',
            color:'#1a8c35',
			borderRadius: '0',
            data: [500]
        }

		]
    });
});
/* End CHART SALES*/