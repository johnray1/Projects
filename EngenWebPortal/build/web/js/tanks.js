/*TANKS LEVELS*/

window.onload = mySuperDiff;
function mySuperDiff(tankId) {
    var theo = document.getElementById("quantity_theo"+tankId).value;
    var dip = document.getElementById("quantity_dip"+tankId).value;
    document.getElementById("quantity_diff"+tankId).innerHTML = "" + theo - dip + "<span>&nbsp;L</span>";

    var levelbg = (theo * 100) / 50000;
    
    document.getElementById("levelbg"+tankId).style.height = "" + levelbg + "%";
};


/*HIDE NOZZLES*/
$('#nozzlep1n1,#nozzlep1n2,#nozzlep2n1,#nozzlep2n2,#nozzlep3n1').click(function () {
    alert("Change the STATUS of this NOZZLE");
    $(this).toggleClass("disable");
});
