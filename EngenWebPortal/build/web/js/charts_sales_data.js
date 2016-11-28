

$(function () {
    
    $('#livetransactions').highcharts({
        chart: {
            type: 'spline',
            animation: Highcharts.svg, // don't animate in old IE
            marginRight: 10,
            
            events: {
                load: function () {
                    
                    // set up the updating of the chart each second
                    var series = this.series[0];
                    setInterval(function () {
                        var x = (new Date()).getTime(), // current time
                        y = Math.random();
                        series.addPoint([x, y], true, true);
                    }, 1000);
                }
            }
        },
        title: {
            text: ''
        },
        
        credits: {
            enabled: false
        },
        
        xAxis: {
            type: 'datetime',
            tickPixelInterval: 150
        },
        yAxis: {
            title: {
                text: 'Transactions counts'
            },
            
            plotLines: [{
                    value: 0,
                    width: 1,
                    color: '#808080'
                }]
        },
        tooltip: {
            formatter: function () {
                return '<b>' + this.series.name + '</b><br/>' +
                        Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) + '<br/>' +
                        Highcharts.numberFormat(this.y, 2);
            }
        },
        legend: {
            enabled: false
        },
        exporting: {
            enabled: false
        },
        series: [{
                name: 'RWF',
                color:'#f40030',
                data: (function () {
                    // generate an array of random data
                    var data = [],
                    time = (new Date()).getTime(),
                    i;
                    
                    for (i = -19; i <= 0; i += 1) {
                        data.push({
                            x: time + i * 1000,
                            y: Math.random()
                        });
                    }
                    return data;
                }())
            }]
    });
});


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
        $('#productpie').highcharts({
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
                        { name: 'Super', color: '#057ac0', y: data.superAmount },
                        { name: 'Gasoil', color: '#d9d928', y: data.gasoilAmount },
                        { name: 'Kerosene', color: '#bf9e64', y: data.keroseneAmount }
                    ]
                }]
        });
        
    });
});


$(function () {
    var branchList = new Array();
    $.getJSON('http://41.74.172.132:8080/EngenPayFuel/ChartManagementService/branchChart/'+from+'/'+to, function(data) {
       
       // Populate series
        for (i = 0; i < data.length; i++){
            branchList.push([data[i].branchName,data[i].income]);
        }
        
        $('#branches').highcharts({
            chart: {
                type: 'column'
            },
            title: {
                text: ''
            },
            
            credits: {
                enabled: false
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
                    text: 'Sells in RWF'
                }
            },
            legend: {
                enabled: false
            },
            tooltip: {
                pointFormat: 'Sells: <b>{point.y:.1f} rwf</b>'
            },
            series: [{
                    name: 'Branches',
                    data: branchList,
                    color: '#042c90',
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


$(function () {
    var cash = new Array();var voucher = new Array();var mtn = new Array();var tigo = new Array();
    var airtel = new Array();var visa = new Array();var master = new Array();var debt = new Array();var engenCard = new Array();
    
    $.getJSON('http://41.74.172.132:8080/EngenPayFuel/ChartManagementService/paymentChart/'+branchId+'/'+from+'/'+to, function(data) {
        cash.push(data.cash);voucher.push(data.voucher);mtn.push(data.mtn);tigo.push(data.tigo);
        airtel.push(data.airtel);visa.push(data.visa);master.push(data.master);debt.push(data.debt);engenCard.push(data.engenCard);
        
        $('#paymethods').highcharts({
            
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
                    name: 'Engencard',
                    color:'#808080',
                    borderRadius: '4',
                    data: engenCard
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







/*
$(function () {
    var cash = new Array();var voucher = new Array();var mtn = new Array();var tigo = new Array();
    var airtel = new Array();var visa = new Array();var master = new Array();var debt = new Array();var engenCard = new Array();
    $.getJSON('http://41.74.172.132:8080/EngenPayFuel/ChartManagementService/paymentChartByProduct', function(data) {
        // Populate series
        for (i = 0; i < data.length; i++){
            cash.push([data[i].cash]);
            mtn.push([data[i].mtn]);
            tigo.push([data[i].tigo]);
            airtel.push([data[i].airtel]);
            visa.push([data[i].visa]);
            master.push([data[i].master]);
        }

        $('#paymethods').highcharts({
            title: {
                text: ''
            },

            credits: {
                enabled: false
            },

            yAxis: {
                title: {
                    text: 'Transaction Counts'
                }
            },
            xAxis: {
                categories: ['Super', 'Gasoil']
            },
            labels: {
                items: [{
                        html: 'Payment Methods Usage',
                        style: {
                            left: '180px',
                            top: '0px',
                            color: (Highcharts.theme && Highcharts.theme.textColor) || 'black'
                        }
                    }]
            },
            series: [{
                    type: 'column',
                    name: 'CASH',
                    color:'#000000',
                    data: cash
                }, {
                    type: 'column',
                    name: 'MTN',
                    color:'#ffc508',
                    data: mtn
                }, {
                    type: 'column',
                    name: 'TIGO',
                    color:'#193370',
                    data: tigo
                },{
                    type: 'column',
                    name: 'AIRTEL',
                    color:'#ec1f27',
                    data: airtel
                },{
                    type: 'column',
                    name: 'Visa',
                    color:'#1a1f71',
                    data: visa
                },
                {
                    type: 'column',
                    name: 'Mastercard',
                    color:'#fcbb37',
                    data: master
                }
            ]
        });
    });
});

CHART ALL PAYMENTS METHODS*/