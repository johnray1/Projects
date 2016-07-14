


//----------------------------------------------------------container product---------------------------------------------------------------------------------------------------------------------------------------------


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
    
    var processed_json = new Array();
    
    $.getJSON('http://localhost:8080/PayFuel/TransactionManagementService/pieProductSale', function(data) {
        
        alert(data);
        // Populate series
        for (i = 0; i < data.length; i++){
            processed_json.push([data[i].name, data[i].y]);
        }
        
        // Build the chart
        $('#containerpro').highcharts({
            chart: {
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false,
                type: 'pie'
            },
            title: {
                text: ''
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



//----------------------------------------------------------container branch---------------------------------------------------------------------------------------------------------------------------------------------

$(function () {
    
    var processed_json = new Array();
    $.getJSON('http://localhost:8080/PayFuel/TransactionManagementService/topBranchSale', function(data) {
        
        // Populate series
        for (i = 0; i < data.length; i++){
            processed_json.push([data[i].name, data[i].y]);
        }
        
        $('#containerbra').highcharts({
            chart: {
                type: 'column'
            },
            title: {
                text: 'Top Branches '
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
                enabled: false
            },
            tooltip: {
                pointFormat: 'Sells: <b>{point.y:.1f} millions</b>'
            },
            series: [{
                    name: 'Branches',
                    data: processed_json,
                    color: '#f40030',
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

//----------------------------------------------------------container paymentmode---------------------------------------------------------------------------------------------------------------------------------------------

$(function () {
    $('#containerpm').highcharts({
        title: {
            text: 'Payment Methods per Products'
        },
        
        yAxis: {
            title: {
                text: 'Transaction Counts'
            }
        },
        xAxis: {
            categories: []
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
        series: [
            {
                type: 'column',
                name: 'CASH',
                color:'#000000',
                data:[
                    {name: 'SUPER',y: 2200}, 
                    {name: 'GASOIL',y: 700}
                ]
            }, 
            {
                type: 'column',
                name: 'MTN',
                color:'#ffc508',
                data: [
                    {name: 'SUPER',y: 2000}, 
                    {name: 'GASOIL',y: 800}]
            }, 
            {
                type: 'column',
                name: 'TIGO',
                color:'#193370',
                data: [
                    {name: 'SUPER',y: 1500}, 
                    {name: 'GASOIL',y: 400}
                ]
            },
            {
                type: 'pie',
                name: 'Total consumption',
                data: [
                    {name: 'Cash',y: 150,color:'#000000'}, 
                    {name: 'MTN Mobile Money',y: 20,color:'#ffc508'},
                    {name: 'Tigo Cash',y: 20,color:'#193370'}
                ],
                center: [225, 35],
                size: 80,
                showInLegend: false,
                dataLabels: {
                    enabled: false
                }
            }]
    });
});

//----------------------------------------------------------container user---------------------------------------------------------------------------------------------------------------------------------------------

$(function () {
    
    var processed_json = new Array();
    $.getJSON('http://localhost:8080/PayFuel/TransactionManagementService/topUserSale', function(data) {
        
        // Populate series
        for (i = 0; i < data.length; i++){
            processed_json.push([data[i].name, data[i].y]);
        }
        
        $('#containerus').highcharts({
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
                title: {
                    text: 'Amount in RWF'
                }
            },
            legend: {
                reversed: false
            },
            plotOptions: {
                series: {
                    stacking: 'medium'
                }
            },
            series: [{
                    name: 'Cash Transacted',
                    color:'#f40030',
                    data: processed_json
                }]
        });
    });
});


//----------------------------------------------------------container transaction---------------------------------------------------------------------------------------------------------------------------------------------

$(function () {
    $(document).ready(function () {
        Highcharts.setOptions({
            global: {
                useUTC: false
            }
        });

        $('#containertra').highcharts({
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
                text: 'Transactions Per Second'
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
});