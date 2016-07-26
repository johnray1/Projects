
$(function () {
    $('#container10').highcharts({
        chart: {
            type: 'bar'
        },
        title: {
            text: 'Sales per Cards'
        },
        xAxis: {
            
            
            categories: ['1,500', '1,000', '750', '500', '400','200','100','50','10']
            
        },
        yAxis: {
            min: 0,
            title: {
                text: 'Amount in NAIRAS'
            }
        },
        legend: {
            enabled: false,
            floating: true
        },
        plotOptions: {
            series: {
                stacking: 'medium'
            }
        },
        series: [{
                name: 'Sales per MTN Recharge Bundle',
                color:'#ff5700',
                data: [260550, 250500,200500,180120,176000,130876,100110,80150,77890]
            }]
        
    });
});



$(function () {
    $('#containerpm').highcharts({
        title: {
            text: 'Payment Methods per Sales'
        },
        
        yAxis: {
            title: {
                text: 'Sales Amount'
            }
        },
        xAxis: {
            categories: ['Airtime']
        },
        
        credits: {
            enabled: false
        },
        series: [{
                type: 'column',
                name: 'Master Card',
                color:'#fcbb37',
                data: [2200]
            }, {
                type: 'column',
                name: 'VISA',
                color:'#1a1f71',
                data: [1700]
            }, {
                type: 'column',
                name: 'VERVE',
                color:'#18536e',
                data: [1500]
            },{
                type: 'column',
                name: 'OTHERS',
                color:'#adadad',
                data: [800]
            }]
    });
});