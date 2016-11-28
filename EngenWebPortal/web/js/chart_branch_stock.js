
//tank
$(function () {
    
    var tankName = new Array();
    var maxCap = new Array();
    var curCap = new Array();
    $.getJSON('http://41.74.172.132:8080/EngenPayFuel/ChartManagementService/tankChart/'+branchId, function(data) {
        
        // Populate series
        for (i = 0; i < data.length; i++){
            tankName.push([data[i].name]);
            maxCap.push([data[i].max]);
            curCap.push([data[i].current]);
        }
        
        $('#tanks').highcharts({
            
            chart: {
                type: 'column',
                style: {	fontFamily: 'ubuntu'}
            },
            
            title: {
                fontSize:'14',
                text: 'Current Quantitiy vs Maximum Capacity'
            },
            
            xAxis: {
                categories: tankName
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
                headerFormat: '<span style="font-size:11px">Quantity Status</span><br/>',
                pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b><br/>',
                shared: true
            },
            
            plotOptions: {
                colorByPoint: true,
                series: {
                    animation: {duration: 2000}
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
            
            series: [
                
                {
                    showInLegend: false, 
                    name: 'Full Quantity',
                    color:'#e6e6e6',
                    borderRadius: '7',
                    data: maxCap
                }, 
                {
                    showInLegend: false, 
                    name: 'Current Quantity',
                    colors: [
                        '#137cce', 
                        '#137cce', 
                        '#eddb11'
                    ],
                    colorByPoint: true,
                    borderRadius: '7',
                    data: curCap
                }
            ]
        });
    });
});


/*CHART QUANTIES PER DAY*/
// product sale per day
$(function () {
    var dateList = new Array();
    var superList = new Array();
    var gasoilList = new Array();
    $.getJSON('http://41.74.172.132:8080/EngenPayFuel/ChartManagementService/dailyProductChart/'+branchId, function(data) {
        
        // Populate series
        for (i = 0; i < data.day.length; i++){
            dateList.push(data.day[i]);
        }
        
        for (j = 0; j < data.superList.length; j++){
            superList.push(data.superList[j]);
        }
        
        for (k = 0; k < data.gasoilList.length; k++){
            gasoilList.push(data.gasoilList[k]);
        }
    
        $('#productsaleperday').highcharts({
            chart: {
                type: 'column',
                style: {	fontFamily: 'ubuntu'}
            },
            title: {
                text: 'Product Quantities Sold/Day'
            },
        
            xAxis: {
                categories: dateList,
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
                }
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
                    color: '#137cce',
                    borderRadius: '3',
                    data: superList
                },{
                    name: 'Gasoil',
                    color: '#eddb11',
                    borderRadius: '3',
                    data: gasoilList
                }]
        });
    });

});

/*End CHART QUANTIES PER DAY*/






/*CHART GOALS*/
$(function () {
    
    // Make monochrome colors and set them as default for all pies
    Highcharts.getOptions().plotOptions.pie.colors = (function () {
        var colors = [],
        base = Highcharts.getOptions().colors[0],
        i;
        
        for (i = 0; i < 10; i += 1) {
            // Start out with a darkened base color (negative brighten), and end
            // up with a much brighter color
            colors.push(Highcharts.Color('#f40030').brighten((i - 3) / 5).get());
        }
        return colors;
    }());
    
    $.getJSON('http://41.74.172.132:8080/EngenPayFuel/ChartManagementService/productPie/'+branchId+'/'+from+'/'+to, function(data) {
        
        // Build the chart
        $('#productspie').highcharts({
            chart: {
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false,
                type: 'pie'
            },
            title: {
                text: ''
            },
            
            credits: {
                enabled: false
            },
            
            tooltip: {
                pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: true,
                        format: '<b>{point.name}</b>: {point.percentage:.1f} %'                  
                    }
                }
            },
            series: [{
                    name: 'Products',
                    data: [
                        { name: 'Super', color: '#057ac0', y: 56.43 },
                        { name: 'Gasoil', color: '#d9d928', y: 70.15 },
                        { name: 'Kerosene', color: '#bf9e64', y: data.keroseneAmount }
                        
                    ]
                }]
        });
        
    });
});



/*CHART GOALS*/
$(function () {
    var monthName = new Array();
    var superr = new Array();
    var gasoil = new Array();
    $.getJSON('http://41.74.172.132:8080/EngenPayFuel/ChartManagementService/monthlyProductChart/'+branchId, function(data) {
        
        // Populate series
       
        monthName.push([data[1].name]);
        superr.push([data[1].superr]);
        gasoil.push([data[1].gasoil]);
        
    
    
        $('#productsalepermonth').highcharts({
            chart: {
                type: 'column',
                style: {	fontFamily: 'ubuntu'}
            },
        
            title: {
                text: 'Product Sold/Month'
            },
        
            xAxis: {
                categories: monthName
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
                    animation: {duration: 2000}
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
                    data: superr
                }, {
                    name: 'Gasoil',
                    color:"#d9d928",
                    borderRadius: '0',
                    data: gasoil
                }]
        });
    });
});

/*End CHART GOALS*/











/*CHART SALES*/
$(function () {
    $('#demo_sales').highcharts({
        
        chart: {
            style: {	fontFamily: 'ubuntu'}
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