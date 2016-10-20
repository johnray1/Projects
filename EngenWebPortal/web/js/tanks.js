/*TANKS LEVELS*/
$('#q_super_theo').change(function () {
    $('#level').css('height', this.value);
});

$('#q_gasoil_theo').change(function () {
    $('#level').css('height', this.value);
});

$('#q_kerosene_theo').change(function () {
    $('#level').css('height', this.value);
});


window.onload = mySuperDiff;
function mySuperDiff() {

    var pms1_theo = document.getElementById("quantity_theo_pms1").value;
    var pms1_dip = document.getElementById("quantity_dip_pms1").value;

    var ago1_theo = document.getElementById("quantity_theo_ago1").value;
    var ago1_dip = document.getElementById("quantity_dip_ago1").value;

    var ker1_theo = document.getElementById("quantity_theo_ker1").value;
    var ker1_dip = document.getElementById("quantity_dip_ker1").value;


    document.getElementById("quantity_diff_pms1").innerHTML = "" + pms1_dip - pms1_theo + "<span>&nbsp;L</span>";
    document.getElementById("quantity_diff_ago1").innerHTML = "" + ago1_dip - ago1_theo + "<span>&nbsp;L</span>";
    document.getElementById("quantity_diff_ker1").innerHTML = "" + ker1_dip - ker1_theo + "<span>&nbsp;L</span>";


    var pms1_levelbg_30000 = (pms1_theo * 100) / 30000;
    var ago1_levelbg_20000 = (ago1_theo * 100) / 20000;
	var ker1_levelbg_11000 = (ker1_theo * 100) / 11000;

    document.getElementById("levelbg_pms1").style.height = "" + pms1_levelbg_30000 + "%";
    document.getElementById("levelbg_ago1").style.height = "" + ago1_levelbg_20000 + "%";
	document.getElementById("levelbg_ker1").style.height = "" + ker1_levelbg_11000 + "%";

};


/*HIDE NOZZLES*/
$('#nozzlep1n1,#nozzlep1n2,#nozzlep2n1,#nozzlep2n2,#nozzlep3n1').click(function () {
    alert("Change the STATUS of this NOZZLE");
    $(this).toggleClass("disable");
});
