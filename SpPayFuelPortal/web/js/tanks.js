/*TANKS LEVELS*/
$('#q_super_theo').change(function () {
    $('#level').css('height', this.value);
});

$('#q_gasoil_theo').change(function () {
    $('#level').css('height', this.value);
});


/*HIDE NOZZLES*/
$('#nozzlep1n1,#nozzlep1n2,#nozzlep2n1,#nozzlep2n2,#nozzlep3n1').click(function () {
    alert("Change the STATUS of this NOZZLE");
    $(this).toggleClass("disable");
});


/*HIDE NOZZLES*/
window.onload = mySuperDiff;
function mySuperDiff() {

    var pms1_theo = document.getElementById("quantity_theo_pms1").value;
    var pms1_dip = document.getElementById("quantity_dip_pms1").value;

    var pms2_theo = document.getElementById("quantity_theo_pms2").value;
    var pms2_dip = document.getElementById("quantity_dip_pms2").value;

    var ago1_theo = document.getElementById("quantity_theo_ago1").value;
    var ago1_dip = document.getElementById("quantity_dip_ago1").value;

    document.getElementById("quantity_diff_pms1").innerHTML = "" + pms1_dip - pms1_theo + "<span>&nbsp;L</span>";
    document.getElementById("quantity_diff_pms2").innerHTML = "" + pms2_dip - pms2_theo + "<span>&nbsp;L</span>";
    document.getElementById("quantity_diff_ago1").innerHTML = "" + ago1_dip - ago1_theo + "<span>&nbsp;L</span>";

    var pms1_levelbg_20600 = (pms1_theo * 100) / 20600;
    var pms2_levelbg_11600 = (pms2_theo * 100) / 11600;
    var ago1_levelbg_20600 = (ago1_theo * 100) / 20600;

    document.getElementById("levelbg_pms1").style.height = "" + pms1_levelbg_20600 + "%";
    document.getElementById("levelbg_pms2").style.height = "" + pms2_levelbg_11600 + "%";
    document.getElementById("levelbg_ago1").style.height = "" + ago1_levelbg_20600 + "%";

};