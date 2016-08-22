//hq sales per month
$(function () {
    
    $('#sp_hq_salespermonth').highcharts({
        chart: {
            type: 'column',
            style: {	fontFamily: 'ubuntu'}
        },
        
        title: {
            text: 'Total Sale Amount:- Previous Month vs Current Month'
        },
        
        xAxis: {
            categories: ['June 2016', 'July 2016']
        },
        
        yAxis: {
            min: 0,
            labels: {
                align: 'right',
                format: '{value:.,0f} FRW'
            },
            title: {
                text: 'Amount'
            }
            
        },
        
        credits: {
            enabled: false
        },
        
        tooltip: {
            valueSuffix: ' FRW',
            headerFormat: '<span style="font-size:11px">Solar</span><br/>',
            pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b><br/>',
            shared: true
        },
        
        plotOptions: {            
            series: {
                animation: {duration: 2000}
            },
            
            column: {
                grouping: true,
                shadow: false,
                borderWidth: 0,				
                dataLabels: {
                    enabled: true,
                    pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b><br/>',
                    format: '{point.y:,.0f} FRW'
                }
            }
        },
        
        series: [{
                showInLegend: false,  
                name: 'Solar',
                color: '#e9ba30',
                borderRadius: '7',
                data: [20600,18000]
            }]
    });
});




//hq sales per day
$(function () {
    
    
    $('#sp_hq_salesperday').highcharts({
        chart: {
            type: 'column',
            style: {	fontFamily: 'ubuntu'}
        },
        title: {
            text: 'Total Sale Amount/Day'
        },
        
        xAxis: {
            categories: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24', '25', '26', '27', '28', '29', '30', '31'],
            crosshair: true
        },
        
        
        yAxis: {
            min: 0,
            labels: {
                align: 'right',
                format: '{value:.,0f} FRW'
            },
            title: {
                text: 'Amount'
            }
        },
        
        
        tooltip: {
            headerFormat: '<strong>Day {point.key}</strong><br />',
            pointFormat: 'Sold quantities of <span style="color:{series.color}">{series.name}: <b>{point.y:.1f}FRW</b></span><br />',
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
                showInLegend: false,  
                name: 'Solar',
                color: '#e9ba30',
                borderRadius: '3',
                data: [2141, 2547, 3212, 3554, 3850, 3776, 4007, 4352, 3914, 3910, 3301, 4050, 4700, 2803, 2915, 3116, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
            }]
    });
});



