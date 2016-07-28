
// product sale per day
$(function () {
    $('#sp_soldquantiesperday').highcharts({
        chart: {
            type: 'column',
            style: {	fontFamily: 'ubuntu'}
        },
        title: {
            text: 'Products Quantities Sold per DAY'
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
                data: [2141, 2547, 3212, 3554, 3850, 3776, 4007, 4352, 3914, 3910, 3301, 4050, 4700, 2803, 2915, 3116, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
                
            },{
                name: 'Gasoil',
                color: '#eddb11',
                borderRadius: '3',
                data: [1141, 1547, 2212, 1554, 2850, 1776, 3007, 2352, 2914, 2910, 2301, 2050, 2700, 1803, 1915, 1116, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
                
            }]
    });
});



// product sale goal
$(function () {
    
    var monthName = new Array();
    var superr = new Array();
    var gasoil = new Array();
    $.getJSON('http://localhost:8080/PayFuel/ChartManagementService/monthlyProductChart', function(data) {
        
        // Populate series
        for (i = 0; i < data.length; i++){
            monthName.push([data[i].name]);
            superr.push([data[i].superr]);
            gasoil.push([data[i].gasoil]);
        }
    
        $('#sp_goals').highcharts({
            chart: {
                type: 'column',
                style: {	fontFamily: 'ubuntu'}
            },
        
            title: {
                text: 'Product Sale PreMonth  vs  Product Sale CurMonth'
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
                        value:20000,
                        label: {
                            align: 'right',
                            style: {
                                color:'#000000',
                                fontWeight: 'bold',
                                fontSize: '15',
                                fontStyle: 'italic'
                            },
                            text: 'Goal for Super :20,000 L',
                            x: -10
                        },
                        zIndex: 111
                    },{
                        color: '#eddb11',
                        dashStyle: 'longdashdot',
                        width:2,
                        value:15000,
                        label: {
                            align: 'right',
                            style: {
                                color:'#000000',
                                fontWeight: 'bold',
                                fontSize: '15',
                                fontStyle: 'italic'
                            },
                            text: 'Goal for Gasoil: 15,000 L',
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



//tank
$(function () {
    
    var tankName = new Array();
    var maxCap = new Array();
    var curCap = new Array();
    $.getJSON('http://localhost:8080/PayFuel/ChartManagementService/tankChart', function(data) {
        
        // Populate series
        for (i = 0; i < data.length; i++){
            tankName.push([data[i].name]);
            maxCap.push([data[i].max]);
            curCap.push([data[i].current]);
        }
        
        $('#sp_tanks').highcharts({
        
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
                    name: 'Full Quantity',
                    color:'#e6e6e6',
                    borderRadius: '7',
                    data: maxCap
                }, 
                {
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



//payment mode
$(function () {
    $('#sp_payments').highcharts({
        
        chart: {
            style: {	fontFamily: 'ubuntu'}
        },
        
        title: {
            text: 'Payment Methods Usage per Products'
        },
        
        yAxis: {
            title: {
                text: 'Transaction Counts'
            }
        },
        xAxis: {
            categories: ['Super', 'Gasoil']
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
                data: [2200, 700]
            }, 
            
            {
                type: 'column',
                name: 'MTN',
                color:'#ffc508',
                borderRadius: '4',
                data: [2000, 800]
            }, 
            
            {
                type: 'column',
                name: 'TIGO',
                color:'#193370',
                borderRadius: '4',
                data: [1500, 400]
            },
            
            {
                type: 'column',
                name: 'AIRTEL',
                color:'#ec1f27',
                borderRadius: '4',
                data: [1400, 600]
            },
            
            {
                type: 'column',
                name: 'Voucher',
                color:'#7aaa75',
                borderRadius: '4',
                data: [2000, 350]
            },
            
            {
                type: 'column',
                name: 'Debt',
                color:'#808080',
                borderRadius: '4',
                data: [200, 100]
            },
            
            {
                type: 'column',
                name: 'Visa',
                color:'#1a1f71',
                borderRadius: '4',
                data: [1100, 200]
            },
            
            {
                type: 'column',
                name: 'Mastercard',
                color:'#fcbb37',
                borderRadius: '4',
                data: [1000, 300]
            },
            
            {
                type: 'column',
                name: 'SP Card',
                color:'#1a8c35',
                borderRadius: '4',
                data: [500, 200]
            }
            
        ]
    });
});

