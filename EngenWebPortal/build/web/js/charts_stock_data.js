


/*CHART QUANTIES PER DAY*/
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
    
    
        $('#productssoldperday').highcharts({
            chart: {
                type: 'column',
                style: {	fontFamily: 'ubuntu'}
            },
            title: {
                text: ''
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
                    data: superList
                
                },{
                    name: 'Gasoil',
                    color: '#d9d928',
                    borderRadius: '0',
                    data: gasoilList
                
                }]
        });
   
    });
});

/*End CHART QUANTIES PER DAY*/






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
        
    
    
        $('#productspersales').highcharts({
            chart: {
                type: 'column',
                style: {	fontFamily: 'ubuntu'}
            },
        
            title: {
                text: '   '
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






/*CHART TANKS*/
$(function () {
    
    var name = new Array();
    var quantity = new Array();
    $.getJSON('http://41.74.172.132:8080/EngenPayFuel/ChartManagementService/totalQuantityAllTankChart', function(data) {
        
        // Populate series
        for (i = 0; i < data.length; i++){
            name.push([data[i].name]);
            quantity.push([data[i].quantity]);
        }
        $('#tanks').highcharts({
            
            chart: {
                type: 'column',
                style: {	fontFamily: 'ubuntu'}
            },
            
            title: {
                fontSize:'14',
                text: ''
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
			'#057ac0', 
			'#d9d928'
                    ],
                    colorByPoint: true,
                    borderRadius: '0',
                    data: quantity
                }]
        });
    });
});
/*End CHART TANKS*/




