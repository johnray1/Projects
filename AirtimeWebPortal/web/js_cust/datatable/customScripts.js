
function  turn2DataTable(tableId){
    $('#'+tableId).DataTable( {
        "bLengthChange": true,
        "paging": true,
        "searching": true,
        "ordering": true,
        "order": [[ 0, "desc" ]],
        "lengthMenu": [5, 10, 20, 50],
        dom: 'B<"clear">lfrtip',
        buttons: [
            'excelHtml5',
            'csvHtml5',
            'pdfHtml5'
        ]
    } );
}