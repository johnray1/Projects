
//hq tank chart
$(function () {
    
    var name = new Array();
    var quantity = new Array();
    $.getJSON('http://41.74.172.132:8080/EngenPayFuel/ChartManagementService/totalQuantityAllTankChart', function(data) {
        
        // Populate series
        for (i = 0; i < data.length; i++){
            name.push([data[i].name]);
            quantity.push([data[i].quantity]);
        }
        
        $('#sp_hq_tanks').highcharts({
        
            chart: {
                type: 'column',
                style: {	fontFamily: 'ubuntu'}
            },
        
            title: {
                fontSize:'14',
                text: 'Cumulative Current Tanks Capacity'
            },
        
            xAxis: {
                categories: name
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
        
            series: [{
                    showInLegend: false,   
                    name: 'Current Quantity',
                    colors: [
                        '#137cce', 
                        '#eddb11'
                    ],
                    colorByPoint: true,
                    borderRadius: '7',
                    data: quantity
                }]
        });
    });
});




//hq sales per month
$(function () {
    
    var monthName = new Array();
    var superr = new Array();
    var gasoil = new Array();
    $.getJSON('http://41.74.172.132:8080/EngenPayFuel/ChartManagementService/monthlyProductChart', function(data) {
        
        // Populate series
        for (i = 0; i < data.length; i++){
            monthName.push([data[i].name]);
            superr.push([data[i].superr]);
            gasoil.push([data[i].gasoil]);
        }
    
        $('#sp_hq_salespermonth').highcharts({
            chart: {
                type: 'column',
                style: {	fontFamily: 'ubuntu'}
            },
        
            title: {
                text: 'Product Sale :- Previous Month vs Current Month'
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
                    text: 'Product Quantities in Liters'
                },
                plotLines: [{
                        color: '#137cce',
                        dashStyle: 'longdashdot',
                        width: 2,
                        value:20,
                        label: {
                            align: 'right',
                            style: {
                                color:'#000000',
                                fontWeight: 'bold',
                                fontSize: '15',
                                fontStyle: 'italic'
                            },
                            text: 'Goal for Super :20 L',
                            x: -10
                        },
                        zIndex: 111
                    },{
                        color: '#eddb11',
                        dashStyle: 'longdashdot',
                        width:2,
                        value:15,
                        label: {
                            align: 'right',
                            style: {
                                color:'#000000',
                                fontWeight: 'bold',
                                fontSize: '15',
                                fontStyle: 'italic'
                            },
                            text: 'Goal for Gasoil: 15 L',
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
                headerFormat: '<span style="font-size:11px">Quantity Status</span><br/>',
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
                    color: '#137cce',
                    borderRadius: '7',
                    data: superr
                }, {
                    name: 'Gasoil',
                    color:"#eddb11",
                    borderRadius: '7',
                    data: gasoil
                }]
        });
    });
});



//hq sales per day
$(function () {
    var dateList = new Array();
    var superList = new Array();
    var gasoilList = new Array();
    $.getJSON('http://41.74.172.132:8080/EngenPayFuel/ChartManagementService/dailyProductChart', function(data) {
        
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
    
        $('#sp_hq_salesperday').highcharts({
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



//hq paymentmodes
$(function () {
    var cash = new Array();var voucher = new Array();var mtn = new Array();var tigo = new Array();
    var airtel = new Array();var visa = new Array();var master = new Array();var debt = new Array();var spCard = new Array();
    $.getJSON('http://41.74.172.132:8080/EngenPayFuel/ChartManagementService/paymentChart', function(data) {
        cash.push(data.cash);voucher.push(data.voucher);mtn.push(data.mtn);tigo.push(data.tigo);
        airtel.push(data.airtel);visa.push(data.visa);master.push(data.master);debt.push(data.debt);spCard.push(data.spCard);
        $('#sp_hq_payment').highcharts({
        
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
                    borderRadius: '7',
                    data: cash
                }, 
            
                {
                    type: 'column',
                    name: 'MTN',
                    color:'#ffc508',
                    borderRadius: '4',
                    data: mtn
                }, 
            
                {
                    type: 'column',
                    name: 'TIGO',
                    color:'#193370',
                    borderRadius: '4',
                    data: tigo
                },
            
                {
                    type: 'column',
                    name: 'AIRTEL',
                    color:'#ec1f27',
                    borderRadius: '4',
                    data: airtel
                },
            
                {
                    type: 'column',
                    name: 'Voucher',
                    color:'#7aaa75',
                    borderRadius: '4',
                    data: voucher
                },
            
                {
                    type: 'column',
                    name: 'Debt',
                    color:'#808080',
                    borderRadius: '4',
                    data: debt
                },
            
                {
                    type: 'column',
                    name: 'Visa',
                    color:'#1a1f71',
                    borderRadius: '4',
                    data: visa
                },
            
                {
                    type: 'column',
                    name: 'Mastercard',
                    color:'#fcbb37',
                    borderRadius: '4',
                    data: master
                }
            ]
        });
    });
});
