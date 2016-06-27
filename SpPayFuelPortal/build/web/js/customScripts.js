
function  turn2DataTable(tableId){
    $('#'+tableId).DataTable( {
        "bLengthChange": true,
        "paging":true,
        "searching": true,
        "ordering": true,
        "lengthMenu": [ [10, 25, 50, "All"]],
        
        dom: 'Bfrtip',		 
        buttons: [
            'excelHtml5',
            'csvHtml5',
            'pdfHtml5'
        ],
    } );
    
    $('#'+tableId+' tbody')
            .on( 'mouseenter', 'td', function () {
                var colIdx = table.cell(this).index().column;
        
        $( table.cells().nodes() ).removeClass( 'highlight' );
        $( table.column( colIdx ).nodes() ).addClass( 'highlight' );
    } );
    
}
