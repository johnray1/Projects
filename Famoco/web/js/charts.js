

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

    // Build the chart
    $('#container').highcharts({
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
            data: [
                { name: 'Super', y: 56.33 },
                { name: 'Gasoil', y: 24.03 },
                { name: 'Kerosene', y: 10.38 }
          
            ]
        }]
    });
});
//]]> 



$(function () {
    $('#container10').highcharts({
        chart: {
            type: 'bar'
        },
        title: {
            text: 'Top 10 Seller Perfomance'
        },
        xAxis: {

            
			categories: ['John', 'Peter', 'Simon', 'Diane', 'Karoli','Jane','Maurice','Abu','Samantha','Lea']
			
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
            data: [260550, 250500,200500,180120,176000,130876,100110,80150,77890,60890]
        },{
            name: 'Working Hours per Day',
			color:'#012d90',
            data: [12,12,11,11,10,10,9,10,10,8]
        }]
    });
});


$(function () {
    $('#containerbranches').highcharts({
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
            data: [
                ['Branch 1', 29315100],
                ['Branch 2', 25222900],
                ['Branch 3', 24665800],
                ['Branch 4', 14215300],
                ['Branch 5', 12885200],
                ['Branch 6', 12895150],
                ['Branch 7', 11225100],
                ['Branch 8', 11115100],
                ['Branch 9', 11105100],
                ['Branch 10', 11000100]
            ],
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


$(function () {
    $(document).ready(function () {
        Highcharts.setOptions({
            global: {
                useUTC: false
            }
        });

        $('#containerlive').highcharts({
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
            categories: ['Super', 'Gasoil', 'Kerosene']
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
            data: [2200, 700, 500]
        }, {
            type: 'column',
            name: 'MTN',
            color:'#ffc508',
            data: [2000, 800, 300]
        }, {
            type: 'column',
            name: 'TIGO',
            color:'#193370',
            data: [1500, 400, 200]
        },{
            type: 'column',
            name: 'AIRTEL',
            color:'#ec1f27',
            data: [1400, 600, 100]
        },{
            type: 'pie',
            name: 'Total consumption',
            data: [{
                name: 'Cash',
                y: 50,
                color:'#000000' // Jane's color
            }, {
                name: 'MTN Mobile Money',
                y: 20,
                color: '#ffc508'// John's color
            }, {
                name: 'Tigo Cash',
                y: 20,
                color:'#193370' // John's color
            },{
                name: 'Airtel Money',
                y: 10,
                color:'#ec1f27' // Joe's color
            }],
            
            
            center: [225, 35],
            size: 80,
            showInLegend: false,
            dataLabels: {
                enabled: false
            }
        }]
    });
});
