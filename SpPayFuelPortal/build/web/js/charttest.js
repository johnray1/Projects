
//----------------------------------------------------------tanks----------------------------------------------------------
$(function () {
    
    var processed_json = new Array();
    $.getJSON('http://localhost:8080/PayFuel/TransactionManagementService/tankChart', function(data) {
        
        // Populate series
        for (i = 0; i < data.length; i++){
            processed_json.push([data[i].name, data[i].y]);
        }
        
        
        
        $('#sp_tanks').highcharts({
            chart: {
                type: 'column'
            },
            title: {
                text: 'Tanks Quantity '
            },
            subtitle: {
                text: ''
            },
            xAxis: {
                type: 'category',
                labels: {
                    rotation: -45,
                    style: {
                        fontSize: '11px',
                        fontFamily: 'Verdana, sans-serif'
                    }
                }
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
            legend: {
                enabled: false
            },
            
            credits: {
                enabled: false
            },
            
            tooltip: {
                pointFormat: 'Quantity: <b>{point.y:.1f} Liters</b>'
            },
            series: [{
                    name: 'Branches',
                    color:'#808080',
                    data: processed_json,
                    zones: [
                        {value: 0,
                            color: '#ff0000'
                        }, 
                        
                        {value: 2500,
                            color: '#ff0000'
                        },
                        
                        
                        {value: 10000,
                            color: '#ff6600'
                        },
                        
                        {value:20000,
                            color: '#1a8c35'
                        },
                        
                        {value:20600,
                            color: '#1a8c35'
                        }
                        
                    ],
                    
                    dataLabels: {
                        enabled: false,
                        rotation: 0,
                        color: '#FFFFFF',
                        align: 'right',
                        format: '{point.y:,.1f}', // one decimal
                        y: 20, // 10 pixels down from the top
                        style: {
                            fontSize: '10px',
                            fontFamily: 'Verdana, sans-serif'
                        }
                    }
                }]
        });
    });
});



//----------------------------------------------------------products----------------------------------------------------------
$(function () {
    
    // Make monochrome colors and set them as default for all pies
    Highcharts.getOptions().plotOptions.pie.colors = (function () {
        var colors = [],
        base = Highcharts.getOptions().colors[0],
        i;
        
        for (i = 0; i < 10; i += 1) {
            // Start out with a darkened base color (negative brighten), and end
            // up with a much brighter color
            colors.push(Highcharts.Color('#eddb10').brighten((i - 1) / 10).get());
        }
        return colors;
    }());
    
    var processed_json = new Array();
    
    $.getJSON('http://localhost:8080/PayFuel/TransactionManagementService/pieProductSale', function(data) {
        
        
        // Populate series
        for (i = 0; i < data.length; i++){
            processed_json.push([data[i].name, data[i].y]);
        }
        
        // Build the chart
        $('#sp_products').highcharts({
            chart: {
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false,
                type: 'pie'
            },
            title: {
                text: 'Products Sold'
            },
            tooltip: {
                pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
            },
            
            credits: {
                enabled: false
            },
            
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: true,
                        format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                        style: {
                            color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'gray'
                        }
                    }
                }
            },
            series: [{
                    name: 'Products',
                    data: processed_json
                }]
        });
    });
});


//----------------------------------------------------------paymentmodes----------------------------------------------------------
$(function () {
    $('#sp_payments').highcharts({
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
                data: [2200, 700]
            }, 
            
            {
                type: 'column',
                name: 'MTN',
                color:'#ffc508',
                data: [2000, 800]
            }, 
            
            {
                type: 'column',
                name: 'TIGO',
                color:'#193370',
                data: [1500, 400]
            },
            
            {
                type: 'column',
                name: 'AIRTEL',
                color:'#ec1f27',
                data: [1400, 600]
            },
            
            {
                type: 'column',
                name: 'Voucher',
                color:'#7aaa75',
                data: [2000, 350]
            },
            
            {
                type: 'column',
                name: 'Debt',
                color:'#808080',
                data: [200, 100]
            },
            
            {
                type: 'column',
                name: 'Visa',
                color:'#1a1f71',
                data: [1100, 200]
            },
            
            {
                type: 'column',
                name: 'Mastercard',
                color:'#fcbb37',
                data: [1000, 300]
            },
            
            {
                type: 'column',
                name: 'SP Card',
                color:'#1a8c35',
                data: [500, 200]
            }
            
        ]
    });
});



//----------------------------------------------------------branches----------------------------------------------------------
$(function () {
    
    var processed_json = new Array();
    $.getJSON('http://localhost:8080/PayFuel/TransactionManagementService/topBranchSale', function(data) {
        
        // Populate series
        for (i = 0; i < data.length; i++){
            processed_json.push([data[i].name, data[i].y]);
        }
        
        $('#sp_topbranches').highcharts({
            chart: {
                type: 'column'
            },
            title: {
                text: 'Top 10 Selling Branches '
            },
            subtitle: {
                text: ''
            },
            xAxis: {
                type: 'category',
                labels: {
                    rotation: -45,
                    style: {
                        fontSize: '11px',
                        fontFamily: 'Verdana, sans-serif'
                    }
                }
            },
            yAxis: {
                min: 0,
                title: {
                    text: 'Sells in RWF millions'
                }
            },
            legend: {
                enabled: false,
                floating: true
            },
            
            credits: {
                enabled: false
            },
            
            tooltip: {
                pointFormat: 'Sells: <b>{point.y:.1f} RWF</b>'
            },
            
            series: [{
                    name: 'Branches',
                    data:processed_json,
                    color: '#1a8c35',
                    dataLabels: {
                        enabled: false,
                        rotation: 0,
                        color: '#FFFFFF',
                        align: 'right',
                        format: '{point.y:,.1f}', // one decimal
                        y: 20, // 10 pixels down from the top
                        style: {
                            fontSize: '10px',
                            fontFamily: 'Verdana, sans-serif'
                        }
                    }
                }]
        });
    });
});


//----------------------------------------------------------sellers----------------------------------------------------------
$(function () {
    
    var processed_json = new Array();
    $.getJSON('http://localhost:8080/PayFuel/TransactionManagementService/topUserSale', function(data) {
        
        // Populate series
        for (i = 0; i < data.length; i++){
            processed_json.push([data[i].name, data[i].y]);
        }
        
        $('#sp_topseller').highcharts({
            chart: {
                type: 'bar'
            },
            title: {
                text: 'Top 10 Seller Perfomance'
            },
            xAxis: {
                categories: []
            },
            
            yAxis: {
                min: 0,
                labels: {
                    format: '{value:.,0f} RwF'
                },
                title: {
                    text: 'Amount in RWF'
                }
            },
            
            legend: {
                enabled: false,
                floating: true
            },
            
            credits: {
                enabled: false
            },
            
            plotOptions: {
                series: {
                    stacking: 'medium'
                }
            },
            
            tooltip: {
                pointFormat: 'Amount Sold: <b>{point.y:.1f} RWF</b>'
            },
            
            series: [{
                    color:'#215cca',
                    data: processed_json
                }]
            
        });
    });
});