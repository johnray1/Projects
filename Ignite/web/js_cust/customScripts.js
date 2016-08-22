
function  turn2DataTable(tableId){
    $('#'+tableId).DataTable( {
        "bLengthChange": true,
        "paging":true,
        "searching": true,
        "ordering": true,
        "order": [[ 0, "desc" ]],
        "lengthMenu": [ [10, 25, 50, "All"]],
		
        dom: 'Bfrtip',		 
        buttons: [
            'excelHtml5',
            'csvHtml5',
            'pdfHtml5'
        ]
    } );
}
