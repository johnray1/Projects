/*TANKS LEVELS*/

window.onload = mySuperDiff;
function mySuperDiff() {

    var theo = document.getElementById("quantity_theo").value;
    var dip = document.getElementById("quantity_dip").value;

    document.getElementById("quantity_diff").innerHTML = "" + theo - dip + "<span>&nbsp;L</span>";


    var levelbg = (theo * 100) / 50000;

    document.getElementById("levelbg").style.height = "" + levelbg + "%";

};


/*HIDE NOZZLES*/
$('#nozzlep1n1,#nozzlep1n2,#nozzlep2n1,#nozzlep2n2,#nozzlep3n1').click(function () {
    alert("Change the STATUS of this NOZZLE");
    $(this).toggleClass("disable");
});
